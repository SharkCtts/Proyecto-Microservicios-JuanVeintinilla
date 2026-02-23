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


public class Cart {

    private Integer id;
    private Integer userId;
    private String status;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;

}




