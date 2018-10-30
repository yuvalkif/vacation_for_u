

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
import sample.Controller;

public class Model implements ISQLModel {
    private Controller controller;
    private sqlLiteJDBCDriverConnection driver = new sqlLiteJDBCDriverConnection();

    public Model() {
    }

    public void createUsersTable() {
        String url = "jdbc:sqlite:vacation_for_u.db";
        String sql = "CREATE TABLE IF NOT EXISTS users (\n\tusername text PRIMARY KEY,\n\tpassword text NOT NULL,\n\tbirth_date DATE ,\n\tfirst_name text NOT NULL,\n\tlast_name text NOT NULL,\n\taddress text NOT NULL\n);";

        try {
            Connection conn = DriverManager.getConnection(url);
            Throwable var4 = null;

            try {
                Statement stmt = conn.createStatement();
                Throwable var6 = null;

                try {
                    stmt.execute(sql);
                    Logger.getInstance().log("created new table users");
                } catch (Throwable var31) {
                    var6 = var31;
                    throw var31;
                } finally {
                    if (stmt != null) {
                        if (var6 != null) {
                            try {
                                stmt.close();
                            } catch (Throwable var30) {
                                var6.addSuppressed(var30);
                            }
                        } else {
                            stmt.close();
                        }
                    }

                }
            } catch (Throwable var33) {
                var4 = var33;
                throw var33;
            } finally {
                if (conn != null) {
                    if (var4 != null) {
                        try {
                            conn.close();
                        } catch (Throwable var29) {
                            var4.addSuppressed(var29);
                        }
                    } else {
                        conn.close();
                    }
                }

            }
        } catch (SQLException var35) {
            var35.printStackTrace();
            System.out.println(var35.getMessage());
            Logger.getInstance().log("failed to create new table users");
        }

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void insert(User user) {
        String sql = "INSERT INTO users(username, password,first_name,last_name,address,birth_date) VALUES(?,?,?,?,?,?)";
        Date sqlDate = dateConvertor(user.getDate());

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
        } catch (SQLException var10) {
            var10.printStackTrace();
            System.out.println(var10.getMessage());
            Logger.getInstance().log(var10.getMessage());
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

        if(!birthDate.equals("") && !birthDateValid(birthDate)){
            controller.raiseError("Invalid date format .\nPlease insert in the format yyyy-mm-dd");
            return;
        }
        String sqlStatement="";
        String sqlStatementPreFix="UPDATE users SET ";
        String usernewUserNameArg="",passowrdArg="",firstNameArg="",lastNameArg="",cityArg="",dateArg="";
        StringJoiner joiner = new StringJoiner(", ");
        int sqlArgsCount=1;
        int [] statementIdx = new int[7];
        System.out.println(username +newUserName+password);

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

            System.out.println(sqlStatement);
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            if(!newUserName.trim().isEmpty() && newUserName!=null){pstmt.setString(sqlArgsCount++, newUserName);}
            System.out.println(sqlArgsCount);
            if(!password.trim().isEmpty()){pstmt.setString(sqlArgsCount++, password);}
            if(!firstName.trim().isEmpty()){pstmt.setString(sqlArgsCount++, firstName);}
            System.out.println(sqlArgsCount);
            if(!lastName.trim().isEmpty()){pstmt.setString(sqlArgsCount++, lastName);}
            if(!city.trim().isEmpty()){pstmt.setString(sqlArgsCount++, city);}
            if(!birthDate.trim().isEmpty()){pstmt.setDate(sqlArgsCount++, dateConvertor(birthDate));}
            if(sqlStatement!="") {pstmt.setString(sqlArgsCount, username);}
                pstmt.executeUpdate();
            System.out.println(sqlStatement);
            System.out.println("Updated user, Those fields have changed: (nulls not changed) " + username + " To " + "userName = [" + newUserName + "], password = [" + password + "], firstName = [" + firstName + "], lastName = [" + lastName + "], city = [" + city + "], birthDate = [" + birthDate + "]");
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

    public ObservableList searchRecordsByFields(User fields) {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM users\nWHERE ";
        ObservableList result = null;
        sql = sql + this.getFieldsForQuery(fields);

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

    private ObservableList convertResultsToObservableList(ResultSet resultSet) {
        ObservableList observableList = FXCollections.observableArrayList();

        try {
            while(resultSet.next()) {
                observableList.add(resultSet.getString(1) + " " + resultSet.getString(2) + " " + dateToStringConvertor(resultSet.getDate(3)) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6));
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

    public Connection openConnection() {
        Connection conn = this.driver.connect();
        Logger.getInstance().log("connection opened");
        return conn;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
            Logger.getInstance().log("connection closed");
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    private boolean birthDateValid(String date){
        if(date.length() != 10)
            return false;

        for(int i = 0 ; i < date.length() ; i++)
            if(i == 5 || i== 8)
                if(date.charAt(i) != '-')
                    return false;
             else
                 if(!isDigit(date.charAt(i)))
                     return false;

        return true;
    }

    private boolean isDigit(char c){

        if(c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0')
            return true;

        return false;
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

    private String dateToStringConvertor(Date date){
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        String ans = df.format(date);
        return ans;
    }


}