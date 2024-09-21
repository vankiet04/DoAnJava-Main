package DAO;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;
import DTO.DTO_ChiTietSanPhamTra;

import java.lang.reflect.Array;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KIET
 */
public class DAO_ChiTietSanPhamTra implements DAOInterface<DTO_ChiTietSanPhamTra> {
    public static DAO_ChiTietSanPhamTra getInstance() {
        return new DAO_ChiTietSanPhamTra();
    }
  
    @Override
    public int insert(DTO_ChiTietSanPhamTra t) {
        int result = 0;
          // private String imei;
    // private int maphienbansp;
    // private int maphieutra;
    // private int tinhtrang;
       

    return result;
     
        // TODO Auto-generated method stub
        
    }

    @Override
    public int update(DTO_ChiTietSanPhamTra t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DTO_ChiTietSanPhamTra> getAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllData'");
    }

    @Override
    public DTO_ChiTietSanPhamTra selectById(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public int getAutoIncrement() {
        return 0;
        // TODO Auto-generated method stub
    }
    
    public int themChiTietSanPhamTra(DTO_ChiTietSanPhamTra t, int maphieutra) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `ctsphamtra`(`maimei`, `maphienbansp`, `maphieutra`, `tinhtrang`) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getImei());
            pst.setInt(2, t.getMaphienbansp());
            pst.setInt(3, maphieutra);
            pst.setInt(4, t.getTinhtrang());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
            JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu trả thành công");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi insert hi tiet phieu trả ne: " + e.getMessage());
        }
        return result;
    }
    
    public ArrayList<DTO_ChiTietSanPhamTra> getAll() {
        ArrayList<DTO_ChiTietSanPhamTra> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctsphamtra";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_ChiTietSanPhamTra ctsp = new DTO_ChiTietSanPhamTra();
                ctsp.setImei(rs.getString("maimei"));
                ctsp.setMaphienbansp(rs.getInt("maphienbansp"));
                ctsp.setmaphieutra(rs.getInt("maphieutra"));
                ctsp.setTinhtrang(rs.getInt("tinhtrang"));
                list.add(ctsp);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi get all ctsphamtra ne: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<DTO_ChiTietSanPhamTra> getAllImeiByMaphienbanVaListMaphieunhap(int maphienban, int maphieunhap) {
        ArrayList<DTO_ChiTietSanPhamTra> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctsphamtra WHERE maphienbansp = ? AND maphieutra = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphienban);
            pst.setInt(2, maphieunhap);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_ChiTietSanPhamTra ctsp = new DTO_ChiTietSanPhamTra();
                ctsp.setImei(rs.getString("maimei"));
                ctsp.setMaphienbansp(rs.getInt("maphienbansp"));
                ctsp.setmaphieutra(rs.getInt("maphieutra"));
                ctsp.setTinhtrang(rs.getInt("tinhtrang"));
                list.add(ctsp);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi get all ctsphamtra ne: " + e.getMessage());
        }
        return list;
    }
}