package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.net.PortUnreachableException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class EmployeeFileControllerTest {
    RentACarDAODatabase db;

    @Start
    public void start(Stage stage) throws Exception{
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employeeFile.fxml"));
        root = loader.load();
        stage.setTitle("Employee File");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
    }

    @Test
    public void AddVehicleTest(FxRobot robot){
        db = new RentACarDAODatabase();

        int numberOfVehicles = db.vehicles().size();

        robot.clickOn("#vehiclesTab");
        robot.clickOn("#addVehicleBtn");

        robot.clickOn("#nameBox");
        robot.write("BMW X5");

        //check if the color is valid
        TextField username = robot.lookup("#nameBox").queryAs(TextField.class);
        Background bg = username.getBackground();
        boolean colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound = true;
        assertTrue(colorFound);

        robot.clickOn("#brandBox");
        robot.write("BMW");

        robot.clickOn("#modelBox");
        robot.write("X5");

        //proof that you can't add if you didn't fill all fields
        robot.clickOn("#okBtn");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        robot.clickOn("#priceBox");
        robot.write("200");

        robot.clickOn("#okBtn");

        int newNumberOfVehicles = numberOfVehicles + 1;

        assertEquals(newNumberOfVehicles, db.vehicles().size());
    }

    @Test
    public void DeleteVehicleTest(FxRobot robot){
        db = new RentACarDAODatabase();

        int numberOfVehicles = db.vehicles().size();

        robot.clickOn("#vehiclesTab");

        robot.clickOn("#choiceBox");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#searchBar");
        robot.write("yes");

        //choosing the one vehicle that is not rented, because rented one can't be deleted
        //if this test fails, robot chose the vehicle that is rented, and it can't be deleted
        robot.clickOn("#tableVehicles");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.clickOn("#deleteVehicleBtn");

        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        int newNumberOfVehicles = numberOfVehicles - 1;
        assertEquals(newNumberOfVehicles, db.vehicles().size());
    }

    @Test
    public void SearchAndCheckReservationTest(FxRobot robot){
        robot.clickOn("#reservationsTab");

        robot.clickOn("#choiceBox2");

        robot.clickOn("#searchBar2");
        robot.write("12");

        robot.press(KeyCode.COMMAND).press(KeyCode.A).release(KeyCode.A).release(KeyCode.COMMAND);
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.A).release(KeyCode.CONTROL);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);

        robot.clickOn("#choiceBox2");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#searchBar2");
        robot.write("29");

        robot.clickOn("#tableReservations");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        //robot.clickOn("#adminCheckRes");
    }
}