package dbObjects;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public abstract class AUserData {
    protected String userName;
    protected ObservableList<AMessage> outMessages;
    protected ObservableList<AMessage> inMessages;

    public AUserData(String userName, ObservableList<AMessage> inMessages,ObservableList<AMessage> outMessages) {
        this.userName = userName;
        this.inMessages = inMessages;
        this.outMessages = outMessages;
    }

    public String getUserName() {
        return userName;
    }

    public ObservableList<AMessage> getOutMessages() {
        return outMessages;
    }

    public ObservableList<AMessage> getInMessages() {
        return inMessages;
    }
}
