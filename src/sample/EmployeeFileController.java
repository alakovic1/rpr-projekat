package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class EmployeeFileController implements Initializable {
    public Button logoutBtn;
    public TabPane tabPane;
    public TableView<Vehicle> tableVehicles;
    public TableColumn<Vehicle, String> columnName;
    public TableColumn<Vehicle, String> columnBrand;
    public TableColumn<Vehicle, String> columnModel;
    public TableColumn<Vehicle, String> columnDoors;
    public TableColumn<Vehicle, String> columnSeats;
    public TableColumn<Vehicle, String> columnEngine;
    public TableColumn<Vehicle, String> columnAvailable;
    public TableColumn<Vehicle, String> columnPrice;
    public ChoiceBox<String> choiceBox;
    public TextField searchBar;
    public Label infoLabel;
    public Button addVehicleBtn;
    public Button editVehicleBtn;
    public Button deleteVehicleBtn;
    public TableView<Reservation> tableReservations;
    public ChoiceBox<String> choiceBox2;
    public TextField searchBar2;
    public Label infoLabel2;
    public Button editReservationBtn;
    public Button deleteReservationBtn;
    public Button adminRentBtn;
    public Button adminCheckRes;

    private RentACarDAODatabase rentacarDAOdb;
    private ObservableList<Vehicle> listOfVehicles;

    public EmployeeFileController() {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
        listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
    }

    public void onLogout(ActionEvent actionEvent) {
        Parent root = null;
        try {
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
            root = loader.load();
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAddVehicle(ActionEvent actionEvent) {
        infoLabel.setText("Adding new vehicle");
        Parent root = null;
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEditVehicleFile.fxml"));
            AddEditVehicleFileController controller = new AddEditVehicleFileController();
            loader.setController(controller);
            root = loader.load();
            primaryStage.setTitle("Add / Edit vehicle");
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();

            primaryStage.setOnHiding( event -> {
                listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
                tableVehicles.setItems(listOfVehicles);
                infoLabel.setText("Added new vehicle");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditVehicle(ActionEvent actionEvent) {
    }

    public void onDeleteVehicle(ActionEvent actionEvent) {
        infoLabel.setText("Deleting a vehicle");
        Vehicle vehicle = tableVehicles.getSelectionModel().getSelectedItem();
        if(vehicle == null) {
            infoLabel.setText("Choose a vehicle you want to delete");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda brisanja");
        alert.setHeaderText("Brisanje vozila ");
        alert.setContentText("Da li ste sigurni da želite obrisati vozilo?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            rentacarDAOdb.removeVehicle(vehicle);
            listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
            tableVehicles.setItems(listOfVehicles);
            infoLabel.setText("Vehicle deleted");
        }
        else if(result.get() == ButtonType.CANCEL){
            infoLabel.setText("Vehicle not deleted");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText("Program running");
        infoLabel2.setText("Program running");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox2.getSelectionModel().selectFirst();

        FilteredList<Vehicle> filteredList = new FilteredList(listOfVehicles, p -> true);
        tableVehicles.setItems(filteredList);

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnDoors.setCellValueFactory(new PropertyValueFactory<>("nmbDoors"));
        columnSeats.setCellValueFactory(new PropertyValueFactory<>("nmbSeats"));
        columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
        columnAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchBar.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue())
            {
                case "Name":
                    filteredList.setPredicate(p -> p.getName().toLowerCase().contains(searchBar.getText().toLowerCase().trim()));
                    break;
                case "Brand":
                    filteredList.setPredicate(p -> p.getBrand().toLowerCase().contains(searchBar.getText().toLowerCase().trim()));
                    break;
                case "Available":
                    filteredList.setPredicate(p -> p.getAvailable().toLowerCase().contains(searchBar.getText().toLowerCase().trim()));
                    break;
                case "Price":
                    filteredList.setPredicate(p -> String.valueOf(p.getPrice()).toLowerCase().contains(searchBar.getText().toLowerCase().trim()));
                    break;
            }
        });
    }

    public void onEditReservation(ActionEvent actionEvent) {
    }

    public void onDeleteReservation(ActionEvent actionEvent) {
    }

    public void onAdminCheckRes(ActionEvent actionEvent) {
    }

    public void onAdminRent(ActionEvent actionEvent) {
    }
}
