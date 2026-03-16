package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.CategoriesRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesController {

    private final CategoriesRepository categoriesRepository;

    // Inyección por constructor
    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

}

