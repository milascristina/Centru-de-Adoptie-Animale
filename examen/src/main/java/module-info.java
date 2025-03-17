module com.example.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.examen to javafx.fxml;
    opens domain to javafx.base;
    exports com.example.examen;
}