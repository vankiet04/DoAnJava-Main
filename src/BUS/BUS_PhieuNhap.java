/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_ChiTietPhieuNhap;
import DAO.DAO_ChiTietSanPham;
import DAO.DAO_PhieuNhap;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietSanPham;
import DTO.DTO_PhieuNhap;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 *
 * @author KIET
 */
public class BUS_PhieuNhap {
    public final DAO_PhieuNhap phieunhapDAO = new DAO_PhieuNhap();
   public final DAO_ChiTietPhieuNhap ctPhieuNhapDAO = new DAO_ChiTietPhieuNhap();
       public final DAO_ChiTietSanPham chitietsanphamDAO = new DAO_ChiTietSanPham();
    
    ArrayList<DTO_PhieuNhap> listPhieuNhap;
    BUS_NhaCungCap nccBUS = new BUS_NhaCungCap();
    BUS_NhanVien nvBUS = new BUS_NhanVien();
    public BUS_PhieuNhap() {
    }

    public ArrayList<DTO_PhieuNhap> getAll() {
        this.listPhieuNhap = phieunhapDAO.getAllData();
        return this.listPhieuNhap;
    }

    public ArrayList<DTO_ChiTietSanPham> convertHashMapToArray(
            HashMap<Integer, ArrayList<DTO_ChiTietSanPham>> chitietsanpham) {
        ArrayList<DTO_ChiTietSanPham> result = new ArrayList<>();
        for (ArrayList<DTO_ChiTietSanPham> ctsp : chitietsanpham.values()) {
            result.addAll(ctsp);
        }
        return result;
    }

