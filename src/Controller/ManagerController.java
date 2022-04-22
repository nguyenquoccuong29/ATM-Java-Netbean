/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ConnectDB.ConnectDB;
import Model.Card;
import Model.User;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ManagerController {
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public  List<User> ShowUser(List<User> listUser){
        String sql = "SELECT * FROM user";
        listUser = new ArrayList<User>();
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("cmnd"),
                        rs.getString("fullname"),
                        rs.getInt("phone"),
                        rs.getString("address"),
                        rs.getString("avt"));
                listUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }
    public  boolean AddUser(User u){
        String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
        ConnectDB c = new ConnectDB();
        System.out.println(u.getCmnd());
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getCmnd());
            ps.setString(2, u.getFullName());
            ps.setInt(3, u.getPhone());
            ps.setString(4, u.getAddress());
            ps.setString(5, u.getAvt());
            if(ps.executeUpdate()>0){
               return true;
            }else{
               return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean EditUser(String lastCMND, User u){
//        String sql = "UPDATE user SET cmnd=?, fullname=?, phone=?, address=?, avt=? WHERE cmnd=?";
        String sql = "SELECT * FROM user WHERE cmnd="+lastCMND;
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            Statement stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, u.getCmnd());
//            ps.setString(2, u.getFullName());
//            ps.setInt(3, u.getPhone());
//            ps.setString(4, u.getAddress());
//            ps.setString(5, u.getAvt());
//            ps.setString(6, lastCMND);
            rs = stmt.executeQuery(sql);
            System.err.println(rs);
            while(rs.next()){
                rs.updateString("cmnd", u.getCmnd());
                rs.updateString("fullname", u.getFullName());
                rs.updateInt("phone", u.getPhone());
                rs.updateString("address", u.getAddress());
                rs.updateString("avt", u.getAvt());
                rs.updateRow();
             
            }
            return true;
//            if (ps.executeUpdate() > 0) {
//                return true;
//            } else {
//                return false;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    
    }
    public boolean DeleteUser(String CMND){
        String sql = "DELETE FROM user WHERE cmnd=?";
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, CMND);
            if (ps.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    public List<User> SearchUser(List<User> listUser, int searchBy, String key) {
        String sql = null;
        switch (searchBy) {
            case 0:
                sql = "SELECT * FROM user WHERE cmnd LIKE '%" + key + "%'";
                break;
            case 1:
                sql = "SELECT * FROM user WHERE fullname LIKE '%" + key + "%'";
                break;
            case 2:
                sql = "SELECT * FROM user WHERE address LIKE '%" + key + "%'";
                break;
            default: break;
        }
        listUser = new ArrayList<User>();
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("cmnd"), rs.getString("fullname"), rs.getInt("phone"), rs.getString("address"), rs.getString("avt"));
                listUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }
    public List<Card> ShowCard(List<Card> listCard){
        String sql = "SELECT card.id,user.cmnd, user.fullname,"
                + " card.bank_name,card.blance,card.pin,card.created_at "
                + "FROM card INNER JOIN user ON card.user_cmnd = user.cmnd";
        listCard = new ArrayList<Card>();
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Card card = new Card(new BigInteger(rs.getString("id")),
                        rs.getString("cmnd"),rs.getString("fullname"),
                        rs.getString("bank_name"),
                        new BigInteger(rs.getString("blance")),
                        rs.getInt("pin"),rs.getDate("created_at"));
                listCard.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCard;
    }
    public String CheckCMND(String cmnd){
        String name = null;
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        String sql = "SELECT * FROM user WHERE cmnd = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cmnd);
            rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    public boolean AddCard(String cmnd, String bankName, String pin){
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        String sql = "INSERT INTO card(bank_name,user_cmnd,pin) VALUES(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, bankName);
            ps.setString(2, cmnd);
            ps.setString(3, pin);
            if(ps.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteCard(String cardID){
        String sql = "DELETE FROM card WHERE id=?";
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cardID);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Card> SearchCard(List<Card> listCard, int searchBy, String key){
        String sql = null;
        switch(searchBy){
            case 0:
                sql = "SELECT card.id,user.cmnd, user.fullname, card.bank_name,card.blance,card.pin,card.created_at FROM card INNER JOIN user ON card.user_cmnd = user.cmnd WHERE card.id LIKE '%"+key+"%'";
                    break;
            case 1:
                sql = "SELECT card.id,user.cmnd, user.fullname, card.bank_name,card.blance,card.pin,card.created_at FROM card INNER JOIN user ON card.user_cmnd = user.cmnd WHERE user.fullname LIKE '%"+key+"%'";
                    break;
            case 2:
                sql = "SELECT card.id,user.cmnd, user.fullname, card.bank_name,card.blance,card.pin,card.created_at FROM card INNER JOIN user ON card.user_cmnd = user.cmnd WHERE card.bank_name LIKE '%"+key+"%'";
                    break;
            default:  break;
        }
        listCard = new ArrayList<Card>();
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Card card = new Card(new BigInteger(rs.getString("id")),rs.getString("cmnd"),rs.getString("fullname"),rs.getString("bank_name"),new BigInteger(rs.getString("blance")),rs.getInt("pin"),rs.getDate("created_at"));
                listCard.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCard;
    }
    public boolean PayAcc(BigInteger cardID, BigInteger money){
        String sql = "UPDATE card SET blance=blance+? WHERE id=?";
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, money+"");
            ps.setString(2, cardID+"");
    
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


