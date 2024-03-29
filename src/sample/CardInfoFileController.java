package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class CardInfoFileController implements Initializable {
    public Vehicle vehicle;
    public Person person;
    public double currentPrice;
    public LocalDate pickupDate;
    public LocalDate returnDate;
    private RentACarDAODatabase rentacarDAOdb;

    public TextField fullPrice;
    public TextField cardNmb;
    public TextField expDate;
    public TextField secCode;
    public TextField firstName;
    public TextField lastName;
    public Button okBtn;

    public boolean cardNmbValid = false;
    public boolean expDateValid = false;
    public boolean secCodeValid = false;
    public boolean firstNameValid = false;
    public boolean lastNameValid = false;

    public CardInfoFileController(Vehicle vehicle, Person person, double currentPrice, LocalDate pickupDate, LocalDate returnDate) {
        this.vehicle = vehicle;
        this.person = person;
        this.currentPrice = currentPrice;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;

        rentacarDAOdb = RentACarDAODatabase.getInstance();
    }

    private boolean valid(String n){
        if(n.length() == 0) return false;
        return true;
    }

    private boolean validCard(String n){
        if(n.length() == 16) return true;
        return false;
    }

    private boolean validExpDate(String n){
        if(n.length() != 5) return false;
        boolean there = false;
        String s = "";
        String s1 = "";
        int br = 0;
        for(int i = 0; i < n.length(); i++){
            if(n.charAt(i) == '/') {
                there = true;
                br++;
            }
            if(there && br > 0) s += n.charAt(i);
            if(!there) s1 += n.charAt(i);
        }
        if(!there) return false;
        String now = String.valueOf(LocalDate.now().getYear()).substring(2, 4);
        s = s.substring(1, 3);
        if(Integer.parseInt(s) < Integer.parseInt(now)) return false;
        if(Integer.parseInt(s1) <= 0 || Integer.parseInt(s1) > 12) return false;
        return true;
    }

    private boolean validSecCode(String n){
        if(n.length() == 3 || n.length() == 4) return true;
        return false;
    }

    private boolean isFormValid(){
        return cardNmbValid && expDateValid && secCodeValid && firstNameValid && lastNameValid;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullPrice.setText(String.valueOf(currentPrice) + " KM");

        cardNmb.getStyleClass().add("no");
        expDate.getStyleClass().add("no");
        secCode.getStyleClass().add("no");
        firstName.getStyleClass().add("no");
        lastName.getStyleClass().add("no");

        cardNmbValid = false;
        expDateValid = false;
        secCodeValid = false;
        firstNameValid = false;
        lastNameValid = false;

        cardNmb.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validCard(n)) {
                    cardNmb.getStyleClass().removeAll("no");
                    cardNmb.getStyleClass().add("yes");
                    cardNmbValid = true;
                } else {
                    cardNmb.getStyleClass().removeAll("yes");
                    cardNmb.getStyleClass().add("no");
                    cardNmbValid = false;
                }
            }
        });

        expDate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validExpDate(n)) {
                    expDate.getStyleClass().removeAll("no");
                    expDate.getStyleClass().add("yes");
                    expDateValid = true;
                } else {
                    expDate.getStyleClass().removeAll("yes");
                    expDate.getStyleClass().add("no");
                    expDateValid = false;
                }
            }
        });

        secCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validSecCode(n)) {
                    secCode.getStyleClass().removeAll("no");
                    secCode.getStyleClass().add("yes");
                    secCodeValid = true;
                } else {
                    secCode.getStyleClass().removeAll("yes");
                    secCode.getStyleClass().add("no");
                    secCodeValid = false;
                }
            }
        });

        firstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (valid(n)) {
                    firstName.getStyleClass().removeAll("no");
                    firstName.getStyleClass().add("yes");
                    firstNameValid = true;
                } else {
                    firstName.getStyleClass().removeAll("yes");
                    firstName.getStyleClass().add("no");
                    firstNameValid = false;
                }
            }
        });

        lastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (valid(n)) {
                    lastName.getStyleClass().removeAll("no");
                    lastName.getStyleClass().add("yes");
                    lastNameValid = true;
                } else {
                    lastName.getStyleClass().removeAll("yes");
                    lastName.getStyleClass().add("no");
                    lastNameValid = false;
                }
            }
        });
    }

    public void isOk(ActionEvent actionEvent) {
        if(isFormValid()){
            vehicle.setAvailable("no");
            rentacarDAOdb.updateVehicle(vehicle);
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd. MM. yyyy");
            String text = pickupDate.format(formatters);
            LocalDate parsedDate = LocalDate.parse(text, formatters);
            String text2 = returnDate.format(formatters);
            LocalDate parsedDate2 = LocalDate.parse(text2, formatters);
            rentacarDAOdb.addReservation2(new Reservation(person, vehicle, parsedDate.format(formatters), parsedDate2.format(formatters), cardNmb.getText(), expDate.getText(), Integer.parseInt(secCode.getText()), firstName.getText(), lastName.getText()));
            Parent root = null;
            try {
                Stage stage = (Stage) fullPrice.getScene().getWindow();
                stage.close();
                RentFileController.stagee.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientFile.fxml"));
                ClientFileController controller = new ClientFileController(person);
                loader.setController(controller);
                root = loader.load();
                primaryStage.setTitle("Client File");
                primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
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
            alert.setHeaderText("You didn't fill all fields or your card expired");
            alert.setContentText("Please fill all red fields or check the payment if you didn't...");
            alert.show();
        }
    }
}
