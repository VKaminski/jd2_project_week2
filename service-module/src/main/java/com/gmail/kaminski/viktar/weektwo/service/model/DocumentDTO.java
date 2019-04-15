package com.gmail.kaminski.viktar.weektwo.service.model;

public class DocumentDTO {
    private Long id;
    private String uuid;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String Uuid) {
        this.uuid = Uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
