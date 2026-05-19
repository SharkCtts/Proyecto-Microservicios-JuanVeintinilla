package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCartRequest;
import co.edu.usbcali.ecommerceusb.mapper.CartMapper;
import co.edu.usbcali.ecommerceusb.model.Cart;
import co.edu.usbcali.ecommerceusb.model.CartStatus;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.CartsRepository;
import co.edu.usbcali.ecommerceusb.repository.UsersRepository;
import co.edu.usbcali.ecommerceusb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartsRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<CartResponse> getAll() {
        List<Cart> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return CartMapper.modelToResponseList(list);
    }

    @Override
    public CartResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        Cart cart = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Carrito no encontrado con id: " + id)
                );

        return CartMapper.modelToResponse(cart);
    }

    @Override
    public CartResponse create(CreateCartRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("Request null");
        }

        if (request.getUserId() == null || request.getUserId() <= 0) {
            throw new Exception("userId inválido");
        }

        // 🔹 Buscar usuario
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new Exception("Usuario no existe"));

        // 🔥 OPCIONAL PRO: evitar múltiples carritos activos
        /*
        boolean existsActive = repository.findAll().stream()
                .anyMatch(c -> c.getUser().getId().equals(user.getId())
                        && c.getStatus() == CartStatus.ACTIVE);

        if (existsActive) {
            throw new Exception("El usuario ya tiene un carrito activo");
        }
        */

        // 🔹 Crear carrito
        Cart cart = Cart.builder()
                .user(user)
                .status(request.getStatus() != null ? request.getStatus() : CartStatus.ACTIVE)
                .build();

        Cart saved = repository.save(cart);

        return CartMapper.modelToResponse(saved);
    }

    @Override
    public CartResponse update(Integer id, CreateCartRequest request) throws Exception {

        // 🔹 Validar id
        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        // 🔹 Validar request
        if (Objects.isNull(request)) {
            throw new Exception("Request null");
        }

        // 🔹 Buscar carrito existente
        Cart cart = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Carrito no encontrado con id: " + id)
                );

        // 🔹 Actualizar usuario
        if (request.getUserId() != null) {

            if (request.getUserId() <= 0) {
                throw new Exception("userId inválido");
            }

            User user = usersRepository.findById(request.getUserId())
                    .orElseThrow(() ->
                            new Exception("Usuario no existe")
                    );

            cart.setUser(user);
        }

        // 🔹 Actualizar status
        if (request.getStatus() != null) {
            cart.setStatus(request.getStatus());
        }

        // 🔹 Guardar
        Cart updated = repository.save(cart);

        // 🔹 Retornar response
        return CartMapper.modelToResponse(updated);
    }

}