package co.edu.usbcali.ecommerceusb.dto;

import lombok.Getter;

@Getter
public class CreateOrderItemRequest {

    private Integer orderId;
    private Integer productId;
    private Integer quantity;

}