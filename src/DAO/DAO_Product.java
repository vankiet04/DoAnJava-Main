/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Kiet
 */

public class DAO_Product implements DAOInterface<DTO_Product>{
    
    public static DAO_Product getInstance() {
        return new DAO_Product();
    }
    
    @Override
    public int insert(DTO_Product t) {
          int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `sanpham`(`masanpham`, `tensanpham`, `hinhanh`, `boxuly`, `hedieuhanh`, `thoigianbaohanh`, `maloai`, `khuvuckho`, `thuonghieu`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMasanpham());
            pst.setString(2, t.getTensanpham());
            pst.setString(3, t.getHinhsanpham());
            pst.setString(4, t.getBoxuly());
            pst.setString(5, t.getHedieuhanh());
            pst.setString(6, t.getThoigianbaohanh());
            pst.setInt(7, t.getmaLoai());
            pst.setString(8, t.getVitrikho());
            pst.setString(9, t.getThuonghieu());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
            
        } catch (SQLException ex) {
            System.out.println("omg");
        }
        return result;
        
    }

    @Override
    public int update(DTO_Product t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `sanpham` SET `tensanpham`=?,`hinhanh`=?,`boxuly`=?,`hedieuhanh`=?,`thoigianbaohanh`=?,`maloai`=?,`khuvuckho`=?,`thuonghieu`=? WHERE `masanpham`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTensanpham());
            pst.setString(2, t.getHinhsanpham());
            pst.setString(3, t.getBoxuly());
            pst.setString(4, t.getHedieuhanh());
            pst.setString(5, t.getThoigianbaohanh());
            pst.setInt(6, t.getmaLoai());
            pst.setString(7, t.getVitrikho());
            pst.setString(8, t.getThuonghieu());
            pst.setInt(9, t.getMasanpham());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            System.out.println("omg");
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `sanpham` SET `trangthai`=0 WHERE `masanpham`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
            result = 1;
        } catch (SQLException ex) {
            System.out.println("omg");
        }
        return result;
    }

    @Override
    public ArrayList<DTO_Product> getAllData() {
       ArrayList<DTO_Product> result = new ArrayList<DTO_Product>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM sanpham WHERE `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masanpham");
                String tendm = rs.getString("tensanpham");
                String hinhanh = rs.getString("hinhanh");
                String boxuly = rs.getString("boxuly");
                String hedieuhanh = rs.getString("hedieuhanh");
                String thoigianbaohanh = rs.getString("thoigianbaohanh");
                String khuvuckho = rs.getString("khuvuckho");
                int trangthai = rs.getInt("trangthai");
                String thuonghieu = rs.getString("thuonghieu");
                int maloai = rs.getInt("maloai");
                DTO_Product sp = new DTO_Product(madm, tendm, boxuly, hedieuhanh, khuvuckho,hinhanh, thoigianbaohanh,trangthai, thuonghieu, maloai);
                result.add(sp);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public DTO_Product selectById(String t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM sanpham WHERE `masanpham`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masanpham");
                String tendm = rs.getString("tensanpham");
                String hinhanh = rs.getString("hinhanh");
                String boxuly = rs.getString("boxuly");
                String hedieuhanh = rs.getString("hedieuhanh");
                String thoigianbaohanh = rs.getString("thoigianbaohanh");
                String khuvuckho = rs.getString("khuvuckho");
                int trangthai = rs.getInt("trangthai");
                String thuonghieu = rs.getString("thuonghieu");
                int maloai = rs.getInt("maloai");
                return new DTO_Product(madm, tendm, boxuly, hedieuhanh, khuvuckho,hinhanh, thoigianbaohanh,trangthai, thuonghieu, maloai);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getMaxRow() {
        try {
             Connection con = (Connection) JDBCUtil.getConnectDB();
             String sql = "SELECT COUNT(*) AS soluong FROM sanpham";
             PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
             ResultSet rs = (ResultSet) pst.executeQuery();
             if (rs.next()) {
                int soLuong = rs.getInt("soluong");
                return soLuong + 1;
             }
        } catch(SQLException e) {
            return -12;
        }
        return -1;
    }
    
    public DTO_Product getProductByID(int id) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM sanpham WHERE masanpham = ? and `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masanpham");
                String tendm = rs.getString("tensanpham");
                String hinhanh = rs.getString("hinhanh");
                String boxuly = rs.getString("boxuly");
                String hedieuhanh = rs.getString("hedieuhanh");
                String thoigianbaohanh = rs.getString("thoigianbaohanh");
                String khuvuckho = rs.getString("khuvuckho");
                int trangthai = rs.getInt("trangthai");
                String thuonghieu = rs.getString("thuonghieu");
                int maloai = rs.getInt("maloai");
                return new DTO_Product(madm, tendm, boxuly, hedieuhanh, khuvuckho, hinhanh, thoigianbaohanh, trangthai,
                        thuonghieu, maloai);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return null;
    }
    
    public <T> ArrayList<DTO_Product> search(String key) {
        ArrayList<DTO_Product> result = new ArrayList<DTO_Product>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE ? and `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, "%" + key + "%");
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masanpham");
                String tendm = rs.getString("tensanpham");
                String hinhanh = rs.getString("hinhanh");
                String boxuly = rs.getString("boxuly");
                String hedieuhanh = rs.getString("hedieuhanh");
                String thoigianbaohanh = rs.getString("thoigianbaohanh");
                String khuvuckho = rs.getString("khuvuckho");
                int trangthai = rs.getInt("trangthai");
                String thuonghieu = rs.getString("thuonghieu");
                int maloai = rs.getInt("maloai");
                DTO_Product sp = new DTO_Product(madm, tendm, boxuly, hedieuhanh, khuvuckho, hinhanh, thoigianbaohanh, trangthai,
                        thuonghieu, maloai);
                result.add(sp);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    public DTO_Product getsanphamtheomaphienban(int mapb) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM sanpham WHERE masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, mapb);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masanpham");
                String tendm = rs.getString("tensanpham");
                String hinhanh = rs.getString("hinhanh");
                String boxuly = rs.getString("boxuly");
                String hedieuhanh = rs.getString("hedieuhanh");
                String thoigianbaohanh = rs.getString("thoigianbaohanh");
                String khuvuckho = rs.getString("khuvuckho");
                int trangthai = rs.getInt("trangthai");
                String thuonghieu = rs.getString("thuonghieu");
                int maloai = rs.getInt("maloai");    
                return new DTO_Product(madm, tendm ,boxuly,hedieuhanh, khuvuckho,hinhanh, thoigianbaohanh,trangthai, thuonghieu, maloai);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return null;
    }

}

