package BUS;

import java.util.ArrayList;

import DAO.DAO_ThongKe;
import DTO.DTO_ThongKeThang;
import DTO.DTO_ThongKeTuNgayDenNgay;
import DTO.DTO_ThongKeTungNgayTrongThang;

public class BUS_ThongKeTuNgayDenNgay {
    DAO_ThongKe thongkeDAO = new DAO_ThongKe();

    public BUS_ThongKeTuNgayDenNgay() {
    }


    public ArrayList<DTO_ThongKeTuNgayDenNgay> getThongKeTuNgayDenNgay(String start, String end){
        return thongkeDAO.getThongKeTuNgayDenNgay(start, end);
    }
}
