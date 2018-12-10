package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.scene.control.TextField;

public class LogInController {

    public TextField txtfld_userName;
    public TextField txtfld_password;
    private Controller controller;

    public void LogInClick() {
        String username = txtfld_userName.getText();
        String password = txtfld_password.getText();
        if(username.equals("") || password.equals("")) {
            ErrorBox e = new ErrorBox();
                    e.showErrorStage("you must fill user name and password");
            return;
        }
        if(controller.correctUserAndPassword(username,password)==null){
            ErrorBox e = new ErrorBox();
            e.showErrorStage("username or password incorrect");
            return;
        }
        //USER SHOULD BE LOGGED IN RIGHT NOW
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }
}
