/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ConnectDB.ConnectDB;
import Model.Card;
import Model.Transaction;
import View.ATM.ViewBlance;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class ATMController {
    private Connection conn;
    private PreparedStatement ps=null,ps1=null,ps2=null,ps3=null;
    private ResultSet rs=null;

      public List<Card> checkLogin(List<Card> lisrCard, BigInteger cardID, int pin){
          ConnectDB c = new ConnectDB();
          conn = c.getConnection();
          String sql = "SELECT card.id,user.cmnd, user.fullname, card.bank_name,card.blance,card.pin,card.created_at"
                  + " FROM card INNER JOIN user ON card.user_cmnd = user.cmnd "
                  + "WHERE card.id ="+cardID +" AND card.pin="+pin;
          try {
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              if (rs.next()) {
                   Card card = new Card(
                           new BigInteger(rs.getString("id")),
                           rs.getString("cmnd"),
                           rs.getString("fullname"),
                           rs.getString("bank_name"),
                           new BigInteger(rs.getString("blance")),
                           rs.getInt("pin"),
                           rs.getDate("created_at"));
                   lisrCard.add(card);
                   return lisrCard;
              }else{
                  return lisrCard;
              }
          } catch (Exception e) {
              e.printStackTrace();
              return lisrCard;
          }
        
      }
      
      public BigInteger ViewBlance(BigInteger cardID){
          ConnectDB c = new ConnectDB();
          BigInteger sd = null;
          conn = c.getConnection();
          String sql = "SELECT blance FROM card where id="+cardID;
          try {
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              if (rs.next()) {   
                   return new BigInteger(rs.getString("blance"));
              }else{
                  return sd;
              }
          } catch (Exception e) {
              e.printStackTrace();
              return sd;
          }
      }
      
      public boolean RutTien(BigInteger cardID, BigInteger money){
          ConnectDB c = new ConnectDB();
          conn = c.getConnection();
          String sql1 = "UPDATE card set blance=blance-" + money + " WHERE id=" + cardID;
          String sql2 = "INSERT INTO transaction(card_id,money,note)"
                  + " VALUES(" + cardID + "," + money + ",'-" + money + " rut tien tai ATM')";
          try {
              ps = conn.prepareStatement(sql1);
              ps1 = conn.prepareStatement(sql2);
              if(ps.executeUpdate()>0 && ps1.executeUpdate()>0){
                  return true;
              }else{
                  return false;
              }
              
          } catch (Exception e) {
              e.printStackTrace();
            return false;
          }
      }
      
      public boolean ChangePin(BigInteger cardID, int newpin){
          ConnectDB c = new ConnectDB();
          conn = c.getConnection();
          String sql = "UPDATE card set pin=" + newpin + " WHERE id=" + cardID;
          try {
              ps = conn.prepareStatement(sql);
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
      
    public boolean Transfer(BigInteger sender_cardID, BigInteger receiver_cardID, BigInteger money, String note) {
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        String sql1 = "UPDATE card set blance=blance-" + money + " WHERE id=" + sender_cardID;
        String sql2 = "UPDATE card set blance=blance+" + money + " WHERE id=" + receiver_cardID;
        String sql3 = "INSERT INTO transaction(card_id,money,note)"
                + " VALUES(" + sender_cardID + "," + money + ",'-" + money + " " + note + "')";
        String sql4 = "INSERT INTO transaction(card_id,money,note)"
                + " VALUES(" + receiver_cardID + "," + money + ",'+" + money + " " + note + "')";
        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
        System.out.println(sql4);
        try {
            ps = conn.prepareStatement(sql1);
            ps1 = conn.prepareStatement(sql2);
            ps2 = conn.prepareStatement(sql3);
            ps3 = conn.prepareStatement(sql4);

            if (ps.executeUpdate() > 0 && ps1.executeUpdate() > 0 && ps2.executeUpdate() > 0 && ps3.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String CheckCardID(BigInteger cardID) {
        String name = null;
        ConnectDB c = new ConnectDB();
        conn = c.getConnection();
        String sql = "SELECT user.fullname FROM card INNER JOIN user ON card.user_cmnd = user.cmnd WHERE id ="+cardID;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    
    public List<Transaction> ViewHistoryTransaction(BigInteger cardID) {
        List<Transaction> listTran = new ArrayList<>();
        ConnectDB c = new ConnectDB();
        BigInteger sd = null;
        conn = c.getConnection();
        String sql = "SELECT * FROM transaction where card_id=" + cardID;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Transaction tran = new Transaction(
                        new BigInteger(rs.getString("card_id")),
                        new BigInteger(rs.getString("money")),
                        rs.getString("note"),
                        rs.getDate("created_at"));
                listTran.add(tran);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return listTran;
    }
      
}

