package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.model.DocumentType;

import java.util.ArrayList;
import java.util.List;

public class DocumentTypeMapper {

    public static DocumentTypeResponse modelToDocumenTypeResponse(DocumentType documentType) {
        return DocumentTypeResponse.builder()
                .id(documentType.getId())
                .code(documentType.getCode())
                .name(documentType.getName())
                .build();

    }

    public static List<DocumentTypeResponse> modelToDocumentTypeResponseList(
        List<DocumentType> documentTypes){

        //leer documentTypes por for each

        List<DocumentTypeResponse> documentTypeResponseList = new ArrayList<>();

        for (DocumentType documentType : documentTypes){
            DocumentTypeResponse documentTypeResponse = modelToDocumenTypeResponse(documentType);
            documentTypeResponseList.add(documentTypeResponse);
        }

        //simplificado
        /*
        * List<DocumentTypeResponse> documentTypeResponseList = new ArrayList<>();
        * for (DocumentType documentType : documentTypes) {
        *   DocumentTypeResponse documentTypeResponse = modelToDocumentTypeResponse(
        *
        * ya no vi como seguía xddddd
        *
        * */

        return documentTypeResponseList;

    }

}



