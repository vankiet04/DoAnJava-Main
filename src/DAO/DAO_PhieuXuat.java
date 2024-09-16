package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;
import DTO.DTO_PhieuNhap;
import DTO.DTO_PhieuXuat;

public class DAO_PhieuXuat implements DAOInterface<DTO_PhieuXuat> {
    public static DAO_PhieuXuat getInstance() {
        return new DAO_PhieuXuat();
    }

    @Override
    public int insert(DTO_PhieuXuat t) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO phieuxuat(maphieuxuat, thoigian, manv, makh, tongtien, trangthai) VALUES(?, ?, ?, ?, ?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieuxuat());
            pst.setString(2, t.getThoigian());
            pst.setInt(3, t.getIdnhanvien());
            pst.setInt(4, t.getIdkhachhang());
            pst.setLong(5, t.getTongtien());

            result = pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(DTO_PhieuXuat t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DTO_PhieuXuat> getAllData() {
        // TODO Auto-generated method stub
        ArrayList<DTO_PhieuXuat> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            //giam dan ma phieu xuat
            String sql = "SELECT * FROM phieuxuat where trangthai = 1 ORDER BY maphieuxuat DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                DTO_PhieuXuat phieuxuat = new DTO_PhieuXuat();
                phieuxuat.setMaphieuxuat(rs.getInt("maphieuxuat"));
                phieuxuat.setThoigian(rs.getString("thoigian"));
                phieuxuat.setIdnhanvien(rs.getInt("manv"));
                phieuxuat.setIdkhachhang(rs.getInt("makh"));
                phieuxuat.setTongtien(rs.getLong("tongtien"));
                list.add(phieuxuat);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DTO_PhieuXuat selectById(String t) {
        // TODO Auto-generated method stub
        DTO_PhieuXuat result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
           
            String sql = "SELECT * FROM phieuxuat where maphieuxuat = ? and trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                result = new DTO_PhieuXuat();
                result.setMaphieuxuat(rs.getInt("maphieuxuat"));
                result.setThoigian(rs.getString("thoigian"));
                result.setIdnhanvien(rs.getInt("manv"));
                result.setIdkhachhang(rs.getInt("makh"));
                result.setTongtien(rs.getLong("tongtien"));
            }
            con.close();
         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Loi select by id: " + e.getMessage());

        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        // get max id from table PhieuXuat
        int max = 0;
        try {
            String sql = "SELECT MAX(maphieuxuat) FROM phieuxuat";
            Connection con = (Connection) JDBCUtil.getConnectDB();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max + 1;
    }

    public DTO_PhieuXuat getPhieuXuatTheoID(int id) {
        // TODO Auto-generated method stub
        DTO_PhieuXuat phieuxuat = new DTO_PhieuXuat();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phieuxuat where maphieuxuat = ? and trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                phieuxuat.setMaphieuxuat(rs.getInt("maphieuxuat"));
                phieuxuat.setThoigian(rs.getString("thoigian"));
                phieuxuat.setIdnhanvien(rs.getInt("manv"));
                phieuxuat.setIdkhachhang(rs.getInt("makh"));
                phieuxuat.setTongtien(rs.getLong("tongtien"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phieuxuat;
    }
    
    public ArrayList<DTO_PhieuXuat> getChiTietPhieuXuatTheoID(int id) {
        // TODO Auto-generated method stub
        ArrayList<DTO_PhieuXuat> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phieuxuat where makh = ? and trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                DTO_PhieuXuat phieuxuat = new DTO_PhieuXuat();
                phieuxuat.setMaphieuxuat(rs.getInt("maphieuxuat"));
                phieuxuat.setThoigian(rs.getString("thoigian"));
                phieuxuat.setIdnhanvien(rs.getInt("manv"));
                phieuxuat.setIdkhachhang(rs.getInt("makh"));
                phieuxuat.setTongtien(rs.getLong("tongtien"));
                list.add(phieuxuat);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public ArrayList<DTO_PhieuXuat> getPhieuXuatGanDay() {
        // get 20 recent phieuxuat
        ArrayList<DTO_PhieuXuat> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phieuxuat where trangthai = 1 ORDER BY maphieuxuat DESC LIMIT 20";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                DTO_PhieuXuat phieuxuat = new DTO_PhieuXuat();
                phieuxuat.setMaphieuxuat(rs.getInt("maphieuxuat"));
                phieuxuat.setThoigian(rs.getString("thoigian"));
                phieuxuat.setIdnhanvien(rs.getInt("manv"));
                phieuxuat.setIdkhachhang(rs.getInt("makh"));
                phieuxuat.setTongtien(rs.getLong("tongtien"));
                list.add(phieuxuat);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
 
}

    public int updateTrangThaiPhieuXuat(int maphieuxuat) {
        // update trangthai = 0
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE phieuxuat SET trangthai = 0 WHERE maphieuxuat = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphieuxuat);
            result = pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getTrangThaiPhieuXuat(int maphieuxuat) {
        // get trangthai of phieuxuat
        int trangthai = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT trangthai FROM phieuxuat WHERE maphieuxuat = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphieuxuat);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                trangthai = rs.getInt("trangthai");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangthai;
    }
}