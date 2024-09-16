/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_RamList;
import DTO.DTO_RomList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kiet
 */
public class DAO_RomList implements DAOInterface<DTO_RomList> {
    
    public static DAO_Product getInstance() {
        return new DAO_Product();
    }
    
    @Override
    public int insert(DTO_RomList t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `rom`(`MaROM`, `KichThuocRom`, `TrangThai`) VALUES (?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMarom());
            pst.setString(2, t.getKichThuocRom());
            pst.execute();
            JDBCUtil.close(con);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(DTO_RomList t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `rom` SET `KichThuocRom`=? WHERE `MaROM`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getKichThuocRom());
            pst.setInt(2, t.getMarom());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("loi sua rom");
            return 0;
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `rom` SET `TrangThai`=0 WHERE `MaROM`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("loi xoa rom");
            return 0;
        }
        return result;
    }

    @Override
    public ArrayList<DTO_RomList> getAllData() {
       ArrayList<DTO_RomList> result = new ArrayList<DTO_RomList>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM rom WHERE `TrangThai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int marom = rs.getInt("MaROM");
                String KichThuocRom = rs.getString("KichThuocRom");
                DTO_RomList rom = new DTO_RomList(marom, KichThuocRom);
                result.add(rom);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public DTO_RomList selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
