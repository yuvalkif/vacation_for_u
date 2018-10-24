package View;

import Logger.StageHolder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller;
import Model.*;
import java.io.IOException;

public class View implements IView{

    private Controller controller;
    private ISQLModel model;
    private Stage primaryStage;

    public View(){}
    /**
     * store the current stage in a stack.
     * set the  new given stage and show it
     * @param stage current stage to show
     */
    public void setCurrentStage(Stage stage){
        this.primaryStage = stage ;

    }


    //region Scenes

    /**
     * opens the sign in scene for the user .
     * holds the new stage in the stageHolder and closes it from the sign in scene controller.
     * a record is returned from the scene controller to be inserted using the main controller class.
     */
    public void signInScene(){

        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(getClass().getResource("logInFXML.fxml").openStream());
            Scene scene = new Scene(root,500,500);
            Stage stage = new Stage();
            stage.setScene(scene);

            primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            primaryStage.show();
            LoginSceneController sceneController = loader.getController();

            //pass to controller
            this.controller.handleSubmitSignIn(sceneController.getToSubmit());

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }

    }

    //endregion

    //region UI LOGIC



    //endregion

    /**
     * set the main model
     * @param model
     */
    public void setModel(ISQLModel model) {
        this.model = model;
    }

    /**
     * set the main controller
     * @param controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
}

