package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.model.Carts;

import java.util.List;

public class CartMapper {

    public static CartResponse modelToResponse(Carts cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUser() != null ? cart.getUser().getId() : null)
                .status(cart.getStatus())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }

    public static List<CartResponse> modelToResponseList(List<Carts> list) {
        return list.stream()
                .map(CartMapper::modelToResponse)
                .toList();
    }
}