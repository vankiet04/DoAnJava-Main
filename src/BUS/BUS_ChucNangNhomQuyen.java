package BUS;

import java.util.ArrayList;

import DAO.DAO_ChucNangNhomQuyen;
import DTO.DTO_ChucNangNhomQuyen;

public class BUS_ChucNangNhomQuyen {
    DAO_ChucNangNhomQuyen chucnangnhomquyen = new DAO_ChucNangNhomQuyen();
    public BUS_ChucNangNhomQuyen() {
    }

    public ArrayList<DTO_ChucNangNhomQuyen> selectsById(String t) {
        return chucnangnhomquyen.selectsById(t);
    }

    public int deleteAllNhomQuyen(int manhomquyen) {
        return chucnangnhomquyen.deleteAllNhomQuyen(manhomquyen);
    }

    public ArrayList<DTO_ChucNangNhomQuyen> selectAllChucNangNQ(int manhomquyen) {
        return chucnangnhomquyen.selectAllChucNangNQ(manhomquyen);
    }
    public String getTenNhomQuyenById(int manhomquyen) {
        return chucnangnhomquyen.getTenNhomQuyenById(manhomquyen);
    }
     


}
