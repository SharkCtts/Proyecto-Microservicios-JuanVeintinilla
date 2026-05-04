package co.edu.usbcali.ecommerceusb.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class InventoryResponse {

    private Integer productId;
    private Integer stock;
    private OffsetDateTime updatedAt;

}