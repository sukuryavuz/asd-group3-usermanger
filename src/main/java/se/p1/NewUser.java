package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NewUser {
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField username;
    @FXML
    PasswordField passwordField;






    @FXML
    public void handleCreateUserAction(ActionEvent actionEvent) {
        System.out.println(name.getText() + " " + surname.getText() + " " + username.getText() + " " + passwordField.getText());
    }
}
