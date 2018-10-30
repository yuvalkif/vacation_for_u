package View;

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

    public void handleDelete(){
        username = txtfld_username.getText();
        if(username.equals("")){
            ErrorBox box = new ErrorBox();
            Stage stage = box.getErrorBoxStage("Must specify username to delete");
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            return;
        }
        StageHolder.getInstance().getStage().close();
    }

    public void handleCancel(){
        StageHolder.getInstance().getStage().close();
    }

    public String getUsername() {
        return username;
    }
}
