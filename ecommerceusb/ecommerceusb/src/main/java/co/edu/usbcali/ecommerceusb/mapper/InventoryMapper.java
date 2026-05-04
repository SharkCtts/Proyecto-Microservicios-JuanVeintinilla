package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.InventoryResponse;
import co.edu.usbcali.ecommerceusb.model.Inventory;

import java.util.List;

public class InventoryMapper {

    public static InventoryResponse modelToResponse(Inventory inv) {
        return InventoryResponse.builder()
                .productId(inv.getProductId())
                .stock(inv.getStock())
                .updatedAt(inv.getUpdatedAt())
                .build();
    }

    public static List<InventoryResponse> modelToResponseList(List<Inventory> list) {
        return list.stream()
                .map(InventoryMapper::modelToResponse)
                .toList();
    }
}