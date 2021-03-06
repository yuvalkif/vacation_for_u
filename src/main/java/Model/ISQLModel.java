package Model;

import dbObjects.*;
import Control.Controller;
import javafx.collections.ObservableList;


import java.util.List;


/**
 * Interface for sql models
 */

public interface ISQLModel {

    //change to generic CRUD interface

    void setController(Controller controller);

    /**
     * insert a record to the database
     */
    void insertUser(RegisteredUser user);

    /**
     * delete a user from the database
     * @param userName the username of the user as it appears in the database
     */
    void deleteUsers(String userName);

    /**
     * updates a record in the database .
     * @param username the old username for which to update fields
     * @param newUserName
     * @param password
     * @param firstName
     * @param lastName
     * @param city
     * @param Date
     */
    void updateUsers(String username,String newUserName  , String password , String firstName, String lastName, String city, String Date,  String email);

    /**
     * create a new table in the database
     */
    void createUsersTable();

    /**
     *  create a new vacations in the database
     */
    void createVacationsTable();

    /**
     *  create a new purchases in the database
     */
    void createPurchaseTable();

    void createCashRequestsTable();

    void createConfirmMessageTable();
    void createTradeRequestsTable();

    /**
     * search records by username
     * @param username
     * @return
     */


    List<RegisteredUser> searchRecordsByFields(String username);

    /**
     * return wither a user is logged in or not
     */

    AUserData login(String username, String password);

    /**
     *
     * @return all the db vacations
     */
    ObservableList getAllVacations();


    /**
     * VERY importent to keep the order of the fields and their values
     * @param dest destionation of the flight
     * @return  a collection of vacations that meet the criterias.
     */
    ObservableList getVacations(String dest);


    /**
     *
     * @param vacationValues vacation fields
     * @return  success to store in db or not
     */
    boolean insertVacation(Vacation vacationValues);

    boolean insertCashRequest(String vacationId);


    void acceptMessage(ConfirmOfferMessage msg);
    void declineMessage(ConfirmOfferMessage msg);


    void freezeVacation(String vacationId);
    void unFreezeVacation(String vacationId);

    AUserData getUserData(String username);

    ObservableList getAllUsers();

//    String getLoggedUser();

    boolean checkTicketExist(String text);

    ObservableList<Vacation> getUserVacations();

    void insertTradeRequests( String offeredVacationId , String requestedVacationId);

    Vacation getVacationById(String vacationId);


}