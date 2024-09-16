/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Brand;
import DTO.DTO_Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Kiet
 */
public class DAO_Brand implements DAOInterface<DTO_Brand> {

    public static DAO_Brand getInstance() {
        return new DAO_Brand();
    }

    public ArrayList<DTO_Brand> getAllData() {
        ArrayList<DTO_Brand> result = new ArrayList<DTO_Brand>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM thuonghieu WHERE `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String tenbrand = rs.getString("tenthuonghieu");
                DTO_Brand brands = new DTO_Brand(tenbrand);
                result.add(brands);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int insert(DTO_Brand t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO thuonghieu(tenthuonghieu, trangthai) VALUES (?, 1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getBrandName());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public int update(DTO_Brand t) {
        // int result = 0;
        // try {
        //     Connection con = (Connection) JDBCUtil.getConnectDB();
        //     String sql = "UPDATE thuonghieu SET tenthuonghieu = ? WHERE tenthuonghieu = ?";
        //     PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
        //     pst.setString(1, t.getBrandName());
        //     result = pst.executeUpdate();
        //     JDBCUtil.close(con);
        // } catch (Exception e) {
        // }
        return 0;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE thuonghieu SET trangthai = 0 WHERE tenthuonghieu = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }
    public int deleteTH(String th) {
        int result = 0;
        //xoa thuong hieu 
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE thuonghieu SET trangthai = 0 WHERE tenthuonghieu = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, th);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public DTO_Brand selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int updatez(String currentBrand, String newBrand) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE thuonghieu SET tenthuonghieu = ? WHERE tenthuonghieu = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, newBrand);
            pst.setString(2, currentBrand);
            result = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    public int findIdByName(String name) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT idthuonghieu FROM thuonghieu WHERE tenthuonghieu = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getInt("idthuonghieu");
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }
}
