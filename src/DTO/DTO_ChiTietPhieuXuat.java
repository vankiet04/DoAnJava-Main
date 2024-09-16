package DTO;

public class DTO_ChiTietPhieuXuat {
    int maphieunhap;
    int maphienbansp;
    int soluong;
    long dongia;

    public DTO_ChiTietPhieuXuat(int maphieunhap, int maphienbansp, int soluong, long dongia) {
        this.maphieunhap = maphieunhap;
        this.maphienbansp = maphienbansp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public DTO_ChiTietPhieuXuat() {
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getDongia() {
        return dongia;
    }

    public void setDongia(long dongia) {
        this.dongia = dongia;
    }

    
}
