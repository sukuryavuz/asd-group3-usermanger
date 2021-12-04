package se.p1;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;

public class SignIn implements Initializable {



    @FXML private TextField userName;
    @FXML private Text actiontarget;


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

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

        confirmation.initModality(Modality.WINDOW_MODAL);

        confirmation.setTitle("Delete \"" + userName.getText() + "\"?");
        confirmation.setHeaderText("Delete \"" + userName.getText() + "\"?");
        confirmation.setContentText("Are you sure you want to delete this?");

        Optional<ButtonType> result = confirmation.showAndWait();

        if(result.get() == ButtonType.OK) {
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

    public void startTimer(Stage stage) {
        final int[] timer = {5};
        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                timer[0]--;
                                System.out.println("this is called every second on UI thread");
                                actiontarget.setText(String.valueOf(timer[0]));


                            }
                        }));
        fiveSecondsWonder.setCycleCount(5);
        fiveSecondsWonder.play();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
        stage.showAndWait();
    }


}
