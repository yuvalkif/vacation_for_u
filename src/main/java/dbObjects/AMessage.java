package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AMessage {

    protected String sender;
    protected String reciver;
    protected String content;
    protected String messageTime;

    public AMessage(String sender, String reciver, String content,String messageTime) {
        this.sender = sender;
        this.reciver = reciver;
        this.content = content;
        this.messageTime = messageTime;
    }

    public AMessage(String sender, String reciver, String content) {
        this.sender = sender;
        this.reciver = reciver;
        this.content = content;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.messageTime = LocalDateTime.now().format(formatter);
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
