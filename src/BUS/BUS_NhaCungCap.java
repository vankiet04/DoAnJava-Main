/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.DTO_NhaCungCap;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.DAO_NhaCungCap;

/**
 *
 * @author KIET
 */
public class BUS_NhaCungCap {
    public DAO_NhaCungCap ncc = new DAO_NhaCungCap();
    ArrayList<DTO_NhaCungCap> listNCC = new ArrayList<DTO_NhaCungCap>();

    public BUS_NhaCungCap() {
        listNCC = ncc.getAllData();
    }

    public ArrayList<DTO_NhaCungCap> getAllData() {
        return ncc.getAllData();
    }
    
    public void insert(DTO_NhaCungCap t) {
        ncc.insert(t);
    }

    public void update(DTO_NhaCungCap t) {
        ncc.update(t);
    }

    public void delete(int id) {
        ncc.delete(id);
    }

    public int getMaNhaCungCap(String tenncc) {
        int i = 0;
        int mancc = -1;
        while (i < this.listNCC.size() && mancc == -1) {
            if (listNCC.get(i).getTenncc().equals(tenncc)) {
                mancc = listNCC.get(i).getMancc();
            } else {
                i++;
            }
        }
        return mancc;
    }

    public String getTenNhaCungCap(int mancc) {
        return listNCC.get(getIndexByMaNCC(mancc)).getTenncc();
    }

    public int getIndexByMaNCC(int mancc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNCC.size() && vitri == -1) {
            if (listNCC.get(i).getMancc() == mancc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    //getByIndex
    public DTO_NhaCungCap getByIndex(int index) {
        return listNCC.get(index);
    }

    public ArrayList<DTO_NhaCungCap> search(String tenncc) {
        ArrayList<DTO_NhaCungCap> result = new ArrayList<>();
        for (DTO_NhaCungCap x : listNCC) {
            if (x.getTenncc().toLowerCase().contains(tenncc.toLowerCase()) || String.valueOf(x.getMancc()).contains(tenncc)) {
                result.add(x);
            }
        }
        return result;
    }
    
    public String getSoLuongNhaCungCap() {
        return String.valueOf(listNCC.size());
    }
}
