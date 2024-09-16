/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.log.Logger;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BUS.BUS_Brand;
import BUS.BUS_ChiTietCauHinh;
import BUS.BUS_ChucNangNhomQuyen;
import BUS.BUS_Product;
import BUS.BUS_RamList;
import ConnectDB.JDBCUtil;
import DTO.DTO_Brand;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChucNangNhomQuyen;
import DTO.DTO_RamList;
import DTO.DTO_TaiKhoan;

/**
 *
 * @author Kiet
 */
public class QuanLyThuongHieu extends javax.swing.JDialog {

    /**
     * Creates new form ViewCauHinh
     */

    BUS_ChiTietCauHinh bus_ChiTietCauHinh = new BUS_ChiTietCauHinh();
    java.awt.Frame parent;
    String currentIDselected = "-1";
    BUS_Brand bus_brand = new BUS_Brand();
    ArrayList<DTO_Brand> listBrand = new ArrayList<>();

    GUI.MainProgram main;
    DTO_TaiKhoan user;
     BUS_ChucNangNhomQuyen busNQ = new BUS_ChucNangNhomQuyen();
     ArrayList<DTO_ChucNangNhomQuyen> listNQ = new ArrayList<>();

     public QuanLyThuongHieu(java.awt.Frame parent, boolean modal, GUI.MainProgram main, DTO_TaiKhoan user) {
         super(parent, modal);
         this.main = main;
         this.user = user;
         initComponents();

         this.parent = parent;
         listBrand = bus_brand.getAllData();
         filltablethuonghieu(listBrand);
         jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent event) {
                 if (jTable1.getSelectedRow() > -1) {
                     currentIDselected = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                 }
             }
         });

         loadChucNangNhomQuyen();
         jPanel3.setVisible(false);
         jPanel4.setVisible(false);
     }
    
     public void loadChucNangNhomQuyen() {
        listNQ = busNQ.selectAllChucNangNQ(user.getManhomquyen());
        for (DTO_ChucNangNhomQuyen iNQ : listNQ) {
            if (iNQ.getMachucnang().equals("thuoctinh") && iNQ.getHanhdong().equals("create")) {
                 jPanel2.setVisible(true);
            } 
            if (iNQ.getMachucnang().equals("thuoctinh") && iNQ.getHanhdong().equals("update")) {
                jPanel3.setVisible(true);
            } 
            if (iNQ.getMachucnang().equals("thuoctinh") && iNQ.getHanhdong().equals("delete")) {
                jPanel4.setVisible(true);
            } 
        }
    }
    public void filltablethuonghieu(ArrayList<DTO_Brand> listBrand) {
        // clear table
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);

        listBrand.forEach((dto) -> {
            ((DefaultTableModel) jTable1.getModel()).addRow(new Object[]{
                dto.getBrandName()
            });
        });

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tên thương hiệu"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel1.setText("Thêm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jLabel2.setText("Sửa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        jLabel3.setText("Xóa");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("QUẢN LÝ THƯƠNG HIỆU");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(328, 328, 328))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
       // them thuong hieu
       ThemThuongHieu themThuongHieu = new ThemThuongHieu(parent, true, this);
        themThuongHieu.setLocationRelativeTo(null);
        themThuongHieu.setVisible(true);
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt ) {//GEN-FIRST:event_jPanel3MousePressed
        // sua thuong hieu
        if (currentIDselected.equals("-1")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn RAM cần sửa");
            return;
        }
        SuaThuongHieu suaThuongHieu = new SuaThuongHieu(parent, true, currentIDselected, this);
        suaThuongHieu.setLocationRelativeTo(null);
        suaThuongHieu.setVisible(true);
        
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        //xóa thương hiêuk
        if (currentIDselected.equals("-1")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn RAM cần xóa");
            return;
        }
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa thương hiệu này?",
                "Xác nhận xóa", dialogButton);
                currentIDselected = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        if (dialogResult == 0) {
            String tenth = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            int result = bus_brand.deleteBrand(tenth);
            listBrand = bus_brand.getAllData();
            filltablethuonghieu(listBrand);
            if(result == 1) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }

    }//GEN-LAST:event_jPanel4MousePressed

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
            java.util.logging.Logger.getLogger(QuanLyThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
