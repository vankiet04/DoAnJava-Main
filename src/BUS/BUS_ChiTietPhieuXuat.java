package BUS;

import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DAO.DAO_ChiTietPhieuXuat;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietPhieuXuat;

public class BUS_ChiTietPhieuXuat {
    DAO_ChiTietPhieuXuat dao = new DAO_ChiTietPhieuXuat();
    public BUS_ChiTietPhieuXuat() {
    }

    public int insert(ArrayList<DTO_ChiTietPhieuXuat> ctpx) {
        return dao.insert(ctpx);
    }

    public ArrayList<DTO_ChiTietPhieuXuat> getPhieuXuatTheoID(int id) {
        return dao.getPhieuXuatTheoID(id);
    }

    public ArrayList<DTO_ChiTietPhieuXuat> getCtPhieuXuatbyID(int id) {
        return dao.getCtPhieuXuatbyID(id);
    }
    
    public ArrayList<DTO_ChiTietPhieuXuat> getCtPhieuXuatbyMaphienban(int mapb) {
        return dao.getCtPhieuXuatbyMaphienban(mapb);
    }

      public int updatesoluongtonkhihuy(ArrayList<DTO_ChiTietPhieuXuat> t) {
        return dao.updatesoluongtonkhihuy(t);
    } 
}
