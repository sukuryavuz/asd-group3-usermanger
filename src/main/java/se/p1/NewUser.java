package se.p1;

import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewUser {
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField username;
    @FXML
    PasswordField passwordField;

    private static Properties properties;

    @FXML
    public void handleCreateUserAction(ActionEvent actionEvent) {
       /* if (!checkInput()){
            showAlert();
        }*/

       // setError(name,false);

        System.out.println(name.getText() + " " + surname.getText() + " " + username.getText() + " " + passwordField.getText());
        insertNewUser();
        Node node = (Node) actionEvent.getSource();
        Stage stageOld = (Stage) node.getScene().getWindow();
        stageOld.close();
    }


    private boolean checkInput() {
        return false;
    }

    /*
    private void showAlert(String errorText) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("The input is not correct");
        errorAlert.showAndWait();
    }
*/


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

            byte[] contentInBytes = (username.getText() + "=" + passwordField.getText() + "\n").getBytes();
            fop.write(contentInBytes);

            fop.flush();
            fop.close();

            Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, "Created new user in password file");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    public void checkName(KeyEvent keyEvent) {
        if (name.getText().equals("milo")){
            setError(name,true);
        }else{
            setError(name,false);
        }

    }*/
}
