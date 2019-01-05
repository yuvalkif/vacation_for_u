package dbObjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * class for holding record fields from the database as an object .
 */

public class User extends AUser{
    private String birthDate;
    private String firstName;
    private String lastName;
    private String city;
    private Rank userRank;
    private StringProperty pUserName;
    private StringProperty pPassword;
    private StringProperty pBirthDate;
    private StringProperty pFirstName;
    private StringProperty pLastName;
    private StringProperty pCity;


    public User(String userName, String password, String firstName, String lastName, String city, String birthDate){
        super(userName,password);
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.pUserName = new SimpleStringProperty(userName);
        this.pPassword = new SimpleStringProperty(password);
        this.pFirstName = new SimpleStringProperty(firstName);
        this.pLastName = new SimpleStringProperty(lastName);
        this.pCity = new SimpleStringProperty(city);
        this.pBirthDate = new SimpleStringProperty(birthDate);
        this.userRank = new Rank();
    }


    public User(String userName, String password, String firstName, String lastName, String city, String birthDate,Rank rank){
        super(userName,password);
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.pUserName = new SimpleStringProperty(userName);
        this.pPassword = new SimpleStringProperty(password);
        this.pFirstName = new SimpleStringProperty(firstName);
        this.pLastName = new SimpleStringProperty(lastName);
        this.pCity = new SimpleStringProperty(city);
        this.pBirthDate = new SimpleStringProperty(birthDate);
        this.userRank = rank;
    }

    public User(ArrayList<String> userParams){
        super(userParams.get(0),userParams.get(1));
        if(userParams.size() > 6 )
            System.out.println("creation failed");
        this .birthDate = userParams.get(2);
        this.firstName = userParams.get(3);
        this.lastName = userParams.get(4);
        this.city = userParams.get(5);
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
