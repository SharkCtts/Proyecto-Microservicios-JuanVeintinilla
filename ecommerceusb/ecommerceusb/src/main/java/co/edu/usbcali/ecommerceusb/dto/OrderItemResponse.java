package co.edu.usbcali.ecommerceusb.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@Getter
public class OrderItemResponse {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal unitPriceSnapshot;
    private BigDecimal lineTotal;
    private OffsetDateTime createdAt;

}