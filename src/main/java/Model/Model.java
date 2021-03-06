package Model;

import Connections.sqlLiteJDBCDriverConnection;
import Logger.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

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
                + "	rank DOUBLE NOT NULL,\n"
                + "	address text NOT NULL,\n"
                + "	email text NOT NULL\n"
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
                + "	askerUserName text NOT NULL,\n"
                + "	replierUserName text NOT NULL,\n"
                + "	vacationId text NOT NULL ,\n"
                + " creationTime DATE NOT NULL,\n"
                + " PRIMARY KEY (askerUserName, replierUserName,vacationId)"
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


    public void createCashRequestsTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS CashRequests (\n"
                + "	requestedVacationId text NOT NULL,\n"
                + "	askerUserName text NOT NULL,\n"
                + "	timeCreated text NOT NULL ,\n"
                + " PRIMARY KEY (requestedVacationId, askerUserName)"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table CashRequest");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table CashRequests");
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
     * This table will hold currently active trade requests
     * Table records:  [SENDER, OFFERDVACAATIONID, RECIVER, REQUESTEDVACATIONID,CREATIONTIME]
     *
     */
    public void createTradeRequestsTable(){
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS TradeRequests (\n"
                + "	senderUserName text NOT NULL,\n"
                + "	offeredVacationId text NOT NULL,\n"
                + "	reciverUserName text NOT NULL,\n"
                + "	requestedVacationId text NOT NULL,\n"
                + "	creationTime text NOT NULL ,\n"
                + " PRIMARY KEY (offeredVacationId, requestedVacationId)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new trade Requests table table messages");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table messages");
        }

    }



    /*****************************************  INSERTION TO DB FUNCTIONS *****************************/


     /**
     * insert a user to the database
     * @param user a record with fields of the user
     */
    public void insertUser(RegisteredUser user) {
        String sql = "INSERT INTO users(username, password,first_name,last_name,address,birth_date,rank,email) VALUES(?,?,?,?,?,?,?,?)";
        Date sqlDate = dateConvert(user.getDate());

        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstname());
            pstmt.setString(4, user.getLastname());
            pstmt.setString(5, user.getCity());
            pstmt.setDate(6, sqlDate);
            pstmt.setDouble(7, new Rank().getAverageScore());
            pstmt.setString(8, user.getEmail());
            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT : " + user.getUserName() + " , " + user.getPassword() + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log(e.getMessage());
        }

    }

    /**
     * add a new trade request to the data base
     * @param offeredVacationId
     * @param requestedVacationId
     */
    public void insertTradeRequests( String offeredVacationId , String requestedVacationId){

        //first find and get the vacations
        Vacation offeredVacation = getVacationById(offeredVacationId);
        Vacation requestedVacation = getVacationById(requestedVacationId);


        //check that the vacations are aviable
        if(offeredVacation.isSold() || offeredVacation.isFreezed() || requestedVacation.isSold() || requestedVacation.isFreezed())
            return;

        //create the sql statement


        String sql = "INSERT INTO TradeRequests(senderUserName, offeredVacationId,reciverUserName,requestedVacationId,creationTime) VALUES(?,?,?,?,?)";

        //get the curr time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String theTimeNow= LocalDateTime.now().format(formatter);

        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, offeredVacation.getOwnerUserName());
            pstmt.setString(2, offeredVacation.getVacationID());
            pstmt.setString(3, requestedVacation.getOwnerUserName());
            pstmt.setString(4, requestedVacation.getVacationID());
            pstmt.setString(5, theTimeNow);
            pstmt.executeUpdate();
            this.closeConnection(conn);


            //send a confirm message to the seller
//            System.out.println("currently logges user: "+controller.getLoggedUser() +" and requested is: "+requestedVacation.getOwnerUserName());
            insertMessage(controller.getLoggedUser(),requestedVacation.getOwnerUserName(),theTimeNow,
                    "confirmTrade",offeredVacation.getOwnerUserName()+ " wants to trade his vacatoin: "+offeredVacation.toPrint()+"\nwith your vacation " +requestedVacation.toPrint(),"waiting",requestedVacationId);

            //send a message to the buyer
