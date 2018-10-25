package Objects;

import java.sql.Date;

public class Record {

    private String username , password , firstname , lastname , city;
    private Date date ;

    public Record(Record record){
        this.username = record.username;
        this.password = record.password;
        this.firstname = record.firstname;
        this.lastname = record.lastname;
        this.date = record.date;
        this.city = record.city;
    }

    public Record(String username , String password , String firstname , String lastname , String city ){

        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;

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

    public Date getDate() {
        return date;
    }

    public String toString(){
        return "" + this.username;
    }
}
