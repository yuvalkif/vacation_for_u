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
import javafx.collections.ObservableList;

public class Controller {
    private IView view;
    private ISQLModel model;

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

    public void handleSubmitSignIn(User submit) {
        if (submit != null) {
            try {
                model.insertUser(submit);
            } catch (NullPointerException var3) {
                Logger.getInstance().log("NULL RECORD");
            }

        }
    }

    public void updateUser(String username, String newUserName, String password, String firstName, String lastName, String city, String date){
        this.model.updateUsers(username,newUserName,password,firstName,lastName,city,date);

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

    public void deleteUser(String username){
        this.model.deleteUsers(username);
    }

    public void setView(IView view) {
        this.view = view;
    }

    public void setModel(ISQLModel model) {
        this.model = model;
    }

    public AUserData correctUserAndPassword(String username, String password){
        return model.login(username,password);
    }
}
