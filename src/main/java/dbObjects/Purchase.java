package dbObjects;

import java.sql.Date;


/**
 * purchase is a buying request which sucessfuly done .
 */
public class Purchase {
    private String askerUserName;
    private String replierUserName;
    private Date purchaseApprovedTime;
    private Vacation vacation;

    public Purchase(String askerUserName, String replierUserName, Date purchaseApprovedTime, Vacation vacation) {
        this.askerUserName = askerUserName;
        this.replierUserName = replierUserName;
        this.purchaseApprovedTime = purchaseApprovedTime;
        this.vacation = vacation;
    }

    public String getAskerUserName() {
        return askerUserName;
    }

    public String getReplierUserName() {
        return replierUserName;
    }

    public Date getPurchaseApprovedTime() {
        return purchaseApprovedTime;
    }

    public Vacation getVacation() {
        return vacation;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "askerUserName='" + askerUserName + '\'' +
                ", replierUserName='" + replierUserName + '\'' +
                ", purchaseApprovedTime=" + purchaseApprovedTime +
                ", vacation=" + vacation +
                '}';
    }
}
