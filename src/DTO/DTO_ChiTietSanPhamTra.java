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
public class DTO_ChiTietSanPhamTra {
     private String imei;
    private int maphienbansp;
    private int maphieutra;
    private int tinhtrang;

    public DTO_ChiTietSanPhamTra(String imei, int maphienbansp, int maphieutra,  int tinhtrang) {
        this.imei = imei;
        this.maphienbansp = maphienbansp;
        this.maphieutra = maphieutra;
  
        this.tinhtrang = tinhtrang;
    }

    public DTO_ChiTietSanPhamTra() {
        //TODO Auto-generated constructor stub
    }

    public String getImei() {
        return imei;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public int getmaphieutra() {
        return maphieutra;
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

    public void setmaphieutra(int maphieutra) {
        this.maphieutra = maphieutra;
    }


    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.imei);
        hash = 67 * hash + this.maphienbansp;
        hash = 67 * hash + this.maphieutra;

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
        final DTO_ChiTietSanPhamTra other = (DTO_ChiTietSanPhamTra) obj;
        return true;
    }
    

    
    
}
