package View;

import Logger.StageHolder;
import Objects.User;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sample.Controller;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAll {

    private User searchFields;
    private boolean isDone;
    private Controller controller;
    @FXML
    public TextField username , password , firstname , lastname , city , birthdate ;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ListView listView;
    ObservableList <User> observableList= FXCollections.observableArrayList();

//    @FXML
//    protected ListProperty<User> listProperty = new SimpleListProperty<>();


//    public ReadAll(ListView<String> listView) {
//        this.listView = listView;
//    }

    public void handleSearch(){
        searchFields = new User(username.getText(),password.getText(),firstname.getText(),lastname.getText(),city.getText(),birthdate.getText());

//        if(searchFields.isNullRecord())
//            searchFields = null;

        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
        isDone = true;
        StageHolder.getInstance().getStage().close();
    }


    public boolean getIsDone(){
        return this.isDone;
    }

    public User getSearchFields(){
        return searchFields;
    }

    /**
     * show the search results . should be from the View
     * @param searchResults list of records that match the search fields
     */
    public void showSearchResults(List<User> searchResults){

        if (searchResults == null)
            return;

        //code for showing results


    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    public String getUserName(){

    return username.getText();

    }

    @FXML
    public void setSearchResults(ArrayList<User> userList){
        FXMLLoader loader = new FXMLLoader();
        try {
//            listProperty.set(FXCollections.observableArrayList(userList));
            Parent root = loader.load(getClass().getResource("readAllController.fxml").openStream());
//          AnchorPane ac = (AnchorPane)root.getChildrenUnmodifiable().get(0);
            SplitPane splitPane = (SplitPane)root.getChildrenUnmodifiable().get(0);

            listView = (ListView)splitPane.getChildrenUnmodifiable().get(0);
//            ObservableList<User> items =FXCollections.observableArrayList (
//                    "Single", "Double", "Suite", "Family App");
            observableList.addAll(userList);
            listView.setItems(observableList);
            listView.setPrefSize(300,300);
//            listView.itemsProperty().bind(listProperty);

//            listProperty.set(FXCollections.observableArrayList(userList));


        } catch (IOException e) {
            e.printStackTrace();
        }
//        observableList.addAll("a","v");
//        listView.setItems(observableList);
//        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
//        {
//            @Override
//            public ListCell<String> call(ListView<String> listView)
//            {
//                return new ListViewCell();
//            }
//        });
//        listView = new ListView<String>();


    }
}



//public class ListViewCell extends ListCell<String>
//{
//    @Override
//    public void updateItem(String string, boolean empty)
//    {
//        super.updateItem(string,empty);
//        if(string != null)
//        {
//            Data data = new Data();
//            data.setInfo(string);
//            setGraphic(data.getBox());
//        }
//    }
//}



