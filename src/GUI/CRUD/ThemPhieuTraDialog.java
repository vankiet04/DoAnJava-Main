/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.kitfox.svg.A;

import BUS.BUS_ChiTietCauHinh;
import BUS.BUS_ChiTietPhieuNhap;
import BUS.BUS_ChiTietPhieuTra;
import BUS.BUS_ChiTietSanPham;
import BUS.BUS_ChiTietSanPhamTra;
import BUS.BUS_NhaCungCap;
import BUS.BUS_NhanVien;
import BUS.BUS_PhieuNhap;
import BUS.BUS_PhieuTra;
import BUS.BUS_Product;
import DAO.DAO_ChiTietCauHinh;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietPhieuTra;
import DTO.DTO_ChiTietSanPham;
import DTO.DTO_ChiTietSanPhamTra;
import DTO.DTO_NhaCungCap;
import DTO.DTO_NhanVien;
import DTO.DTO_PhieuNhap;
import DTO.DTO_PhieuTra;
import DTO.DTO_Product;
import DTO.DTO_TaiKhoan;

/**
 *
 * @author KIET
 */
public class ThemPhieuTraDialog extends javax.swing.JDialog {

    /**
     * Creates new form ThemPhieuTraDialog
     */
    long tonggiaxuat = 0;
    int flag = 0;
        GUI.MainProgram main;
        DTO_TaiKhoan user;
        GUI.Menu.QuanLyTraHang ap;
    
         BUS_NhaCungCap nccBus = new BUS_NhaCungCap();
         BUS_NhanVien nvBus = new BUS_NhanVien();

         BUS_PhieuNhap busPhieuNhap = new BUS_PhieuNhap();
         BUS_ChiTietSanPham busChiTietSanPham = new BUS_ChiTietSanPham();
         BUS_ChiTietCauHinh busChiTietCauHinh = new BUS_ChiTietCauHinh();
         BUS_Product busProduct = new BUS_Product();
         BUS_ChiTietPhieuNhap busChiTietPhieuNhap = new BUS_ChiTietPhieuNhap();
         BUS_PhieuTra busPhieuTra = new BUS_PhieuTra();
         BUS_ChiTietPhieuTra busChiTietPhieuTra = new BUS_ChiTietPhieuTra();
         BUS_ChiTietSanPhamTra busChiTietSanPhamTra = new BUS_ChiTietSanPhamTra();
        
         DefaultTableModel model;
         ArrayList<DTO_ChiTietSanPham> listImeiDaChon = new ArrayList<>();
         ArrayList<ArrayList<DTO_ChiTietSanPham>> listImeiDaChonVaoPhieuTra = new ArrayList<>();
         ArrayList<DTO_ChiTietPhieuTra> ctphieutra;
         ArrayList<DTO_ChiTietSanPhamTra> ctsanphamtra;


    public ThemPhieuTraDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ThemPhieuTraDialog(java.awt.Frame parent, boolean modal, GUI.Menu.QuanLyTraHang ap, DTO_TaiKhoan user) {
        super(parent, modal);
        this.ap = ap;
        this.user = user;
        initComponents();
        jComboBox4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        jTextField9.setEnabled(false);

        // this.jComboBox3.addItem("Chọn cấu hình");
        loadComboboxNhaCungCap();
        loadComboboxNhanVien();

    }


    public void loadComboboxNhanVien() {
        jComboBox4.removeAllItems();
        DTO_NhanVien nvCb = nvBus.getNhanVien(user.getManv());
        jComboBox4.addItem(nvCb.getManv() + " - " + nvCb.getHoten());
        jComboBox4.setFocusable(false);

    }
        
