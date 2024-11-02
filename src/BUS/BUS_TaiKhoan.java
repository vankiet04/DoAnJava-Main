package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.DAO_TaiKhoan;
import DTO.DTO_TaiKhoan;

public class BUS_TaiKhoan {
    public DAO_TaiKhoan tk = new DAO_TaiKhoan();
    private ArrayList<DTO_TaiKhoan> listtk = new ArrayList<>();


    public BUS_TaiKhoan() {
        listtk = tk.getAllData();
    }
    
    public ArrayList<DTO_TaiKhoan> getAllData() {
        return tk.getAllData();
    }

    public int insert(int manv, String username, String password, int manhomquyen) {

        for (int i = 0; i < listtk.size(); i++) {
            if (listtk.get(i).getTendangnhap().equals(username)) {
                return 0;
            }
        }

        return tk.insert(manv, username, password, manhomquyen);
    }

    public int updatetk(int currentmanv, int newmanv, String username, String password, int manhomquyen) {
        return tk.updatetk(currentmanv, newmanv, username, password, manhomquyen);
    }

    public int delete(int manv) {
        return tk.delete(manv);
    }

    public ArrayList<DTO_TaiKhoan> search(String keyword) {
        return tk.search(keyword);
    }
    public String getTenDangNhapByIdNhanVien(int manv) {
        return tk.getTenDangNhapByIdNhanVien(manv);
    }
}

