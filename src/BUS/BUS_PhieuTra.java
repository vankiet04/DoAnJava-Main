package BUS;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import DAO.DAO_PhieuTra;
import DTO.DTO_PhieuTra;
import DAO.DAO_ChiTietPhieuNhap;
import DAO.DAO_ChiTietSanPham;
import DAO.DAO_PhieuNhap;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietSanPham;
import DTO.DTO_PhieuTra;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;


public class BUS_PhieuTra {
    DAO_PhieuTra daoPhieuTra = new DAO_PhieuTra();
    DTO_PhieuTra dtoPhieuTra = new DTO_PhieuTra();
       public final DAO_ChiTietSanPham chitietsanphamDAO = new DAO_ChiTietSanPham();
    
    ArrayList<DTO_PhieuTra> listPhieuNhap;
    BUS_NhaCungCap nccBUS = new BUS_NhaCungCap();
    BUS_NhanVien nvBUS = new BUS_NhanVien();

    public BUS_PhieuTra() {
    }

    public int getMaPhieuTra() {
        return daoPhieuTra.getAutoIncrement();
    }

    public int themPhieuTra(DTO_PhieuTra dtoPhieuTra) {
        return daoPhieuTra.insert(dtoPhieuTra);
    }

    public ArrayList<DTO_PhieuTra> getAll() {
        return daoPhieuTra.getAll();
    }

    public ArrayList<DTO_PhieuTra> fillterPhieuNhapCoNgay(int type, String input, int mancc, int manv, Date ngaybatdau,
            Date ngayketthuc, long price_min, long price_max) {
        ArrayList<DTO_PhieuTra> result = new ArrayList<>();
        java.sql.Timestamp timebd = convertDateToTimestamp(ngaybatdau);
        java.sql.Timestamp timekt = convertDateToTimestamp(ngayketthuc);
        if (timebd == null || timekt == null) {
            // change timebd to 1-1-1970
            timebd = new java.sql.Timestamp(0);
            // timekt to now
            timekt = new java.sql.Timestamp(System.currentTimeMillis());
        }
        for (DTO_PhieuTra phieutra : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieutra.getMaphieu()).contains(input.toLowerCase())
                            || nccBUS.getTenNhaCungCap(phieutra.getManhacungcap()).toLowerCase()
                                    .contains(input.toLowerCase())
                            || nvBUS.getNameById(phieutra.getManguoitao()).toLowerCase()
                                    .contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieutra.getMaphieu()).contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nccBUS.getTenNhaCungCap(phieutra.getManhacungcap()).toLowerCase()
                            .contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieutra.getManguoitao()).toLowerCase().contains(input.toLowerCase())) {
                        match = true;
                    }
                }
            }

            if (match && (manv == 0 || phieutra.getManguoitao() == manv)
                    && (mancc == 0 || phieutra.getManhacungcap() == mancc)
                    && (phieutra.getThoigiantao().compareTo(timebd) >= 0)
                    && (phieutra.getThoigiantao().compareTo(timekt) <= 0)
                    && phieutra.getTongTien() >= price_min
                    && phieutra.getTongTien() <= price_max) {
                // JOptionPane.showMessageDialog(null, "phieutra: "+result);
                result.add(phieutra);
            }

        }
        return result;
    }
    public Timestamp convertDateToTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public Date convertTimestampToDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp.getTime());
    }

    public boolean compareTimestamp(Timestamp t1, Timestamp t2) {
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.compareTo(t2) == 0;
    }

    public DTO_PhieuTra getPhieuTraById(int id) {
        return daoPhieuTra.getPhieuTraById(id);
    }
    
}
