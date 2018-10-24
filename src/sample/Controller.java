package sample;

/**
 * Controller class
 */

import java.sql.Connection;
import Connections.sqlLiteJDBCDriverConnection;
import Model.*;
import View.IView;

public class Controller {

    private IView view;
    private ISQLModel model;
    private sqlLiteJDBCDriverConnection driver ;

    public Controller(){
        this.driver = new sqlLiteJDBCDriverConnection();
    }


    public void insertUser(){

    }

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
}
