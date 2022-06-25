package controller;

import DAO.AppointmentDao;
import DAO.AppointmentDaoImpl;
import DAO.JDBC;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.time.ZoneId;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the "Main Appointments" Menu controller.
 *
 * <p>This is the first page to populate after the user logs in to the program/database. This page displays a list of the databases'
 * current list of appointments, and gives the user the ability to search appointments by date, as well as the ability to add, modify (update),
 * and delete customer appointments from the database. The user can also filter upcoming appointments by week and month. This page also
 * offers the user the ability to navigate the rest of the program. The user can also exit the program from this page.</p>
 *
 * @author Lydia Strough
 */
public class MainAppointments implements Initializable {
    Stage stage;
    Parent scene;
    /**
     * This is the "view by" toggle group
     */
    public ToggleGroup viewByTgl;
    /**
     * This is the "search appointment by date" date picker
     */
    public DatePicker datePicker;
    /**
     * This is the appointment table
     */
    public TableView<Appointment> apptTableView;
    /**
     * This is the appointment ID column
     */
    public TableColumn apptIdCol;
    /**
     * This is the appointment title column
     */
    public TableColumn titleCol;
    /**
     * This is the appointment description column
     */
    public TableColumn descriptionCol;
    /**
     * This is the appointment location column
     */
    public TableColumn locationCol;
    /**
     * This is the appointment associated contact column
     */
    public TableColumn contactCol;
    /**
     * This is the appointment type column
     */
    public TableColumn typeCol;
    /**
     * This is the appointment start date column
     */
    public TableColumn startDateCol;
    /**
     * This is the appointment end date column
     */
    public TableColumn endDateCol;
    /**
     * This is the appointment start time column
     */
    public TableColumn startTimeCol;
    /**
     * This is the appointment end time column
     */
    public TableColumn endTimeCol;
    /**
     * This is the appointment customer ID column
     */
    public TableColumn customerIdCol;
    /**
     * This is the appointments' associated user ID column
     */
    public TableColumn userIdCol;
    /**
     * This is the (systems default) time zone label
     */
    public Label userTimeZoneLbl;

    /**
     * This is the "view appointments by the upcoming week" method.
     *
     * <p>This method opens the database connections and then calls the "upcomingApptsWeek" method from the AppointmentDao class,
     * and then populates the filtered appointments list into the apptTableView.</p>
     *
     * @param actionEvent the "view by week" radio button is clicked
     */
    public void onActionViewByWeek(ActionEvent actionEvent) {
        System.out.println("View by Week Radio Button Clicked!");

        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        apptTableView.setItems(apptDao.upcomingApptsWeek(LocalDate.from(DAO.LoginToDB.getLoginLDT())));
    }

    /**
     * This is the "view appointments for the rest of the month" method.
     *
     * <p>This method opens the database connections and then calls the "upcomingApptsMonth" method from the AppointmentDao class,
     * and then populates the filtered appointments list into the apptTableView.</p>
     *
     * @param actionEvent the "view by month" radio button is clicked
     */
    public void onActionViewByMonth(ActionEvent actionEvent) {
        System.out.println("View by Month Radio Button Clicked!");

        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        apptTableView.setItems(apptDao.upcomingApptsMonth(LocalDate.from(DAO.LoginToDB.getLoginLDT())));
    }

    /**
     * This is the "view all appointments" method.
     *
     * <p> This method opens the database connections and then calls the "getAllAppointments" method from the AppointmentDao class,
     * and then populates the all appointments list into the apptTableView.</p>
     *
     * @param actionEvent the "view all" radio button is clicked
     */
    public void onActionViewAll(ActionEvent actionEvent) {
        System.out.println("View All Radio Button Clicked!");

        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        apptTableView.setItems(appointmentDao.getAllAppointments());
    }

