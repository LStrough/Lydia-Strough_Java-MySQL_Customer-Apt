package controller;

import DAO.AppointmentDao;
import DAO.AppointmentDaoImpl;
import DAO.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.LoginToDB.loginQuery;

/**
 * This is the "Login" controller.
 *
 *<p>This class allows the user to access the program by prompting the user to enter a valid database username and password. </p>
 *
 * @author Lydia Strough
 */
public class Login implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * This is the username label
     */
    public Label usernameLbl;
    /**
     * This is the password label
     */
    public Label passwordLbl;
    /**
     * This is the time Zone label
     */
    public Label timeZoneLbl;
    /**
     * This is the username text field
     */
    public TextField usernameTxt;
    /**
     * This is the password text field
     */
    public PasswordField passwordTxt;
    /**
     * This is the (users' default) time zone label
     */
    public Label userTimeZoneLbl;
    /**
     * This is the reset button
     */
    public Button resetBttn;
    /**
     * This is the login button
     */
    public Button loginBttn;

    /**
     * This is the "Login" method.
     *
     * <p></p>
     *
     * @param actionEvent the user pushes the login button
     */
    public void onActionLogIn(ActionEvent actionEvent) {
        System.out.println("Login Button clicked!");

        try{
            String userName = usernameTxt.getText();
            String password = String.valueOf(passwordTxt.getText());
            User userResult = loginQuery(userName, password);
            LocalDateTime now = LocalDateTime.now();
            String fileName = "login_activity.txt";

            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(fileWriter);

            if (userResult != null){
                stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

                JDBC.openConnection();
                AppointmentDao appointmentDao = new AppointmentDaoImpl();
                LocalDateTime loginLDT = DAO.LoginToDB.getLoginLDT();
                appointmentDao.upcomingApptAlert(loginLDT);
                outputFile.println(userName + " Login was successful at " + now + " (" + ZoneId.systemDefault() + ")");
            }
            else if(Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("bundle/language_fr", Locale.getDefault());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rb.getString("Invalid") + " " + rb.getString("Username") +
                        " " + rb.getString("and") + "/" + rb.getString("or") +
                        " " + rb.getString("Password") + ". " + rb.getString("Please") +
                        " " + rb.getString("try") + rb.getString("again") + "!");
                alert.showAndWait();

                outputFile.println(userName + " Login was unsuccessful at " + now + " (" + ZoneId.systemDefault() + ")");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username and/or Password. Please try again!");
                alert.showAndWait();

                outputFile.println(userName + " Login was unsuccessful at " + now + " (" + ZoneId.systemDefault() + ")");
            }
            outputFile.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This is the "reset username and password text field" method.
     *
     * <p></p>
     *
     * @param actionEvent the user pushes the reset button
     */
    public void onActionResetUserPass(ActionEvent actionEvent) {
        System.out.println("Reset Button clicked!");
        usernameTxt.setText("");
        passwordTxt.setText("");
    }

    /**
     * This is the "Login" controller initialize method.
     *
     * <p>This is the first method called when the screen populates.</p>
     *
     * <p></p>
     */
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
