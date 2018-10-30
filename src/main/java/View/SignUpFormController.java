package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * controller class for the sign up scene . controlled by 'SignUpForm.fxml'
 */

public class SignUpFormController {
    private Controller controller;
    private User toSubmit;
    @FXML
    public TextField username, password, firstname, lastname, city, date;

    public void handleSubmit() {
        this.toSubmit = new User(username.getText(), password.getText(), firstname.getText(), lastname.getText(), city.getText(), date.getText());

        if (toSubmit.hasNullField()) {
            raiseError("Must fill all the fields");
            toSubmit=null;
            return;
        }
        if(!isValidDate(toSubmit.getDate())){
            raiseError("Please insert a valid date of format YYYY-MM-DD");
            toSubmit=null;
            return;
        }
        if (controller.searchInDataBase(toSubmit).size() > 0) {//check if username already exists
            raiseError("Username already exists! Please choose \na new one");
            toSubmit=null;
            return;
        }
        if(!isValidDate(toSubmit.getDate())){
            raiseError("Please insert a valid date of format YYYY-MM-DD");
            toSubmit=null;
            return;
        }


        StageHolder.getInstance().getStage().close();
    }

    private void raiseError(String msg) {
        ErrorBox box = new ErrorBox();
        box.showErrorStage(msg);
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }

    public User getToSubmit() {
        return toSubmit;
    }

    public void setController(Controller controller) {
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
