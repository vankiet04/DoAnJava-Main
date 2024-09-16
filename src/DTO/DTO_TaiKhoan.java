package DTO;

public class DTO_TaiKhoan {
    int manv;
    String tendangnhap;   
    String matkhau;
    int manhomquyen;
    int trangthai;

    public DTO_TaiKhoan() {
    }

    public DTO_TaiKhoan(int manv, String tendangnhap, String matkhau, int manhomquyen, int trangthai ) {
        this.manv = manv;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.manhomquyen = manhomquyen;
        this.trangthai = trangthai;
    }

    //getter setter
    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

}
