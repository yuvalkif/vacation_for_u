package dbObjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * class for holding record fields from the database as an object .
 */

public class RegisteredUser extends AUser{
    private String birthDate;
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private Rank userRank;
    private StringProperty pUserName;
    private StringProperty pPassword;
    private StringProperty pBirthDate;
    private StringProperty pFirstName;
    private StringProperty pLastName;
    private StringProperty pCity;
    private StringProperty pEmail;



    public RegisteredUser(String userName, String password, String firstName, String lastName, String city, String birthDate, String email){
        super(userName,password);
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
        this.pUserName = new SimpleStringProperty(userName);
        this.pPassword = new SimpleStringProperty(password);
        this.pFirstName = new SimpleStringProperty(firstName);
        this.pLastName = new SimpleStringProperty(lastName);
        this.pCity = new SimpleStringProperty(city);
        this.pEmail = new SimpleStringProperty(email);
        this.pBirthDate = new SimpleStringProperty(birthDate);
        this.userRank = new Rank();
    }


    public RegisteredUser(String userName, String password, String firstName, String lastName, String city, String birthDate, Rank rank, String email){
        super(userName,password);
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
        this.pUserName = new SimpleStringProperty(userName);
        this.pPassword = new SimpleStringProperty(password);
        this.pFirstName = new SimpleStringProperty(firstName);
        this.pLastName = new SimpleStringProperty(lastName);
        this.pCity = new SimpleStringProperty(city);
        this.pEmail = new SimpleStringProperty(email);
        this.pBirthDate = new SimpleStringProperty(birthDate);
        this.userRank = rank;
    }

    public RegisteredUser(ArrayList<String> userParams){
        super(userParams.get(0),userParams.get(1));
        if(userParams.size() > 7 )
            System.out.println("creation failed");
        this .birthDate = userParams.get(2);
        this.firstName = userParams.get(3);
        this.lastName = userParams.get(4);
        this.city = userParams.get(5);
        this.email = userParams.get(6);
        this.userRank = new Rank();
    }


    public void rank(double score){
        this.userRank.addRanker(score);
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

    public String getEmail(){return email;}

    public boolean nullRecord(){
        return userName.equals("") && password.equals("") && firstName.equals("") && lastName.equals("") && city.equals("") && birthDate.equals("");
    }

    public boolean hasNullField(){
        return userName.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || city.equals("") || birthDate.equals("");
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getpUserName() {
        return pUserName.get();
    }

    public StringProperty pUserNameProperty() {
        return pUserName;
    }

    public String getpPassword() {
        return pPassword.get();
    }

    public StringProperty pPasswordProperty() {
        return pPassword;
    }

    public String getpBirthDate() {
        return pBirthDate.get();
    }

    public StringProperty pBirthDateProperty() {
        return pBirthDate;
    }

    public String getpFirstName() {
        return pFirstName.get();
    }

    public StringProperty pFirstNameProperty() {
        return pFirstName;
    }

    public String getpLastName() {
        return pLastName.get();
    }

    public StringProperty pLastNameProperty() {
        return pLastName;
    }

    public String getpCity() {
        return pCity.get();
    }

    public StringProperty pCityProperty() {
        return pCity;
    }

    public Rank getUserRank() {
        return userRank;
    }
}
