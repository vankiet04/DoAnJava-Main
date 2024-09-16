/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DTO_KhachHang;
import DTO.DTO_Kho;
import DTO.DTO_NhaCungCap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;

/**
 *
 * @author Admin
 */
public class DAO_KhachHang implements DAOInterface<DTO_KhachHang> {
    public static DAO_KhachHang getInstance(){
        return new DAO_KhachHang();
    }
   

    @Override
    public int insert(DTO_KhachHang t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO khachhang(MaKH, TenKH, DiaChi, SDT, NgayThamGia, GioiTinh, TrangThai) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getMaKhachHang());
            pst.setString(2, t.getHoTen());
            pst.setString(3, t.getDiaChi());
            pst.setString(4, t.getSoDienThoai());
            pst.setString(5, t.getNgayThamGia());
            pst.setString(6, t.getgioitinh());
            pst.setInt(7, 1);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(DTO_KhachHang t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `khachhang` SET `MaKH`=?,`TenKH`=?,`DiaChi`=?,`SDT`=?,`NgayThamGia`=?,`GioiTinh`=? WHERE `MaKH` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getMaKhachHang());
            pst.setString(2, t.getHoTen());
            pst.setString(3, t.getDiaChi());
            pst.setString(4, t.getSoDienThoai());

            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // String date = sdf.format(t.getNgayThamGia());
            pst.setString(5, t.getNgayThamGia());

            pst.setString(6, t.getgioitinh());
            pst.setString(7, t.getMaKhachHang());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            System.out.println("omg");
        }
        return result;
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DTO_KhachHang> getAllData() {
        ArrayList<DTO_KhachHang> result = new ArrayList<DTO_KhachHang>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM khachhang WHERE `TrangThai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("MaKH");
                String HoTen = rs.getString("TenKH");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SDT");
                String ngayThamGia = rs.getString("NgayThamGia");
                String gioitinh = rs.getString("GioiTinh");
                DTO_KhachHang kh = new DTO_KhachHang(maKhachHang, HoTen, diaChi, soDienThoai, ngayThamGia, gioitinh);
                result.add(kh);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public DTO_KhachHang selectById(String t) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM khachhang WHERE `MaKH` = ? AND `TrangThai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);

            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                String maKhachHang = rs.getString("MaKH");
                String HoTen = rs.getString("TenKH");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SDT");
                String ngayThamGia = rs.getString("NgayThamGia");
                String gioitinh = rs.getString("GioiTinh");
                DTO_KhachHang kh = new DTO_KhachHang(maKhachHang, HoTen, diaChi, soDienThoai, ngayThamGia, gioitinh);
                JDBCUtil.close(con);
              
                return kh;
            }
            JOptionPane.showMessageDialog(null, "Khong tim thay khach hang");
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Loi truy van du lieu: ");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int delete(String id) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `khachhang` SET `TrangThai`=0 WHERE `MaKH` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, id);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            System.out.println("omg");
        }
        return result;
    }
}
