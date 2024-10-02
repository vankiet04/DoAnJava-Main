package DTO;

import java.sql.Timestamp;

public class DTO_PhieuXuat {
    int maphieuxuat;
    Timestamp thoigian;
    long tongtien;
    int idnhanvien;
    int idkhachhang;
    
    public DTO_PhieuXuat() {
    }

    public DTO_PhieuXuat(int maphieuxuat, Timestamp thoigian, long tongtien, int idnhanvien, int idkhachhang) {
        this.maphieuxuat = maphieuxuat;
        this.thoigian = thoigian;
        this.tongtien = tongtien;
        this.idnhanvien = idnhanvien;
        this.idkhachhang = idkhachhang;
    }

    // getter setter
    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public Timestamp getThoigian() {
        return thoigian;
    }

    public void setThoigian(Timestamp thoigian) {
        this.thoigian = thoigian;
    }

    public long getTongtien() { 
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }



}
