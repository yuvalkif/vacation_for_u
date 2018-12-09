package dbObjects;

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
    private int hotelRank;
    private boolean sold;
    private boolean freezed;

    public Vacation(int vacationID ,String publisherUserName, String flightCompany, Date fromDate, Date untilDate, String baggageIncluded, int numberOfTickets, String destination, boolean twoDirections, String ticketType, String vacationType, boolean includeSleep, String hotelName, int hotelRank, boolean sold, boolean freezed) {
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

    public int getHotelRank() {
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
