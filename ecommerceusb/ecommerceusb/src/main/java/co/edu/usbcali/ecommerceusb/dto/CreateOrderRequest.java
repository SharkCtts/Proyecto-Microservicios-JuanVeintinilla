package co.edu.usbcali.ecommerceusb.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateOrderRequest {

    private Integer userId;
    private BigDecimal totalAmount;
    private String currency;

}