package DTO;

public class DTO_HuyPhieuXuat {
    int mahd;
    int manv;
    String lydo;
    String ngayhuy;

    public DTO_HuyPhieuXuat(int mahd, int manv, String lydo, String ngayhuy) {
        this.mahd = mahd;
        this.manv = manv;
        this.lydo = lydo;
        this.ngayhuy = ngayhuy;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }


    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getNgayhuy() {
        return ngayhuy;
    }

    public void setNgayhuy(String ngayhuy) {
        this.ngayhuy = ngayhuy;
    }
    
}
