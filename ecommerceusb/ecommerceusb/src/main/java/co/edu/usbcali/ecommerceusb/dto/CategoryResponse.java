package co.edu.usbcali.ecommerceusb.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class CategoryResponse {

    private Integer id;
    private String name;
    private Integer parentId;
    private OffsetDateTime createdAt;

}