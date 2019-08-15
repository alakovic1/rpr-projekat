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
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

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

    private boolean areDatesOkay(){
        if(pickupDate.getValue().isAfter(returnDate.getValue())) return false;
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
        if(areDatesOkay()) {
            long daysBetween = DAYS.between(pickupDate.getValue(), returnDate.getValue());
            if(daysBetween <= 60) {
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
                        if (nowCheckBox.isSelected()) {
                            Parent root = null;
                            try {
                                Stage primaryStage = new Stage();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cardInfoFile.fxml"));
                                CardInfoFileController controller = new CardInfoFileController(vehicle, person);
                                loader.setController(controller);
                                root = loader.load();
                                primaryStage.setTitle("Card Info");
                                primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
                                primaryStage.initModality(Modality.APPLICATION_MODAL);
                                primaryStage.setResizable(false);
                                primaryStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (shopCheckBox.isSelected()) {

                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("You didn't fill all fields");
                    alert.setContentText("Please fill all red fields or check the payment if you didn't...");
                    alert.show();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Try again");
                alert.setContentText("We are sorry, but you can only rent a car up to 60 days");
                alert.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Try again");
            alert.setContentText("Dates aren't correct!\n Please choose your dates again.");
            alert.show();
        }
    }
}
