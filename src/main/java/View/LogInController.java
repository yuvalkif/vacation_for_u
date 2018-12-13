package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.AUserData;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LogInController {

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

    public void setImageParameters(){
        img_backLogIn.fitWidthProperty().bind((mainpane.getScene().getWindow()).widthProperty());
        img_backLogIn.fitHeightProperty().bind((mainpane.getScene().getWindow()).heightProperty());
    }
}
