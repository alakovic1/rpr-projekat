package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class LoginnRegisterControllerTest {

    @Start
    public void start(Stage stage) throws Exception{
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginnregister.fxml"));
        root = loader.load();
        stage.setTitle("Register / Login");
        stage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
    }

    @Test
    public void ValidateLoginTest(FxRobot robot){
        robot.clickOn("#usernameLogin");
        robot.write("alakovic1");
        robot.clickOn("#passwordLogin");
        robot.write("amila");
        robot.clickOn("#loginBttn");
    }
}