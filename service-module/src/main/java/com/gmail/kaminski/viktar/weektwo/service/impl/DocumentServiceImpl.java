package com.gmail.kaminski.viktar.weektwo.service.impl;

import com.gmail.kaminski.viktar.weektwo.repository.DocumentRepository;
import com.gmail.kaminski.viktar.weektwo.repository.model.Document;
import com.gmail.kaminski.viktar.weektwo.service.DocumentService;
import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;
import com.gmail.kaminski.viktar.weektwo.service.util.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;
    private DocumentConverter documentConverter;


    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentConverter documentConverter) {
        this.documentRepository = documentRepository;
        this.documentConverter = documentConverter;
    }

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        documentDTO.setUuid(UUID.randomUUID().toString());
        Document document = documentConverter.toDocument(documentDTO);
        Document documentAdded = documentRepository.add(document);
        return documentConverter.toDocumentDTO(documentAdded);
    }

    @Override
    public DocumentDTO get(Long id) {
        Document document = documentRepository.get(id);
        if (document == null) {
            return null;
        }
        return documentConverter.toDocumentDTO(document);
    }

    @Override
    public void delete(Long id) {
        documentRepository.delete(id);
    }
}
