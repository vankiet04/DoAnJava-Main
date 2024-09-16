package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectDB.JDBCUtil;
import DTO.DTO_HuyPhieuXuat;

public class DAO_HuyHoaDon {
    public DAO_HuyHoaDon() {
    }

    public int insert(DTO_HuyPhieuXuat t) {
        int res = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO huyhoadon(mahoadon, manv, lydohuy, thoigian) VALUES(?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMahd());
            pst.setInt(2, t.getManv());
            pst.setString(3, t.getLydo());
            pst.setString(4, t.getNgayhuy());
            res = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}