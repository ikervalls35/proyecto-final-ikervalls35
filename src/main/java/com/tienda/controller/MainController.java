package com.tienda.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.tienda.App;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private void abrirProductos(ActionEvent e) throws Exception {
        App.setRoot("productos-view");
    }

    @FXML
    private void abrirCategorias(ActionEvent e) throws Exception {
        App.setRoot("categorias-view");
    }

    @FXML
    private void abrirProveedores(ActionEvent e) throws Exception {
        App.setRoot("proveedores-view");
    }
}