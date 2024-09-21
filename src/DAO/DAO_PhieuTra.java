package DAO;
import DTO.DTO_PhieuTra;
import java.util.ArrayList;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_NhanVien;
import DTO.DTO_PhieuTra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_PhieuTra implements DAOInterface<DTO_PhieuTra> {

    // getInstance
    public static DAO_PhieuTra getInstance() {
        return new DAO_PhieuTra();
    }

    @Override
    public int insert(DTO_PhieuTra t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `phieutra`(`maphieutra`, `thoigian`, `manhacungcap`, `nguoitao`, `tongtien`, `trangthai`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieu());
            pst.setTimestamp(2, t.getThoigiantao());
            pst.setInt(3, t.getManhacungcap());
            pst.setInt(4, t.getManguoitao());
            pst.setLong(5, t.getTongTien());
            pst.setInt(6, t.getTrangthai());
            result = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm phiếu trả thành công");
            JDBCUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi insert phiếu trả ne: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int update(DTO_PhieuTra t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DTO_PhieuTra> getAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllData'");
    }

    @Override
    public DTO_PhieuTra selectById(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        int maxId = 0;
        try {
            String query = "SELECT MAX(maphieutra) as maxId FROM phieutra";
            Connection con = JDBCUtil.getConnectDB();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("maxId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId + 1;

    }

    public ArrayList<DTO_PhieuTra> getAll() {
        ArrayList<DTO_PhieuTra> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phieutra";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieutra");
                java.sql.Timestamp thoigian = rs.getTimestamp("thoigian");
                int manhacungcap = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitao");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                DTO_PhieuTra phieuTra = new DTO_PhieuTra(maphieu, thoigian, manhacungcap, nguoitao, tongtien,
                        trangthai);
                result.add(phieuTra);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi get all phiếu trả ne: " + e.getMessage());
        }
        return result;
    }
    
    public DTO_PhieuTra getPhieuTraById(int maphieutra) {
        DTO_PhieuTra result = null;
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phieutra WHERE maphieutra = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphieutra);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieutra");
                java.sql.Timestamp thoigian = rs.getTimestamp("thoigian");
                int manhacungcap = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitao");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                result = new DTO_PhieuTra(maphieu, thoigian, manhacungcap, nguoitao, tongtien,
                        trangthai);
            }
            JDBCUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Loi get phiếu trả by id ne: " + e.getMessage());
        }
        return result;
    }

}