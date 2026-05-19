package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CreateOrderRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAll();

    OrderResponse getById(Integer id) throws Exception;

    OrderResponse create(CreateOrderRequest request) throws Exception;

    //Put

    OrderResponse update(Integer id, CreateOrderRequest request) throws Exception;

    //Metodo Delete
    void delete(Integer id) throws Exception;
}