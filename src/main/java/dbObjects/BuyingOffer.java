package dbObjects;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BuyingOffer {
    private String BuyerUserName;
    private String PurchseOfferTime;
    private Purchase PurchaseOfferDetails;

    public BuyingOffer(String buyerUserName, Timestamp purchseOfferTime, Purchase purchaseOfferDetails) {
        BuyerUserName = buyerUserName;
        PurchseOfferTime = new SimpleDateFormat("yyyy-mm-dd-HH-mm-ss").format(new java.util.Date());
        PurchaseOfferDetails = purchaseOfferDetails;
    }

    public String getBuyerUserName() {
        return BuyerUserName;
    }

    public String getPurchseOfferTime() {
        return PurchseOfferTime;
    }

    public Purchase getPurchaseOfferDetaiils() {
        return PurchaseOfferDetails;
    }

    @Override
    public String toString() {
        return "BuyingOffer{" +
                "BuyerUserName='" + BuyerUserName + '\'' +
                ", PurchseOfferTime=" + PurchseOfferTime +
                ", PurchaseOfferDetails=" + PurchaseOfferDetails +
                '}';
    }
}
