module se.p1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.p1 to javafx.fxml;
    exports se.p1;
}