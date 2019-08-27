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
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class LoginnRegisterController {
    public Person person;
    public TextField usernameLogin;
    public TextField passwordLogin;
    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public TextField adress;
    public TextField email;
    public TextField password;
    public Button loginBttn;
    public Button registerBttn;
    public Tab loginTab;
    public Tab registerTab;

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

    private boolean validateText(String n) {
        if(n.length() != 0) return true;
        return false;
    }

    private boolean isCorrectPassword(){
        Person currentPerson = new Person();
        for(Person p : listOfPersons){
            if(usernameLogin.getText().equals(p.getUsername())){
                currentPerson = p;
                break;
            }
        }

        if(currentPerson.getPassword().equals(passwordLogin.getText())) return true;
        return false;
    }

    private boolean doesExistClient(){
        Person currentPerson = new Person();
        for(Person p : listOfPersons){
            if(usernameLogin.getText().equals(p.getUsername())){
                currentPerson = p;
                break;
            }
        }

        if(!currentPerson.getFirstName().equals("")) return true;
        return false;
    }

    private boolean isValidForm(){
        if(firstName.getText().length() != 0 && lastName.getText().length() != 0 && username.getText().length() != 0 && adress.getText().length() != 0 && email.getText().length() != 0 && password.getText().length() != 0 && emailIsValid) return true;
        return false;
    }

    private boolean isValidEmail(String n) {
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '@') return true;
        return false;
    }

    private boolean usernameDoesExist(){
        Person currentPerson = new Person();
        for(Person p : listOfPersons){
            if(username.getText().equals(p.getUsername())){
                currentPerson = p;
                break;
            }
        }

        if(currentPerson.getFirstName().equals("")) return true;
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

        usernameLogin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
                    usernameLogin.getStyleClass().removeAll("no");
                    usernameLogin.getStyleClass().add("yes");
                    usernameLoginIsValid = true;
                } else {
                    usernameLogin.getStyleClass().removeAll("yes");
                    usernameLogin.getStyleClass().add("no");
                    usernameLoginIsValid = false;
                }
            }
        });

        passwordLogin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
                    passwordLogin.getStyleClass().removeAll("no");
                    passwordLogin.getStyleClass().add("yes");
                    passwordLoginIsValid = true;
                } else {
                    passwordLogin.getStyleClass().removeAll("yes");
                    passwordLogin.getStyleClass().add("no");
                    passwordLoginIsValid = false;
                }
            }
        });

        firstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
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
                if (validateText(n)) {
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

        username.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
                    username.getStyleClass().removeAll("no");
                    username.getStyleClass().add("yes");
                    usernameIsValid = true;
                } else {
                    username.getStyleClass().removeAll("yes");
                    username.getStyleClass().add("no");
                    usernameIsValid = false;
                }
            }
        });

        adress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
                    adress.getStyleClass().removeAll("no");
                    adress.getStyleClass().add("yes");
                    adressIsValid = true;
                } else {
                    adress.getStyleClass().removeAll("yes");
                    adress.getStyleClass().add("no");
                    adressIsValid = false;
                }
            }
        });

        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (isValidEmail(n)) {
                    email.getStyleClass().removeAll("no");
                    email.getStyleClass().add("yes");
                    emailIsValid = true;
                } else {
                    email.getStyleClass().removeAll("yes");
                    email.getStyleClass().add("no");
                    emailIsValid = false;
                }
            }
        });

        password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validateText(n)) {
                    password.getStyleClass().removeAll("no");
                    password.getStyleClass().add("yes");
                    passwordIsValid = true;
                } else {
                    password.getStyleClass().removeAll("yes");
                    password.getStyleClass().add("no");
                    passwordIsValid = false;
                }
            }
        });
    }

    public void onRegister(ActionEvent actionEvent) {
        if (isValidForm()) {
            //if (usernameDoesExist()) {
                Person newPerson = new Person(firstName.getText(), lastName.getText(), username.getText(), adress.getText(), email.getText(), password.getText());
                rentacarDAOdb.addPerson(newPerson);
                Parent root = null;
                try {
                    Stage stage = (Stage) usernameLogin.getScene().getWindow();
                    stage.close();
                    //StartController.stage.close();
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientFile.fxml"));
                    ClientFileController controller = new ClientFileController(newPerson);
                    loader.setController(controller);
                    root = loader.load();
                    primaryStage.setTitle("Client File");
                    primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    primaryStage.initModality(Modality.APPLICATION_MODAL);
                    primaryStage.setResizable(false);
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            /*}
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("That username is alredy used by another user");
                alert.setContentText("Please enter a new username");
                alert.show();
            }*/
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You didn't fill all fields");
            alert.setContentText("Please fill all red fields correctly");
            alert.show();
        }
    }

    public void onLogin(ActionEvent actionEvent) {
        if(usernameLogin.getText().length() != 0 && passwordLogin.getText().length() != 0) {
            if (usernameLogin.getText().equals("admin") && passwordLogin.getText().equals("admin")) {
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
            } else {
                if (!doesExistClient()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("This client does not exist");
                    alert.setContentText("Please enter your username or password correctly, or register");
                    alert.show();
                } else {
                    if (isCorrectPassword()) {
                        Person currentPerson = new Person();
                        for(Person p : listOfPersons){
                            if(usernameLogin.getText().equals(p.getUsername())){
                                currentPerson = p;
                                break;
                            }
                        }
                        Parent root = null;
                        try {
                            Stage stage = (Stage) usernameLogin.getScene().getWindow();
                            stage.close();
                            //StartController.stage.close();
                            Stage primaryStage = new Stage();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientFile.fxml"));
                            ClientFileController controller = new ClientFileController(currentPerson);
                            loader.setController(controller);
                            root = loader.load();
                            primaryStage.setTitle("Client File");
                            primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                            primaryStage.initModality(Modality.APPLICATION_MODAL);
                            primaryStage.setResizable(false);
                            primaryStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Incorrect password");
                        alert.setContentText("Please enter your password correctly");
                        alert.show();
                    }
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You didn't fill all fields");
            alert.setContentText("Please fill all red fields");
            alert.show();
        }
    }
}
