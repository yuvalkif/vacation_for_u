package View;

import Control.Controller;
import Logger.StageHolder;
import dbObjects.Vacation;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.ZoneId;


public class VacationFormController {

    private Controller controller;
    private Vacation vacation;
    private boolean back;

    @FXML
    public TextField baggage ,flightComp ,  numberOfTickets , destination,ticketType,vacationTye,hotelName,hotelRank;
    public TextField txtfld_price;

    @FXML
    public CheckBox  nightsIncluded , roundTrip;

    @FXML
    public DatePicker fromDate , toDate;

    public VacationFormController(){}
    /**
     * set a new Vacation object with all the fields entered by the user .
     * if a field left blank , set the object to null.
*/
    public void handleInsert(){

        int numOfTickets ;
        double hotelRank, price ;
        java.sql.Date sqlFromDate , sqlToDate ;

        try{

            sqlFromDate = utilDateToSqlDate(Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            sqlToDate = utilDateToSqlDate(Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            numOfTickets = Integer.parseInt(this.numberOfTickets.getText());
            hotelRank = Double.parseDouble(this.hotelRank.getText());
            price = Double.parseDouble(txtfld_price.getText());


           this.vacation = new Vacation(1,controller.getLoggedUser(),flightComp.getText(),sqlFromDate,sqlToDate,baggage.getText(),numOfTickets,destination.getText(),roundTrip.isSelected(),ticketType.getText(),
                   vacationTye.getText(),nightsIncluded.isSelected(),hotelName.getText(),hotelRank,false,false,price);


        }catch (Exception e){
            this.vacation = null ;
        }
        StageHolder.getInstance().getStage().close();
    }

    public void handleBack(){
        StageHolder.getInstance().getStage().close();
        this.back = true;
    }

    private Date utilDateToSqlDate(java.util.Date utilDate ){

        return new java.sql.Date(utilDate.getTime());
    }

    public Vacation getVacationToInsert(){
        return this.vacation;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public boolean isBack() {
        return back;
    }
}
