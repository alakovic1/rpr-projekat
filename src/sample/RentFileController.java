package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RentFileController implements Initializable {
    public Vehicle vehicle;
    public Person person;
    public TextField vehicleInfoField;
    public DatePicker pickupDate;
    public DatePicker returnDate;
    public CheckBox nowCheckBox;
    public CheckBox shopCheckBox;
    public TextField firstAndLastNameField;
    public TextField emailField;
    public Button finishBtn;
    public Button closeBtn;

    public RentFileController() {
    }

    public RentFileController(Vehicle vehicle, Person person) {
        this.vehicle = vehicle;
        this.person = person;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleInfoField.setText(vehicle.getName() + " (" + vehicle.getEngine() + ")");
        firstAndLastNameField.setText(person.getFirstName() + " " + person.getLastName());
        emailField.setText(person.getEmail());
    }

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) vehicleInfoField.getScene().getWindow();
        stage.close();
    }

    public void onFinish(ActionEvent actionEvent) {
    }
}
