package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RentFileController implements Initializable {
    public Vehicle vehicle;
    public TextField vehicleInfoField;

    public RentFileController() {
    }

    public RentFileController(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleInfoField.setText(vehicle.getName() + " (" + vehicle.getEngine() + ")");
    }
}
