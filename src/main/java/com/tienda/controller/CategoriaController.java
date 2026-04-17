package com.tienda.controller;
import com.tienda.entity.Categoria;
import com.tienda.service.CategoriaService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @FXML
    private TableView<Categoria> tabla;

    @FXML
    private TextField txtNombre, txtDescripcion;
    @FXML private TableColumn<Categoria, String> colNombre;
    @FXML private TableColumn<Categoria, String> colDescripcion;
    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colDescripcion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescripcion()));
        tabla.getItems().setAll(service.findAll());
    }

    @FXML
    private void guardar() {
        Categoria c = new Categoria();
        c.setNombre(txtNombre.getText());
        c.setDescripcion(txtDescripcion.getText());

        service.save(c);
        tabla.getItems().setAll(service.findAll());
    }

    @FXML
    private void eliminar() {
        Categoria seleccionada = tabla.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            service.delete(seleccionada.getId());
            tabla.getItems().setAll(service.findAll());
        }
    }
}