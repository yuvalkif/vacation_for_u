package Model;

import Connections.sqlLiteJDBCDriverConnection;
import Logger.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;

import dbObjects.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Control.Controller;

public class Model implements ISQLModel {
    private Controller controller;
    private sqlLiteJDBCDriverConnection driver = new sqlLiteJDBCDriverConnection();
    private String loggedUser = "";
    private final String SYSTEM = "Vacation4u system";

    public Model() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    /****************************************** TABLES CREATION ************************************************/


    /**
     * create a new users table
     */
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

    public void createVacationsTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS vacations (\n"
                + "	publisherUserName text NOT NULL,\n"
                + "	flightCompany text NOT NULL,\n"
                + "	fromDate DATE,\n"
                + "	untilDate DATE,\n"
                + "	baggageIncluded text NOT NULL,\n"
                + "	numberOfTickets INTEGER NOT NULL,\n"
                + "	destination text NOT NULL,\n"
                + "	twoDirections INTEGER NOT NULL,\n"
                + "	ticketType text NOT NULL,\n"
                + "	vacationType text NOT NULL,\n"
                + "	includeSleep INTEGER NOT NULL,\n"
                + "	hotelName text,\n"
                + " hotelRank DOUBLE,\n"
                + " vacationId text PRIMARY KEY,\n"
                + " sold Integer NOT NULL,\n"
                + " price DOUBLE NOT NULL,\n"
                + " freezed INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table vacations");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table vacations");
        }
    }



    @Override
    public void createPurchaseTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS purchases (\n"
                + "	cardOwnerUserName text NOT NULL,\n"
                + "	cardOwnerName text NOT NULL,\n"
                + "	cardType text NOT NULL ,\n"
                + "	cardNumber text NOT NULL,\n"
                + "	cardCvv text NOT NULL,\n"
                + "	cardExpireDate DATE NOT NULL,\n"
                + " targetVacation text NOT NULL,\n"
                + " PRIMARY KEY (cardOwnerUserName, targetVacation)"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table purchases");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table purchases");
        }

    }

    @Override
    public void createOffersTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS offers (\n"
                + "	vacationId text NOT NULL,\n"
                + "	buyerUserName text NOT NULL,\n"
                + "	purchseOfferTime text NOT NULL ,\n"
                + " PRIMARY KEY (vacationId, buyerUserName)"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table offers");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table offers");
        }

    }


    private void measureTimeForFreeze(String id) {

        Vacation vacation = getVacationAsObjectById(id);

        long time = System.currentTimeMillis();
        while (time < 5000 * 60) {
            time += System.currentTimeMillis();
        }

        if (!vacation.isSold()) {
            unFreezeVacation(vacation.getPVacationID());
            Logger.getInstance().log("freezed vacation " + id + " after 5 minutes");
        }
    }

    @Override
    public void createConfirmMessageTable() {
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS messages (\n"
                + "	senderUserName text NOT NULL,\n"
                + "	reciverUserName text NOT NULL,\n"
                + "	creationTime text NOT NULL ,\n"
                + " messageType text NOT NULL ,\n"
                + " messageContent text NOT NULL , \n"
                + " status text NOT NULL , \n"
                + " vacationId text NOT NULL , \n"
                + " PRIMARY KEY (senderUserName, reciverUserName, vacationId)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table messages");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table messages");
        }

    }

    /**
     * this is only a demo of credit card validation in front of the credit card company
     */
    @Override
    public void createCreditCardPoolTable(){

        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS credit_cards (\n"
                + "	cardOwnerName text PRIMARY KEY,\n"
                + "	cardType text NOT NULL ,\n"
                + "	cardNumber text NOT NULL,\n"
                + "	cardCvv text NOT NULL,\n"
                + "	cardExpireDate DATE NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table credit_cards");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table credit_cards");
        }

    }

    /*****************************************  INSERTION TO DB FUNCTIONS *****************************/


     /**
     * insert a user to the database
     * @param user a record with fields of the user
     */
    public void insertUser(User user) {
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







    public boolean checkTicketExist(String ticketId){
        Vacation v = getVacationAsObjectById(ticketId);
        return v!=null;

    }






    @Override
    public boolean insertVacation(Vacation vacationValues) {

        String sql = "INSERT INTO vacations(publisherUserName,flightCompany,fromDate,untilDate,baggageIncluded,numberOfTickets,destination,twoDirections,ticketType,vacationType,includeSleep,hotelName,hotelRank,sold,price,freezed,vacationId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        //processing dates
        int includeSleep = (vacationValues.isIncludeSleep()) ? 1 : 0;
        int roundTrip = (vacationValues.isTwoDirections()) ? 1 : 0;
        int sold = (vacationValues.isSold() == true) ? 1 : 0;
        int freezed = (vacationValues.isFreezed()) ? 1 : 0;

        try {
            Connection conn = this.openConnection();
            int index = 0;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vacationValues.getPublisherUserName());
            pstmt.setString(2, vacationValues.getFlightCompany());
            pstmt.setDate(3, vacationValues.getFromDate());
            pstmt.setDate(4, vacationValues.getUntilDate());
            pstmt.setString(5, vacationValues.getBaggageIncluded());
            pstmt.setInt(6, vacationValues.getNumberOfTickets());
            pstmt.setString(7, vacationValues.getDestination());
            pstmt.setInt(8, roundTrip);
            pstmt.setString(9, vacationValues.getTicketType());
            pstmt.setString(10, vacationValues.getVacationType());
            pstmt.setInt(11, includeSleep);
            pstmt.setString(12, vacationValues.getHotelName());
            pstmt.setDouble(13, vacationValues.getHotelRank());
            pstmt.setInt(14, sold);
            pstmt.setDouble(15, vacationValues.getPrice());
            //by default freeze is off
            pstmt.setInt(16, freezed);
            pstmt.setString(17,vacationValues.getVacationID());
            pstmt.executeUpdate();
//            String sqlGetlastInsertId = "select last_insert_rowid()";
//            Statement s = conn.createStatement();
//            ResultSet rs = s.executeQuery(sqlGetlastInsertId );
//            System.out.println(sqlGetlastInsertId +" this is the id , and type: "+(sqlGetlastInsertId).getClass());
//            this.closeConnection(conn);
//
//            insertMessage("SYSTEM",vacationValues.getPublisherUserName(),LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    "conform","succesfuly added a vacation","ReadOnly",1);
            Logger.getInstance().log("INSERT : " + vacationValues.toString() + "- SUCCESS");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log(e.getMessage());
            return false;
        }

    }

    /**
     * this will add the requested vacation by id to the currently offerd table.
     *
     * @param vacationId
     * @param buyerUsername
     * @param purchaseOfferDetails
     * @return success or not
     */
    @Override
    public boolean insertBuyingOffer(String vacationId, String buyerUsername,Purchase purchaseOfferDetails) {

        String sqlStatement = "INSERT INTO offers(vacationId, buyerUsername,purchseOfferTime) VALUES(?,?,?)";
        Vacation vacation = getVacationAsObjectById(vacationId);
        String theTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, vacationId);
            pstmt.setString(2, buyerUsername);
            pstmt.setString(3, theTimeNow);

            pstmt.executeUpdate();
            this.closeConnection(conn);
            /**
            Boolean creditAuthed  =  isValidCreditCard(purchaseOfferDetails);
            if(!creditAuthed)
                return false;
             **/
            freezeVacation(vacationId);
            Thread t1 = new Thread(()-> {measureTimeForFreeze(vacationId);});
            t1.start();

            insertMessage(controller.getLoggedUser(),vacation.getPublisherUserName(),theTimeNow,
                    "confirm",buyerUsername+ " wants to buy your vacation, id: "+vacationId,"waiting",vacationId);
