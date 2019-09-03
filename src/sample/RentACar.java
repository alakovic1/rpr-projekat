package sample;

import java.util.ArrayList;

public class RentACar {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public RentACar() {
    }

    public RentACar(ArrayList<Vehicle> vehicles, ArrayList<Reservation> reservations) {
        this.vehicles = vehicles;
        this.reservations = reservations;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
}
