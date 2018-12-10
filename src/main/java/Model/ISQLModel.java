package Model;

import dbObjects.AUserData;
import dbObjects.Purchase;
import dbObjects.User;
import Control.Controller;
import dbObjects.Vacation;
import javafx.collections.ObservableList;


import java.sql.Timestamp;
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
    void insertUser(User user);

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
    void updateUsers(String username,String newUserName  , String password , String firstName, String lastName, String city, String Date);

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

    void createOffersTable();

    void createConfirmMessageTable();

    /**
     * search records by username
     * @param username
     * @return
     */


    List<User> searchRecordsByFields(String username);

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
     * @param criteria the fields which the search will be by them
     * @param Values    the values of those fields
     * @return  a collection of vacations that meet the criterias.
     */
    ObservableList getVacations(String [] criteria , String [] Values);


    /**
     *
     * @param vacationValues vacation fields
     * @return  success to store in db or not
     */
    boolean insertVacation(Vacation vacationValues);

    boolean insertBuyingOffer(int vacationId ,
                           String buyerUsername ,
                           String purchseOfferTime,
                           Purchase purchaseOfferDetails);


    void freezeVacation(int vacationId);
    void unFreezeVacation(int vacationId);



    ObservableList selectAllDataBase();
}