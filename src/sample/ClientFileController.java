package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ClientFileController implements Initializable {
    public Person person;
    public TableView<Vehicle> tableofVehiclesClient;
    public TableColumn<Vehicle, String> columnName;
    public TableColumn<Vehicle, String> columnBrand;
    public TableColumn<Vehicle, String> columnModel;
    public TableColumn<Vehicle, String> columnDoors;
    public TableColumn<Vehicle, String> columnSeats;
    public TableColumn<Vehicle, String> columnEngine;
    public TableColumn<Vehicle, String> columnAvailable;
    public TableColumn<Vehicle, String> columnPrice;
    public ChoiceBox<String> boxFilter;
    public Button infoBtn;
    public Button rentBtn;
    public Button logoutBtn;
    public TextField usernameClientField;
    public TextField searchBar;

    private RentACarDAODatabase rentacarDAOdb;
    private ObservableList<Vehicle> listOfVehicles;

    public ClientFileController(Person person) {
        this.person = person;
        rentacarDAOdb = RentACarDAODatabase.getInstance();
        listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
    }

    public void initialize(URL location, ResourceBundle resources) {
        usernameClientField.setText(person.getUsername());
        boxFilter.getSelectionModel().selectFirst();

        FilteredList<Vehicle> filteredList = new FilteredList(listOfVehicles, p -> true);
        tableofVehiclesClient.setItems(filteredList);

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
            switch (boxFilter.getValue())
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

    public void onInfoBtn(ActionEvent actionEvent) {
        Vehicle vehicle = tableofVehiclesClient.getSelectionModel().getSelectedItem();
        if(vehicle == null) return;
        Parent root = null;
        try {
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/moreInfoFile.fxml"));
            MoreInfoFileController controller = new MoreInfoFileController(vehicle, person);
            loader.setController(controller);
            root = loader.load();
            primaryStage.setTitle("More information");
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRentBtn(ActionEvent actionEvent) {
        Vehicle vehicle = tableofVehiclesClient.getSelectionModel().getSelectedItem();
        if(vehicle == null) return;
        if(vehicle.getAvailable().equals("yes")) {
            Parent root = null;
            try {
                Stage stage = (Stage) logoutBtn.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rentFile.fxml"));
                RentFileController controller = new RentFileController(vehicle, person);
                loader.setController(controller);
                root = loader.load();
                primaryStage.setTitle("Rent");
                primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("We are sorry...");
            alert.setContentText("This car isn't available for rent now");
            alert.show();
        }
    }

    public void onLogOut(ActionEvent actionEvent) {
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
}
