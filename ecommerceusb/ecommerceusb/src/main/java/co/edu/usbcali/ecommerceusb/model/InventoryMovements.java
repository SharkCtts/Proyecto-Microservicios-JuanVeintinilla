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


public class InventoryMovements {

    private Integer id;
    private Integer productId;
    private Integer orderId;
    private String type;
    private Integer qty;
    private java.sql.Timestamp createdAt;

}




