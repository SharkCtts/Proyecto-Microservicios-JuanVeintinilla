package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCartItemRequest;
import co.edu.usbcali.ecommerceusb.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemsController {

    @Autowired
    private CartItemService service;

    @GetMapping("/all")
    public List<CartItemResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemResponse> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> addItem(
            @RequestBody CreateCartItemRequest request
    ) throws Exception {
        return new ResponseEntity<>(
                service.addItem(request),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemResponse> update(
            @PathVariable Integer id,
            @RequestBody CreateCartItemRequest request
    ) throws Exception {

        return new ResponseEntity<>(
                service.update(id, request),
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