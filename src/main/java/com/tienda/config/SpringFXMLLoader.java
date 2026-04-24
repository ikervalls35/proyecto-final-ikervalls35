package com.tienda.config;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class SpringFXMLLoader {

    private static ApplicationContext context;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static FXMLLoader load(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(SpringFXMLLoader.class.getResource(fxmlPath));
        loader.setControllerFactory(context::getBean);
        loader.load();
        return loader;
    }
}