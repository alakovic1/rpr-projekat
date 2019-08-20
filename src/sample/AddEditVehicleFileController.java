package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditVehicleFileController implements Initializable {
    private RentACarDAODatabase rentacarDAOdb;
    private Vehicle vehicle;

    public TextField nameBox;
    public TextField brandBox;
    public TextField modelBox;
    public Spinner<Integer> nmbDoorsBox;
    public Spinner<Integer> nmbSeatsBox;
    public ChoiceBox<String> engineBox;
    public TextField priceBox;
    public Button okBtn;

    private boolean nameIsValid = false;
    private boolean brandIsValid = false;
    private boolean modelIsValid = false;
    private boolean priceIsValid = false;

    public AddEditVehicleFileController(Vehicle vehicle) {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
        this.vehicle = vehicle;
    }

    private boolean isFormValid() {
        return nameIsValid && brandIsValid && modelIsValid && priceIsValid;
    }

    private boolean validateText(String n) {
        if (n.length() == 0) return false;
        return true;
    }

    private boolean validatePrice(String n) {
        if (n.length() == 0) return false;
        for (int i = 0; i < n.length(); i++) {
            if ((n.charAt(i) >= 'A' && n.charAt(i) <= 'Z') || (n.charAt(i) >= 'a' && n.charAt(i) <= 'z')) return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (vehicle != null) {
            nameBox.setText(vehicle.getName());
            brandBox.setText(vehicle.getBrand());
            modelBox.setText(vehicle.getModel());
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, vehicle.getNmbDoors());
            nmbDoorsBox.setValueFactory(valueFactory);
            SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, vehicle.getNmbSeats());
            nmbSeatsBox.setValueFactory(valueFactory2);
            engineBox.setValue(vehicle.getEngine());
            priceBox.setText(String.valueOf(vehicle.getPrice()));
        } else {
            engineBox.getSelectionModel().selectFirst();
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5);
            nmbDoorsBox.setValueFactory(valueFactory);
            SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5);
            nmbSeatsBox.setValueFactory(valueFactory2);

            nameIsValid = false;
            brandIsValid = false;
            modelIsValid = false;
            priceIsValid = false;

            nameBox.getStyleClass().add("no");
            brandBox.getStyleClass().add("no");
            modelBox.getStyleClass().add("no");
            priceBox.getStyleClass().add("no");

            nameBox.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                    if (validateText(n)) {
                        nameBox.getStyleClass().removeAll("no");
                        nameBox.getStyleClass().add("yes");
                        nameIsValid = true;
                    } else {
                        nameBox.getStyleClass().removeAll("yes");
                        nameBox.getStyleClass().add("no");
                        nameIsValid = false;
                    }
                }
            });

            brandBox.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                    if (validateText(n)) {
                        brandBox.getStyleClass().removeAll("no");
                        brandBox.getStyleClass().add("yes");
                        brandIsValid = true;
                    } else {
                        brandBox.getStyleClass().removeAll("yes");
                        brandBox.getStyleClass().add("no");
                        brandIsValid = false;
                    }
                }
            });

            modelBox.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                    if (validateText(n)) {
                        modelBox.getStyleClass().removeAll("no");
                        modelBox.getStyleClass().add("yes");
                        modelIsValid = true;
                    } else {
                        modelBox.getStyleClass().removeAll("yes");
                        modelBox.getStyleClass().add("no");
                        modelIsValid = false;
                    }
                }
            });

            priceBox.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                    if (validatePrice(n)) {
                        priceBox.getStyleClass().removeAll("no");
                        priceBox.getStyleClass().add("yes");
                        priceIsValid = true;
                    } else {
                        priceBox.getStyleClass().removeAll("yes");
                        priceBox.getStyleClass().add("no");
                        priceIsValid = false;
                    }
                }
            });
        }
    }

    public void onOkClick(ActionEvent actionEvent) {
        if (vehicle == null) {
            if (isFormValid()) {
                Vehicle newVehicle = new Vehicle(nameBox.getText(), brandBox.getText(), modelBox.getText(), nmbDoorsBox.getValue(), nmbSeatsBox.getValue(), engineBox.getValue(), "yes", Integer.valueOf(priceBox.getText()));
                rentacarDAOdb.addVehicle(newVehicle);
                Stage stage = (Stage) nameBox.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You didn't fill all fields");
                alert.setContentText("Please fill all red fields...");
                alert.show();
            }
        } else {
            vehicle.setName(nameBox.getText());
            vehicle.setBrand(brandBox.getText());
            vehicle.setModel(modelBox.getText());
            vehicle.setNmbDoors(nmbDoorsBox.getValue());
            vehicle.setNmbSeats(nmbSeatsBox.getValue());
            vehicle.setEngine(engineBox.getValue());
            vehicle.setAvailable("yes");
            vehicle.setPrice(Integer.valueOf(priceBox.getText()));
            rentacarDAOdb.updateVehicle(vehicle);
            Stage stage = (Stage) nameBox.getScene().getWindow();
            stage.close();
        }
    }
}
