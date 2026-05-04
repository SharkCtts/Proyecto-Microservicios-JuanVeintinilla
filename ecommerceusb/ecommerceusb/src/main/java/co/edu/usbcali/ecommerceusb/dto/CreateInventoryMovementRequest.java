package co.edu.usbcali.ecommerceusb.dto;

import co.edu.usbcali.ecommerceusb.model.MovementType;
import lombok.Getter;

@Getter
public class CreateInventoryMovementRequest {

    private Integer productId;
    private Integer orderId; // puede ser null en algunos casos
    private MovementType type;
    private Integer qty;

}