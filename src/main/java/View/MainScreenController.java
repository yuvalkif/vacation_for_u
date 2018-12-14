package View;

import Control.Controller;
import Logger.Logger;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;

public class MainScreenController implements IView{

    public ChoiceBox numOfAdults;
    public ChoiceBox numOfKids;
    private Controller controller;
    private Stage primaryStage;
    private ConfirmOfferMessage inOfferMaassage;

    public TextField txtfld_destination;
    public TabPane tabPane_tab ;
    public Tab tab_searchVacation;
    public ImageView img_backImg;
    public Tab tab_user , tab_vacationBoard;
    public Label lbl_SignUp;
    public Button btn_update;
    public Button btn_delete;
    public Label lbl_userName;
    public Label lbl_hello, lbl_signOut;
    public Label lbl_signIn ,inboxLabel , outboxLabel;

    @FXML
    public TextArea massageArea;
    @FXML
    public TableView inbox , outbox;
    @FXML
    public ListView vacationList;
    @FXML
    public TableColumn<AMessage,String> inboxFrom , inboxTime,inboxContent , outboxTo,outboxTime,outboxContent;

    //add the needed listeners
    public void initializeListeners(){
        setTravelers();

        this.inbox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                AMessage msg = (AMessage) inbox.getSelectionModel().getSelectedItems().get(0);
                showMassage(msg);
            }
        });
    }


    /**
     * refresh the inbox and outbox msgs
     */
    private void refreshInboxAndOutbox(){

        UserData currentUser = controller.getCurrentUserData();
        showUserMassages(currentUser);
    }

    /**
     * show a massage in a text area so it can be read fully
     * @param massage
     */
    private void showMassage(AMessage massage){
        if(massage != null)
         this.massageArea.setText(massage.getContent());
    }

    /**
     * show all the possible vacations
     */
    private void showAllVacations(){
        ObservableList allVacations = this.controller.getAllVacations();
        this.vacationList.setItems(allVacations);
    }


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
            sceneController.setImageParameters();
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

    public void handleConfirmOrder(){

        ConfirmOfferMessage msg = (ConfirmOfferMessage)this.inbox.getSelectionModel().getSelectedItems().get(0);

        if(msg != null)
            this.controller.confirmOrderMassage(msg);

        refreshInboxAndOutbox();
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
            sceneController.setImageParameters();
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
                setVisibleLoggedIn(true,true,false,false,true,false);
            }

            //get the user data and show the massages on the inbox and outbox
            UserData userData =(UserData) sceneController.getUserData();
            showUserMassages(userData);

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    /**
     * show the massages after signing in
     * @param userData user to show its massages
     */
    private void showUserMassages(UserData userData){

        if(userData == null)
            return;

        ObservableList<AMessage> inList = userData.getInMessages();
        ObservableList<AMessage> outList = userData.getOutMessages();
        initializeColumnsInbox(inList);
        initializeColumnsOutbox(outList);
        this.inbox.setItems(inList);
        this.outbox.setItems(outList);

    }

    private void initializeColumnsInbox(ObservableList<AMessage> list){
        this.inboxFrom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSender()));
        this.inboxContent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
        this.inboxTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMessageTime()));
    }

    private void initializeColumnsOutbox(ObservableList<AMessage> list){
        this.outboxTo.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getReciver()));
        this.outboxTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMessageTime()));
        this.outboxContent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
    }

    public void handlePurchaseVacation(){


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
            sceneController.setImageParameters();
            stage.showAndWait();
            if(sceneController.getDeleted()) {
                setVisibleLoggedIn(false, false, true, true, true,true);
                tabPane_tab.getSelectionModel().select(tab_searchVacation);
                //Logger.getInstance().log("Removed user : " + controller.getLoggedUser());
            }
            this.primaryStage.show();
            refreshInboxAndOutbox();

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
            primaryStage.hide();
            stage.showAndWait();
            this.primaryStage.show();
            refreshInboxAndOutbox();

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
            uc.setImageParameters();
            uc.setController(this.controller);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();
            this.primaryStage.show();
            refreshInboxAndOutbox();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handleVacationButton(){
        FXMLLoader loader = new FXMLLoader();
        try{
            Parent root = loader.load(this.getClass().getClassLoader().getResource("PublishVacationForm.fxml").openStream());
            Scene scene = new Scene(root);
            //      scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Vacation");
            this.primaryStage.hide();
            StageHolder.getInstance().holdStage(stage);
            VacationFormController vacationFormController =(VacationFormController) loader.getController();
            vacationFormController.setController(this.controller);
            StageHolder.getInstance().holdStage(stage);
            vacationFormController.setImageParameters();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    handleXPress();
                }
            });
            stage.showAndWait();

            Vacation toInsert = vacationFormController.getVacationToInsert();
            refreshInboxAndOutbox();

            if(toInsert != null)
                this.controller.insertVacation(toInsert);

            primaryStage.show();

        }catch (Exception e){
            System.out.println("at handle vacation button");
            e.printStackTrace();
        }
    }

    public void setTravelers(){
        ObservableList<String> channelItems = FXCollections.observableArrayList("1", "2", "3", "4");

        numOfAdults.setItems(channelItems);
        numOfAdults.getSelectionModel().selectFirst();
        numOfKids.setItems(channelItems);
        numOfKids.getSelectionModel().selectFirst();
    }

    /**
     * initialize and return a new stage
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

    private void setVisibleLoggedIn(boolean username, boolean hello, boolean signIn, boolean signUp, boolean signout, boolean userTab){
        lbl_userName.setVisible(username);
        lbl_hello.setVisible(hello);
        lbl_signIn.setVisible(signIn);
        lbl_SignUp.setVisible(signUp);
        lbl_signOut.setVisible(signout);
        tab_user.setDisable(userTab);
    }

    private void handleXPress(){
        StageHolder.getInstance().getStage();
    }

    public void handleSignOut() {
        controller.signOut();
        setVisibleLoggedIn(false,false,true,true,false,true);
        Alert a = new Alert(Alert.AlertType.INFORMATION,"You are now signed out");
        a.show();
    }
}
