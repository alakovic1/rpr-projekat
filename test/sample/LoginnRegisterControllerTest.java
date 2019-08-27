package sample;

import javafx.collections.ObservableList;
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
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class LoginnRegisterControllerTest {
    RentACarDAODatabase db;

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
        robot.write("amilaaaaaaa");
        robot.clickOn("#loginBttn");

        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        TextField username = robot.lookup("#usernameLogin").queryAs(TextField.class);
        Background bg = username.getBackground();
        boolean colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound = true;
        assertTrue(colorFound);

        TextField password1 = robot.lookup("#passwordLogin").queryAs(TextField.class);
        robot.clickOn("#passwordLogin");
        robot.press(KeyCode.COMMAND).press(KeyCode.A).release(KeyCode.A).release(KeyCode.COMMAND);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        Background bg1 = password1.getBackground();
        boolean colorFound1 = false;
        for (BackgroundFill bf : bg1.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound1 = true;
        assertFalse(colorFound1);

        robot.clickOn("#passwordLogin");
        robot.write("amila");

        TextField password = robot.lookup("#passwordLogin").queryAs(TextField.class);
        Background bg2 = password.getBackground();
        boolean colorFound2 = false;
        for (BackgroundFill bf : bg2.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound2 = true;
        assertTrue(colorFound2);

        //robot.clickOn("#loginBttn");
    }

    @Test
    public void ValidateRegistrationTest(FxRobot robot){
        robot.clickOn("#registerTab");

        robot.clickOn("#firstName");
        robot.write("Amila");

        robot.clickOn("#lastName");
        robot.write("Lakovic");

        robot.clickOn("#username");
        robot.write("amilakovic");

        robot.clickOn("#adress");
        robot.write("Zmaja od Bosne bb");

        robot.clickOn("#email");
        robot.write("amila");

        robot.clickOn("#password");
        robot.write("amila");

        TextField password = robot.lookup("#email").queryAs(TextField.class);
        Background bg2 = password.getBackground();
        boolean colorFound2 = false;
        for (BackgroundFill bf : bg2.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound2 = true;
        assertFalse(colorFound2);
        robot.clickOn("#registerBttn");

        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        robot.clickOn("#email");
        robot.press(KeyCode.COMMAND).press(KeyCode.A).release(KeyCode.A).release(KeyCode.COMMAND);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("alakovic1@etf.unsa.ba");
    }

    @Test
    public void AddRegistrationToDatabase(FxRobot robot){
        db = new RentACarDAODatabase();
        ArrayList<Person> persons = db.persons();
        int numberOfRegistrations = persons.size();

        robot.clickOn("#registerTab");

        robot.clickOn("#firstName");
        robot.write("Amila");

        robot.clickOn("#lastName");
        robot.write("Lakovic");

        robot.clickOn("#username");
        robot.write("amilakovic");

        robot.clickOn("#adress");
        robot.write("Zmaja od Bosne bb");

        robot.clickOn("#email");
        robot.write("alakovic1@etf.unsa.ba");

        robot.clickOn("#password");
        robot.write("amila");

        robot.clickOn("#registerBttn");

        int newNumberOfRegistrations = numberOfRegistrations + 1;
        assertEquals(newNumberOfRegistrations, db.persons().size());
    }
}