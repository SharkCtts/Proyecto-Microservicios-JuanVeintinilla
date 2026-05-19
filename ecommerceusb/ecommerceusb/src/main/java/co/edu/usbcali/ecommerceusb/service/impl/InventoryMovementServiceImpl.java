package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateInventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import co.edu.usbcali.ecommerceusb.mapper.InventoryMovementMapper;
import co.edu.usbcali.ecommerceusb.model.*;
import co.edu.usbcali.ecommerceusb.repository.*;
import co.edu.usbcali.ecommerceusb.service.InventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import co.edu.usbcali.ecommerceusb.model.MovementType;


@Service
public class InventoryMovementServiceImpl implements InventoryMovementService {

    @Autowired
    private InventoryMovementsRepository repository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<InventoryMovementResponse> getAll() {
        List<InventoryMovements> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return InventoryMovementMapper.modelToResponseList(list);
    }

    @Override
    public InventoryMovementResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        InventoryMovements mov = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Movimiento no encontrado con id: " + id)
                );

        return InventoryMovementMapper.modelToResponse(mov);
    }

    @Override
    public InventoryMovementResponse create(CreateInventoryMovementRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("Request null");
        }

        if (request.getProductId() == null || request.getProductId() <= 0) {
            throw new Exception("productId inválido");
        }

        if (request.getType() == null) {
            throw new Exception("type obligatorio");
        }

        if (request.getQty() == null || request.getQty() <= 0) {
            throw new Exception("qty inválido");
        }

        // 🔹 Buscar producto
        Product product = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("Producto no existe"));

        // 🔹 Buscar orden (opcional)
        Order order = null;
        if (request.getOrderId() != null) {
            order = ordersRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new Exception("Orden no existe"));
        }

        // 🔥 INVENTARIO (clave)
        Inventory inventory = inventoryRepository.findById(product.getId())
                .orElseThrow(() -> new Exception("No existe inventario para el producto"));

        // 🔥 Ajuste de stock según tipo
        if (request.getType() == MovementType.IN
                || request.getType() == MovementType.CREDIT) {

            inventory.setStock(inventory.getStock() + request.getQty());

        } else if (request.getType() == MovementType.DEBIT) {

            if (inventory.getStock() < request.getQty()) {
                throw new Exception("Stock insuficiente");
            }

            inventory.setStock(inventory.getStock() - request.getQty());

        }

        inventoryRepository.save(inventory);

        // 🔹 Crear movimiento
        InventoryMovements movement = InventoryMovements.builder()
                .product(product)
                .order(order)
                .type(request.getType())
                .qty(request.getQty())
                .build();

        InventoryMovements saved = repository.save(movement);

        return InventoryMovementMapper.modelToResponse(saved);
    }

    //FUNCION PARA EL PUT

    @Override
    public InventoryMovementResponse update(
            Integer id,
            CreateInventoryMovementRequest request
    ) throws Exception {

        // 🔹 Validar ID
        if (id == null || id <= 0) {
            throw new Exception("id inválido");
        }

        // 🔹 Validar request
        if (Objects.isNull(request)) {
            throw new Exception("Request null");
        }

        // 🔹 Buscar movimiento existente
        InventoryMovements movement = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Movimiento no encontrado con id: " + id)
                );

        // 🔹 Actualizar producto
        if (request.getProductId() != null) {

            Product product = productsRepository.findById(request.getProductId())
                    .orElseThrow(() ->
                            new Exception("Producto no existe")
                    );

            movement.setProduct(product);
        }

        // 🔹 Actualizar orden
        if (request.getOrderId() != null) {

            Order order = ordersRepository.findById(request.getOrderId())
                    .orElseThrow(() ->
                            new Exception("Orden no existe")
                    );

            movement.setOrder(order);
        }

        // 🔹 Actualizar type
        if (request.getType() != null) {
            movement.setType(request.getType());
        }

        // 🔹 Actualizar qty
        if (request.getQty() != null) {

            if (request.getQty() <= 0) {
                throw new Exception("qty inválido");
            }

            movement.setQty(request.getQty());
        }

        // 🔹 Guardar cambios
        InventoryMovements updated = repository.save(movement);

        return InventoryMovementMapper.modelToResponse(updated);
    }

    //DELETE
    @Override
    public void delete(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        InventoryMovements movement = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Movimiento no encontrado con id: " + id)
                );

        repository.delete(movement);
    }

}