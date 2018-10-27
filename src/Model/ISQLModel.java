package Model;

import Objects.User;
import Objects.User;
import View.IView;
import sample.Controller;
import java.util.Date;
import java.util.List;

/**
 * Interface for sql models
 */

public interface ISQLModel {

    void setController(Controller controller);
    void insert(String username , String password , String firstName, String lastName, String city, java.sql.Date birthDate);
    void deleteUsers(String userName);
    void updateUsers();
    void createUsersTable();
    List<User> searchRecordsByFields(User fields);
}
