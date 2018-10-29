package View;

import Logger.StageHolder;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class LoginSceneController{

   private User toSubmit;
   @FXML
   public TextField username , password , firstname , lastname , city , date;

    public void handleSubmit(){
        this.toSubmit = new User(username.getText(),password.getText(),firstname.getText(),lastname.getText(),city.getText(),date.getText());
        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

    public User getToSubmit() {
        return toSubmit;
    }
}
