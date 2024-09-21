package DAO;

import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietPhieuTra;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;

import java.lang.reflect.Array;
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
public class DAO_ChiTietPhieuTra implements DAOChiTietInterface<DTO_ChiTietPhieuTra> {
    public static DAO_ChiTietPhieuTra getInstance() {
        return new DAO_ChiTietPhieuTra();
    }

    @Override
    public int insert(ArrayList<DTO_ChiTietPhieuTra> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnectDB();
                String sql = "INSERT INTO `ctphieutra`(`maphieutra`, `maphienbansp`, `soluong`, `tonggia`) VALUES (?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphieu());
                pst.setInt(2, t.get(i).getMaphienbansp());
                pst.setLong(3, t.get(i).getSoluong());
                pst.setLong(4, t.get(i).gettonggia());
                result = pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu trả thành công");
                JDBCUtil.close(con);
               
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Loi insert hi tiet phieu trả ne: " + e.getMessage());
            }
            // DAO_ChiTietCauHinh.getInstance().updateSoLuongTon(t.get(i).getMaphienbansp(), t.get(i).getSoluong());
        }
        // TODO Auto-generated method stub

        return result;
    }

    @Override
    public int delete(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int update(ArrayList<DTO_ChiTietPhieuTra> t, String pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<DTO_ChiTietPhieuTra> selectAll(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    public void getAll() {
        //

    }

    public ArrayList<DTO_ChiTietPhieuTra> getAllByMaPhieuTra(int maphieutra) {
        ArrayList<DTO_ChiTietPhieuTra> list = new ArrayList<DTO_ChiTietPhieuTra>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM `ctphieutra` WHERE `maphieutra` = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphieutra);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_ChiTietPhieuTra dto = new DTO_ChiTietPhieuTra();
                dto.setMaphieu(rs.getInt("maphieutra"));
                dto.setMaphienbansp(rs.getInt("maphienbansp"));
                dto.setSoluong(rs.getInt("soluong"));
                dto.settonggia(rs.getLong("tonggia"));
                list.add(dto);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi get all by ma phieu tra ne: " + e.getMessage());
        }
        return list;
    }

    
}