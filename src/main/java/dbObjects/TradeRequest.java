package dbObjects;

public class TradeRequest extends ARequest {

    private Vacation askerVacationHeWantsToTrade;

    public TradeRequest(String askerUserName, String timeCreated, Vacation askerVacationHeWantsToTrade) {
        super(askerUserName, timeCreated);
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
