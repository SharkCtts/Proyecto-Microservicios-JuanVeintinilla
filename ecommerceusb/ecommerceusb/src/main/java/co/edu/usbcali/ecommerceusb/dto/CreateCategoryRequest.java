package co.edu.usbcali.ecommerceusb.dto;

import lombok.Getter;

@Getter
public class CreateCategoryRequest {

    private String name;
    private Integer parentId;

}