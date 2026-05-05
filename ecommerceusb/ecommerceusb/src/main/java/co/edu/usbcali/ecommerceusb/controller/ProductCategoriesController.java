package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CreateProductCategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;
import co.edu.usbcali.ecommerceusb.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoriesController {

    @Autowired
    private ProductCategoryService service;

    @GetMapping("/all")
    public List<ProductCategoryResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResponse> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ProductCategoryResponse> create(
            @RequestBody CreateProductCategoryRequest request
    ) throws Exception {
        return new ResponseEntity<>(
                service.create(request),
                HttpStatus.CREATED
        );
    }

    //PUT

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryResponse> update(
            @PathVariable Integer id,
            @RequestBody CreateProductCategoryRequest request
    ) throws Exception {

        return new ResponseEntity<>(
                service.update(id, request),
                HttpStatus.OK
        );
    }

}