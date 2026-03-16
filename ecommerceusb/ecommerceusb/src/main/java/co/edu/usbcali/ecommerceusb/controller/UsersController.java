package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.UsersRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UsersRepository usersRepository;

    // Inyección por constructor
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

}