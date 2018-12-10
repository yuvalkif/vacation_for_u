package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.Vacation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class SearchVacationController {
    private Controller controller;
    public TableView tableView;
    public TableColumn<Vacation,String> publisherUserName,flightCompany,fromDate,untilDate,baggageIncluded,numberOfTickets,destination,twoDirections,ticketType,vacationType,includeSleep,hotelName,vacationId;

    public void setPreferable(String dest){
        ObservableList l = controller.searchVacationInDB(dest);
        if(l==null || l.size()==0) {
            ErrorBox e = new ErrorBox();
            e.showErrorStage("no results found for this destination");
            StageHolder.getInstance().getStage().close();
        }
        else
            showResults(l);
    }

    public void showResults(ObservableList<Vacation> searchResults) {

        if (searchResults != null) {
            vacationId.setCellValueFactory(cellData -> cellData.getValue().PVacationIDProperty());
            publisherUserName.setCellValueFactory(cellData -> cellData.getValue().ppublisherUserNameProperty());
            flightCompany.setCellValueFactory(cellData -> cellData.getValue().pflightCompanyProperty());
            fromDate.setCellValueFactory(cellData -> cellData.getValue().pfromDateProperty());
            untilDate.setCellValueFactory(cellData -> cellData.getValue().puntilDateProperty());
            baggageIncluded.setCellValueFactory(cellData -> cellData.getValue().pbaggageIncludedProperty());
            numberOfTickets.setCellValueFactory(cellData -> cellData.getValue().pnumberOfTicketsProperty());
            destination.setCellValueFactory(cellData -> cellData.getValue().pdestinationProperty());
            twoDirections.setCellValueFactory(cellData -> cellData.getValue().ptwoDirectionsProperty());
            ticketType.setCellValueFactory(cellData -> cellData.getValue().pticketTypeProperty());
            vacationType.setCellValueFactory(cellData -> cellData.getValue().pvacationTypeProperty());
            includeSleep.setCellValueFactory(cellData -> cellData.getValue().pincludeSleepProperty());
            hotelName.setCellValueFactory(cellData -> cellData.getValue().photelNameProperty());
            this.tableView.setItems(searchResults);
        }
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

    public void submitRequest() {

    }
}
