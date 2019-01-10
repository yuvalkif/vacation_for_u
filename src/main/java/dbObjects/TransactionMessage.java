package dbObjects;

public class TransactionMessage extends AMessage {
    private Vacation wantedVacation;
    public TransactionMessage(String sender, String reciver, String content , Vacation vacation) {
        super(sender, reciver, content);
        this.wantedVacation = vacation;
    }

    public Vacation getVacation() {
        return wantedVacation;
    }

    @Override
    public String getMessageTime() {
        return super.getMessageTime();
    }

    @Override
    public String getSender() {
        return super.getSender();
    }

    @Override
    public String getReciver() {
        return super.getReciver();
    }

    @Override
    public String getContent() {
        return super.getContent();
    }

    @Override
    public String toString() {
        return "TransactionMessage{" +
                "vacation=" + wantedVacation +
                ", sender='" + sender + '\'' +
                ", reciver='" + reciver + '\'' +
                ", content='" + content + '\'' +
                ", messageTime='" + messageTime + '\'' +
                '}';
    }
}
