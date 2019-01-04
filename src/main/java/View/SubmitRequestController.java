package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.Purchase;
import javafx.scene.control.*;

public class SubmitRequestController {
    public TextField tb_vacationID;
    public TextField tb_userName;
    public TextField tb_price;
    public Controller controller;
    public TextField tb_vacationID1;
    public TextField tb_userName1;
    public ListView lv_toTrade;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }

    public void submit(String loggedUser, String vacationID, Double price) {
        tb_vacationID.setDisable(true);
        tb_vacationID1.setDisable(true);
        tb_userName.setDisable(true);
        tb_userName1.setDisable(true);
        tb_price.setDisable(true);
        tb_vacationID.setText(""+vacationID);
        tb_vacationID1.setText(""+vacationID);
        tb_userName.setText(loggedUser);
        tb_userName1.setText(loggedUser);
        tb_price.setText(""+price);
        lv_toTrade.getItems().addAll(controller.getUserVacations());
    }

    public void clickSubmitCash(){
        //CREATE A NEW CASH REQUEST
        //Purchase p = new Purchase(controller.getLoggedUser(),tb_toFillNameOnCard.getText(), tb_toFillType.getSelectionModel().toString(), tb_toFillVisaNumber.getText(), tb_toFillCVV.getText() , java.sql.Date.valueOf(tb_date.getValue()),tb_vacationID.getText());
        //controller.insertOfferRequest(p);
        ErrorBox e = new ErrorBox();
        e.showErrorStage("We have sent your request to the seller,\n he will let you know :)");
        StageHolder.getInstance().getStage().close();
    }


    public void clickSubmitTrade() {
        String vacationID = (String)lv_toTrade.getSelectionModel().getSelectedItem();
        if(vacationID==null) {
            ErrorBox e = new ErrorBox();
            e.showErrorStage("Please choose a vacation to trade");
            return;
        }
        //MAKE A TRADE REQUEST
        //Purchase p = new Purchase(controller.getLoggedUser(),tb_toFillNameOnCard.getText(), tb_toFillType.getSelectionModel().toString(), tb_toFillVisaNumber.getText(), tb_toFillCVV.getText() , java.sql.Date.valueOf(tb_date.getValue()),tb_vacationID.getText());
        //controller.insertBuyingRequest(p);
        ErrorBox e = new ErrorBox();
        e.showErrorStage("We have sent your request to the seller,\n he will let you know :)");
        StageHolder.getInstance().getStage().close();
    }
}
