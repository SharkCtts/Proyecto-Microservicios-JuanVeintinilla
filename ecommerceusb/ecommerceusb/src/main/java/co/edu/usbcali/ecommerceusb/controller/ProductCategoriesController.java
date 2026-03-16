package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.ProductCategoriesRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCategoriesController {

    private final ProductCategoriesRepository productCategoriesRepository;

    // Inyección por constructor
    public ProductCategoriesController(ProductCategoriesRepository productCategoriesRepository) {
        this.productCategoriesRepository = productCategoriesRepository;
    }

}