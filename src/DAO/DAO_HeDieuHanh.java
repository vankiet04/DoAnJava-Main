/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_HeDieuHanh;
import DTO.DTO_Kho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 *
 * @author Kiet
 */
public class DAO_HeDieuHanh implements DAOInterface<DTO_HeDieuHanh> {

    @Override
    public int insert(DTO_HeDieuHanh t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO hedieuhanh(MaHDH, TenHDH, TrangThai) VALUES (?, ?, 1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMahdh());
            pst.setString(2, t.getTenhdh());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("loi add hdh");
        }
        return result;
    }

    @Override
    public int update(DTO_HeDieuHanh t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `hedieuhanh` SET `TrangThai`=0 WHERE `MaHDH` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("loi delete hdh");
        }
        return result;
    }

    @Override
    public ArrayList<DTO_HeDieuHanh> getAllData() {
        ArrayList<DTO_HeDieuHanh> result = new ArrayList<DTO_HeDieuHanh>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM hedieuhanh WHERE `TrangThai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int mahdh = rs.getInt("MaHDH");
                String tenhdh = rs.getString("TenHDH");
                DTO_HeDieuHanh hdh = new DTO_HeDieuHanh(mahdh, tenhdh);
                result.add(hdh);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("loi add hdh");
        }
        return result;
    }

    @Override
    public DTO_HeDieuHanh selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int update(String tenth, String newten) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `hedieuhanh` SET `TenHDH`=? WHERE `TenHDH` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, newten);
            pst.setString(2, tenth);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("loi update hdh");
        }
        return result;
    }
    public int deleteTH(String th) {
        int result = 0;

        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `hedieuhanh` SET `TrangThai`=0 WHERE `TenHDH` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, th);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } catch (Exception e) {
            System.out.println("loi delete hdh");
        }
        return result;
    }
      
    
}
