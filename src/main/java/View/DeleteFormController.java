package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * controller class for the Delete scene . controlled by 'DeleteForm.fxml'
 */

public class DeleteFormController {

    public TextField txtfld_username;
    private String username;
    private Controller controller;

    public void handleDelete(){
        username = txtfld_username.getText();

        if(username.equals("")) {
            raiseError("Must specify username to delete");
            return;
        }

        if(controller.searchInDataBase(new User(username,"","","","","")).size() == 0){
            raiseError("Username does not exist.");
            return;
        }

        StageHolder.getInstance().getStage().close();
    }

    private void raiseError(String msg){
        ErrorBox box = new ErrorBox();
        box.showErrorStage(msg);
    }

    public void handleCancel(){
        StageHolder.getInstance().getStage().close();
    }

    public String getUsername() {
        return username;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
