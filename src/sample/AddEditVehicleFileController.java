package sample;

public class AddEditVehicleFileController {
    private RentACarDAODatabase rentacarDAOdb;

    public AddEditVehicleFileController() {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
    }
}
