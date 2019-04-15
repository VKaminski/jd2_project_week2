package com.gmail.kaminski.viktar.weektwo.controller.impl;

import com.gmail.kaminski.viktar.weektwo.controller.DocumentController;
import com.gmail.kaminski.viktar.weektwo.controller.exception.ControllerException;
import com.gmail.kaminski.viktar.weektwo.service.DocumentService;
import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DocumentControllerImpl implements DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentControllerImpl(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        String description = documentDTO.getDescription();
        if (description == null) {
            throw new ControllerException("Description has NULL value!");
        }
        if (description.length() > 100) {
            throw new ControllerException("Description longer than 100 symbols!");
        }
        return documentService.add(documentDTO);
    }

    @Override
    public DocumentDTO get(Long id) {
        if (id == null) {
            throw new ControllerException("ID is NULL!");
        }
        DocumentDTO documentDTO = documentService.get(id);
        return documentDTO;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new ControllerException("ID is NULL");
        }
        documentService.delete(id);
    }
}
