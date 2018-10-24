package sample;

/**
 * Controller class
 */

import java.sql.Connection;
import Connections.sqlLiteJDBCDriverConnection;
import Model.*;
import Objects.Record;
import View.IView;
import javafx.stage.Stage;

public class Controller {

    private IView view;
    private ISQLModel model;
    private sqlLiteJDBCDriverConnection driver ;
    private Stage primaryStage;

    public Controller(){
        this.driver = new sqlLiteJDBCDriverConnection();
    }

    //region USER ACTIONS
    public void handleSubmitSignIn(Record submit){
        this.model.insert(submit.getUsername(),submit.getPassword(),submit.getFirstname(),submit.getLastname(),submit.getCity(),null);
        System.out.println("submit = [" + submit + "]");
    }

    //endregion

    //region SETS
    /**
     * set MVC connections
     */
    public void setAll(){
        view.setController(this);
        view.setModel(this.model);
        model.setController(this);
        model.setView(view);
    }

    /**
     * set the view of this controller
     * @param view
     */
    public void setView(IView view){
        this.view = view;
    }

    /**
     * set the model of this controller
     * @param model
     */
    public void setModel(ISQLModel model) {
        this.model = model;
    }
    //endregion

    //region CONNECTIONS
    /**
     * open a connection to the database and log it
     * @return new connection
     */
    public Connection openConnection(){

        Connection conn = driver.connect();
        Logger.Logger.getInstance().log("connection opened" + System.currentTimeMillis());
        return conn;
    }

    /**
     * close the connection to the database and log it
     */
    public void closeConnection(){
        this.driver.closeConnection();
        Logger.Logger.getInstance().log("connection closed" + System.currentTimeMillis());
    }

    //endregion
}
