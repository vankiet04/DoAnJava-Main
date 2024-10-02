/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import DTO.AccountDTO;
import DTO.DTO_ChucNangNhomQuyen;
import DTO.DTO_TaiKhoan;
import GUI.LogIn;
import GUI.MainProgram;
import GUI.Menu.TrangChu;
import GUI.Menu.FormItems;
import GUI.Menu.KhachHang;
import GUI.Menu.NhaCungCap;
import GUI.Menu.NhanVien;
import GUI.Menu.PhanQuyen;
import GUI.Menu.QuanLyPhieuNhap;
import GUI.Menu.QuanLyPhieuXuat;
import GUI.Menu.QuanLyThuocTinh;
import GUI.Menu.TaiKhoan;
import GUI.Menu.ThongKe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.bridj.cpp.std.list;

import BUS.BUS_ChucNangNhomQuyen;
import GUI.Menu.QuanLyTraHang;

/**
 *
 * @author Kiet
 */
public class MenuTaskBar extends JPanel {
    MainProgram main;
    DTO_TaiKhoan user;

    Color DefaultColor = new Color(135, 206, 235); // DefaultColor: là màu xanh biển của menu nha
    Color HowerBackgroundColor = new Color(192, 192, 192);
    Color backgroundItemtaskbar = new Color(255, 255, 255);
    public boolean isSelected;
    JPanel pnlBottom, pnlHeader;    

    TrangChu trangchu;

    BUS_ChucNangNhomQuyen busNQ = new BUS_ChucNangNhomQuyen();
    ArrayList<DTO_ChucNangNhomQuyen> listNQ = new ArrayList<>();
    String[][] listComponent = {
            { "Trang chủ", "home.svg", "trangchu" },
            { "Sản phẩm", "product.svg", "sanpham" },
            { "Thuộc tính", "product.svg", "sanpham" },
            { "Phiếu nhập", "phieunhap.svg", "phieunhap" },
            { "Phiếu xuất", "phieuxuat.svg", "phieuxuat" },
            { "Khách hàng", "khachhang.svg", "khachhang" },
            { "Thống kê", "thongke.svg", "thongke" },
            { "Nhân viên", "nhanvien.svg", "nhanvien" },
            { "Nhà cung cấp", "nhacc.svg", "nhacungcap" },
            { "Tài khoản", "account.svg", "account" },
            { "Phân quyền", "phanquyen.svg", "sanpham" },
            { "Đăng xuất", "logout.svg", "sanpham" },
            { "Trả hàng", "phieuxuat.svg", "trahang" },
    };

    public itemTaskbar[] listitem;
    JPanel mainContainer, pnlCenter;
    JScrollPane scrollPane;

    public MenuTaskBar(MainProgram main, DTO_TaiKhoan tk) {
        this.main = main;
        this.user = tk;
        KhoiTaoLeftMenu();
    }

    public void KhoiTaoLeftMenu() {
        listitem = new itemTaskbar[listComponent.length];
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));
        pnlHeader = new JPanel();
        // pnlHeader.setPreferredSize(new Dimension(225, 120));
        pnlHeader.setPreferredSize(new Dimension(350, 150));
        pnlHeader.setBackground(DefaultColor);
        pnlHeader.setLayout(new FlowLayout(0, 0, 5));
        pnlHeader.setBorder(new EmptyBorder(5, 10, 0, 10));

        headerTaskbar header = new headerTaskbar("admin.svg", "    Xin chào " + user.getTendangnhap() + "!");
        pnlHeader.add(header, BorderLayout.CENTER);
        this.add(pnlHeader, BorderLayout.NORTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 300));
        pnlCenter.setLayout(new FlowLayout(0, 0, 5));
        pnlCenter.setBackground(DefaultColor);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 0));
        this.add(scrollPane, BorderLayout.CENTER);

      

        for (int i = 0; i < listComponent.length; i++) {
            listitem[i] = new itemTaskbar(listComponent[i][1], listComponent[i][0]);
            pnlCenter.add(listitem[i]);
            if (i != 0) {
                // nếu phân quyền không có ở listitem hiện tại thì không hiện
                // if (!checkRole(listComponent[i][2])) {
                // listitem[i].setVisible(false);
                // }
            }
        }

        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].isSelected = true;
        for (itemTaskbar iBtn : listitem) {
            iBtn.setVisible(false);
        }
        listitem[0].setVisible(true);
        listitem[11].setVisible(true);


        listNQ = busNQ.selectAllChucNangNQ(user.getManhomquyen());
        for(DTO_ChucNangNhomQuyen item : listNQ){
           
            if (item.getMachucnang().equals("sanpham")) {
                listitem[1].setVisible(true);
            }
            if(item.getMachucnang().equals("thuoctinh")){
                listitem[2].setVisible(true);
            }
            if (item.getMachucnang().equals("nhaphang")) {
                listitem[3].setVisible(true);
            }
            if (item.getMachucnang().equals("xuathang")) {
                listitem[4].setVisible(true);
            }
            if (item.getMachucnang().equals("khachhang")) {
                listitem[5].setVisible(true);
            }
            if (item.getMachucnang().equals("thongke")) {
                listitem[6].setVisible(true);
            }
            if (item.getMachucnang().equals("nhanvien")) {
                listitem[7].setVisible(true);
            }
            if (item.getMachucnang().equals("nhacungcap")) {
                listitem[8].setVisible(true);
            }
            if (item.getMachucnang().equals("taikhoan")) {
                listitem[9].setVisible(true);
            }
            if (item.getMachucnang().equals("nhomquyen")) {
                listitem[10].setVisible(true);
            }

        }

        for (int i = 0; i < listComponent.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    AddHover(evt);
                }
            });
        }

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                TrangChu a = new TrangChu();
                main.changePages(a);
            }
        });

        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                FormItems product = new FormItems(main, user);
                main.changePages(product);

            }
        });

        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                QuanLyPhieuNhap qlpn = new QuanLyPhieuNhap(main, user);
                main.changePages(qlpn);
            }
        });
        

        listitem[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                QuanLyPhieuXuat qlpx = new QuanLyPhieuXuat(main, user);
                main.changePages(qlpx);
            }
        });
        listitem[12].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                QuanLyTraHang qlth = new QuanLyTraHang(main, user);
                main.changePages(qlth);
            }
        });

        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                QuanLyThuocTinh qltt =  new QuanLyThuocTinh(main, user); 
                main.changePages(qltt);
            }
        });
        
        listitem[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                // thay quanlythuoctinh thành 
                KhachHang kh =  new KhachHang(main, user); 
                main.changePages(kh);
            }
        });
        
        listitem[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                // thay quanlythuoctinh thành 
                ThongKe tk =  new ThongKe(); 
                main.changePages(tk);
            }
        });
        
        listitem[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                NhaCungCap zx =  new NhaCungCap(main, user); 
                main.changePages(zx);
            }
        });
        
         listitem[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                NhanVien product = new NhanVien(main, user);
                main.changePages(product);
            }
        });

         listitem[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                 TaiKhoan tk = new TaiKhoan(
                    main, user
                );
                main.changePages(tk);
            }
        });

        listitem[10].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                PhanQuyen pq = new PhanQuyen(main, user);
                main.changePages(pq);
            }
        });

        listitem[11].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
             int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    LogIn login = new LogIn();
                    login.setVisible(true);
                    main.dispose();
                    
                }
            }
        });





    }

    public void AddHover(MouseEvent evt) {
        for (int i = 0; i < listitem.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(backgroundItemtaskbar);
            }
        }
    }
}