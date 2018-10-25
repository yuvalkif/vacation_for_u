package Model;

import java.sql.*;

import Logger.Logger;
import sample.Controller;
import View.*;

public class Model implements ISQLModel{

    private IView view;
    private Controller controller;

    public Model() {

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

    public void setView(View view) {
        this.view = view;
    }

    public void setView(IView view) {
        this.view = view;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void insert(String userName, String password, String firstName, String lastName, String city, java.util.Date birthDate) {
        String sql = "INSERT INTO users(username, password, birth_date, first_name, last_name ,address) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = this.controller.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, city);
            pstmt.setDate(6, new Date(1,2,3));
            pstmt.executeUpdate();
            System.out.println("userName = [" + userName + "], password = [" + password + "], firstName = [" + firstName + "], lastName = [" + lastName + "], city = [" + city + "], birthDate = [" + birthDate + "]");
            this.controller.closeConnection(conn);
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
        try(Connection connection = this.controller.openConnection() ;
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
}