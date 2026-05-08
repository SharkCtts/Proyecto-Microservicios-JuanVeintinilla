package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CreatePaymentRequest;
import co.edu.usbcali.ecommerceusb.dto.PaymentResponse;
import co.edu.usbcali.ecommerceusb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentService service;

    @GetMapping("/all")
    public List<PaymentResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(
            @RequestBody CreatePaymentRequest request
    ) throws Exception {
        return new ResponseEntity<>(
                service.create(request),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> update(
            @PathVariable Integer id,
            @RequestBody CreatePaymentRequest request
    ) throws Exception {

        return new ResponseEntity<>(
                service.update(id, request),
                HttpStatus.OK
        );
    }

}