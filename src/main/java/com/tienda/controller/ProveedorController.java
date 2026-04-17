package com.tienda.controller;

import com.tienda.entity.Proveedor;
import com.tienda.service.ProveedorService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @FXML
    private TableView<Proveedor> tabla;

    @FXML
    private TextField txtNombre, txtTelefono, txtEmail;

    @FXML private TableColumn<Proveedor, String> colNombre;
    @FXML private TableColumn<Proveedor, String> colTelefono;
    @FXML private TableColumn<Proveedor, String> colEmail;
    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefono()));
        colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        tabla.getItems().setAll(service.findAll());
    }

    @FXML
    private void guardar() {
        Proveedor p = new Proveedor();
        p.setNombre(txtNombre.getText());
        p.setTelefono(txtTelefono.getText());
        p.setEmail(txtEmail.getText());

        service.save(p);
        tabla.getItems().setAll(service.findAll());
    }

    @FXML
    private void eliminar() {
        Proveedor seleccionado = tabla.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            service.delete(seleccionado.getId());
            tabla.getItems().setAll(service.findAll());
        }
    }
}