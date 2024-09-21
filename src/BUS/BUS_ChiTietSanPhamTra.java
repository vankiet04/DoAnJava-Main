package BUS;

import java.util.ArrayList;

import DAO.DAO_ChiTietSanPhamTra;
import DTO.DTO_ChiTietSanPhamTra;

public class BUS_ChiTietSanPhamTra {
    DAO_ChiTietSanPhamTra daoCtspTra = new DAO_ChiTietSanPhamTra();
    DTO_ChiTietSanPhamTra dtoCtspTra = new DTO_ChiTietSanPhamTra();


    public BUS_ChiTietSanPhamTra() {
    }

    public int themChiTietSanPhamTra(DTO_ChiTietSanPhamTra dtoCtspTra, int maphieutra) {
        return daoCtspTra.themChiTietSanPhamTra(dtoCtspTra, maphieutra);
    }

    public ArrayList<DTO_ChiTietSanPhamTra> getAll() {
        return daoCtspTra.getAll();
    }
    
    public ArrayList<DTO_ChiTietSanPhamTra> getAllImeiByMaphienbanVaListMaphieunhap(int maphienban, int maPhieuTra) {
        return daoCtspTra.getAllImeiByMaphienbanVaListMaphieunhap(maphienban, maPhieuTra);
    }
}
