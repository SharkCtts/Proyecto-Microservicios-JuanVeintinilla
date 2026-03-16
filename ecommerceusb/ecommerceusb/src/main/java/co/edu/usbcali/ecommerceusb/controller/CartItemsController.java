package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.CartItemsRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemsController {

    private final CartItemsRepository cartItemsRepository;

    // Inyección por constructor
    public CartItemsController(CartItemsRepository cartItemsRepository) {
        this.cartItemsRepository = cartItemsRepository;
    }

}