    public void loadComboboxNhaCungCap() {
        jComboBox5.removeAllItems();
        // mac dinh 0 la CHon nha cung cap
        jComboBox5.addItem("Chọn nhà cung cấp");
        ArrayList<DTO_NhaCungCap> listNCC = nccBus.getAllData();
        for (DTO_NhaCungCap ncc : listNCC) {
            jComboBox5.addItem(ncc.getMancc() + " - " + ncc.getTenncc());
        }
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (jComboBox5.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp");
                    jComboBox5.setSelectedIndex(1);
                    showPhieuNhapByNhaCungCap();
                }
                else {
                    showPhieuNhapByNhaCungCap();
                }
            }
        });
    }

    public void showPhieuNhapByNhaCungCap() {
        
        String tennhacungcap = jComboBox5.getSelectedItem().toString().split("-")[1].trim();
        int manhacungcap = nccBus.getMaNhaCungCap(tennhacungcap);
        // JOptionPane.showMessageDialog(null, manhacungcap);
        ArrayList<Integer> listMaPhieuNhap = busPhieuNhap.getAllMaPhieuNhapByMancc(manhacungcap);
        // JOptionPane.showMessageDialog(null, listMaPhieuNhap);
        ArrayList<Integer> listMaPhienBan = busChiTietSanPham.getAllMaPhienBanByMaPhieuNhap(listMaPhieuNhap);
        ArrayList<DTO_ChiTietCauHinh> listPhienBanSP = busChiTietCauHinh.getAllPhienBanByListMaPhienBan(listMaPhienBan);
        //sap xep theo ten san pham
        // listPhienBanSP.sort((a, b) -> {
        //     return busProduct.getProductByID(a.getMasanpham()).getTensanpham().compareTo(busProduct.getProductByID(b.getMasanpham()).getTensanpham());
        // });
        loadTablePhienBanSP(listPhienBanSP, listMaPhieuNhap);

    }
    
    public void loadTablePhienBanSP(ArrayList<DTO_ChiTietCauHinh> listpbsp,  ArrayList<Integer> listMaPhieuNhap) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        int i = 1;
        for (DTO_ChiTietCauHinh pbsp : listpbsp) {
            String phienban = pbsp.getRam() + "GB Ram - " + pbsp.getRom() + "GB Rom";
            String tensanpham = busProduct.getProductByID(pbsp.getMasanpham()).getTensanpham();
            int soluongphienban = busChiTietSanPham.getTongSoLuongPhienBanSanPham(pbsp.getMaphienbansp(),
                    listMaPhieuNhap);
            model.addRow(new Object[]{
                pbsp.getMaphienbansp(),
                tensanpham,
                    phienban,
                    soluongphienban
            });
        }
        
        
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = jTable1.getSelectedRow();
                if (row != -1) {
                    jTextField5.setText(jTable1.getValueAt(row, 0).toString());
                    jTextField6.setText(jTable1.getValueAt(row, 1).toString());
                    jTextField9.setText(jTable1.getValueAt(row, 2).toString());
                    //so luong ve 0
                    jTextField10.setText("");
                    jButton4.setText("Chọn");
                    listImeiDaChon.clear();
                 

                    
                }
            }
        });
          //jTable1 can giua 
          DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
          centerRenderer.setHorizontalAlignment( JLabel.CENTER );
          jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
          jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
          jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
          jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
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
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        txtSearchNCC3 = new com.raven.suportSwing.TextField();
        myButton1NCC3 = new com.raven.suportSwing.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã phiên bản :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên sản phẩm đã chọn:");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Chọn imei: (hoặc mặc định)");

        jButton4.setText("Chưa chọn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Phiên bản:");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Nhập số lượng trả:");

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jButton5.setText("Thêm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField9)
                    .addComponent(jLabel15)
                    .addComponent(jTextField10)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Chọn nhà cung cấp trả:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tên nhân viên :");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setPreferredSize(new java.awt.Dimension(72, 30));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setPreferredSize(new java.awt.Dimension(72, 30));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("TỔNG TIỀN:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 102));
        jLabel4.setText("0 đ");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("XUẤT PHIẾU TRẢ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        txtSearchNCC3.setLabelText("Tìm theo tên or mã");
        txtSearchNCC3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchNCC3FocusGained(evt);
            }
        });
        txtSearchNCC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchNCC3ActionPerformed(evt);
            }
        });
        txtSearchNCC3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchNCC3KeyReleased(evt);
            }
        });

        myButton1NCC3.setText("Tìm cơ bản");
        myButton1NCC3.setBorderColor(new java.awt.Color(153, 255, 255));
        myButton1NCC3.setRadius(20);
        myButton1NCC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1NCC3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchNCC3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(myButton1NCC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(myButton1NCC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchNCC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã phiên bản", "Tên sản phẩm", "Phiên bản", "Tồn kho"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addGap(18, 30, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Thông tin sản phẩm đã được chọn, để thêm vào phiếu trả:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã phiên bản", "Tên sản phẩm", "RAM", "ROM", "Số lượng", "Tổng giá"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //neusoluong la rong
        if (jTextField10.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng trả");
            return;
        }
        int soluongtra = Integer.parseInt(jTextField10.getText());
        
        // neu so luong tra > tonkho o table index
        if (soluongtra > Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString())) {
            JOptionPane.showMessageDialog(null, "Số lượng trả không được lớn hơn số lượng tồn kho");
            return;
        }
      
        int maphienban = Integer.parseInt(jTextField5.getText());
        int manhacungcap = nccBus.getMaNhaCungCap(jComboBox5.getSelectedItem().toString().split("-")[1].trim());
        ArrayList<Integer> listMaPhieuNhap = busPhieuNhap.getAllMaPhieuNhapByMancc(manhacungcap);
        ChonImeiTra chonImei = new ChonImeiTra(null, true, this, soluongtra, maphienban, listMaPhieuNhap);
        chonImei.setLocationRelativeTo(null);
        chonImei.setVisible(true);
        if (listImeiDaChon.size() > 0) {
            jButton4.setText("Đã chọn");
        }
        else {
            jButton4.setText("Chọn");
        }
        //listImeiDaChon



    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: Xoa
        //Xoa trong list imei da chon
        //Jtabl2 khong co gi hien thi thi jComboBox5 se hien thi
        if (jTable2.getSelectedRow() != -1) {
            int row = jTable2.getSelectedRow();
            int maphienban = Integer.parseInt(jTable2.getValueAt(row, 1).toString());
            for (int i = 0; i < listImeiDaChonVaoPhieuTra.size(); i++) {
                for (int j = 0; j < listImeiDaChonVaoPhieuTra.get(i).size(); j++) {
                    if (listImeiDaChonVaoPhieuTra.get(i).get(j).getMaphienbansp() == maphienban) {
                        listImeiDaChonVaoPhieuTra.remove(i);
                        break;
                    }
                }
            }
            //xoa trong table
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.removeRow(row);
            // stt tu cap nhat lai
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiên bản sản phẩm cần xóa");
        }
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int maphieutra = busPhieuTra.getMaPhieuTra();
        long now = System.currentTimeMillis();
        java.sql.Timestamp currenTime = new java.sql.Timestamp(now);
        int manv = user.getManv();
        int mancc = nccBus.getMaNhaCungCap(jComboBox5.getSelectedItem().toString().split("-")[1].trim());


        //them chi tiet san pham tra
        for (ArrayList<DTO_ChiTietSanPham> listImei : listImeiDaChonVaoPhieuTra) {
            for (DTO_ChiTietSanPham imei : listImei) {
                DTO_ChiTietSanPhamTra ctsanphamtra = new DTO_ChiTietSanPhamTra(imei.getImei(), imei.getMaphienbansp(),
                        maphieutra, 1);
                int result3 = busChiTietSanPhamTra.themChiTietSanPhamTra(ctsanphamtra,maphieutra );
                if (result3 == 0) {
                    JOptionPane.showMessageDialog(null, "Thêm chi tiết sản phẩm trả thất bại");
                    return;
                }

            }
        }


        tonggiaxuat = 0;
        for (ArrayList<DTO_ChiTietSanPham> listImei : listImeiDaChonVaoPhieuTra) {
            for (DTO_ChiTietSanPham imei : listImei) {
                tonggiaxuat += busChiTietPhieuNhap.getGiaNhap(imei.getMaphieunhap(), imei.getMaphienbansp());
            }
        }
        DTO_PhieuTra phieutra = new DTO_PhieuTra(maphieutra, currenTime, mancc, manv, tonggiaxuat, 1);
        int result = busPhieuTra.themPhieuTra(phieutra);
        if(result == 0){
            JOptionPane.showMessageDialog(null, "Thêm phiếu trả thất bại");
            return;
        }
        ///them chi tiet phieu tra tu jTable2

        model = (DefaultTableModel) jTable2.getModel();
        ArrayList<DTO_ChiTietPhieuTra> listCTPTra = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            int maphienban = Integer.parseInt(model.getValueAt(i, 1).toString());
            int soluongtra = Integer.parseInt(model.getValueAt(i, 5).toString());
            long tonggia = Long.parseLong(model.getValueAt(i, 6).toString());
            DTO_ChiTietPhieuTra ctptra = new DTO_ChiTietPhieuTra(maphieutra, maphienban, soluongtra, tonggia);
            //tru so luong ton bang so luong tra voi ma phien ban
            // JOptionPane.showMessageDialog(null, maphienban + " " + soluongtra);
            int kq = 0;
            kq = DAO_ChiTietCauHinh.getInstance().updateSoLuongTon(maphienban,
                    -(soluongtra));
            if (kq == 0) {
                JOptionPane.showMessageDialog(null, "Trừ số lượng tồn thất bại");
                return;
            }
            listCTPTra.add(ctptra);
            // tru so luong cau hinh san pham trong kho
            // busChiTietCauHinh.truSoLuongCauHinhSanPham(maphienban, soluongtra);
        }
        int result2 = busChiTietPhieuTra.themChiTietPhieuTra(listCTPTra);
        if (result2 == 0) {
            JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu trả thất bại");
            return;
        }

       
        ArrayList<DTO_PhieuTra> listPhieuTra = busPhieuTra.getAll();
        ap.loadTablePhieuTra(listPhieuTra);
        JOptionPane.showMessageDialog(null, "Thêm phiếu trả thành công");
        this.dispose();
    
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
        //lay tat ca ma phieu nhap cua nha cung cap do
     
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void txtSearchNCC3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNCC3FocusGained

    }//GEN-LAST:event_txtSearchNCC3FocusGained

    private void txtSearchNCC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchNCC3ActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtSearchNCC3ActionPerformed

    private void txtSearchNCC3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNCC3KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSearchNCC3KeyReleased

    private void myButton1NCC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1NCC3ActionPerformed
        // TODO add your handling code here:
        // reset
      
    }//GEN-LAST:event_myButton1NCC3ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //kiem tra so luong tra co vuot qua so luong ton
        //so luong tra ohai la so
        //   khong duoc trung ma phien ban vao phieu tra
        int maphienban = Integer.parseInt(jTextField5.getText());
        //kiem tra co trung ma phien ban voi duoi trong table chua
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            //khac null va bang int ma phien ban
            if (jTable2.getValueAt(i, 1) != null && Integer.parseInt(jTable2.getValueAt(i, 1).toString()) == maphienban) {
                JOptionPane.showMessageDialog(null, "Không được trùng mã phiên bản");
                return;
            }
        }
        
        if( jTextField10.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nhập số lượng trả");
            return;
        }
        int soluongtra = Integer.parseInt(jTextField10.getText());
        if (soluongtra < 1) {
            JOptionPane.showMessageDialog(null, "Số lượng trả phải lớn hơn 0");
            return;
        }
        
     
        if (listImeiDaChon.size() < 1) {
            JOptionPane.showMessageDialog(null, "Chưa chọn imei");
            return;
        }
        ArrayList<DTO_ChiTietPhieuNhap> listCTPN = busChiTietPhieuNhap.getAll();
        //tong tien khi listCTPN co ohien ban va ma phieu hap giong 1 phan tu listImeiDaChon
        long tongtien = 0;
        for (DTO_ChiTietPhieuNhap ctpn : listCTPN) {
            for (DTO_ChiTietSanPham imei : listImeiDaChon) {
                if (ctpn.getMaphienbansp() == imei.getMaphienbansp() && ctpn.getMaphieu() == imei.getMaphieunhap()) {
                    tongtien += ctpn.getDongia();
                }
            }
        }
        //Hay them tiep tuc phan tu vao listImeiDaChonVaoPhieuTra
        listImeiDaChonVaoPhieuTra.add(listImeiDaChon);
        // JOptionPane.showMessageDialog(null, listImeiDaChonVaoPhieuTra);
        //setenable nhacungcap
        jComboBox5.setEnabled(false);
      
        loadTablePhienBan(tongtien);
    }//GEN-LAST:event_jButton5ActionPerformed

    
    
    public void loadTablePhienBan(long tongtien){
        DTO_ChiTietCauHinh cauhinhsp = busChiTietCauHinh.getChiTietCauHinh(Integer.parseInt(jTextField5.getText()));
        int maphienban = Integer.parseInt(jTextField5.getText());
        int soluongtra = Integer.parseInt(jTextField10.getText());
     
        model = (DefaultTableModel) jTable2.getModel();
        if (flag == 0) {
            model.setRowCount(0);
            flag = 1;
        }
        model.addRow(new Object[]{
            model.getRowCount() + 1,
            maphienban,
            busProduct.getProductByID(cauhinhsp.getMasanpham()).getTensanpham(),
            cauhinhsp.getRam() + " GB",
            cauhinhsp.getRom() +  " GB",
                soluongtra,
                        tongtien
                    
            });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable2.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        jTable2.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
    }
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
            java.util.logging.Logger.getLogger(ThemPhieuTraDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuTraDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuTraDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuTraDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemPhieuTraDialog dialog = new ThemPhieuTraDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private com.raven.suportSwing.MyButton myButton1NCC3;
    private com.raven.suportSwing.TextField txtSearchNCC3;
    // End of variables declaration//GEN-END:variables
}
