package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CreateInventoryRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> getAll();

    InventoryResponse getByProductId(Integer productId) throws Exception;

    InventoryResponse create(CreateInventoryRequest request) throws Exception;

    //put

    InventoryResponse update(
            Integer productId,
            CreateInventoryRequest request
    ) throws Exception;

}