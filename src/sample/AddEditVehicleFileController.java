package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditVehicleFileController implements Initializable {
    private RentACarDAODatabase rentacarDAOdb;

    public TextField nameBox;
    public TextField brandBox;
    public TextField modelBox;
    public Spinner<Integer> nmbDoorsBox;
    public Spinner<Integer> nmbSeatsBox;
    public ChoiceBox<String> engineBox;
    public TextField priceBox;
    public Button okBtn;

    public AddEditVehicleFileController() {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
    }

    public void onOkClick(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameBox.getStyleClass().add("no");
        brandBox.getStyleClass().add("no");
        modelBox.getStyleClass().add("no");
        priceBox.getStyleClass().add("no");
    }
}
