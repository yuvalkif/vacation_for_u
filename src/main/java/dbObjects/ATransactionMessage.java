package dbObjects;

public class ATransactionMessage extends AMessage {
    private Vacation vacation;
    public ATransactionMessage(String sender, String reciver, String content ,Vacation vacation) {
        super(sender, reciver, content);
        this.vacation = vacation;
    }

    public Vacation getVacation() {
        return vacation;
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
        return "ATransactionMessage{" +
                "vacation=" + vacation +
                ", sender='" + sender + '\'' +
                ", reciver='" + reciver + '\'' +
                ", content='" + content + '\'' +
                ", messageTime='" + messageTime + '\'' +
                '}';
    }
}
