/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_NhanVien;
import DTO.DTO_NhanVien;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author KIET
 */
public class BUS_NhanVien {
    public DAO_NhanVien nv = new DAO_NhanVien();
    public ArrayList<DTO_NhanVien> listNhanVien = new ArrayList<>();

    public BUS_NhanVien() {
        listNhanVien = nv.getAllData();
    }
     public ArrayList<DTO_NhanVien> getAllData() {
        return nv.getAllData();
    }
    
    public void insert(DTO_NhanVien t) {
        nv.insert(t);
    }

    public void update(DTO_NhanVien t) {
        nv.update(t);
    }

    public void delete(int id) {
        nv.delete(id);
    }
    
    public ArrayList<DTO_NhanVien> search(String name) {
        ArrayList<DTO_NhanVien> result = new ArrayList<>();
        for (DTO_NhanVien x : listNhanVien) {
            if (x.getHoten().toLowerCase().contains(name.toLowerCase()) || String.valueOf(x.getManv()).contains(name)) {
                result.add(x);
            }
        }
        return result;
    }

    public String getNameById(int manv) {
        //JOptionPane.showMessageDialog(null, "getNameById: "+this.listNhanVien.get(manv).getHoten());
        return nv.selectById(manv + "").getHoten();
    }

    public int getIdByName(String name) {
        int i = 0;
        int manv = -1;
        while (i < this.listNhanVien.size() && manv == -1) {
            if (listNhanVien.get(i).getHoten().equals(name)) {
                manv = listNhanVien.get(i).getManv();
            } else {
                i++;
            }
        }
        return manv;
    }
    // getByIndex
    public DTO_NhanVien getByIndex(int index) {
        return listNhanVien.get(index);
    }

    public ArrayList<DTO_NhanVien> getNhanVienchuaCoTK() {
        return nv.getNhanVienchuaCoTaiKhoan();
    }

    public DTO_NhanVien getNhanVien(int manv) {
        return nv.getNhanVien(manv);
    }

    public DTO_NhanVien getNvtheoid(int id) {
        ArrayList<DTO_NhanVien> list = nv.getAllData();
        DTO_NhanVien dto = new DTO_NhanVien();
        for (DTO_NhanVien x : list) {
            if (x.getManv() == id) {
                dto = x;
            }
        }
        return dto;
    }

    public String getSoLuongNhanVien() {
        return String.valueOf(listNhanVien.size());
    }

}
