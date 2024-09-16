package DTO;

import java.sql.Date;

public class DTO_ThongKeTungNgayTrongThang {
    // new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
    private Date ngay;
    private long chiphi;
    private long doanhthu;
    private long loinhuan;

    public DTO_ThongKeTungNgayTrongThang() {
    }

    public DTO_ThongKeTungNgayTrongThang(Date ngay, long chiphi, long doanhthu, long loinhuan) {
        this.ngay = ngay;
        this.chiphi = chiphi;
        this.doanhthu = doanhthu;
        this.loinhuan = loinhuan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public long getChiphi() {
        return chiphi;
    }

    public void setChiphi(long chiphi) {
        this.chiphi = chiphi;
    }

    public long getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(long doanhthu) {
        this.doanhthu = doanhthu;
    }

    public long getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(long loinhuan) {
        this.loinhuan = loinhuan;
    }
}
