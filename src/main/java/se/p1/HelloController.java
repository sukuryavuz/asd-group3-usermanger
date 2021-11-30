package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    TextField username;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to the Application!");
    }


    @FXML
    private Text actiontarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");

        FXMLLoader fxmlLoader = new FXMLLoader(SignIn.class.getResource("sign-in.fxml"));

        Node node = (Node) event.getSource();
        // Step 3
        Stage stageOld = (Stage) node.getScene().getWindow();
        stageOld.close();
        try {
            Parent root1 = (Parent) fxmlLoader.load();
            ((SignIn)fxmlLoader.getController()).displayUserName(username.getText());
            Stage stageNew = new Stage();
            //set what you want on your stage
            stageNew.setTitle("Welcome "+ username.getText());
            stageNew.setScene(new Scene(root1));
            stageNew.setResizable(false);
            stageNew.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleResetPasswordAction(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader(ResetPassword.class.getResource("reset-password.fxml"));

        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Report Page");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();


    }

    @FXML
    public void handleNewUserAction(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader(ResetPassword.class.getResource("new-user.fxml"));

        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Hello new user!");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();

    }
}