package dbObjects;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AMessage {

    protected String sender;
    protected String reciver;
    protected String content;
    protected String messageTime;

    public AMessage(String sender, String reciver, String content) {
        this.sender = sender;
        this.reciver = reciver;
        this.content = content;
        this.messageTime =  new SimpleDateFormat("yyyy-mm-dd-HH-mm-ss").format(new Date());
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getSender() {
        return sender;
    }

    public String getReciver() {
        return reciver;
    }

    public String getContent() {
        return content;
    }
}
