package com.example.salary_project_javafx;

import com.example.salary_project_javafx.Database.PayPerHourDAO;
import com.example.salary_project_javafx.Database.UsersDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MenuController {
    @FXML
    private TextField PayPerHour ;

    @FXML
    public  void initialize(){
        PayPerHour.setText(PayPerHourDAO.getLastPayPerHour());
    }

}