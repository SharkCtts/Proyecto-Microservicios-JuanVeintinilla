package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import co.edu.usbcali.ecommerceusb.model.CartItems;

import java.util.List;

public class CartItemMapper {

    public static CartItemResponse modelToResponse(CartItems item) {
        return CartItemResponse.builder()
                .id(item.getId())
                .cartId(item.getCart() != null ? item.getCart().getId() : null)
                .productId(item.getProduct() != null ? item.getProduct().getId() : null)
                .quantity(item.getQuantity())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }

    public static List<CartItemResponse> modelToResponseList(List<CartItems> list) {
        return list.stream()
                .map(CartItemMapper::modelToResponse)
                .toList();
    }
}