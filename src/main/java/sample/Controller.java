//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample;

import Logger.Logger;
import Model.ISQLModel;
import Model.Model;
import View.IView;
import java.sql.Date;
import java.util.List;

import View.User;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Controller {
    private IView view;
    private ISQLModel model;
    private Stage primaryStage;

    public Controller() {
    }

    public void createUsersTable() {
        this.model.createUsersTable();
    }

    public void handleSubmitSignIn(User submit) {
        if (submit != null) {
            try {
                //this.model.insert(submit.getUserName(), submit.getPassword(), submit.getFirstName(), submit.getLastName(), submit.getCity(), (Date)null);
                model.createUser(submit);
            } catch (NullPointerException var3) {
                Logger.getInstance().log("NULL RECORD");
            }

        }
    }

    public void updateUser(String username, String newUserName, String password, String firstName, String lastName, String city, String date){
        this.model.updateUsers(username,newUserName,password,firstName,lastName,city,date);

    }

    public List<User> searchAllRecordsByFields(User fields) {
        return fields == null ? null : this.model.searchRecordsByFields(fields);
    }

    public ObservableList searchInDataBase(User user) {
        return ((Model)this.model).searchRecordsByFields(user);
    }

    public ObservableList getAllDataBase() {
        return ((Model)this.model).selectAllDataBase();
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
}
