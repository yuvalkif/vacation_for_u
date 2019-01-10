package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.RegisteredUser;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * controller class for the sign up scene . controlled by 'SignUpForm.fxml'
 */

public class UserSignUpController implements ISubController{
    private Controller controller;
    private RegisteredUser toSubmit;
    @FXML
    public TextField username, password, firstname, lastname, city, email;
    public DatePicker datepk_age;
    public ImageView img_backSignUp;
    public AnchorPane mainpane;

    public void handleSubmit() {
        String date="";
        if(datepk_age.getValue()==null){
            raiseError("Must fill all the fields");
            return;
        }
        else {
            LocalDate l = datepk_age.getValue();
            if(Period.between(l,LocalDate.now()).getYears()<18){
                raiseError("You must be at least 18 years old");
                return;
            }
            date = datepk_age.getValue().toString();
        }

        this.toSubmit = new RegisteredUser(username.getText(), password.getText(), firstname.getText(), lastname.getText(), city.getText(),date,email.getText());

        if (toSubmit.hasNullField() || datepk_age.getValue()==null) {
            raiseError("Must fill all the fields");
            toSubmit=null;
            return;
        }

        boolean goodEmail = false;
        String theEmail = email.getText();
        for (int i=0;i<theEmail.length();i++){
            if (theEmail.charAt(i)=='@')
                goodEmail=true;
        }

        if (!goodEmail) {
            raiseError("Please enter valid E-mail");
            toSubmit=null;
            return;
        }

        if(password.getText().length()<8){
            raiseError("Password must be at least 8 characters");
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

    public RegisteredUser getToSubmit() {
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

    public void setDateInitial(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("01-01-1991", formatter);
        datepk_age.setValue(localDate);
    }
}
