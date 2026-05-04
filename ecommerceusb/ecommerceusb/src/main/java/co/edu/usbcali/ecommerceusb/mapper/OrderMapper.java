package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.OrderResponse;
import co.edu.usbcali.ecommerceusb.model.Orders;

import java.util.List;

public class OrderMapper {

    public static OrderResponse modelToResponse(Orders order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser() != null ? order.getUser().getId() : null)
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .currency(order.getCurrency())
                .createdAt(order.getCreatedAt())
                .paidAt(order.getPaidAt())
                .cancelledAt(order.getCancelledAt())
                .build();
    }

    public static List<OrderResponse> modelToResponseList(List<Orders> list) {
        return list.stream()
                .map(OrderMapper::modelToResponse)
                .toList();
    }
}