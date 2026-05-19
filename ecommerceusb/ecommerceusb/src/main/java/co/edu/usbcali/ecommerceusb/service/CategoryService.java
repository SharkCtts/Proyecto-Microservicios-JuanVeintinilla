package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CategoryResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAll();

    CategoryResponse getById(Integer id) throws Exception;

    CategoryResponse create(CreateCategoryRequest request) throws Exception;

    //PUT

    CategoryResponse update(Integer id, CreateCategoryRequest request) throws Exception;

    //Metodo Delete
    void delete(Integer id) throws Exception;
}