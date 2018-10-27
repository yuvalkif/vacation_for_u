package Objects;

import java.sql.Date;

public class User {

    private String username , password , firstname , lastname , city;
    private String date ;

    public User(User record){
        this.username = record.username;
        this.password = record.password;
        this.firstname = record.firstname;
        this.lastname = record.lastname;
        this.date = record.date;
        this.city = record.city;
        this.date = record.date;
    }

    public User(String username , String password , String firstname , String lastname , String city ,String date){

        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.date = date;

    }

    public String getCity() {
        return city;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String toString(){
        return "" + this.username;
    }
}
