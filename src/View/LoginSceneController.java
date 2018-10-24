package View;

import Logger.StageHolder;
import Objects.Record;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class LoginSceneController{

   private Record toSubmit;
   @FXML
   public TextField username , password , firstname , lastname , city , date;

    public void handleSubmit(){
        this.toSubmit = new Record(username.getText(),password.getText(),firstname.getText(),lastname.getText(),city.getText());
        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){

    }

    public Record getToSubmit() {
        return toSubmit;
    }
}
