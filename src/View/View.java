package View;

import Logger.StageHolder;
import Objects.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controller;
import java.io.IOException;
import java.util.List;

public class View implements IView{

    private Controller controller;
    private Stage primaryStage;
    private List<User> searchResults;

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

    public void handleRead(){

        FXMLLoader loader = new FXMLLoader();
        try{
            Parent root = loader.load(getClass().getResource("readAllController.fxml").openStream());
            SplitPane splitPane =(SplitPane) root.getChildrenUnmodifiable().get(0);
            AnchorPane upperPane =(AnchorPane) splitPane.getItems().get(0);
            Node scrollPane = (ScrollPane)upperPane.getChildren().get(0);

            Scene scene = new Scene(root,600,600);
            Stage stage = new Stage();
            stage.setScene(scene);
            ReadAll readAll = loader.getController();

            while(!readAll.getIsDone()) {
                primaryStage.hide();
                StageHolder.getInstance().holdStage(stage);
                stage.showAndWait();
                showSearchResults(this.controller.searchAllRecordsByFields(readAll.getSearchFields()),(ScrollPane) scrollPane);
            }
            primaryStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * show the results in search window
     * @param searchResults list of records that match the search fields
     * @param pane the pane to show the results in
     */
    private void showSearchResults(List<User> searchResults , ScrollPane pane){

        if(searchResults == null || pane == null)
            return;

        double x = 10, y = 20 , xDelta = 10 , yDelta = 30;
        int counter = 1;

        for (User record : searchResults
             ) {
            Label username = new Label(record.getUsername());
            Label password = new Label(record.getPassword());
            Label firstname = new Label(record.getFirstname());
            Label lastname = new Label(record.getLastname());
            Label city = new Label(record.getCity());
            Label date = new Label(record.getDate());

            username.setLayoutX(x); username.setLayoutY(y);
            password.setLayoutX(x+xDelta*(counter++)); password.setLayoutY(y);
            firstname.setLayoutX(x+xDelta*(counter++)); firstname.setLayoutY(y);
            lastname.setLayoutX(x+xDelta*(counter++));lastname.setLayoutY(y);
            city.setLayoutX(x+xDelta*(counter++)); city.setLayoutY(y);
            date.setLayoutX(x+xDelta*(counter++)); date.setLayoutY(y);

            counter = 1;
            y += yDelta;
            pane.getChildrenUnmodifiable().addAll(username,password,firstname,lastname,city,date);
        }
    }

    //endregion

    //region SETTERS-GETTERS

    /**
     * set the main controller
     * @param controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    //endregion
}

