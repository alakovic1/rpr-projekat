package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CardInfoFileController implements Initializable {
    public Vehicle vehicle;
    public Person person;
    public double currentPrice;

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

    public CardInfoFileController(Vehicle vehicle, Person person, double currentPrice) {
        this.vehicle = vehicle;
        this.person = person;
        this.currentPrice = currentPrice;
    }

    private boolean valid(String n){
        if(n.length() == 0) return false;
        return true;
    }

    private boolean isFormValid(){
        return cardNmbValid && expDateValid && secCodeValid;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullPrice.setText(String.valueOf(currentPrice) + " KM");
        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());

        cardNmb.getStyleClass().add("no");
        expDate.getStyleClass().add("no");
        secCode.getStyleClass().add("no");
        firstName.getStyleClass().add("yes");
        lastName.getStyleClass().add("yes");

        cardNmbValid = false;
        expDateValid = false;
        secCodeValid = false;

        cardNmb.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (valid(n)) {
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
                if (valid(n)) {
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
                if (valid(n)) {
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
    }

    public void isOk(ActionEvent actionEvent) {
        if(isFormValid()){

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You didn't fill all fields");
            alert.setContentText("Please fill all red fields or check the payment if you didn't...");
            alert.show();
        }
    }
}
