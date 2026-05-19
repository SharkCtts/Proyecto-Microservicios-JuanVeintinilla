package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.mapper.DocumentTypeMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import co.edu.usbcali.ecommerceusb.dto.CreateDocumentTypeRequest;
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

    //METODO PARA EL PUT

    @Override
    public DocumentTypeResponse update(
            Integer id,
            CreateDocumentTypeRequest request
    ) throws Exception {

        // 🔹 Validar ID
        if (id == null || id <= 0) {
            throw new Exception("Id inválido");
        }

        // 🔹 Validar request
        if (request == null) {
            throw new Exception("El request no puede ser null");
        }

        // 🔹 Buscar document type
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Tipo de documento no encontrado con id: " + id)
                );

        // 🔹 Actualizar code
        if (request.getCode() != null && !request.getCode().isBlank()) {

            boolean exists = documentTypeRepository.findAll().stream()
                    .anyMatch(dt ->
                            dt.getCode().equalsIgnoreCase(request.getCode())
                                    && !dt.getId().equals(id)
                    );

            if (exists) {
                throw new Exception("Ya existe un tipo de documento con ese código");
            }

            documentType.setCode(request.getCode());
        }

        // 🔹 Actualizar name
        if (request.getName() != null && !request.getName().isBlank()) {
            documentType.setName(request.getName());
        }

        // 🔹 Guardar cambios
        DocumentType updated = documentTypeRepository.save(documentType);

        return DocumentTypeMapper.modelToDocumentTypeResponse(updated);
    }

    //DELETE
    @Override
    public void delete(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Tipo de documento no encontrado con id: " + id)
                );

        documentTypeRepository.delete(documentType);
    }

}