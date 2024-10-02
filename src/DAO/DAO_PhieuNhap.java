/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.DTO_PhieuNhap;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_NhanVien;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KIET
 */
public class DAO_PhieuNhap implements DAOInterface<DTO_PhieuNhap> {
    
    // getInstance
    public static DAO_PhieuNhap getInstance() {
        return new DAO_PhieuNhap();
    }
    @Override
    public int insert(DTO_PhieuNhap t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `phieunhap`(`maphieunhap`, `thoigian`, `manhacungcap`, `nguoitao`, `tongtien`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieu());
            pst.setTimestamp(2, t.getThoigiantao());
            pst.setInt(3, t.getManhacungcap());
            pst.setInt(4, t.getManguoitao());
            pst.setDouble(5, t.getTongTien());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
            //  JOptionPane.showMessageDialog(null, "Them thanh cong phieu nhap: "+ result);
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, "Loi insert: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int update(DTO_PhieuNhap t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DTO_PhieuNhap> getAllData() {
        ArrayList<DTO_PhieuNhap> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phieunhap ORDER BY maphieunhap DESC";
            
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                java.sql.Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int mancc = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitao");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                DTO_PhieuNhap phieuNhap = new DTO_PhieuNhap(maphieu, thoigiantao, mancc, nguoitao, tongtien, trangthai);
                result.add(phieuNhap);
            }

            // JOptionPane.showMessageDialog(null, "Lay du lieu thaadasdnh cong: "+result);
            JDBCUtil.close(con);
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, "Loi lay du lieu: " + e.getMessage());
        }
        return result;
       
    }

    @Override
    public DTO_PhieuNhap selectById(String t) {
        DTO_PhieuNhap result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phieunhap WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                java.sql.Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int mancc = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitao");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                result = new DTO_PhieuNhap(maphieu, thoigiantao, mancc, nguoitao, tongtien, trangthai);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, "Loi select by id: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int maxId = 0;
        try {
            String query = "SELECT MAX(maphieunhap) as maxId FROM phieunhap";
            Connection con = JDBCUtil.getConnectDB();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("maxId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId + 1;
    }
    public int cancelPhieuNhap(int maphieu){
        int result = 0;
        ArrayList<DTO_ChiTietPhieuNhap> arrCt = DAO_ChiTietPhieuNhap.getInstance().selectAll(Integer.toString(maphieu));
        for (DTO_ChiTietPhieuNhap chiTietPhieuNhapDTO : arrCt) {
            DAO_ChiTietCauHinh.getInstance().updateSoLuongTon(chiTietPhieuNhapDTO.getMaphienbansp(), -(chiTietPhieuNhapDTO.getSoluong()));
        }
        DAO_ChiTietPhieuNhap.getInstance().delete(Integer.toString(maphieu));
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM phieunhap WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphieu);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
            // JOptionPane.showMessageDialog(null, "Huy phieu nhap thanh cong: "+ result);
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, "Loi huy phieu nhap: "+ ex.getMessage());
            
        }
        return result;
    }
    
}