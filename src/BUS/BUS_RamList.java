/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_Product;
import DAO.DAO_RamList;
import DTO.DTO_Product;
import DTO.DTO_RamList;
import java.util.ArrayList;

/**
 *
 * @author Kiet
 */
public class BUS_RamList {
    public DAO_RamList ram = new DAO_RamList();
    private ArrayList<DTO_RamList> listRAM = new ArrayList<>();


    public BUS_RamList() {
        listRAM = ram.getAllData();
    }
    
    public ArrayList<DTO_RamList> getAllData() {
        return this.listRAM;
    }

    public int getMaxIDRAM() {
        int max = 0;
        for (DTO_RamList dto : listRAM) {
            if (dto.getMaram() > max) {
                max = dto.getMaram();
            }
        }
        return max;
    }

    public int insert(DTO_RamList t) {
        int result = ram.insert(t);
        if (result == 1) {
            listRAM.add(t);
        }
        return result;
    }

    public int getDungLuongRam(int id) {
        for (DTO_RamList dto : listRAM) {
            if (dto.getMaram() == id) {
                return Integer.parseInt(dto.getKichThuocRam());
            }
        }   
        return -1;
    }

    public int update(DTO_RamList t) {
        int result = ram.update(t);
        if (result == 1) {
            for (DTO_RamList dto : listRAM) {
                if (dto.getMaram() == t.getMaram()) {
                    dto.setKichThuocRam(t.getKichThuocRam());
                }
            }
        }
        return result;
    }

    public int delete(int id) {
        int result = ram.delete(id);
        if (result == 1) {
            for (DTO_RamList dto : listRAM) {
                if (dto.getMaram() == id) {
                    listRAM.remove(dto);
                    break;
                }
            }
        }
        return result;
    }

    public boolean checkRAM(String kichthuoc) {
        for (DTO_RamList dto : listRAM) {
            if (dto.getKichThuocRam().equals(kichthuoc)) {
                return true;
            }
        }
        return false;
    }
}
