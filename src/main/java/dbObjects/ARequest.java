package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ARequest {

    protected String askerUserName;
    protected String timeCreated;
    protected Vacation requestedVacation;


    public ARequest(String askerUserName,Vacation requestedVacation,String timeCreated){
        this.askerUserName = askerUserName;
        this.requestedVacation = requestedVacation;
        this.timeCreated = timeCreated;
    }


    public ARequest(String askerUserName,Vacation requestedVacation){
        this.askerUserName = askerUserName;
        this.requestedVacation = requestedVacation;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timeCreated = LocalDateTime.now().format(formatter);
    }

    public ARequest(String askerUserName,String timeCreated,Vacation requestedVacation) {
        this.askerUserName = askerUserName;
        this.timeCreated = timeCreated;
        this.requestedVacation = requestedVacation;

    }

    public String getAskerUserName() {
        return askerUserName;
    }

    public String getTimeCreated() {
        return timeCreated;
    }


    public Vacation getRequestedVacation() {
        return requestedVacation;
    }
}
