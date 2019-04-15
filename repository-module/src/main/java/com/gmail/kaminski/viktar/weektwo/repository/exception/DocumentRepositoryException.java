package com.gmail.kaminski.viktar.weektwo.repository.exception;

public class DocumentRepositoryException extends RuntimeException {
    public DocumentRepositoryException(String message) {
        super(message);
    }

    public DocumentRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
