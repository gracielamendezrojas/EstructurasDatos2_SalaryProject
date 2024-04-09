package com.example.salary_project_javafx.Database;

import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.sql.Date;
public class WorkedHours {
    private static String strConexion = "jdbc:mysql://database-salary-project.clae0oycgbhc.us-east-1.rds.amazonaws.com/project_salary?user=" + DatabaseInfo.getUser() + "&password=" + DatabaseInfo.getPassword() + ".&useSSL=false";

    public String getFullName(String id){
        String result = "";

        try {
            Connection conn = null;
            String query = "SELECT NAME, LASTNAME FROM USERS where ID = " + id;
            Statement stmt = null;
            ResultSet rs = null;
            String name = "";
            String lastName = "";
            //AJUSTAR con datos que te envié por chat
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);// el rs almacena la información de la base de datos.
            if (rs.next()) {
                // Obtener los valores de las columnas "NAME" y "LASTNAME"
                name = rs.getString("NAME");
                lastName = rs.getString("LASTNAME");

                // Concatenar los valores en una sola cadena
                result = name + " " + lastName;
            }
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    public void insertUserPaymentData(String userId, int regularHours, int extraHours, double salary, double extraSalary, LocalDate startDate, LocalDate endDate){
        String result = "";

        try {

            String query = "INSERT INTO SALARY(userId, RegularHours, ExtraHours, Salary, ExtraSalary, StartDate, EndDate) VALUES('" + userId + "','" + regularHours +"','" + extraHours+
                    "','"+ salary+ "','" + extraSalary + "','" + startDate + "','" + endDate + "')";
            Connection conn = null;

            Statement stmt = null;
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            stmt.execute(query);
        }
        catch (Exception e){
            System.out.println("Hubo un error: " + e.getMessage() + ". Intente de nuevo o contacte al administrador.");
        }
    }
}
