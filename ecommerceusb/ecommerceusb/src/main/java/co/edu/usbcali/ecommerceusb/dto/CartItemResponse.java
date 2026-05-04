package co.edu.usbcali.ecommerceusb.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class CartItemResponse {

    private Integer id;
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}