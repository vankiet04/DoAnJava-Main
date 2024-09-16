package BUS;

import java.util.ArrayList;

import DAO.DAO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietPhieuNhap;

public class BUS_ChiTietPhieuNhap {
    DAO_ChiTietPhieuNhap dao = new DAO_ChiTietPhieuNhap();

    public BUS_ChiTietPhieuNhap() {
    }

    ArrayList<DTO_ChiTietPhieuNhap> laytatcactphieunhap(int mapbsp) {
        return dao.laytatcactphieunhap(mapbsp);
    }
}
