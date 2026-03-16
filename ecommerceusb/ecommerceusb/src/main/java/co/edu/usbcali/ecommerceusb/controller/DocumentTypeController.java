package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentTypeController {

    private final DocumentTypeRepository documentTypeRepository;

    // Inyección por constructor
    public DocumentTypeController(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

}