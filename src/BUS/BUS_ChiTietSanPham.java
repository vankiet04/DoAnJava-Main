/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DAO.DAO_ChiTietSanPham;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietSanPham;

/**
 *
 * @author KIET
 */
public class BUS_ChiTietSanPham {
    private final DAO_ChiTietSanPham ctspDAO = new DAO_ChiTietSanPham();
    public BUS_ChiTietCauHinh pbspbus = new BUS_ChiTietCauHinh();
    public ArrayList<DTO_ChiTietCauHinh> listpbsp;
    public ArrayList<DTO_ChiTietSanPham> listctsp = new ArrayList<>();

    public BUS_ChiTietSanPham() {

    }
    public ArrayList<DTO_ChiTietSanPham> getAllByMaPBSP(int pbsp) {
        listctsp = ctspDAO.selectbyPb(pbsp);
        return listctsp;
    }

    public ArrayList<DTO_ChiTietSanPham> getAll() {
        return this.listctsp;
    }

    public DTO_ChiTietSanPham getByIndex(int index) {
        return this.listctsp.get(index);
    }

    public ArrayList<DTO_ChiTietSanPham> getAllByMaPhieuNhap(int maphieunhap) {
        return ctspDAO.selectbyPhieuNhap(maphieunhap);
    }
    
    public ArrayList<DTO_ChiTietSanPham> getAllByMaPhieuNhapAndMaPBSP(int mapn, int mapbsp) {
        return ctspDAO.getAllByMaPhieuNhapAndMaPBSP( mapn,  mapbsp);
    }
    
    // public ArrayList<ChiTietPhieuDTO> getChiTietPhieu_Type(int maphieunhap) {
    //     ArrayList<ChiTietPhieuNhapDTO> arr = ctPhieuNhapDAO.selectAll(Integer.toString(maphieunhap));
    //     ArrayList<ChiTietPhieuDTO> result = new ArrayList<>();
    //     for (ChiTietPhieuDTO i : arr) {
    //         result.add(i);
    //     }
    //     return result;

    public ArrayList<String> getImeiTheoMaPhienban(int mapb) {
        return ctspDAO.getImeiTheoMaPhienban(mapb);
    }

    public ArrayList<String> getImeiTheoMaPhieuXuat(int mapb, int mapx) {
        return ctspDAO.getImeiTheoMaPhieuXuat(mapb, mapx);
    }

    public int updateMaPhieuXuat(ArrayList<DTO_ChiTietCauHinh> list, int maphieuxuat, ArrayList<ArrayList<String>> danhsachimei) {
        // lấy ra toàn bộ các ctsanpham
        return ctspDAO.updateMaPhieuXuat(list, maphieuxuat, danhsachimei);
    }
    
    public ArrayList<DTO_ChiTietSanPham> getAllByMaPhieuXuat(int maphieuxuat) {
        return ctspDAO.getAllByMaPhieuXuat(maphieuxuat);
    }

    public int capNhatctspTinhTrangbang0(int maphieunhap) {
        return ctspDAO.capNhatctspTinhTrangbang0(maphieunhap);
    }

    public int setmaphieuxuatnull(int maphieuxuat) {
        return ctspDAO.setmaphieuxuatnull(maphieuxuat);
    }

    public int getsoluongphienbansanphamtontaitrongphieuxuat(int maphieunhap) {
        return ctspDAO.getsoluongphienbansanphamtontaitrongphieuxuat(maphieunhap);
    }

    public boolean checkmapbsp(int mapbsp) {
        return ctspDAO.checkmapbsp(mapbsp);
    }
    
    public ArrayList<String> getToanBoImeiTheoPhienBanSanPham(int mapb) {
        return ctspDAO.getToanBoImeiTheoPhienBanSanPham(mapb);
    }
}


