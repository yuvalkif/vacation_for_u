package View;

import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Controller;

/**
 * controller class for the sign up scene . controlled by 'SignUpForm.fxml'
 */

public class SignUpFormController {

    private Controller controller;
   private User toSubmit;
   @FXML
   public TextField username , password , firstname , lastname , city , date;

    public void handleSubmit(){
        this.toSubmit = new User(username.getText(),password.getText(),firstname.getText(),lastname.getText(),city.getText(),date.getText());

        if(toSubmit.hasNullField()){
            ErrorBox box = new ErrorBox();
            Stage errorBoxStage = box.getErrorBoxStage("Must fill all fields");
            StageHolder.getInstance().holdStage(errorBoxStage);
            errorBoxStage.showAndWait();
            return;
        }
        if(controller.searchInDataBase(toSubmit).size()>0)
        {
            ErrorBox box = new ErrorBox();
            Stage errorBoxStage = box.getErrorBoxStage("Username exists");
            StageHolder.getInstance().holdStage(errorBoxStage);
            errorBoxStage.showAndWait();
            return;
        }
        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

    public User getToSubmit() {
        return toSubmit;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