//            insertMessage(SYSTEM,offeredVacation.getOwnerUserName(),theTimeNow,"regular","Your trade request sent to: "+requestedVacation.getOwnerUserName(),"regular",requestedVacationId);



            markVacationAsSold(requestedVacationId);
            markVacationAsSold(offeredVacationId);


            Logger.getInstance().log("INSERT : " + offeredVacation.getOwnerUserName() + " , " + requestedVacation.getOwnerUserName() + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log(e.getMessage());
        }

    }

    public boolean checkTicketExist(String ticketId){
        Vacation v = getVacationById(ticketId);
        return v!=null;

    }


    public boolean tradeRequestExist(String askerUserName,String replierUserName,String requestedVacationID){
        TradeRequest t = getTradeRequestAsObjectByAskerAndReplier(askerUserName,replierUserName,requestedVacationID);
        return t!=null;

    }


    public boolean CashRequestExist(String askerUserName,String replierUserName,String requestedVacationID){
        CashRequest t = getCashRequestAsObjectByAskerAndReplier(askerUserName,replierUserName,requestedVacationID);
        return t!=null;
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

            pstmt.setString(1, vacationValues.getOwnerUserName());
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
     * @return success or not
     */
    @Override
    public boolean insertCashRequest(String requestedVacationId) {

        String askerUserName = controller.getLoggedUser();

        //get the vacation

        Vacation requestedVacation = getVacationById(requestedVacationId);
//
//
//        String sql = "CREATE TABLE IF NOT EXISTS CashRequests (\n"
//                + "	requestedVacationId text NOT NULL,\n"
//                + "	askerUserName text NOT NULL,\n"
//                + "	timeCreated text NOT NULL ,\n"
//                + " PRIMARY KEY (requestedVacationId, askerUserName)"
//                + ");";
        String sqlStatement = "INSERT INTO CashRequests(requestedVacationId, askerUserName,timeCreated) VALUES(?,?,?)";
        Vacation vacation = getVacationById(requestedVacationId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String theTimeNow = LocalDateTime.now().format(formatter);;
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, requestedVacationId);
            pstmt.setString(2, askerUserName);
            pstmt.setString(3, theTimeNow);

            pstmt.executeUpdate();
            this.closeConnection(conn);
            /**
            Boolean creditAuthed  =  isValidCreditCard(purchaseOfferDetails);
            if(!creditAuthed)
                return false;
             **/
            //send message to the asker notify its request sent
//            insertMessage(SYSTEM,askerUserName,theTimeNow,"regular","Your buying request sent to: "+requestedVacation.getOwnerUserName(),"regular",requestedVacationId);

            //send a confirm message to the seller
            insertMessage(controller.getLoggedUser(),vacation.getOwnerUserName(),theTimeNow,
                    "confirmBuying",askerUserName+ " wants to buy your vacation, \nid: "+requestedVacationId+"\n you can contact him via: "+ getUserEmailByUserName(loggedUser),"waiting",requestedVacationId);
            markVacationAsSold(requestedVacationId);
            Logger.getInstance().log("INSERT Buying Offer on vacationID: " + requestedVacationId + " By user: " + askerUserName + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log("INSERT Buying Offer on vacationID: " + requestedVacationId + " By user: " + askerUserName + " - FAILED");
            return false;
        }
        return true;

    }

    private void insertPurchase(Purchase purchase) {
        String sqlStatement = "INSERT INTO purchases(askerUserName, replierUserName,vacationId,creationTime) VALUES(?,?,?,?)";

        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, purchase.getAskerUserName());
            pstmt.setString(2, purchase.getReplierUserName());
            pstmt.setString(3, purchase.getVacation().getVacationID());
            pstmt.setDate(4, purchase.getPurchaseApprovedTime());
            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT : " + purchase.toString() + " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log("INSERT : " + purchase.toString() + " - FAILED");
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
    public void updateUsers(String username, String newUserName, String password, String firstName, String lastName, String city, String birthDate,String email) {
        String sqlStatement = "";
        String sqlStatementPreFix = "UPDATE users SET ";
        StringJoiner joiner = new StringJoiner(", ");
        int sqlArgsCount = 1;
        int[] statementIdx = new int[8];

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
            if(!email.trim().isEmpty()){
                joiner.add("email = ?");
            }

            statementIdx[7] = sqlArgsCount;

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

            if(!email.trim().isEmpty()){
                pstmt.setString(sqlArgsCount++,email);
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
        String sqlStatement = "UPDATE vacations SET freeze = 1 WHERE vacationId = " + "'" + vacationId + "'";
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

    private void markVacationAsAviable(String vacationId) {
        String sqlStatement = "UPDATE vacations SET sold = 0 WHERE vacationId = " + "'" + vacationId + "'";
        try {

            Connection conn = this.openConnection();

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);

            pstmt.executeUpdate();

            this.closeConnection(conn);
            Logger.getInstance().log("Update  , mark as aviable vacation : " + vacationId + " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("Update  , mark as aviable vacation : " + vacationId + " - Failed");
        }

    }

    public AUserData getUpdatedViewContent(){
        if(controller.getLoggedUser() == null)
            return null;
        return getUserData(controller.getLoggedUser());
    }

    public void declineMessage(ConfirmOfferMessage msg ) {
        String sqlStatement = "UPDATE messages SET status = 'decline' WHERE vacationId = " + "'" + msg.getVacation().getVacationID() + "'";

        try {

            if(msg.getSender().equals(SYSTEM))
                return;

            if (msg.getSender().equals(SYSTEM))
                return;

            Connection conn = this.openConnection();

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);

            pstmt.executeUpdate();


            this.closeConnection(conn);
            //delete the old message
            deleteMessage(msg.getSender(),msg.getReciver(),msg.getVacation().getVacationID());
            //send to the buyer
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeNow = LocalDateTime.now().format(formatter);
            String decline = timeNow+"\n"+msg.getReciver()+" Has Declined your request\n on: Vacation" +"\n"+msg.getVacation().toPrint()+"\n"+" \nCONTACT: 09-3201483 \n We are sorry";
            insertMessage(SYSTEM,msg.getSender(),timeNow,"confirm",decline,"Decline",msg.getVacation().getVacationID());
            insertMessage(SYSTEM,msg.getReciver(),timeNow,"regular","you have declined :"+msg.getSender()+" request","regular",msg.getVacation().getVacationID());
            //send to the seller
            //msg.getVacation().getVacationID();
            Vacation v = getVacationById(msg.getVacation().getVacationID());
            markVacationAsAviable(msg.getVacation().getVacationID());
            Logger.getInstance().log("declining message:  : " + msg.getVacation().getVacationID() +" "+msg.getSender() +" "+ msg.getReciver()+ " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("declining message:  : " + msg.getVacation().getVacationID() +" "+msg.getSender() +" "+msg.getReciver()+ " - FAILURE");
        }
    }

    public void acceptMessage(ConfirmOfferMessage msg ) {
        String sqlStatement = "UPDATE messages SET status = 'accept' WHERE vacationId = " + "'" + msg.getVacation().getVacationID() + "'";

        try {
            if(msg.getSender().equals(SYSTEM))
                return;

            if (msg.getSender().equals(SYSTEM))
                return;

            Connection conn = this.openConnection();

            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);

            pstmt.executeUpdate();


            this.closeConnection(conn);
            //delete the old message
            deleteMessage(msg.getSender(),msg.getReciver(),msg.getVacation().getVacationID());
            //send to the buyer
            //TWO CASES:
            //TRADE ACCEPTED OR BUYING ACCEPTED


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeNow = LocalDateTime.now().format(formatter);
            //TRADE ACCEPTED
            if(tradeRequestExist(msg.getSender(),msg.getReciver(),msg.getVacation().getVacationID())) {
                //so its a trade request need to be accepted
                TradeRequest tr = getTradeRequestAsObjectByAskerAndReplier(msg.getSender(),msg.getReciver(),msg.getVacation().getVacationID());
                Vacation tradedVacationOfTheAsker = getVacationById(tr.getAskerVacationHeWantsToTrade().getVacationID());
                String recpietAsker ="TRADE SUCCESS"+"\n"+timeNow+"\n sender: "+msg.getSender()+"\n travel to: "+msg.getVacation().getDestination()+"\n"+" CONTACT: 09-3201483 \n   ENJOY";
                String recpietReciver ="TRADE SUCCESS"+"\n"+timeNow+"\n resiver: "+msg.getReciver()+"\n travel to: "+tradedVacationOfTheAsker.getDestination()+"\n"+" CONTACT: 09-3201483 \n   ENJOY";
                //send to the asker
                insertMessage(SYSTEM,msg.getSender(),timeNow,"confirm",recpietAsker,"accept",msg.getVacation().getVacationID());
                //send to the reciver
                insertMessage(SYSTEM,msg.getReciver(),timeNow,"confirm",recpietReciver,"accept",tradedVacationOfTheAsker.getVacationID());

                Purchase p1 = new Purchase(msg.getSender(),msg.getReciver(),Date.valueOf(LocalDate.now()),tradedVacationOfTheAsker);
                insertPurchase(p1);
                Purchase p2 = new Purchase(msg.getReciver(),msg.getSender(),Date.valueOf(LocalDate.now()),msg.getVacation());
                insertPurchase(p2);

            }

            //Cash ACCEPTED
            else if(CashRequestExist(msg.getSender(),msg.getReciver(),msg.getVacation().getVacationID())){
                String recpiet =timeNow+"\n sender: "+msg.getSender()+"\n travel to:"+msg.getVacation().toString()+"\n"+" CONTACT: 09320148304 \n   ENJOY";
                insertMessage(SYSTEM,msg.getSender(),timeNow,"confirm",recpiet,"accept",msg.getVacation().getVacationID());
                //send to the seller
                insertMessage(SYSTEM,msg.getReciver(),timeNow,"confirm","username: "+msg.getSender()+" \nhas bought your vacation: "+msg.getVacation().toPrint()+" \nfor the price of: "+msg.getVacation().getPrice(),"accept",
                        msg.getVacation().getVacationID());

                Purchase p = new Purchase(msg.getSender(),msg.getReciver(),Date.valueOf(LocalDate.now()),msg.getVacation());
                insertPurchase(p);
            }

            else
                 System.out.println("unable to find either in trades or in cash with this confirm mesg: "+msg.toString());

            Vacation v = getVacationById(msg.getVacation().getVacationID());
            markVacationAsSold(msg.getVacation().getVacationID());
            Logger.getInstance().log("accepting message:  : " + msg.getVacation().getVacationID() +" "+msg.getSender() +" "+ msg.getReciver()+ " - SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().log("accepting message:  : " + msg.getVacation().getVacationID() +" "+msg.getSender() +" "+msg.getReciver()+ " - FAILURE");
        }
    }


    /*********************************************** SEARCHING FUNCTIONS************************************************/


     /**
     * returns all the records in the database
     * @return a list with all the records
     */
    public ObservableList getAllUsers() {
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
    public ObservableList<RegisteredUser> searchRecordsByFields(String username) {
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
    public ObservableList<Vacation> getUserVacations() {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM vacations WHERE publisherUserName = " + "'" + loggedUser + "'";
        ObservableList<Vacation> result = null;

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

//        + "	senderUserName text NOT NULL,\n"
//                + "	reciverUserName text NOT NULL,\n"
//                + "	creationTime text NOT NULL ,\n"
//                + " messageType text NOT NULL ,\n"
//                + " messageContent text NOT NULL , \n"
//                + " status text NOT NULL , \n"
//                + " vacationId text NOT NULL , \n"
//                + " PRIMARY KEY (senderUserName, reciverUserName, vacationId)\n"
//                + ");";


        String sql = "DELETE FROM messages WHERE vacationId = ? AND reciverUserName = ? AND senderUserName = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vacaitonId);
            stmt.setString(2, reciver);
            stmt.setString(3, sender);
            stmt.executeUpdate();
            Logger.getInstance().log("DELETED " + sender + reciver + vacaitonId);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getInstance().log("FAILED TO DELETE " +  sender + reciver + vacaitonId);

        }
    }

    public void deleteTradeRequest(String offeredVacationId,String requestedVacationId) {
        String sql = "DELETE FROM messages WHERE offeredVacationId = ? AND requestedVacationId = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, offeredVacationId);
            stmt.setString(2, requestedVacationId);
            stmt.executeUpdate();
            Logger.getInstance().log("DELETED " + offeredVacationId + requestedVacationId);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getInstance().log("FAILED TO DELETE " + offeredVacationId + requestedVacationId);

        }
    }

    /********************************************   LOGIN *************************************************/

    @Override
    public AUserData login(String username, String password) {
        ResultSet resultSet;
        ObservableList<RegisteredUser> result;
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


    /***************************************  RESULTSET TO OBSERVABLE LIST   ***************************************/


    private ObservableList<RegisteredUser> convertUsersResultsToObservableList(ResultSet resultSet) {
        ObservableList<RegisteredUser> observableList = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                java.util.Date myDate = resultSet.getDate("birth_date");
                observableList.add(new RegisteredUser(resultSet.getString(1), resultSet.getString(2), dateToStringConvert(resultSet.getDate(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),resultSet.getString(7)));
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
//                if(getMinutesgap(creationTime,now) > 5)
//                    unFreezeVacation(vacationId);
                AMessage msg = null;

                Vacation v = getVacationById(vacationId);
                if (!expired)   //the vaction will be set from vacation table;
                    msg = new ConfirmOfferMessage(sender, reciver, content, v, status);
                else {
                    String expireExplain = "user: +" + sender + "\n tried to buy vacation: " + v.toString() + "\n but 48 hours have passed" +
                            "-so offer is expired";
//                    markVacationAsAviable(v.getVacationID());
                    msg = new ExpiredOfferMessage(sender, reciver, expireExplain, getVacationById(vacationId));
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
//                if (getHoursGap(creationTime, now) > 48)
//                    expired = true;

                AMessage msg = null;

                Vacation v = getVacationById(vacationId);
                if (!expired)   //the vaction will be set from vacation table;
                    msg = new ConfirmOfferMessage(sender, reciver, content, v, status);
                else {
                    String expireExplain = "We are sorry,\n but the seller:  "+ sender +  "\nVacation: "+v.toString()+" \nhas not replied to your request in 48 hours - so the request expired" ;
                    msg = new ExpiredOfferMessage(sender, reciver, expireExplain, getVacationById(vacationId));
                }


                observableList.add(msg);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return observableList;
    }






    /*******************************************  FROM DB TO OBJECT **********************************************/

    public Vacation getVacationById(String vacationId) {
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


    public String getUserEmailByUserName(String userName) {
        ResultSet resultSet;
        ObservableList<Vacation> result = null;
        String sql = "SELECT * FROM users WHERE userName = " + "'" + userName + "'";
        String email = null;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            email = resultSet.getString("email");
            conn.close();
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            System.out.println("unable to create Email Object from DB by ID");
        }
        return email;
    }
//
//
    private TradeRequest getTradeRequestAsObjectByAskerAndReplier(String askerUserName,String replierUserName,String requestedVacationId) {
        ResultSet resultSet;


        String sql = "SELECT * FROM TradeRequests WHERE requestedVacationId = " + "'" + requestedVacationId + "' AND senderUserName = "+ "'" + askerUserName + "' AND reciverUserName = "+ "'" + replierUserName + "'";

        TradeRequest ans = null;
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            if (!resultSet.next())
                return null;
            String senderUserName = resultSet.getString("senderUserName");
            String timeCreated = resultSet.getString("creationTime");
            String askerVacationHeWantsToTrade = resultSet.getString("offeredVacationId");
            String requestedVacation = resultSet.getString("requestedVacationId");
            ans=  new TradeRequest(senderUserName,timeCreated, getVacationById(askerVacationHeWantsToTrade), getVacationById(requestedVacation));

            conn.close();
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            System.out.println("unable to create tradeRequest Object from DB by ID");
        }

        return ans;

    }





        private CashRequest getCashRequestAsObjectByAskerAndReplier(String askerUserName,String replierUserName,String requestedVacationId) {
        ResultSet resultSet;


        String sql = "SELECT * FROM CashRequests WHERE requestedVacationId = " + "'" + requestedVacationId + "' AND askerUserName = "+ "'" + askerUserName + "'";
            CashRequest ans = null;
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            if (!resultSet.next())
                return null;
            String senderUserName = resultSet.getString("askerUserName");
            String timeCreated = resultSet.getString("timeCreated");
            String requestedVacation = resultSet.getString("requestedVacationId");
            ans=  new CashRequest(senderUserName, getVacationById(requestedVacation),timeCreated);

            conn.close();
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            System.out.println("unable to create CashRequest Object from DB by ID");
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
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSetIn = stmt.executeQuery(sqlInboundMessages);
            inboundMessages = this.convertInMessageResultsToObservableList(resultSetIn);
//            stmt = conn.createStatement();
//            resultSetOut = stmt.executeQuery(sqlOutboundMessages);
            conn.close();

//            outboundMessages = this.convertOutMessageResultsToObservableList(resultSetOut);
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
            return null;
        }




        String sqlOutboundMessages = "SELECT * FROM messages WHERE senderUserName =" + "'" + username + "'";
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSetOut = stmt.executeQuery(sqlOutboundMessages);
            outboundMessages = this.convertOutMessageResultsToObservableList(resultSetOut);
//            stmt = conn.createStatement();
//            resultSetOut = stmt.executeQuery(sqlOutboundMessages);
            conn.close();

//            outboundMessages = this.convertOutMessageResultsToObservableList(resultSetOut);
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
    private String getFieldsForQuery(RegisteredUser fields) {
        String ans = "";
        if (!fields.getUserName().equals("")) {
            ans = ans + "username = '" + fields.getUserName() + "',";
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

//    public String getLoggedUser() {
  //      return loggedUser;
   // }

}



