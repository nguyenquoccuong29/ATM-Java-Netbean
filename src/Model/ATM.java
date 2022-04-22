/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ATM {
    int qty_500k, qty_200k, qty_100k, qty_50k, sumMoney;
    
    @Override
    public String toString() {
        return "ATM{" + "qty_500k=" + qty_500k + ", qty_200k=" + qty_200k + ", qty_100k=" + qty_100k + ", qty_50k=" + qty_50k + ", sumMoney=" + sumMoney + '}';
    }

    public ATM(int qty_500k, int qty_200k, int qty_100k, int qty_50k, int sumMoney) {
        this.qty_500k = qty_500k;
        this.qty_200k = qty_200k;
        this.qty_100k = qty_100k;
        this.qty_50k = qty_50k;
        this.sumMoney = sumMoney;
    }

    public int getQty_500k() {
        return qty_500k;
    }

    public void setQty_500k(int qty_500k) {
        this.qty_500k = qty_500k;
    }

    public int getQty_200k() {
        return qty_200k;
    }

    public void setQty_200k(int qty_200k) {
        this.qty_200k = qty_200k;
    }

    public int getQty_100k() {
        return qty_100k;
    }

    public void setQty_100k(int qty_100k) {
        this.qty_100k = qty_100k;
    }

    public int getQty_50k() {
        return qty_50k;
    }

    public void setQty_50k(int qty_50k) {
        this.qty_50k = qty_50k;
    }

    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }
    
}
