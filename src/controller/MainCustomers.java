package controller;

import DAO.CustomerDao;
import DAO.CustomerDaoImpl;
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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the "Main Customers" Menu controller.
 *
 * <p>This page displays a current list of the databases' customers, and gives the user the ability to search customers
 * by customer ID and customer name. The user also has the ability to add, modify (update), and delete customers
 * as well as their associated appointments, from the database. This page also offers the user the ability to navigate the
 * rest of the program. The user can also exit the program from this page.</p>
 *
 * @author Lydia Strough
 */
public class MainCustomers implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * This is the "search customer by customer ID or customer name" text field
     */
    public TextField searchCustomer;
    /**
     * This is the customer table
     */
    public TableView<Customer> customerTableView;
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
     * This is the customer division column
     */
    public TableColumn stateProvinceCol;
    /**
     * This is the (system default) time zone label
     */
    public Label userTimeZoneLbl;

    /**
     * This is the "Search Customer" method.
     *
     * <p>This method populates the customerTableView with customer results based on customer ID or customer name.</p>
     *
     * <p> The database connection is opened and the "getAllCustomers" method is called from the CustomerDao class. The customerTableView
     * is set to the all Customers list. Text is gathered from search bar and converted into a string. Customer ID is assigned the text.
     * The "lookupCustomer" method is called, utilizing customer ID as the parameter. The customerTableView highlights a
     * result or returns null.</p>
     *
     * <p>If null is returned, the text is assigned to customerName and the "lookupCustomer" method is called again using customerName.
     * If a match (or a similar results) is found, the table filters based on the text. If no match is found, an error message:
     * "No item was found." populates in an alert dialogue box.</p>
     *
     * @param actionEvent text is entered into search customer bar, then enter is pressed
     * */
    public void onActionSearchCustomer(ActionEvent actionEvent) {
        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoImpl();
        customerTableView.setItems(customerDao.getAllCustomers());

        try{
            int customerId = Integer.parseInt(searchCustomer.getText());
            Customer customer = customerDao.lookUpCustomer(customerId);
            customerTableView.getSelectionModel().select(customer);
            customerTableView.scrollTo(customer);
            customerTableView.requestFocus();
        }catch(NumberFormatException e) {
            String customerName = searchCustomer.getText();
            ObservableList<Customer> customers = customerDao.lookUpCustomer(customerName);
            customerTableView.setItems(customers);
        }
        if(!((CustomerDaoImpl) customerDao).customerFound){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No item was found.");
            alert.showAndWait();
        }
    }

    /**
     * This is the "Add Customer" method.
     *
     * <p>The scene shifts from the Main Customers Menu screen, to the Add Customer form. </p>
     *
     * @param actionEvent the add customer button is clicked
     * @throws IOException
     */
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Customer Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Update Customer" method.
     *
     * <p>The scene is shifted from the Main Customers Menu to the Update Customer form.</p>
     *
     * <p>The user is expected to select a customer from the customerTableView. If the user fails to do so,
     * a runtime exception is created, and an alert dialogue box populates with the following error message:
     * "Please select a customer to update!".</p>
     *
     * @param actionEvent the update customer button is clicked
     * @throws IOException
     */
    public void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Customer Button Clicked!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateCustomer.fxml"));
            Parent scene = loader.load();

            UpdateCustomer updateCustomerController = loader.getController();

            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

            updateCustomerController.updateCustomer(selectedCustomer);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Customer Selected");
            alert.setContentText("Please select a customer to update!");
            alert.showAndWait();
        }
    }

    /**
     * This is the "Delete Customer" method.
     *
     * <p>The user selects a row in the customer table view and it is assigned to selectedCustomer. </p>
     *
     * <p>The database connection is opened, the "getCustomerId" method is then called and the value of the selected
     * customer ID is assigned to the customerId variable. The "getCustomerName" method is then called and the customer name
     * is assigned to the customerName variable. </p>
     *
     * <p>An alert dialog box populates and the following message displays: "The selected \"Customer\" and their corresponding "Appointment(s)"
     * will be deleted. Do you wish to continue?" If the user selects the OK button, the "deleteCustomer" method is called
     * from the CustomerDao class and the selected customer ID and customer name are utilized as parameters.</p>
     *
     * <p>The result of the "deleteCustomer" method will then populate in an alert dialogue box and the customerTableView will
     * then re-populate with an up to date list of customers. </p>
     *
     * @param actionEvent the delete customer button clicked
     * */
    public void onActionDeleteCustomer(ActionEvent actionEvent) {
        System.out.println("Delete Customer Button Clicked!");

        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        int customerId = selectedCustomer.getCustomerId();
        String customerName = selectedCustomer.getCustomerName();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("The selected \"Customer\" and their corresponding \"Appointment(s)\" will be deleted. Do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.out.println(customerDao.deleteCustomer(customerId, customerName));

            JDBC.openConnection();
            customerTableView.setItems(customerDao.getAllCustomers());
        }
    }

    /**
     * This is the "Appointments" method.
     *
     * <p>The scene shifts from the Main Customers Menu screen, to the Main Appointments Menu. </p>
     *
     * @param actionEvent the appointments button is clicked
     * @throws IOException
     */
    public void onActionAppointments(ActionEvent actionEvent) throws IOException {
        System.out.println("Appointments Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Reports" method.
     *
     * <p>The scene shifts from the Main Customers Menu screen, to the Contact Report screen.</p>
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
     * stays in the Main Customers Menu.</p>
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
     * <p>This is the first method to run when the Main Customers page is populated.</p>
     *
     * <p>The userTimeZoneLbl label text is set to "Your Time Zone: " + the string value of ZoneId.systemDefault().</p>
     *
     * <p>customerIdCol is assigned with the customerId value.</p>
     * <p>customerNameCol is assigned with the customerName value.</p>
     * <p>addressCol is assigned with the address value.</p>
     * <p>postalCodeCol is assigned with the postalCode value.</p>
     * <p>phoneNumCol is assigned with the phone value.</p>
     * <p>stateProvinceCol is assigned with the divisionName value.</p>
     *
     *  <p>The database connection is opened, and the customerTableView calls the "getAllCustomers" method from the CustomerDao
     *  class and assigns all customers to its rows.</p>
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Customer Records: I am initialized!");

        userTimeZoneLbl.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stateProvinceCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));

        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoImpl();
        customerTableView.setItems(customerDao.getAllCustomers());
    }
}
