package Model;

import Connections.sqlLiteJDBCDriverConnection;
import Logger.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.StringJoiner;
import View.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Control.Controller;

public class Model implements ISQLModel {
    private Controller controller;
    private sqlLiteJDBCDriverConnection driver = new sqlLiteJDBCDriverConnection();

    public Model() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
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

    public void insert(User user) {
        String sql = "INSERT INTO users(username, password,first_name,last_name,address,birth_date) VALUES(?,?,?,?,?,?)";
        Date sqlDate = dateConvert(user.getDate());

        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstname());
            pstmt.setString(4, user.getLastname());
            pstmt.setString(5, user.getCity());
            pstmt.setDate(6, sqlDate);
            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT : " + user.getUsername() + " , " + user.getPassword() + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log(e.getMessage());
        }

    }

    public void deleteUsers(String userName) {
        String sql = "DELETE FROM users WHERE username = ? ";
        try{
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userName);
            stmt.executeUpdate();
            Logger.getInstance().log("DELETED " + userName);
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
            Logger.getInstance().log("FAILED TO DELETE " + userName);

        }


    }

    public void updateUsers(String username ,String newUserName, String password , String firstName, String lastName, String city, String birthDate) {
        String sqlStatement="";
        String sqlStatementPreFix="UPDATE users SET ";
        StringJoiner joiner = new StringJoiner(", ");
        int sqlArgsCount=1;
        int [] statementIdx = new int[7];

        try {

            Connection conn = this.openConnection();
            if(!newUserName.trim().isEmpty()){joiner.add("username = ?");}
            if(!password.trim().isEmpty()){joiner.add("password = ?");}
            if(!firstName.trim().isEmpty()){joiner.add("first_name = ?");}
            if(!lastName.trim().isEmpty()){joiner.add("last_name = ?");}
            if(!city.trim().isEmpty()){joiner.add("address = ?");}
            if(!birthDate.trim().isEmpty()){joiner.add("birth_date = ?");}
            statementIdx[6]=sqlArgsCount;
            if(joiner.toString()!="") {String sqlArgs = joiner.toString(); sqlStatement = sqlStatementPreFix +sqlArgs + " WHERE username = ? ;";}
            else if(sqlStatement==""){return;}

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            if(!newUserName.trim().isEmpty() && newUserName!=null){pstmt.setString(sqlArgsCount++, newUserName);}
            if(!password.trim().isEmpty()){pstmt.setString(sqlArgsCount++, password);}
            if(!firstName.trim().isEmpty()){pstmt.setString(sqlArgsCount++, firstName);}

            if(!lastName.trim().isEmpty()){pstmt.setString(sqlArgsCount++, lastName);}
            if(!city.trim().isEmpty()){pstmt.setString(sqlArgsCount++, city);}
            if(!birthDate.trim().isEmpty()){pstmt.setDate(sqlArgsCount++, dateConvert(birthDate));}
            if(sqlStatement!="") {pstmt.setString(sqlArgsCount, username);}
            pstmt.executeUpdate();

            this.closeConnection(conn);
            Logger.getInstance().log("Update : " + username + " , " + password + " - SUCCESS");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public ObservableList selectAllDataBase() {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM users";
        ObservableList result = null;

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
            Logger.getInstance().log(var6.getMessage());
        }

        return result;
    }

    public ObservableList<User> searchRecordsByFields(String username) {
        ResultSet resultSet ;
        ObservableList result = null;
        String sql = "SELECT * FROM users WHERE username = " + "'"+username+"'";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
        }

        return result;
    }

    private boolean checkDateformat(String date){

        if(date == null || date.length() != 10)
            return false;

        for(int i = 0 ; i < date.length() ; i++){
            if(i == 5 || i == 8)
                if(date.charAt(i) != '-')
                    return false;

            else
                if(!isDigit(date.charAt(i)))
                    return false;
        }

        return true;
    }

    private boolean isDigit(char c){

        if(c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0')
            return true;

        return false;
    }

    private ObservableList<User> convertResultsToObservableList(ResultSet resultSet) {
        ObservableList<User> observableList = FXCollections.observableArrayList();

        try {
            while(resultSet.next()) {
                java.util.Date myDate = resultSet.getDate("birth_date");
                observableList.add(new User(resultSet.getString(1),resultSet.getString(2) , dateToStringConvert(resultSet.getDate(3)), resultSet.getString(4), resultSet.getString(5) ,resultSet.getString(6)));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return observableList;
    }

    private String getFieldsForQuery(User fields) {
        String ans = "";
        if (!fields.getUsername().equals("")) {
            ans = ans + "username = '" + fields.getUsername() + "',";
        }

        if (!fields.getPassword().equals("")) {
            ans = ans + "password = " + fields.getPassword() + ",";
        }

        if (!fields.getFirstname().equals("")) {
            ans = ans + "first_name = " + fields.getFirstname() + ",";
        }

        if (!fields.getLastname().equals("")) {
            ans = ans + "last_name = " + fields.getLastname() + ",";
        }

        if (!fields.getCity().equals("")) {
            ans = ans + "address = " + fields.getCity() + ",";
        }

        return ans.substring(0, ans.length() - 1);
    }

    private Date dateConvert(String sDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
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

    private String dateToStringConvert(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String ans = df.format(date);
        return ans;
    }

    private Connection openConnection() {
        Connection conn = this.driver.connect();
        Logger.getInstance().log("connection opened");
        return conn;
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
            Logger.getInstance().log("connection closed");
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
}



