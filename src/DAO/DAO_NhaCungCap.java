/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DTO_NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ConnectDB.JDBCUtil;

/**
 *
 * @author KIET
 */

public class DAO_NhaCungCap implements DAOInterface<DTO_NhaCungCap>{
    // implemment all methods
    public static DAO_NhaCungCap getInstance(){
        return new DAO_NhaCungCap();
    }
   
    @Override
    public int insert(DTO_NhaCungCap t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `nhacungcap`(`manhacungcap`,`tennhacungcap`, `diachi`, `email`, `sdt`,`trangthai`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMancc());
            pst.setString(2, t.getTenncc());
            pst.setString(3, t.getDiachi());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getsodienthoai());
            pst.setInt(6, 1);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
         
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi them nha cung cap: " + e.getMessage());
            return -1;
        }
    }

    

    @Override
    public int update(DTO_NhaCungCap t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `nhacungcap` SET `tennhacungcap`=?,`diachi`=?,`email`=?,`sdt`=? WHERE `manhacungcap`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenncc());
            pst.setString(2, t.getDiachi());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getsodienthoai());
            pst.setInt(5, t.getMancc());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Loi cap nhat nha cung cap: ");
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public int delete(int t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `nhacungcap` SET `trangthai`=0 WHERE `manhacungcap`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Loi xoa nha cung cap: ");
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ArrayList<DTO_NhaCungCap> getAllData() {
        ArrayList<DTO_NhaCungCap> result = new ArrayList<DTO_NhaCungCap>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM `nhacungcap` where trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int mancc = rs.getInt("manhacungcap");
                String tenncc = rs.getString("tennhacungcap");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                DTO_NhaCungCap ncc = new DTO_NhaCungCap(mancc, tenncc, diachi, email, sdt, 1);
                result.add(ncc);
            }
            JDBCUtil.close(con);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi truy van du lieu: ");   
            e.printStackTrace();
        }
        //tra ve danh sach DTO_NhaCungCap
        return result;
    }

    @Override
    
    public DTO_NhaCungCap selectById(String t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM `nhacungcap` WHERE `manhacungcap`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int mancc = rs.getInt("manhacungcap");
                String tenncc = rs.getString("tennhacungcap");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                DTO_NhaCungCap ncc = new DTO_NhaCungCap(mancc, tenncc, diachi, email, sdt, 1);
                return ncc;
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Loi truy van du lieu: ");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int getAutoIncrement() {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT MAX(manhacungcap) as max_mancungcap FROM nhacungcap";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int max_mancungcap = rs.getInt("max_mancungcap");
                return max_mancungcap + 1;
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
