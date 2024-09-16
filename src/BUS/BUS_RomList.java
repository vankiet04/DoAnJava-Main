/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_RamList;
import DAO.DAO_RomList;
import DTO.DTO_RamList;
import DTO.DTO_RomList;
import java.util.ArrayList;

/**
 *
 * @author Kiet
 */
public class BUS_RomList {
    public DAO_RomList rom = new DAO_RomList();
    private ArrayList<DTO_RomList> listRom = new ArrayList<>();
    
    public BUS_RomList() {
        listRom = rom.getAllData();
    }
    
    public ArrayList<DTO_RomList> getAllData() {
        return this.listRom;
    }

    public int delete(int id) {
        int result = rom.delete(id);
        if (result == 1) {
            for (DTO_RomList dto : listRom) {
                if (dto.getMarom() == id) {
                    listRom.remove(dto);
                    break;
                }
            }
        }
        return result;
    }

    public int insert(DTO_RomList t) {
        int result = rom.insert(t);
        if (result == 1) {
            listRom.add(t);
        }
        return result;
    }

    public int getMaxIDROM() {
        int max = 0;
        for (DTO_RomList dto : listRom) {
            if (dto.getMarom() > max) {
                max = dto.getMarom();
            }
        }
        return max;
    }

    public int update(DTO_RomList t) {
        int result = rom.update(t);
        if (result == 1) {
            for (DTO_RomList dto : listRom) {
                if (dto.getMarom() == t.getMarom()) {
                    dto.setKichThuocRom(t.getKichThuocRom());
                    break;
                }
            }
        }
        return result;
    }

    public int getDungLuongROM(int id) {
        for (DTO_RomList dto : listRom) {
            if (dto.getMarom() == id) {
                return Integer.parseInt(dto.getKichThuocRom());
            }
        }
        return 0;
    }

    public boolean checkROM(String kichthuoc) {
        for (DTO_RomList dto : listRom) {
            if (dto.getKichThuocRom().equals(kichthuoc)) {
                return true;
            }
        }
        return false;
    }
}
