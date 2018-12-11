package dbObjects;

public class ConfirmOfferMessage extends AMessage {
    private String status;
    private Vacation vacation;
    public ConfirmOfferMessage(String sender, String reciver, String content,Vacation vacation,String status) {
        super(sender, reciver, content);
        this.status = status;
        this.vacation=vacation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Vacation getVacation() {
        return vacation;
    }
}
