package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCustomer implements Initializable {
    Stage stage;
    Parent scene;
    public TextField customerIdTxt;
    public TextField customerNameTxt;
    public TextField customerAddressTxt;
    public TextField customerPostalCodeTxt;
    public TextField customerPhoneNumTxt;
    public ComboBox customerCountryComboBx;
    public ComboBox customerDivisionComboBx;
    public Label customerNameE;
    public Label customerAddressE;
    public Label customerPostalCodeE;
    public Label customerPhoneNumE;
    public Label customerCountryE;
    public Label customerDivisionE;

    public void onActionUpdateCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");
    }

    public void onActionReturnToCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Customer: I am initialized!");
    }
}
