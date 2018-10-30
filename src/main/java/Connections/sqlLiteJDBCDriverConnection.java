package Connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlLiteJDBCDriverConnection {

    private static Connection conn;


    /**
     * close the connection
     */
    public void closeConnection(){
        try{
            this.conn.close();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Connect to a sample database
     */
    public Connection connect() {

        try {
            // db parameters
            String url = "jdbc:sqlite:vacation_for_u.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }







}