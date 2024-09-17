/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_ChiTietCauHinh;
import DTO.DTO_ChiTietCauHinh;
import java.util.ArrayList;


/**
 *
 * @author Kiet
 */
public class BUS_ChiTietCauHinh {
    public DAO_ChiTietCauHinh chitietproduct = new DAO_ChiTietCauHinh();
    private ArrayList<DTO_ChiTietCauHinh> listdetailProduct = new ArrayList<>();
    
    public BUS_ChiTietCauHinh() {

    }
    
    public void insert(DTO_ChiTietCauHinh t) {
        chitietproduct.insert(t);
    }
    
    public int getmacauhinhnew(int idsp) {
        return chitietproduct.getNextID(idsp);
    }

    public ArrayList<DTO_ChiTietCauHinh> getCauHinhbyID(int id) {
        return chitietproduct.getCauHinhbyID(id);
    }

    public ArrayList<DTO_ChiTietCauHinh> getAllChiTietCauHinh(String id) {
        return chitietproduct.selectAll(id);
    }

    public int getMaxID(int masanpham) {
        return chitietproduct.getMaxID(masanpham);
    }

    public DTO_ChiTietCauHinh getChiTietCauHinh(int maphienbansp) {
        return chitietproduct.selectById(maphienbansp);
    }

    public void delete(String t1, String t2) {
        chitietproduct.delete(t1, t2);
    }
    public DTO_ChiTietCauHinh getByMaPhienBan(int mapb) {
        return chitietproduct.selectById(mapb);
    }

    public ArrayList<DTO_ChiTietCauHinh> getAllData() {
        return chitietproduct.getAllData();
    }

    public int updateSoLuongTonPX(int maphienbansp, int soluong) {
        return chitietproduct.updateSoLuongTonPX(maphienbansp, soluong);
    }

    public DTO_ChiTietCauHinh getchitietcauhinh(int maphienbansp) {
        return chitietproduct.selectById(maphienbansp);
    }

    public int update(DTO_ChiTietCauHinh t) {
        return chitietproduct.updateMPBSB(t);
    }

    public int getMaxID() {
        return chitietproduct.getMaxID();
    }

    public int getSoLuong(int masp) {
        return chitietproduct.getSoLuong(masp);
    }

    public void truSoLuongPhienBanSanPham(int maphienbansp, int soluong) {
        chitietproduct.truSoLuongPhienBanSanPham(maphienbansp, soluong);
    }

    public boolean checkMaSP(int masp) {
        return chitietproduct.checkMaSP(masp);
    }
  
  
}
