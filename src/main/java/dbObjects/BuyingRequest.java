package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyingRequest extends ARequest {


    public BuyingRequest(String askerUserName,Vacation requestedVacation, String timeCreated) {
        super(askerUserName,requestedVacation, timeCreated);
    }

    public BuyingRequest(String askerUserName,Vacation requestedVacation) {
        super(askerUserName,requestedVacation);
    }


    @Override
    public String toString() {
        return "BuyingRequest{" +
                "askerUserName='" + askerUserName + '\'' +
                ", timeCreated='" + timeCreated + '\'' +
                '}';
    }
}
