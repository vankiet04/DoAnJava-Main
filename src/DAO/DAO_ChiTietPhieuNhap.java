/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DTO_ChiTietPhieuNhap;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author KIET
 */
public class DAO_ChiTietPhieuNhap implements DAOChiTietInterface<DTO_ChiTietPhieuNhap> {
    public static DAO_ChiTietPhieuNhap getInstance() {
        return new DAO_ChiTietPhieuNhap();
    }

    @Override
    public int insert(ArrayList<DTO_ChiTietPhieuNhap> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnectDB();
                String sql = "INSERT INTO `ctphieunhap`(`maphieunhap`, `maphienbansp`, `soluong`, `dongia`) VALUES (?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphieu());
                pst.setInt(2, t.get(i).getMaphienbansp());
                pst.setLong(3, t.get(i).getSoluong());
                pst.setLong(4, t.get(i).getDongia());
                result = pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu nhập thành công");
                JDBCUtil.close(con);
               
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Loi insert hi tiet phieu nhap ne: " + e.getMessage());
            }
            DAO_ChiTietCauHinh.getInstance().updateSoLuongTon(t.get(i).getMaphienbansp(), t.get(i).getSoluong());
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM ctphieunhap WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
          
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Loi delete chi tiet phieu nhap ne: " + ex.getMessage());
        }
        return result;
    }
        


    @Override
    public int update(ArrayList<DTO_ChiTietPhieuNhap> t, String pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DTO_ChiTietPhieuNhap> selectAll(String t) {
        ArrayList<DTO_ChiTietPhieuNhap> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctphieunhap WHERE maphieunhap = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                int maphienbansp = rs.getInt("maphienbansp");
                long dongia = rs.getLong("dongia");
                int soluong = rs.getInt("soluong");
                DTO_ChiTietPhieuNhap ctphieu = new DTO_ChiTietPhieuNhap(maphieu, maphienbansp, soluong, dongia);
                result.add(ctphieu);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Loi select all chi tiet phieu nhap ne: " + e.getMessage());
        }
        return result;
    }
    
    public ArrayList<DTO_ChiTietPhieuNhap> laytatcactphieunhap(int maphienban) {
        ArrayList<DTO_ChiTietPhieuNhap> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctphieunhap WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphienban);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                int maphienbansp = rs.getInt("maphienbansp");
                int dongia = rs.getInt("dongia");
                int soluong = rs.getInt("soluong");
                DTO_ChiTietPhieuNhap ctphieu = new DTO_ChiTietPhieuNhap(maphieu, maphienbansp, soluong, dongia);
                result.add(ctphieu);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Loi select all chi tiet phieu nhap ne: " + e.getMessage());
        }
        return result;
    }
   
    public ArrayList<DTO_ChiTietPhieuNhap> getAllChiTietPhieuNhapByMaPhieinBanSanPham(int maphienbansanpham) {
        ArrayList<DTO_ChiTietPhieuNhap> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctphieunhap WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphienbansanpham);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                int maphienbansp = rs.getInt("maphienbansp");
                int dongia = rs.getInt("dongia");
                int soluong = rs.getInt("soluong");
                DTO_ChiTietPhieuNhap ctphieu = new DTO_ChiTietPhieuNhap(maphieu, maphienbansp, soluong, dongia);
                result.add(ctphieu);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Loi select all chi tiet phieu nhap ne: " + e.getMessage());
        }
        return result;
    }
    
}
