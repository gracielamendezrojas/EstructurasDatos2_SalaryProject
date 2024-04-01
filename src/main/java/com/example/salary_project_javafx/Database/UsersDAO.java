package com.example.salary_project_javafx.Database;
import java.sql.*;

public class UsersDAO {

    private static String strConexion = "jdbc:mysql://database-salary-project.clae0oycgbhc.us-east-1.rds.amazonaws.com/project_salary?user=" + DatabaseInfo.getUser() + "&password=" + DatabaseInfo.getPassword() + ".&useSSL=false";

    public static void listUsers() {
        try {
            Connection conn = null;
            String query = "SELECT * FROM USERS";
            Statement stmt = null;
            ResultSet rs = null;
            //AJUSTAR con datos que te envié por chat
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);// el rs almacena la información de la base de datos.
            // TENGO QUE RECORRERLA
            while (rs.next()) { //rs.next devuelve true si hay más líneas en el result set. por defecto, al iniciar el ciclo, el rs está en la línea 0.
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("LASTNAME"));
                System.out.println(rs.getString("ID"));
            }
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void createUser(String id, String name, String lastname){
        try {

            String query = "INSERT INTO USERS(ID,NAME,LASTNAME) VALUES('" + id + "','" + name + "','" + lastname +"')";
            Connection conn = null;

            Statement stmt = null;
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            stmt.execute(query);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
