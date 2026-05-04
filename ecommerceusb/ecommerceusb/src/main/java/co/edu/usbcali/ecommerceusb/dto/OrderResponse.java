package co.edu.usbcali.ecommerceusb.dto;

import co.edu.usbcali.ecommerceusb.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@Getter
public class OrderResponse {

    private Integer id;
    private Integer userId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private String currency;
    private OffsetDateTime createdAt;
    private OffsetDateTime paidAt;
    private OffsetDateTime cancelledAt;

}