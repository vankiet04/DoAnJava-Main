/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.bridj.cpp.std.list;

import BUS.BUS_ChiTietCauHinh;
import BUS.BUS_ChiTietSanPham;
import BUS.BUS_ChiTietSanPhamTra;
import BUS.BUS_Product;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietSanPham;
import DTO.DTO_ChiTietSanPhamTra;

/**
 *
 * @author KIET
 */
public class ChonImeiTra extends javax.swing.JDialog {

    /**
     * Creates new form ChonImeiTra
     */
   GUI.CRUD.ThemPhieuTraDialog themPhieuTraDialog;
    ArrayList<Integer> listmaphieunhap;
    int maphienban;
    int soluongtra;
    BUS_ChiTietSanPham busChiTietSanPham = new BUS_ChiTietSanPham();
    BUS_ChiTietCauHinh busChiTietCauHinh = new BUS_ChiTietCauHinh();
    BUS_Product busProduct = new BUS_Product();
    ArrayList<DTO_ChiTietSanPham> listImeiDaChon = new ArrayList<>();
    ArrayList<DTO_ChiTietSanPham> listImei;
    BUS_ChiTietSanPhamTra busChiTietSanPhamTra = new BUS_ChiTietSanPhamTra();   
    public ChonImeiTra(java.awt.Frame parent, boolean modal, GUI.CRUD.ThemPhieuTraDialog themPhieuTraDialog,
            int soluongtra, int maphienban, ArrayList<Integer> listmaphieunhap) {
        super(parent, modal);
        initComponents();
        this.themPhieuTraDialog = themPhieuTraDialog;
        this.listmaphieunhap = listmaphieunhap;
        this.maphienban = maphienban;
        this.soluongtra = soluongtra;
        DTO_ChiTietCauHinh phienbansanpham = busChiTietCauHinh.getByMaPhienBan(maphienban);
        jTextField5.setText(String.valueOf(maphienban));
        jTextField6.setText(busProduct.getsanphamtheomaphienban(phienbansanpham.getMasanpham()).getTensanpham());
        String cauhinh = phienbansanpham.getRam() + "GB Ram - " + phienbansanpham.getRom() + "GB Rom";
        jTextField9.setText(cauhinh);
        jTextField10.setText(String.valueOf(soluongtra));
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jTextField9.setEditable(false);
        jTextField10.setEditable(false);
        listImei = busChiTietSanPham.getAllImeiByMaphienbanVaListMaphieunhap(maphienban,
                listmaphieunhap);
        ArrayList<DTO_ChiTietSanPhamTra> listImeiTra = busChiTietSanPhamTra.getAll();
        for (int i = 0; i < listImeiTra.size(); i++) {
            for (int j = 0; j < listImei.size(); j++) {
                if (listImeiTra.get(i).getImei().equals(listImei.get(j).getImei())) {
                    listImei.remove(j);
                    j--; 
                }
            }
        }
        
        //listImeiDaChon la phan tu = spluongtra dau tien cua listImei
        if (soluongtra <= listImei.size()) {
            // listImeiDaChon là phần tử = số lượng trả đầu tiên của listImei
            for (int i = 0; i < soluongtra; i++) {
                listImeiDaChon.add(listImei.get(i));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Số lượng trả vượt quá kích thước của danh sách IMEI");
        }
        
        ArrayList<DTO_ChiTietSanPham> listImeiKhac = new ArrayList<>(listImei);
        // lấy phần tử khác với những phần tử của listImeiDaChon
        for (int i = 0; i < listImeiDaChon.size(); i++) {
            for (int j = 0; j < listImeiKhac.size(); j++) {
                if (listImeiDaChon.get(i).getImei().equals(listImeiKhac.get(j).getImei())) {
                    listImeiKhac.remove(j);
                    j--; // Giảm chỉ số j để không bỏ qua phần tử tiếp theo
                }
            }
        }

        loadTableImeiDaChon(listImeiDaChon);
        if(listImeiKhac.size() > 0) {
            loadTableImeiKhac(listImeiKhac);
        }


   

    }

    public ChonImeiTra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }
    
