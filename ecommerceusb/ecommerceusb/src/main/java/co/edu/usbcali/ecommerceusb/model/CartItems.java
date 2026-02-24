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


public class CartItems {

    private Integer id;
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;

}




