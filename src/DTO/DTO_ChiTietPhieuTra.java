/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author KIET
 */
public class DTO_ChiTietPhieuTra {
    private int maphieu;
    private int maphienbansp;
    private long soluong;
    private long tonggia;

    public DTO_ChiTietPhieuTra() {
    }
    public DTO_ChiTietPhieuTra(int maphieu, int maphienbansp, int soluong, long tonggia) {
        this.maphieu = maphieu;
        this.maphienbansp = maphienbansp;
        this.soluong = soluong;
        this.tonggia = tonggia;
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

    public long gettonggia() {
        return tonggia;
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

    public void settonggia(long tonggia) {
        this.tonggia = tonggia;
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
        final DTO_ChiTietPhieuTra other = (DTO_ChiTietPhieuTra) obj;
        if (this.maphieu != other.maphieu) {
            return false;
        }
        if (this.maphienbansp != other.maphienbansp) {
            return false;
        }
        if (this.soluong != other.soluong) {
            return false;
        }
        return this.tonggia == other.tonggia;
    }

    @Override
    public String toString() {
        return "DTO_ChiTietPhieu{" + "maphieu=" + maphieu + ", maphienbansp=" + maphienbansp + ", soluong=" + soluong + ", tonggia=" + tonggia + '}';
    }
    
    
    
}
