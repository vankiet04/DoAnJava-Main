/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_Brand;
import DAO.DAO_Product;
import DTO.DTO_Brand;
import DTO.DTO_Product;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Kiet
 */
public class BUS_Brand {
    public DAO_Brand brand = new DAO_Brand();
    private ArrayList<DTO_Brand> listBrand = new ArrayList<>();
    
    public BUS_Brand() {
        listBrand = brand.getAllData();
    }
    
    public ArrayList<DTO_Brand> getAllData() {
        return this.listBrand;
    }

    public int addBrand(DTO_Brand t) {
        int result = brand.insert(t);
        if (result == 1) {
            listBrand.add(t);
        }
        return result;
    }  

    public boolean checkBrandName(String brand) {
        for (DTO_Brand dto : listBrand) {
            if (dto.getBrandName().equals(brand)) {
                return true;
            }
        }
        return false;
    }

    public  ArrayList<DTO_Brand> editbrand(String tenth, String newten) {
        int result = brand.updatez(tenth, newten);
        ArrayList<DTO_Brand> list = brand.getAllData();

        if (result == 1) {
            for (DTO_Brand dto : listBrand) {
                if (dto.getBrandName() == tenth) {
                    dto.setBrandName(newten);
                }
            }
        }
        return list;
    }

   public int deleteBrand(String tenth) {
     
       int result = brand.deleteTH(tenth);
       if (result == 1) {
           for (DTO_Brand dto : listBrand) {
               if (dto.getBrandName() == tenth) {
                   listBrand.remove(dto);
                   break;
               }
           }
       }
       return result;
   }
}
