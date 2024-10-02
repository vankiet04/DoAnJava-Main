
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Menu;

import BUS.BUS_Brand;
import BUS.BUS_ChiTietCauHinh;
import BUS.BUS_ChucNangNhomQuyen;
import BUS.BUS_Product;

import DAO.DAO_Product;
import DTO.DTO_Brand;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChucNangNhomQuyen;
import DTO.DTO_Product;
import DTO.DTO_TaiKhoan;
import GUI.CRUD.AddProducts;
import GUI.CRUD.EditProduct;
import GUI.CRUD.ViewCauHinh;
import GUI.CRUD.XemChiTietSanPham;

import com.formdev.flatlaf.FlatLightLaf;
//import static com.microsoft.schemas.office.excel.STObjectType.Enum.table;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





/**
 *
 * @author kiet
 */
public final class FormItems extends javax.swing.JPanel {

    public BUS_Product productBUS = new BUS_Product();
    public ArrayList<DTO_Product> listProducts = productBUS.getAllData();
    public BUS_Brand brandBUS = new BUS_Brand();
    public BUS_ChiTietCauHinh chitietcauhinhBUS = new BUS_ChiTietCauHinh();
    public ArrayList<DTO_Brand> listBrands = brandBUS.getAllData();
    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
    int currentIDselected = -1; // khi người dùng ấn vào 1 row trong table và ấn chỉnh sửa, ta sẽ dựa vào ID sản phẩm
    // của row đó để hiển thị thông tin của sản phẩm đó
    

    GUI.MainProgram main;
    DTO_TaiKhoan user;
     BUS_ChucNangNhomQuyen busNQ = new BUS_ChucNangNhomQuyen();
     ArrayList<DTO_ChucNangNhomQuyen> listNQ = new ArrayList<>();
    DefaultTableModel model;

    public FormItems(GUI.MainProgram main, DTO_TaiKhoan user) {
        this.main = main;
        this.user = user;
        initComponents();
        txtSearch.setLabelText("Tìm kiếm theo tên sản phẩm:");
        FillTable(listProducts);


        fillComboboxBrand(listBrands);

        // add event click
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) { // Đảm bảo rằng có hàng được chọn
                        currentIDselected = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
                    }
                }
            }
        });
        jLabel2.setEnabled(false);
        jLabel2.setFocusable(false);
        jLabel2.setVisible(false);
        jLabel1.setEnabled(false);
        jLabel1.setFocusable(false);
        jLabel1.setVisible(false);
        jLabel4.setEnabled(false);
        jLabel4.setFocusable(false);
        jLabel4.setVisible(false);
        jLabel7.setEnabled(false);
        jLabel7.setFocusable(false);
        jLabel7.setVisible(false);

        jLabel5.setVisible(false);
        jLabel6.setVisible(false);

        loadChucNangNhomQuyen();
    }

    public void loadChucNangNhomQuyen() {
        // JOptionPane. showMessageDialog(null,"kkk"+ user.getManhomquyen());
        listNQ = busNQ.selectAllChucNangNQ(user.getManhomquyen());

        for (DTO_ChucNangNhomQuyen iNQ : listNQ) {
            if (iNQ.getMachucnang().equals("sanpham") && iNQ.getHanhdong().equals("create")) {
                jLabel2.setEnabled(true);
                jLabel2.setFocusable(true);
                jLabel2.setVisible(true);
            }
            if (iNQ.getMachucnang().equals("sanpham") && iNQ.getHanhdong().equals("update")) {
                jLabel1.setEnabled(true);
                jLabel1.setFocusable(true);
                jLabel1.setVisible(true);
                jLabel5.setVisible(true);

            }
            if (iNQ.getMachucnang().equals("sanpham") && iNQ.getHanhdong().equals("delete")) {
                jLabel4.setEnabled(true);
                jLabel4.setFocusable(true);
                jLabel4.setVisible(true);
                jLabel6.setVisible(true);

            }
            if (iNQ.getMachucnang().equals("sanpham") && iNQ.getHanhdong().equals("view")) {
                jLabel7.setEnabled(true);
                jLabel7.setFocusable(true);
                jLabel7.setVisible(true);

            }
        }
    }
    

  
     public void FillTable(ArrayList<DTO_Product> result) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (DTO_Product sp : result) {
            model.addRow(new Object[]{sp.getMasanpham(), sp.getTensanpham(), 
                sp.getBoxuly(), sp.getHedieuhanh(), sp.getThuonghieu(), sp.getThoigianbaohanh() + " năm"
            });
        } 
        

        //CAN GIUA
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }  
    }
     
    public void fillComboboxBrand(ArrayList<DTO_Brand> result) {
        // DefaultComboBoxModel cbModel = (DefaultComboBoxModel) jComboBox1.getModel();

        // for (DTO_Brand combobox : result) {
        //     cbModel.addElement(combobox.getBrandName());
        // }
    }

    public void exportJTableToExceql() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn lưu file Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX files", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
    
        int userChoice = fileChooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            model = (DefaultTableModel) jTable1.getModel();

            Workbook workbook = new XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet();

            // Create header row
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
            
            headerRow.createCell(0).setCellValue("Mã sản phẩm");
            headerRow.createCell(1).setCellValue("Tên sản phẩm");
            headerRow.createCell(2).setCellValue("Bộ xử lý");
            headerRow.createCell(3).setCellValue("Hệ điều hành");
            headerRow.createCell(4).setCellValue("Thương hiệu");

            int columnWidth = 30 * 256; // 50 characters wide
            for (int i = 0; i < 5; i++) {
                sheet.setColumnWidth(i, columnWidth);
            }
            // Create data rows
            for (int i = 0; i < model.getRowCount(); i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(model.getValueAt(i, 0).toString());
                row.createCell(1).setCellValue(model.getValueAt(i, 1).toString());
                row.createCell(2).setCellValue(model.getValueAt(i, 2).toString());
                row.createCell(3).setCellValue(model.getValueAt(i, 3).toString());
                row.createCell(4).setCellValue(model.getValueAt(i, 4).toString());
            }
            

            // Write to file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                // Show success message
                JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    } 
   


