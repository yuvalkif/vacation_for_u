package View;

import Control.Controller;
import Logger.StageHolder;
import dbObjects.User;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;

public class MainScreenController implements IView{

    public ImageView img_backImg;
    private Controller controller;
    private Stage primaryStage;

    public void setCurrentStage(Stage stage) {
        this.primaryStage = stage;
        img_backImg.fitWidthProperty().bind(primaryStage.widthProperty());
        img_backImg.fitHeightProperty().bind(primaryStage.heightProperty());
    }

    public void handleSignUp() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("SignUpForm.fxml").openStream());
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

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }

    }

    public void handleSignIn() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("LogInWindow.fxml").openStream());
            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            LogInController sceneController = (LogInController)loader.getController();
            sceneController.setController(controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();

            //DO SOMETHING ABOUT LOGGING IN



        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handleSearch(){

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

            stage.showAndWait();
            this.controller.insertVacation(vacationFormController.getVacationToInsert());

        }catch (Exception e){
            System.out.println("at handle vacation button");
            e.printStackTrace();
        }
    }

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

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void handleXPress(){
        StageHolder.getInstance().getStage();
    }

}
