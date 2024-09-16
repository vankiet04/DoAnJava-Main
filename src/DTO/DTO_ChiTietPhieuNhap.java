/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author KIET
 */
public class DTO_ChiTietPhieuNhap {
    private int maphieu;
    private int maphienbansp;
    private long soluong;
    private long dongia;

    public DTO_ChiTietPhieuNhap() {
    }
    public DTO_ChiTietPhieuNhap(int maphieu, int maphienbansp, int soluong, long dongia) {
        this.maphieu = maphieu;
        this.maphienbansp = maphienbansp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public long getSoluong() {
        return soluong;
    }

    public long getDongia() {
        return dongia;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public void setSoluong(long soluong) {
        this.soluong = soluong;
    }

    public void setDongia(long dongia) {
        this.dongia = dongia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DTO_ChiTietPhieuNhap other = (DTO_ChiTietPhieuNhap) obj;
        if (this.maphieu != other.maphieu) {
            return false;
        }
        if (this.maphienbansp != other.maphienbansp) {
            return false;
        }
        if (this.soluong != other.soluong) {
            return false;
        }
        return this.dongia == other.dongia;
    }

    @Override
    public String toString() {
        return "DTO_ChiTietPhieu{" + "maphieu=" + maphieu + ", maphienbansp=" + maphienbansp + ", soluong=" + soluong + ", dongia=" + dongia + '}';
    }
    
    
    
}
