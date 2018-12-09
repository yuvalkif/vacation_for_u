package dbObjects;

public class ConfirmOfferMessage extends AMessage {
    private boolean accept;
    public ConfirmOfferMessage(String sender, String reciver, String content,boolean accept) {
        super(sender, reciver, content);
        this.accept = accept;
    }
}
