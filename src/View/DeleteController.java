package View;

import Logger.StageHolder;
import javafx.scene.control.TextField;

public class DeleteController {
    public TextField txtfld_username;
    private String username;

    public void handleDelete(){
        username = txtfld_username.getText();
        StageHolder.getInstance().getStage().close();
    }

    public void handleCancel(){
        StageHolder.getInstance().getStage().close();
    }

    public String getUsername() {
        return username;
    }
}
