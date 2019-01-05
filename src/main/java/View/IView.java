package View;

import javafx.stage.Stage;
import Control.Controller;

/**
 * interface for GUI view classes
 */

public interface IView {
    void setCurrentStage(Stage primaryStage);

    void setController(Controller controller);

    void initializeListeners();

    void handleSignUp();

    void handleSignIn();

    void handleConfirmOrder();

    void handleSearchVacation();

    void handleDelete();

    void handleUserSearch();

    void handleUserUpdate();

    void handlePublishVacation();

    void handleSignOut();


}
