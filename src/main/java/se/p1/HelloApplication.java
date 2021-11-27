package se.p1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloApplication extends Application {

    public static Properties properties;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        try {
            properties = readProperties();
        } catch (IOException e) {
            Logger.getLogger(HelloApplication.class.getName()).log(Level.SEVERE, "Properties file could not be read", e);
        }



        launch();
    }

    /**
     * Reads and returns all configs from password.properties file
     * @return all Properties from password.properties file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static Properties readProperties() throws FileNotFoundException, IOException
    {
        File file = new File("password.properties");

        Properties properties;

        try (FileInputStream fileInput = new FileInputStream(file)) {
            properties = new Properties();
            properties.load(fileInput);
        }
        return properties;
    }
}