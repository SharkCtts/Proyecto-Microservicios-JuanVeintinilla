package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCartItemRequest;

import java.util.List;

public interface CartItemService {

    List<CartItemResponse> getAll();

    CartItemResponse getById(Integer id) throws Exception;

    CartItemResponse addItem(CreateCartItemRequest request) throws Exception;

    //PUT

    CartItemResponse update(Integer id, CreateCartItemRequest request) throws Exception;


}