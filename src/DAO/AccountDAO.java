package DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kiet
 */
import ConnectDB.JDBCUtil;
import DTO.AccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO implements DAOInterface<AccountDTO> {
    public static AccountDAO getInstance(){
        return new AccountDAO();
    }
    // thêm sửa xóa db
    public AccountDTO selectByUser(String t) {
        AccountDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM taikhoan WHERE tendangnhap=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();

            while (rs.next()) {

                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                AccountDTO tk = new AccountDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                
                result = tk;

            }

            JDBCUtil.close(con);

        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int insert(AccountDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(AccountDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AccountDTO> getAllData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AccountDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int login(String user, String pass) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM taikhoan WHERE tendangnhap=? AND matkhau=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = (ResultSet) pst.executeQuery();

            while (rs.next()) {
                result = 1;
            }

            JDBCUtil.close(con);

        } catch (Exception e) {
        }
        return result;
    }
}
