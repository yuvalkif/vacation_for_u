package View;

import Control.Controller;
import Logger.StageHolder;

public interface ISubController {
    void setController(Controller controller);

    void handleBack();
}
