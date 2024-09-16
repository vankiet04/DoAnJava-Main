package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;
import DTO.DTO_ChucNangNhomQuyen;

public class DAO_ChucNangNhomQuyen {

    public ArrayList<DTO_ChucNangNhomQuyen> selectsById(String t) {
        // TODO Auto-generated method stub
        int id = Integer.parseInt(t);
        ArrayList<DTO_ChucNangNhomQuyen> list = new ArrayList<DTO_ChucNangNhomQuyen>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM chucnangnhomquyen where manhomquyen = ?";     
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String machucnang = rs.getString("machucnang");
                String hanhdong = rs.getString("hanhdong");
                DTO_ChucNangNhomQuyen chucnangnhomquyen = new DTO_ChucNangNhomQuyen(manhomquyen, machucnang, hanhdong);
                list.add(chucnangnhomquyen);
            }
          
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Load data failure!");
        }
        return list;
    }

    public int deleteAllNhomQuyen(int manhomquyen) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM chucnangnhomquyen WHERE manhomquyen = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, manhomquyen);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<DTO_ChucNangNhomQuyen> selectAllChucNangNQ(int manhomquyen) {
        // TODO Auto-generated method stub
        ArrayList<DTO_ChucNangNhomQuyen> list = new ArrayList<DTO_ChucNangNhomQuyen>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM chucnangnhomquyen where manhomquyen = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, manhomquyen);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manhomquyen1 = rs.getInt("manhomquyen");
                String machucnang = rs.getString("machucnang");
                String hanhdong = rs.getString("hanhdong");
                DTO_ChucNangNhomQuyen chucnangnhomquyen = new DTO_ChucNangNhomQuyen(manhomquyen1, machucnang, hanhdong);
                list.add(chucnangnhomquyen);
            }
            // JOptionPane.showMessageDialog(null, "Lay tat ca!" + list);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Load data failure!");
        }
        return list;
    }

    public String getTenNhomQuyenById(int manhomquyen) {
        // TODO Auto-generated method stub
        String tenNhomQuyen = "";
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT tennhomquyen FROM nhomquyen WHERE manhomquyen = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, manhomquyen);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tenNhomQuyen = rs.getString("tennhomquyen");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenNhomQuyen;
    }
}
