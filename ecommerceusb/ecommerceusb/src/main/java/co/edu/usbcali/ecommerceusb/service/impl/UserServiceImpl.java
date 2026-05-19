package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateUserRequest;
import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.mapper.UserMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import co.edu.usbcali.ecommerceusb.repository.UsersRepository;
import co.edu.usbcali.ecommerceusb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    //Inyección de dependencias
    @Autowired
    private UsersRepository userRepository;

    //inyecion de dependencias de DocumentTypeRepository
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<UserResponse> getUsers() {
        //definir una lista de Users
        List<User> users = userRepository.findAll();

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
        User users = userRepository.findById(id)
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
    public UserResponse getUserByEmail(String email) throws Exception {
        //validar que el email contenga un valor
        if(email == null || email.isBlank()) {
            throw new Exception("Debe ingresar el email");
        }

        // Buscar usuario en base de datos por email
        User userByEmail = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new Exception (
                                String.format("usuario no entrado con el email: %s", email)));

        // Mapear a Response y retornar

        return UserMapper.modelToUserResponse(userByEmail);
    }

    //ESTE METODO SIRVE PARA CREAR UN USUARIO
    // @param createUserRequest
    // @return
    // @throws

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) throws Exception {

        //Validar el objeto createUserRequest

        // Validar que el objeto no sea nuelo

        if(Objects.isNull(createUserRequest)){
            throw new Exception("El objeto createUserRequest no puede ser creado poq ta vacio");
        }

        // validar que el campo fullName no sea nulo

        if (Objects.isNull(createUserRequest.getFullName()) ||
        createUserRequest.getFullName().isBlank()){
            throw new Exception("eL CAMPO FULLNAME NO PUEDE SER NULO NI VACIO");
        }

        //validar que el campo phone no sea nulo ni vacio

        if (Objects.isNull(createUserRequest.getPhone()) ||
        createUserRequest.getPhone().isBlank()){
            throw new Exception("El campo Phone no puede ser nulo ni vacio");
        }

        // Validar que el campo Email no sea nulo ni vacio

        if (Objects.isNull(createUserRequest.getEmail()) ||
                createUserRequest.getEmail().isBlank()){
            throw new Exception("El campo Email no puede ser nulo ni vacio");
        }

        // Validar que el campo documentTypeId no sea nulo ni vacio

        if (createUserRequest.getDocumentTypeId() == null || createUserRequest.getDocumentTypeId() <= 0){
            throw new Exception("El campo documentTypeId debe contener un valor mayor a 0");
        }

        // Validar que el campo document_number no sea nulo ni vacio

        if (Objects.isNull(createUserRequest.getDocumentNumber()) ||
                createUserRequest.getDocumentNumber().isBlank()){
            throw new Exception("El campo documentNumber no puede ser nulo ni vacio");
        }

        // Validar que el campo birth_date no sea nulo ni vacio

        if (Objects.isNull(createUserRequest.getBirthDate()) ||
                createUserRequest.getBirthDate().isBlank()){
            throw new Exception("El campo birth_date no puede ser nulo ni vacio");
        }

        // Validar que el campo country no sea nulo ni vacio

        if (Objects.isNull(createUserRequest.getCountry()) ||
                createUserRequest.getCountry().isBlank()){
            throw new Exception("El campo country no puede ser nulo ni vacio");
        }

        // Validar que el campo address no sea nulo ni vacio

        if (Objects.isNull(createUserRequest.getAddress()) ||
                createUserRequest.getAddress().isBlank()){
            throw new Exception("El campo address no puede ser nulo ni vacio");
        }

        // Validar que no exista un usuario creado con el mismo email

        if (userRepository.existsByEmail(createUserRequest.getEmail())){
            throw new Exception("Ya existe un usuario con el email integrsado");
        }

        // Validar que no exista un suario creado con el mismo documento y tipo de documento

        if (userRepository.existsByDocumentNumberAndDocumentTypeId(
                createUserRequest.getDocumentNumber(), createUserRequest.getDocumentTypeId())){
            throw new Exception("Ya existe un usuario con el ese id");
        }

        // Validar que existe el document Type

        DocumentType documentType = documentTypeRepository.findById(createUserRequest.getDocumentTypeId())
                .orElseThrow(() -> new Exception("El tipo de documento no existe"));

        /*
        // Convertir el objeto createUserRequest a User
        Users users = Users.builder()
                .fullName(createUserRequest.getFullName())
                .phone(createUserRequest.getPhone())
                .email(createUserRequest.getEmail())
                .documentType(documentType)
                .documentNumber(createUserRequest.getDocumentNumber())
                .birthDate(
                        LocalDate.parse(
                                createUserRequest.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        )
                )
                .country(createUserRequest.getCountry())
                .address(createUserRequest.getAddress())
                .build();
*/

        //Mapear User
        User user = UserMapper.createUserRequestToUser(createUserRequest, documentType);

        User savedUser = userRepository.save(user);

        return UserMapper.modelToUserResponse(savedUser);

        /*
        users = userRepository.save(users);
        UserResponse userResponse = userMapper.modelToUserResponse(user);
        return userResponse;
*/

    }


// -------------- AQUI ESTÁ EL PUT ---------------

    @Override
    public UserResponse updateUser(Integer id, CreateUserRequest request) throws Exception {

        // 🔹 Validar ID
        if (id == null) {
            throw new Exception("El id es obligatorio");
        }

        // 🔹 Buscar usuario existente
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Usuario no encontrado con id: " + id));

        // 🔹 Validaciones básicas (puedes reutilizar las del create si quieres)
        if (request.getFullName() == null || request.getFullName().isBlank()) {
            throw new Exception("El nombre no puede ser vacío");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new Exception("El email no puede ser vacío");
        }

        // 🔹 Validar DocumentType
        DocumentType documentType = documentTypeRepository
                .findById(request.getDocumentTypeId())
                .orElseThrow(() -> new Exception("DocumentType no existe"));

        // 🔹 ACTUALIZAR CAMPOS
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setDocumentType(documentType);
        user.setDocumentNumber(request.getDocumentNumber());
        user.setBirthDate(
                LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
        user.setCountry(request.getCountry());
        user.setAddress(request.getAddress());

        // 🔹 Guardar (esto hace UPDATE automáticamente)
        User updatedUser = userRepository.save(user);

        return UserMapper.modelToUserResponse(updatedUser);
    }


    //DELETE
    @Override
    public void delete(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Orden no encontrada con id: " + id)
                );

        userRepository.delete(user);

    }
}