    public DTO_ChiTietPhieuNhap findCT(ArrayList<DTO_ChiTietPhieuNhap> ctphieu, int mapb) {
        DTO_ChiTietPhieuNhap p = null;
        int i = 0;
        while (i < ctphieu.size() && p == null) {
            if (ctphieu.get(i).getMaphienbansp() == mapb) {
                p = ctphieu.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public long getTongTien(ArrayList<DTO_ChiTietPhieuNhap> ctphieu) {
        long result = 0;
        for (DTO_ChiTietPhieuNhap item : ctphieu) {
            result += item.getDongia() * item.getSoluong();
        }
        return result;
    }

    public boolean add(DTO_PhieuNhap phieu, ArrayList<DTO_ChiTietPhieuNhap> ctPhieu) {
        boolean check = phieunhapDAO.insert(phieu) != 0;
        Random rand = new Random();
        ctPhieu.forEach((ctPhieuItem) -> {
            ctPhieuItem.setMaphieu(phieu.getMaphieu());
            for (int i = 0; i < ctPhieuItem.getSoluong(); i++) {
                String maimei = ctPhieuItem.getMaphieu() + String.format("%09d", rand.nextInt(100000000));
                DTO_ChiTietSanPham ctsanpham = new DTO_ChiTietSanPham(maimei, ctPhieuItem.getMaphienbansp(), phieu.getMaphieu(), 0, 1);
                chitietsanphamDAO.insert(ctsanpham);
            }
        });
        if (check) {
            check = ctPhieuNhapDAO.insert(ctPhieu) != 0;
        }
        return check;
    }

    public int cancelPhieuNhap(int maphieu) {
        return phieunhapDAO.cancelPhieuNhap(maphieu);
    }

    public ArrayList<DTO_ChiTietPhieuNhap> getChiTietPhieu_Type(int maphieunhap) {
        ArrayList<DTO_ChiTietPhieuNhap> arr = ctPhieuNhapDAO.selectAll(Integer.toString(maphieunhap));
        ArrayList<DTO_ChiTietPhieuNhap> result = new ArrayList<>();
        for (DTO_ChiTietPhieuNhap i : arr) {
            result.add(i);
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
    

    public ArrayList<DTO_PhieuNhap> fillterPhieuNhapCoNgay(int type, String input, int mancc, int manv, Date ngaybatdau,
            Date ngayketthuc, long price_min, long price_max) {
        ArrayList<DTO_PhieuNhap> result = new ArrayList<>();
        java.sql.Timestamp timebd = convertDateToTimestamp(ngaybatdau);
        java.sql.Timestamp timekt = convertDateToTimestamp(ngayketthuc);
        if (timebd == null || timekt == null) {
            // change timebd to 1-1-1970
            timebd = new java.sql.Timestamp(0);
            // timekt to now
            timekt = new java.sql.Timestamp(System.currentTimeMillis());
        }
        for (DTO_PhieuNhap phieuNhap : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input.toLowerCase())
                            || nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input.toLowerCase())
                            || nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input.toLowerCase())) {
                        match = true;
                    }
                }
            }
           



               if(match && (manv == 0 || phieuNhap.getManguoitao() == manv) && (mancc == 0 || phieuNhap.getManhacungcap() == mancc)
                    && (phieuNhap.getThoigiantao().compareTo(timebd) >= 0)
                    && (phieuNhap.getThoigiantao().compareTo(timekt) <= 0)
                    && phieuNhap.getTongTien() >= price_min
                    && phieuNhap.getTongTien() <= price_max) {
                   // JOptionPane.showMessageDialog(null, "PhieuNhap: "+result);
                result.add(phieuNhap);
            }

        }
        return result;
    }

    public ArrayList<DTO_PhieuNhap> fillerPhieuNhapTheoTenLoai(int type, String input, int mancc, int manv) {
        ArrayList<DTO_PhieuNhap> result = new ArrayList<>();
        for (DTO_PhieuNhap phieuNhap : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)
                            || nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                      
                        match = true;
                    }
                }
            }

            if (match && (manv == 0 || phieuNhap.getManguoitao() == manv) && (mancc == 0 || phieuNhap.getManhacungcap() == mancc)) {
                result.add(phieuNhap);
            }
        }
        // JOptionPane.showMessageDialog(null, "PhieuNhap: "+result);
        return result;


    }

    public ArrayList<DTO_ChiTietPhieuNhap> getAllChiTietPhieuNhap(int maphienbansanpham) {
        return ctPhieuNhapDAO.getAllChiTietPhieuNhapByMaPhieinBanSanPham(maphienbansanpham);
    }
    public ArrayList<DTO_PhieuNhap> fillerPhieuNhap(int type, String input, int mancc, int manv, Date time_s,
            Date time_e, String price_minnn, String price_maxxx) {
        int price_min = 0;
        int price_max = Integer.MAX_VALUE;
        if (!price_minnn.equals("")) {
            price_min = Integer.parseInt(price_minnn);
        }
        if (!price_maxxx.equals("")) {
            price_max = Integer.parseInt(price_maxxx);
        }

        // Timestamp time_start = new Timestamp(time_s.getTime());
        Calendar calendar = Calendar.getInstance();
        // calendar.setTimeInMillis(time_e.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        // Timestamp time_end = new Timestamp(calendar.getTimeInMillis());
        ArrayList<DTO_PhieuNhap> result = new ArrayList<>();
        for (DTO_PhieuNhap phieuNhap : getAll()) {
            // JOptionPane.showMessageDialog(null, "PhieuNhap: "+phieuNhap);
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)
                            || nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
               
            }

            if (match
                    && (manv == 0 || phieuNhap.getManguoitao() == manv) && (mancc == 0 || phieuNhap.getManhacungcap() == mancc)
                    // && (phieuNhap.getThoigiantao().compareTo(time_start) >= 0)
                    // && (phieuNhap.getThoigiantao().compareTo(time_end) <= 0)
                    && phieuNhap.getTongTien() >= price_min
                    && phieuNhap.getTongTien() <= price_max) {
                result.add(phieuNhap);
            }
        }

        return result;
    }
}
