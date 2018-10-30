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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchFormController {
    private User searchFields;
    private View view;
    private Controller controller;
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
    private TableView<User> tableView;
    @FXML
    private TableColumn<User,String> userNameCol,passwordCol,firstNameCol,lastNameCol,cityCol,dateCol;


    public SearchFormController() {
    }

    public void handleSearch() {
        this.searchFields = new User(this.username.getText(), this.password.getText(), this.firstname.getText(), this.lastname.getText(), this.city.getText(), this.birthdate.getText());
        if(searchFields.getUsername().equals("")) {
            raiseError("Must specify a username");
            searchFields=null;
            return;
        }

        if(this.controller.searchInDataBase(searchFields).size() == 0){
            raiseError("Username does not exist.");
            searchFields = null;
            return;
        }

         this.showSearchResults(this.view.getSearchResultsFromController(searchFields));
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
        if(this.controller.getAllDataBase().size() == 0){
            raiseError("No records in database");
            return;
        }
        showSearchResults(view.getAllDataBase());
    }


    public User getSearchFields() {
        return this.searchFields;
    }

    public void showSearchResults(ObservableList<User> searchResults) {

        if (searchResults != null) {
            userNameCol.setCellValueFactory(cellData -> cellData.getValue().pUserNameProperty());
            passwordCol.setCellValueFactory(cellData -> cellData.getValue().pPasswordProperty());
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

    public void setView(View view) {
        this.view = view;
    }


    public void setTableView(TableView<User> tableView) {
        this.tableView = tableView;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
