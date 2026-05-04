package co.edu.usbcali.ecommerceusb.dto;

import co.edu.usbcali.ecommerceusb.model.CartStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class CartResponse {

    private Integer id;
    private Integer userId;
    private CartStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}