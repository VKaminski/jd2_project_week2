package com.gmail.kaminski.viktar.weektwo.controller;

import com.gmail.kaminski.viktar.weektwo.controller.exception.ControllerException;
import com.gmail.kaminski.viktar.weektwo.controller.impl.DocumentControllerImpl;
import com.gmail.kaminski.viktar.weektwo.service.DocumentService;
import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentControllerTest {

    private DocumentController documentController;
    @Mock
    private DocumentService documentService;

    @Before
    public void init() {
        documentController = new DocumentControllerImpl(documentService);
    }

    @Test
    public void shouldAddCorrectDocumentDTO() {
        String description = "11111111111111111111111111111111111111111111111111" +
                "11111111111111111111111111111111111111111111111111";
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription(description);
        Long id = 1L;
        DocumentDTO documentDTOServiceAnswer = new DocumentDTO();
        documentDTOServiceAnswer.setId(id);
        documentDTOServiceAnswer.setDescription(description);
        when(documentService.add(documentDTO)).thenReturn(documentDTOServiceAnswer);
        DocumentDTO documentDTOAnswer = documentController.add(documentDTO);
        Assert.assertEquals(documentDTO.getDescription(), documentDTOAnswer.getDescription());
        Assert.assertNotNull(documentDTOAnswer.getId());
    }

    @Test(expected = ControllerException.class)
    public void shouldAddDocumentDTOWithLongThatHundredSymbolsDescription() {
        String description = "11111111111111111111111111111111111111111111111111" +
                              "111111111111111111111111111111111111111111111111111";
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription(description);
        documentController.add(documentDTO);
    }

    @Test(expected = ControllerException.class)
    public void shouldAddDocumentDTOWithoutDescription() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentController.add(documentDTO);
    }

    @Test
    public void shouldGetDocumentDTOByCorrectId() {
        Long id = 1L;
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(id);
        when(documentService.get(id)).thenReturn(documentDTO);
        DocumentDTO documentDTOAnswer = documentController.get(id);
        Assert.assertEquals(id, documentDTOAnswer.getId());
    }

    @Test(expected = ControllerException.class)
    public void shouldGetDocumentDTOByNullId() {
        Long id = null;
        documentController.get(id);
    }


    @Test
    public void shouldDeleteDocumentDTOWithCorrectId() {
        Long id = 1L;
        documentController.delete(id);
    }

    @Test(expected = ControllerException.class)
    public void shouldDeleteDocumentDTOWithNullId() {
        Long id = null;
        documentController.delete(id);
    }


}
