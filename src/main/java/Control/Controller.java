//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Control;

import Logger.Logger;
import Model.ISQLModel;
import Model.Model;
import View.IView;
import dbObjects.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Controller {
    private IView view;
    private ISQLModel model;
    private String loggedUser;
    private StringProperty sp_loggedUser;

    public Controller() {


    }

    /********************************SET ACTIONS**************************/
    public void setAll() {
        this.view.setController(this);
        this.model.setController(this);
    }

    //public void setModel(ISQLModel model) {
      //  this.model = model;
       // sp_loggedUser = new SimpleStringProperty("" + model.getLoggedUser());
   // }

    public void setView(IView view) {
        this.view = view;
    }

    /********************************TABLES CREATE**************************/
    public void createUsersTable() {
        this.model.createUsersTable();
    }

    public void createVacationsTable() {
        this.model.createVacationsTable();
    }

    public void createPurchaseTable() {
        this.model.createPurchaseTable();
    }

    public void createConfirmMessageTable() {
        this.model.createConfirmMessageTable();
    }

    public void createOffersTable() {
        model.createCashRequestsTable();
    }

    public void createCreditCardTable() {
        model.createCreditCardPoolTable();
    }

    public void createTradeRequestTable(){
        model.createTradeRequestsTable();
    }

    /********************************USER ACTIONS**************************/
    public void handleSubmitSignUp(RegisteredUser submit) {
        if (submit != null) {
            try {
                model.insertUser(submit);
            } catch (NullPointerException var3) {
                Logger.getInstance().log("NULL RECORD");
            }

        }
    }

    public void updateUser(String newUserName, String password, String firstName, String lastName, String city, String date) {
        this.model.updateUsers(loggedUser, newUserName, password, firstName, lastName, city, date);

    }

    public void deleteUser() {
        this.model.deleteUsers(loggedUser);
    }

    public ObservableList<RegisteredUser> searchInDataBase(RegisteredUser user) {
        return ((Model) this.model).searchRecordsByFields(user.getUserName());
    }

    public ObservableList searchInDataBase(String username) {
        return (ObservableList) model.searchRecordsByFields(username);
    }

    public ObservableList getAllDataBase() {
        return this.model.selectAllDataBase();
    }

    public AUserData correctUserAndPassword(String username, String password) {
        AUserData logged = model.login(username, password);
        if (logged == null)
            return null;
        loggedUser = logged.getUserName();
        sp_loggedUser.set(loggedUser);
        return logged;
    }

    public String getLoggedUser() {
        return sp_loggedUser.get();
    }

    public StringProperty getLoggedUserProperty() {
        return sp_loggedUser;
    }

    public void signOut() {
        loggedUser = "";
        sp_loggedUser.set("");
    }

    public UserData getCurrentUserData() {
        return (UserData) this.model.getUserData(loggedUser);
    }

    /********************************VACATION ACTIONS**************************/
    public ObservableList getAllVacations() {
        return this.model.getAllVacations();
    }

    public ObservableList<Vacation> getUserVacations() {
        return model.getUserVacations();
    }

    public void insertVacation(Vacation vacation) {
        this.model.insertVacation(vacation);
    }

    public ObservableList searchVacationInDB(String dest) {
        return model.getVacations(dest);
    }

    public Vacation getVacationAsObjectById(String vacationId){
        return model.getVacationAsObjectById(vacationId);
    }

    /********************************MESSAGE ACTIONS**************************/
    public void confirmOrderMassage(ConfirmOfferMessage msg) {
        this.model.acceptMessage(msg);
    }

//    public void insertBuyingRequest(CashRequest buyingRequest){
//        model.insertBuyingRequest(buyingRequest.get,p.get(),p);
//    }

    /********************************OTHERS**************************/
    public boolean containsTicketID(String ticketID) {
        return model.checkTicketExist(ticketID);
    }

    /*************************************REQUESTS****************************/
    public void insertTradeRequest(TradeRequest tr) {
        model.insertTradeRequests(tr.getRequestedVacation().getVacationID(),tr.getAskerVacationHeWantsToTrade().getVacationID());
    }

    public void insertBuyingRequest(CashRequest br){
        model.insertCashRequest(br.getRequestedVacation().getVacationID());
    }
}
