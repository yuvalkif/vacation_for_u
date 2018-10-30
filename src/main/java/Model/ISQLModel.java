package Model;

import View.User;
import sample.Controller;


import java.sql.Date;
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
    void insert(User user);

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
     * search records by username
     * @param fields
     * @return
     */
    List<User> searchRecordsByFields(User fields);
}
