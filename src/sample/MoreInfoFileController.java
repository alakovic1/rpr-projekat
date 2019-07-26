package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoreInfoFileController implements Initializable {
    public Vehicle vehicle;
    public TextField nameField;
    public TextField brandField;
    public TextField modelField;
    public TextField nmbDoorsField;
    public TextField nmbSeatsField;
    public TextField engineField;
    public TextField availableField;
    public TextField mainPriceField;
    public TextField price1Field;
    public TextField price2Field;
    public TextField price3Field;
    public Button rentCarBtn;
    public Button cancelBtn;

    public MoreInfoFileController() {
    }

    public MoreInfoFileController(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(vehicle != null){
            nameField.setText(vehicle.getName());
            brandField.setText(vehicle.getBrand());
            modelField.setText(vehicle.getModel());
            nmbDoorsField.setText(String.valueOf(vehicle.getNmbDoors()));
            nmbSeatsField.setText(String.valueOf(vehicle.getNmbSeats()));
            engineField.setText(vehicle.getEngine());
            availableField.setText(vehicle.getAvailable());
            mainPriceField.setText(String.valueOf(vehicle.getPrice()));
            int minPrice1 = vehicle.getPrice();
            int maxPrice1 = 3 * vehicle.getPrice();
            String forPrice1 = minPrice1 + " - " + maxPrice1;
            price1Field.setText(forPrice1);
            int minPrice2 = 3 * vehicle.getPrice();
            int maxPrice2 = 15 * vehicle.getPrice();
            String forPrice2 = minPrice2 + " - " + maxPrice2;
            price2Field.setText(forPrice2);
            int minPrice3 = 15 * vehicle.getPrice();
            int maxPrice3 = 60 * vehicle.getPrice();
            String forPrice3 = minPrice3 + " - " + maxPrice3;
            price3Field.setText(forPrice3);

            if(vehicle.getAvailable().equals("yes")){
                availableField.getStyleClass().add("yes");
            }
            else{
                availableField.getStyleClass().add("no");
                rentCarBtn.setDisable(true);
            }
        }
    }

    public void onRentThisCar(ActionEvent actionEvent) {
        Parent root = null;
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rentFile.fxml"));
            root = loader.load();
            primaryStage.setTitle("Rent");
            primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
