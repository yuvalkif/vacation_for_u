package Objects;
import Logger.StageHolder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A class to show an error stage. create a new object and use the show function with the wanted msg
 */

public class ErrorBox {

    private Stage errorStage;
    private Label errorLabel;

    public ErrorBox(){
        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane,400,200);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("Forms.css").toExternalForm());
        Button button_close = new Button("Close");
        this.errorLabel = new Label();
        button_close.setLayoutX(120);
        button_close.setLayoutY(150);
        this.errorLabel.setLayoutX(90);
        this.errorLabel.setLayoutY(50);
        button_close.setPrefWidth(150);
        button_close.setPrefHeight(40);
        button_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageHolder.getInstance().getStage().close();
            }
        });
        pane.getChildren().addAll(button_close,errorLabel);
        Stage stage = new Stage();
        stage.setTitle("Error");
        stage.setResizable(false);
        stage.setScene(scene);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                StageHolder.getInstance().getStage().close();
            }
        });

        this.errorStage = stage;

    }

    /**
     * show the error stage with a ms
     * @param errorMsg the msg wanted on the stage
     */
    public void showErrorStage(String errorMsg){
        this.errorLabel.setText(errorMsg);
        StageHolder.getInstance().holdStage(this.errorStage);
        this.errorStage.showAndWait();

    }

}