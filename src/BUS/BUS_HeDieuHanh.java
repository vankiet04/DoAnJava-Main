/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_HeDieuHanh;
import DTO.DTO_HeDieuHanh;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Kiet
 */
public class BUS_HeDieuHanh {
    public DAO_HeDieuHanh hdh = new DAO_HeDieuHanh();
    private ArrayList<DTO_HeDieuHanh> listHDH = new ArrayList<>();
    
    public BUS_HeDieuHanh() {
        listHDH = hdh.getAllData();
    }
    
    public ArrayList<DTO_HeDieuHanh> getAllData() {
        return this.listHDH;
    }

    public int getMaxIDHDH() {
        int maxhdh = 0;
       for (int i = 0; i < listHDH.size(); i++) {
           if (listHDH.get(i).getMahdh() > maxhdh) {
               maxhdh = listHDH.get(i).getMahdh();
           }
       }
       return maxhdh;
   }

   public int insert(DTO_HeDieuHanh t) {
       int result = hdh.insert(t);
       if (result == 1) {
           listHDH.add(t);
       }
       return result;
   }

   public DTO_HeDieuHanh getByID(int id) {
       for (DTO_HeDieuHanh dto : listHDH) {
           if (dto.getMahdh() == id) {
               return dto;
           }
       }
       return null;
   }

   public boolean checkBrandName(String name) {
       for (DTO_HeDieuHanh dto : listHDH) {
           if (dto.getTenhdh().equals(name)) {
               return true;
           }
       }
       return false;
   }

   public int editbrand(String tenth, String newten) {

       int result = hdh.update(tenth, newten);
       if (result == 1) {
           for (DTO_HeDieuHanh dto : listHDH) {
               if (dto.getTenhdh().equals(tenth)) {
                   dto.setTenhdh(newten);
               }
           }
       }
       return result;
   }
   
   public int delete(int t) {
       int result = hdh.delete(t);
       if (result == 1) {
           for (DTO_HeDieuHanh dto : listHDH) {
               if (dto.getMahdh() == t) {
                   listHDH.remove(dto);
                   break;
               }
           }
       }
       return result;
   }

   public int deleteTH(String th) {
       int result = hdh.deleteTH(th);
       if (result == 1) {
           for (DTO_HeDieuHanh dto : listHDH) {
               if (dto.getTenhdh().equals(th)) {
                   listHDH.remove(dto);
                   break;
               }
           }
       }
       return result;
   }
}
