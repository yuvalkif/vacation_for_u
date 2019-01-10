package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.Vacation;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class SearchVacationController implements ISubController{
    public Button btn_submitReq;
    public Button btn_beck;
    private Controller controller;
    private Stage primaryStage;
    public TableView tableView;
    public TableColumn<Vacation,String> publisherUserName,flightCompany,fromDate,untilDate,baggageIncluded,numberOfTickets,destination,twoDirections,ticketType,vacationType,includeSleep,hotelName,vacationId,hotelRank,price;

    public void showResults(ObservableList<Vacation> searchResults) {

        if (searchResults != null) {
            vacationId.setCellValueFactory(cellData -> cellData.getValue().PVacationIDProperty());
            publisherUserName.setCellValueFactory(cellData -> cellData.getValue().pownerUserNameProperty());
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
            hotelRank.setCellValueFactory(cellData -> cellData.getValue().photelRankProperty());
            price.setCellValueFactory(cellData -> cellData.getValue().ppriceProperty());
            this.tableView.setItems(searchResults);
        }
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
    }

    public void setPrimaryStage(Stage primaryStage){

        if (controller.getLoggedUser().equals("")){
            btn_submitReq.setDisable(true);
        }
        this.primaryStage = primaryStage;
    }

    public void submitRequest() {

        FXMLLoader loader = new FXMLLoader();

        try {

            if (tableView.getSelectionModel().getSelectedItem()!=null) {
                Vacation v = (Vacation) tableView.getSelectionModel().getSelectedItem();
                if(v.getPownerUserName().equals(controller.getLoggedUser())){
                    ErrorBox e = new ErrorBox();
                    e.showErrorStage("you can't buy your own vacation");
                    StageHolder.getInstance().getStage().close();
                    return;
                }
                Parent root = loader.load(this.getClass().getClassLoader().getResource("SubmitRequest.fxml").openStream());
                Scene scene = new Scene(root);
                scene.getStylesheets().add(this.getClass().getClassLoader().getResource("Forms.css").toExternalForm());
                Stage stage = new Stage();
                stage.requestFocus();
                stage.setScene(scene);
                stage.setTitle("Submit Request");
                stage.setResizable(false);
                StageHolder.getInstance().holdStage(stage);
                SubmitRequestController c = (SubmitRequestController) loader.getController();
                c.setController(this.controller);
                c.submit(controller.getLoggedUser(),v.getVacationID(),v.getPrice());
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        StageHolder.getInstance().getStage();
                    }
                });
                this.primaryStage.hide();
                stage.showAndWait();
                //StageHolder.getInstance().getStage().close();
                //this.primaryStage.show();
            }
            else {
                ErrorBox e = new ErrorBox();
                e.showErrorStage("you need to choose vacation to submit a request");
            }

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    private void showPrimaryStage(){
        this.primaryStage.show();
    }
}
