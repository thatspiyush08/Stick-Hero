module com.example.ap_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;

    requires org.junit.platform.commons;
    opens com.example.ap_project to javafx.fxml;
    exports com.example.ap_project;
}