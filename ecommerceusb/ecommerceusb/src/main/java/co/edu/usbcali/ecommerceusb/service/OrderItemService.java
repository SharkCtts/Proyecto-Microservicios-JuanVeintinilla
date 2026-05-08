package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CreateOrderItemRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getAll();

    OrderItemResponse getById(Integer id) throws Exception;

    OrderItemResponse create(CreateOrderItemRequest request) throws Exception;

    //PUT
    OrderItemResponse update(Integer id, CreateOrderItemRequest request) throws Exception;

}