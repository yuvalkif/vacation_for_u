//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package View;

import Logger.StageHolder;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Control.Controller;

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
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("SignUpForm.fxml").openStream());
            Scene scene = new Scene(root, 500.0D, 450);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            this.primaryStage.show();
            SignUpFormController sceneController = (SignUpFormController)loader.getController();

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
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("SearchForm.fxml").openStream());
            SplitPane splitPane = (SplitPane)root.getChildrenUnmodifiable().get(0);
            AnchorPane upperPane = (AnchorPane)splitPane.getItems().get(0);
//            ListView listView = (ListView)upperPane.getChildren().get(0);
            TableView<User> tableView = (TableView<User>)upperPane.getChildren().get(0);
            Scene scene = new Scene(root, 570, 550);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            SearchFormController searchFormController = (SearchFormController)loader.getController();
            searchFormController.setTableView(tableView);
            searchFormController.setView(this);
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
            Parent root = (Parent)loader.load(this.getClass().getClassLoader().getResource("UpdateForm.fxml").openStream());
            Scene scene = new Scene(root, 600, 450);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            this.primaryStage.show();
            UpdateFormController uc = (UpdateFormController) loader.getController();
            User toUpdate = uc.getUser();
            if(!toUpdate.nullRecord())
                this.controller.updateUser(uc.getsUserName(),toUpdate.getUsername(),toUpdate.getpPassword(),toUpdate.getFirstname(),
                        toUpdate.getLastname(),toUpdate.getCity(),toUpdate.getDate());

        } catch (IOException var6) {
            var6.getCause();
            var6.printStackTrace();
        }

    }

    public void handleDelete(){
        FXMLLoader loader = new FXMLLoader();
        try{
            Parent root = loader.load(getClass().getClassLoader().getResource("DeleteForm.fxml").openStream());
            Scene scene = new Scene(root,400,270);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            DeleteFormController deleteFormController = loader.getController();
            StageHolder.getInstance().holdStage(stage);
            stage.showAndWait();
            String username = deleteFormController.getUsername();
            if(!username.equals("") && !username.trim().isEmpty())
                controller.deleteUser(username);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    //endregion

    public ObservableList<User> getSearchResultsFromController(User user) {
        return this.controller.searchInDataBase(user);
    }

    public ObservableList getAllDataBase() {
        return this.controller.getAllDataBase();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
