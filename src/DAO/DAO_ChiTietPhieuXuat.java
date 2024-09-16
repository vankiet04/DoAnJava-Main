package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.kitfox.svg.A;

import ConnectDB.JDBCUtil;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietPhieuXuat;

public class DAO_ChiTietPhieuXuat implements DAOChiTietInterface<DTO_ChiTietPhieuXuat> {
    public static DAO_ChiTietPhieuXuat getInstance() {
        return new DAO_ChiTietPhieuXuat();
    }


    @Override
    public int insert(ArrayList<DTO_ChiTietPhieuXuat> t) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `ctphieuxuat`(`maphieuxuat`, `maphienbansp`, `soluong`, `dongia`) VALUES (?,?,?,?)";
            for (int i = 0; i < t.size(); i++) {
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphieunhap());
                pst.setInt(2, t.get(i).getMaphienbansp());
                pst.setInt(3, t.get(i).getSoluong());
                pst.setLong(4, t.get(i).getDongia());
                result = pst.executeUpdate();
            }
            JDBCUtil.close(con);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int delete(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int update(ArrayList<DTO_ChiTietPhieuXuat> t, String pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<DTO_ChiTietPhieuXuat> selectAll(String t) {
        // TODO Auto-generated method stub
        ArrayList<DTO_ChiTietPhieuXuat> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_ChiTietPhieuXuat ctphieuxuat = new DTO_ChiTietPhieuXuat();
                ctphieuxuat.setMaphieunhap(rs.getInt("maphieuxuat"));
                ctphieuxuat.setMaphienbansp(rs.getInt("maphienbansp"));
                ctphieuxuat.setSoluong(rs.getInt("soluong"));
                ctphieuxuat.setDongia(rs.getLong("dongia"));
                result.add(ctphieuxuat);
            }
          
            JDBCUtil.close(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Loi select all chi tiet phieu nhap ne: " + e.getMessage());
        }
        return result;
    
    }

    public ArrayList<DTO_ChiTietPhieuXuat> getPhieuXuatTheoID(int id) {
        ArrayList<DTO_ChiTietPhieuXuat> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_ChiTietPhieuXuat ctphieuxuat = new DTO_ChiTietPhieuXuat();
                ctphieuxuat.setMaphieunhap(rs.getInt("maphieuxuat"));
                ctphieuxuat.setMaphienbansp(rs.getInt("maphienbansp"));
                ctphieuxuat.setSoluong(rs.getInt("soluong"));
                ctphieuxuat.setDongia(rs.getLong("dongia"));
                list.add(ctphieuxuat);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<DTO_ChiTietPhieuXuat> getCtPhieuXuatbyID(int masp) {
        ArrayList<DTO_ChiTietPhieuXuat> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphienbansp = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, masp);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_ChiTietPhieuXuat ctphieuxuat = new DTO_ChiTietPhieuXuat();
                ctphieuxuat.setMaphieunhap(rs.getInt("maphieuxuat"));
                ctphieuxuat.setMaphienbansp(rs.getInt("maphienbansp"));
                ctphieuxuat.setSoluong(rs.getInt("soluong"));
                ctphieuxuat.setDongia(rs.getLong("dongia"));
                list.add(ctphieuxuat);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<DTO_ChiTietPhieuXuat> getCtPhieuXuatbyMaphienban(int mapb) {
        // lay so luong cua ma phien ban do thoi
        
        ArrayList<DTO_ChiTietPhieuXuat> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphienbansp = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, mapb);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_ChiTietPhieuXuat ctphieuxuat = new DTO_ChiTietPhieuXuat();
                ctphieuxuat.setMaphieunhap(rs.getInt("maphieuxuat"));
                ctphieuxuat.setMaphienbansp(rs.getInt("maphienbansp"));
                ctphieuxuat.setSoluong(rs.getInt("soluong"));
                ctphieuxuat.setDongia(rs.getLong("dongia"));
                list.add(ctphieuxuat);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

     public int updatesoluongtonkhihuy(ArrayList<DTO_ChiTietPhieuXuat> t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            for (int i = 0; i < t.size(); i++) {
                String sql = "UPDATE `phienbansanpham` SET `soluongton` = `soluongton` + ? WHERE `maphienbansp` = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getSoluong());
                pst.setInt(2, t.get(i).getMaphienbansp());
                result = pst.executeUpdate();
            }
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loi update so luong ton: " + ex.getMessage());

        }
        return result;
    }
    
}
