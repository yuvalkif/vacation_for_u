package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyingRequest extends ARequest {


    public BuyingRequest(String askerUserName, String timeCreated) {
        super(askerUserName, timeCreated);
    }


    @Override
    public String toString() {
        return "BuyingRequest{" +
                "askerUserName='" + askerUserName + '\'' +
                ", timeCreated='" + timeCreated + '\'' +
                '}';
    }
}
