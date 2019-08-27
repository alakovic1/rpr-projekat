package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ClientFileControllerTest {
    Stage theStage;

    @Start
    public void start(Stage stage) throws Exception{
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientFile.fxml"));
        ClientFileController controller = new ClientFileController(new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password"));
        loader.setController(controller);
        root = loader.load();
        stage.setTitle("Client File");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();

        theStage = stage;
    }

    @Test
    public void SearchingVehiclesTest(FxRobot robot){
        //search the vehicle by price
        robot.clickOn("#boxFilter");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#searchBar");
        robot.write("200");

        //choose a vehicle
        robot.clickOn("#tableofVehiclesClient");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.UP).release(KeyCode.UP);

        assertTrue(theStage.isShowing());
    }

    @Test
    public void MoreInformationTest(FxRobot robot){
        robot.clickOn("#tableofVehiclesClient");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.UP).release(KeyCode.UP);

        robot.clickOn("#infoBtn");
        robot.clickOn("#cancelBtn");

        assertFalse(theStage.isShowing());
    }
}