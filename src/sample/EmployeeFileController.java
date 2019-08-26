package sample;

import javafx.beans.property.SimpleStringProperty;
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
    public TableColumn<Reservation, String> personColumn;
    public TableColumn<Reservation, String> vehicleColumn;
    public TableColumn<Reservation, String> pickupDateColumn;
    public TableColumn<Reservation, String> returnDateColumn;
    public TableColumn<Reservation, String> cardNumberColumn;

    private RentACarDAODatabase rentacarDAOdb;
    private ObservableList<Vehicle> listOfVehicles;
    private ObservableList<Reservation> listOfReservations;

    public EmployeeFileController() {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
        listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
        listOfReservations = FXCollections.observableArrayList(rentacarDAOdb.reservations());
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
            AddEditVehicleFileController controller = new AddEditVehicleFileController(null);
            loader.setController(controller);
            root = loader.load();
            primaryStage.setTitle("Add / Edit vehicle");
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();

            primaryStage.setOnHiding(event -> {
                listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
                FilteredList<Vehicle> filteredList = new FilteredList(listOfVehicles, p -> true);
                tableVehicles.setItems(filteredList);
                searchBar.setOnKeyReleased(keyEvent ->
                {
                    switch (choiceBox.getValue()) {
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
                infoLabel.setText("Added new vehicle");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditVehicle(ActionEvent actionEvent) {
        Vehicle vehicle = tableVehicles.getSelectionModel().getSelectedItem();
        if (vehicle == null) {
            infoLabel.setText("Choose a vehicle you want to edit");
            return;
        }
        infoLabel.setText("Editing vehicle");
        Parent root = null;
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEditVehicleFile.fxml"));
            AddEditVehicleFileController controller = new AddEditVehicleFileController(vehicle);
            loader.setController(controller);
            root = loader.load();
            primaryStage.setTitle("Add / Edit vehicle");
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();

            primaryStage.setOnHiding(event -> {
                listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
                FilteredList<Vehicle> filteredList = new FilteredList(listOfVehicles, p -> true);
                tableVehicles.setItems(filteredList);
                searchBar.setOnKeyReleased(keyEvent ->
                {
                    switch (choiceBox.getValue()) {
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
                infoLabel.setText("Vehicle edited");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeleteVehicle(ActionEvent actionEvent) {
        infoLabel.setText("Deleting a vehicle");
        Vehicle vehicle = tableVehicles.getSelectionModel().getSelectedItem();
        if (vehicle == null) {
            infoLabel.setText("Choose a vehicle you want to delete");
            return;
        }
        if (vehicle.getAvailable().equals("yes")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Deleting a vehicle");
            alert.setContentText("Are you sure you want to delete this vehicle?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                rentacarDAOdb.removeVehicle(vehicle);
                listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
                FilteredList<Vehicle> filteredList = new FilteredList(listOfVehicles, p -> true);
                tableVehicles.setItems(filteredList);
                searchBar.setOnKeyReleased(keyEvent ->
                {
                    switch (choiceBox.getValue()) {
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
                infoLabel.setText("Vehicle deleted");
            } else if (result.get() == ButtonType.CANCEL) {
                infoLabel.setText("Vehicle not deleted");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Oops...");
            alert.setContentText("You can't delete a vehicle that is rented...");
            alert.show();
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
            switch (choiceBox.getValue()) {
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

        FilteredList<Reservation> filteredList2 = new FilteredList(listOfReservations, p -> true);
        tableReservations.setItems(filteredList2);

        personColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPerson().getFirstName() + " " + data.getValue().getPerson().getLastName()));
        vehicleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getName() + " (" + data.getValue().getVehicle().getEngine() + ")"));
        pickupDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));

        searchBar2.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox2.getValue()) {
                case "Pickup date":
                    filteredList2.setPredicate(p -> p.getPickupDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                    break;
                case "Return date":
                    filteredList2.setPredicate(p -> p.getReturnDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                    break;
            }
        });
    }

    public void onEditReservation(ActionEvent actionEvent) {
        Reservation reservation = tableReservations.getSelectionModel().getSelectedItem();
        if (reservation == null) {
            infoLabel2.setText("Choose a reservation you want to edit");
            return;
        }
        infoLabel.setText("Editing reservation");
        Parent root = null;
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editReservationFile.fxml"));
            EditReservationFileController controller = new EditReservationFileController(reservation);
            loader.setController(controller);
            root = loader.load();
            primaryStage.setTitle("Edit reservation");
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();

            primaryStage.setOnHiding(event -> {
                listOfReservations = FXCollections.observableArrayList(rentacarDAOdb.reservations());
                FilteredList<Reservation> filteredList2 = new FilteredList(listOfReservations, p -> true);
                tableReservations.setItems(filteredList2);
                searchBar2.setOnKeyReleased(keyEvent ->
                {
                    switch (choiceBox2.getValue()) {
                        case "Pickup date":
                            filteredList2.setPredicate(p -> p.getPickupDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                            break;
                        case "Return date":
                            filteredList2.setPredicate(p -> p.getReturnDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                            break;
                    }
                });
                infoLabel2.setText("Reservation edited");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeleteReservation(ActionEvent actionEvent) {
        infoLabel2.setText("Deleting a reservation");
        Reservation reservation = tableReservations.getSelectionModel().getSelectedItem();
        if (reservation == null) {
            infoLabel2.setText("Choose a reservation you want to delete");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Deleting a reservation");
        alert.setContentText("Are you sure you want to delete this reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            rentacarDAOdb.deleteReservation(reservation);
            reservation.getVehicle().setAvailable("yes");
            rentacarDAOdb.updateVehicle(reservation.getVehicle());
            listOfReservations = FXCollections.observableArrayList(rentacarDAOdb.reservations());
            FilteredList<Reservation> filteredList2 = new FilteredList(listOfReservations, p -> true);
            tableReservations.setItems(filteredList2);
            searchBar2.setOnKeyReleased(keyEvent ->
            {
                switch (choiceBox2.getValue()) {
                    case "Pickup date":
                        filteredList2.setPredicate(p -> p.getPickupDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                        break;
                    case "Return date":
                        filteredList2.setPredicate(p -> p.getReturnDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                        break;
                }
            });
            infoLabel2.setText("Reservation deleted");

            listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
            FilteredList<Vehicle> filteredList = new FilteredList(listOfVehicles, p -> true);
            tableVehicles.setItems(filteredList);
            searchBar.setOnKeyReleased(keyEvent ->
            {
                switch (choiceBox.getValue()) {
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
        } else if (result.get() == ButtonType.CANCEL) {
            infoLabel2.setText("Reservation not deleted");
        }
    }

    public void onAdminCheckRes(ActionEvent actionEvent) {
        infoLabel2.setText("Checking a reservation");
        Reservation reservation = tableReservations.getSelectionModel().getSelectedItem();
        if (reservation == null) {
            infoLabel2.setText("Choose a reservation you want to check");
            return;
        }
        Parent root = null;
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/checkReservationFile.fxml"));
            CheckReservationFileController controller = new CheckReservationFileController(reservation);
            loader.setController(controller);
            root = loader.load();
            primaryStage.setTitle("Reservation");
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAdminRent(ActionEvent actionEvent) {
        infoLabel.setText("Renting...");
        Vehicle vehicle = tableVehicles.getSelectionModel().getSelectedItem();
        if (vehicle == null) {
            infoLabel.setText("Choose a vehicle you want to rent");
            return;
        }
        if (vehicle.getAvailable().equals("yes")) {
            Parent root = null;
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminRentFile.fxml"));
                AdminRentFileController controller = new AdminRentFileController(vehicle);
                loader.setController(controller);
                root = loader.load();
                primaryStage.setTitle("Rent");
                primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();

                primaryStage.setOnHiding(event -> {
                    listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
                    FilteredList<Vehicle> filteredList = new FilteredList(listOfVehicles, p -> true);
                    tableVehicles.setItems(filteredList);
                    searchBar.setOnKeyReleased(keyEvent ->
                    {
                        switch (choiceBox.getValue()) {
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
                    infoLabel.setText("Renting finished...");

                    listOfReservations = FXCollections.observableArrayList(rentacarDAOdb.reservations());
                    FilteredList<Reservation> filteredList2 = new FilteredList(listOfReservations, p -> true);
                    tableReservations.setItems(filteredList2);
                    searchBar2.setOnKeyReleased(keyEvent ->
                    {
                        switch (choiceBox2.getValue()) {
                            case "Pickup date":
                                filteredList2.setPredicate(p -> p.getPickupDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                                break;
                            case "Return date":
                                filteredList2.setPredicate(p -> p.getReturnDate().toLowerCase().contains(searchBar2.getText().toLowerCase().trim()));
                                break;
                        }
                    });
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("We are sorry...");
            alert.setContentText("This car isn't available for rent now");
            alert.show();
        }
    }
}
