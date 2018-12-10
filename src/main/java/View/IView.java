package View;

import javafx.stage.Stage;
import Control.Controller;

/**
 * interface for GUI view classes
 */

public interface IView {
    void setCurrentStage(Stage primaryStage);

    void setController(Controller controller);

    /**
     * handle the signup scene
     */
    //void signInScene();
    //void setCurrentStage(Stage stage);

    /**
     * handle deletion scene
     */
    //void handleDelete();

    /**
     * handle update scene
     */
    //void handleUpdate();

    /**
     * handle search scene
     */
    ///void handleRead();

}
