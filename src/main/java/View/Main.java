package View;

/**
 * Initialize everything needed to start the app
 */

import Model.Model;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Control.Controller;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    /*public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("MainMenuForm.fxml").openStream());
        View view = loader.getController();

        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 419.0, 320.0));
        primaryStage.getScene().getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        Controller controller = new Controller();

        controller.setView(view);
        controller.setModel(new Model());
        controller.setAll();
        controller.createUsersTable();
        controller.createVacationsTable();
        controller.createPurchaseTable();
        view.setCurrentStage(primaryStage);
        primaryStage.show();


    }*/

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("MainScreenForm.fxml").openStream());
        IView mainView = loader.getController();

        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        Controller controller = new Controller();

        controller.setView(mainView);
        controller.setModel(new Model());
        controller.setAll();
        controller.createUsersTable();
        controller.createVacationsTable();
        controller.createPurchaseTable();
        mainView.setCurrentStage(primaryStage);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}



