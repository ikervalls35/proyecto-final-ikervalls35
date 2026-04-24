package com.tienda.controller;
import com.tienda.App;
import com.tienda.entity.Categoria;
import com.tienda.entity.Proveedor;
import com.tienda.entity.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProveedorService;
import com.tienda.service.ProductoService;
import com.tienda.api.ApiService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ApiService apiService;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TextField txtNombre, txtPrecio, txtStock;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, Boolean> colActivo;

    @FXML private ComboBox<Categoria> comboCategoria;
    @FXML private ComboBox<Proveedor> comboProveedor;

    @FXML
    private void initialize() {

        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colPrecio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrecio()));
        colStock.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getStock()));
        colActivo.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().isActivo()));

        tabla.getItems().setAll(service.findAll());

        comboCategoria.getItems().setAll(categoriaService.findAll());
        comboProveedor.getItems().setAll(proveedorService.findAll());
    }
   @FXML
    private void guardar() {

        if (txtNombre.getText().isEmpty() ||
                txtPrecio.getText().isEmpty() ||
                txtStock.getText().isEmpty() ||
                comboCategoria.getValue() == null ||
                comboProveedor.getValue() == null) {

            Alert a = new Alert(Alert.AlertType.WARNING, "Rellena todos los campos.");
            a.show();
            return;
        }

        try {
            Producto p = new Producto();
            p.setNombre(txtNombre.getText());
            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
            p.setStock(Integer.parseInt(txtStock.getText()));
            p.setCategoria(comboCategoria.getValue());
            p.setProveedor(comboProveedor.getValue());

            double precioUSD = apiService.convertirEuroADolar(p.getPrecio());
            System.out.println("Precio en USD: " + precioUSD);

            service.save(p);
            tabla.getItems().setAll(service.findAll());

        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Precio o stock no son válidos.");
            a.show();
        }
    }
    @FXML
    private void eliminar() {
        Producto seleccionado = tabla.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            service.delete(seleccionado.getId());
            tabla.getItems().setAll(service.findAll());
        }
    }
    @FXML
    private void exportarCSV() {
        try {
            FileWriter w = new FileWriter("productos.csv");

            w.write("id,nombre,precio,stock\n");

            for (Producto p : service.findAll()) {
                w.write(
                        p.getId() + "," +
                                p.getNombre() + "," +
                                p.getPrecio() + "," +
                                p.getStock() + "\n"
                );
            }
            w.close();

            Alert a = new Alert(Alert.AlertType.INFORMATION, "CSV generado.");
            a.show();

        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al generar CSV.");
            a.show();
        }
    }
    @FXML
    private void volverMenu(ActionEvent event) throws Exception {
        App.setRoot("main-view");
    }
}