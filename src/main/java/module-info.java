module com.example.salary_project_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.salary_project_javafx to javafx.fxml;
    exports com.example.salary_project_javafx;
}