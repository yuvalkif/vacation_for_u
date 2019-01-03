package dbObjects;

public class ARequest {

    protected String askerUserName;
    protected String timeCreated;

    public ARequest(String askerUserName, String timeCreated) {
        this.askerUserName = askerUserName;
        this.timeCreated = timeCreated;
    }

    public String getAskerUserName() {
        return askerUserName;
    }

    public String getTimeCreated() {
        return timeCreated;
    }
}
