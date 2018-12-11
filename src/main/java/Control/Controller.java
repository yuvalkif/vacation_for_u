//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Control;

import Logger.Logger;
import Model.ISQLModel;
import Model.Model;
import View.IView;
import dbObjects.AUserData;
import dbObjects.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import dbObjects.Vacation;
import javafx.collections.ObservableList;

public class Controller {
    private IView view;
    private ISQLModel model;
    private String loggedUser;
    private StringProperty sp_loggedUser;


    public Controller() {


    }

    public void createUsersTable() {
        this.model.createUsersTable();
    }
    public void createVacationsTable() {
        this.model.createVacationsTable();
    }
    public void createPurchaseTable() {
        this.model.createPurchaseTable();
    }
    public void  createConfirmMessageTable(){
        this.model.createConfirmMessageTable();
    }

    public void handleSubmitSignUp(User submit) {
        if (submit != null) {
            try {
                model.insertUser(submit);
            } catch (NullPointerException var3) {
                Logger.getInstance().log("NULL RECORD");
            }

        }
    }

    public void updateUser( String newUserName, String password, String firstName, String lastName, String city, String date){
        this.model.updateUsers(loggedUser,newUserName,password,firstName,lastName,city,date);

    }

    public ObservableList<User> searchInDataBase(User user) {
        return ((Model)this.model).searchRecordsByFields(user.getUsername());
    }

    public ObservableList searchInDataBase(String username){
        return (ObservableList) model.searchRecordsByFields(username);
    }

    public ObservableList getAllDataBase() {
        return this.model.selectAllDataBase();
    }

    public void setAll() {
        this.view.setController(this);
        this.model.setController(this);
    }

    public void deleteUser(){
        this.model.deleteUsers(loggedUser);
    }

    public void setView(IView view) {
        this.view = view;
    }

    public void setModel(ISQLModel model) {
        this.model = model;
        sp_loggedUser = new SimpleStringProperty(""+model.getLoggedUser());
    }

    public AUserData correctUserAndPassword(String username, String password){
        AUserData logged = model.login(username,password);
        if(logged==null)
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

    public void insertVacation(Vacation vacation){
        this.model.insertVacation(vacation);
    }

    public ObservableList searchVacationInDB(String dest) {
        return model.getVacations(dest);
    }

    public void signOut() {
        loggedUser="";
        sp_loggedUser.set("");
    }
}