    public void loadTableImeiDaChon(ArrayList<DTO_ChiTietSanPham> listImeiDaChon) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (int i = 0; i < listImeiDaChon.size(); i++) {
            model.addRow(new Object[] {
                    i + 1,
                    listImeiDaChon.get(i).getImei()
            });
        }

    }
    
    public void loadTableImeiKhac(ArrayList<DTO_ChiTietSanPham> listImeiKhac) {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        for (int i = 0; i < listImeiKhac.size(); i++) {
            model.addRow(new Object[] {
                    i + 1,
                    listImeiKhac.get(i).getImei()
            });
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
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        txtSearchNCC3 = new com.raven.suportSwing.TextField();
        myButton1NCC3 = new com.raven.suportSwing.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        txtSearchNCC4 = new com.raven.suportSwing.TextField();
        myButton1NCC4 = new com.raven.suportSwing.MyButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

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

        myButton1NCC3.setText("Tim Imei");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "STT", "Imei (đã chọn)"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "STT", "Imei (Khác)"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        txtSearchNCC4.setLabelText("Tìm theo tên or mã");
        txtSearchNCC4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchNCC4FocusGained(evt);
            }
        });
        txtSearchNCC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchNCC4ActionPerformed(evt);
            }
        });
        txtSearchNCC4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchNCC4KeyReleased(evt);
            }
        });

        myButton1NCC4.setText("Tim Imei");
        myButton1NCC4.setBorderColor(new java.awt.Color(153, 255, 255));
        myButton1NCC4.setRadius(20);
        myButton1NCC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1NCC4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSearchNCC4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(myButton1NCC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchNCC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton1NCC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Chọn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên sản phẩm đã chọn:");

        jButton5.setText("Xác nhận");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã phiên bản :");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Phiên bản:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Sô lượng cần chọn:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Danh sách Imei khác:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Danh sách Imei đã chọn:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField9)
                    .addComponent(jLabel15)
                    .addComponent(jTextField10)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel10)
                        .addGap(320, 320, 320)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
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
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //xoa imei trong listImeiDaChon
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn IMEI cần xóa");
        } else {
            listImeiDaChon.remove(row);
            loadTableImeiDaChon(listImeiDaChon);
            ArrayList<DTO_ChiTietSanPham> listImeiKhac = listImei;
            for (int i = 0; i < listImeiDaChon.size(); i++) {
                for (int j = 0; j < listImeiKhac.size(); j++) {
                    if (listImeiDaChon.get(i).getImei().equals(listImeiKhac.get(j).getImei())) {
                        listImeiKhac.remove(j);
                        j--; // Giảm chỉ số j để không bỏ qua phần tử tiếp theo
                    }
                }
            }
            loadTableImeiKhac(listImeiKhac);
        }

        
    }
    
    //GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: 
        //Them Imei vao listImeiDaChon
        //khong duoc them qu so luong 
        if( listImeiDaChon.size() >= soluongtra) {
            JOptionPane.showMessageDialog(null, "Số lượng IMEI đã chọn đã đủ");
            return;
        }
        int row = jTable3.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn IMEI cần thêm");
        } else {
            listImeiDaChon.add(listImei.get(row));
            loadTableImeiDaChon(listImeiDaChon);
            ArrayList<DTO_ChiTietSanPham> listImeiKhac = listImei;
            for (int i = 0; i < listImeiDaChon.size(); i++) {
                for (int j = 0; j < listImeiKhac.size(); j++) {
                    if (listImeiDaChon.get(i).getImei().equals(listImeiKhac.get(j).getImei())) {
                        listImeiKhac.remove(j);
                        j--; // Giảm chỉ số j để không bỏ qua phần tử tiếp theo
                    }
                }
            }
            loadTableImeiKhac(listImeiKhac);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //khong duoc vuot qua so luong tra
        if (listImeiDaChon.size() > soluongtra) {
            JOptionPane.showMessageDialog(null, "Số lượng IMEI đã chọn vượt quá số lượng cần trả");
            return;
        }
        //khong du so luong
        if (listImeiDaChon.size() < soluongtra) {
            JOptionPane.showMessageDialog(null, "Số lượng IMEI đã chọn chưa đủ số lượng cần trả");
            return;
        }
        themPhieuTraDialog.listImeiDaChon = listImeiDaChon;
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void txtSearchNCC4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNCC4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchNCC4FocusGained

    private void txtSearchNCC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchNCC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchNCC4ActionPerformed

    private void txtSearchNCC4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNCC4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchNCC4KeyReleased

    private void myButton1NCC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1NCC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton1NCC4ActionPerformed

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
            java.util.logging.Logger.getLogger(ChonImeiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChonImeiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChonImeiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChonImeiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChonImeiTra dialog = new ChonImeiTra(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private com.raven.suportSwing.MyButton myButton1NCC3;
    private com.raven.suportSwing.MyButton myButton1NCC4;
    private com.raven.suportSwing.TextField txtSearchNCC3;
    private com.raven.suportSwing.TextField txtSearchNCC4;
    // End of variables declaration//GEN-END:variables
}
