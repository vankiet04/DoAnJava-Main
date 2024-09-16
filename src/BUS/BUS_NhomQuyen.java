package BUS;

import java.util.ArrayList;
import java.util.HashMap;

import DAO.DAO_NhomQuyen;
import DTO.DTO_NhomQuyen;

public class BUS_NhomQuyen {
    public DAO_NhomQuyen nq = new DAO_NhomQuyen();
    public ArrayList<DTO_NhomQuyen> listNhomquyen = nq.getAllData();

    public BUS_NhomQuyen() {
        listNhomquyen = nq.getAllData();
    }

    public ArrayList<DTO_NhomQuyen> getAllData() {
        return nq.getAllData();
    }

    public int insert(DTO_NhomQuyen t) {
        return nq.insert(t);
    }

    public int getnewIncrement() {
        int max = 0;
        for (int i = 0; i < listNhomquyen.size(); i++) {
            if (listNhomquyen.get(i).getManhomquyen() > max) {
                max = listNhomquyen.get(i).getManhomquyen();
            }
        }
        return max + 1;
    }


    public int insertChitietNhomQuyen(int manhomquyen, HashMap<String, ArrayList<String>> ctquyen) {
        return nq.insertChitietNhomQuyen(manhomquyen, ctquyen);
    }
    
    public int delete(int manhomquyen) {

        return nq.delete(manhomquyen);
    }

    public DTO_NhomQuyen getNhomQuyen(int manhomquyen) {
        return nq.getNhomQuyen(manhomquyen);
    }

    public int update(DTO_NhomQuyen t) {
        return nq.update(t);
    }

    public int getManhomquyenbyTennhomquyen(String tennhomquyen) {
        return nq.getManhomquyenbyTennhomquyen(tennhomquyen);
    }

    public int deleteChitietNhomQuyen(int manhomquyen) {
        return nq.deleteChitietNhomQuyen(manhomquyen);
    }

    public ArrayList<DTO_NhomQuyen> search (String keyword) {
        return nq.search(keyword);
    }
}
