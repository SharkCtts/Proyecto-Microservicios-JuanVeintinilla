package co.edu.usbcali.ecommerceusb.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    /*
    Esto es una clse inmutable
    La cual solo vamos a instanciar por medio del builder
    no se le pueden modificar los valores a sus atributos
     */

    private Integer id;
    private String fullName;
    private String email;
    private Integer documentTypeId;
    private String documentTypeName;
    private String documentNumber;

}
