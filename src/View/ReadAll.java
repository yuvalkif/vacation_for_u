package View;

import Logger.StageHolder;
import Objects.Record;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class ReadAll {

    private Record searchFields;
    private boolean isDone;
    @FXML
    public TextField username , password , firstname , lastname , city , birthdate ;


    public void handleSearch(){
        searchFields = new Record(username.getText(),password.getText(),firstname.getText(),lastname.getText(),city.getText(),birthdate.getText());

        if(searchFields.isNullRecord())
            searchFields = null;

        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
        isDone = true;
        StageHolder.getInstance().getStage().close();
    }


    public boolean getIsDone(){
        return this.isDone;
    }

    public Record getSearchFields(){
        return searchFields;
    }

    /**
     * show the search results . should be from the View
     * @param searchResults list of records that match the search fields
     */
    public void showSearchResults(List<Record> searchResults){

        if (searchResults == null)
            return;

        //code for showing results
    }
}
