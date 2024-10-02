/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.BUS_ChiTietCauHinh;
import BUS.BUS_ChiTietPhieuNhap;
import BUS.BUS_ChiTietPhieuXuat;
import BUS.BUS_ChiTietSanPham;
import BUS.BUS_KhachHang;
import BUS.BUS_NhaCungCap;
import BUS.BUS_NhanVien;
import BUS.BUS_PhieuNhap;
import BUS.BUS_PhieuXuat;
import BUS.BUS_Product;

import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietPhieuXuat;
import DTO.DTO_Imei;
import DTO.DTO_NhaCungCap;
import DTO.DTO_NhanVien;
import DTO.DTO_PhieuXuat;
import DTO.DTO_Product;
import DTO.DTO_TaiKhoan;
import helper.Formater;

//import numberformat
import java.text.NumberFormat;
/**
 *
 * @author Admin
 */
public class ThemPhieuXuat extends javax.swing.JDialog {

    /**
     * Creates new form ThemPhieuNhap
     */
    ArrayList<String> imeidachon = new ArrayList<>();
    ArrayList<String> allimei = new ArrayList<>();
    BUS_Product busProduct = new BUS_Product();
    BUS_ChiTietCauHinh busChiTietCauHinh = new BUS_ChiTietCauHinh();
    BUS_PhieuXuat busPhieuXuat = new BUS_PhieuXuat();
    BUS_KhachHang  busKhachHang = new BUS_KhachHang();
    BUS_ChiTietPhieuXuat busChiTietPhieuXuat = new BUS_ChiTietPhieuXuat();
    BUS_ChiTietSanPham busChiTietSanPham = new BUS_ChiTietSanPham();
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> mapsoluong = new HashMap<>();
    int value = map.getOrDefault(1, 0);
    int currentIDselected= -1;
    int currentIDselected2 = -1;
    ArrayList<DTO_ChiTietCauHinh> danhsachnhap = new ArrayList<>();
    ArrayList<String> tenspmappingdanhsach = new ArrayList<>(); 
    ArrayList<Long> giamappingdanhsach = new ArrayList<>();
    BUS_NhaCungCap busNhaCungCap = new BUS_NhaCungCap();
    ArrayList<String> tempimeidachon = new ArrayList<>();
    DTO_TaiKhoan user = new DTO_TaiKhoan();
    ArrayList<ArrayList<String>> danhsachimeitungsanpham = new ArrayList<>();
    GUI.Menu.QuanLyPhieuXuat px;
    boolean check = true;
    BUS_PhieuNhap busPhieuNhap = new BUS_PhieuNhap();
    BUS_PhieuXuat phieuxuatBUS = new BUS_PhieuXuat();
    public ThemPhieuXuat(java.awt.Frame parent, boolean modal, DTO_TaiKhoan user, GUI.Menu.QuanLyPhieuXuat px) {
        super(parent, modal);
        this.user = user;
        this.px = px;
      
        initComponents();
        jTextField2.setEditable(false);
        jTextField8.setEditable(false);
        loadTableProducts();
        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!check) return;
                int index = jComboBox1.getSelectedIndex();
                jTextField8.setText(Integer.toString(map.getOrDefault(index, 0)));
                jTextField8.setEditable(false);
                imeidachon = new ArrayList<>();
                allimei = new ArrayList<>();
                BUS_ChiTietCauHinh bus_ChiTietCauHinh = new BUS_ChiTietCauHinh();
                int maphienban = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" ")[11]);
                DTO_ChiTietCauHinh dto_ChiTietCauHinh = bus_ChiTietCauHinh.getchitietcauhinh(maphienban);
                // vnd gia xuat currenct vietnam
                Locale vietnam = Locale.forLanguageTag("vi-VN");
                NumberFormat format = NumberFormat.getCurrencyInstance(vietnam);
                jTextField3.setText("10");
                

            }
        });
        // addeventclick
        jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                check = false;
                int row = jTable2.getSelectedRow();
                if (row != -1) {
                    currentIDselected = Integer.parseInt(jTable2.getValueAt(row, 0).toString());
                    loadInfo();
                    imeidachon = new ArrayList<>();
                    allimei = new ArrayList<>();
                }
                // lay gia xuat
                BUS_ChiTietCauHinh bus_ChiTietCauHinh = new BUS_ChiTietCauHinh();
                int maphienban = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" ")[11]);
                DTO_ChiTietCauHinh dto_ChiTietCauHinh = bus_ChiTietCauHinh.getchitietcauhinh(maphienban);
                jTextField3.setText("10");
                check = true;
            }
        });

        // addeventclick table list pn
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = jTable1.getSelectedRow();
                if (row != -1) {
                    currentIDselected2 = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
                
                }
            }
        });

        // loadTablenhacungcap();
        loadnhanviennhap();
        loadmaphieuxuat();
        loadkhachhang();
    }


    public int validatethemsanphampn() {
        if (jTextField5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống");
            return 0;
        }
        if (jTextField3.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Phần trăm lợi nhuận không được để trống");
            return 0;
        }
        // nếu số lượng xuất có chữ cái
        try {
            int soluong = Integer.parseInt(jTextField5.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số");
            return 0;
        }
        // nếu đơn giá có chữ cái
        try {
            long dongia = Long.parseLong(jTextField3.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số");
            return 0;
        }
        return 1;

    }
    public void loadkhachhang() {
        jComboBox2.removeAllItems();
        busKhachHang.getAllData().forEach((i) -> {
            jComboBox2.addItem(i.getMaKhachHang() + " - " + i.getHoTen());
        });
    }

    public void loadmaphieuxuat() {
        jTextField6.setText(Integer.toString(busPhieuXuat.getnewmaphieuxuat()));
        jTextField6.setEditable(false);
    }

    public void loadnhanviennhap() {
        BUS_NhanVien busNhanVien = new BUS_NhanVien();
        ArrayList<DTO_NhanVien> arr = busNhanVien.getAllData();
        for (DTO_NhanVien dtonv : arr) {
            if (dtonv.getManv() == user.getManv()) {
                jTextField7.setText(dtonv.getManv() + " - " + dtonv.getHoten());
            }
        }
        jTextField7.setEditable(false);
    }


    public void loadTableProducts() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        ArrayList<DTO_Product> arr = busProduct.getAllData();
        ArrayList<DTO_ChiTietCauHinh> arr2 = busChiTietCauHinh.getAllData();
        arr2.forEach((i) -> {
            map.put(i.getMasanpham(), map.getOrDefault(i.getMasanpham(), 0) + i.getSoluongton());
        });

        int ii = 1;
        for (DTO_Product dto_Product : arr) {
            model.addRow(new Object[]{
                dto_Product.getMasanpham(),
                dto_Product.getTensanpham(),
                map.getOrDefault(dto_Product.getMasanpham(), 0)
            });
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }  

    }

    public void loadTableselected() {
        
    }

    public void loadInfo() {
        DTO_Product dto_Product = busProduct.getProductByID(currentIDselected);
        jTextField2.setText(dto_Product.getMasanpham() + " - " + dto_Product.getTensanpham());

        // loadcombobox phien an sp
        jComboBox1.removeAllItems();
        ArrayList<DTO_ChiTietCauHinh> arr = busChiTietCauHinh.getAllChiTietCauHinh(Integer.toString(currentIDselected));
        mapsoluong = new HashMap<>();

        for (DTO_ChiTietCauHinh i : arr) {
            jComboBox1.addItem("ROM: " + i.getRom() + " GB - RAM: " + i.getRam() + " GB" + " - Mã phiên bản: " + i.getMaphienbansp());
            map.put(jComboBox1.getItemCount() - 1, i.getSoluongton());
        }

        /// load soluongton first arr
        jTextField8.setText(Integer.toString(map.getOrDefault(0, 0)));
        // set so luong ton
        
        //load imei

    }

    public void getnewmaphieuxuat() {
    }

    public void loaddsphieunhap() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (int i = 0; i < danhsachnhap.size(); i++) {
            DTO_ChiTietCauHinh dto_ChiTietCauHinh = danhsachnhap.get(i);
            model.addRow(new Object[]{
                dto_ChiTietCauHinh.getMasanpham(),
                tenspmappingdanhsach.get(i),
                dto_ChiTietCauHinh.getRam() + " GB",
                dto_ChiTietCauHinh.getRom() + " GB",
                Formater.FormatVND(giamappingdanhsach.get(i)),
                dto_ChiTietCauHinh.getSoluongton()
            });
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1060, 680));

        jPanel2.setPreferredSize(new java.awt.Dimension(840, 670));

        jPanel10.setPreferredSize(new java.awt.Dimension(410, 360));

        jTextField1.setText("Tìm kiếm");
        jTextField1.setPreferredSize(new java.awt.Dimension(400, 40));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField1);
        jPanel10.add(jPanel19);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 245));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ));
        // jTable2.setPreferredSize(new java.awt.Dimension(225, 120));
        jScrollPane2.setViewportView(jTable2);

        jPanel10.add(jScrollPane2);

        jPanel2.add(jPanel10);

        jPanel4.setMinimumSize(new java.awt.Dimension(420, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(410, 255));
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel16.setMinimumSize(new java.awt.Dimension(439, 40));
        jPanel16.setPreferredSize(new java.awt.Dimension(380, 150));

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã sản phẩm - Tên sản phẩm");
        jLabel1.setPreferredSize(new java.awt.Dimension(410, 30));
        jPanel7.add(jLabel1, java.awt.BorderLayout.CENTER);

        jTextField2.setPreferredSize(new java.awt.Dimension(410, 40));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField2, java.awt.BorderLayout.PAGE_END);

        jPanel16.add(jPanel7);

        jPanel5.setMinimumSize(new java.awt.Dimension(50, 42));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Cấu hình");
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel5.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jComboBox1.setPreferredSize(new java.awt.Dimension(50, 22));
       
      
        jPanel5.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel5);

        jPanel9.setPreferredSize(new java.awt.Dimension(80, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Số lượng nhập");
        jLabel5.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel9.add(jLabel5, java.awt.BorderLayout.CENTER);

        jTextField5.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel9.add(jTextField5, java.awt.BorderLayout.PAGE_END);

        jPanel16.add(jPanel9);

        jPanel18.setPreferredSize(new java.awt.Dimension(80, 50));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Số lượng tồn");
        jLabel11.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel18.add(jLabel11, java.awt.BorderLayout.CENTER);

        jTextField8.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel18.add(jTextField8, java.awt.BorderLayout.PAGE_END);

        jPanel16.add(jPanel18);

        jPanel20.setPreferredSize(new java.awt.Dimension(25, 50));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("IMEI");
        jLabel12.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel20.add(jLabel12, java.awt.BorderLayout.CENTER);

        jButton5.setText("...");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });
        jPanel20.add(jButton5, java.awt.BorderLayout.PAGE_END);

        jPanel16.add(jPanel20);

        jPanel4.add(jPanel16, java.awt.BorderLayout.PAGE_START);

        jPanel17.setPreferredSize(new java.awt.Dimension(415, 55));

        jButton2.setText("Thêm sản phẩm");
        jButton2.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton2);

        jButton1.setText("Xoá sản phẩm");
        jButton1.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton1);

        jPanel4.add(jPanel17, java.awt.BorderLayout.SOUTH);

        jPanel6.setMinimumSize(new java.awt.Dimension(64, 50));
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jTextField3.setMaximumSize(new java.awt.Dimension(333, 333));
        jTextField3.setMinimumSize(new java.awt.Dimension(64, 1));
        jTextField3.setPreferredSize(new java.awt.Dimension(10, 22));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField3, java.awt.BorderLayout.CENTER);

        jLabel3.setText("Giá xuất");
        jLabel3.setPreferredSize(new java.awt.Dimension(43, 20));
        jPanel6.add(jLabel3, java.awt.BorderLayout.NORTH);

        jLabel4.setText("Phần trăm thu được (%):");
        jLabel4.setPreferredSize(new java.awt.Dimension(43, 20));
        jPanel6.add(jLabel4, java.awt.BorderLayout.NORTH);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(825, 300));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Ram", "Rom", "Đơn giá", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);

        jPanel1.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(210, 670));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel11.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jButton3.setText("THÊM");
        jButton3.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton3, java.awt.BorderLayout.CENTER);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tổng tiền");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setPreferredSize(new java.awt.Dimension(200, 32));
        jPanel11.add(jLabel9, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel15.setPreferredSize(new java.awt.Dimension(210, 300));

        jPanel12.setLayout(new java.awt.BorderLayout());

        jTextField6.setText("jTextField2");
        jTextField6.setPreferredSize(new java.awt.Dimension(200, 40));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel12.add(jTextField6, java.awt.BorderLayout.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Mã phiếu xuất");
        jLabel6.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel12.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        jPanel15.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Nhân viên xuất");
        jLabel7.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel13.add(jLabel7, java.awt.BorderLayout.PAGE_START);

        jTextField7.setText("jTextField2");
        jTextField7.setPreferredSize(new java.awt.Dimension(200, 40));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel13.add(jTextField7, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());
        jPanel15.add(jPanel14);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Khách hàng");
        jLabel8.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel15.add(jLabel8);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(200, 40));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel15.add(jComboBox2);

        jPanel3.add(jPanel15, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (currentIDselected2 == -1) {
            JOptionPane.showMessageDialog(null, "Chọn sản phẩm cần xoá");
            return;
        }
        int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xoá sản phẩm này không?");
        if (a != 0) {
            return;
        }
        for (int i = 0; i < danhsachnhap.size(); i++) {
            if (danhsachnhap.get(i).getMasanpham() == currentIDselected2) {
                danhsachnhap.remove(i);
                tenspmappingdanhsach.remove(i);
                giamappingdanhsach.remove(i);
                danhsachimeitungsanpham.remove(i);
                loaddsphieunhap();
                return;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: them phieu x
       
       
        int maphieuxuat = Integer.parseInt(jTextField6.getText());
        int manv = Integer.parseInt(jTextField7.getText().split(" - ")[0]);
        int makh = Integer.parseInt(jComboBox2.getSelectedItem().toString().split(" - ")[0]);
        long tongtien = 0;
        for (int i = 0; i < danhsachnhap.size(); i++) {
            tongtien += danhsachnhap.get(i).getSoluongton() * giamappingdanhsach.get(i);
        }
        long now = System.currentTimeMillis();
        java.sql.Timestamp thoigian = new java.sql.Timestamp(now);

        DTO_PhieuXuat dto_PhieuXuat = new DTO_PhieuXuat(maphieuxuat, thoigian, tongtien, manv, makh);
        int res = busPhieuXuat.insert(dto_PhieuXuat);
        if (res > 0) {
            //  neu gia xuat lon hon 50 ty thi ko cho xuat
            if (tongtien > 50000000000L) {
                JOptionPane.showMessageDialog(null, "Tổng tiền vượt quá 50 tỷ, không thể xuất");
                return;
            }
            // them chitiet phieu xuat
            ArrayList<DTO_ChiTietPhieuXuat> arr = new ArrayList<>();
            for (int i = 0; i < danhsachnhap.size(); i++) {
                DTO_ChiTietPhieuXuat dto_ChiTietPhieuXuat = new DTO_ChiTietPhieuXuat(maphieuxuat, danhsachnhap.get(i).getMaphienbansp(), danhsachnhap.get(i).getSoluongton(), giamappingdanhsach.get(i) * danhsachnhap.get(i).getSoluongton());
                arr.add(dto_ChiTietPhieuXuat);
            }
            int res2 = busChiTietPhieuXuat.insert(arr);
            if (res2 > 0) {
                // 
                // truwf so luong ton
                for (int i = 0; i < danhsachnhap.size(); i++) {
                    busChiTietCauHinh.truSoLuongPhienBanSanPham(danhsachnhap.get(i).getMaphienbansp(), danhsachnhap.get(i).getSoluongton());
                }

                // update ma phieu xuat cho imei trong table ctsp
                busChiTietSanPham.updateMaPhieuXuat(danhsachnhap, Integer.parseInt(jTextField6.getText()), danhsachimeitungsanpham);
                
                px.loadtablepx(phieuxuatBUS.getAll());
                JOptionPane.showMessageDialog(null, "Thêm phiếu xuất thành công");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu xuất thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Thêm phiếu xuất thất bại");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here: them sp
        int res = validatethemsanphampn();
        if (res == 0) return;

        if (Integer.parseInt(jTextField5.getText()) > Integer.parseInt(jTextField8.getText())) {
            JOptionPane.showMessageDialog(null, "Số lượng xuất lớn hơn số lượng tồn!");
            return;
        }

        if (Integer.parseInt(jTextField5.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng xuất phải lớn hơn 0");
            return;
        }

        if (imeidachon.size() == 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn imei");
            return;
        }
        //pham tram  lợi nhuận phai tu 0 den 100 (%)
        if (Integer.parseInt(jTextField3.getText()) < 0 || Integer.parseInt(jTextField3.getText()) > 100) {
            JOptionPane.showMessageDialog(null, "Phần trăm lợi nhuận phải từ 0 đến 100");
            return;
        }
        // them n imei vao list
        // them imei vao list
    
        danhsachimeitungsanpham.add(new ArrayList<>(imeidachon));

        ArrayList<DTO_ChiTietCauHinh> arr = busChiTietCauHinh.getAllChiTietCauHinh(Integer.toString(currentIDselected));
        int rom = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" ")[1]);
        int ram = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" ")[5]);
        DTO_ChiTietCauHinh dto_ChiTietCauHinh = new DTO_ChiTietCauHinh();
        for (DTO_ChiTietCauHinh i : arr) {
            if (i.getRom() == rom && i.getRam() == ram) {
                dto_ChiTietCauHinh = i;
                dto_ChiTietCauHinh.setSoluongton(Integer.parseInt(jTextField5.getText()));
                break;
            }
        }

        for (int i = 0; i < danhsachnhap.size(); i++) {
            // check co bi trùng không
            if (danhsachnhap.get(i).getMasanpham() == dto_ChiTietCauHinh.getMasanpham() && danhsachnhap.get(i).getRam() == dto_ChiTietCauHinh.getRam() && danhsachnhap.get(i).getRom() == dto_ChiTietCauHinh.getRom()) {
                JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại trong danh sách");
                return;
            }
        }
        danhsachnhap.add(dto_ChiTietCauHinh);
        tenspmappingdanhsach.add(jTextField2.getText().split(" - ")[1]);
        int phantram = Integer.parseInt(jTextField3.getText());
        int maphienbansanpham = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" ")[11]);
        ArrayList<DTO_ChiTietPhieuNhap> listctphieunhap = busPhieuNhap.getAllChiTietPhieuNhap(maphienbansanpham);
        long sum = 0;
        long soluong = 0;
        for (DTO_ChiTietPhieuNhap i : listctphieunhap) {
            if (i.getMaphienbansp() == maphienbansanpham) {
                sum += i.getDongia() * i.getSoluong();
                soluong += i.getSoluong();
            }

        }
        

        long giaban = sum / soluong;
        giaban = giaban + giaban * phantram / 100;
        giamappingdanhsach.add(giaban);
        loaddsphieunhap();
        // tinh tong tien
        long tongtien = 0;
        for (int i = 0; i < danhsachnhap.size(); i++) {
            tongtien += danhsachnhap.get(i).getSoluongton() * giamappingdanhsach.get(i);
        }
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("vi-VN"));

        jLabel9.setText("Tổng tiền: " + format.format(tongtien));
        imeidachon = new ArrayList<>();

    }//GEN-LAST:event_jButton2MousePressed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        // TODO add your handling code here:
        allimei = busChiTietSanPham.getImeiTheoMaPhienban(Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" ")[11]));
        ArrayList<String> tempimeidachon = new ArrayList<>(imeidachon);
        // nếu chưa nhập số lượng xuất
        if (jTextField5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nhập số lượng trước khi chọn imei");
            return;
        }
        DSIMEII dsimei = new DSIMEII(null, true, allimei, this, tempimeidachon, Integer.parseInt(jTextField5.getText()));
        dsimei.setLocationRelativeTo(null);
        dsimei.setVisible(true);
        
    }//GEN-LAST:event_jButton5MousePressed
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed


    private void jButton5MousePressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_jButton5ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
