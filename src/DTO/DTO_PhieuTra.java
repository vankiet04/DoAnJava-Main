/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

/**
 *
 * @author KIET
 */
public class DTO_PhieuTra extends DTO_Phieu {
         int manhacungcap;
        // ??????

    public DTO_PhieuTra() {
    }

    public DTO_PhieuTra(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public DTO_PhieuTra(int maphieu, Timestamp thoigiantao, int manhacungcap, int manguoitao, long tongTien,
            int trangthai) {
    
        this.maphieu = maphieu;
        this.thoigiantao = thoigiantao;
        this.manhacungcap = manhacungcap;
        this.manguoitao = manguoitao;
        this.tongTien = tongTien;
        this.trangthai = trangthai;

    }

    

    public int getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }


    


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.manhacungcap;
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
        final DTO_PhieuTra other = (DTO_PhieuTra) obj;
        return this.manhacungcap == other.manhacungcap;
    }

    @Override
    public String toString() {
        return "DTO_PhieuTra{" + "manhacungcap=" + manhacungcap + '}';
    }

    
}
