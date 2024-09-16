/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author KIET
 */
public class DTO_ChiTietSanPham {
     private String imei;
    private int maphienbansp;
    private int maphieunhap;
    private int maphieuxuat;
    private int tinhtrang;

    public DTO_ChiTietSanPham(String imei, int maphienbansp, int maphieunhap, int maphieuxuat, int tinhtrang) {
        this.imei = imei;
        this.maphienbansp = maphienbansp;
        this.maphieunhap = maphieunhap;
        this.maphieuxuat = maphieuxuat;
        this.tinhtrang = tinhtrang;
    }

    public DTO_ChiTietSanPham() {
        //TODO Auto-generated constructor stub
    }

    public String getImei() {
        return imei;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.imei);
        hash = 67 * hash + this.maphienbansp;
        hash = 67 * hash + this.maphieunhap;
        hash = 67 * hash + this.maphieuxuat;
        hash = 67 * hash + this.tinhtrang;
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
        final DTO_ChiTietSanPham other = (DTO_ChiTietSanPham) obj;
        return true;
    }
    

    @Override
    public String toString() {
        return "DTO_ChiTietSanPham{" + "imei=" + imei + ", maphienbansp=" + maphienbansp + ", maphieunhap=" + maphieunhap + ", maphieuxuat=" + maphieuxuat + ", tinhtrang=" + tinhtrang + '}';
    }
    
    
}
