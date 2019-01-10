package dbObjects;

import java.sql.Date;

public class Flight {
    private String flightCompany;
    private String destination;
    private String ticketType;
    private String baggageIncluded;
    private java.sql.Date fromDate;
    private java.sql.Date untilDate;
    private boolean isTwoDirections;


    public Flight(String flightCompany, String destination, String getTicketType, String baggageIncluded, Date fromDate, Date untilDate, boolean isTwoDirections) {
        this.flightCompany = flightCompany;
        this.destination = destination;
        this.ticketType = getTicketType;
        this.baggageIncluded = baggageIncluded;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.isTwoDirections = isTwoDirections;
    }


    public String getFlightCompany() {
        return flightCompany;
    }

    public String getDestination() {
        return destination;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getBaggageIncluded() {
        return baggageIncluded;
    }

    public Date getFromDate() {
        return fromDate;
    }


    public Date getUntilDate() {
        return untilDate;
    }

    public boolean isTwoDirections() {
        return isTwoDirections;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "flightCompany='" + flightCompany + '\'' +
                ", destination='" + destination + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", baggageIncluded='" + baggageIncluded + '\'' +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                ", isTwoDirections=" + isTwoDirections +
                '}';
    }

    public String toPrint() {
        return " \n{flightCompany='" + flightCompany + '\'' +
                ",\ndestination='" + destination + '\'' +
                ",\nticketType='" + ticketType + '\'' +
                ",\nbaggageIncluded='" + baggageIncluded + '\'' +
                ",\nfromDate=" + fromDate +
                ",\nuntilDate=" + untilDate +
                ",\nisTwoDirections=" + isTwoDirections +
                '}';
    }
}
