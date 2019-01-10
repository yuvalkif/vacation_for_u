package dbObjects;

import java.sql.Date;

public class Accommodation {

    private String AccomodationName;
    private double AccomodationRank;
    private String AccomodationLocation;
    private java.sql.Date fromDate;
    private java.sql.Date until;


    public Accommodation(String accomodationName, double accomodationRank, String accomodationLocation, Date fromDate, Date until) {
        AccomodationName = accomodationName;
        AccomodationRank = accomodationRank;
        AccomodationLocation = accomodationLocation;
        this.fromDate = fromDate;
        this.until = until;
    }


    public String getAccomodationName() {
        return AccomodationName;
    }

    public double getAccomodationRank() {
        return AccomodationRank;
    }

    public String getAccomodationLocation() {
        return AccomodationLocation;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getUntil() {
        return until;
    }


    @Override
    public String toString() {
        return "Accommodation{" +
                "AccomodationName='" + AccomodationName + '\'' +
                ", AccomodationRank=" + AccomodationRank +
                ", AccomodationLocation='" + AccomodationLocation + '\'' +
                ", fromDate=" + fromDate +
                ", until=" + until +
                '}';
    }
}
