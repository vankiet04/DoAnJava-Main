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
public class DTO_PhieuNhap extends DTO_Phieu {
         int manhacungcap;
        // ??????

    public DTO_PhieuNhap() {
    }

    public DTO_PhieuNhap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public DTO_PhieuNhap(int maphieu, Timestamp thoigiantao, int manhacungcap, int manguoitao, long tongTien,
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
        final DTO_PhieuNhap other = (DTO_PhieuNhap) obj;
        return this.manhacungcap == other.manhacungcap;
    }

    @Override
    public String toString() {
        return "DTO_PhieuNhap{" + "manhacungcap=" + manhacungcap + '}';
    }

    
}
