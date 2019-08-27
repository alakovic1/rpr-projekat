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
    }

    @Test
    public void testRentingVehicle1(FxRobot robot){
/*
        //search the vehicle by price
        robot.clickOn("#boxFilter");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#searchBar");
        robot.write("12");

        //choose a vehicle
        robot.clickOn("#tableofVehiclesClient");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.clickOn("#tableofVehiclesClient");
        robot.clickOn("#rentBtn");

        //fill dates
        robot.clickOn("#pickupDate");
        robot.write("09/03/19");

        //see if dates are filled correctly
        DatePicker pickupDate = robot.lookup("#pickupDate").queryAs(DatePicker.class);
        Background bg = pickupDate.getEditor().getBackground();
        boolean colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound = true;
        assertTrue(colorFound);

        robot.clickOn("#returnDate");
        robot.write("10/10/19");

        DatePicker returnDate = robot.lookup("#returnDate").queryAs(DatePicker.class);
        Background bg2 = returnDate.getEditor().getBackground();
        boolean colorFound2 = false;
        for (BackgroundFill bf : bg2.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound2 = true;
        assertTrue(colorFound2);

        //press finish to see that the fields arent't filled correctly
        robot.clickOn("#finishBtn");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);*/
    }
}