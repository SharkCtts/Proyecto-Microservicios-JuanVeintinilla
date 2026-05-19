package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;
import co.edu.usbcali.ecommerceusb.model.OrderItem;

import java.util.List;

public class OrderItemMapper {

    public static OrderItemResponse modelToResponse(OrderItem item) {
        return OrderItemResponse.builder()
                .id(item.getId())
                .orderId(item.getOrder() != null ? item.getOrder().getId() : null)
                .productId(item.getProduct() != null ? item.getProduct().getId() : null)
                .quantity(item.getQuantity())
                .unitPriceSnapshot(item.getUnitPriceSnapshot())
                .lineTotal(item.getLineTotal())
                .createdAt(item.getCreatedAt())
                .build();
    }

    public static List<OrderItemResponse> modelToResponseList(List<OrderItem> list) {
        return list.stream()
                .map(OrderItemMapper::modelToResponse)
                .toList();
    }
}