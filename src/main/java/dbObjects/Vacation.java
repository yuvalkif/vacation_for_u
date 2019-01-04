package dbObjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Vacation {
    private String VacationID;
    private String ownerUserName;
    private Flight flight;
    private Accommodation accommodation;
    private int numberOfTickets;
    private String vacationType;
    private boolean includeSleep;
    private boolean sold;
    private boolean freezed;
    private double price;

    private StringProperty PVacationID;
    private StringProperty PownerUserName;
    private StringProperty PflightCompany;
    private StringProperty PfromDate;
    private StringProperty PuntilDate;
    private StringProperty PbaggageIncluded;
    private StringProperty PnumberOfTickets;
    private StringProperty PtwoDirections;
    private StringProperty Pdestination;
    private StringProperty PticketType;
    private StringProperty PvacationType;
    private StringProperty PincludeSleep;
    private StringProperty PhotelName;
    private StringProperty PhotelRank;
    private StringProperty Pprice;

    public Vacation(String vacationID , String publisherUserName, String flightCompany, Date fromDate, Date untilDate, String baggageIncluded, int numberOfTickets, String destination, boolean twoDirections, String ticketType, String vacationType, boolean includeSleep, String hotelName, double hotelRank, boolean sold, boolean freezed, double price) {
        this.VacationID = vacationID;
        this.ownerUserName = publisherUserName;

        this.flight = new Flight(flightCompany,destination,ticketType,baggageIncluded,fromDate,untilDate,isTwoDirections());
        this.accommodation = new Accommodation(hotelName,hotelRank,destination,fromDate,untilDate);
        this.numberOfTickets = numberOfTickets;
        this.vacationType = vacationType;
        this.includeSleep = includeSleep;
        this.sold = sold;
        this.freezed = freezed;
        this.price = price;
        PVacationID = new SimpleStringProperty(""+vacationID);
        PownerUserName = new SimpleStringProperty(publisherUserName);
        PflightCompany = new SimpleStringProperty(flightCompany);
        PfromDate = new SimpleStringProperty(fromDate.toString());
        PuntilDate =new SimpleStringProperty(untilDate.toString()) ;
        PbaggageIncluded=new SimpleStringProperty(baggageIncluded);
        PnumberOfTickets=new SimpleStringProperty(""+numberOfTickets);
        PtwoDirections=new SimpleStringProperty(""+twoDirections);
        Pdestination=new SimpleStringProperty(destination);
        PticketType=new SimpleStringProperty(ticketType);
        PvacationType=new SimpleStringProperty(vacationType);
        PincludeSleep=new SimpleStringProperty(""+includeSleep);
        PhotelName=new SimpleStringProperty(hotelName);
        PhotelRank=new SimpleStringProperty(""+hotelRank);
        Pprice = new SimpleStringProperty(""+price);
    }

    public boolean isFreezed() {
        return freezed;
    }

    public boolean isSold() {
        return sold;
    }

    public String getVacationID() {
        return VacationID;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public String getFlightCompany() {
        return flight.getFlightCompany();
    }

    public Date getFromDate() {
        return flight.getFromDate();
    }

    public Date getUntilDate() {
        return flight.getUntilDate();
    }

    public String getBaggageIncluded() {
        return flight.getBaggageIncluded();
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public double getPrice() {
        return price;
    }

    public String getPprice() {
        return Pprice.get();
    }

    public StringProperty ppriceProperty() {
        return Pprice;
    }

    public String getPhotelRank() {
        return PhotelRank.get();
    }

    public StringProperty photelRankProperty() {
        return PhotelRank;
    }

    public String getPdestination() {
        return Pdestination.get();
    }

    public StringProperty pdestinationProperty() {
        return Pdestination;
    }

    public String getPVacationID() {
        return PVacationID.get();
    }

    public StringProperty PVacationIDProperty() {
        return PVacationID;
    }

    public String getPownerUserName() {
        return PownerUserName.get();
    }

    public StringProperty pownerUserNameProperty() {
        return PownerUserName;
    }

    public String getPflightCompany() {
        return PflightCompany.get();
    }

    public StringProperty pflightCompanyProperty() {
        return PflightCompany;
    }

    public String getPfromDate() {
        return PfromDate.get();
    }

    public StringProperty pfromDateProperty() {
        return PfromDate;
    }

    public String getPuntilDate() {
        return PuntilDate.get();
    }

    public StringProperty puntilDateProperty() {
        return PuntilDate;
    }

    public String getPbaggageIncluded() {
        return PbaggageIncluded.get();
    }

    public StringProperty pbaggageIncludedProperty() {
        return PbaggageIncluded;
    }

    public String getPnumberOfTickets() {
        return PnumberOfTickets.get();
    }

    public StringProperty pnumberOfTicketsProperty() {
        return PnumberOfTickets;
    }

    public String getPtwoDirections() {
        return PtwoDirections.get();
    }

    public StringProperty ptwoDirectionsProperty() {
        return PtwoDirections;
    }

    public String getPticketType() {
        return PticketType.get();
    }

    public StringProperty pticketTypeProperty() {
        return PticketType;
    }

    public String getPvacationType() {
        return PvacationType.get();
    }

    public StringProperty pvacationTypeProperty() {
        return PvacationType;
    }

    public String getPincludeSleep() {
        return PincludeSleep.get();
    }

    public StringProperty pincludeSleepProperty() {
        return PincludeSleep;
    }

    public String getPhotelName() {
        return PhotelName.get();
    }

    public StringProperty photelNameProperty() {
        return PhotelName;
    }

    public String getDestination() {
        return flight.getDestination();
    }

    public boolean isTwoDirections() {
        return flight.isTwoDirections();
    }

    public String getTicketType() {
        return flight.getGetTicketType();
    }

    public String getVacationType() {
        return vacationType;
    }

    public boolean isIncludeSleep() {
        return includeSleep;
    }

    public String getHotelName() {
        return accommodation.getAccomodationName();
    }

    public double getHotelRank() {
        return accommodation.getAccomodationRank();
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "VacationID='" + VacationID + '\'' +
                ", ownerUserName='" + ownerUserName + '\'' +
                ", flight=" + flight +
                ", accommodation=" + accommodation +
                ", numberOfTickets=" + numberOfTickets +
                ", vacationType='" + vacationType + '\'' +
                ", includeSleep=" + includeSleep +
                ", sold=" + sold +
                ", freezed=" + freezed +
                ", price=" + price +
                ", PVacationID=" + PVacationID +
                ", PownerUserName=" + PownerUserName +
                ", PflightCompany=" + PflightCompany +
                ", PfromDate=" + PfromDate +
                ", PuntilDate=" + PuntilDate +
                ", PbaggageIncluded=" + PbaggageIncluded +
                ", PnumberOfTickets=" + PnumberOfTickets +
                ", PtwoDirections=" + PtwoDirections +
                ", Pdestination=" + Pdestination +
                ", PticketType=" + PticketType +
                ", PvacationType=" + PvacationType +
                ", PincludeSleep=" + PincludeSleep +
                ", PhotelName=" + PhotelName +
                ", PhotelRank=" + PhotelRank +
                ", Pprice=" + Pprice +
                '}';
    }
}
