package dbObjects;

public class ExpiredOfferMessage extends TransactionMessage {
    public ExpiredOfferMessage(String sender, String reciver, String content,Vacation vacation) {
        super(sender, reciver, content,vacation);
    }


    @Override
    public String toString() {
        return "ExpiredOfferMessage{" +
                "sender='" + sender + '\'' +
                ", reciver='" + reciver + '\'' +
                ", content='" + content + '\'' +
                ", messageTime='" + messageTime + '\'' +
                '}';
    }
}
