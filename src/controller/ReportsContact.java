package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the "Contact Report" controller.
 *
 * <p>This page displays a list of the databases' current list of appointments that are associated with a selected customer contact.
 * This page also tallies up the number of appointments associated with the selected customer contact. This page also gives the user
 * the ability to navigate the reports offered in the program. The user can also exit the program from this page or return to
 * the Main Appointments Menu page.</p>
 *
 * @author Lydia Strough
 */
public class ReportsContact implements Initializable {
    private Stage stage;
    private Parent scene;
    /**
     * This is the contact report table
     */
    public TableView<Appointment> reportTableView;
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
     * This is the contact combo box
     */
    public ComboBox<Contact> contactComboBx;
    /**
     * This is the "total appointments" label
     */
    public Label totalApptsLbl;

    /**
     * This is the "Return to Main Appointments" method.
     *
     * <p>The scene shifts from the Contact Report screen, to the Main Appointments Menu. </p>
     *
     * @param actionEvent the cancel button is clicked
     * @throws IOException
     */
    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Logout" method.
     *
     * <p>A confirmation dialog box populates: "Do you wish to Exit the program?".
     * If the user hits the OK button, the program ends. If cancel is selected, the user
     * stays in the Contact Report page.</p>
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
     * This is the "Country Report" method.
     *
     * <p>The scene shifts from the Contact Report screen, to the Country Report page. </p>
     *
     * @param actionEvent the country report button is clicked
     * @throws IOException
     */
    public void onActionCountryReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Country Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsCountry.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Month &amp; Type Report" method.
     *
     * <p>The scene shifts from the Contact Report screen, to the Month &amp; Type Report page. </p>
     *
     * @param actionEvent the Month &amp; Type Report button is clicked
     * @throws IOException
     */
    public void onActionMonthTypeReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Month & Type Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsMonthType.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "populate report table" method.
     *
     * <p>The database connection is opened, then the contact ID variable calls the "getContactId" method and assigns the selected
     *  customer contacts' contact ID to itself. </p>
     *
     *  <p>The reportTableView then calls the "getApptByContact" from the AppontmentDao class, using the contact ID variable as its
     *  parameter. The reportTableView then populates the results of the method.</p>
     *
     *  <p>The totalApptsLbl text is then assigned to: "Total Appointments" + the number of appointments associated with
     *  the selected contact. </p>
     *
     * @param actionEvent a contact is selected from the contact combo box
     */
    public void onActionPopulateTable(ActionEvent actionEvent) {
        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        int contactId = contactComboBx.getSelectionModel().getSelectedItem().getContactId();
        reportTableView.setItems(apptDao.getApptByContact(contactId));

        totalApptsLbl.setText("Total Appointments: " + apptDao.getApptByContact(contactId).size());
    }

    /**
     * This is the initialize method.
     *
     * <p>This is the first method to run when the Contact Report page is populated.</p>
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
     *  <p>The database connection is opened, and the contact combo box calls the "getAllContacts" method from the ContactDao
     *  class and assigns all contacts to itself.</p>
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports (Contact): I am Initialized!");

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
        ContactDao contactDao = new ContactDaoImpl();
        contactComboBx.setItems(contactDao.getAllContacts());
    }
}
