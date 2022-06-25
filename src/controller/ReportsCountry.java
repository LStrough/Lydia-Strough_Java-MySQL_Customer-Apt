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
import model.Country;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the "Country Report" controller.
 *
 * <p>This page displays a list of the databases' current list of customers that are associated with a selected country.
 * This page also tallies up the number of customers associated with the selected country. This page also gives the user
 * the ability to navigate the reports offered in the program. The user can also exit the program from this page or return to
 * the Main Appointments Menu page.</p>
 *
 * @author Lydia Strough
 */
public class ReportsCountry implements Initializable {
    private Stage stage;
    private Parent scene;

    /**
     * This is the country report table
     */
    public TableView<Customer> reportTableView;
    /**
     * This is the customer ID column
     */
    public TableColumn customerIdCol;
    /**
     * This is the customer name column
     */
    public TableColumn customerNameCol;
    /**
     * This is the customer address column
     */
    public TableColumn addressCol;
    /**
     * This is the customer postal code column
     */
    public TableColumn postalCodeCol;
    /**
     * This is the customer phone number column
     */
    public TableColumn phoneNumCol;
    /**
     * This is the customer country column
     */
    public TableColumn countryCol;
    /**
     * This is the country combo box
     */
    public ComboBox<Country> countryComboBx;
    /**
     * This is the "total customers" label
     */
    public Label totalCustomersLbl;

    /**
     * This is the "Return to Main Appointments" method.
     *
     * <p>The scene shifts from the Country Report screen, to the Main Appointments Menu. </p>
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
     * stays in the Country Report page.</p>
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
     * This is the "Contact Report" method.
     *
     * <p>The scene shifts from the Country Report screen, to the Contact Report page. </p>
     *
     * @param actionEvent the contact report button is clicked
     * @throws IOException
     */
    public void onActionContactReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Contact Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsContact.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Month &amp; Type Report" method.
     *
     * <p>The scene shifts from the Country Report screen, to the Month &amp; Type Report page. </p>
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
     * <p>The database connection is opened, then the country ID variable calls the "getCountryId" method and assigns the selected
     *  countries' country ID to itself. </p>
     *
     *  <p>The reportTableView then calls the "getCustomersByCountry" from the CustomerDao class, using the country ID variable as its
     *  parameter. The reportTableView then populates the results of the method.</p>
     *
     *  <p>The totalCustomersLbl text is then assigned to: "Total Customerss" + the number of customers associated with
     *  the selected country. </p>
     *
     * @param actionEvent a country is selected from the country combo box
     */
    public void onActionPopulateTable(ActionEvent actionEvent) {
        JDBC.openConnection();
        CustomerDao custDao = new CustomerDaoImpl();
        int countryId = countryComboBx.getSelectionModel().getSelectedItem().getCountryId();
        reportTableView.setItems(custDao.getCustomersByCountry(countryId));

        totalCustomersLbl.setText("Total Customers: " + custDao.getCustomersByCountry(countryId).size());
    }

    /**
     * This is the initialize method.
     *
     * <p>This is the first method to run when the Country Report page is populated.</p>
     *
     * <p>customerIdCol is assigned with the customerId value.</p>
     * <p>customerNameCol is assigned with the customerName value.</p>
     * <p>addressCol is assigned with the address value.</p>
     * <p>postalCodeCol is assigned with the postalCode value.</p>
     * <p>phoneNumCol is assigned with the phone value.</p>
     * <p>countryCol is assigned with the countryName value.</p>
     *
     * <p>The database connection is opened, and the country combo box calls the "getAllCountries" method from the CountryDao
     * class and assigns all countries to itself.</p>
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports (Country): I am Initialized!");

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoImpl();
        countryComboBx.setItems(countryDao.getAllCountries());
    }
}
