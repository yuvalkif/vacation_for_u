//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package View;

import Logger.StageHolder;
import java.io.IOException;

import Objects.ErrorBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controller;

public class View implements IView {
    private Controller controller;
    private Stage primaryStage;

    public View() {
    }

    public void setCurrentStage(Stage stage) {
        this.primaryStage = stage;
    }

    //region SCENES

    public void signInScene() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("logInFXML.fxml").openStream());
            Scene scene = new Scene(root, 500.0D, 450);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            this.primaryStage.show();
            LoginSceneController sceneController = (LoginSceneController)loader.getController();

            User toSubmit = sceneController.getToSubmit();
            System.out.println(toSubmit);
            if( toSubmit != null && !sceneController.getToSubmit().hasNullField())
                this.controller.handleSubmitSignIn(toSubmit);

        } catch (IOException var6) {
            var6.getCause();
            var6.printStackTrace();
        }

    }

    public void handleRead() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("readAllController.fxml").openStream());
            SplitPane splitPane = (SplitPane)root.getChildrenUnmodifiable().get(0);
            AnchorPane upperPane = (AnchorPane)splitPane.getItems().get(0);
//            ListView listView = (ListView)upperPane.getChildren().get(0);
            TableView<User> tableView = (TableView<User>)upperPane.getChildren().get(0);
            Scene scene = new Scene(root, 570, 550);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            ReadAll readAll = (ReadAll)loader.getController();
            readAll.setTableView(tableView);
            readAll.setView(this);
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            this.primaryStage.show();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }

    public void handleUpdate() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("UpdateController.fxml").openStream());
            Scene scene = new Scene(root, 600, 450);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            this.primaryStage.show();
            UpdateController uc = (UpdateController) loader.getController();
            User toUpdate = new User(uc.getsUserName(),uc.getsPassword(),uc.getsFirstName(),uc.getsLastName(),
                    uc.getsCity(),uc.getsDate());
            if(!toUpdate.nullRecord())
                this.controller.updateUser(uc.getsUserName(),uc.getsNewUserName(),uc.getsPassword(),uc.getsFirstName(),uc.getsLastName(),
                        uc.getsCity(),uc.getsDate());

        } catch (IOException var6) {
            var6.getCause();
            var6.printStackTrace();
        }

    }

    public void handleDelete(){
        FXMLLoader loader = new FXMLLoader();
        try{
            Parent root = loader.load(getClass().getClassLoader().getResource("Delete.fxml").openStream());
            Scene scene = new Scene(root,400,270);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            DeleteController deleteController = loader.getController();
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            String username = deleteController.getUsername();
            if(!username.equals(""))
                controller.deleteUser(username);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    //endregion

    public ObservableList<User> searchInDataBase(User user) {
        return this.controller.searchInDataBase(user);
    }

    public ObservableList getAllDataBase() {
        return this.controller.getAllDataBase();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
