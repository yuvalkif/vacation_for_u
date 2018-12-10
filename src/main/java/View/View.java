package View;

import Logger.StageHolder;
import java.io.IOException;

import dbObjects.User;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Control.Controller;
import javafx.stage.WindowEvent;

public class View implements IView {
    private Controller controller;
    private Stage primaryStage;

    @FXML
    private AnchorPane mainPane ;

    public View() {
    }

    public void setCurrentStage(Stage stage) {
        this.primaryStage = stage;
    }

    //region SCENES

    /*public void signInScene() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("SignUpForm.fxml").openStream());
            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            SignUpFormController sceneController = (SignUpFormController)loader.getController();
            sceneController.setController(controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();
            User toSubmit = sceneController.getToSubmit();

            if( toSubmit != null && !sceneController.getToSubmit().hasNullField())
                this.controller.handleSubmitSignIn(toSubmit);

        } catch (IOException var6) {
            var6.getCause();
            var6.printStackTrace();
        }

    }*/

    public void handleRead() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("SearchForm.fxml").openStream());
            SplitPane splitPane = (SplitPane)root.getChildrenUnmodifiable().get(0);
            AnchorPane upperPane = (AnchorPane)splitPane.getItems().get(0);
            TableView<User> tableView = (TableView<User>)upperPane.getChildren().get(0);
            Scene scene = new Scene(root, 570, 550);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Search");
            stage.setResizable(false);
            SearchFormController searchFormController = (SearchFormController)loader.getController();
            searchFormController.setController(controller);
            searchFormController.setTableView(tableView);
            searchFormController.setController(this.controller);
            StageHolder.getInstance().holdStage(stage);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }

    public void handleUpdate() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("UpdateForm.fxml").openStream());
            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Update");
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            UpdateFormController uc = (UpdateFormController) loader.getController();
            uc.setController(this.controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();

        } catch (IOException var6) {
            var6.getCause();
            var6.printStackTrace();
        }

    }

    public void handleVacationButton(){
        FXMLLoader loader = new FXMLLoader();
        try{
            loader.load(getClass().getClassLoader().getResource("PublishVacationForm.fxml").openStream());
            Stage stage = initializeNewStage("PublishVacationForm.fxml","Forms.css","Vacation",false,650,500);
            VacationFormController vacationFormController =(VacationFormController) loader.getController();
            vacationFormController.setController(this.controller);
            StageHolder.getInstance().holdStage(stage);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });

            //vacationFormController.getVacation();

            stage.showAndWait();
            this.controller.insertVacation(vacationFormController.getVacationToInsert());

        }catch (Exception e){
            System.out.println("at handle vacation button");
            e.printStackTrace();
        }
    }


    /*public void handleDelete(){
        FXMLLoader loader = new FXMLLoader();
        try{
            Parent root = loader.load(getClass().getClassLoader().getResource("DeleteForm.fxml").openStream());
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Delete");
            stage.setScene(scene);
            DeleteFormController deleteFormController = loader.getController();
            deleteFormController.setController(this.controller);
            StageHolder.getInstance().holdStage(stage);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            String username = deleteFormController.getUsername();
            if(username != null && !username.equals(""))
                controller.deleteUser(username);
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/

    //endregion

    /**
     * initialize and return a new stage
     * @param fxmlPath path to fxml file of the stage
     * @param cssPath path to css of the stage
     * @param title the title to be shown
     * @param resizeable true or false
     * @return a stage initialized with all the parameters
     */
    private Stage initializeNewStage(String fxmlPath , String cssPath , String title , boolean resizeable , double width , double height){

        FXMLLoader loader = new FXMLLoader();
        try{
            Parent root = loader.load(getClass().getClassLoader().getResource(fxmlPath).openStream());
            Scene scene = new Scene(root,width,height);
            scene.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(resizeable);

            return stage;
        }catch (Exception e){
            return null;
        }
    }

    public ObservableList<User> getSearchResultsFromController(User user) {
        return this.controller.searchInDataBase(user);
    }

    public ObservableList getAllDataBase() {
        return this.controller.getAllDataBase();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void handleXPress(){
        StageHolder.getInstance().getStage();
    }
}
