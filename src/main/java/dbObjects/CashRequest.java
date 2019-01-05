package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashRequest extends ARequest {


    public CashRequest(String askerUserName, Vacation requestedVacation, String timeCreated) {
        super(askerUserName,requestedVacation, timeCreated);
    }

    public CashRequest(String askerUserName, Vacation requestedVacation) {
        super(askerUserName,requestedVacation);
    }


    @Override
    public String toString() {
        return "CashRequest{" +
                "askerUserName='" + askerUserName + '\'' +
                ", timeCreated='" + timeCreated + '\'' +
                '}';
    }
}
