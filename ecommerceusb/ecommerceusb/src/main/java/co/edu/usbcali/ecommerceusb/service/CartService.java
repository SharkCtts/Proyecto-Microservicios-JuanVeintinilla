package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCartRequest;

import java.util.List;

public interface CartService {

    List<CartResponse> getAll();

    CartResponse getById(Integer id) throws Exception;

    CartResponse create(CreateCartRequest request) throws Exception;

    //PUT

    CartResponse update(Integer id, CreateCartRequest request) throws Exception;

    //Metodo Delete
    void delete(Integer id) throws Exception;
}