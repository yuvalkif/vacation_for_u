package View;

import Control.Controller;
import Logger.StageHolder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * controller class for the Delete scene . controlled by 'DeleteForm.fxml'
 */
public class DeleteFormController {

    private Controller controller;
    private boolean deleted;
    public ImageView img_backDelete;
    public AnchorPane mainpane;

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

    public void setImageParameters(){
        img_backDelete.fitWidthProperty().bind((mainpane.getScene().getWindow()).widthProperty());
        img_backDelete.fitHeightProperty().bind((mainpane.getScene().getWindow()).heightProperty());
    }
}
