package co.edu.usbcali.ecommerceusb.dto;

import lombok.Getter;

@Getter
public class CreateInventoryRequest {

    private Integer productId;
    private Integer stock;

}