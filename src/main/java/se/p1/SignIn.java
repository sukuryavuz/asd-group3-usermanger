package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Reset Password");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();


    }

    public void handleDeleteAccountAction(ActionEvent actionEvent) {

        Properties props = readProperties();
        props.remove(userName.getText());

        try {
            props.store(new FileOutputStream(new File("password.properties")),null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
