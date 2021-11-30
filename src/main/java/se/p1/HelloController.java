package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    TextField username;

    @FXML
    PasswordField passwordField;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to the Application!");
    }


    @FXML
    private Text actiontarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");

        Properties properties = readProperties();

        boolean validUser = isUserValid(properties, username.getText(), passwordField.getText());

        if (validUser){
            FXMLLoader fxmlLoader = new FXMLLoader(SignIn.class.getResource("sign-in.fxml"));

            hideStage(event);

            try {
                Parent root1 = (Parent) fxmlLoader.load();
                ((SignIn)fxmlLoader.getController()).displayUserName(username.getText());
                Stage stageNew = new Stage();
                stageNew.setTitle("Welcome "+ username.getText());
                stageNew.setScene(new Scene(root1));
                stageNew.setResizable(false);
                stageNew.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private boolean isUserValid(Properties properties, String username, String password) {
        Enumeration keys = properties.keys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            String value = properties.getProperty(key);
            if (key.equals(username)){
                if(value.equals(password) ){
                    return true;
                }
            }
           return false;
        }
        return false;
    }

    private Properties readProperties(){
        File file = new File("password.properties");

        Properties properties = null;

        try (FileInputStream fileInput = new FileInputStream(file)) {
            properties = new Properties();
            properties.load(fileInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @FXML
    public void handleResetPasswordAction(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader(ResetPassword.class.getResource("reset-password.fxml"));

        hideStage(actionEvent);

        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Reset Password");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();


    }

    private void hideStage(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stageOld = (Stage) node.getScene().getWindow();
        stageOld.close();
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