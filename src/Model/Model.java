package Model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import Connections.sqlLiteJDBCDriverConnection;
import Logger.Logger;
import Objects.User;
import javafx.scene.control.ListView;
import sample.Controller;
import View.*;

import javax.swing.*;

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
    public void insert(String userName, String password, String firstName, String lastName, String city, java.sql.Date birthDate) {
        String sql = "INSERT INTO users(username, password, birth_date, first_name, last_name ,address) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, city);
            pstmt.setDate(6, birthDate);
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
            /*String sql = "UPDATE users SET name = ? , "
                + "capacity = ? "
                + "WHERE username = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getInstance().log("FAILED UPDATE " +  userName);
        }*/
    }

    public List<User> searchRecordsByFields(User fields){

        List<User> result = null ;
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



    public void createUser(User user){
        Date sqlUserBirthDate = dateConvertor(user.getDate());
        insert(user.getUsername(),user.getPassword(),user.getFirstname(),user.getLastname(),user.getCity(),sqlUserBirthDate);

    }


    private Date dateConvertor(String sDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        try {
            java.util.Date jDate = dateFormat.parse(sDate);
            java.sql.Date sqlDate = new java.sql.Date(jDate.getTime());
            return sqlDate;


        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("problem with parsing the date form string");
            return null;
        }

    }



    //search with params
    public ArrayList<User> findUser(String userName){
        ArrayList<User> searchResults = new ArrayList<>();
        String sql = "SELECT *"
                + "FROM users WHERE userName == ?";

        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the value
            pstmt.setString(1, userName);
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
//                System.out.println(rs.getString("birth_date"));

                   User user = new User(rs.getString("userName"),rs.getString("password")
                           ,rs.getString("first_name"),rs.getString("last_name"),rs.getString("address"),"19/02/1995");
                   searchResults.add(user);
//                System.out.println(rs.getString("userName") + "\t" +
//                        rs.getString("password") + "\t"
//                        +rs.getDate("birth_date")
//                System.out.println(searchResults.get(0).getCity());
            }

            System.out.println(searchResults.get(0).getLastname());

//            DefaultListModel<User> listModel = new DefaultListModel<User>();
//            // add all words from wordList to model
//            for(User u: searchResults){
//                listModel.addElement(u);
//            }
//
//            JList<User> userList = new JList<>(listModel);
//            return userList;
              return searchResults;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
//        System.out.println(searchResults);
        return null;

    }



}