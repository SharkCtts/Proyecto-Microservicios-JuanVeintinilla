package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateOrderItemRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;
import co.edu.usbcali.ecommerceusb.mapper.OrderItemMapper;
import co.edu.usbcali.ecommerceusb.model.OrderItems;
import co.edu.usbcali.ecommerceusb.model.Orders;
import co.edu.usbcali.ecommerceusb.model.Products;
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
        List<OrderItems> list = repository.findAll();

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

        OrderItems item = repository.findById(id)
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
        Orders order = ordersRepository.findById(request.getOrderId())
                .orElseThrow(() -> new Exception("La orden no existe"));

        Products product = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("El producto no existe"));

        // 🔥 SNAPSHOT DEL PRECIO (esto es CLAVE)
        OrderItems item = OrderItems.builder()
                .order(order)
                .product(product)
                .quantity(request.getQuantity())
                .unitPriceSnapshot(product.getPrice()) // 🔥 importante
                .build();

        OrderItems saved = repository.save(item);

        return OrderItemMapper.modelToResponse(saved);
    }
}