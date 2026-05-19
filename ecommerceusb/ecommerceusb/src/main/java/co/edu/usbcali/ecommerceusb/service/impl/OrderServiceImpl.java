package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateOrderRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderResponse;
import co.edu.usbcali.ecommerceusb.mapper.OrderMapper;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.OrdersRepository;
import co.edu.usbcali.ecommerceusb.repository.UsersRepository;
import co.edu.usbcali.ecommerceusb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<OrderResponse> getAll() {
        List<Order> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return OrderMapper.modelToResponseList(list);
    }

    @Override
    public OrderResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Orden no encontrada con id: " + id)
                );

        return OrderMapper.modelToResponse(order);
    }

    @Override
    public OrderResponse create(CreateOrderRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (request.getUserId() == null || request.getUserId() <= 0) {
            throw new Exception("userId inválido");
        }

        // 🔹 Buscar usuario
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new Exception("El usuario no existe"));

        // 🔹 Validar total
        BigDecimal total = request.getTotalAmount() != null
                ? request.getTotalAmount()
                : BigDecimal.ZERO;

        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("El total no puede ser negativo");
        }

        // 🔹 Crear orden
        Order order = Order.builder()
                .user(user)
                .totalAmount(total)
                .currency(request.getCurrency() != null ? request.getCurrency() : "COP")
                .build();

        Order saved = repository.save(order);

        return OrderMapper.modelToResponse(saved);
    }

    // FUNCION PARA PUT

    @Override
    public OrderResponse update(Integer id, CreateOrderRequest request) throws Exception {

        // 🔹 Validar ID
        if (id == null) {
            throw new Exception("El id es obligatorio");
        }

        // 🔹 Buscar orden
        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Orden no encontrada con id: " + id)
                );

        // 🔹 Validar request
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (request.getUserId() == null || request.getUserId() <= 0) {
            throw new Exception("userId inválido");
        }

        // 🔹 Buscar usuario
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new Exception("El usuario no existe")
                );

        // 🔹 Validar total
        BigDecimal total = request.getTotalAmount() != null
                ? request.getTotalAmount()
                : BigDecimal.ZERO;

        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("El total no puede ser negativo");
        }

        // 🔹 Currency por defecto
        String currency = request.getCurrency() != null &&
                !request.getCurrency().isBlank()
                ? request.getCurrency()
                : "COP";

        // 🔹 Actualizar datos
        order.setUser(user);
        order.setTotalAmount(total);
        order.setCurrency(currency);

        // 🔹 Guardar
        Order updated = repository.save(order);

        // 🔹 Retornar
        return OrderMapper.modelToResponse(updated);
    }

}