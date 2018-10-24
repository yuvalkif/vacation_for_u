package Model;

import View.IView;
import sample.Controller;

import java.sql.SQLData;

/**
 * Interface for sql models
 */

public interface ISQLModel {

    void setView(IView view);
    void setController(Controller controller);
    void insert(String username , String password , String firstName, String lastName, String city, SQLData birthDate);
    void remove(String userName);
    void update();
}
