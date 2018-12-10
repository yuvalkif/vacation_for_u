package View;

import Control.Controller;
import Logger.StageHolder;

public class SubmitRequestController {
    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void handleBack() {
        StageHolder.getInstance().getStage().close();
    }

}
