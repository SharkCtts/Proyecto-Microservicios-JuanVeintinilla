/*

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






 */



package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.mapper.DocumentTypeMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/document-type")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @GetMapping("/all")
    public List<DocumentTypeResponse> getAll(){
        //invoca al mapper para convertir la lista de DocumentType
        //a una lista de DocumentTypeResponse
        return DocumentTypeMapper.modelToDocumentTypeResponseList(
                documentTypeRepository.findAll()
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> getById(@PathVariable int id){

        //consultar Document Type

        DocumentType documentType = documentTypeRepository.getReferenceById(id);

        //mapear o convertir DocumentTypeResponse
        //Invocando el mapper para convertir

        DocumentTypeResponse documentTypeResponse =
                DocumentTypeMapper.modelToDocumenTypeResponse(documentType);

        //Retornar el ResponseEntity con el documentTypeResponse

        return new ResponseEntity<>(
                documentTypeResponse,
                HttpStatus.OK
        );
    }

}