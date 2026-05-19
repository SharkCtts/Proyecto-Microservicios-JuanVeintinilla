package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateProductRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductResponse;
import co.edu.usbcali.ecommerceusb.mapper.ProductMapper;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.ProductsRepository;
import co.edu.usbcali.ecommerceusb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = productsRepository.findAll();

        if (products.isEmpty()) {
            return List.of();
        }

        return ProductMapper.modelToResponseList(products);
    }

    @Override
    public ProductResponse getProductById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        Product product = productsRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Producto no encontrado con id: " + id)
                );

        return ProductMapper.modelToResponse(product);
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) throws Exception {

        // Validaciones básicas
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (Objects.isNull(request.getName()) || request.getName().isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (Objects.isNull(request.getPrice())) {
            throw new Exception("El precio es obligatorio");
        }

        if (request.getPrice().doubleValue() <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }

        // Mapear
        Product product = ProductMapper.requestToModel(request);

        // Guardar
        Product saved = productsRepository.save(product);

        // Retornar
        return ProductMapper.modelToResponse(saved);
    }

    //metodo para el PUT de Productos

    @Override
    public ProductResponse updateProduct(Integer id, CreateProductRequest request) throws Exception {

        // 🔹 Validar ID
        if (id == null) {
            throw new Exception("El id es obligatorio");
        }

        // 🔹 Buscar producto existente
        Product product = productsRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Producto no encontrado con id: " + id)
                );

        // 🔹 Validar request
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (Objects.isNull(request.getName()) || request.getName().isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (Objects.isNull(request.getPrice())) {
            throw new Exception("El precio es obligatorio");
        }

        if (request.getPrice().doubleValue() <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }

        // 🔹 ACTUALIZAR CAMPOS
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        // 🔥 Manejo de available (detalle importante)
        if (request.getAvailable() != null) {
            product.setAvailable(request.getAvailable());
        }

        // 🔹 Guardar (esto hace UPDATE)
        Product updatedProduct = productsRepository.save(product);

        // 🔹 Retornar response
        return ProductMapper.modelToResponse(updatedProduct);
    }

    //DELETE
    @Override
    public void delete(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        Product product = productsRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Orden no encontrada con id: " + id)
                );

        productsRepository.delete(product);

    }

}