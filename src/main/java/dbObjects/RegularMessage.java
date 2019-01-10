package dbObjects;

public class RegularMessage extends AMessage{


    public RegularMessage(String sender, String reciver, String content) {
        super(sender, reciver, content);
    }

    public RegularMessage(String sender, String reciver, String content, String messageTime) {
        super(sender, reciver, content, messageTime);
    }

    @Override
    public String toString() {
        return "RegularMessage{" +
                "sender='" + sender + '\'' +
                ", reciver='" + reciver + '\'' +
                ", content='" + content + '\'' +
                ", messageTime='" + messageTime + '\'' +
                '}';
    }
}
