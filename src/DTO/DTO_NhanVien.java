/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author KIET
 */
public class DTO_NhanVien {
    private int manv;
    private String hoten;
    private int gioitinh;
    private Date ngaysinh;
    private String sdt;
    private String email;
    private int trangthai;

    public DTO_NhanVien(int manv, String hoten, int gioitinh, Date ngaysinh, String sdt, String email, int trangthai) {
        this.manv = manv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.email = email;
        this.trangthai = trangthai;
    }

    public DTO_NhanVien() {
    }

    public int getManv() {
        return manv;
    }

    public String getHoten() {
        return hoten;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public String getEmail() {
        return email;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.manv;
        hash = 71 * hash + Objects.hashCode(this.hoten);
        hash = 71 * hash + this.gioitinh;
        hash = 71 * hash + Objects.hashCode(this.sdt);
        hash = 71 * hash + Objects.hashCode(this.ngaysinh);
        hash = 71 * hash + this.trangthai;
        hash = 71 * hash + Objects.hashCode(this.email);
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
        final DTO_NhanVien other = (DTO_NhanVien) obj;
        if (this.manv != other.manv) {
            return false;
        }
        if (this.gioitinh != other.gioitinh) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.hoten, other.hoten)) {
            return false;
        }
        if (!Objects.equals(this.sdt, other.sdt)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.ngaysinh, other.ngaysinh);
    }

    @Override
    public String toString() {
        return "DTO_NhanVien{" + "manv=" + manv + ", hoten=" + hoten + ", gioitinh=" + gioitinh + ", sdt=" + sdt + ", ngaysinh=" + ngaysinh + ", trangthai=" + trangthai + ", email=" + email + '}';
    }
        
    
}
