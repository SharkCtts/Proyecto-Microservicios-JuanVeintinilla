package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CreateInventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import co.edu.usbcali.ecommerceusb.service.InventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory-movements")
public class InventoryMovementsController {

    @Autowired
    private InventoryMovementService service;

    @GetMapping("/all")
    public List<InventoryMovementResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementResponse> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<InventoryMovementResponse> create(
            @RequestBody CreateInventoryMovementRequest request
    ) throws Exception {
        return new ResponseEntity<>(
                service.create(request),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovementResponse> update(
            @PathVariable Integer id,
            @RequestBody CreateInventoryMovementRequest request
    ) throws Exception {

        return new ResponseEntity<>(
                service.update(id, request),
                HttpStatus.OK
        );
    }

}