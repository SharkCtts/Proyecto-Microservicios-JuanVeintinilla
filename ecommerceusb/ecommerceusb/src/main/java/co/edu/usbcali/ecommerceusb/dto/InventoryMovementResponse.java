package co.edu.usbcali.ecommerceusb.dto;

import co.edu.usbcali.ecommerceusb.model.MovementType;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class InventoryMovementResponse {

    private Integer id;
    private Integer productId;
    private Integer orderId;
    private MovementType type;
    private Integer qty;
    private OffsetDateTime createdAt;

}