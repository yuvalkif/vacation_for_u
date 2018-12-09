package dbObjects;

public class OfferMessage extends AMessage {
    private Vacation vacation;

    public OfferMessage(String sender, String reciver, String content, Vacation vacation) {
        super(sender, reciver, content);
        this.vacation = vacation;
    }


    public Vacation getVacation() {
        return vacation;
    }
}
