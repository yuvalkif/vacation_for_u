//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package View;

/**
 * controller class for the Read scene . controlled by 'SearchForm.fxml'
 */

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.RegisteredUser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchUserController implements ISubController{
    private RegisteredUser searchFields;
    private boolean isDone;
    private ListView listView;

    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public TextField firstname;
    @FXML
    public TextField lastname;
    @FXML
    public TextField city;
    @FXML
    public TextField birthdate;
    @FXML
    public TextField email;
    @FXML
    private TableView<RegisteredUser> tableView;
    @FXML
    private TableColumn<RegisteredUser,String> userNameCol,passwordCol,firstNameCol,lastNameCol,cityCol,dateCol;
    private Controller controller;

    public SearchUserController() {
    }

    public void handleSearch() {
        this.searchFields = new RegisteredUser(this.username.getText(), this.password.getText(), this.firstname.getText(), this.lastname.getText(), this.city.getText(), this.birthdate.getText(), this.email.getText());
        if(searchFields.getUserName().equals("")) {
            raiseError("Must specify a username");
            return;
        }

        if(this.controller.searchInDataBase(searchFields).size() == 0){
            raiseError("Username does not exist.");
            return;
        }

         this.showSearchResults(this.controller.searchInDataBase(searchFields));
    }

    private void raiseError(String errorMsg){
        ErrorBox box = new ErrorBox();
        box.showErrorStage(errorMsg);

    }

    public void handleBack() {
        this.isDone = true;
        StageHolder.getInstance().getStage().close();
    }


    public void handleSearchAll(){
        if(this.controller.getAllUsers().size() == 0){
            raiseError("No records in database");
            return;
        }
        showSearchResults(this.controller.getAllUsers());
    }


    public RegisteredUser getSearchFields() {
        return this.searchFields;
    }

    public void showSearchResults(ObservableList<RegisteredUser> searchResults) {

            if (searchResults != null) {
                userNameCol.setCellValueFactory(cellData -> cellData.getValue().pUserNameProperty());
                //passwordCol.setCellValueFactory(cellData -> cellData.getValue().pPasswordProperty());
                firstNameCol.setCellValueFactory(cellData -> cellData.getValue().pFirstNameProperty());
                lastNameCol.setCellValueFactory(cellData -> cellData.getValue().pLastNameProperty());
                cityCol.setCellValueFactory(cellData -> cellData.getValue().pCityProperty());
                dateCol.setCellValueFactory(cellData -> cellData.getValue().pBirthDateProperty());
                this.tableView.setItems(searchResults);

//            this.listView.setItems(searchResults);
            }
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

//    public void setView(View view) {
//        this.view = view;
//    }


    public void setTableView(TableView<RegisteredUser> tableView) {
        this.tableView = tableView;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
