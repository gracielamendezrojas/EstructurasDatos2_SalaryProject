package com.example.salary_project_javafx;

import com.example.salary_project_javafx.Database.PayPerHourDAO;
import com.example.salary_project_javafx.Database.UsersDAO;
import com.example.salary_project_javafx.Database.WorkedHours;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.w3c.dom.Text;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

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
    private TextField UserIdSearch;

    @FXML
    private Label UserFullName;

    @FXML
    private TextField WorkedHoursMonday1;

    @FXML
    private TextField WorkedHoursTuesday1;

    @FXML
    private TextField WorkedHoursMonday2;

    @FXML
    private TextField WorkedHoursTuesday2;

    @FXML
    private TextField WorkedHoursWednesday1;

    @FXML
    private TextField WorkedHoursWednesday2;

    @FXML
    private TextField WorkedHoursThursday1;

    @FXML
    private TextField WorkedHoursFriday1;

    @FXML
    private TextField WorkedHoursThursday2;

    @FXML
    private TextField WorkedHoursFriday2;

    @FXML
    private TextField WorkedHoursSaturday1;

    @FXML
    private TextField WorkedHoursSaturday2;

    @FXML
    private TextField WorkedHoursSunday1;

    @FXML
    private TextField WorkedHoursSunday2;

    @FXML
    private DatePicker DateInit;

    @FXML
    private DatePicker DateFinal;

    @FXML
    private TextField TotalWorkedHours2Weeks;

    @FXML
    private TextField TotalExtraWorkedHours;

    @FXML
    private TextField BaseSalary;

    @FXML
    private TextField ExtraWorkedHoursSalary;

    @FXML
    private TextField TotalSalary;

    private int hours = 0;
    private int extraHours = 0;
    private double payPerHour = Double.parseDouble(PayPerHourDAO.getLastPayPerHour());
    private double salary = 0;
    private double extraSalary = 0;
    private LocalDate startDate;
    private LocalDate endDate;

    private WorkedHours workedHours = new WorkedHours();

    private int totalHours;

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
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
    public void onClickSearchUserId(ActionEvent actionEvent){
        String result = workedHours.getFullName(UserIdSearch.getText());
        UserFullName.setText(result);
    }

    public void onClickCalculateHours(ActionEvent e){

        hours = Integer.parseInt(WorkedHoursMonday1.getText()) +
                Integer.parseInt(WorkedHoursTuesday1.getText()) +
                Integer.parseInt(WorkedHoursWednesday1.getText()) +
                Integer.parseInt(WorkedHoursThursday1.getText()) +
                Integer.parseInt(WorkedHoursFriday1.getText()) +
                Integer.parseInt(WorkedHoursSaturday1.getText()) +
                Integer.parseInt(WorkedHoursSunday1.getText()) +
                Integer.parseInt(WorkedHoursMonday2.getText()) +
                Integer.parseInt(WorkedHoursTuesday2.getText()) +
                Integer.parseInt(WorkedHoursWednesday2.getText()) +
                Integer.parseInt(WorkedHoursThursday2.getText()) +
                Integer.parseInt(WorkedHoursFriday2.getText()) +
                Integer.parseInt(WorkedHoursSaturday2.getText()) +
                Integer.parseInt(WorkedHoursSunday2.getText());
        setTotalHours(hours);

        if(hours <= 0 || Objects.equals(UserIdSearch.getText(), "") || UserIdSearch.getText() == null){
            AlertBox("Error", "Debe ingresar al menos una hora para realizar el cálculo o ingrese la identidad de la persona");
        }else{
            if(hours > 80){
                extraHours = hours - 80;
                hours = hours - extraHours;
                System.out.println(extraHours);
                System.out.println(hours);
            }
            if(extraHours > 0){
                System.out.println(extraHours);
                System.out.println(payPerHour);
                extraSalary = (extraHours * payPerHour) * 1.5;
            }
            salary = payPerHour * hours;

            startDate = DateInit.getValue();

            endDate = DateFinal.getValue();

            TotalWorkedHours2Weeks.setText(String.valueOf(hours));

            TotalExtraWorkedHours.setText(String.valueOf(extraHours));

            BaseSalary.setText(String.valueOf(salary));

            ExtraWorkedHoursSalary.setText(String.valueOf(extraSalary));

            TotalSalary.setText(String.valueOf(salary+extraSalary));
        }



    }

    public void saveOnDB(){
        hours = Integer.parseInt(WorkedHoursMonday1.getText()) +
                Integer.parseInt(WorkedHoursTuesday1.getText()) +
                Integer.parseInt(WorkedHoursWednesday1.getText()) +
                Integer.parseInt(WorkedHoursThursday1.getText()) +
                Integer.parseInt(WorkedHoursFriday1.getText()) +
                Integer.parseInt(WorkedHoursSaturday1.getText()) +
                Integer.parseInt(WorkedHoursSunday1.getText()) +
                Integer.parseInt(WorkedHoursMonday2.getText()) +
                Integer.parseInt(WorkedHoursTuesday2.getText()) +
                Integer.parseInt(WorkedHoursWednesday2.getText()) +
                Integer.parseInt(WorkedHoursThursday2.getText()) +
                Integer.parseInt(WorkedHoursFriday2.getText()) +
                Integer.parseInt(WorkedHoursSaturday2.getText()) +
                Integer.parseInt(WorkedHoursSunday2.getText());
        setTotalHours(hours);
        if(DateInit.getValue() == null || DateFinal.getValue() == null){
            AlertBox("Error", "Debe ingresar una fecha de inicio y una fecha de final");
        }else{
            if(hours <= 0 || Objects.equals(UserIdSearch.getText(), "") || UserIdSearch.getText() == null){
                AlertBox("Error", "Debe ingresar al menos una hora para realizar el cálculo o ingrese la identidad de la persona");
            }else{
                if(hours > 80){

                    extraHours = hours - 80;
                    hours = hours - extraHours;
                    System.out.println(extraHours);
                    System.out.println(hours);
                }
                if(extraHours > 0){
                    System.out.println(extraHours);
                    System.out.println(payPerHour);
                    extraSalary = (extraHours * payPerHour) * 1.5;
                }
                salary = payPerHour * hours;

                startDate = DateInit.getValue();

                endDate = DateFinal.getValue();

                TotalWorkedHours2Weeks.setText(String.valueOf(hours));

                TotalExtraWorkedHours.setText(String.valueOf(extraHours));

                BaseSalary.setText(String.valueOf(salary));

                ExtraWorkedHoursSalary.setText(String.valueOf(extraSalary));

                TotalSalary.setText(String.valueOf(salary+extraSalary));

                workedHours.insertUserPaymentData(UserIdSearch.getText(), hours, extraHours, salary, extraSalary, startDate, endDate);
                AlertBox("Datos guardados", "Los datos se almacenaron correctamente");
            }
        }


    }

    public void clearScreen(){
                WorkedHoursMonday1.setText("0");
                WorkedHoursTuesday1.setText("0");
                WorkedHoursWednesday1.setText("0");
                WorkedHoursThursday1.setText("0");
                WorkedHoursFriday1.setText("0");
                WorkedHoursSaturday1.setText("0");
                WorkedHoursSunday1.setText("0");
                WorkedHoursMonday2.setText("0");
                WorkedHoursTuesday2.setText("0");
                WorkedHoursWednesday2.setText("0");
                WorkedHoursThursday2.setText("0");
                WorkedHoursFriday2.setText("0");
                WorkedHoursSaturday2.setText("0");
                WorkedHoursSunday2.setText("0");

                TotalWorkedHours2Weeks.setText("");

                TotalExtraWorkedHours.setText("");

                BaseSalary.setText("");

                ExtraWorkedHoursSalary.setText("");

                TotalSalary.setText("");
    }
}