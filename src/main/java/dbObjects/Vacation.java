package dbObjects;

import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Vacation {
    private int VacationID;
    private String publisherUserName;
    private String flightCompany;
    private Date fromDate;
    private Date untilDate;
    private String baggageIncluded;
    private int numberOfTickets;
    private String destination;
    private boolean twoDirections;
    private String ticketType;
    private String vacationType;
    private boolean includeSleep;
    private String hotelName;
    private double hotelRank;
    private boolean sold;
    private boolean freezed;

    private StringProperty PVacationID;
    private StringProperty PpublisherUserName;
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

    public String getPpublisherUserName() {
        return PpublisherUserName.get();
    }

    public StringProperty ppublisherUserNameProperty() {
        return PpublisherUserName;
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

    public Vacation(int vacationID , String publisherUserName, String flightCompany, Date fromDate, Date untilDate, String baggageIncluded, int numberOfTickets, String destination, boolean twoDirections, String ticketType, String vacationType, boolean includeSleep, String hotelName, double hotelRank, boolean sold, boolean freezed) {
        this.VacationID = vacationID;
        this.publisherUserName = publisherUserName;
        this.flightCompany = flightCompany;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.baggageIncluded = baggageIncluded;
        this.numberOfTickets = numberOfTickets;
        this.destination = destination;
        this.twoDirections = twoDirections;
        this.ticketType = ticketType;
        this.vacationType = vacationType;
        this.includeSleep = includeSleep;
        this.hotelName = hotelName;
        this.hotelRank = hotelRank;
        this.sold = sold;
        this.freezed = freezed;
    }

    public boolean isFreezed() {
        return freezed;
    }

    public boolean isSold() {
        return sold;
    }

    public int getVacationID() {
        return VacationID;
    }

    public String getPublisherUserName() {
        return publisherUserName;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public String getBaggageIncluded() {
        return baggageIncluded;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isTwoDirections() {
        return twoDirections;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getVacationType() {
        return vacationType;
    }

    public boolean isIncludeSleep() {
        return includeSleep;
    }

    public String getHotelName() {
        return hotelName;
    }

    public double getHotelRank() {
        return hotelRank;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "VacationID=" + VacationID +
                ", publisherUserName='" + publisherUserName + '\'' +
                ", flightCompany='" + flightCompany + '\'' +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                ", baggageIncluded='" + baggageIncluded + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                ", destination='" + destination + '\'' +
                ", twoDirections=" + twoDirections +
                ", ticketType='" + ticketType + '\'' +
                ", vacationType='" + vacationType + '\'' +
                ", includeSleep=" + includeSleep +
                ", hotelName='" + hotelName + '\'' +
                ", hotelRank=" + hotelRank +
                ", sold=" + sold +
                ", freezed=" + freezed +
                '}';
    }
}
