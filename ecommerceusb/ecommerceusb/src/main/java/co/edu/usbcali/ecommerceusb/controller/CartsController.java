package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCartRequest;
import co.edu.usbcali.ecommerceusb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartsController {

    @Autowired
    private CartService service;

    @GetMapping("/all")
    public List<CartResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CartResponse> create(
            @RequestBody CreateCartRequest request
    ) throws Exception {
        return new ResponseEntity<>(
                service.create(request),
                HttpStatus.CREATED
        );
    }
}