package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignIn implements Initializable {



    @FXML
    private TextField userName;

    public void displayUserName(String passedUserName){
        userName.setText(passedUserName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
