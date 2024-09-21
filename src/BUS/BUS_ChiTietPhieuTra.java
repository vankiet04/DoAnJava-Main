package BUS;

import java.lang.reflect.Array;
import java.util.ArrayList;

import DAO.DAO_ChiTietPhieuTra;
import DTO.DTO_ChiTietPhieuTra;
import DTO.DTO_ChiTietSanPhamTra;

public class BUS_ChiTietPhieuTra {
    DAO_ChiTietPhieuTra daoCtphieutra = new DAO_ChiTietPhieuTra();
    DTO_ChiTietPhieuTra dtoCtphieutra = new DTO_ChiTietPhieuTra();

    public BUS_ChiTietPhieuTra() {
    }

    public int themChiTietPhieuTra(ArrayList<DTO_ChiTietPhieuTra> dtoCtphieutra) {
        return daoCtphieutra.insert(dtoCtphieutra);
    }
    // public ArrayList<DTO_ChiTietPhieuTra> getAll() {
    //     return daoCtphieutra.getAll();
    // }

    public ArrayList<DTO_ChiTietPhieuTra> getAllByMaPhieuTra(int maPhieuTra) {
        return daoCtphieutra.getAllByMaPhieuTra(maPhieuTra);
    }

    
}
