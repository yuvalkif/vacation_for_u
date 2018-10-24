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




    public void createNewTable(String name) {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+name+" (\n"
                + "	username text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	birth_date DATE NOT NULL,\n"
                + "	first_name text NOT NULL,\n"
                + "	last_name text NOT NULL,\n"
                + "	address text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table " + name);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table " + name);
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

    public void insertUser(String username, String password, Date birthDate, String first_name, String last_name, String address) {

    }

    @Override
    public void insert(String userName, String password, String firstName, String lastName, String city, java.util.Date birthDate) {
        String sql = "INSERT INTO users(username, password, birth_date, first_name, last_name ,address) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.controller.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, city);
            pstmt.setDate(6, (Date) birthDate);

            pstmt.executeUpdate();
            System.out.println("userName = [" + userName + "], password = [" + password + "], firstName = [" + firstName + "], lastName = [" + lastName + "], city = [" + city + "], birthDate = [" + birthDate + "]");
            this.controller.closeConnection();
            Logger.getInstance().log("INSERT : " + userName + " , " + password + " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log(e.getMessage());
        }
    }

    @Override
    public void remove(String userName) {

    }

    @Override
    public void update() {

    }
}
/**
    public void update(){

        try{
            Connection conn = sqlLiteJDBCDriverConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql))
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            this.controller.log(e.getMessage());
        }
    }

//    private Date dateConvertorToSql()

}
**/