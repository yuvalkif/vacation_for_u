package dbObjects;

import java.time.LocalDateTime;

public class PurchaseRecipt {
    private String newOwnerUserName;
    private LocalDateTime purchaseTime;
    private Purchase purchaseDeatils;
    private Vacation vacation;

    public PurchaseRecipt(String newOwnerUserName, LocalDateTime purchaseTime, Purchase purchaseDeatils, Vacation vacation) {
        this.newOwnerUserName = newOwnerUserName;
        this.purchaseTime = purchaseTime;
        this.purchaseDeatils = purchaseDeatils;
        this.vacation = vacation;
    }

    public String getNewOwnerUserName() {
        return newOwnerUserName;
    }

    public void setNewOwnerUserName(String newOwnerUserName) {
        this.newOwnerUserName = newOwnerUserName;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Purchase getPurchaseDeatils() {
        return purchaseDeatils;
    }

    public void setPurchaseDeatils(Purchase purchaseDeatils) {
        this.purchaseDeatils = purchaseDeatils;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }
}
