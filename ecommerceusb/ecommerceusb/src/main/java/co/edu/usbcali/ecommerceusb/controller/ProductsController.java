package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.ProductsRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    private final ProductsRepository productsRepository;

    // Inyección por constructor
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

}