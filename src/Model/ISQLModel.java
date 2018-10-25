package Model;

import View.IView;
import sample.Controller;
import java.util.Date;

/**
 * Interface for sql models
 */

public interface ISQLModel {

    void setView(IView view);
    void setController(Controller controller);
    void insert(String username , String password , String firstName, String lastName, String city, Date birthDate);
    void deleteUsers(String userName);
    void updateUsers();
    void createUsersTable();
}
