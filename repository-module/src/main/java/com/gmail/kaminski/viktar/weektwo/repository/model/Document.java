package com.gmail.kaminski.viktar.weektwo.repository.model;

public class Document {
    private Long id;
    private String UUID;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return UUID;
    }

    public void setUuid(String Uuid) {
        this.UUID = Uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
