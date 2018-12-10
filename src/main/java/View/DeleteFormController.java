package View;

import Control.Controller;
import Logger.StageHolder;

/**
 * controller class for the Delete scene . controlled by 'DeleteForm.fxml'
 */
public class DeleteFormController {

    private Controller controller;
    private boolean deleted;

    public void handleDelete(){
        controller.deleteUser();
        deleted = true;
        StageHolder.getInstance().getStage().close();
    }

    public void handleCancel(){
        deleted = false;
        StageHolder.getInstance().getStage().close();
    }

    public boolean getDeleted(){
        return deleted;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
