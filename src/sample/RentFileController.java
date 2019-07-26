package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) vehicleInfoField.getScene().getWindow();
        stage.close();
    }
}
