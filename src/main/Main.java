package main;

import DAO.*;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the Main class.
 *
 * @author Lydia Strough
 * */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Customer Appointment Management System");
        stage.setScene(new Scene(root, 1200, 542));
        stage.show();
    }
    /**
     * This is the main method.
     *<p>This is the first method that gets called when the Java program is run.
     * JDBC connection is opened before launch, then closed after the program is closed.</p>
     * */
    public static void main(String[] args) {
        //Locale.setDefault(new Locale("fr"));
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
