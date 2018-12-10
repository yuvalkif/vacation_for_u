package dbObjects;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyingOffer {
    private String buyerUserName;
    private String purchseOfferTime;
    private Purchase PurchaseOfferDetails;

    public BuyingOffer(String buyerUserName,Purchase purchaseOfferDetails) {
        buyerUserName = buyerUserName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.purchseOfferTime = LocalDateTime.now().format(formatter);
        PurchaseOfferDetails = purchaseOfferDetails;
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public String getPurchseOfferTime() {
        return purchseOfferTime;
    }

    public Purchase getPurchaseOfferDetaiils() {
        return PurchaseOfferDetails;
    }

    @Override
    public String toString() {
        return "BuyingOffer{" +
                "BuyerUserName='" + buyerUserName + '\'' +
                ", PurchseOfferTime=" + purchseOfferTime +
                ", PurchaseOfferDetails=" + PurchaseOfferDetails +
                '}';
    }
}
