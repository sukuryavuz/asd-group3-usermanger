package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetPassword {

    @FXML private TextField username;
    @FXML private PasswordField passwordFieldOld;
    @FXML private PasswordField passwordFieldNew;
    @FXML private PasswordField passwordFieldNew2;
    private Caeser caeser = new Caeser();

    public void handleResetAction(ActionEvent actionEvent) {

        Properties props = readProperties();
        boolean isValid = isUserValid(props,username.getText(),passwordFieldOld.getText());

        if (passwordFieldNew.getText().equals(passwordFieldNew2.getText())){
            if (isValid){
                Enumeration keys = props.keys();

                while (keys.hasMoreElements()) {
                    String key = keys.nextElement().toString();
                    if (key.equals(username.getText())){
                        props.setProperty(key,caeser.encrypt(passwordFieldNew.getText()));
                    }
                }

                try {
                    props.store(new FileOutputStream(new File("password.properties")),null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            Node node = (Node) actionEvent.getSource();
            Stage stageOld = (Stage) node.getScene().getWindow();
            stageOld.close();
        }else if(!passwordFieldNew.getText().equals(passwordFieldNew2.getText())){
            System.out.println(passwordFieldNew + " " + passwordFieldNew2);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Kennwörter nicht gleich!");
            a.show();
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

    private boolean isUserValid(Properties properties, String username, String password) {
        Enumeration keys = properties.keys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            String value = properties.getProperty(key);
            if (key.equals(username)){
                if(value.equals(caeser.encrypt(password)) ){
                    return true;
                }
            }
        }
        return false;
    }
}
