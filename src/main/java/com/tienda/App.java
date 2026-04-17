package com.tienda;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class App extends Application {

    private static ConfigurableApplicationContext springContext;
    private static Scene scene;

    @Override
    public void init() {
        springContext =
                new SpringApplicationBuilder(ProyectoFinalIkerVallsApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("main-view"));
        stage.setScene(scene);
        stage.setTitle("Tienda - Inventario");
        stage.setWidth(900);
        stage.setHeight(600);

        stage.show();
    }

    public static void setRoot(String fxml) throws Exception {
        scene.setRoot(loadFXML(fxml));
    }

    private static javafx.scene.Parent loadFXML(String fxml) throws Exception {
        FXMLLoader loader =
                new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));

        loader.setControllerFactory(springContext::getBean);

        return loader.load();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch();
    }
}