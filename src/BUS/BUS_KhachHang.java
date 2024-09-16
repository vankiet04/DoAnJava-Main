package BUS;

import java.util.ArrayList;

import DAO.DAO_KhachHang;
import DTO.DTO_KhachHang;

public class BUS_KhachHang {
    public DAO_KhachHang kh = new DAO_KhachHang();
    private ArrayList<DTO_KhachHang> listKhachHang = new ArrayList<>();

    public BUS_KhachHang() {
        listKhachHang = kh.getAllData();
    }
    
    public ArrayList<DTO_KhachHang> getAllData() {
        return this.listKhachHang;
    }

    public int getMaxID() {
        int maxID = 0;
        for (DTO_KhachHang x : listKhachHang) {
            int id = Integer.parseInt(x.getMaKhachHang());
            maxID = Math.max(maxID, id);
        }
        return maxID;
    }

    public int insert(DTO_KhachHang khachHang) {
        if (kh.insert(khachHang) > 0) {
            listKhachHang.add(khachHang);
            return 1;
        }
        return -1;
    }

    public DTO_KhachHang getKHbyid(String id) {
        for (int i = 0; i < listKhachHang.size(); i++) {
            if (listKhachHang.get(i).getMaKhachHang().equals(id)) {
                return listKhachHang.get(i);
            }
        }
        return null;
    }

    public int update(DTO_KhachHang khachhang) {
        if (kh.update(khachhang) == 1) {
            listKhachHang = kh.getAllData();
            return 1;
        }
        return 0;
    }

    public int delete(String id) {
        if (kh.delete(id) == 1) {
            for (int i = 0; i < listKhachHang.size(); i++) {
                if (listKhachHang.get(i).getMaKhachHang().equals(id)) {
                    listKhachHang.remove(i);
                    break;
                }
            }
            return 1;
        }
        return 0;
    }

    public ArrayList<DTO_KhachHang> search(String name) {
        ArrayList<DTO_KhachHang> result = new ArrayList<>();
        for (DTO_KhachHang x : listKhachHang) {
            if (x.getHoTen().toLowerCase().contains(name.toLowerCase()) || x.getMaKhachHang().toLowerCase().contains(name.toLowerCase()) ){
                result.add(x);
            }
        }
        return result;
    }

    public DTO_KhachHang getkhtheoid(int id) {
        ArrayList<DTO_KhachHang> list = kh.getAllData();
        DTO_KhachHang kh = new DTO_KhachHang();
        for (DTO_KhachHang x : list) {
            if (Integer.parseInt(x.getMaKhachHang()) == id) {
                kh = x;
            }
        }
        return kh;
    }

    public DTO_KhachHang getByIndex(int index) {
        return listKhachHang.get(index);
    }

    public String getSoLuongKhachHang() {
        return String.valueOf(listKhachHang.size());
    }
}
