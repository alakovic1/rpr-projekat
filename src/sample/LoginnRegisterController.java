package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class LoginnRegisterController {
    public TextField usernameLogin;
    public TextField passwordLogin;
    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public TextField adress;
    public TextField email;
    public TextField password;

    private boolean usernameLoginIsValid = false;
    private boolean passwordLoginIsValid = false;
    private boolean firstNameIsValid = false;
    private boolean lastNameIsValid = false;
    private boolean usernameIsValid = false;
    private boolean adressIsValid = false;
    private boolean emailIsValid = false;
    private boolean passwordIsValid = false;

    private RentACarDAODatabase rentacarDAOdb;
    private ObservableList<Person> listOfPersons;

    public LoginnRegisterController() {
        rentacarDAOdb = RentACarDAODatabase.getInstance();
        listOfPersons = FXCollections.observableArrayList(rentacarDAOdb.persons());
    }

    private boolean validateFirstAndLastName(String n) {
        if(n.length() != 0) return true;
        return false;
    }

    @FXML
    public void initialize(){
        usernameLoginIsValid = false;
        passwordLoginIsValid = false;
        firstNameIsValid = false;
        lastNameIsValid = false;
        usernameIsValid = false;
        adressIsValid = false;
        emailIsValid = false;
        passwordIsValid = false;

        usernameLogin.getStyleClass().add("no");
        passwordLogin.getStyleClass().add("no");
        firstName.getStyleClass().add("no");
        lastName.getStyleClass().add("no");
        username.getStyleClass().add("no");
        adress.getStyleClass().add("no");
        email.getStyleClass().add("no");
        password.getStyleClass().add("no");

        firstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateFirstAndLastName(n)) {
                    firstName.getStyleClass().removeAll("no");
                    firstName.getStyleClass().add("yes");
                    firstNameIsValid = true;
                } else {
                    firstName.getStyleClass().removeAll("yes");
                    firstName.getStyleClass().add("no");
                    firstNameIsValid = false;
                }
            }
        });

        lastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateFirstAndLastName(n)) {
                    lastName.getStyleClass().removeAll("no");
                    lastName.getStyleClass().add("yes");
                    lastNameIsValid = true;
                } else {
                    lastName.getStyleClass().removeAll("yes");
                    lastName.getStyleClass().add("no");
                    lastNameIsValid = false;
                }
            }
        });
    }

    public void onRegister(ActionEvent actionEvent) {
    }

    public void onLogin(ActionEvent actionEvent) {
        if(usernameLogin.getText().equals("admin") && passwordLogin.getText().equals("admin")){
            Parent root = null;
            try {
                Stage stage = (Stage) usernameLogin.getScene().getWindow();
                stage.close();
                StartController.stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employeeFile.fxml"));
                root = loader.load();
                primaryStage.setTitle("Employee File");
                primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Parent root = null;
            try {
                Stage stage = (Stage) usernameLogin.getScene().getWindow();
                stage.close();
                StartController.stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientFile.fxml"));
                root = loader.load();
                primaryStage.setTitle("Client File");
                primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
