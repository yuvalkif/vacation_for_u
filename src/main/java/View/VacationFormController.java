package View;

import Control.Controller;
import Logger.StageHolder;
import Objects.ErrorBox;
import dbObjects.Vacation;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.time.ZoneId;
import java.util.Calendar;


public class VacationFormController {

    private Controller controller;
    private Vacation vacation;
    private boolean back;

    @FXML
    public TextField baggage ,flightComp ,  numberOfTickets , destination,ticketType,vacationTye,hotelName,hotelRank,ticketId;
    public TextField txtfld_price;

    @FXML
    public CheckBox  nightsIncluded , roundTrip;
    public DatePicker fromDate , toDate;
    @FXML
    public AnchorPane mainpane;

    public ImageView img_backPublishVacation;

    public VacationFormController(){}
    /**
     * set a new Vacation object with all the fields entered by the user .
     * if a field left blank , set the object to null.
*/
    public void handleInsert(){

        String ticketID = ticketId.getText();
        String flightCompany = flightComp.getText();
        String baggage1 = baggage.getText();
        String dest = destination.getText();
        String tickettype = ticketType.getText();
        String vacationType =  vacationTye.getText();
        String hotelname = hotelName.getText();
        String numberoftickets  = numberOfTickets.getText();
        String hotelrank = hotelRank.getText();
        String priceS = txtfld_price.getText();

        ErrorBox e = new ErrorBox();
        if(ticketID.equals("") || flightCompany.equals("") || baggage1.equals("") || dest.equals("") || tickettype.equals("") || vacationType.equals("") || hotelname.equals("")
        || numberoftickets.equals("") || hotelrank.equals("") || priceS.equals("") || fromDate.getValue()== null || toDate.getValue()== null) {
            e.showErrorStage("All fields must be entered");
            return;
        }
        if(controller.containsticketID(ticketID)) {
            e.showErrorStage("ticket ID already exists");
            return;
        }
        if(!isInteger(numberoftickets)) {
            e.showErrorStage("number of tickets must be an integer");
            return;
        }
        if(!isDouble(hotelrank)) {
            e.showErrorStage("hotel rank must be a number");
            return;
        }
        if(!isDouble(priceS)) {
            e.showErrorStage("price must be a number");
            return;
        }

        int numOfTickets = Integer.parseInt(numberoftickets);
        double hotelRank = Double.parseDouble(hotelrank);
        double price = Double.parseDouble(priceS);
        java.sql.Date sqlFromDate , sqlToDate , sqlTodayDate;
        try {
            sqlFromDate = utilDateToSqlDate(Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            sqlToDate = utilDateToSqlDate(Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            sqlTodayDate = utilDateToSqlDate(Date.from(Calendar.getInstance().getTime().toInstant()));

            if(sqlFromDate.compareTo(sqlTodayDate)<0){
                e.showErrorStage("Please peek a date later than today");
                return;
            }
            if(sqlFromDate.compareTo(sqlToDate)>0) {
                e.showErrorStage("Return date must be after from date");
                return;
            }
            this.vacation = new Vacation(ticketID, controller.getLoggedUser(), flightCompany, sqlFromDate, sqlToDate, baggage1, numOfTickets, dest, roundTrip.isSelected(), tickettype,
                    vacationType, nightsIncluded.isSelected(), hotelname, hotelRank, false, false, price);
            StageHolder.getInstance().getStage().close();
        }catch (Exception ex){
            this.vacation = null ;
        }
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

    private boolean isDouble(String text){
        try{
            Double.parseDouble(text);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean isInteger(String text){
        try{
            Integer.parseInt(text);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
