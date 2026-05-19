package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateOrderItemRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;
import co.edu.usbcali.ecommerceusb.mapper.OrderItemMapper;
import co.edu.usbcali.ecommerceusb.model.OrderItem;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.repository.OrderItemsRepository;
import co.edu.usbcali.ecommerceusb.repository.OrdersRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductsRepository;
import co.edu.usbcali.ecommerceusb.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemsRepository repository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<OrderItemResponse> getAll() {
        List<OrderItem> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return OrderItemMapper.modelToResponseList(list);
    }

    @Override
    public OrderItemResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        OrderItem item = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("OrderItem no encontrado con id: " + id)
                );

        return OrderItemMapper.modelToResponse(item);
    }

    @Override
    public OrderItemResponse create(CreateOrderItemRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (request.getOrderId() == null || request.getOrderId() <= 0) {
            throw new Exception("orderId inválido");
        }

        if (request.getProductId() == null || request.getProductId() <= 0) {
            throw new Exception("productId inválido");
        }

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new Exception("Cantidad inválida");
        }

        // 🔹 Buscar relaciones
        Order order = ordersRepository.findById(request.getOrderId())
                .orElseThrow(() -> new Exception("La orden no existe"));

        Product product = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("El producto no existe"));

        // 🔥 SNAPSHOT DEL PRECIO (esto es CLAVE)
        OrderItem item = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(request.getQuantity())
                .unitPriceSnapshot(product.getPrice()) // 🔥 importante
                .build();

        OrderItem saved = repository.save(item);

        return OrderItemMapper.modelToResponse(saved);
    }

    // FUNCION PARA EL PUT

    @Override
    public OrderItemResponse update(Integer id, CreateOrderItemRequest request) throws Exception {

        // 🔹 Validar ID
        if (id == null) {
            throw new Exception("El id es obligatorio");
        }

        // 🔹 Buscar OrderItem existente
        OrderItem item = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("OrderItem no encontrado con id: " + id)
                );

        // 🔹 Validar request
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (request.getOrderId() == null || request.getOrderId() <= 0) {
            throw new Exception("orderId inválido");
        }

        if (request.getProductId() == null || request.getProductId() <= 0) {
            throw new Exception("productId inválido");
        }

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new Exception("Cantidad inválida");
        }

        // 🔹 Buscar relaciones
        Order order = ordersRepository.findById(request.getOrderId())
                .orElseThrow(() ->
                        new Exception("La orden no existe")
                );

        Product product = productsRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new Exception("El producto no existe")
                );

        // 🔥 VALIDAR DUPLICADO order-product
        boolean exists = repository.findAll().stream()
                .anyMatch(oi ->
                        !oi.getId().equals(id) &&
                                oi.getOrder().getId().equals(request.getOrderId()) &&
                                oi.getProduct().getId().equals(request.getProductId())
                );

        if (exists) {
            throw new Exception("Ya existe ese producto en la orden");
        }

        // 🔹 Actualizar datos
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        // 🔥 IMPORTANTE:
        // NO actualizar unitPriceSnapshot
        // porque representa el precio histórico

        // 🔹 Guardar
        OrderItem updated = repository.save(item);

        // 🔹 Retornar
        return OrderItemMapper.modelToResponse(updated);
    }

    //DELETE
    @Override
    public void delete(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        OrderItem item = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("OrderItem no encontrado con id: " + id)
                );

        repository.delete(item);
    }

}