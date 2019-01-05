package dbObjects;

public class ConfirmOfferMessage extends TransactionMessage {
    private String status;
    private Vacation vacation;
    public ConfirmOfferMessage(String sender, String reciver, String content,Vacation vacation,String status) {
        super(sender, reciver, content,vacation);
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConfirmOfferMessage{" +
                "status='" + status + '\'' +
                ", vacation=" + vacation +
                ", sender='" + sender + '\'' +
                ", reciver='" + reciver + '\'' +
                ", content='" + content + '\'' +
                ", messageTime='" + messageTime + '\'' +
                '}';
    }
}
