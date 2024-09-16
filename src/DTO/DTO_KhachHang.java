/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class DTO_KhachHang {
    private String maKhachHang;
    private String HoTen;
    private String diaChi;
    private String soDienThoai;
    private String ngayThamGia;
    private String gioitinh;

    public DTO_KhachHang() {
    }

    public DTO_KhachHang(String maKhachHang, String HoTen, String diaChi, String soDienThoai, String ngayThamGia, String gioitinh) {
        this.maKhachHang = maKhachHang;
        this.HoTen = HoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.ngayThamGia = ngayThamGia;
        this.gioitinh = gioitinh;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getHoTen() {
        return HoTen;
    }


    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getNgayThamGia() {
        return ngayThamGia;
    }
    
    public String getgioitinh() {
        return gioitinh;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }


    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
}