private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {
    // TODO add your handling code here:
    int index = jTable1.getSelectedRow();
    if (index == -1) {
        JOptionPane.showMessageDialog(parent, "Vui lòng chọn sản phẩm để xem chi tiết", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        return;
    }
    XemChiTietSanPham xem = new XemChiTietSanPham(parent, true, currentIDselected, this);

    xem.setLocationRelativeTo(null);
    xem.setVisible(true);
    xem.setResizable(false);

}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new com.raven.suportSwing.TextField();
        myButton2 = new com.raven.suportSwing.MyButton();
        lblSearch = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        scrollBarCustom1 = new com.raven.suportSwing.ScrollBarCustom();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setLabelText("Tìm theo tên or mã");
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        myButton2.setBackground(new java.awt.Color(204, 255, 255));
        myButton2.setText("Reset");
        myButton2.setBorderColor(new java.awt.Color(153, 255, 255));
        myButton2.setRadius(20);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        lblSearch.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(255, 51, 0));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-75.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-85.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel3.setText("Thêm");

        jLabel5.setText("Sửa");

        jLabel6.setText("Xóa");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-view-75.png"))); // NOI18N

        jLabel8.setText("Xem chi tiết");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-75.png"))); // NOI18N

        jLabel13.setText("Cài đặt phiên bản sản phẩm");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-config-75.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel15MousePressed(evt);
            }
        });

        jButton2.setText("Xuất");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-edit-75.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel15)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel15))))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addComponent(jButton2)
                        .addComponent(jLabel13)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Bộ xử lý", "Hệ điều hành", "Thương hiệu", "Thời gian bảo hành"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 238, Short.MAX_VALUE)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(363, 363, 363))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(1714, Short.MAX_VALUE)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, 5, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        // TODO add your handling code here:
        //reset
        txtSearch.setText("");
        lblSearch.setText("");
        FillTable(productBUS.getAllData());

    }//GEN-LAST:event_myButton2ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String search = txtSearch.getText();
        search= search.trim();
        if( search.contains("%"))
            search= search.replace("%", "!");
        if (search.equals("")) {
            lblSearch.setText("Vui lòng nhập từ khóa để tìm kiếm");
            FillTable(productBUS.getAllData());
        } else {
            ArrayList<DTO_Product> result = productBUS.search(search);
            if (result.size() > 0) {
                lblSearch.setText("Tìm thấy " + result.size() + " kết quả");
                FillTable(result);
            } else {
                lblSearch.setText("Không tìm thấy kết quả");
                FillTable(productBUS.getAllData());
            }
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained

    }//GEN-LAST:event_txtSearchFocusGained

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        AddProducts add = new AddProducts(parent, true, this);
        add.setLocationRelativeTo(null); 
        add.setVisible(true);
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // Thêm sự kiện lắng nghe sự kiện chọn hàng của JTable
        if (currentIDselected == -1) {
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn sản phẩm để chỉnh sửa", "Cảnh báo!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // kiểm tra xem sản phẩm này có trong chi tiết cấu hình không
        if (chitietcauhinhBUS.getSoLuong(currentIDselected) > 0) {
            JOptionPane.showMessageDialog(parent, "Không thể chỉnh sửa vì trong kho đang hiện có: " + chitietcauhinhBUS.getSoLuong(currentIDselected) + " sản phẩm này!", "Cảnh báo!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        EditProduct add = new EditProduct(parent, true, currentIDselected, this);
        add.setLocationRelativeTo(null); 
        add.setVisible(true);
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        // this func to delete product
        if (currentIDselected == -1) {
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn sản phẩm để xóa", "Cảnh báo!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int masp = currentIDselected;
        // kiểm tra xem sản phẩm này có trong chi tiết cấu hình không
        if (chitietcauhinhBUS.getSoLuong(masp) > 0) {
            JOptionPane.showMessageDialog(parent, "Không thể xóa vì trong kho đang hiện có: " + chitietcauhinhBUS.getSoLuong(masp) + " sản phẩm này!", "Cảnh báo!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // ban co chan chan xoa khong
        int option = JOptionPane.showConfirmDialog(parent, "Bạn có chắc chắn muốn xóa sản phẩm này không?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.NO_OPTION) {
            return;
        }
        else if(option == JOptionPane.CLOSED_OPTION){
            return;
        }
                
        productBUS.delete(currentIDselected);
        JOptionPane.showMessageDialog(parent, "Xóa sản phẩm thành công", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
        FillTable(productBUS.getAllData());
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed
        int index = jTable1.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn sản phẩm để chỉnh sửa cấu hình", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ViewCauHinh vch = new ViewCauHinh(parent, true, currentIDselected);
        vch.setLocationRelativeTo(null);
        vch.setVisible(true);
        vch.setResizable(false);
    }//GEN-LAST:event_jLabel15MousePressed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         exportJTableToExceql();

    }//GEN-LAST:event_jButton2ActionPerformed

                                 



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblSearch;
    private com.raven.suportSwing.MyButton myButton2;
    private com.raven.suportSwing.ScrollBarCustom scrollBarCustom1;
    private com.raven.suportSwing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