    /**
     * This is the "search appointments by start date" method.
     *
     * <p>This method populates the apptTableView with appointment results based on their start date.</p>
     *
     * <p> The database connections is opened and the "getAllAppointments" method is called from the AppointmentDao class.
     * The apptTableView is then set to the all appointments list. A local start date is selected, using the date picker, and selDate
     * is assigned the value.</p>
     *
     * <p> A new observable list called appts is created and assigned the result of the "lookUpAppointment" method.
     * The selDate value is used as a parameter in this method. The apptTableView populates the appointment results of this method.</p>
     *
     * <p>If the method returns null, an alert dialogue box with the following error message populates: "No item was found."</p>
     *
     * @param actionEvent a date is selected, using the "search appointments by date" date picker
     */
    public void onActionSearchApptByDate(ActionEvent actionEvent) {
        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        apptTableView.setItems(appointmentDao.getAllAppointments());
        LocalDate selDate = datePicker.getValue();

        try{
            ObservableList<Appointment> appts = appointmentDao.lookUpAppointment(selDate);
            apptTableView.setItems(appts);
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if(!((AppointmentDaoImpl) appointmentDao).apptFound) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No item was found.");
            alert.showAndWait();
        }
    }

    /**
     * This is the "Add Appointment" method.
     *
     * <p>The scene shifts from the Main Appointments Menu screen, to the Add Appointment form. </p>
     *
     * @param actionEvent the add appointment button is clicked
     * @throws IOException
     */
    public void onActionAddAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Appointment Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * @param actionEvent the update appointment button is clicked
     * @throws IOException
     */
    public void onActionUpdateAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Appointment Button Clicked!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppointment.fxml"));
            Parent scene = loader.load();

            UpdateAppointment updateApptController = loader.getController();

            Appointment selectedAppt = apptTableView.getSelectionModel().getSelectedItem();

            updateApptController.updateAppointment(selectedAppt);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Appointment Selected");
            alert.setContentText("Please select an appointment to update!");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param actionEvent the delete appointment button is clicked
     */
    public void onActionDeleteAppt(ActionEvent actionEvent) {
        System.out.println("Delete Appointment Button Clicked!");

        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        Appointment selAppt = apptTableView.getSelectionModel().getSelectedItem();
        int appointmentId = selAppt.getAppointmentId();
        int customerId = selAppt.getCustomerId();
        String type = selAppt.getType();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("The selected \"Appointment\" will be deleted. Do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.out.println(apptDao.deleteAppointment(appointmentId, customerId, type));

            JDBC.openConnection();
            apptTableView.setItems(apptDao.getAllAppointments());
        }
    }

    /**
     * This is the "Customer" method.
     *
     * <p>The scene shifts from the Main Appointments Menu screen, to the Main Customers Menu. </p>
     *
     * @param actionEvent the customers button is clicked
     * @throws IOException
     */
    public void onActionCustomers(ActionEvent actionEvent) throws IOException {
        System.out.println("Customers Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Reports" method.
     *
     * <p>The scene shifts from the Main Appointments Menu screen, to the Contact Report screen.</p>
     *
     * @param actionEvent the reports button is clicked
     * @throws IOException
     */
    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("Reports Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsContact.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Logout" method.
     *
     * <p>A confirmation dialog box populates: "Do you wish to Exit the program?".
     * If the user hits the OK button, the program ends. If cancel is selected, the user
     * stays in the Main Appointments Menu.</p>
     *
     * @param actionEvent the logout button is clicked
     */
    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Logout Button Clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Do you wish to Exit the program?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.exit(0);
        }
    }

    /**
     * This is the initialize method.
     *
     * <p>This is the first method to run when the Main Appointments page is populated.</p>
     *
     * <p>The userTimeZoneLbl label text is set to "Your Time Zone: " + the string value of ZoneId.systemDefault().</p>
     *
     * <p>apptIdCol is assigned with the appointment ID value.</p>
     * <p>titleCol is assigned with the title value.</p>
     * <p>descriptionCol is assigned with the description value.</p>
     * <p>locationCol is assigned with the description value.</p>
     * <p>contactCol is assigned with the contact ID value.</p>
     * <p>typeCol is assigned with the type value.</p>
     * <p>startDateCol is assigned with the startDate value.</p>
     * <p>endDateCol is assigned with the endDate value.</p>
     * <p>startTimeCol is assigned with the startTime value.</p>
     * <p>endTimeCol is assigned with the endTimeCol value.</p>
     * <p>customerIdCol is assigned with the customerId value.</p>
     * <p>userIdCol is assigned with the userId value.</p>
     *
     *  <p>The database connection is opened, and the apptTableView calls the "getAllAppointments" method from the AppointmentDao
     *  class and assigns all appointments to its rows.</p>
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Appointment Schedule (Main Menu): I am initialized!");

        userTimeZoneLbl.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));

        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        apptTableView.setItems(appointmentDao.getAllAppointments());
    }
}
