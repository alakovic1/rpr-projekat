package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
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

    public boolean pickupDateValidate = false;
    public boolean returnDateValidate = false;

    public RentFileController() {
    }

    public RentFileController(Vehicle vehicle, Person person) {
        this.vehicle = vehicle;
        this.person = person;
    }

    private boolean isValidDate(String n) {
        if (n.length() == 0) return false;
        return true;
    }

    private boolean isFormValid() {
        if (!nowCheckBox.isSelected() && !shopCheckBox.isSelected()) return false;
        if (!pickupDateValidate || !returnDateValidate) return false;
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleInfoField.setText(vehicle.getName() + " (" + vehicle.getEngine() + ")");
        firstAndLastNameField.setText(person.getFirstName() + " " + person.getLastName());
        emailField.setText(person.getEmail());

        pickupDate.getStyleClass().add("no");
        returnDate.getStyleClass().add("no");
        firstAndLastNameField.getStyleClass().add("yes");
        emailField.getStyleClass().add("yes");

        pickupDateValidate = false;
        returnDateValidate = false;

        pickupDate.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String n) {
                if (isValidDate(n)) {
                    pickupDate.getStyleClass().removeAll("no");
                    pickupDate.getStyleClass().add("yes");
                    pickupDateValidate = true;
                } else {
                    pickupDate.getStyleClass().removeAll("yes");
                    pickupDate.getStyleClass().add("no");
                    pickupDateValidate = false;
                }
            }
        });

        returnDate.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String n) {
                if (isValidDate(n)) {
                    returnDate.getStyleClass().removeAll("no");
                    returnDate.getStyleClass().add("yes");
                    returnDateValidate = true;
                } else {
                    returnDate.getStyleClass().removeAll("yes");
                    returnDate.getStyleClass().add("no");
                    returnDateValidate = false;
                }
            }
        });
    }

    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) vehicleInfoField.getScene().getWindow();
        stage.close();
    }

    public void onFinish(ActionEvent actionEvent) {
        if (isFormValid()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Are you sure you want to rent this car?");
            alert.setContentText("OK for yes!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //todo uraditi potvrdu sa ispisom cijene
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You didn't fill all fields");
            alert.setContentText("Please fill all red fields or check the payment if you didn't...");
            alert.show();
        }
    }
}
