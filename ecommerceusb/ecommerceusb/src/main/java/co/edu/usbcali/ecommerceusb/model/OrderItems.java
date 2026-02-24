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


public class OrderItems {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private java.math.BigDecimal unitPriceSnapshot;
    private java.math.BigDecimal lineTotal;
    private java.sql.Timestamp createdAt;

}




