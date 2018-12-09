package dbObjects;

import java.sql.Date;

public class Purchase {
    private String cardOwnerUserName;
    private String cardOwnerName;
    private String cardType;
    private String cardNumber;
    private String cardCvv;
    private int payingOnVacation;
    private Date   cardExpireDate;

    public Purchase(String cardOwnerUserName,String cardOwnerName, String cardType, String cardNumber, String cardCvv, Date cardExpireDate,int payingOnVacation) {
        this.cardOwnerName = cardOwnerName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.cardExpireDate = cardExpireDate;
        this.cardOwnerUserName = cardOwnerUserName;
        this.payingOnVacation = payingOnVacation;
    }


    public String getCardOwnerUserName() {
        return cardOwnerUserName;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public Date getCardExpireDate() {
        return cardExpireDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "cardOwnerUserName='" + cardOwnerUserName + '\'' +
                ", cardOwnerName='" + cardOwnerName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardCvv='" + cardCvv + '\'' +
                ", cardExpireDate=" + cardExpireDate +
                '}';
    }
}
