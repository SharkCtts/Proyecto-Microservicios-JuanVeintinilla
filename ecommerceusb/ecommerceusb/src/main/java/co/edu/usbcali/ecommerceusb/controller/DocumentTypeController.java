package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CreateDocumentTypeRequest;
import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.service.DocumentTypeService;
import co.edu.usbcali.ecommerceusb.dto.CreateDocumentTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document-type")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/all")
    public List<DocumentTypeResponse> getAll() {
        return documentTypeService.getAllDocumentTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(
                documentTypeService.getDocumentTypeById(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<DocumentTypeResponse> getByCode(@PathVariable String code) {
        return new ResponseEntity<>(
                documentTypeService.getDocumentTypeByCode(code),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> update(
            @PathVariable Integer id,
            @RequestBody CreateDocumentTypeRequest request
    ) throws Exception {

        return new ResponseEntity<>(
                documentTypeService.update(id, request),
                HttpStatus.OK
        );
    }

}