package com.wernerparedes.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wernerparedes.webapp.biblioteca.model.Cliente;
import com.wernerparedes.webapp.biblioteca.model.Empleado;
import com.wernerparedes.webapp.biblioteca.model.Prestamo;
import com.wernerparedes.webapp.biblioteca.service.ClienteService;
import com.wernerparedes.webapp.biblioteca.service.EmpleadoService;
import com.wernerparedes.webapp.biblioteca.service.PrestamoService;
import com.wernerparedes.webapp.biblioteca.system.Main;
import com.wernerparedes.webapp.biblioteca.util.MethodType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class PrestamoControllerFX implements Initializable {

    @FXML
    TextField tfId, tfPrestamo, tfDevolucion, tfVigencia, tfBuscar;

    @FXML
    Button btnGuardar, btnLimpiar, btnRegresar, btnEliminar, btnBuscar;

    @FXML
    ComboBox cmbLibro, cmbEmpleado, cmbCliente;

    @FXML
    TableView tblPrestamos;

    @FXML
    TableColumn colId, colPrestamo, colDevolucion, colVigencia, colEmpleado, colCliente, colLibro, colPres;

    @Setter
    private Main stage;

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EmpleadoService empleadoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbEmpleado.setItems(FXCollections.observableList(empleadoService.listarEmpleados()));
        cmbCliente.setItems(FXCollections.observableList(clienteService.listarClientes()));
        cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                agregarPrestamo();
            } else {
                eliminarPrestamo();
            }
        } else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        } else if (event.getSource() == btnRegresar) {
            stage.indexView();
        } else if (event.getSource() == btnEliminar) {
            eliminarPrestamo();
        } else if (event.getSource() == btnBuscar) {
            tblPrestamos.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblPrestamos.getItems().add(buscarPrestamo());
                colId.setCellValueFactory(new PropertyValueFactory<Prestamo, Long>("id"));
                colPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, Date>("fechaDePrestamo"));
                colDevolucion.setCellValueFactory(new PropertyValueFactory<Prestamo, Date>("fechaDeDevolucion"));
                colVigencia.setCellValueFactory(new PropertyValueFactory<Prestamo, Boolean>("vigencia"));
                colEmpleado.setCellValueFactory(new PropertyValueFactory<Prestamo, Empleado>("empleado"));
                colCliente.setCellValueFactory(new PropertyValueFactory<Prestamo, Cliente>("cliente"));
            }

        }
    }

    public void cargarDatos() {
        tblPrestamos.setItems(listarPrestamos());
        colId.setCellValueFactory(new PropertyValueFactory<Prestamo, Long>("id"));
        colPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, Date>("fechaDePrestamo"));
        colDevolucion.setCellValueFactory(new PropertyValueFactory<Prestamo, Date>("fechaDeDevolucion"));
        colVigencia.setCellValueFactory(new PropertyValueFactory<Prestamo, Boolean>("vigencia"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Prestamo, Empleado>("empleado"));
        colCliente.setCellValueFactory(new PropertyValueFactory<Prestamo, Cliente>("cliente"));
    }

    public ObservableList<Prestamo> listarPrestamos() {
        return FXCollections.observableList(prestamoService.listarPrestamos());
    }

    public void cargarForm() {
        Prestamo prestamo = (Prestamo) tblPrestamos.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            tfId.setText(prestamo.getId().toString());
            tfPrestamo.setText(prestamo.getFechaDePrestamo().toString());
            tfDevolucion.setText(prestamo.getFechaDeDevolucion().toString());
            tfVigencia.setText(prestamo.getVigencia().toString());
            cmbEmpleado.getSelectionModel().select(obtenerIndexEmpleado());
            cmbCliente.getSelectionModel().select(obtenerIndexCliente());
        }
    }

    public void limpiarForm() {
        tfId.clear();
        tfPrestamo.clear();
        tfDevolucion.clear();
        tfVigencia.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbEmpleado.getSelectionModel().clearSelection();
    }

    public void agregarPrestamo() {
        try {
            Prestamo prestamo = new Prestamo();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaPrestamo = formatter.parse(tfPrestamo.getText());
            java.util.Date fechaDevolucion = formatter.parse(tfDevolucion.getText());
            prestamo.setFechaDePrestamo(new java.sql.Date(fechaPrestamo.getTime()));
            prestamo.setFechaDeDevolucion(new java.sql.Date(fechaDevolucion.getTime()));
            prestamo.setVigencia(true);
            prestamo.setCliente((Cliente)cmbCliente.getSelectionModel().getSelectedItem());
            prestamo.setEmpleado((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem());
            prestamoService.guardarPrestamo(prestamo, MethodType.POST);
        cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void editarPrestamo() {
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(Long.parseLong(tfId.getText()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaPrestamo = formatter.parse(tfPrestamo.getText());
            java.util.Date fechaDevolucion = formatter.parse(tfDevolucion.getText());
            prestamo.setFechaDePrestamo(new java.sql.Date(fechaPrestamo.getTime()));
            prestamo.setFechaDeDevolucion(new java.sql.Date(fechaDevolucion.getTime()));
            prestamo.setCliente((Cliente)cmbCliente.getSelectionModel().getSelectedItem());
            prestamo.setEmpleado((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem());
            prestamoService.guardarPrestamo(prestamo, MethodType.PUT);
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void eliminarPrestamo() {
        Prestamo prestamo = prestamoService.buscarPrestamoPorId(Long.parseLong(tfId.getText()));
        prestamoService.eliminarPrestamo(prestamo);
        cargarDatos();
    }

    public Prestamo buscarPrestamo() {
        return prestamoService.buscarPrestamoPorId(Long.parseLong(tfBuscar.getText()));
    }

    public int obtenerIndexEmpleado() {
        int index = 0;
        for (int i = 0; i < cmbEmpleado.getItems().size(); i++) {
            String empleadoCmb = cmbEmpleado.getItems().get(i).toString();
            String empleadoTbl = ((Prestamo) tblPrestamos.getSelectionModel().getSelectedItem()).getEmpleado().toString();
            System.out.println(empleadoCmb);
            System.out.println(empleadoTbl);
            if (empleadoCmb.equals(empleadoTbl)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int obtenerIndexCliente() {
        int index = 0;
        for (int i = 0; i < cmbCliente.getItems().size(); i++) {
            String clienteCmb = cmbCliente.getItems().get(i).toString();
            String clienteTbl = ((Prestamo) tblPrestamos.getSelectionModel().getSelectedItem()).getCliente().toString();
            System.out.println(clienteCmb);
            System.out.println(clienteTbl);
            if (clienteCmb.equals(clienteTbl)) {
                index = i;
                break;
            }
        }
        return index;
    }

}

