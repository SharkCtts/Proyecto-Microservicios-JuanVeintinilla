package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.mapper.DocumentTypeMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import co.edu.usbcali.ecommerceusb.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentTypeResponse> getAllDocumentTypes() {

        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        if (documentTypes.isEmpty()) {
            return List.of();
        }

        return DocumentTypeMapper.modelToDocumentTypeResponseList(documentTypes);
    }

    @Override
    public DocumentTypeResponse getDocumentTypeById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("Debe ingresar el id para buscar");
        }

        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                String.format("Tipo de documento no encontrado con el id: %d", id)
                        ));

        return DocumentTypeMapper.modelToDocumentTypeResponse(documentType);
    }

    @Override
    public DocumentTypeResponse getDocumentTypeByCode(String code) {

        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar el código para buscar");
        }

        DocumentType documentType = documentTypeRepository.findByCode(code)
                .orElseThrow(() ->
                        new RuntimeException(
                                String.format("Tipo de documento no encontrado con el código: %s", code)
                        ));

        return DocumentTypeMapper.modelToDocumentTypeResponse(documentType);
    }
}