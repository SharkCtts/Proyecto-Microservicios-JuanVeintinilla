package co.edu.usbcali.ecommerceusb.dto;

import lombok.Getter;

@Getter
public class CreateCartItemRequest {

    private Integer cartId;
    private Integer productId;
    private Integer quantity;

}