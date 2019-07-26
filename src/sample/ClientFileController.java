package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ClientFileController implements Initializable {
    public TableView<Vehicle> tableofVehiclesClient;
    public TableColumn<Vehicle, String> columnName;
    public TableColumn<Vehicle, String> columnBrand;
    public TableColumn<Vehicle, String> columnModel;
    public TableColumn<Vehicle, String> columnDoors;
    public TableColumn<Vehicle, String> columnSeats;
    public TableColumn<Vehicle, String> columnEngine;
    public TableColumn<Vehicle, String> columnAvailable;
    public TableColumn<Vehicle, String> columnPrice;
    public ComboBox boxFilter;

    private RentACarDAODatabase rentacarDAOdb;
    private ObservableList<Vehicle> listOfVehicles;

    public ClientFileController() {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
        listOfVehicles = FXCollections.observableArrayList(rentacarDAOdb.vehicles());
    }

    public void initialize(URL location, ResourceBundle resources) {
        boxFilter.getSelectionModel().selectFirst(); //todo uraditi za promjenu... filtriranje
        tableofVehiclesClient.setItems(listOfVehicles);

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnDoors.setCellValueFactory(new PropertyValueFactory<>("nmbDoors"));
        columnSeats.setCellValueFactory(new PropertyValueFactory<>("nmbSeats"));
        columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
        columnAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void onComboChange(ActionEvent actionEvent) {
        //todo ovdje detetkovati promjenu u combobox, samo skontat kako uzeti selektovanu vrijednost
        if(boxFilter.getSelectionModel().getSelectedIndex() == 1){
            tableofVehiclesClient.setItems(listOfVehicles);

            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
            columnDoors.setCellValueFactory(new PropertyValueFactory<>("nmbDoors"));
            columnSeats.setCellValueFactory(new PropertyValueFactory<>("nmbSeats"));
            columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
            columnAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
        else if(boxFilter.getSelectionModel().getSelectedIndex() == 2){
            ObservableList<Vehicle> newList = listOfVehicles;
            Collections.sort(newList, new Comparator<Vehicle>() {
                @Override
                public int compare(Vehicle o1, Vehicle o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            tableofVehiclesClient.setItems(newList);
            //todo jedino sta mi je ostalo da bazu filtriram jer ne moze ovakooo...

            /*columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
            columnDoors.setCellValueFactory(new PropertyValueFactory<>("nmbDoors"));
            columnSeats.setCellValueFactory(new PropertyValueFactory<>("nmbSeats"));
            columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
            columnAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));*/
        }
    }
}
