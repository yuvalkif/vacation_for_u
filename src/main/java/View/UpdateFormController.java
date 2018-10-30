package View;

import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * controller class for the update scene. controlled by 'UpdateFormController.fxml'
 */

public class UpdateFormController {


    @FXML
    public TextField username ,newUserName, password , firstname , lastname , city , date;
    private String sUserName="",sNewUserName="",sPassword="",sFirstName="",sLastName="",sCity="",sDate="";
    private User user ;

    public void handleExecuteUpdate(){
        user = new User(sNewUserName = newUserName.getText(),sPassword = password.getText(),sFirstName = firstname.getText(),sLastName = lastname.getText(),
                sCity = city.getText(), sDate = date.getText());

        sUserName = username.getText();

        if(sUserName.equals("")){
            showError("Please enter a username to be updated");
            return;
        }

        if(user.nullRecord()) {
            showError("Please enter atleast 1 field \n" + "to be updated");
            return;
        }

//        if(sNewUserName.trim().isEmpty()){
//            showError("Please enter a userName to update");
//        }
        StageHolder.getInstance().getStage().close();
    }


    private void showError(String msg){
        ErrorBox box = new ErrorBox();
        Stage stage = box.getErrorBoxStage(msg);
        StageHolder.getInstance().holdStage(stage);
        stage.showAndWait();
    }


    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

    public String getsUserName() {
        return sUserName;
    }

    public User getUser(){
        return this.user;
    }
}
