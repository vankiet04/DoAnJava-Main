/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Product;
import DTO.DTO_RamList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kiet
 */
public class DAO_RamList implements DAOInterface<DTO_RamList> {
    public static DAO_Product getInstance() {
        return new DAO_Product();
    }

    @Override
    public int insert(DTO_RamList t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `ram`(`MaRAM`, `KichThuocRam`, `TrangThai`) VALUES (?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaram());
            pst.setInt(2, Integer.parseInt(t.getKichThuocRam()));
            pst.execute();
            JDBCUtil.close(con);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(DTO_RamList t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `ram` SET `KichThuocRam`=? WHERE `MaRAM`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t.getKichThuocRam()));
            pst.setInt(2, t.getMaram());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("loi sua ram");
            return 0;
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `ram` SET `TrangThai`=0 WHERE `MaRAM`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    @Override
    public ArrayList<DTO_RamList> getAllData() {
        ArrayList<DTO_RamList> result = new ArrayList<DTO_RamList>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ram WHERE `TrangThai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maram = rs.getInt("MaRAM");
                String KichThuocRam = rs.getString("KichThuocRam");
                DTO_RamList ram = new DTO_RamList(maram, KichThuocRam);
                result.add(ram);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        
        return result;
    }

    @Override
    public DTO_RamList selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static int getMaxIDRam() {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT MAX(MaRAM) FROM ram";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return 0;
    }
}
