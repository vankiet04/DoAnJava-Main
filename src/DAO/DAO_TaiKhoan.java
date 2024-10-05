/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_NhanVien;
import DTO.DTO_TaiKhoan;

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
public class DAO_TaiKhoan implements DAOInterface<DTO_TaiKhoan> {
    
    public static DAO_TaiKhoan getInstance(){
        return new DAO_TaiKhoan();
    }
   

    @Override
    public int insert(DTO_TaiKhoan t) {
       return 0;
    }

    @Override
    public int update(DTO_TaiKhoan t) {
        
        return 0;
    }

    @Override
    public int delete(int t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM `taikhoan` WHERE `manv`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi xoa nhan vien: " + e.getMessage());
            return -1;
        }

    
    }

    @Override
    public ArrayList<DTO_TaiKhoan> getAllData() {
        ArrayList<DTO_TaiKhoan> result = new ArrayList<DTO_TaiKhoan>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM taikhoan where trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String tentaikhoan = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manv = rs.getInt("manv");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                DTO_TaiKhoan tk = new DTO_TaiKhoan(manv, tentaikhoan, matkhau, manhomquyen, trangthai);
                result.add(tk);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi lay tat ca nhan vien: " + e.getMessage());
        }
        return result;
    }

    @Override
    public DTO_TaiKhoan selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            JOptionPane.showMessageDialog(null, "Loi lay AUTO_INCREMENT: " + e.getMessage());
        }
        return -1;
    }

    public int insert(int manv, String tendangnhap, String matkhau, int manhomquyen) {
        try{
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `taikhoan`(`manv`, `matkhau`, `manhomquyen`, `tendangnhap`, `trangthai`) VALUES (?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, manv);
            pst.setString(2, matkhau);
            pst.setInt(3, manhomquyen);
            pst.setString(4, tendangnhap);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            // JOptionPane.showMessageDialog(null, "Them nhan vien thanh cong" + result);
            return result;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Loi them nhan vien: " + e.getMessage());
            return -1;
        }

    }

    public int hardDelete(int id) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM `taikhoan` WHERE `manv`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi xoa nhan vien: " + e.getMessage());
            return -1;
        }
    }
    
    public int updateNhomQuyen(int id, int idnhomquyen) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `taikhoan` SET `manhomquyen`=? WHERE `manv`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, idnhomquyen);
            pst.setInt(2, id);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi cap nhat nhan vien: " + e.getMessage());
            return -1;
        }
    }

    public int updatetk(int currentmanv, int newmanv, String username, String password, int manhomquyen) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `taikhoan` SET `manv`=?,`matkhau`=?,`manhomquyen`=?,`tendangnhap`=? WHERE `manv`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, newmanv);
            pst.setString(2, password);
            pst.setInt(3, manhomquyen);
            pst.setString(4, username);
            pst.setInt(5, currentmanv);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi cap nhat nhan vien: " + e.getMessage());
            return -1;
        }
    }

    public DTO_TaiKhoan getTaiKhoan(String username, String pass) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM taikhoan where tendangnhap = ? and matkhau = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, pass);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                DTO_TaiKhoan tk = new DTO_TaiKhoan(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                JDBCUtil.close(con);
                return tk;
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi lay tat ca nhan vien: " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<DTO_TaiKhoan> search(String keyword) {
        ArrayList<DTO_TaiKhoan> result = new ArrayList<DTO_TaiKhoan>();

        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM taikhoan where trangthai = 1 and (manv like ? or tendangnhap like ? or matkhau like ? or manhomquyen like ?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            pst.setString(3, "%" + keyword + "%");
            pst.setString(4, "%" + keyword + "%");
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String tentaikhoan = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manv = rs.getInt("manv");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                DTO_TaiKhoan tk = new DTO_TaiKhoan(manv, tentaikhoan, matkhau, manhomquyen, trangthai);
                result.add(tk);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi lay tat ca nhan vien: " + e.getMessage());
        }
        return result;
    }
    
    public String getTenDangNhapByIdNhanVien(int manv) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM taikhoan where manv = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, manv);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                String tendangnhap = rs.getString("tendangnhap");
                JDBCUtil.close(con);
                return tendangnhap;
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi lay tat ca nhan vien: " + e.getMessage());
        }
        return null;

    }

}
