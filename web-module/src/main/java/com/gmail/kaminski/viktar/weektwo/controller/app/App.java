package com.gmail.kaminski.viktar.weektwo.controller.app;


import com.gmail.kaminski.viktar.weektwo.controller.DocumentController;
import com.gmail.kaminski.viktar.weektwo.controller.config.AppConfig;
import com.gmail.kaminski.viktar.weektwo.service.model.DocumentDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        DocumentController documentController = context.getBean(DocumentController.class);

        DocumentDTO documentDTO1 = new DocumentDTO();
        DocumentDTO documentDTO2 = new DocumentDTO();
        DocumentDTO documentDTO3 = new DocumentDTO();
        documentDTO1.setDescription("Hello");
        documentDTO2.setDescription("Привет");
        documentDTO3.setDescription("Ohayo");
        documentController.add(documentDTO1);
        documentController.add(documentDTO2);
        documentController.add(documentDTO3);
        documentController.get(2l);
        documentController.delete(1l);
    }
}
