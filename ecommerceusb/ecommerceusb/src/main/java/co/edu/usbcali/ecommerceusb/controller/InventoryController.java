package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CreateInventoryRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryResponse;
import co.edu.usbcali.ecommerceusb.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/all")
    public List<InventoryResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getByProductId(@PathVariable Integer productId) throws Exception {
        return new ResponseEntity<>(
                service.getByProductId(productId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> create(
            @RequestBody CreateInventoryRequest request
    ) throws Exception {
        return new ResponseEntity<>(
                service.create(request),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<InventoryResponse> update(
            @PathVariable Integer productId,
            @RequestBody CreateInventoryRequest request
    ) throws Exception {

        return new ResponseEntity<>(
                service.update(productId, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws Exception {

        service.delete(id);

        return new ResponseEntity<>(
                "Registro eliminado correctamente",
                HttpStatus.OK
        );
    }
}