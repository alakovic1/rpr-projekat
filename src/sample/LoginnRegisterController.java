package sample;

import javafx.event.ActionEvent;
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

    public void onRegister(ActionEvent actionEvent) {
    }

    public void onLogin(ActionEvent actionEvent) {
        if(usernameLogin.getText().equals("admin") && passwordLogin.getText().equals("admin")){
            Parent root = null;
            try {
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
    }
}
