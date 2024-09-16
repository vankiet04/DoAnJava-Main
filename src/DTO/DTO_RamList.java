/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_RamList {
    private int maram;
    private String KichThuocRam;
    
    public DTO_RamList(int maram, String KichThuocRam) {
        this.maram = maram;
        this.KichThuocRam = KichThuocRam;
    }

    // Getter cho maram
    public int getMaram() {
        return maram;
    }

    // Setter cho maram
    public void setMaram(int maram) {
        this.maram = maram;
    }

    // Getter cho tenram
    public String getKichThuocRam() {
        return KichThuocRam;
    }

    // Setter cho tenram
    public void setKichThuocRam(String KichThuocRam) {
        this.KichThuocRam = KichThuocRam;
    }
}
