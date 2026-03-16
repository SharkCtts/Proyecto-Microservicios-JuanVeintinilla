package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.InventoryMovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryMovementsController {

    @Autowired
    private InventoryMovementsRepository inventoryMovementsRepository;

}