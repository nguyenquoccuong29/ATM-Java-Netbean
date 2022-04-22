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
public class User {
    String cmnd;
    String fullName;
    int phone;
    String address;
    String avt;

    @Override
    public String toString() {
        return "User{" + "cmnd=" + cmnd + ", fullName=" + fullName + ", phone=" + phone + ", address=" + address + ", avt=" + avt + '}';
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public User(String cmnd, String fullName, int phone, String address, String avt) {
        this.cmnd = cmnd;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.avt = avt;
    }
    
}
