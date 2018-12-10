package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.User;
import dbObjects.Vacation;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;

public class MainScreenController implements IView{

    private Controller controller;
    private Stage primaryStage;

    public TextField txtfld_destination;
    public TabPane tabPane_tab;
    public Tab tab_searchVacation;
    public ImageView img_backImg;
    public Tab tab_user;
    public Label lbl_SignUp;
    public Button btn_update;
    public Button btn_delete;
    public Label lbl_userName;
    public Label lbl_hello;
    public Label lbl_signIn;


    public void setCurrentStage(Stage stage) {
        this.primaryStage = stage;
        img_backImg.fitWidthProperty().bind(primaryStage.widthProperty());
        img_backImg.fitHeightProperty().bind(primaryStage.heightProperty());
    }

    public void handleSignUp() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("SignUpForm.fxml").openStream());
            Scene scene = new Scene(root);
         //   scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            SignUpFormController sceneController = (SignUpFormController)loader.getController();
            sceneController.setController(controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();
            User toSubmit = sceneController.getToSubmit();

            if( toSubmit != null && !sceneController.getToSubmit().hasNullField())
                this.controller.handleSubmitSignUp(toSubmit);

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }

    }

    public void handleSignIn() {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("LogInWindow.fxml").openStream());
            Scene scene = new Scene(root);
         //   scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            LogInController sceneController = (LogInController)loader.getController();
            sceneController.setController(controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();

            if(!lbl_userName.getText().equals("")) {
                setVisibleLoggedIn(true,true,false,false,false);
            }

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handleSearchVacation(){
        String dest = txtfld_destination.getText();
        if(dest.equals("")) {
            ErrorBox e = new ErrorBox();
            e.showErrorStage("please enter a valid destination");
            return;
        }
        ObservableList l = controller.searchVacationInDB(dest);
        if(l== null || l.size()==0){
            ErrorBox e = new ErrorBox();
            e.showErrorStage("no results for this destination");
            return;
        }

        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("SearchResultWindow.fxml").openStream());
            Scene scene = new Scene(root);
         //   scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Search Results");
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            SearchVacationController sceneController = (SearchVacationController)loader.getController();
            sceneController.setController(controller);
            sceneController.showResults(l);
            sceneController.setPrimaryStage(primaryStage);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handleDelete(){
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("DeleteForm.fxml").openStream());
            Scene scene = new Scene(root);
         //   scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Delete");
            stage.setResizable(false);
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            DeleteFormController sceneController = (DeleteFormController)loader.getController();
            sceneController.setController(controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            if(sceneController.getDeleted()) {
                setVisibleLoggedIn(false, false, true, true, true);
                tabPane_tab.getSelectionModel().select(tab_searchVacation);
            }
            this.primaryStage.show();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handleUserSearch(){
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("SearchForm.fxml").openStream());
            SplitPane splitPane = (SplitPane)root.getChildrenUnmodifiable().get(0);
            AnchorPane upperPane = (AnchorPane)splitPane.getItems().get(0);
            TableView<User> tableView = (TableView<User>)upperPane.getChildren().get(0);
            Scene scene = new Scene(root, 570, 550);
        //    scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Search");
            stage.setResizable(false);
            SearchFormController searchFormController = (SearchFormController)loader.getController();
            searchFormController.setController(controller);
            searchFormController.setTableView(tableView);
            StageHolder.getInstance().holdStage(stage);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleUserUpdate(){
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("UpdateForm.fxml").openStream());
            Scene scene = new Scene(root);
      //      scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Update");
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            UpdateFormController uc = (UpdateFormController) loader.getController();
            uc.setController(this.controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handleVacationButton(){
        FXMLLoader loader = new FXMLLoader();
        try{
            Stage stage = initializeNewStage(loader,"PublishVacationForm.fxml","Forms.css","Vacation",false,650,500);
            VacationFormController vacationFormController =(VacationFormController) loader.getController();
            vacationFormController.setController(this.controller);
            StageHolder.getInstance().holdStage(stage);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });

            stage.showAndWait();

            Vacation toInsert = vacationFormController.getVacationToInsert();
            if(toInsert != null)
                this.controller.insertVacation(toInsert);

        }catch (Exception e){
            System.out.println("at handle vacation button");
            e.printStackTrace();
        }
    }

    /**
     * initialize and return a new stage
     *
     * @param loader
     * @param fxmlPath path to fxml file of the stage
     * @param cssPath path to css of the stage
     * @param title the title to be shown
     * @param resizeable true or false
     * @return a stage initialized with all the parameters
     */
    private Stage initializeNewStage(FXMLLoader loader, String fxmlPath, String cssPath, String title, boolean resizeable, double width, double height){
        try{
            Parent root = loader.load(getClass().getClassLoader().getResource(fxmlPath).openStream());
            Scene scene = new Scene(root,width,height);
         //   scene.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(resizeable);

            return stage;
        }catch (Exception e){
            return null;
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
        lbl_userName.textProperty().bind(controller.getLoggedUserProperty());

    }

    private void setVisibleLoggedIn(boolean username, boolean hello, boolean signIn, boolean signUp, boolean userTab){
        lbl_userName.setVisible(username);
        lbl_hello.setVisible(hello);
        lbl_signIn.setVisible(signIn);
        lbl_SignUp.setVisible(signUp);
        tab_user.setDisable(userTab);
    }

    private void handleXPress(){
        StageHolder.getInstance().getStage();
    }

}
