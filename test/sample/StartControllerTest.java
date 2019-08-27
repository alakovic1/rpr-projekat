package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PopupControl;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class StartControllerTest {
    Stage theStage;

    @Start
    public void start(Stage stage) throws Exception{
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
        root = loader.load();
        stage.setScene(new Scene(root, PopupControl.USE_COMPUTED_SIZE, PopupControl.USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();

        theStage = stage;
    }

    @Test
    public void StartLoginLogoutAdminClientTest(FxRobot robot){
        robot.clickOn("#registernlogin");

        robot.clickOn("#usernameLogin");
        robot.write("alakovic1");

        robot.clickOn("#passwordLogin");
        robot.write("amila");

        robot.clickOn("#loginBttn");

        robot.clickOn("#logoutBtn");

        robot.clickOn("#registernlogin");

        robot.clickOn("#usernameLogin");
        robot.write("admin");

        robot.clickOn("#passwordLogin");
        robot.write("admin");

        robot.clickOn("#loginBttn");

        robot.clickOn("#logoutBtn");

        assertFalse(theStage.isShowing());
    }
}