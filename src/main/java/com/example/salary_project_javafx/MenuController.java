package com.example.salary_project_javafx;

import com.example.salary_project_javafx.Database.PayPerHourDAO;
import com.example.salary_project_javafx.Database.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private TextField PayPerHour ;
    @FXML
    private TextField UserName;
    @FXML
    private TextField UserLastName;
    @FXML
    private TextField UserId;

    @FXML
    public  void initialize(){
        PayPerHour.setText(PayPerHourDAO.getLastPayPerHour());
    }

    public void onClickButtonPayPerHour(ActionEvent event) {
        String result = PayPerHourDAO.createRecordPayPerHour(PayPerHour.getText());
        AlertBox("Transaccción", result);
    }

    public void onClickUserRegistryButton(ActionEvent event) {
        String result = UsersDAO.createUser(UserId.getText(),UserName.getText(), UserLastName.getText());
        AlertBox("Transaccción", result);
    }
    public void AlertBox(String title, String message){
        Stage ventana = new Stage();

        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle(title);
        ventana.setMinWidth(300);

        Label mensaje = new Label();
        mensaje.setText(message);
        mensaje.setAlignment(Pos.CENTER);
        Button botonCerrar = new Button ("Cerrar");
        botonCerrar.setAlignment(Pos.CENTER);
        botonCerrar.setOnAction(e -> {
            ventana.close();
            UserName.clear();
            UserLastName.clear();
            UserId.clear();
        });

        VBox layout = new VBox(50);
        layout.getChildren().addAll(mensaje, botonCerrar);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        ventana.setScene(scene);
        ventana.showAndWait();
    }
}