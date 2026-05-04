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
        List<CartItems> list = repository.findAll();

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

        CartItems item = repository.findById(id)
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
        Carts cart = cartsRepository.findById(request.getCartId())
                .orElseThrow(() -> new Exception("Carrito no existe"));

        // 🔹 Validar estado del carrito
        if (cart.getStatus() != CartStatus.ACTIVE) {
            throw new Exception("El carrito no está activo");
        }

        // 🔹 Buscar producto
        Products product = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("Producto no existe"));

        // 🔥 Buscar si ya existe el item
        CartItems existing = repository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (existing != null) {
            // 🔥 SUMAR cantidad
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            CartItems updated = repository.save(existing);
            return CartItemMapper.modelToResponse(updated);
        }

        // 🔹 Crear nuevo item
        CartItems item = CartItems.builder()
                .cart(cart)
                .product(product)
                .quantity(request.getQuantity())
                .build();

        CartItems saved = repository.save(item);

        return CartItemMapper.modelToResponse(saved);
    }
}