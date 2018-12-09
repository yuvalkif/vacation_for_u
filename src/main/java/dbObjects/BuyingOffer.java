package dbObjects;

import java.sql.Date;
import java.sql.Timestamp;

public class BuyingOffer {
    private String BuyerUserName;
    private Timestamp PurchseOfferTime;
    private Purchase PurchaseOfferDetails;

    public BuyingOffer(String buyerUserName, Timestamp purchseOfferTime, Purchase purchaseOfferDetails) {
        BuyerUserName = buyerUserName;
        PurchseOfferTime = purchseOfferTime;
        PurchaseOfferDetails = purchaseOfferDetails;
    }

    public String getBuyerUserName() {
        return BuyerUserName;
    }

    public Timestamp getPurchseOfferTime() {
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
