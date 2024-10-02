package BUS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DAO.DAO_ChiTietPhieuNhap;
import DAO.DAO_ChiTietSanPham;
import DAO.DAO_PhieuNhap;
import DAO.DAO_PhieuXuat;
import DTO.DTO_Phieu;
import DTO.DTO_PhieuNhap;
import DTO.DTO_PhieuXuat;

public class BUS_PhieuXuat {
    DAO_PhieuXuat phieuxuat = new DAO_PhieuXuat();
    
    
   public final DAO_ChiTietPhieuNhap ctPhieuNhapDAO = new DAO_ChiTietPhieuNhap();
   public final DAO_ChiTietSanPham chitietsanphamDAO = new DAO_ChiTietSanPham();
   ArrayList<DTO_PhieuXuat> listPhieuXuat;
   BUS_KhachHang khBUS = new BUS_KhachHang();
   BUS_NhanVien nvBUS = new BUS_NhanVien();

    public BUS_PhieuXuat() {
        
    }

    public int getnewmaphieuxuat() {
        return phieuxuat.getAutoIncrement();
    }

    public int insert(DTO_PhieuXuat phieu) {
        return phieuxuat.insert(phieu);
    }

    public ArrayList<DTO_PhieuXuat> getAll() {
        return phieuxuat.getAllData();
    }
    

    public DTO_PhieuXuat getPhieuXuatTheoID(int id) {
        return phieuxuat.getPhieuXuatTheoID(id);
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
    
    public Timestamp convertStringToTimestamp(String str_date) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = dateFormat.parse(str_date);
        return new Timestamp(parsedDate.getTime());
    } catch(Exception e) { //this generic but you can control another types of exception
        // look the origin of excption 
        return null;
    }
}
    public ArrayList<DTO_PhieuXuat> fillerphieuXuatCoNgay(int type, String input, int makh, int manv, Date ngaybatdau, Date ngayketthuc, long price_min, long price_max) {
        ArrayList<DTO_PhieuXuat> result = new ArrayList<>();
        java.sql.Timestamp timebd = convertDateToTimestamp(ngaybatdau);
        java.sql.Timestamp timekt = convertDateToTimestamp(ngayketthuc);

        if (timebd == null || timekt == null) {
            // change timebd to 1-1-1970
            timebd = new java.sql.Timestamp(0);
            // timekt to now
            timekt = new java.sql.Timestamp(System.currentTimeMillis());
        }

        // JOptionPane.showMessageDialog(null, "okkee");
        for (DTO_PhieuXuat phieuXuat : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuXuat.getMaphieuxuat()).contains(input.toLowerCase())
                            || nvBUS.getNameById(phieuXuat.getIdnhanvien()).toLowerCase().contains(input.toLowerCase())
                            || khBUS.getKHbyid(Integer.toString(phieuXuat.getIdkhachhang()) + "").getHoTen().toLowerCase().contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuXuat.getMaphieuxuat()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if(khBUS.getKHbyid(Integer.toString(phieuXuat.getIdkhachhang())).getHoTen().toLowerCase().contains(input.toLowerCase())){
                        match = true;
                    }
                    
                    
                }
                case 3 -> {
                   if(nvBUS.getNameById(phieuXuat.getIdnhanvien()).toLowerCase().contains(input.toLowerCase())){
                       match = true;
                   }
                }
            }

               if (match && (manv == 0 || phieuXuat.getIdnhanvien() == manv)
                       && (phieuXuat.getThoigian().compareTo(timebd) >=0)
                       && (phieuXuat.getThoigian().compareTo(timekt) <=0)
                       && (makh == 0 || phieuXuat.getIdkhachhang() == makh)
                       && (price_min == 0 || phieuXuat.getTongtien() >= price_min)
                       && (price_max == 0 || phieuXuat.getTongtien() <= price_max))
                   
                result.add(phieuXuat);
            



                 
        }
        return result;


    }
     public ArrayList<DTO_PhieuXuat> fillerphieuXuatTheoTenLoai(int type, String input, int makh, int manv) {
        ArrayList<DTO_PhieuXuat> result = new ArrayList<>();
        for (DTO_PhieuXuat phieuXuat : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuXuat.getMaphieuxuat()).contains(input.toLowerCase())
                            // || phieuXuat.getThoigian().toLowerCase().contains(input.toLowerCase())
                            // || Double.toString(phieuXuat.getTongtien()).contains(input)
                            || nvBUS.getNameById(phieuXuat.getIdnhanvien()).toLowerCase().contains(input.toLowerCase())
                            || khBUS.getKHbyid(Integer.toString(phieuXuat.getIdkhachhang()) + "").getHoTen().toLowerCase().contains(input.toLowerCase())) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuXuat.getMaphieuxuat()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if(khBUS.getKHbyid(Integer.toString(phieuXuat.getIdkhachhang())).getHoTen().toLowerCase().contains(input.toLowerCase())){
                        match = true;
                    }
                    
                    
                }
                case 3 -> {
                   if(nvBUS.getNameById(phieuXuat.getIdnhanvien()).toLowerCase().contains(input.toLowerCase())){
                       match = true;
                   }
                }
            }

            if (match && (manv == 0 || phieuXuat.getIdnhanvien() == manv) && (makh == 0 || phieuXuat.getIdkhachhang() == makh)) {
                result.add(phieuXuat);
            }
        }
        // JOptionPane.showMessageDialog(null, "phieuXuat: "+result);
        return result;


    }

    public ArrayList<DTO_PhieuXuat> getPhieuXuatGanDay() {
        return phieuxuat.getPhieuXuatGanDay();

    }

}

