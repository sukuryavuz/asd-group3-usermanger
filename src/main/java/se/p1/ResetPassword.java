package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetPassword {

    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordFieldOld;
    @FXML
    private PasswordField passwordFieldNew;

    public void handleResetAction(ActionEvent actionEvent) {

        Properties props = readProperties();
        boolean isValid = isUserValid(props,username.getText(),passwordFieldOld.getText());

        if (isValid){
            Enumeration keys = props.keys();

            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                if (key.equals(username.getText())){
                    props.setProperty(key,passwordFieldNew.getText());
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
                if(value.equals(password) ){
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
