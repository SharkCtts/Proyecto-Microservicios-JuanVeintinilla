package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CreatePaymentRequest;
import co.edu.usbcali.ecommerceusb.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {

    List<PaymentResponse> getAll();

    PaymentResponse getById(Integer id) throws Exception;

    PaymentResponse create(CreatePaymentRequest request) throws Exception;

    //Put para payment

    PaymentResponse update(Integer id, CreatePaymentRequest request) throws Exception;

}