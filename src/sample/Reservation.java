package sample;

import java.util.Date;

public class Reservation {
    private int id;
    private Person person;
    private Vehicle vehicle;
    private String pickupDate;
    private String returnDate;
    private String cardNumber;
    private String expirationDate;
    private int securityCode;
    private String firstName;
    private String lastName;

    public Reservation() {
        this.person = new Person();
        this.vehicle = new Vehicle();
        this.pickupDate = "";
        this.returnDate = "";
        this.cardNumber = "0000 0000 0000 0000";
        this.expirationDate = "00/00";
        this.securityCode = 0;
        this.firstName = "";
        this.lastName = "";
    }

    public Reservation(int id, Person person, Vehicle vehicle, String pickupDate, String returnDate, String cardNumber, String expirationDate, int securityCode, String firstName, String lastName) {
        this.id = id;
        this.person = person;
        this.vehicle = vehicle;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Reservation(Person person, Vehicle vehicle, String pickupDate, String returnDate, String cardNumber, String expirationDate, int securityCode, String firstName, String lastName) {
        this.person = person;
        this.vehicle = vehicle;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Reservation(Person person, Vehicle vehicle, String pickupDate, String returnDate) {
        this.person = person;
        this.vehicle = vehicle;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
