package co.edu.usbcali.ecommerceusb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Products {

    private Integer id;
    private String name;
    private String description;
    private java.math.BigDecimal price;
    private Boolean available;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;

}




