package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.mapper.UserMapper;
import co.edu.usbcali.ecommerceusb.model.Users;
import co.edu.usbcali.ecommerceusb.repository.UsersRepository;
import co.edu.usbcali.ecommerceusb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //Inyección de dependencias
    @Autowired
    private UsersRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        //definir una lista de Users
        List<Users> users = userRepository.findAll();

        //validar si la lsita está vacía
        if (users.isEmpty()){
            return List.of();
        }

        // Si la lista contiene información, entonces retornamos
        // una lista mapeada de UserResponse
        List <UserResponse> userResponses = UserMapper.modelToUserResponseList(users);
        return userResponses;
    }

    @Override
    public UserResponse getUserById(Integer id) throws Exception {

        // Validar que venga un valor en id
        if (id == null) {
            throw new Exception("Debe ingresar el id para buscar");
        }
        //Buscar usuario en base de datos por id
        //Si no lo encuentra, lanza excepción
        Users users = userRepository.findById(id)
                .orElseThrow(() ->
                        new Exception(
                                String.format("Usuario no encontrado con el id: %d", id)
                        ));

        //Mapear a Response
        UserResponse userResponse = UserMapper.modelToUserResponse(users);

        //retornar Usuario Encontrado
        return userResponse;
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return null;
    }
}
