package View;

import Control.Controller;
import Logger.StageHolder;
import dbObjects.User;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;

public class MainScreenController implements IView{

    public ImageView img_backImg;
    public Tab tab_user;
    public Label lbl_SignUp;
    public Button btn_newVacation;
    public Button btn_serchUser;
    public Button btn_update;
    public Button btn_delete;
    private Controller controller;
    private Stage primaryStage;
    public Label lbl_userName;
    public Label lbl_hello;
    public Label lbl_signIn;


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
                this.controller.handleSubmitSignUp(toSubmit);

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

            if(!lbl_userName.getText().equals("")) {
                lbl_userName.setVisible(true);
                lbl_hello.setVisible(true);
                lbl_signIn.setVisible(false);
                lbl_SignUp.setVisible(false);
                tab_user.setDisable(false);
            }

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handleSearch(){

    }

    public void handleDelete(){
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("DeleteForm.fxml").openStream());
            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Delete");
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


    public void setController(Controller controller) {
        this.controller = controller;
        lbl_userName.textProperty().bind(controller.getLoggedUserProperty());

    }

    private void handleXPress(){
        StageHolder.getInstance().getStage();
    }
}
