package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateOrderRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderResponse;
import co.edu.usbcali.ecommerceusb.mapper.OrderMapper;
import co.edu.usbcali.ecommerceusb.model.Orders;
import co.edu.usbcali.ecommerceusb.model.Users;
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
        List<Orders> list = repository.findAll();

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

        Orders order = repository.findById(id)
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
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new Exception("El usuario no existe"));

        // 🔹 Validar total
        BigDecimal total = request.getTotalAmount() != null
                ? request.getTotalAmount()
                : BigDecimal.ZERO;

        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("El total no puede ser negativo");
        }

        // 🔹 Crear orden
        Orders order = Orders.builder()
                .user(user)
                .totalAmount(total)
                .currency(request.getCurrency() != null ? request.getCurrency() : "COP")
                .build();

        Orders saved = repository.save(order);

        return OrderMapper.modelToResponse(saved);
    }
}