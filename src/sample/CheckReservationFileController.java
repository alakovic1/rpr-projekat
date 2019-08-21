package sample;


import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckReservationFileController implements Initializable {
    private Reservation reservation;

    public TextField firstAndLastName;
    public TextField email;
    public TextField vehicleNBM;
    public TextField numberDS;
    public TextField engine;
    public TextField price;
    public TextField PRDate;
    public TextField cardNmb;
    public TextField expDate;
    public TextField secCode;
    public TextField firstNameCard;
    public TextField lastNameCard;

    public CheckReservationFileController(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstAndLastName.setText(reservation.getPerson().getFirstName() + " " + reservation.getPerson().getLastName());
        email.setText(reservation.getPerson().getEmail());
        vehicleNBM.setText(reservation.getVehicle().getName() + " / " + reservation.getVehicle().getBrand() + " / " + reservation.getVehicle().getModel());
        numberDS.setText(reservation.getVehicle().getNmbDoors() + " / " + reservation.getVehicle().getNmbSeats());
        engine.setText(reservation.getVehicle().getEngine());
        price.setText(String.valueOf(reservation.getVehicle().getPrice()));
        PRDate.setText(reservation.getPickupDate() + " - " + reservation.getReturnDate());
        cardNmb.setText(reservation.getCardNumber());
        expDate.setText(reservation.getExpirationDate());
        secCode.setText(String.valueOf(reservation.getSecurityCode()));
        firstNameCard.setText(reservation.getFirstName());
        lastNameCard.setText(reservation.getLastName());
    }
}
