package View;

import Control.Controller;
import Logger.StageHolder;

/**
 * controller class for the Delete scene . controlled by 'DeleteForm.fxml'
 */
public class DeleteFormController {

    private Controller controller;

    public void handleDelete(){
        controller.deleteUser();
        StageHolder.getInstance().getStage().close();
    }

    public void handleCancel(){
        StageHolder.getInstance().getStage().close();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
