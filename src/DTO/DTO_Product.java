/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_Product {
    private int masanpham;
    private String tensanpham;
    private String boxuly;
    private String hedieuhanh;
    private String vitrikho;
    private String hinhsanpham;
    private String thoigianbaohanh;
    private int trangthai;
    private String thuonghieu;
    private int maLoai;
    private int soluongtong;
    
    public DTO_Product(int masanpham, String tensanpham, String boxuly, String hedieuhanh, String vitrikho, String hinhsanpham, String thoigianbaohanh, int trangthai, String thuonghieu, int maLoai) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.boxuly = boxuly;
        this.hedieuhanh = hedieuhanh;
        this.vitrikho = vitrikho;
        this.hinhsanpham = hinhsanpham;
        this.thoigianbaohanh = thoigianbaohanh;
        this.trangthai = trangthai;
        this.thuonghieu = thuonghieu;
        this.maLoai = maLoai;
    }

    public DTO_Product(int masanpham, String tensanpham, String boxuly, String hedieuhanh, String vitrikho, String hinhsanpham, String thoigianbaohanh, int trangthai, String thuonghieu, int maLoai, int soluongtong) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.boxuly = boxuly;
        this.hedieuhanh = hedieuhanh;
        this.vitrikho = vitrikho;
        this.hinhsanpham = hinhsanpham;
        this.thoigianbaohanh = thoigianbaohanh;
        this.trangthai = trangthai;
        this.thuonghieu = thuonghieu;
        this.maLoai = maLoai;
        this.soluongtong = soluongtong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public int getSoluongtong() {
        return soluongtong;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setSoluongtong(int soluongtong) {
        this.soluongtong = soluongtong;
    }
    
    
    public int getmaLoai() {
        return maLoai;
    }
    
    public void setmaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
    
    public String getThuonghieu() {
        return this.thuonghieu;
    }
    
    public void setthuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }
    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    // Getter và Setter cho tensanpham
    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    // Getter và Setter cho boxuly
    public String getBoxuly() {
        return boxuly;
    }

    public void setBoxuly(String boxuly) {
        this.boxuly = boxuly;
    }


    // Getter và Setter cho hedieuhanh
    public String getHedieuhanh() {
        return hedieuhanh;
    }

    public void setHedieuhanh(String hedieuhanh) {
        this.hedieuhanh = hedieuhanh;
    }

    // Getter và Setter cho vitrikho
    public String getVitrikho() {
        return vitrikho;
    }

    public void setVitrikho(String vitrikho) {
        this.vitrikho = vitrikho;
    }

    // Getter và Setter cho hinhsanpham
    public String getHinhsanpham() {
        return hinhsanpham;
    }

    public void setHinhsanpham(String hinhsanpham) {
        this.hinhsanpham = hinhsanpham;
    }

    // Getter và Setter cho thoigianbaohanh
    public String getThoigianbaohanh() {
        return thoigianbaohanh;
    }

    public void setThoigianbaohanh(String thoigianbaohanh) {
        this.thoigianbaohanh = thoigianbaohanh;
    }

    // Getter và Setter cho trangthai
    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    @Override
    public String toString() {
        return "DTO_Product:::::{" + "masanpham=" + masanpham + ", tensanpham=" + tensanpham + ", boxuly=" + boxuly + ", hedieuhanh=" + hedieuhanh + ", vitrikho=" + vitrikho + ", hinhsanpham=" + hinhsanpham + ", thoigianbaohanh=" + thoigianbaohanh + ", trangthai=" + trangthai + ", thuonghieu=" + thuonghieu + ", maLoai=" + maLoai + '}';
    }

}
