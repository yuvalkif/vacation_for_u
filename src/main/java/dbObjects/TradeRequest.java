package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TradeRequest extends ARequest {

    private Vacation askerVacationHeWantsToTrade;

    public TradeRequest(String askerUserName, String timeCreated, Vacation askerVacationHeWantsToTrade) {
        super(askerUserName, askerVacationHeWantsToTrade,timeCreated);
        this.askerVacationHeWantsToTrade = askerVacationHeWantsToTrade;
    }

    public TradeRequest(String askerUserName, Vacation askerVacationHeWantsToTrade) {
        super(askerUserName, askerVacationHeWantsToTrade);
        this.askerVacationHeWantsToTrade = askerVacationHeWantsToTrade;
    }

    @Override
    public String toString() {
        return "TradeRequest{" +
                "askerVacationHeWantsToTrade=" + askerVacationHeWantsToTrade +
                ", askerUserName='" + askerUserName + '\'' +
                ", timeCreated='" + timeCreated + '\'' +
                '}';
    }
}
