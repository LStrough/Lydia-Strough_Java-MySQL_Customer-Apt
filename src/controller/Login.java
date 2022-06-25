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
    private Stage stage;
    private Parent scene;

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
     * <p>The username variable is assigned to the string entered (or not) into the username text field. The password variable is
     * assigned to the string entered (or not) into the password text field. The "loginQuery" method is then called, using both of these variables
     * as its parameters. The "loginQuery" method validates the users username and password and then returns a user object
     * if one is found.</p>
     *
     * <p>Simultaneously, the "now" variable is assigned with the operating systems current local date and time. A file is also
     * created (if it does not exist), called "login_activity.txt". A new fileWriter and printWriter is created and appends a message to the
     * "login_activity.txt" file when credentials are met. </p>
     *
     * <p>If the user credentials were validated by the "loginQuery" method, then the Main Appointments Menu populates on the screen.
     * The database connection is opened, and the "getLoginLDT" method is called, gathering the current local date and time of the
     * system upon the users' successful login. The variable, loginLDT, is assigned this value. Then the "upcomingApptAlert" method is called
     * from the AppointmentDao class. The loginLDT variable is passed as a parameter, and the method checks to see if
     * there are any upcoming appointments. If there are any appointments occurring within 15 minutes of the current local date
     *  and time, an alert message will populate with a list of these appointments as the user enters the Main Appointments menu.
     *  However, if there are no upcoming appointments, then a different alert message will populate saying that "There are no appointments
     *  scheduled to begin within the next 15 minutes!"</p>
     *
     * <p>As the user attempts to login to the database/program, the "login_activity.txt" records the users login attempt results.
     *  If the user successfully logs in to the program/database, the text: "userName + " Login was successful at " + now + " (" + ZoneId.systemDefault() + ")"
     *  is recorded on a new line in the "login_activity.txt" file (replacing the userName, now, and systemDefault zone ID with the unique values
     *  depending on the username string entered, as well as the zone ID and local date and time at login.</p>
     *
     *  <p>If the "loginQuery" method returns no user result, then an alert combo box will populate with the following message:
     *  "Invalid Username and/or Password. Please try again!". The following text will also be recorded in the "login_activity.txt" file
     *  upon the users failed login (replacing variables with the user/operating systems specific credentials):
     *  userName + " Login was unsuccessful at " + now + " (" + ZoneId.systemDefault() + ")".</p>
     *
     *  <p>Also, if the operating systems' default language is set to french, all alert messages and labels will be displayed in french
     *  (the "login_activity.txt" messages, however, will continue to write in english, regardless of the selected language.)</p>
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
     * <p>This method "clears" the username and password text fields. </p>
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
     * <p>The userTimeZoneLbl text is set to display the operating systems default zone ID.</p>
     *
     * <p>If the operating systems default language is set to french, then the french language resource bundle is called from the bundle package.
     * Upon the screens initialization, all text displayed on the screen is translated to french. However, if the operating systems language
     * is set to anything but french, then all text displayed will be displayed in english. </p>
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