//                    null,"standby")));
            insertPurchase(purchaseOfferDetails,vacationId);
            Logger.getInstance().log("INSERT Buying Offer on vacationID: " + vacationId + " By user: " + buyerUsername + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log("INSERT Buying Offer on vacationID: " + vacationId + " By user: " + buyerUsername + " - FAILED");
            return false;
        }
        return true;

    }


    private void insertPurchase(Purchase purchase, String vacationId) {
        String sqlStatement = "INSERT INTO purchases(cardOwnerUserName, cardOwnerName,cardType,cardNumber,cardCvv,cardExpireDate,targetVacation) VALUES(?,?,?,?,?,?,?)";


        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, purchase.getCardOwnerUserName());
            pstmt.setString(2, purchase.getCardOwnerName());
            pstmt.setString(3, purchase.getCardType());
            pstmt.setString(4, purchase.getCardNumber());
            pstmt.setString(5, purchase.getCardCvv());
            pstmt.setDate(6, purchase.getCardExpireDate());
            pstmt.setString(7, vacationId);
            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT : " + purchase.toString() + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log("INSERT : " + purchase.toString() + " - FAILED");
        }
    }

    private void insertCreditCard(Purchase purchase) {
        String sqlStatement = "INSERT INTO credit_cards(cardOwnerName,cardType,cardNumber,cardCvv,cardExpireDate) VALUES(?,?,?,?,?)";

        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, purchase.getCardOwnerName());
            pstmt.setString(2, purchase.getCardType());
            pstmt.setString(3, purchase.getCardNumber());
            pstmt.setString(4, purchase.getCardCvv());
            pstmt.setDate(5, purchase.getCardExpireDate());
            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT into creadit cards: " + purchase.toString() + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log("INSERT : into credit cards" + purchase.toString() + " - FAILED");
        }
    }

    public void insertMessage(String senderUserName ,String reciverUserName,String creationTime,String messageType,String messageContent,String status,String vacationId ) {
        try {
            String sql = "INSERT INTO messages(senderUserName,reciverUserName,creationTime,messageType,messageContent,status,vacationId) VALUES(?,?,?,?,?,?,?)";
            Connection conn = this.openConnection();
            int index = 0;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, senderUserName);
            pstmt.setString(2, reciverUserName);
            pstmt.setString(3, creationTime);
            pstmt.setString(4, messageType);
            pstmt.setString(5, messageContent);
            pstmt.setString(6, status);
            pstmt.setString(7, vacationId);

            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT Message, content : " + messageContent + "- SUCCESS");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("in insertMessage");
        }

    }


    /***************************************** UPDATE FUNCTIONS *********************************************/

     /**
     * update a user in the database
     * @param username the old username for which to update fields
     * @param newUserName
     * @param password
     * @param firstName
     * @param lastName
     * @param city
     * @param birthDate
     */
    public void updateUsers(String username, String newUserName, String password, String firstName, String lastName, String city, String birthDate) {
        String sqlStatement = "";
        String sqlStatementPreFix = "UPDATE users SET ";
        StringJoiner joiner = new StringJoiner(", ");
        int sqlArgsCount = 1;
        int[] statementIdx = new int[7];

        try {

            Connection conn = this.openConnection();
            if (!newUserName.trim().isEmpty()) {
                joiner.add("username = ?");
            }
            if (!password.trim().isEmpty()) {
                joiner.add("password = ?");
            }
            if (!firstName.trim().isEmpty()) {
                joiner.add("first_name = ?");
            }
            if (!lastName.trim().isEmpty()) {
                joiner.add("last_name = ?");
            }
            if (!city.trim().isEmpty()) {
                joiner.add("address = ?");
            }
            if (!birthDate.trim().isEmpty()) {
                joiner.add("birth_date = ?");
            }
            statementIdx[6] = sqlArgsCount;
            if (joiner.toString() != "") {
                String sqlArgs = joiner.toString();
                sqlStatement = sqlStatementPreFix + sqlArgs + " WHERE username = ? ;";
            } else if (sqlStatement == "") {
                return;
            }

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            if (!newUserName.trim().isEmpty() && newUserName != null) {
                pstmt.setString(sqlArgsCount++, newUserName);
            }
            if (!password.trim().isEmpty()) {
                pstmt.setString(sqlArgsCount++, password);
            }
            if (!firstName.trim().isEmpty()) {
                pstmt.setString(sqlArgsCount++, firstName);
            }

            if (!lastName.trim().isEmpty()) {
                pstmt.setString(sqlArgsCount++, lastName);
            }
            if (!city.trim().isEmpty()) {
                pstmt.setString(sqlArgsCount++, city);
            }
            if (!birthDate.trim().isEmpty()) {
                pstmt.setDate(sqlArgsCount++, dateConvert(birthDate));
            }
            if (sqlStatement != "") {
                pstmt.setString(sqlArgsCount, username);
            }
            pstmt.executeUpdate();

            this.closeConnection(conn);
            Logger.getInstance().log("Update : " + username + " , " + password + " - SUCCESS");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void freezeVacation(String vacationId) {
        String sqlStatement = "UPDATE vacations SET freezed = 1 WHERE vacationId = " + "'" + vacationId + "'";
        try {

            Connection conn = this.openConnection();

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);

            pstmt.executeUpdate();

            this.closeConnection(conn);
            Logger.getInstance().log("Update  , freezing vacation : " + vacationId + " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("Update  , freezing vacation : " + vacationId + " - Failed");
        }
    }

    @Override
    public void unFreezeVacation(String vacationId) {
        String sqlStatement = "UPDATE vacations SET freezed = 0 WHERE vacationId = " + "'" + vacationId + "'";
        try {

            Connection conn = this.openConnection();

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);

            pstmt.executeUpdate();

            this.closeConnection(conn);
            Logger.getInstance().log("Update  , unfreezing vacation : " + vacationId + " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("Update  , unfreezing vacation : " + vacationId + " - Failed");
        }
    }


    private void markVacationAsSold(String vacationId) {
        String sqlStatement = "UPDATE vacations SET sold = 1 WHERE vacationId = " + "'" + vacationId + "'";
        try {

            Connection conn = this.openConnection();

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);

            pstmt.executeUpdate();

            this.closeConnection(conn);
            Logger.getInstance().log("Update  , mark as sold vacation : " + vacationId + " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("Update  , mark as sold vacation : " + vacationId + " - Failed");
        }

    }


    @Override
    public void acceptMessage(ConfirmOfferMessage msg ) {
        String sqlStatement = "UPDATE messages SET status = 'accept' WHERE vacationId = " + "'" + msg.getVacation().getVacationID() + "'";

        try {

            Connection conn = this.openConnection();

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);

            pstmt.executeUpdate();


            this.closeConnection(conn);
            //delete the old message
            deleteMessage(msg.getSender(),msg.getReciver(),msg.getVacation().getVacationID());
            //send to the buyer
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeNow = LocalDateTime.now().format(formatter);
            String recpiet =timeNow+"\n"+msg.getSender()+"\n"+msg.getVacation().toString()+"\n"+" CONTACT: 09320148304 \n   ENJOY";
            insertMessage(SYSTEM,msg.getSender(),timeNow,"confirm",recpiet,"accept",msg.getVacation().getVacationID());
            //send to the seller
            insertMessage(SYSTEM,msg.getReciver(),timeNow,"confirm","username: "+msg.getSender()+" has bought your vacation: "+msg.getVacation()+" we sent you: "+msg.getVacation().getPrice(),"accept",
                    msg.getVacation().getVacationID());
            Vacation v = getVacationAsObjectById(msg.getVacation().getVacationID());
            markVacationAsSold(msg.getVacation().getVacationID());
            Logger.getInstance().log("accepting message:  : " + msg.getVacation().getVacationID() +" "+msg.getSender() +" "+ msg.getReciver()+ " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("accepting message:  : " + msg.getVacation().getVacationID() +" "+msg.getSender() +" "+msg.getReciver()+ " - FAILURE");
        }
    }

    @Override
    public void declineMessage(ConfirmOfferMessage msg) {

    }


    /*********************************************** SEARCHING FUNCTIONS************************************************/


     /**
     * returns all the records in the database
     * @return a list with all the records
     */
    public ObservableList selectAllDataBase() {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM users";
        ObservableList result = null;

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertUsersResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
            Logger.getInstance().log(var6.getMessage());
        }

        return result;
    }

    /**
     * search a record by a field given
     *
     * @param username the username of the record
     * @return a list with all the records
     */
    public ObservableList<User> searchRecordsByFields(String username) {
        ResultSet resultSet;
        ObservableList result = null;
        String sql = "SELECT * FROM users WHERE username = " + "'" + username + "'";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertUsersResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
        }

        return result;
    }

    private boolean isValidCreditCard(Purchase purchase){
        ResultSet resultSet;
        ObservableList result = null;
        boolean ans = false;
        String sql = "SELECT * FROM credit_cards WHERE cardOwnerName = " + "'" + purchase.getCardOwnerName() + "'"+
                ", cardType = "+ "'" + purchase.getCardType() + "'"+
                ", cardNumber = "+ "'" + purchase.getCardType() + "'"+
                ", cardCvv = "+ "'" + purchase.getCardType() + "'"+
                ", cardExpireDate = "+ "'" + purchase.getCardType() + "'";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            if(resultSet.next())
                ans=true;
            Logger.getInstance().log("did purchase: "+purchase.toString() +" successed? : "+ans);
            conn.close();
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            Logger.getInstance().log("did purchase: "+purchase.toString() +" successed? : no it catched exception");

        }

        return ans;
    }

    @Override
    public ObservableList getAllVacations() {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM vacations";
        ObservableList result = null;

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertVacationResultsToObservableList(resultSet,false);
            conn.close();
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
            Logger.getInstance().log(var6.getMessage());
        }

        return result;
    }

    @Override
    public ObservableList<Vacation> getVacations(String dest) {

        String sql = "SELECT * FROM vacations WHERE destination = " + "'" + dest + "'";
        ResultSet resultSet;
        ObservableList result = null;
        try {

            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertVacationResultsToObservableList(resultSet,false);
            Logger.getInstance().log("SUCCSSES retrive vacations to dest: " + dest);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("FAILED TO retrive vacations to dest: " + dest);

        }
        return result;
    }


    /******************************************** DELETE FUNCS****************************************/

     /**
     * delete a record from the data base
     * @param userName the username of the user as it appears in the database
     */
    public void deleteUsers(String userName) {
        String sql = "DELETE FROM users WHERE username = ? ";
        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.executeUpdate();
            Logger.getInstance().log("DELETED " + userName);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("FAILED TO DELETE " + userName);

        }


    }

    public void deleteMessage(String sender,String reciver , String vacaitonId) {
        String sql = "DELETE FROM messages WHERE vacationId = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vacaitonId);
            //stmt.setString(2, reciver);
           // stmt.setString(3, vacaitonId);
            stmt.executeUpdate();
            Logger.getInstance().log("DELETED " + sender + reciver + vacaitonId);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getInstance().log("FAILED TO DELETE " +  sender + reciver + vacaitonId);

        }


    }

    /********************************************   LOGIN *************************************************/

    @Override
    public AUserData login(String username, String password) {
        ResultSet resultSet;
        ObservableList<User> result;
        boolean auth = false;
        String sql = "SELECT * FROM users WHERE username = " + "'" + username + "'";;

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
//            resultSet = stmt.executeQuery(sql)
//            PreparedStatement stmt = conn.prepareStatement(sql);
            resultSet = stmt.executeQuery(sql);
            result = this.convertUsersResultsToObservableList(resultSet);
            conn.close();
            if (result.size() > 0) {
                if (result.get(0).getPassword().equals(password)) {
                    auth = true;
                    AUserData serverResponse = getUserData(username);
                    loggedUser = serverResponse.getUserName();

                    return serverResponse;
                }
            }

            return null;

        } catch (SQLException var7) {
            System.out.println("in login");
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            return null;

        }

    }


    /***************************************  RESULTSET TO OBSERVABLE LIST   ****************************/


    private ObservableList<User> convertUsersResultsToObservableList(ResultSet resultSet) {
        ObservableList<User> observableList = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                java.util.Date myDate = resultSet.getDate("birth_date");
                observableList.add(new User(resultSet.getString(1), resultSet.getString(2), dateToStringConvert(resultSet.getDate(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return observableList;
    }



    private ObservableList<AMessage> convertInMessageResultsToObservableList(ResultSet resultSet) {
        ObservableList<AMessage> observableList = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        try {
            while (resultSet.next()) {
                LocalDateTime creationTime = convertStringToLocalDateTime(resultSet.getString("creationTime"));
                String sender = resultSet.getString("senderUserName");
                String reciver = resultSet.getString("reciverUserName");
                String content = resultSet.getString("messageContent");
                String msgType = resultSet.getString("messageType");
                String status = resultSet.getString("status");
                String vacationId = resultSet.getString("vacationId");
                boolean expired = false;
                boolean needToUnFreeze = false;
                if (getHoursGap(creationTime, now) > 48)
                    expired = true;
                if(getMinutesgap(creationTime,now) > 5)
                    unFreezeVacation(vacationId);
                AMessage msg = null;

                Vacation v = getVacationAsObjectById(vacationId);
                if (!expired)   //the vaction will be set from vacation table;
                    msg = new ConfirmOfferMessage(sender, reciver, content, v, status);
                else {
                    String expireExplain = "user: +" + sender + " tried to buy vacation: " + v.toString() + " but 48 have passed" +
                            "so offer is expired";
                    msg = new ExpiredOfferMessage(sender, reciver, expireExplain);
                }


                observableList.add(msg);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return observableList;
    }





    private ObservableList<Vacation> convertVacationResultsToObservableList(ResultSet resultSet,boolean showSoldAndFrozen) {
        ObservableList<Vacation> observableList = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                Date fromDate = resultSet.getDate("fromDate");
                Date untilDate = resultSet.getDate("untilDate");
                int numberOfTickets = resultSet.getInt("numberOfTickets");
                boolean twoDirections = (resultSet.getInt("numberOfTickets") == 1) ? true : false;
                boolean includeSleep = (resultSet.getInt("includeSleep") == 1) ? true : false;
                boolean sold = (resultSet.getInt("sold") == 1) ? true : false;
                double hotelRank = resultSet.getDouble("hotelRank");
                boolean freezed = (resultSet.getInt("freezed") == 1) ? true : false;
                double price = (resultSet.getDouble("price"));
                Vacation v = new Vacation(resultSet.getString(14), resultSet.getString(1),
                        resultSet.getString(2),
                        fromDate,
                        untilDate,
                        resultSet.getString(5),
                        numberOfTickets,
                        resultSet.getString(7),
                        twoDirections,
                        resultSet.getString(9),
                        resultSet.getString(10),
                        includeSleep,
                        resultSet.getString(12),
                        hotelRank, sold, freezed, price);
                if (!showSoldAndFrozen) {
                    if (!v.isFreezed() && !v.isSold()) {
                        observableList.add(v);
                    }
                } else observableList.add(v);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return observableList;
    }


    private ObservableList<AMessage> convertOutMessageResultsToObservableList(ResultSet resultSet) {
        ObservableList<AMessage> observableList = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        try {
            while (resultSet.next()) {
                LocalDateTime creationTime = convertStringToLocalDateTime(resultSet.getString("creationTime"));
                String sender = resultSet.getString("senderUserName");
                String reciver = resultSet.getString("reciverUserName");
                String content = resultSet.getString("messageContent");
                String msgType = resultSet.getString("messageType");
                String status = resultSet.getString("status");
                String vacationId = resultSet.getString("vacationId");
                boolean expired = false;
                if (getHoursGap(creationTime, now) > 48)
                    expired = true;

                AMessage msg = null;

                Vacation v = getVacationAsObjectById(vacationId);
                if (!expired)   //the vaction will be set from vacation table;
                    msg = new ConfirmOfferMessage(sender, reciver, content, v, status);
                else {
                    String expireExplain = "We are sorry, but the seller:  "+ sender +  "Vacation: "+v.toString()+" has not replied to your request in 48 hours so its expired" ;
                    msg = new ExpiredOfferMessage(sender, reciver, expireExplain);
                }


                observableList.add(msg);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return observableList;
    }






    /*******************************************  FROM DB TO OBJECT **********************************************/

    private Vacation getVacationAsObjectById(String vacationId) {
        ResultSet resultSet;
        ObservableList<Vacation> result = null;
        String sql = "SELECT * FROM vacations WHERE vacationId = " + "'" + vacationId + "'";
        Vacation ans = null;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertVacationResultsToObservableList(resultSet,true);
            conn.close();
            if(result.size()>0)
                ans = result.get(0);
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            System.out.println("unable to create Vacation Object from DB by ID");
        }

        return ans;

    }


    /******************************************** PRIVATE FUNCTIONS ***********************************************/


    /**
     * this is the server response with all the user data
     * this will sent after user login so the controller can set the view the right contents
     * c
     *
     * @param username
     * @return
     */
    public AUserData getUserData(String username) {
        //get user inMessages
        ResultSet resultSetIn;
        ResultSet resultSetOut;
        ObservableList<AMessage> inboundMessages = null;
        ObservableList<AMessage> outboundMessages = null;
        String sqlInboundMessages = "SELECT * FROM messages WHERE reciverUserName =" + "'" + username + "'";
        String sqlOutboundMessages = "SELECT * FROM messages WHERE senderUserName =" + "'" + username + "'";
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSetIn = stmt.executeQuery(sqlInboundMessages);
            //conn.close();
            conn = this.openConnection();
            resultSetOut = stmt.executeQuery(sqlOutboundMessages);
            conn.close();

            inboundMessages = this.convertInMessageResultsToObservableList(resultSetIn);
            outboundMessages = this.convertOutMessageResultsToObservableList(resultSetOut);
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            return null;
        }

        /***** NEED TO ADD OUTBOUND MESSAGES ****************/
        return new UserData(username, inboundMessages, outboundMessages);
        //check if they are not expired

        //if not , add as it is to user messages

        //else return an expire message

    }


    private boolean isDigit(char c) {

        if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0')
            return true;

        return false;
    }

    /**
     * converts a set of results from the database to a list
     * @param resultSet set with results
     * @return a list with records
     */


    /**
     * get the relevant fields for a query based on if a field is empty or not
     *
     * @param fields all fields wanted for the query
     * @return a string with the fields for the query
     */
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

    /**
     * convert a string to a date
     *
     * @param sDate
     * @return
     */
    private Date dateConvert(String sDate) {
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

    /**
     * convert from string to local date time so we can subtract with Period
     *
     * @param sDate time
     * @return time in LocalDateTime object
     */

    private LocalDateTime convertStringToLocalDateTime(String sDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime formatDateTime = LocalDateTime.parse(sDate, formatter);

        return formatDateTime;
    }

    private long getHoursGap(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds() / 60 / 60;

    }


    private long getMinutesgap(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds() / 60;

    }

    /**
     * convert a date to a string
     *
     * @param date
     * @return
     */
    private String dateToStringConvert(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String ans = df.format(date);
        return ans;
    }


    /**
     * open a connection to the database
     *
     * @return
     */
    private Connection openConnection() {
        Connection conn = this.driver.connect();
        Logger.getInstance().log("connection opened");
        return conn;
    }

    /**
     * close connection to the database
     *
     * @param connection
     */
    private void closeConnection(Connection connection) {
        try {
            connection.close();
            Logger.getInstance().log("connection closed");
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public String getLoggedUser() {
        return loggedUser;
    }

}



