/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiet
 */
public class DTO_ThongKeThang {
    private int thang;
    private long chiphi;
    private long doanhthu;
    private long loinhuan;
    
    public DTO_ThongKeThang(){
        
    }
    
    public DTO_ThongKeThang(int thang, long chiphi, long doanhthu, long loinhuan){
        this.thang = thang;
        this.chiphi = chiphi;
        this.doanhthu = doanhthu;
        this.loinhuan = loinhuan;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public long getChiphi() {
        return chiphi;
    }

    public void setChiphi(int chiphi) {
        this.chiphi = chiphi;
    }

    public long getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public long getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(int loinhuan) {
        this.loinhuan = loinhuan;
    }
}
