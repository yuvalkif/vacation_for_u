package View;

import javafx.stage.Stage;
import sample.Controller;

/**
 * interface for GUI view classes
 */

public interface IView {

    void setController(Controller controller);
    void signInScene();
    void setCurrentStage(Stage stage);
}
