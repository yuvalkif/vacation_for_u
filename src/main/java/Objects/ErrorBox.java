package Objects;
import Logger.StageHolder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class ErrorBox {
    public ErrorBox(){
    }
    public Stage getErrorBoxStage(String errorMsg){
        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane,400,200);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("Forms.css").toExternalForm());
        Button button_close = new Button("Close");
        Label label = new Label(errorMsg);
        button_close.setLayoutX(120);
        button_close.setLayoutY(150);
        label.setLayoutX(110);
        label.setLayoutY(50);
        button_close.setPrefWidth(150);
        button_close.setPrefHeight(40);
        button_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageHolder.getInstance().getStage().close();
            }
        });
        pane.getChildren().addAll(button_close,label);
        Stage stage = new Stage();
        stage.setTitle("Error");
        stage.setResizable(false);
        stage.setScene(scene);
        return stage;
    }
}