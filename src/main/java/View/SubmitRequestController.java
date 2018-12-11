package View;

import Control.Controller;
import Logger.StageHolder;
import javafx.beans.property.StringProperty;

public class SubmitRequestController {
    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }

    public void submit(String loggedUser, String stringProperty) {

    }

    public void submit(String loggedUser, int vacationID, Object price) {
        



    }
}
