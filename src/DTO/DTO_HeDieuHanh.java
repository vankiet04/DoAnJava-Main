/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_HeDieuHanh {
    private int mahdh;
    private String tenhdh;
    
    public DTO_HeDieuHanh(int mahdh, String tenhdh) {
        this.mahdh = mahdh;
        this.tenhdh = tenhdh;
    }

    // Getter và setter cho mahdh
    public int getMahdh() {
        return mahdh;
    }

    public void setMahdh(int mahdh) {
        this.mahdh = mahdh;
    }

    // Getter và setter cho tenhdh
    public String getTenhdh() {
        return tenhdh;
    }

    public void setTenhdh(String tenhdh) {
        this.tenhdh = tenhdh;
    }

}
