package View;

import java.util.ArrayList;

/**
 * class for holding record fields from the database as an object .
 */

public class User {

    private String userName;
    private String password;
    private String birthDate;
    private String firstName;
    private String lastName;
    private String city;

    public User(String userName, String password, String firstName, String lastName, String city, String birthDate){
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
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

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return birthDate;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public boolean nullRecord(){
        return userName.equals("") && password.equals("") && firstName.equals("") && lastName.equals("") && city.equals("") && birthDate.equals("");
    }

    public boolean hasNullField(){
        return userName.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || city.equals("") || birthDate.equals("");
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
