package View;


import Model.ISQLModel;
import sample.Controller;

/**
 * interface for GUI view classes
 */

public interface IView {

    void setController(Controller controller);
    void setModel(ISQLModel model);
}
