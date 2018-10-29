package View;

import java.util.ArrayList;

/*user of 6 fields*/
public class User {

    private String userName;
    private String password;
    private String birthDate;
    private String firstName;
    private String lastName;
    private String city;

    public User(String userName, String password, String birthDate, String firstName, String lastName, String city){
        this.userName = userName;
        this.password = password;
        this .birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public User(ArrayList<String> userParams){
        if(userParams.size() > 6 )
            System.out.println("creation failed");
        this.userName = userParams.get(0);
        this.password = userParams.get(1);
        this .birthDate = userParams.get(2);
        this.firstName = userParams.get(3);
        this.lastName = userParams.get(4);
        this.city = userParams.get(5);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
