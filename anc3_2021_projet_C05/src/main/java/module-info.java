module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires javafx.base;
    requires java.sql;

    opens main to javafx.fxml;
    exports main;
    //exports mvvm;
    //exports model;
}