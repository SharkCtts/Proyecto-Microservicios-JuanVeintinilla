package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CreateInventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;

import java.util.List;

public interface InventoryMovementService {

    List<InventoryMovementResponse> getAll();

    InventoryMovementResponse getById(Integer id) throws Exception;

    InventoryMovementResponse create(CreateInventoryMovementRequest request) throws Exception;

    //PUT

    InventoryMovementResponse update(
            Integer id,
            CreateInventoryMovementRequest request
    ) throws Exception;

}