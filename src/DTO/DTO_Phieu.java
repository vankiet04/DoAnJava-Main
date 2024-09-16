/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.*;
import java.util.Objects;

/**
 *
 * @author KIET
 */
public class DTO_Phieu {
     public int maphieu;
     public Timestamp thoigiantao;
     public int manguoitao;
     public long tongTien;
     public int trangthai;

    public DTO_Phieu() {
    }

    public DTO_Phieu(int maphieu, Timestamp thoigiantao, int manguoitao, long tongTien, int trangthai) {
        this.maphieu = maphieu;
        this.thoigiantao = thoigiantao;
        this.manguoitao = manguoitao;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
    }

   
    public int getMaphieu() {
        return maphieu;
    }

    public int getManguoitao() {
        return manguoitao;
    }

    public Timestamp getThoigiantao() {
        return thoigiantao;
    }

    public long getTongTien() {
        return tongTien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public void setManguoitao(int manguoitao) {
        this.manguoitao = manguoitao;
    }

    public void setThoigiantao(Timestamp thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.maphieu;
        hash = 43 * hash + this.manguoitao;
        hash = 43 * hash + Objects.hashCode(this.thoigiantao);
        hash = 43 * hash + (int) (this.tongTien ^ (this.tongTien >>> 32));
        hash = 43 * hash + this.trangthai;
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
        final DTO_Phieu other = (DTO_Phieu) obj;
        if (this.maphieu != other.maphieu) {
            return false;
        }
        if (this.manguoitao != other.manguoitao) {
            return false;
        }
        if (this.tongTien != other.tongTien) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        return Objects.equals(this.thoigiantao, other.thoigiantao);
    }

    @Override
    public String toString() {
        return "DTO_Phieu{" + "maphieu=" + maphieu + ", manguoitao=" + manguoitao + ", thoigiantao=" + thoigiantao + ", tongTien=" + tongTien + ", trangthai=" + trangthai + '}';
    }
    
    
}
