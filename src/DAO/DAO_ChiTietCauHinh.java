/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Brand;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietPhieuXuat;
import DTO.DTO_Kho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Kiet
 */
public class DAO_ChiTietCauHinh implements DAOInterface_Detail<DTO_ChiTietCauHinh> {
    public static DAO_ChiTietCauHinh getInstance() {
        return new DAO_ChiTietCauHinh();
    }

    @Override
    public int insert(ArrayList<DTO_ChiTietCauHinh> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnectDB();
                String sql = "INSERT INTO `phienbansanpham`(`maphienbansp`, `masanpham`, `rom`, `ram`, `boxuly`, `gianhap`, `giaxuat`,`soluongton`) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphienbansp());
                pst.setInt(2, t.get(i).getMasanpham());
                pst.setInt(3, t.get(i).getRom());
                pst.setInt(4, t.get(i).getRam());
                pst.setInt(5, t.get(i).getGianhap());
                pst.setInt(6, t.get(i).getGiaxuat());
                pst.setInt(7, t.get(i).getSoluongton());
                result = pst.executeUpdate();
                JDBCUtil.close(con);
            } catch (SQLException ex) {
                System.out.println("omg");
            }
        }
        return result;
    }

    public int insert(DTO_ChiTietCauHinh t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `phienbansanpham`(`maphienbansp`, `masanpham`, `rom`, `ram`, `gianhap`, `giaxuat`, `soluongton`, `trangthai`) VALUES (?,?,?,?,?,?,0,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphienbansp());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getRom());
            pst.setInt(4, t.getRam());
            pst.setInt(5, t.getGianhap());
            pst.setInt(6, t.getGiaxuat());
            pst.setInt(7, 1);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            System.out.println("omgw");
        }
        return result;
    }

    @Override
    public void delete(String masanpham, String maphienbansp) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "Delete from phienbansanpham where maphienbansp = ? and masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(maphienbansp));
            pst.setInt(2, Integer.parseInt(masanpham));
            pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            System.out.println("omg");
        }
    }

    @Override
    public int update(ArrayList<DTO_ChiTietCauHinh> t, String pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DTO_ChiTietCauHinh> selectAll(String t) {
        int id = Integer.parseInt(t);
        // select all chitietcauhinh from db 
        ArrayList<DTO_ChiTietCauHinh> result = new ArrayList<DTO_ChiTietCauHinh>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phienbansanpham WHERE masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphienbansp = rs.getInt("maphienbansp");
                int masanpham = rs.getInt("masanpham");
                int rom = rs.getInt("rom");
                int ram = rs.getInt("ram");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                int soluongton = rs.getInt("soluongton");
                DTO_ChiTietCauHinh sp = new DTO_ChiTietCauHinh(maphienbansp, masanpham, rom, ram, gianhap, giaxuat,
                        soluongton);
                result.add(sp);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            System.out.println("omg");
        }
        return result;
    }

    public int getNextID(int idsp) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT COUNT(*) AS soluong FROM phienbansanpham where masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, idsp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                int soLuong = rs.getInt("soluong");
                return soLuong + 1;
            }
        } catch (SQLException e) {
            return -12;
        }
        return -1;
    }

    public ArrayList<DTO_ChiTietCauHinh> getCauHinhbyID(int id) {
        ArrayList<DTO_ChiTietCauHinh> result = new ArrayList<DTO_ChiTietCauHinh>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM sanpham, phienbansanpham WHERE sanpham.masanpham = phienbansanpham.masanpham AND sanpham.masanpham = ? AND phienbansanpham.trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();

            while (rs.next()) {
                int maphienbansp = rs.getInt("maphienbansp");
                int masanpham = rs.getInt("masanpham");
                int rom = rs.getInt("rom");
                int ram = rs.getInt("ram");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                int soluongton = rs.getInt("soluongton");
                int trangthai = rs.getInt("trangthai");
                DTO_ChiTietCauHinh sp = new DTO_ChiTietCauHinh(maphienbansp, masanpham, rom, ram, gianhap, giaxuat,
                        soluongton);
                result.add(sp);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    public int getMaxID(int masanpham) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT MAX(maphienbansp) AS maxid FROM phienbansanpham WHERE masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, masanpham);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                int maxid = rs.getInt("maxid");
                return maxid;
            }
        } catch (SQLException e) {
            return -1;
        }
        return -1;
    }

    public DTO_ChiTietCauHinh selectById(int maphienbansp) {
        DTO_ChiTietCauHinh result = new DTO_ChiTietCauHinh();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phienbansanpham WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphienbansp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                int masanpham = rs.getInt("masanpham");
                int rom = rs.getInt("rom");
                int ram = rs.getInt("ram");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                int soluongton = rs.getInt("soluongton");
                result = new DTO_ChiTietCauHinh(maphienbansp, masanpham, rom, ram, gianhap, giaxuat, soluongton);
            }
            JDBCUtil.close(con);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi select: " + e.getMessage());
        }
        return result;
    }

    public int updateSoLuongTon(int maphienban, long soluong) {
        DTO_ChiTietCauHinh pbsp = this.selectById(maphienban);
        int result = 0;
        long quantity_change = pbsp.getSoluongton() + soluong;
        JOptionPane.showMessageDialog(null, "so luong ton: " + quantity_change + " " + pbsp.getMaphienbansp());
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `phienbansanpham` SET `soluongton`=? WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setLong(1, quantity_change);
            pst.setInt(2, pbsp.getMaphienbansp());
            result = pst.executeUpdate();
          
            JOptionPane.showMessageDialog(null, "update so luong ton thanh cong");
            JDBCUtil.close(con);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loi update so luong ton: " + ex.getMessage());

        }
        return result;
    }

    public ArrayList<DTO_ChiTietCauHinh> getAllData() {
        ArrayList<DTO_ChiTietCauHinh> result = new ArrayList<DTO_ChiTietCauHinh>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM phienbansanpham";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphienbansp = rs.getInt("maphienbansp");
                int masanpham = rs.getInt("masanpham");
                int rom = rs.getInt("rom");
                int ram = rs.getInt("ram");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                int soluongton = rs.getInt("soluongton");
                DTO_ChiTietCauHinh sp = new DTO_ChiTietCauHinh(maphienbansp, masanpham, rom, ram, gianhap, giaxuat,
                        soluongton);
                result.add(sp);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return result;
    }

    public int updateSoLuongTonPX(int maphienbansp, int soluong) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `phienbansanpham` SET `soluongton`= soluongton - ? WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, soluong);
            pst.setInt(2, maphienbansp);
            result = pst.executeUpdate();
            if (result > 0) JOptionPane.showMessageDialog(null, "tru so luong ton thanh cong");
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loi update so luong ton: " + ex.getMessage());

        }
        return result;
    }

    public int updateMPBSB(DTO_ChiTietCauHinh t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `phienbansanpham` SET `rom`=?,`ram`=?,`gianhap`=?,`giaxuat`=?,`soluongton`=? WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getRom());
            pst.setInt(2, t.getRam());
            pst.setInt(3, t.getGianhap());
            pst.setInt(4, t.getGiaxuat());
            pst.setInt(5, t.getSoluongton());
            pst.setInt(6, t.getMaphienbansp());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            System.out.println("omgw");
        }
        return result;
    }

    public int getMaxID() {
        int max = 0;
        try {
            String sql = "SELECT MAX(maphienbansp) FROM phienbansanpham";
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

    public int getSoLuong(int masp) {
        int soluong = 0;
        try {
            String sql = "SELECT SUM(soluongton) FROM phienbansanpham WHERE masanpham = ?";
            Connection con = (Connection) JDBCUtil.getConnectDB();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, masp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                soluong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soluong;
    }

    public void truSoLuongPhienBanSanPham(int maphienbansp, int soluong) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `phienbansanpham` SET `soluongton`= soluongton - ? WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, soluong);
            pst.setInt(2, maphienbansp);
            pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loi update so luong ton: " + ex.getMessage());
        }
    }

    public boolean checkMaSP(int masp) {
        try {
            String sql = "SELECT * FROM phienbansanpham WHERE masanpham = ?";
            Connection con = (Connection) JDBCUtil.getConnectDB();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, masp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<DTO_ChiTietCauHinh> getAllPhienBanByListMaPhienBan(ArrayList<Integer> listmaphienban) {
        ArrayList<DTO_ChiTietCauHinh> result = new ArrayList<DTO_ChiTietCauHinh>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();

            String sql = "SELECT * FROM phienbansanpham WHERE maphienbansp IN (";
            for (int i = 0; i < listmaphienban.size(); i++) {
                if (i == listmaphienban.size() - 1) {
                    sql += listmaphienban.get(i) + ")";
                } else {
                    sql += listmaphienban.get(i) + ",";
                }
            }
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                //neu so luong ton > 0 thi them vao list
                if (rs.getInt("soluongton") > 0) {
                    int maphienbansp = rs.getInt("maphienbansp");
                    int masanpham = rs.getInt("masanpham");
                    int rom = rs.getInt("rom");
                    int ram = rs.getInt("ram");
                    int gianhap = rs.getInt("gianhap");
                    int giaxuat = rs.getInt("giaxuat");
                    int soluongton = rs.getInt("soluongton");
                    DTO_ChiTietCauHinh sp = new DTO_ChiTietCauHinh(maphienbansp, masanpham, rom, ram, gianhap, giaxuat,
                            soluongton);
                    result.add(sp);
                }
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        //sap xep result co tensanpham tang dan
        return result;
    }

    public int truSoLuongTonPhienBanSanPham(int maphienbansp, int soluong) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE `phienbansanpham` SET `soluongton`= soluongton - ? WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, soluong);
            pst.setInt(2, maphienbansp);
            result = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "tru so luong ton thanh cong");
            JDBCUtil.close(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loi update so luong ton: " + ex.getMessage());
        }
        return result;

}
}
