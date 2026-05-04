package co.edu.usbcali.ecommerceusb.dto;

import co.edu.usbcali.ecommerceusb.model.PaymentStatus;
import lombok.Getter;

@Getter
public class CreatePaymentRequest {

    private Integer orderId;
    private PaymentStatus status;
    private String providerRef;
    private String idempotencyKey;

}