package controller;

import DAO.JDBC;
import comboBox.Language;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.LoginQuery.loginQuery;

public class Login implements Initializable {
    Stage stage;
    Parent scene;
    public TextField usernameTxt;
    public PasswordField passwordTxt;
    public ComboBox languageComboBx;
    public Label userTimeZoneLbl;

    public void onActionLogIn(ActionEvent actionEvent) {
        System.out.println("Login Button clicked!");

        try{
            String userName = usernameTxt.getText();
            String password = String.valueOf(passwordTxt.getText());
            User userResult = loginQuery(userName, password);

            if (userResult != null){
                stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
                JDBC.closeConnection();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username and/or Password. Please try again!");
                alert.showAndWait();
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void onActionResetUserPass(ActionEvent actionEvent) {
        System.out.println("Reset Button clicked!");
        usernameTxt.setText("");
        passwordTxt.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login: I am initialized!");

        ObservableList<Language> languages = Language.getLanguages();
        languageComboBx.setItems(languages);

        if(languages.size() == 0) {                                             //delete
            languages.add(new Language("English"));
            languages.add(new Language("Français"));
        }
    }
}
