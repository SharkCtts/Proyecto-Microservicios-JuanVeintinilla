package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.InventoryRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    // Inyección por constructor
    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

}