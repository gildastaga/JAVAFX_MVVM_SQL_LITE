module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires javafx.base;

    opens main to javafx.fxml;
    exports main;
    //exports mvvm;
    //exports model;
}