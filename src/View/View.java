package View;

import sample.Controller;
import Model.*;

public class View implements IView{

    private Controller controller;
    private ISQLModel model;

    public View(){}








    public void setModel(ISQLModel model) {
        this.model = model;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}

