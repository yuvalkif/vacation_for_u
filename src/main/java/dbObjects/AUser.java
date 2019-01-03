package dbObjects;


/**
 * class ressmable an abstract user which must a have a username and passowrd/
 */

public abstract class AUser {
    protected String userName;
    protected String password;

    public AUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
