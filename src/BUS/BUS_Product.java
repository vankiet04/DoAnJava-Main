/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_Product;
import DTO.DTO_Product;
import java.util.ArrayList;

/**
 *
 * @author Kiet
 */
public class BUS_Product {
    public DAO_Product product = new DAO_Product();
    

    public BUS_Product() {

    }
    
    public ArrayList<DTO_Product> getAllData() {
        return product.getAllData();
    }
    
    public void insert(DTO_Product t) {
        product.insert(t);
    }

    public void update(DTO_Product t) {
        product.update(t);
    }

    public void delete(int id) {
        product.delete(id);
    }
    
    public int getmaloainew() {
        return product.getMaxRow();
    }
    
    public DTO_Product getProductByID(int id) {
        return product.getProductByID(id);
    }

    public ArrayList<DTO_Product> search(String key) {
        return product.search(key);
    }

     
    public DTO_Product getsanphamtheomaphienban(int mapb) {
        return product.getsanphamtheomaphienban(mapb);
    }
}
