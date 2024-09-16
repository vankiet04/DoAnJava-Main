/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_Kho {
    private int makho;
    private String tenkho;
    
    public DTO_Kho(int makho, String tenkho) {
        this.makho = makho;
        this.tenkho = tenkho;
    }

    // Getter cho makho
    public int getMakho() {
        return makho;
    }

    // Setter cho makho
    public void setMakho(int makho) {
        this.makho = makho;
    }

    // Getter cho tenkho
    public String getTenkho() {
        return tenkho;
    }

    // Setter cho tenkho
    public void setTenkho(String tenkho) {
        this.tenkho = tenkho;
    }
}
