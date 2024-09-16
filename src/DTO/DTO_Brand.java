/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_Brand {
    private String namebrand;
    
    public DTO_Brand(String namebrand) {
        this.namebrand = namebrand;
    }
    
    public String getBrandName() {
        return namebrand;
    }

    public void setBrandName(String namebrand) {
        this.namebrand = namebrand;
    }
    
}
