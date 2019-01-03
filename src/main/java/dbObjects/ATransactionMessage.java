package dbObjects;

public class ATransactionMessage extends AMessage {
    public ATransactionMessage(String sender, String reciver, String content) {
        super(sender, reciver, content);
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
}
