package View;


import Model.ISQLModel;
import javafx.stage.Stage;
import sample.Controller;

/**
 * interface for GUI view classes
 */

public interface IView {

    void setController(Controller controller);
    void setModel(ISQLModel model);
    void signInScene();
    void setCurrentStage(Stage stage);
}
