package co.edu.usbcali.ecommerceusb.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;

}