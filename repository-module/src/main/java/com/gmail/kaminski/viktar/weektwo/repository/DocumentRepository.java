package com.gmail.kaminski.viktar.weektwo.repository;

import com.gmail.kaminski.viktar.weektwo.repository.model.Document;

public interface DocumentRepository {
    Document add(Document document);

    Document get(Long id);

    void delete(Long id);
}
