package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.PaymentResponse;
import co.edu.usbcali.ecommerceusb.model.Payment;

import java.util.List;

public class PaymentMapper {

    public static PaymentResponse modelToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrder() != null ? payment.getOrder().getId() : null)
                .status(payment.getStatus())
                .providerRef(payment.getProviderRef())
                .idempotencyKey(payment.getIdempotencyKey())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    public static List<PaymentResponse> modelToResponseList(List<Payment> list) {
        return list.stream()
                .map(PaymentMapper::modelToResponse)
                .toList();
    }
}