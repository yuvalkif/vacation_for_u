package Connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class sqlLiteJDBCDriverConnection {


    /**
     * Connect to a sample database
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:vacation_for_u.db";


            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getStackTrace());
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }







}