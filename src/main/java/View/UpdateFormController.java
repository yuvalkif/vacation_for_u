package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.RegisteredUser;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * controller class for the update scene. controlled by 'UpdateFormController.fxml'
 */

public class UpdateFormController implements ISubController{
    private RegisteredUser user ;

    private String sUserName="",sNewUserName="",sPassword="",sFirstName="",sLastName="",sCity="",sDate="";
    private Controller controller ;
    @FXML
    public TextField newUserName, password , firstname , lastname , city , date;
    public AnchorPane mainpane;
    public ImageView img_backUpdateUser;

    public void handleExecuteUpdate() {
        user = new RegisteredUser(controller.getLoggedUser(), sPassword = password.getText(), sFirstName = firstname.getText(), sLastName = lastname.getText(),
                sCity = city.getText(), sDate = date.getText());

        if (user.nullRecord()) {
            showError("Please fill atleast 1 field \n" + "to be updated");
            user = null;
            return;
        }

        if(password.getText().length()<8){
            showError("Password must be atleast 8 characters");
            user = null;
            return;
        }
        /*ObservableList result = controller.searchInDataBase(user);
        if (result.size() > 0) {
            showError("Username already exists. please choose\n" +
                    "another one");
            user = null;
            return;
        }*/

        //date valid check
        if (user.getDate().length() > 0 && !isValidDate(user.getDate())) {
            showError("Please insert a valid date of format YYYY-MM-DD");
            user = null;
            return;
        }



        //the update if all ok
        this.controller.updateUser(controller.getLoggedUser(), user.getpPassword(), user.getFirstname(), user.getLastname(), user.getCity(), user.getDate());

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

    public RegisteredUser getUser(){
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
