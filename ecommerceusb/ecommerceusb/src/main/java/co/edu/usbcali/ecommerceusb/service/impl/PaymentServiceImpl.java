package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreatePaymentRequest;
import co.edu.usbcali.ecommerceusb.dto.PaymentResponse;
import co.edu.usbcali.ecommerceusb.mapper.PaymentMapper;
import co.edu.usbcali.ecommerceusb.model.Orders;
import co.edu.usbcali.ecommerceusb.model.Payments;
import co.edu.usbcali.ecommerceusb.repository.OrdersRepository;
import co.edu.usbcali.ecommerceusb.repository.PaymentsRepository;
import co.edu.usbcali.ecommerceusb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentsRepository repository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<PaymentResponse> getAll() {
        List<Payments> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return PaymentMapper.modelToResponseList(list);
    }

    @Override
    public PaymentResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        Payments payment = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Pago no encontrado con id: " + id)
                );

        return PaymentMapper.modelToResponse(payment);
    }

    @Override
    public PaymentResponse create(CreatePaymentRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (request.getOrderId() == null || request.getOrderId() <= 0) {
            throw new Exception("orderId inválido");
        }

        if (request.getStatus() == null) {
            throw new Exception("El status es obligatorio");
        }

        if (request.getIdempotencyKey() == null || request.getIdempotencyKey().isBlank()) {
            throw new Exception("idempotencyKey obligatorio");
        }

        // VALIDACIÓN IMPORTANTE WE
        boolean exists = repository.findAll().stream()
                .anyMatch(p -> p.getIdempotencyKey().equals(request.getIdempotencyKey()));

        if (exists) {
            throw new Exception("Ya existe un pago con ese idempotencyKey");
        }

        // 🔹 Buscar order
        Orders order = ordersRepository.findById(request.getOrderId())
                .orElseThrow(() -> new Exception("La orden no existe"));

        // 🔹 Crear entidad
        Payments payment = Payments.builder()
                .order(order)
                .status(request.getStatus())
                .providerRef(request.getProviderRef())
                .idempotencyKey(request.getIdempotencyKey())
                .build();

        Payments saved = repository.save(payment);

        return PaymentMapper.modelToResponse(saved);
    }
}