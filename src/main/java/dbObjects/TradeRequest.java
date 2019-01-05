package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TradeRequest extends ARequest {

    private Vacation askerVacationHeWantsToTrade;

    public TradeRequest(String askerUserName, String timeCreated, Vacation askerVacationHeWantsToTrade, Vacation requestedVacation) {
        super(askerUserName, requestedVacation,timeCreated);
        this.askerVacationHeWantsToTrade = askerVacationHeWantsToTrade;
    }

    public TradeRequest(String askerUserName, Vacation askerVacationHeWantsToTrade ,Vacation requestedVacation) {
        super(askerUserName, requestedVacation);
        this.askerVacationHeWantsToTrade = askerVacationHeWantsToTrade;
    }

    public Vacation getAskerVacationHeWantsToTrade() {
        return askerVacationHeWantsToTrade;
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
