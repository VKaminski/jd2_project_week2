package com.gmail.kaminski.viktar.weektwo.controller;

import com.gmail.kaminski.viktar.weektwo.controller.config.AppConfig;
import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class DocumentControllerIntegrationTest {
    private final String defaultTrueDescription = "11111111111111111111111111111111111111111111111111" +
            "11111111111111111111111111111111111111111111111111";

    @Autowired
    private DocumentController documentController;

    @Test
    public void shouldAddDocumentDTO() {
        String description = defaultTrueDescription;
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription(description);
        DocumentDTO documentDTOAnswer = documentController.add(documentDTO);
        Assert.assertNotNull(documentDTOAnswer);
        Assert.assertNotNull(documentDTOAnswer.getId());
        Assert.assertEquals(description, documentDTOAnswer.getDescription());
    }

    @Test
    public void shouldAddAndGetDocumentDTOById() {
        String description = defaultTrueDescription;
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription(description);
        DocumentDTO documentDTOAdded = documentController.add(documentDTO);
        Long id = documentDTOAdded.getId();
        String uuid = documentDTOAdded.getUuid();
        DocumentDTO documentDTOGetted = documentController.get(id);
        Assert.assertEquals(id, documentDTOGetted.getId());
        Assert.assertEquals(uuid, documentDTOGetted.getUuid());
        Assert.assertEquals(description, documentDTOGetted.getDescription());
    }

    @Test
    public void shouldAddAndGetDocumentDTOByAnotherId() {
        String description = defaultTrueDescription;
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription(description);
        DocumentDTO documentDTOAdded = documentController.add(documentDTO);
        Long id = documentDTOAdded.getId() + 1L;
        Assert.assertNull(documentController.get(id));
    }

    @Test
    public void shouldAddAndDeleteDocumentDTOById() {
        String description = defaultTrueDescription;
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription(description);
        DocumentDTO documentDTOAdded = documentController.add(documentDTO);
        Long id = documentDTOAdded.getId();
        documentController.delete(id);
        Assert.assertNull(documentController.get(id));
    }

    @Test
    public void shouldDeleteDocumentDTOByNonexistentId() {
        Long id = 100L;
        documentController.delete(id);
    }


}
