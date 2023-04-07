module com.example.lab1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab1 to javafx.fxml;
    exports com.example.lab1;
    exports com.example.lab1.view;
    opens com.example.lab1.view to javafx.fxml;
    exports com.example.lab1.model;
    opens com.example.lab1.model to javafx.fxml;
    exports com.example.lab1.core;
    opens com.example.lab1.core to javafx.fxml;
    exports com.example.lab1.presenter;
    opens com.example.lab1.presenter to javafx.fxml;
    exports com.example.lab1.core.ai;
    opens com.example.lab1.core.ai to javafx.fxml;
}