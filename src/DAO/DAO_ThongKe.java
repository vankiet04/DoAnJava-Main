/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Brand;
import DTO.DTO_Product;
import DTO.DTO_ThongKeThang;
import DTO.DTO_ThongKeTuNgayDenNgay;
import DTO.DTO_ThongKeTungNgayTrongThang;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 *
 * @author Kiet
 */
public class DAO_ThongKe {
    public ArrayList<DTO_ThongKeThang> getThongKeTheoThang(int nam) {
        ArrayList<DTO_ThongKeThang> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT months.month AS thang, \n"
                    + "       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi,\n"
                    + "       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu\n"
                    + "FROM (\n"
                    + "       SELECT 1 AS month\n"
                    + "       UNION ALL SELECT 2\n"
                    + "       UNION ALL SELECT 3\n"
                    + "       UNION ALL SELECT 4\n"
                    + "       UNION ALL SELECT 5\n"
                    + "       UNION ALL SELECT 6\n"
                    + "       UNION ALL SELECT 7\n"
                    + "       UNION ALL SELECT 8\n"
                    + "       UNION ALL SELECT 9\n"
                    + "       UNION ALL SELECT 10\n"
                    + "       UNION ALL SELECT 11\n"
                    + "       UNION ALL SELECT 12\n"
                    + "     ) AS months\n"
                    + "LEFT JOIN phieuxuat ON MONTH(phieuxuat.thoigian) = months.month AND YEAR(phieuxuat.thoigian) = ? \n"
                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp\n"
                    + "LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp\n"
                    + "GROUP BY months.month\n"
                    + "ORDER BY months.month;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, nam);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int thang = rs.getInt("thang");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                long loinhuan = doanhthu - chiphi;
                DTO_ThongKeThang thongke = new DTO_ThongKeThang(thang, chiphi, doanhthu, loinhuan);
                result.add(thongke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<DTO_ThongKeTungNgayTrongThang> getThongKeTungNgayTrongThang(int thang, int nam) {
        ArrayList<DTO_ThongKeTungNgayTrongThang> result = new ArrayList<>();
        try {
            String ngayString = nam + "-" + thang + "-" + "01";
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT \n"
                    + "  dates.date AS ngay, \n"
                    + "  COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, \n"
                    + "  COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu\n"
                    + "FROM (\n"
                    + "  SELECT DATE('" + ngayString + "') + INTERVAL c.number DAY AS date\n"
                    + "  FROM (\n"
                    + "    SELECT 0 AS number\n"
                    + "    UNION ALL SELECT 1\n"
                    + "    UNION ALL SELECT 2\n"
                    + "    UNION ALL SELECT 3\n"
                    + "    UNION ALL SELECT 4\n"
                    + "    UNION ALL SELECT 5\n"
                    + "    UNION ALL SELECT 6\n"
                    + "    UNION ALL SELECT 7\n"
                    + "    UNION ALL SELECT 8\n"
                    + "    UNION ALL SELECT 9\n"
                    + "    UNION ALL SELECT 10\n"
                    + "    UNION ALL SELECT 11\n"
                    + "    UNION ALL SELECT 12\n"
                    + "    UNION ALL SELECT 13\n"
                    + "    UNION ALL SELECT 14\n"
                    + "    UNION ALL SELECT 15\n"
                    + "    UNION ALL SELECT 16\n"
                    + "    UNION ALL SELECT 17\n"
                    + "    UNION ALL SELECT 18\n"
                    + "    UNION ALL SELECT 19\n"
                    + "    UNION ALL SELECT 20\n"
                    + "    UNION ALL SELECT 21\n"
                    + "    UNION ALL SELECT 22\n"
                    + "    UNION ALL SELECT 23\n"
                    + "    UNION ALL SELECT 24\n"
                    + "    UNION ALL SELECT 25\n"
                    + "    UNION ALL SELECT 26\n"
                    + "    UNION ALL SELECT 27\n"
                    + "    UNION ALL SELECT 28\n"
                    + "    UNION ALL SELECT 29\n"
                    + "    UNION ALL SELECT 30\n"
                    + "  ) AS c\n"
                    + "  WHERE DATE('" + ngayString + "') + INTERVAL c.number DAY <= LAST_DAY('" + ngayString + "')\n"
                    + ") AS dates\n"
                    + "LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date\n"
                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp\n"
                    + "LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp\n"
                    + "GROUP BY dates.date\n"
                    + "ORDER BY dates.date;";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                long loinhuan = doanhthu - chiphi;
                DTO_ThongKeTungNgayTrongThang tn = new DTO_ThongKeTungNgayTrongThang(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<DTO_ThongKeTuNgayDenNgay> getThongKeTuNgayDenNgay(String start, String end) {
        ArrayList<DTO_ThongKeTuNgayDenNgay> result = new ArrayList<>();
        start = start.replace("/", "-");
        end = end.replace("/", "-");
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT dates.selected_date, COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu " +
            "FROM ( " +
            "    SELECT ADDDATE('1970-01-01', t4.i * 10000 + t3.i * 1000 + t2.i * 100 + t1.i * 10 + t0.i) AS selected_date " +
            "    FROM " +
            "        (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t0, " +
            "        (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t1, " +
            "        (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t2, " +
            "        (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t3, " +
            "        (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t4 " +
            ") dates " +
            "LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.selected_date " +
            "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat " +
            "LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp " +
            "LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp " +
            "WHERE dates.selected_date BETWEEN ? AND ? " +
            "GROUP BY dates.selected_date " +
            "ORDER BY dates.selected_date;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, start);
            pst.setString(2, end);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("selected_date");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                long loinhuan = doanhthu - chiphi;
                DTO_ThongKeTuNgayDenNgay tn = new DTO_ThongKeTuNgayDenNgay(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
