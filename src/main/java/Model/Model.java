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

import dbObjects.*;
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
                + "	fromDate DATE ,\n"
                + "	untilDate DATE ,\n"
                + "	baggageIncluded text NOT NULL,\n"
                + "	numberOfTickets INTEGER NOT NULL,\n"
                + "	destination text NOT NULL,\n"
                + "	twoDirections INTEGER NOT NULL,\n"
                + "	ticketType text NOT NULL,\n"
                + "	vacationType text NOT NULL,\n"
                + "	includeSleep INTEGER NOT NULL,\n"
                + "	hotelName text ,\n"
                + " vacationId INTEGER PRIMARY KEY AUTOINCREMENT \n"
                + " sold Integer NOT NULL\n"
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



    public void createPurchasedVacationsTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS purchased_vacations (\n"
                + "	buyerUserName text NOT NULL,\n"
                + "	flightCompany text NOT NULL,\n"
                + "	fromDate DATE ,\n"
                + "	untilDate DATE ,\n"
                + "	baggageIncluded text NOT NULL,\n"
                + "	numberOfTickets INTEGER NOT NULL,\n"
                + "	destination text NOT NULL,\n"
                + "	twoDirections INTEGER NOT NULL,\n"
                + "	ticketType text NOT NULL,\n"
                + "	vacationType text NOT NULL,\n"
                + "	includeSleep INTEGER NOT NULL,\n"
                + "	hotelName text ,\n"
                + " vacationId INTEGER PRIMARY KEY AUTOINCREMENT \n"
                + " status string NOT NULL\n"
                + " freezed INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            Logger.getInstance().log("created new table purchased_vacations");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Logger.getInstance().log("failed to create new table purchased_vacations");
        }
    }

    @Override
    public void createPurchaseTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vacation_for_u.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS purchases (\n"
                + "	cardOwnerUserName text PRIMARY KEY,\n"
                + "	cardOwnerName text NOT NULL,\n"
                + "	cardType text NOT NULL ,\n"
                + "	cardNumber text NOT NULL,\n"
                + "	cardCvv text NOT NULL,\n"
                + "	cardExpireDate DATE NOT NULL\n"
                +  "targetVacation INTEGER NOT NULL"
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

    @Override
    public void createConfirmMessageTable(){
        String url = "jdbc:sqlite:vacation_for_u.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS messages (\n"
                + "	senderUserName text NOT NULL,\n"
                + "	reciverUserName text NOT NULL,\n"
                + "	creationTime text NOT NULL ,\n"
                + " messageType text NOT NULL ,\n"
                + " messageContent text NOT NULL , \n"
                + " status text NOT NULL , \n"
                + " vacationId INTEGER NOT NULL , \n"
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

    /*****************************************  INSERTION TO DB FUNCTIONS *****************************




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


    @Override
    public boolean insertVacation(String[] vacationValues) {

        String sql = "INSERT INTO vacations(publisherUserName,flightCompany,fromDate,untilDate,baggageIncluded,numberOfTickets,destination,twoDirections,ticketType,vacationType,includeSleep,hotelName,hotelRank,sold,freezed) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //processing dates
        Date fromDate = dateConvert(vacationValues[2]);
        Date untilDate = dateConvert(vacationValues[3]);
        int numberOfTickets = Integer.parseInt(vacationValues[5]);
        int twoDirections = ((vacationValues[7].equals("true")) ? 1 : 0);
        int includeSleep = ((vacationValues[10].equals("true")) ? 1 : 0);
        int hotelRank = Integer.parseInt(vacationValues[12]);
        int sold = ((vacationValues[13].equals("true")) ? 1 : 0);
        int freezed = ((vacationValues[14].equals("true")) ? 1 : 0);

        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vacationValues[0]);
            pstmt.setString(2, vacationValues[1]);
            pstmt.setDate(3, fromDate);
            pstmt.setDate(4, untilDate);
            pstmt.setString(5, vacationValues[4]);
            pstmt.setInt(6, numberOfTickets);
            pstmt.setString(7, vacationValues[6]);
            pstmt.setInt(8,twoDirections );
            pstmt.setString(9, vacationValues[8]);
            pstmt.setString(10, vacationValues[9]);
            pstmt.setInt(11,includeSleep);
            pstmt.setString(12, vacationValues[11]);
            pstmt.setInt(13, hotelRank);
            pstmt.setInt(14,sold);
            //by default freeze is off
            pstmt.setInt(15,freezed);
            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT : " + vacationValues.toString() +"- SUCCESS");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log(e.getMessage());
            return false;
        }

    }

    /**
     * this will add the requested vacation by id to the currently offerd table.
     * @param vacationId
     * @param buyerUsername
     * @param purchseOfferTime
     * @param purchaseOfferDetails
     * @return success or not
     */
    @Override
    public boolean insertBuyingOffer(int vacationId, String buyerUsername, String purchseOfferTime, Purchase purchaseOfferDetails) {

        String sqlStatement="INSERT INTO offers(vacationId, buyerUsername,purchseOfferTime) VALUES(?,?,?)";


        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1,vacationId);
            pstmt.setString(2, buyerUsername);
            pstmt.setString(3, purchseOfferTime);

            pstmt.executeUpdate();
            this.closeConnection(conn);
            this.insertPurchase(purchaseOfferDetails,vacationId);
            markVacationAsSold(vacationId);
            Logger.getInstance().log("INSERT Buying Offer on vacationID: " + vacationId+ " By user: "+buyerUsername+ " - SUCCESS");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log("INSERT Buying Offer on vacationID: " + vacationId+ " By user: "+buyerUsername+ " - FAILED");
            return false;
        }

    }

    private void insertPurchase(Purchase purchase,int vacationId){
        String sqlStatement="INSERT INTO purchases(cardOwnerUserName, cardOwnerName,cardType,cardNumber,cardCvv,cardExpireDate,targetVacation) VALUES(?,?,?,?,?,?,?)";


        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, purchase.getCardOwnerUserName());
            pstmt.setString(2, purchase.getCardOwnerName());
            pstmt.setString(3, purchase.getCardType());
            pstmt.setString(4, purchase.getCardNumber());
            pstmt.setString(5, purchase.getCardCvv());
            pstmt.setDate(6, purchase.getCardExpireDate());
            pstmt.setInt(7,vacationId);
            pstmt.executeUpdate();
            this.closeConnection(conn);
            Logger.getInstance().log("INSERT : " + purchase.toString()+ " - SUCCESS");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getInstance().log("INSERT : " + purchase.toString()+ " - FAILED");
        }
    }



    public void insertMessage(AMessage msg){

    }








    /***************************************** UPDATE FUNCTIONS *********************************************

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







    @Override
    public void freezeVacation(int vacationId) {
        String sqlStatement = "UPDATE vacations SET freeze = 1 WHERE vacationId = " + "'"+vacationId+"'";
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
    public void unFreezeVacation(int vacationId){
        String sqlStatement = "UPDATE vacations SET freeze = 0 WHERE vacationId = " + "'"+vacationId+"'";
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


    private void markVacationAsSold(int vacationId){
        String sqlStatement = "UPDATE vacations SET sold = 1 WHERE vacationId = " +"'"+vacationId+"'";
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





/*********************************************** SEARCHING FUNCTIONS************************************************






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
     * @param username the username of the record
     * @return a list with all the records
     */
    public ObservableList<User> searchRecordsByFields(String username) {
        ResultSet resultSet ;
        ObservableList result = null;
        String sql = "SELECT * FROM users WHERE username = " + "'"+username+"'";

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
            result = this.convertVacationResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
            Logger.getInstance().log(var6.getMessage());
        }

        return result;
    }

    @Override
    public ObservableList getVacations(String[] criteria, String[] Values) {
        return null;
    }








    /******************************************** DELETE FUNCS****************************************




    /**
     * delete a record from the data base
     * @param userName the username of the user as it appears in the database
     */
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


    /**
     * check if a char is a digit
     * @param c
     * @return true if its a digit
     */





    /********************************************   LOGIN *************************************************/



     @Override
     public AUserData login(String username, String password) {
     ResultSet resultSet ;
     boolean auth = false;
     String sql = "SELECT * FROM users WHERE username = " + "'"+username+"'";

     try {
     Connection conn = this.openConnection();
     Statement stmt = conn.createStatement();
     resultSet = stmt.executeQuery(sql);
     conn.close();
     if(resultSet.next()) {
         if (resultSet.getString("password").equals(password)) {
             auth = true;
             AUserData serverResponse = getUserData(username);
             return serverResponse;
         }
     }

     return null;

     } catch (SQLException var7) {
     System.out.println(var7.getMessage());
     Logger.getInstance().log(var7.getMessage());
     return null;

     }

     }









    /***************************************  RESULTSET TO OBSERVABLE LIST   ****************************/








    private ObservableList<User> convertUsersResultsToObservableList(ResultSet resultSet) {
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


    private ObservableList<Vacation> convertVacationResultsToObservableList(ResultSet resultSet) {
        ObservableList<Vacation> observableList = FXCollections.observableArrayList();

        try {
            while(resultSet.next()) {
                Date fromDate = resultSet.getDate("startDate");
                Date untilDate = resultSet.getDate("untilDate");
                int numberOfTickets = resultSet.getInt("numberOfTickets");
                boolean twoDirections = (resultSet.getInt("numberOfTickets") == 1) ? true : false;
                boolean includeSleep = (resultSet.getInt("includeSleep") == 1) ? true : false;
                boolean sold = (resultSet.getInt("sold") == 1) ? true : false;
                int hotelRank = resultSet.getInt("hotelRank");
                boolean freezed = (resultSet.getInt("freezed") == 1) ? true : false;
                Vacation v = new Vacation(resultSet.getInt(14),resultSet.getString(1),
                        resultSet.getString(2) ,
                        fromDate,
                        untilDate,
                        resultSet.getString(5) ,
                        numberOfTickets,
                        resultSet.getString(7),
                        twoDirections,
                        resultSet.getString(9),
                        resultSet.getString(10),
                        includeSleep,
                        resultSet.getString(12),
                        hotelRank,sold,freezed);
                if(!v.isFreezed() && !v.isSold())
                    observableList.add(v);
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
            while(resultSet.next()) {
                LocalDateTime creationTime = convertStringToLocalDateTime(resultSet.getString("creationTime"));
                String sender = resultSet.getString("senderUserName");
                String reciver = resultSet.getString("reciverUserName");
                String content = resultSet.getString("messageContent");
                String msgType = resultSet.getString("messageType");
                String status  = resultSet.getString("status");
                int vacationId = resultSet.getInt("vacationId");
                boolean expired = false;
                if(getHoursGap(creationTime,now)>48)
                    expired = true;
                AMessage msg = null;
                Vacation v =getVacationAsObjectById(vacationId);
                if(!expired)   //the vaction will be set from vacation table;
                    msg = new ConfirmOfferMessage(sender,reciver,content,v,status);
                else {
                    String expireExplain = "user: +"+sender+" tried to buy vacation: "+v.toString()+" but 48 have passed" +
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







    /*******************************************  FROM DB TO OBJECT **********************************************/

    private Vacation getVacationAsObjectById (int vacationId){
        ResultSet resultSet ;
        ObservableList <Vacation> result = null;
        String sql = "SELECT * FROM vacations WHERE vacationId = " + "'"+vacationId+"'";
        Vacation ans = null;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertVacationResultsToObservableList(resultSet);
            conn.close();
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
     * @param username
     * @return
     */
    private AUserData getUserData(String username){
        //get user inMessages
        ResultSet resultSet ;
        ObservableList inboundMessages = null;
        String sqlInboundMessages = "SELECT * FROM messages WHERE reciver = " + "'"+username+"'";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sqlInboundMessages);
            inboundMessages = this.convertInMessageResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
            Logger.getInstance().log(var7.getMessage());
        }
        /** NEED TO FINISH IT **/


        //check if they are not expired

        //if not , add as it is to user messages

        //else return an expire message

        ObservableList<AMessage> userMessages;
        return null;
    }



    private boolean isDigit(char c){

        if(c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0')
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
     * @param sDate
     * @return
     */
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

    /**
     * convert from string to local date time so we can subtract with Period
     * @param sDate time
     * @return time in LocalDateTime object
     */

    private  LocalDateTime convertStringToLocalDateTime(String sDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime formatDateTime = LocalDateTime.parse(sDate, formatter);

        return formatDateTime;
    }

    private long getHoursGap(LocalDateTime start , LocalDateTime end){
        Duration duration = Duration.between(start, end);
        return duration.getSeconds()/60/60;

    }


    private long getMinutesgap(LocalDateTime start , LocalDateTime end){
        Duration duration = Duration.between(start, end);
        return duration.getSeconds()/60;

    }

    /**
     * convert a date to a string
     * @param date
     * @return
     */
    private String dateToStringConvert(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String ans = df.format(date);
        return ans;
    }




    /**
     * open a connection to the database
     * @return
     */
    private Connection openConnection() {
        Connection conn = this.driver.connect();
        Logger.getInstance().log("connection opened");
        return conn;
    }

    /**
     * close connection to the database
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

    /**
     *
     * @param username
     * @return
     */







}



