package controller;

import DAO.*;
import utilities.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the "Add Customer" controller.
 *
 *<p>This class allows the user to create a customer and add them to the database. The user gives the customer a name,
 * address, postal code, and phone number, and selects a country ID and division ID.</p>
 *
 * @author Lydia Strough
 */
public class AddCustomer implements Initializable {
    private Stage stage;
    private Parent scene;

    /**
     * This is the customer name text field.
     */
    public TextField nameTxt;
    /**
     * This is the customer address text field.
     */
    public TextField addressTxt;
    /**
     * This is the customer postal code text field.
     */
    public TextField postalCodeTxt;
    /**
     * This is the customer phone number text field.
     */
    public TextField phoneTxt;
    /**
     * This is the country combo box
     */
    public ComboBox<Country> countryComboBx;
    /**
     * This is the division combo box
     */
    public ComboBox<Division> divisionComboBx;
    /**
     * This is the customer name error message label.
     */
    public Label nameE;
    /**
     * This is the customer address error message label.
     */
    public Label addressE;
    /**
     * This is the customer postal code error message label.
     */
    public Label postalCodeE;
    /**
     * This is the customer phone number error message label.
     */
    public Label phoneE;
    /**
     * This is the customer country error message label.
     */
    public Label countryE;
    /**
     * This is the customer division error message label.
     */
    public Label divisionE;

    /**
     * desired country ID
     */
    private int countryId;
    /**
     * desired customer name
     */
    public String customerName;
    /**
     * desired customer address
     */
    public String address;
    /**
     * desired customer postal code
     */
    public String postalCode;
    /**
     * desired customer phone number
     */
    public String phone;

    /**
     * This is the "Save Customer" method.
     *
     * <p>The User modifies text fields and combo boxes with desired values. The save method then checks to see if each
     *text field is blank. If the text fields are blank, the "errorMessage" method is called to populate each correlated text fields'
     * error message label with an error prompt.</p>
     *
     * <p>If none of the text fields are blank, then the "addCustomer" method is called from the CustomerDao class. If
     * the customer is successfully added to the database, then the Main Customers Menu re-populates.</p>
     *
     * @param actionEvent save button is pushed
     */
    public void onActionSaveCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            boolean formatError = false;
            customerName = nameTxt.getText();
            address = addressTxt.getText();
            postalCode = postalCodeTxt.getText();
            phone = phoneTxt.getText();
            int divisionId = divisionComboBx.getSelectionModel().getSelectedItem().getDivisionId();

            if(customerName.isBlank()) {
                errorMessage(1);
                formatError = true;
            } else if(address.isBlank()) {
                errorMessage(2);
                formatError = true;
            } else if (postalCode.isBlank()){
                errorMessage(3);
                formatError = true;
            }else if (phone.isBlank()){
                errorMessage(4);
                formatError = true;
            }

            if(!formatError) {
                CustomerDao customerDao = new CustomerDaoImpl();
                customerDao.addCustomer(customerName, address, postalCode, phone, divisionId);

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This is the "Return to Main" method.
     *
     * <p>A confirmation dialog box populates: "All changes will be forgotten, do you wish to continue?".
     *  If the user hits the OK button, the scene shifts to the Main Customers Menu. If cancel is selected, the user
     *  stays in the Add Customer page.</p>
     *
     * @param actionEvent cancel button is pushed
     * */
    public void onActionReturnToCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel \"Add Customer\"");
        alert.setContentText("All changes will be forgotten, do you wish to continue?");
        alert.showAndWait();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
           stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
           scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
           stage.setScene(new Scene(scene));
           stage.show();
           JDBC.closeConnection();
        }
    }

    /**
     * This is the "Select Country" method.
     *
     * <p>This method calls the "getFilteredDivisions" method from the ListManager class if a new country item is selected from
     * inside the country combo box. Once selected, the country ID is taken from the selected country object and used as a parameter
     * in the "getFilteredDivisions" method. The Division combo box is then populated with the new country items' associated
     * divisions list. The first item in the division combo box is then selected. </p>
     *
     * @param actionEvent new item is selected in the country combo box
     */
    public void onActionSelectCountry(ActionEvent actionEvent) {
        countryId = countryComboBx.getValue().getCountryId();
        divisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
        divisionComboBx.getSelectionModel().selectFirst();
    }

    /**
     * This is the "error message" method.
     * <p>LAMBDA EXPRESSION: When this method is called, it populates an error message in an "error message label" next to
     * its associated text field.</p>
     * <p>WHY I CHOSE TO USE A LAMBDA IN THIS SCENARIO: This was a simple and effective way to generate unique error messages. The lambda
     * function also helped to cut down on the amount of code needed for the method as the case number associated with the "->"
     * symbol directly returned the associated error message.</p>
     * @param errorNum error message case number
     */
    public void errorMessage(int errorNum){
        switch (errorNum) {
            case 1 -> {
                nameE.setText("Customer \"Name\" cannot be empty!");
            }
            case 2 -> {
                addressE.setText("Customer \"Address\" cannot be empty!");
            }
            case 3 -> {
                postalCodeE.setText("Customer \"Postal Code\" cannot be empty!");
            }
            case 4 -> {
                phoneE.setText("Customer \"Phone Number\" cannot be empty!");
            }
        }
    }

    /**
     * This is the "AddCustomer" controller initialize method.
     *
     * <p>This is the first method called when the screen populates.</p>
     *
     * <p>The database connection is opened, and the "getAllCountries" method is called from the CountryDao class. The country combo box is
     * populated with the "allCountries" list, and the first item in the country combo box is selected. The country ID variable is then
     * assigned with the selected country objects associated country ID. The "getFilteredDivisions" method is then called
     * from the ListManager class. The divisions combo box is then populated with the country objects associated divisions list.
     * The first item in the division combo box is then selected.</p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Customer: I am initialized!");
        try {
            JDBC.openConnection();
            CountryDao countryDao = new CountryDaoImpl();

            countryComboBx.setItems(countryDao.getAllCountries());
            countryComboBx.getSelectionModel().selectFirst();
            countryId = countryComboBx.getValue().getCountryId();
            divisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
            divisionComboBx.getSelectionModel().selectFirst();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
