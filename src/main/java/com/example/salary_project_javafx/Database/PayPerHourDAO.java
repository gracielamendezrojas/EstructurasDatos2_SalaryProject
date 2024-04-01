package com.example.salary_project_javafx.Database;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class PayPerHourDAO {
    private static String strConexion = "jdbc:mysql://database-salary-project.clae0oycgbhc.us-east-1.rds.amazonaws.com/project_salary?user=" + DatabaseInfo.getUser() + "&password=" + DatabaseInfo.getPassword() + ".&useSSL=false";

    public static String getLastPayPerHour() {
        String lastPayHour = "0";
        try {
            Connection conn = null;
            String query = "SELECT hourpay FROM HOURPAY ORDER BY id DESC LIMIT 1";
            Statement stmt = null;
            ResultSet rs = null;
            //AJUSTAR con datos que te envié por chat
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);// el rs almacena la información de la base de datos.
            // TENGO QUE RECORRERLA
            while (rs.next()) { //rs.next devuelve true si hay más líneas en el result set. por defecto, al iniciar el ciclo, el rs está en la línea 0.
                lastPayHour= rs.getString("HOURPAY");
            }
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return lastPayHour;
    }
    public static String createRecordPayPerHour(String newHourPay){
        String result = "El pago por hora fue actualizado.";
        try {
            BigDecimal newHourPayDecimalValue = new BigDecimal(newHourPay);
            String query = "INSERT INTO HOURPAY(date, hourpay) VALUES('" + LocalDate.now() + "','" + newHourPayDecimalValue +"')";
            Connection conn = null;

            Statement stmt = null;
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            stmt.execute(query);
        }
        catch (Exception e){
            result = "Hubo un error: " + e.getMessage() + ". Intente de nuevo o contacte al administrador.";
        }
        return result;
    }

}
