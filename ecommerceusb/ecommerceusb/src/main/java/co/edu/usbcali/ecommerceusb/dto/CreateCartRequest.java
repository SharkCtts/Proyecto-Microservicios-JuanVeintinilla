package co.edu.usbcali.ecommerceusb.dto;

import co.edu.usbcali.ecommerceusb.model.CartStatus;
import lombok.Getter;

@Getter
public class CreateCartRequest {

    private Integer userId;
    private CartStatus status; // opcional, puedes dejarlo null

}