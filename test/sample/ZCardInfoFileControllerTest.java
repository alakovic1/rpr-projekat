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
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ZCardInfoFileControllerTest {

    @Start
    public void start(Stage stage) throws Exception{
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cardInfoFile.fxml"));
        CardInfoFileController controller = new CardInfoFileController(new Vehicle(), new Person(), 0, LocalDate.now(), LocalDate.now());
        loader.setController(controller);
        root = loader.load();
        stage.setTitle("Card Info");
        stage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
    }

    @Test
    public void ValidationTest(FxRobot robot){
        robot.clickOn("#cardNmb");
        robot.write("1234444");

        //check color
        TextField cardNmb = robot.lookup("#cardNmb").queryAs(TextField.class);
        Background bg = cardNmb.getBackground();
        boolean colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound = true;
        assertFalse(colorFound);

        robot.clickOn("#expDate");
        robot.write("14/20");

        //check color
        TextField exp = robot.lookup("#expDate").queryAs(TextField.class);
        Background bg2 = exp.getBackground();
        boolean colorFound2 = false;
        for (BackgroundFill bf : bg2.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound2 = true;
        assertFalse(colorFound2);

        //check button
        robot.clickOn("#okBtn");

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        robot.clickOn("#secCode");
        robot.write("123");

        //check color
        TextField sec = robot.lookup("#secCode").queryAs(TextField.class);
        Background bg3 = sec.getBackground();
        boolean colorFound3 = false;
        for (BackgroundFill bf : bg3.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound3 = true;
        assertTrue(colorFound3);

        robot.clickOn("#firstName");
        robot.write("Amila");

        //check color
        TextField fn = robot.lookup("#firstName").queryAs(TextField.class);
        Background bg4 = fn.getBackground();
        boolean colorFound4 = false;
        for (BackgroundFill bf : bg4.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound4 = true;
        assertTrue(colorFound4);

        robot.clickOn("#lastName");
        robot.write("Lakovic");

        //check color
        TextField ln = robot.lookup("#lastName").queryAs(TextField.class);
        Background bg5 = ln.getBackground();
        boolean colorFound5 = false;
        for (BackgroundFill bf : bg5.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound5 = true;
        assertTrue(colorFound5);

        //check button
        robot.clickOn("#okBtn");

        DialogPane dialogPane2 = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton2 = (Button) dialogPane2.lookupButton(ButtonType.OK);
        robot.clickOn(okButton2);

        robot.clickOn("#cardNmb");
        robot.press(KeyCode.COMMAND).press(KeyCode.A).release(KeyCode.A).release(KeyCode.COMMAND);
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.A).release(KeyCode.CONTROL);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("1234123412341234");

        //check color
        TextField card2 = robot.lookup("#firstName").queryAs(TextField.class);
        Background bg6 = card2.getBackground();
        boolean colorFound6 = false;
        for (BackgroundFill bf : bg6.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound6 = true;
        assertTrue(colorFound6);

        robot.clickOn("#expDate");
        robot.press(KeyCode.COMMAND).press(KeyCode.A).release(KeyCode.A).release(KeyCode.COMMAND);
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.A).release(KeyCode.CONTROL);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("04/20");

        //check color
        TextField exp2 = robot.lookup("#firstName").queryAs(TextField.class);
        Background bg7 = exp2.getBackground();
        boolean colorFound7 = false;
        for (BackgroundFill bf : bg7.getFills())
            if (bf.getFill().toString().contains("64c288"))
                colorFound7 = true;
        assertTrue(colorFound7);
    }
}