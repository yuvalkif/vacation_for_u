package View;

import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * controller class for the update scene. controlled by 'UpdateController.fxml'
 */

public class UpdateController{


    @FXML
    public TextField username ,newUserName, password , firstname , lastname , city , date;
    private String sUserName="",sNewUserName="",sPassword="",sFirstName="",sLastName="",sCity="",sDate="";

    public void handleExecuteUpdate(){
        sUserName = username.getText();
        sNewUserName = newUserName.getText();
        sPassword = password.getText();
        sFirstName = firstname.getText();
        sLastName = lastname.getText();
        sCity = city.getText();
        sDate = date.getText();

        if(sUserName.equals("") || sUserName.trim().isEmpty()){
            ErrorBox box = new ErrorBox();
            Stage stage = box.getErrorBoxStage("Please pick a username to be updated");
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
        }

        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

    public String getsUserName() {
        return sUserName;
    }

    public String getsNewUserName() {
        return sNewUserName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public String getsFirstName() {
        return sFirstName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public String getsCity() {
        return sCity;
    }

    public String getsDate() {
        return sDate;
    }
}
