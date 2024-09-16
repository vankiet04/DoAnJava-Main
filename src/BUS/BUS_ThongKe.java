package BUS;

import java.util.ArrayList;

import DAO.DAO_ThongKe;
import DTO.DTO_ThongKeThang;
import DTO.DTO_ThongKeTungNgayTrongThang;

public class BUS_ThongKe {
    DAO_ThongKe thongkeDAO = new DAO_ThongKe();

    public BUS_ThongKe() {
    }

    public ArrayList<DTO_ThongKeThang> getThongKeTheoThang(int nam){
        return thongkeDAO.getThongKeTheoThang(nam);
    }

    public ArrayList<DTO_ThongKeTungNgayTrongThang> getThongKeTungNgayTrongThang(int nam, int thang){
        return thongkeDAO.getThongKeTungNgayTrongThang(nam, thang);
    }
}
