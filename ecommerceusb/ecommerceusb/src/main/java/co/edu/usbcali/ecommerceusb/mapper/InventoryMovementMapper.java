package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import co.edu.usbcali.ecommerceusb.model.InventoryMovements;

import java.util.List;

public class InventoryMovementMapper {

    public static InventoryMovementResponse modelToResponse(InventoryMovements mov) {
        return InventoryMovementResponse.builder()
                .id(mov.getId())
                .productId(mov.getProduct() != null ? mov.getProduct().getId() : null)
                .orderId(mov.getOrder() != null ? mov.getOrder().getId() : null)
                .type(mov.getType())
                .qty(mov.getQty())
                .createdAt(mov.getCreatedAt())
                .build();
    }

    public static List<InventoryMovementResponse> modelToResponseList(List<InventoryMovements> list) {
        return list.stream()
                .map(InventoryMovementMapper::modelToResponse)
                .toList();
    }
}