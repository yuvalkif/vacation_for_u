package View;

import Control.Controller;
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

    @FXML
    public TextField baggage ,username ,flightComp ,  numberOfTickets , destination,ticketType,vacationTye,hotelName,hotelRank;

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
        double hotelRank ;
        java.sql.Date sqlFromDate , sqlToDate ;

        try{

            sqlFromDate = utilDateToSqlDate(Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            sqlToDate = utilDateToSqlDate(Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            numOfTickets = Integer.parseInt(this.numberOfTickets.getText());
            hotelRank = Double.parseDouble(this.hotelRank.getText());


           this.vacation = new Vacation(1,username.getText(),flightComp.getText(),sqlFromDate,sqlToDate,baggage.getText(),numOfTickets,destination.getText(),roundTrip.isSelected(),ticketType.getText(),
                   vacationTye.getText(),nightsIncluded.isSelected(),hotelName.getText(),hotelRank,false,false);


        }catch (Exception e){
            this.vacation = null ;
        }
    }

    public void handleBack(){

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
}