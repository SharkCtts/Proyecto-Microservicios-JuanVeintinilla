package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.model.Users;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    //User /Entity - Model) -> userResponse (DTO)

    public static UserResponse modelToUserResponse (Users user) {

        DocumentType documentType = user.getDocumentType();

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                // Uso de un IF Ternario
                .documentTypeId(user.getDocumentType() != null ?
                        user.getDocumentType().getId() : null)
                .documentTypeName(user.getDocumentType() != null ?
                        user.getDocumentType().getName() : null)
                .documentNumber(user.getDocumentNumber())
                .build();

    }



    public static List<UserResponse> modelToUserResponseList(List<Users> users){
        return users.stream().map(UserMapper::modelToUserResponse).toList();
    }


    // falta hacer la lista como en documentypemapper

}
