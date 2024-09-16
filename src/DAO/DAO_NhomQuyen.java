package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import ConnectDB.JDBCUtil;
import DTO.DTO_NhomQuyen;

public class DAO_NhomQuyen implements DAOInterface<DTO_NhomQuyen> {

    @Override
    public int insert(DTO_NhomQuyen t) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO nhomquyen(tennhomquyen, trangthai) VALUES(?,1)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennhomquyen());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(DTO_NhomQuyen t) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "UPDATE nhomquyen SET tennhomquyen = ? WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennhomquyen());
            pst.setInt(2, t.getManhomquyen());
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM nhomquyen WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<DTO_NhomQuyen> getAllData() {
        ArrayList<DTO_NhomQuyen> list = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM nhomquyen WHERE trangthai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               int manhomquyen = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                DTO_NhomQuyen nq = new DTO_NhomQuyen(manhomquyen, tennhomquyen);
                list.add(nq);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DTO_NhomQuyen selectById(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }

    public int insertChitietNhomQuyen(int manhomquyen, HashMap<String, ArrayList<String>> ctquyen) {
     
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO chucnangnhomquyen(`manhomquyen`, `machucnang`, `hanhdong`) VALUES(?,?,?)";
            // traverse all the key hash map
            for (String key : ctquyen.keySet()) {
                // get the value of the key
                ArrayList<String> value = ctquyen.get(key);
                // traverse all the value of the key
                for (String val : value) {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, manhomquyen);
                    pst.setString(2, key);
                    pst.setString(3, val);
                    result = pst.executeUpdate();
                }
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public DTO_NhomQuyen getNhomQuyen(int manhomquyen) {
        DTO_NhomQuyen nq = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM nhomquyen WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, manhomquyen);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manhomquyen1 = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                nq = new DTO_NhomQuyen(manhomquyen1, tennhomquyen);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nq;
    }

    public int getManhomquyenbyTennhomquyen(String tennhomquyen) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT manhomquyen FROM `nhomquyen` WHERE tennhomquyen=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, tennhomquyen);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("manhomquyen");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Loi lay manhomquyen: " + e.getMessage());
        }
        return -1;
}

    public int deleteChitietNhomQuyen(int manhomquyen) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "DELETE FROM chucnangnhomquyen WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, manhomquyen);
            result = pst.executeUpdate();
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        }

    public ArrayList<DTO_NhomQuyen> search(String keyword) {
      
        ArrayList<DTO_NhomQuyen> result = new ArrayList<DTO_NhomQuyen>();
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM nhomquyen where trangthai = 1 and (manhomquyen like ? or tennhomquyen like ?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                DTO_NhomQuyen nq = new DTO_NhomQuyen(manhomquyen, tennhomquyen);
                result.add(nq);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
