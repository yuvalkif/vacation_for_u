package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.Purchase;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SubmitRequestController {
    public TextField tb_vacationID;
    public TextField tb_userName;
    public TextField tb_price;
    public TextField tb_toFill3;
    public TextField tb_toFill2;
    public TextField tb_toFill1;
    public TextField tb_toFill4;
    public DatePicker tb_date;
    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }

    public void submit(String loggedUser, int vacationID, Double price) {
        tb_vacationID.setDisable(true);
        tb_userName.setDisable(true);
        tb_price.setDisable(true);
        tb_vacationID.setText(""+vacationID);
        tb_userName.setText(loggedUser);
        tb_price.setText(""+price);

    }

    public void clickSubmit(){
        if (tb_toFill3.getText().equals("") ||tb_toFill2.getText().equals("")||tb_toFill1.getText().equals("")||tb_toFill4.getText().equals("") ||tb_date.getValue().toString().equals("")){
            ErrorBox e = new ErrorBox();
            e.showErrorStage("fill all of your details");
            return;
        }
        Purchase p = new Purchase(controller.getLoggedUser(),tb_toFill3.getText(),tb_toFill4.getText(),tb_toFill1.getText(),tb_toFill2.getText(),java.sql.Date.valueOf(tb_date.getValue()),Integer.parseInt(tb_vacationID.getText()));
        controller.insertOfferRequest(p);
        StageHolder.getInstance().getStage().close();

    }





}
