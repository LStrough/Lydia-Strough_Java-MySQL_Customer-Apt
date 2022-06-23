package controller;

import DAO.AppointmentDao;
import DAO.AppointmentDaoImpl;
import DAO.JDBC;
import DAO.LoginToDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.LoginToDB.loginQuery;

public class Login implements Initializable {
    Stage stage;
    Parent scene;

    public Label usernameLbl, passwordLbl, timeZoneLbl;
    public TextField usernameTxt;
    public PasswordField passwordTxt;
    public Label userTimeZoneLbl;
    public Button resetBttn, loginBttn;

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

                JDBC.openConnection();
                AppointmentDao appointmentDao = new AppointmentDaoImpl();
                LocalDateTime loginLDT = DAO.LoginToDB.getLoginLDT();
                appointmentDao.upcomingApptAlert(loginLDT);
            }
            else if(Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("bundle/language_fr", Locale.getDefault());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rb.getString("Invalid") + " " + rb.getString("Username") +
                        " " + rb.getString("and") + "/" + rb.getString("or") +
                        " " + rb.getString("Password") + ". " + rb.getString("Please") +
                        " " + rb.getString("try") + rb.getString("again") + "!");
                alert.showAndWait();
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

        userTimeZoneLbl.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("bundle/language_fr", Locale.getDefault());

                usernameLbl.setText(rb.getString("Username"));
                passwordLbl.setText(rb.getString("Password"));
                timeZoneLbl.setText(rb.getString("TimeZone"));
                resetBttn.setText(rb.getString("Reset"));
                loginBttn.setText(rb.getString("Login"));
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
