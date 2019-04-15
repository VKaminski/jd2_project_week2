package com.gmail.kaminski.viktar.weektwo.service;

import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;

public interface DocumentService {
    DocumentDTO add(DocumentDTO documentDTO);

    DocumentDTO get(Long id);

    void delete(Long id);
}
