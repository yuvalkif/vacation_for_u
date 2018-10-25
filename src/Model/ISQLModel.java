package Model;

import Objects.Record;
import View.IView;
import sample.Controller;
import java.util.Date;
import java.util.List;

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
    List<Record> searchRecordsByFields(Record fields);
}
