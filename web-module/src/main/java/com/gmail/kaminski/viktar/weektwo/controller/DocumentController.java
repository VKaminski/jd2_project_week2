package com.gmail.kaminski.viktar.weektwo.controller;

import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;

public interface DocumentController {
    DocumentDTO add(DocumentDTO documentDTO);

    DocumentDTO get(Long id);

    void delete(Long id);
}
