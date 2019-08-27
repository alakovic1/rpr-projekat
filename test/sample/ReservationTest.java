package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void getPerson() {
        Person person = new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password");
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        Reservation reservation = new Reservation(person, vehicle, "12. 09. 2019", "30. 09. 2019", "1234123412341234", "05/21", 123, "firstName", "lastName");
        assertEquals("firstName", reservation.getPerson().getFirstName());
    }

    @Test
    void getPickupDate() {
        Reservation reservation = new Reservation(new Person(), new Vehicle(), "12. 09. 2019", "30. 09. 2019", "1234123412341234", "05/21", 123, "firstName", "lastName");
        assertEquals("12. 09. 2019", reservation.getPickupDate());
    }

    @Test
    void setReturnDate() {
        Reservation reservation = new Reservation(new Person(), new Vehicle(), "12. 09. 2019", "30. 09. 2019", "1234123412341234", "05/21", 123, "firstName", "lastName");
        reservation.setReturnDate("01. 10. 2019");
        assertEquals("01. 10. 2019", reservation.getReturnDate());
    }

    @Test
    void setCardNumber() {
        Reservation reservation = new Reservation(new Person(), new Vehicle(), "12. 09. 2019", "30. 09. 2019", "1234123412341234", "05/21", 123, "firstName", "lastName");
        reservation.setCardNumber("4321432143214321");
        assertEquals("4321432143214321", reservation.getCardNumber());
    }

}