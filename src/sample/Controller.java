package sample;

/**
 * Controller class
 */

import Logger.Logger;
import Model.*;
import Objects.User;
import View.IView;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private IView view;
    private ISQLModel model;
    private Stage primaryStage;

    public Controller(){
    }

    public void createUsersTable(){
        model.createUsersTable();
    }

    //region USER ACTIONS
    public void handleSubmitSignIn(User submit){

        if(submit == null)
            return ;

        try {
            ((Model)this.model).createUser(submit);
//            this.model.insert(submit.getUsername(), submit.getPassword(), submit.getFirstname(), submit.getLastname(), submit.getCity(), null);
//
        }catch (NullPointerException e){
            Logger.getInstance().log("NULL RECORD");
        }
    }

//    public List<User> searchAllRecordsByFields(User fields){
//        if(fields == null)
//            return null ;
//
//        return this.model.searchRecordsByFields(fields);
//    }

    public void exFind(String userName){
        if(userName == null)
            return;
        ((Model)this.model).findUser(userName);
    }

    //endregion

    public ArrayList<User> findUser(String userName){
        return ((Model)this.model).findUser(userName);
    }



    //region PROGRAM FLOW

    /**
     * set MVC connections
     */
    public void setAll(){
        view.setController(this);
        model.setController(this);
    }

    /**
     * set the view of this controller
     * @param view
     */
    public void setView(IView view){
        this.view = view;
    }

    public void setModel(ISQLModel model){
        this.model = model;
    }
    //endregion
}
