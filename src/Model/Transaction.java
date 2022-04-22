/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Transaction {
    BigInteger cardID;
    BigInteger money;
    String note;
    Date created_at;

    public Transaction(BigInteger cardID, BigInteger money, String note, Date created_at) {
        this.cardID = cardID;
        this.money = money;
        this.note = note;
        this.created_at = created_at;
    }

    public BigInteger getCardID() {
        return cardID;
    }

    public void setCardID(BigInteger cardID) {
        this.cardID = cardID;
    }

    public BigInteger getMoney() {
        return money;
    }

    public void setMoney(BigInteger money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
}
