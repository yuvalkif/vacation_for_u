package dbObjects;

import javafx.collections.ObservableList;

public class UserData extends AUserData {
    public UserData(String userName, ObservableList<AMessage> inMessages, ObservableList<AMessage> outMessages) {
        super(userName, inMessages, outMessages);
    }






    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", outMessages=" + outMessages +
                ", inMessages=" + inMessages +
                '}';
    }
}
