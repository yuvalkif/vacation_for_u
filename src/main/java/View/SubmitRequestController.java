package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.beans.property.StringProperty;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SubmitRequestController {
    public TextField tb_vacationID;
    public TextField tb_userName;
    public TextField tb_price;
    public PasswordField tb_toFill3;
    public TextField tb_toFill2;
    public TextField tb_toFill1;
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
        if (tb_toFill3.getText().equals("") ||tb_toFill2.getText().equals("")||tb_toFill1.getText().equals("")){
            ErrorBox e = new ErrorBox();
            e.showErrorStage("fill all of your details");
            return;
        }
        if (!tb_toFill3.getText().equals("") &&!tb_toFill2.getText().equals("")&&!tb_toFill1.getText().equals("")){

        }
    }



}
