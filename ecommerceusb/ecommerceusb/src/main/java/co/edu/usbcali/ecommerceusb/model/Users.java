package co.edu.usbcali.ecommerceusb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class User {

    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String documentNumber;
    private java.sql.Date birthDate;
    private String country;
    private String address;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;

}





