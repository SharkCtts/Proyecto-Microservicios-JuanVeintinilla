package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CreateProductCategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryResponse> getAll();

    ProductCategoryResponse getById(Integer id) throws Exception;

    ProductCategoryResponse create(CreateProductCategoryRequest request) throws Exception;

// PARA HACER EL PUT

    ProductCategoryResponse update(Integer id, CreateProductCategoryRequest request) throws Exception;
}