package View;

import Logger.StageHolder;
import Objects.User;
import Objects.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class UpdateController{


    @FXML
    public TextField username ,newUserName, password , firstname , lastname , city , date;
    private String sUserName,sNewUserName,sPassword,sFirstName,sLastName,sCity,sDate;

    public void handleExecuteUpdate(){
        sUserName = username.getText();
        sNewUserName = newUserName.getText();
        sPassword = password.getText();
        sFirstName = firstname.getText();
        sLastName = lastname.getText();
        sCity = city.getText();
        sDate = date.getText();
        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

//    public User getToSubmit() {
//        return toSubmit;
//    }


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
