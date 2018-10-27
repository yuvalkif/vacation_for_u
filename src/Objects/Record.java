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

    public Record(String username , String password , String firstname , String lastname , String city , String date ){

        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;

    }

    private String dateToString(){

        String ans = "";

        return ans;
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
        return dateToString();
    }

    public String toString(){
        return "" + this.username;
    }

    /**
     * check if its a null record
     * @return if all fields are null return true
     */
    public boolean isNullRecord(){
        return username == null && password == null && lastname == null && firstname == null && city == null && date == null;
    }
}
