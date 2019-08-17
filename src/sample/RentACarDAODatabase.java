package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RentACarDAODatabase {
    public static RentACarDAODatabase instance = null;
    public Connection connection;

    private PreparedStatement isValidDB, getPersonsUpit, addPersonUpit, newPersonID, getVehiclesUpit, addReservationUpit, newReservationID, getPersonResUpit, getVehicleResUpit, updateVehicleUpit;

    public static void initialize() {
        instance = new RentACarDAODatabase();
    }

    public static RentACarDAODatabase getInstance() {
        if(instance == null) initialize();
        return instance;
    }

    public RentACarDAODatabase() {
        connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:rentacar.db");
        } catch (SQLException e) {
            System.out.println("Can't read from database: " + e.getMessage());
        }

        try {
            isValidDB = connection.prepareStatement("SELECT * FROM person");
        } catch (SQLException e) {
            openDBAgain();
            try {
                isValidDB = connection.prepareStatement("SELECT * FROM person");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        try {
            getPersonsUpit = connection.prepareStatement("SELECT * FROM person");
            addPersonUpit = connection.prepareStatement("INSERT INTO person VALUES(?,?,?,?,?,?,?)");
            newPersonID = connection.prepareStatement("SELECT MAX(id)+1 FROM person");
            getVehiclesUpit = connection.prepareStatement("SELECT * FROM vehicle");
            addReservationUpit = connection.prepareStatement("INSERT INTO reservation VALUES(?,?,?,?,?,?,?,?,?,?)");
            newReservationID = connection.prepareStatement("SELECT MAX(id)+1 FROM reservation");
            getPersonResUpit = connection.prepareStatement("SELECT * FROM person WHERE id=?");
            getVehicleResUpit = connection.prepareStatement("SELECT * FROM vehicle WHERE id=?");
            updateVehicleUpit = connection.prepareStatement("UPDATE vehicle SET name=?, brand=?, model=?, nmbDoors=?, nmbSeats=?, engine=?, available=?, price=? WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openDBAgain() {
        Scanner entry = null;
        try {
            entry = new Scanner(new FileInputStream("rentacar.db.sql"));
            String sqlUpit = "";
            while (entry.hasNext()) {
                sqlUpit += entry.nextLine();
                if ( sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            entry.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> persons() {
        ArrayList<Person> result = new ArrayList();
        try {
            ResultSet rs = getPersonsUpit.executeQuery();
            while (rs.next()) {
                Person person = new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7));
                result.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Vehicle> vehicles() {
        ArrayList<Vehicle> result = new ArrayList();
        try {
            ResultSet rs = getVehiclesUpit.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getInt(5),rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                result.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addPerson(Person person){
        try {
            ResultSet rs = newPersonID.executeQuery();
            int id = 1;
            if(rs.next()) id = rs.getInt(1);

            addPersonUpit.setInt(1,id);
            addPersonUpit.setString(2,person.getFirstName());
            addPersonUpit.setString(3,person.getLastName());
            addPersonUpit.setString(4,person.getUsername());
            addPersonUpit.setString(5,person.getAdress());
            addPersonUpit.setString(6,person.getEmail());
            addPersonUpit.setString(7,person.getPassword());

            addPersonUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Person findPerson(Person person) {
        try {
            getPersonResUpit.setInt(1, person.getId());
            ResultSet rs = getPersonResUpit.executeQuery();
            if (!rs.next()) return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    private Vehicle findVehicle(Vehicle vehicle) {
        try {
            getVehicleResUpit.setInt(1, vehicle.getId());
            ResultSet rs = getVehicleResUpit.executeQuery();
            if (!rs.next()) return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public void addReservation(Reservation reservation){
        try {
            reservation.setPerson(findPerson(reservation.getPerson()));
            reservation.setVehicle(findVehicle(reservation.getVehicle()));

            ResultSet rs = newReservationID.executeQuery();
            int id = 1;
            if(rs.next()) id = rs.getInt(1);

            addReservationUpit.setInt(1,id);
            addReservationUpit.setInt(2,reservation.getPerson().getId());
            addReservationUpit.setInt(3,reservation.getVehicle().getId());
            addReservationUpit.setString(4, reservation.getPickupDate());
            addReservationUpit.setString(5, reservation.getReturnDate());
            addReservationUpit.setString(6,reservation.getCardNumber());
            addReservationUpit.setString(7,reservation.getExpirationDate());
            addReservationUpit.setInt(8,reservation.getSecurityCode());
            addReservationUpit.setString(9,reservation.getFirstName());
            addReservationUpit.setString(10,reservation.getLastName());

            addReservationUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        try {
            updateVehicleUpit.setInt(9, vehicle.getId());
            updateVehicleUpit.setString(1, vehicle.getName());
            updateVehicleUpit.setString(2, vehicle.getBrand());
            updateVehicleUpit.setString(3, vehicle.getModel());
            updateVehicleUpit.setInt(4, vehicle.getNmbDoors());
            updateVehicleUpit.setInt(5, vehicle.getNmbSeats());
            updateVehicleUpit.setString(6, vehicle.getEngine());
            updateVehicleUpit.setString(7, vehicle.getAvailable());
            updateVehicleUpit.setInt(8, vehicle.getPrice());
            updateVehicleUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
