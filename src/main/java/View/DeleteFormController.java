package View;

import Control.Controller;
import Logger.StageHolder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * controller class for the Delete scene . controlled by 'DeleteForm.fxml'
 */
public class DeleteFormController implements ISubController{

    private Controller controller;
    private boolean deleted;
    public ImageView img_backDelete;
    public AnchorPane mainpane;

    public void handleDelete(){
        controller.deleteUser();
        deleted = true;
        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
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
