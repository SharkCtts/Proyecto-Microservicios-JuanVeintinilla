package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CreateOrderItemRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;
import co.edu.usbcali.ecommerceusb.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemService service;

    @GetMapping("/all")
    public List<OrderItemResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<OrderItemResponse> create(
            @RequestBody CreateOrderItemRequest request
    ) throws Exception {
        return new ResponseEntity<>(
                service.create(request),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponse> update(
            @PathVariable Integer id,
            @RequestBody CreateOrderItemRequest request
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