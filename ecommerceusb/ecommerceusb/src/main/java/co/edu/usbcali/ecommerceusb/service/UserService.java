package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CreateUserRequest;
import co.edu.usbcali.ecommerceusb.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    UserResponse getUserById (Integer id) throws Exception;
    UserResponse getUserByEmail (String email) throws Exception;

    UserResponse createUser (CreateUserRequest createUserRequest) throws Exception;

    // Metodo para el put en user
    UserResponse updateUser(Integer id, CreateUserRequest request) throws Exception;

    //Metodo Delete
    void delete(Integer id) throws Exception;

}
