package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentTypeResponse> getAllDocumentTypes();
    DocumentTypeResponse getDocumentTypeById(Integer id) throws Exception;;
    DocumentTypeResponse getDocumentTypeByCode(String code);

}