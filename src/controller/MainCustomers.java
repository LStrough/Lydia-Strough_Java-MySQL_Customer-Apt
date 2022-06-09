package controller;

import DAO.CustomerDaoImpl;
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

public class MainCustomers implements Initializable {
    Stage stage;
    Parent scene;
    public TextField searchCustomer;
    public TableView customerTableView;
    public TableColumn customerIdCol;
    public TableColumn customerNameCol;
    public TableColumn addressCol;
    public TableColumn postalCodeCol;
    public TableColumn phoneNumCol;
    public TableColumn stateProvinceCol;
    public Label userTimeZoneLbl;

    public void onActionSearchCustomer(ActionEvent actionEvent) {
    }

    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Customer Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Customer Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) {
        System.out.println("Delete Customer Button Clicked!");
    }

    public void onActionAppointments(ActionEvent actionEvent) throws IOException {
        System.out.println("Appointments Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("Reports Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportCustomerAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Logout Button Clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you wish to Exit the program?");
        alert.showAndWait();

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Customer Records: I am initialized!");

        userTimeZoneLbl.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));

        /*
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stateProvinceCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));     //how to convert id to name

        try{
            Customers.addAll(CustomerDaoImpl.getAllCustomers());      //lambda
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        customerTableView.setItems(Customers);
        //Using lambda for efficient selection of tableview
         */
    }
}
