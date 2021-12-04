package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void handleLogOutAction(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stageOld = (Stage) node.getScene().getWindow();
        stageOld.close();
    }
}
