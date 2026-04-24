package com.tienda.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.tienda.App;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private void abrirProductos() throws Exception {
        App.setRoot("productos-view");
    }


    @FXML
    private void abrirCategorias() throws Exception {
        App.setRoot("categorias-view");
    }

    @FXML
    private void abrirProveedores() throws Exception {
        App.setRoot("proveedores-view");
    }
}