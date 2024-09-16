/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Menu;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Admin
 */
public class ThongKe extends JPanel{
        JTabbedPane tab;
        JPanel sanPham,doanhThu;
        
        public ThongKe(){
            init();
        }
        public void init(){
            this.setLayout(new GridLayout(1,1));
            
            sanPham= new ThongKeTonKho();
            doanhThu= new ThongKeDoanhThu();
            ThongKeDoanhThuTheoNgayTrongThang doanhthutheothang = new ThongKeDoanhThuTheoNgayTrongThang();
            ThongKeTuNgayDenNgayy tk = new ThongKeTuNgayDenNgayy();
            tab= new JTabbedPane();
            tab.addTab("Doanh Thu Theo Từng Tháng Trong Năm", doanhThu);
            tab.addTab("Doanh Thu Theo Ngày Trong Tháng", doanhthutheothang);
            tab.addTab("Doanh Thu Theo Khoảng Thời Gian", tk);
            tab.addTab("Tồn Kho", sanPham);
            this.add(tab);
        }
}
