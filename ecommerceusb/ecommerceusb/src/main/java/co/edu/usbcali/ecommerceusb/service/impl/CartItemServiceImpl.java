package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCartItemRequest;
import co.edu.usbcali.ecommerceusb.mapper.CartItemMapper;
import co.edu.usbcali.ecommerceusb.model.*;
import co.edu.usbcali.ecommerceusb.repository.*;
import co.edu.usbcali.ecommerceusb.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemsRepository repository;

    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<CartItemResponse> getAll() {
        List<CartItem> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return CartItemMapper.modelToResponseList(list);
    }

    @Override
    public CartItemResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        CartItem item = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("CartItem no encontrado con id: " + id)
                );

        return CartItemMapper.modelToResponse(item);
    }

    @Override
    public CartItemResponse addItem(CreateCartItemRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("Request null");
        }

        if (request.getCartId() == null || request.getCartId() <= 0) {
            throw new Exception("cartId inválido");
        }

        if (request.getProductId() == null || request.getProductId() <= 0) {
            throw new Exception("productId inválido");
        }

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new Exception("quantity inválida");
        }

        // 🔹 Buscar carrito
        Cart cart = cartsRepository.findById(request.getCartId())
                .orElseThrow(() -> new Exception("Carrito no existe"));

        // 🔹 Validar estado del carrito
        if (cart.getStatus() != CartStatus.ACTIVE) {
            throw new Exception("El carrito no está activo");
        }

        // 🔹 Buscar producto
        Product product = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("Producto no existe"));

        // 🔥 Buscar si ya existe el item
        CartItem existing = repository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (existing != null) {
            // 🔥 SUMAR cantidad
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            CartItem updated = repository.save(existing);
            return CartItemMapper.modelToResponse(updated);
        }

        // 🔹 Crear nuevo item
        CartItem item = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(request.getQuantity())
                .build();

        CartItem saved = repository.save(item);

        return CartItemMapper.modelToResponse(saved);
    }

    //METODO PARA EL PUT

    @Override
    public CartItemResponse update(Integer id, CreateCartItemRequest request) throws Exception {

        // 🔹 Validar id
        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        // 🔹 Validar request
        if (Objects.isNull(request)) {
            throw new Exception("Request null");
        }

        // 🔹 Buscar item existente
        CartItem item = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("CartItem no encontrado con id: " + id)
                );

        // 🔹 Actualizar carrito
        if (request.getCartId() != null) {

            if (request.getCartId() <= 0) {
                throw new Exception("cartId inválido");
            }

            Cart cart = cartsRepository.findById(request.getCartId())
                    .orElseThrow(() ->
                            new Exception("Carrito no existe")
                    );

            // 🔥 Validar carrito activo
            if (cart.getStatus() != CartStatus.ACTIVE) {
                throw new Exception("El carrito no está activo");
            }

            item.setCart(cart);
        }

        // 🔹 Actualizar producto
        if (request.getProductId() != null) {

            if (request.getProductId() <= 0) {
                throw new Exception("productId inválido");
            }

            Product product = productsRepository.findById(request.getProductId())
                    .orElseThrow(() ->
                            new Exception("Producto no existe")
                    );

            item.setProduct(product);
        }

        // 🔹 Actualizar cantidad
        if (request.getQuantity() != null) {

            if (request.getQuantity() <= 0) {
                throw new Exception("quantity inválida");
            }

            item.setQuantity(request.getQuantity());
        }

        // 🔹 Guardar
        CartItem updated = repository.save(item);

        // 🔹 Retornar response
        return CartItemMapper.modelToResponse(updated);
    }

}