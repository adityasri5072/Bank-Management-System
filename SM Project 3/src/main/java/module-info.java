module com.example.sm_project_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.junit.jupiter.api;

    opens com.example.sm_project_3 to javafx.fxml;
    exports com.example.sm_project_3;
}