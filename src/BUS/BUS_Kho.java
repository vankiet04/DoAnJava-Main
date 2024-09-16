/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_Kho;
import DTO.DTO_Kho;
import DTO.DTO_Product;
import java.util.ArrayList;

/**
 *
 * @author Kiet
 */


public class BUS_Kho {
    public DAO_Kho kho = new DAO_Kho();

    public ArrayList<DTO_Kho> getAllData() {
        return kho.getAllData();
    }
}
