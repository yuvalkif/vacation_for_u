package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * controller class for the update scene. controlled by 'UpdateFormController.fxml'
 */

public class UpdateFormController {
    private User user ;
    private Controller controller ;
    @FXML
    public TextField username ,newUserName, password , firstname , lastname , city , date;
    private String sUserName="",sNewUserName="",sPassword="",sFirstName="",sLastName="",sCity="",sDate="";

    public void handleExecuteUpdate() {
        user = new User(sNewUserName = newUserName.getText(), sPassword = password.getText(), sFirstName = firstname.getText(), sLastName = lastname.getText(),
                sCity = city.getText(), sDate = date.getText());

        sUserName = username.getText();

        if (sUserName.equals("")) {
            showError("Please enter a username to be updated");
            return;
        }

        if (user.nullRecord()) {
            showError("Please fill atleast 1 field \n" + "to be updated");
            return;
        }

        ObservableList result = controller.searchInDataBase(user);
        if (result.size() > 0) {
            showError("Username already exists. please choose\n" +
                    "a new one");
            return;
        }
        //date valid check

        if(user.getDate().length()>0 && !isValidDate(user.getDate())){
            showError("Please insert a valid date of format YYYY-MM-DD");
            return;
        }

            //the update if all ok
            this.controller.updateUser(sUserName, user.getUsername(), user.getpPassword(), user.getFirstname(), user.getLastname(),
                    user.getCity(), user.getDate());

            StageHolder.getInstance().getStage().close();
        }



    private void showError(String msg){
        ErrorBox box = new ErrorBox();
        box.showErrorStage(msg);
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

    public void setController(Controller controller){
        this.controller = controller;
    }


    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


}
