package com.gmail.kaminski.viktar.weektwo.service.util;

import com.gmail.kaminski.viktar.weektwo.repository.model.Document;
import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;
import org.springframework.stereotype.Component;

@Component
public class DocumentConverter {
    public Document toDocument(DocumentDTO documentDTO) {
        Document document = new Document();
        if(documentDTO.getId() != null){
            document.setId(documentDTO.getId());
        }
        document.setUuid(documentDTO.getUuid());
        document.setDescription(documentDTO.getDescription());
        return document;
    }

    public DocumentDTO toDocumentDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        if(document.getId() != null){
            documentDTO.setId(document.getId());
        }
        documentDTO.setUuid(document.getUuid());
        documentDTO.setDescription(document.getDescription());
        return documentDTO;
    }
}
