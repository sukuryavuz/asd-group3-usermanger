module se.p1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens se.p1 to javafx.fxml;
    exports se.p1;
}