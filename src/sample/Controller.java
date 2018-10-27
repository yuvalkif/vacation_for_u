package sample;

/**
 * Controller class
 */

import Logger.Logger;
import Model.*;
import Objects.Record;
import View.IView;
import javafx.stage.Stage;

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
    public void handleSubmitSignIn(Record submit){

        if(submit == null)
            return ;

        try {
            this.model.insert(submit.getUsername(), submit.getPassword(), submit.getFirstname(), submit.getLastname(), submit.getCity(), null);
        }catch (NullPointerException e){
            Logger.getInstance().log("NULL RECORD");
        }
    }

    public List<Record> searchAllRecordsByFields(Record fields){
        if(fields == null)
            return null ;

        return this.model.searchRecordsByFields(fields);
    }

    //endregion



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
