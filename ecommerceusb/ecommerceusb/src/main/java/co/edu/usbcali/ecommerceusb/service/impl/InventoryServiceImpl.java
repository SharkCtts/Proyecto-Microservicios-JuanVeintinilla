package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateInventoryRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryResponse;
import co.edu.usbcali.ecommerceusb.mapper.InventoryMapper;
import co.edu.usbcali.ecommerceusb.model.Inventory;
import co.edu.usbcali.ecommerceusb.model.Products;
import co.edu.usbcali.ecommerceusb.repository.InventoryRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductsRepository;
import co.edu.usbcali.ecommerceusb.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<InventoryResponse> getAll() {
        List<Inventory> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return InventoryMapper.modelToResponseList(list);
    }

    @Override
    public InventoryResponse getByProductId(Integer productId) throws Exception {

        if (productId == null) {
            throw new Exception("Debe ingresar el productId");
        }

        Inventory inv = repository.findById(productId)
                .orElseThrow(() ->
                        new Exception("Inventario no encontrado para productId: " + productId)
                );

        return InventoryMapper.modelToResponse(inv);
    }

    @Override
    public InventoryResponse create(CreateInventoryRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (request.getProductId() == null || request.getProductId() <= 0) {
            throw new Exception("productId inválido");
        }

        if (request.getStock() == null || request.getStock() < 0) {
            throw new Exception("Stock inválido");
        }

        // 🔥 Validar que el producto existe
        Products product = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("El producto no existe"));

        // 🔥 Validar que no exista inventario ya
        if (repository.existsById(request.getProductId())) {
            throw new Exception("Ya existe inventario para este producto");
        }

        // 🔹 Crear inventario (PK compartida)
        Inventory inventory = Inventory.builder()
                .product(product)
                .stock(request.getStock())
                .build();

        Inventory saved = repository.save(inventory);

        return InventoryMapper.modelToResponse(saved);
    }
}