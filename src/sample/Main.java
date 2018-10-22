package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

import Connections.sqlLiteJDBCDriverConnection;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        sqlLiteJDBCDriverConnection.connect();
        createNewTable();
        Model model = new Model();
        Date d = new Date(1995,05,05);
        model.insertUser("yuvals","1234",d,"yuva","ad","akdsada 454");

    }

    public static void main(String[] args) {
        launch(args);
    }


       public static void createNewTable() {
            // SQLite connection string
            String url = "jdbc:sqlite:vacation_for_u.db";

            // SQL statement for creating a new table
            String sql = "CREATE TABLE IF NOT EXISTS users (\n"
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
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        }
}



