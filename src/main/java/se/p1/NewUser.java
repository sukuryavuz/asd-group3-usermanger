package se.p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewUser {
    @FXML TextField name;
    @FXML TextField surname;
    @FXML TextField username;
    @FXML PasswordField passwordField;
    Caeser caeaser = new Caeser();

    @FXML
    public void handleCreateUserAction(ActionEvent actionEvent) {
        Properties props = readProperties();

        boolean userExists = props.get(username.getText()) != null;

        if (!userExists){
            insertNewUser();
            Node node = (Node) actionEvent.getSource();
            Stage stageOld = (Stage) node.getScene().getWindow();
            stageOld.close();
        }else{
            Alert userExistsAlert = new Alert(Alert.AlertType.ERROR);
            userExistsAlert.setContentText("Username existiert bereits!");
            userExistsAlert.show();
        }

    }



    /**
     * Sets or clears the error highlight for <code>node</code>
     *
     * @param node
     * @param state true sets the error, false clears the error
     */
    private void setError(Node node, boolean state) {
        if (state) {
            node.setStyle("-fx-border-color: red");

        } else {
            node.setStyle("-fx-border-color: lightgreen");
        }
    }


    /**
     * Reads and returns all configs from password.properties file
     * @return all Properties from password.properties file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void insertNewUser(){
        File file = new File("password.properties");
        try {
            //true, appends to the file
            FileOutputStream fop = new FileOutputStream(file,true);
            if (!file.exists()) {
                file.createNewFile();
            }

            String passwordEncrypted = caeaser.encrypt(passwordField.getText());
            byte[] contentInBytes = (username.getText() + "=" + passwordEncrypted + "\n").getBytes();
            fop.write(contentInBytes);

            fop.flush();
            fop.close();

            Logger.getLogger(NewUser.class.getName()).log(Level.INFO, "Created new user in password file");

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

    public void checkName(KeyEvent keyEvent) {
        if (readProperties().get(username.getText()) != null){
            setError(username,true);
        }else{
            setError(username,false);
        }
    }
}
