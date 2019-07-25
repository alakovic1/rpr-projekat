package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RentACarDAODatabase {
    public static RentACarDAODatabase instance = null;
    public Connection connection;

    private PreparedStatement isValidDB, getPersonsUpit, addPersonUpit, newPersonID;

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
}
