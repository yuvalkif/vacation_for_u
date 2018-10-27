package Model;

import java.sql.*;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import Connections.sqlLiteJDBCDriverConnection;
import Logger.Logger;
import Objects.Record;
import sample.Controller;
import View.*;

public class Model implements ISQLModel{

    private Controller controller;
    private sqlLiteJDBCDriverConnection driver;

    public Model() {
        driver = new sqlLiteJDBCDriverConnection();
    }

    public void createUsersTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	username text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	birth_date DATE ,\n"
                + "	first_name text NOT NULL,\n"
                + "	last_name text NOT NULL,\n"
                + "	address text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table users");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table users");
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void insert(String userName, String password, String firstName, String lastName, String city, java.util.Date birthDate) {
        String sql = "INSERT INTO users(username, password, birth_date, first_name, last_name ,address) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, city);
            pstmt.setDate(6, new Date(1,2,3));
            pstmt.executeUpdate();
            System.out.println("userName = [" + userName + "], password = [" + password + "], firstName = [" + firstName + "], lastName = [" + lastName + "], city = [" + city + "], birthDate = [" + birthDate + "]");
            closeConnection(conn);
            //this.controller.closeConnection();
            Logger.getInstance().log("INSERT : " + userName + " , " + password + " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log(e.getMessage());
        }
    }

    @Override
    public void deleteUsers(String userName) {
        String sql = "DELETE FROM users WHERE username = ?" ;
        try(Connection connection = openConnection() ;
        PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,userName);
            pstmt.executeUpdate();
        }catch (SQLException e){
            Logger.getInstance().log("FAILED REMOVE " +  userName);
        }
    }

    @Override
    public void updateUsers() {

    }

    public List<Record> searchRecordsByFields(Record fields){

        List<Record> result = null ;
        //code here


        return result ;
    }

    //region CONNECTIONS
    /**
     * open a connection to the database and log it
     * @return new connection
     */
    public Connection openConnection(){

        Connection conn = driver.connect();
        Logger.getInstance().log("connection opened");
        return conn;
    }

    /**
     * close the connection to the database and log it
     */
    public void closeConnection(Connection connection){
        try {
            connection.close();
            Logger.getInstance().log("connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //endregion

}