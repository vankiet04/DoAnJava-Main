/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author KIET
 */
public class DAO_NhanVien implements DAOInterface<DTO_NhanVien> {
    
    public static DAO_NhanVien getInstance(){
        return new DAO_NhanVien();
    }
   

    @Override
    public int insert(DTO_NhanVien t) {
        try{
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `nhanvien`(`manv`, `hoten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `trangthai`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getManv());
            pst.setString(2, t.getHoten());
            pst.setInt(3, t.getGioitinh());
            pst.setDate(4, new java.sql.Date(t.getNgaysinh().getTime())); // Set ngaysinh
            pst.setString(5, t.getSdt()); // Set sdt
            pst.setString(6, t.getEmail());
            pst.setInt(7, t.getTrangthai());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            // JOptionPane.showMessageDialog(null, "Them nhan vien thanh cong" + result);
            return result;
        }catch(Exception e){
            // JOptionPane.showMessageDialog(null, "Loi them nhan vien: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public int update(DTO_NhanVien t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `nhanvien` SET `hoten`=?,`gioitinh`=?,`sdt`=?,`ngaysinh`=?,`email`=?,`trangthai`=? WHERE `manv`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setInt(2, t.getGioitinh());
            pst.setString(3, t.getSdt());
            pst.setDate(4, (java.sql.Date) t.getNgaysinh());
            pst.setString(5, t.getEmail());
            pst.setInt(6, t.getTrangthai());
            pst.setInt(7, t.getManv());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
            
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Loi update nhan vien: " + e.getMessage());
            return -1;
        }

    }

    @Override
    public int delete(int t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM `nhanvien` WHERE `manv`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Loi xoa nhan vien: " + e.getMessage());
            return -1;
        }

    
    }

    @Override
    public ArrayList<DTO_NhanVien> getAllData() {
        ArrayList<DTO_NhanVien> result = new ArrayList<DTO_NhanVien>();
       try(Connection con = (Connection) JDBCUtil.getConnectDB()){
            String sql = "SELECT * FROM `nhanvien`";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                String sdt = rs.getString("sdt");
                java.sql.Date ngaysinh = rs.getDate("ngaysinh");
                String email = rs.getString("email");
                int trangthai = rs.getInt("trangthai");
                DTO_NhanVien nv = new DTO_NhanVien(manv, hoten, gioitinh, ngaysinh, sdt, email, trangthai);
                result.add(nv);

            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Loi lay tat ca nhan vien: " + e.getMessage());
            return null;
        }
        // JOptionPane.showMessageDialog(null, "Lay tat ca nhan vien thanh cong"+ result);
        return result;
    }

    @Override
    public  DTO_NhanVien selectById(String t) {
        DTO_NhanVien result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM nhanvien WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if(rs.next()){
                int manv = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                String sdt = rs.getString("sdt");
                java.sql.Date ngaysinh = rs.getDate("ngaysinh");
                String email = rs.getString("email");
                int trangthai = rs.getInt("trangthai");
                result = new DTO_NhanVien(manv, hoten, gioitinh, ngaysinh, sdt, email, trangthai);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT Max(manv) as AUTO_INCREMENT FROM `nhanvien`";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return rs.getInt("AUTO_INCREMENT") + 1;
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, "Loi lay AUTO_INCREMENT: " + e.getMessage());
        }
        return -1;
    }
    
    public ArrayList<DTO_NhanVien> getNhanVienchuaCoTaiKhoan() {
        ArrayList<DTO_NhanVien> result = new ArrayList<DTO_NhanVien>();
        try(Connection con = (Connection) JDBCUtil.getConnectDB()){
            String sql = "SELECT * FROM `nhanvien` WHERE manv NOT IN (SELECT manv FROM taikhoan) AND trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                String sdt = rs.getString("sdt");
                java.sql.Date ngaysinh = rs.getDate("ngaysinh");
                String email = rs.getString("email");
                int trangthai = rs.getInt("trangthai");
                DTO_NhanVien nv = new DTO_NhanVien(manv, hoten, gioitinh, ngaysinh, sdt, email, trangthai);
                result.add(nv);

            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi lay tat ca nhan vien: " + e.getMessage());
            return null;
        }
        // JOptionPane.showMessageDialog(null, "Lay tat ca nhan vien thanh cong"+ result);
        return result;
    }

    public DTO_NhanVien getNhanVien(int manv) {
        DTO_NhanVien result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM nhanvien WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, manv);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if(rs.next()){
                int manv1 = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                String sdt = rs.getString("sdt");
                java.sql.Date ngaysinh = rs.getDate("ngaysinh");
                String email = rs.getString("email");
                int trangthai = rs.getInt("trangthai");
                result = new DTO_NhanVien(manv1, hoten, gioitinh, ngaysinh, sdt, email, trangthai);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    
}
