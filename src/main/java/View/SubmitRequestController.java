package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SubmitRequestController {
    public TextField tb_vacationID;
    public TextField tb_userName;
    public TextField tb_price;

    public DatePicker tb_date;
    public TextField tb_toFillVisaNumber;
    public TextField tb_toFillNameOnCard;
    public TextField tb_toFillCVV;
    public ChoiceBox tb_toFillType;
    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
        setViza();
    }

    public void setViza() {
        ObservableList<String> channelItems = FXCollections.observableArrayList("VISA", "MasterCard", "IsraCard");

        tb_toFillType.setItems(channelItems);
        tb_toFillType.getSelectionModel().selectFirst();
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }

    public void submit(String loggedUser, String vacationID, Double price) {
        tb_vacationID.setDisable(true);
        tb_userName.setDisable(true);
        tb_price.setDisable(true);
        tb_vacationID.setText(""+vacationID);
        tb_userName.setText(loggedUser);
        tb_price.setText(""+price);

    }

    public void clickSubmit(){
        String chack = tb_toFillCVV.getText();
        try {
            int f = Integer.parseInt(chack);
        }catch (Exception e){
            ErrorBox errorBox = new ErrorBox();
            errorBox.showErrorStage("CVV shule be only numbers");
            return;
        }
        if (chack.equals("") || (chack.length() != 3)){
            ErrorBox e = new ErrorBox();
            e.showErrorStage("Worng CVV");
            return;
        }
        chack = tb_toFillNameOnCard.getText();
        if (chack.equals("")) {
            ErrorBox e = new ErrorBox();
            e.showErrorStage("Fill your full name");
            return;
        }
        chack=tb_toFillVisaNumber.getText();
        try {
            Long.parseLong(chack);
        }catch (Exception e){
            ErrorBox errorBox = new ErrorBox();
            errorBox.showErrorStage("Not a number");
            return;
        }
        if (chack.equals("")|| chack.length()!=16) {
            ErrorBox e = new ErrorBox();
            e.showErrorStage("The number should be 16 digits");
            return;
        }
        if (tb_date.getValue().toString().equals("")){
            ErrorBox e = new ErrorBox();
            e.showErrorStage("Choose a date ");
            return;
        }
        Purchase p = new Purchase(controller.getLoggedUser(),tb_toFillNameOnCard.getText(), tb_toFillType.getSelectionModel().toString(), tb_toFillVisaNumber.getText(), tb_toFillCVV.getText() , java.sql.Date.valueOf(tb_date.getValue()),tb_vacationID.getText());
        controller.insertBuyingRequest(p);
        ErrorBox e = new ErrorBox();
        e.showErrorStage("We have sent your request to the seller,\n he will let you know :)");
        StageHolder.getInstance().getStage().close();
    }





}
