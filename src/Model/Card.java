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
public class Card {
    BigInteger id;
    String user_cmnd;
    String nameBoss;
    String bankName;
    BigInteger blance;
    int pin;
    Date created_at;



    public Card(BigInteger id, String user_cmnd, String nameBoss, String bankName, BigInteger blance, int pin, Date created_at) {
        this.id = id;
        this.user_cmnd = user_cmnd;
        this.nameBoss = nameBoss;
        this.bankName = bankName;
        this.blance = blance;
        this.pin = pin;
        this.created_at = created_at;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUser_cmnd() {
        return user_cmnd;
    }

    public void setUser_cmnd(String user_cmnd) {
        this.user_cmnd = user_cmnd;
    }

    public String getNameBoss() {
        return nameBoss;
    }

    public void setNameBoss(String nameBoss) {
        this.nameBoss = nameBoss;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigInteger getBlance() {
        return blance;
    }

    public void setBlance(BigInteger blance) {
        this.blance = blance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }



   
}
