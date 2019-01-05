package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.AUserData;
import dbObjects.RegisteredUser;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LogInController implements ISubController{

    public TextField txtfld_userName;
    public TextField txtfld_password;
    public ImageView img_backLogIn;
    public AnchorPane mainpane;
    private Controller controller;
    private AUserData userData;

    public void LogInClick() {
        String username = txtfld_userName.getText();
        String password = txtfld_password.getText();
        if(username.equals("") || password.equals("")) {
            ErrorBox e = new ErrorBox();
                    e.showErrorStage("you must fill user name and password");
            return;
        }
        AUserData userData = controller.correctUserAndPassword(username,password);
        if(userData == null){
            ErrorBox e = new ErrorBox();
            e.showErrorStage("username or password incorrect");
            return;
        }
        this.userData = userData;
        StageHolder.getInstance().getStage().close();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }

    public AUserData getUserData() {
        return userData;
    }

    public void handleSingUp(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("SignUpForm.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.setResizable(false);
            StageHolder.getInstance().holdStage(stage);
            SignUpFormController sceneController = (SignUpFormController)loader.getController();
            sceneController.setDateInitial();
            sceneController.setController(controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            RegisteredUser toSubmit = sceneController.getToSubmit();

            if( toSubmit != null && !sceneController.getToSubmit().hasNullField())
                this.controller.handleSubmitSignUp(toSubmit);

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }

    }


    private void handleXPress(){
        StageHolder.getInstance().getStage();
    }


}
