package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.BuyingRequest;
import dbObjects.Purchase;
import dbObjects.TradeRequest;
import dbObjects.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SubmitRequestController {
    public Controller controller;

    public TextField tb_vacationID;
    public TextField tb_userName;
    public TextField tb_price;
    public TextField tb_vacationID1;
    public TextField tb_userName1;
    public ChoiceBox<String> cb_tradeVacation;

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
        ObservableList<Vacation> userVacations= controller.getUserVacations();
        ObservableList<String> vacationIDAndDest = getVacationsData(userVacations);
        cb_tradeVacation.getItems().addAll(vacationIDAndDest);
    }

    private ObservableList<String> getVacationsData(ObservableList<Vacation> userVacations) {
        ObservableList<String> result = FXCollections.observableArrayList();
        for (Vacation v : userVacations) {
            result.add(v.getVacationID()+ " " + v.getDestination());
        }
        return result;
    }

    public void clickSubmitCash(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        BuyingRequest br = new BuyingRequest(controller.getLoggedUser(),controller.getVacationAsObjectById(tb_vacationID.getText()),LocalDateTime.now().format(formatter));
        controller.insertBuyingRequest(br);
        ErrorBox e = new ErrorBox();
        e.showErrorStage("We have sent your request to the seller,\n he will let you know :)");
        StageHolder.getInstance().getStage().close();
    }


    public void clickSubmitTrade() {
        String vacationID = cb_tradeVacation.getSelectionModel().getSelectedItem();
        if(vacationID==null) {
            ErrorBox e = new ErrorBox();
            e.showErrorStage("Please choose a vacation to trade");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TradeRequest tr = new TradeRequest(controller.getLoggedUser(),LocalDateTime.now().format(formatter),controller.getVacationAsObjectById(vacationID.substring(0,vacationID.indexOf(" "))),controller.getVacationAsObjectById(tb_vacationID.getText()));
        controller.insertTradeRequest(tr);
        ErrorBox e = new ErrorBox();
        e.showErrorStage("We have sent your request to the seller,\n he will let you know :)");
        StageHolder.getInstance().getStage().close();
    }
}