/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_RomList {
   private int marom;
    private String KichThuocRom;
    
    public DTO_RomList(int marom, String KichThuocRom) {
        this.marom = marom;
        this.KichThuocRom = KichThuocRom;
    }

    // Getter cho maram
    public int getMarom() {
        return marom;
    }

    // Setter cho maram
    public void setMarom(int marom) {
        this.marom = marom;
    }

    // Getter cho tenram
    public String getKichThuocRom() {
        return KichThuocRom;
    }

    // Setter cho tenram
    public void setKichThuocRom(String KichThuocRom) {
        this.KichThuocRom = KichThuocRom;
    } 
}
