package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditReservationFileController implements Initializable {
    private RentACarDAODatabase rentacarDAOdb;
    private Reservation reservation;

    public DatePicker pickupDate;
    public DatePicker returnDate;

    public EditReservationFileController(Reservation reservation) {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
        this.reservation = reservation;
    }

    public void onOK(ActionEvent actionEvent) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd. MM. yyyy");
        String text = pickupDate.getValue().format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        String text2 = returnDate.getValue().format(formatters);
        LocalDate parsedDate2 = LocalDate.parse(text2, formatters);
        rentacarDAOdb.updateReservation(new Reservation(reservation.getPerson(), reservation.getVehicle(), parsedDate.format(formatters), parsedDate2.format(formatters), reservation.getCardNumber(), reservation.getExpirationDate(), reservation.getSecurityCode(), reservation.getFirstName(), reservation.getLastName()));
        Stage stage = (Stage) pickupDate.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");
        pickupDate.setValue(LocalDate.parse(reservation.getPickupDate(), formatter));
        returnDate.setValue(LocalDate.parse(reservation.getReturnDate(), formatter));
    }
}
