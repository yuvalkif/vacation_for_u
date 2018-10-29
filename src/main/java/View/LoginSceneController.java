package View;

import Logger.StageHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginSceneController{

   private User toSubmit;
   @FXML
   public TextField username , password , firstname , lastname , city , date;

    public void handleSubmit(){
        if(!username.getText().equals("") && !password.getText().equals("") && !firstname.getText().equals("")&&!lastname.getText().equals("")&&!city.getText().equals("")&&!date.getText().equals("")) {
            String dateCheck = date.getText();
            if( dateCheck.length()==10 && dateCheck.charAt(2)=='-' && dateCheck.charAt(5)=='-') {
                try {
                    Integer.parseInt(dateCheck.substring(0, 1));
                    Integer.parseInt(dateCheck.substring(2, 4));
                    Integer.parseInt(dateCheck.substring(6, 9));
                    this.toSubmit = new User(username.getText(), password.getText(), date.getText(),firstname.getText(), lastname.getText(), city.getText());
                    StageHolder.getInstance().getStage().close();
                }
                catch(Exception e) {
                    new Alert(Alert.AlertType.WARNING, "INVALID DATE").show();
                }
            }
            else {
                new Alert(Alert.AlertType.WARNING, "INVALID DATE").show();
            }
        }
        else
            new Alert(Alert.AlertType.WARNING, "FILL ALL THE BLANKS").show();


    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

    public User getToSubmit() {
        return toSubmit;
    }
}
