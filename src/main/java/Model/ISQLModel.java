package Model;

import View.User;
import sample.Controller;

import java.util.List;

/**
 * Interface for sql models
 */

public interface ISQLModel {

    //change to generic CRUD interface

    void setController(Controller controller);
    void insert(String username,String password, String firstName, String lastName, String city, java.sql.Date birthDate);
    void deleteUsers(String userName);
    void updateUsers(String username,String newUserName  , String password , String firstName, String lastName, String city, String Date);
    void createUsersTable();
    List<User> searchRecordsByFields(User fields);
    void createUser(User user);
}
