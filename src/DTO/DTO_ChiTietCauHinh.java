/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_ChiTietCauHinh {
    private int maphienbansp;
    private int masanpham;
    private int rom;
    private int ram;
    private int gianhap;
    private int giaxuat;
    private int soluongton;
    // Constructor không tham số
    public DTO_ChiTietCauHinh() {
        // Khởi tạo các giá trị mặc định (nếu có)
    }

    // Constructor có tham số
    public DTO_ChiTietCauHinh(int maphienbansp, int masanpham, int rom, int ram, int gianhap, int giaxuat, int soluongton) {
        this.maphienbansp = maphienbansp;
        this.masanpham = masanpham;
        this.rom = rom;
        this.ram = ram;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.soluongton = soluongton;
    }

    // Getter và setter cho maphienbansp
    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    // Getter và setter cho masanpham
    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    // Getter và setter cho rom
    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    // Getter và setter cho ram
    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    // Getter và setter cho gianhap
    public int getGianhap() {
        return gianhap;
    }

    public String getGianhapVND() {
        return String.format("%,d", gianhap);
    }

    public String getGiaxuatVND() {
        return String.format("%,d", giaxuat);
    }
    
    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    // Getter và setter cho giaxuat
    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    // Getter và setter cho soluongton
    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

}
