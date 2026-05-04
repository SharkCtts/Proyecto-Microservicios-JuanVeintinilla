package co.edu.usbcali.ecommerceusb.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@Getter
public class ProductResponse {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}