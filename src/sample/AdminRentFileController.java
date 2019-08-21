package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminRentFileController implements Initializable {
    private Vehicle vehicle;
    private RentACarDAODatabase rentacarDAOdb;

    public TextField vehicleInfoField;
    public DatePicker pickupDate;
    public DatePicker returnDate;
    public TextField emailField;
    public Button finishBtn;
    public TextField firstNameField;
    public TextField lastNameField;

    public boolean pickupDateValidate = false;
    public boolean returnDateValidate = false;
    public boolean firstNameValidate = false;
    public boolean lastNameValidate = false;
    public boolean emailValidate = false;

    public AdminRentFileController(Vehicle vehicle) {
        this.vehicle = vehicle;

        rentacarDAOdb = RentACarDAODatabase.getInstance();
    }

    private boolean isValidDate(String n) {
        if (n.length() == 0) return false;
        return true;
    }

    private boolean isFormValid() {
        return pickupDateValidate && returnDateValidate && firstNameValidate && lastNameValidate && emailValidate;
    }

    private boolean areDatesOkay() {
        if(pickupDate.getEditor().getText().length() == 0 || returnDate.getEditor().getText().length() == 0) return false;
        if (pickupDate.getValue().isAfter(returnDate.getValue())) return false;
        return true;
    }

    private boolean validateText(String n) {
        if(n.length() == 0) return false;
        return true;
    }

    private boolean isValidEmail(String n) {
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '@') return true;
        return false;
    }

    public void onFinish(ActionEvent actionEvent) {
        if (areDatesOkay()) {
            long daysBetween = DAYS.between(pickupDate.getValue(), returnDate.getValue());
            if (daysBetween <= 60) {
                if (isFormValid()) {
                    double currentPrice = 0;

                    if (daysBetween < 3) {
                        currentPrice = daysBetween * vehicle.getPrice();
                    } else if (daysBetween >= 3 && daysBetween <= 15) {
                        currentPrice = daysBetween * (vehicle.getPrice() - 0.09);
                    } else if (daysBetween > 15 && daysBetween <= 60) {
                        currentPrice = daysBetween * (vehicle.getPrice() - 0.2);
                    }

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("Are you sure you want to rent this car?");
                    alert.setContentText("Full price for this car will be: " + currentPrice + " KM.\nClick OK for yes!");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        vehicle.setAvailable("no");
                        rentacarDAOdb.updateVehicle(vehicle);
                        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd. MM. yyyy");
                        String text = pickupDate.getValue().format(formatters);
                        LocalDate parsedDate = LocalDate.parse(text, formatters);
                        String text2 = returnDate.getValue().format(formatters);
                        LocalDate parsedDate2 = LocalDate.parse(text2, formatters);
                        Person person = new Person(firstNameField.getText(), lastNameField.getText(), "", "", emailField.getText(), "");
                        rentacarDAOdb.addReservation2(new Reservation(person, vehicle, parsedDate.format(formatters), parsedDate2.format(formatters)));
                        Stage stage = (Stage) vehicleInfoField.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("You didn't fill all fields");
                    alert.setContentText("Please fill all red fields or check the payment if you didn't...");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Try again");
                alert.setContentText("We are sorry, but you can only rent a car up to 60 days");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Try again");
            alert.setContentText("Dates aren't correct!\nPlease choose your dates again.");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleInfoField.setText(vehicle.getName() + " (" + vehicle.getEngine() + ")");

        pickupDate.getStyleClass().add("no");
        returnDate.getStyleClass().add("no");
        firstNameField.getStyleClass().add("no");
        lastNameField.getStyleClass().add("no");
        emailField.getStyleClass().add("no");

        pickupDateValidate = false;
        returnDateValidate = false;
        firstNameValidate = false;
        lastNameValidate = false;
        emailValidate = false;

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

        firstNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
                    firstNameField.getStyleClass().removeAll("no");
                    firstNameField.getStyleClass().add("yes");
                    firstNameValidate = true;
                } else {
                    firstNameField.getStyleClass().removeAll("yes");
                    firstNameField.getStyleClass().add("no");
                    firstNameValidate = false;
                }
            }
        });

        lastNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
                    lastNameField.getStyleClass().removeAll("no");
                    lastNameField.getStyleClass().add("yes");
                    lastNameValidate = true;
                } else {
                    lastNameField.getStyleClass().removeAll("yes");
                    lastNameField.getStyleClass().add("no");
                    lastNameValidate = false;
                }
            }
        });

        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (isValidEmail(n)) {
                    emailField.getStyleClass().removeAll("no");
                    emailField.getStyleClass().add("yes");
                    emailValidate = true;
                } else {
                    emailField.getStyleClass().removeAll("yes");
                    emailField.getStyleClass().add("no");
                    emailValidate = false;
                }
            }
        });
    }
}
