package com.example.salary_project_javafx;

import com.example.salary_project_javafx.Database.UsersDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SalaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SalaryApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Salary system");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        UsersDAO.listUsers();
        UsersDAO.createUser("1123746297", "Álvaro", "Fallas");
        launch();
    }
}