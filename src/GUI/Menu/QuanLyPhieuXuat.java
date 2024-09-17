/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Menu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.formdev.flatlaf.json.ParseException;


import BUS.BUS_ChucNangNhomQuyen;
import BUS.BUS_KhachHang;
import BUS.BUS_NhanVien;
import BUS.BUS_PhieuXuat;
import BUS.BUS_Product;
import DTO.DTO_ChucNangNhomQuyen;
import DTO.DTO_PhieuXuat;
import DTO.DTO_Product;
import DTO.DTO_TaiKhoan;
import GUI.CRUD.HuyHoaDon;
import GUI.CRUD.ThemPhieuXuat;
import GUI.CRUD.XemChiTietPhieuXuatDialog;
// import them phieu nhap hang
import GUI.CRUD.ThemPhieuTraHang;


/**
 *
 * @author Admin
 */
public class QuanLyPhieuXuat extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyPhieuNhap
     */
    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
    BUS_Product bus = new BUS_Product();
    GUI.MainProgram main;
    ArrayList<DTO_Product> listProduct = bus.getAllData();
    DTO_TaiKhoan user;
    BUS_KhachHang buskh = new BUS_KhachHang();
    BUS_NhanVien busnv = new BUS_NhanVien();
    BUS_ChucNangNhomQuyen busNQ = new BUS_ChucNangNhomQuyen();
    ArrayList<DTO_ChucNangNhomQuyen> listNQ = new ArrayList<>();
    ArrayList<DTO_PhieuXuat> listPhieu = new ArrayList<>();
    BUS_PhieuXuat phieuxuatBUS = new BUS_PhieuXuat();
    DefaultTableModel model;


    int currentIDselected = -1;

    public QuanLyPhieuXuat(GUI.MainProgram main, DTO_TaiKhoan user) {
        this.main = main;
        this.user = user;
        initComponents();
        loadComboSearch();
        onChangeSearch();
        
        jTextField3.setText("0");
        jTextField4.setText("1000000000");
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        jLabel1NCC.setEnabled(false);
        jLabel2NCC.setEnabled(false);
        jLabel3NCC.setEnabled(false);
        jLabel4NCC.setEnabled(false);
        loadChucNangNhomQuyen();
        

        // system list phieu
        loadtablepx();
        jPanel6.setVisible(false);
    }
    public void onChangeSearch() {
        jComboBox1.addActionListener((e) -> {
            try {
                Fillter();
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        jComboBox2.addActionListener((e) -> {
            try {
                Fillter();
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        jComboBoxNCC.addActionListener((e) -> {
            try {
                Fillter();
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    Fillter();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
                    Fillter();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
                    Fillter();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    Fillter();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    Fillter();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
         
    }

    public void loadComboSearch() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Tất cả");
        for (int i = 0; i < buskh.getAllData().size(); i++) {
            jComboBox1.addItem(buskh.getAllData().get(i).getHoTen());
        }

        jComboBox2.removeAllItems();
        jComboBox2.addItem("Tất cả");
        for (int i = 0; i < busnv.getAllData().size(); i++) {
            jComboBox2.addItem(busnv.getAllData().get(i).getHoten());
        }

        jComboBoxNCC.removeAllItems();
        jComboBoxNCC.addItem("Tất cả");
        jComboBoxNCC.addItem("Mã phiếu nhập");
        jComboBoxNCC.addItem("Tên khach hang");
        jComboBoxNCC.addItem("Tên nhân viên");
    }

    public void Fillter() throws ParseException {
        // JOptionPane.showMessageDialog(null, "Fillter");
        int type = jComboBoxNCC.getSelectedIndex();
        String makh = jComboBox1.getSelectedIndex() == 0 ? "0"
                : buskh.getByIndex(jComboBox1.getSelectedIndex() - 1).getMaKhachHang();
        int manv = jComboBox2.getSelectedIndex() == 0 ? 0
                : busnv.getByIndex(jComboBox2.getSelectedIndex() - 1).getManv();
        String input = jTextField1.getText() != null ? jTextField1.getText() : "";
        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser2.setDateFormatString("dd/MM/yyyy");
        java.util.Date ngaybatdau = jDateChooser1.getDate();
        java.util.Date ngayketthuc = jDateChooser2.getDate();
        if (ngaybatdau != null && ngayketthuc != null && ngaybatdau.after(ngayketthuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!jTextField3.getText().matches("[0-9]+") && jTextField3.getText() != "") {
            // JOptionPane.showMessageDialog(this, "Vui lòng nhập số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;

        }
        if (!jTextField4.getText().matches("[0-9]+") && jTextField4.getText() != "") {
            // JOptionPane.showMessageDialog(this, "Vui lòng nhập số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;

        }

        long min_price = Long.parseLong(jTextField3.getText());
        long max_price = Long.parseLong(jTextField4.getText());
        if(min_price > max_price){
            JOptionPane.showMessageDialog(this, "Giá trị tối thiểu không được lớn hơn giá trị tối đa", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int makhInt = Integer.parseInt(makh);

        this.listPhieu = phieuxuatBUS.fillerphieuXuatCoNgay(type, input, makhInt, manv, ngaybatdau, ngayketthuc, min_price, max_price);
        loadtablepx();
    }

    public void exportJTableToExcel() {
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

        model = (DefaultTableModel) jTable2.getModel();

        Workbook workbook = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet();

        // Create header row
        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã phiếu");
        headerRow.createCell(1).setCellValue("Thời gian tạo");
        headerRow.createCell(2).setCellValue("Tên khách hàng");
        headerRow.createCell(3).setCellValue("Tên người tạo");
        headerRow.createCell(4).setCellValue("Tổng tiền");

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

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        
        }
    }
    }

    public void loadChucNangNhomQuyen() {
        // JOptionPane. showMessageDialog(null,"kkk"+ user.getManhomquyen());
        listNQ = busNQ.selectAllChucNangNQ(user.getManhomquyen());

        for (DTO_ChucNangNhomQuyen iNQ : listNQ) {
            if (iNQ.getMachucnang().equals("xuathang") && iNQ.getHanhdong().equals("create")) {
                jButton4.setEnabled(true);
                jLabel1NCC.setEnabled(true);
      
            } 
            if (iNQ.getMachucnang().equals("xuathang") && iNQ.getHanhdong().equals("update")) {
                jButton2.setEnabled(true);
                jLabel2NCC.setEnabled(true);

            }
            if (iNQ.getMachucnang().equals("xuathang") && iNQ.getHanhdong().equals("delete")) {
                jButton3.setEnabled(true);
                jLabel3NCC.setEnabled(true);

            } 
            if (iNQ.getMachucnang().equals("xuathang") && iNQ.getHanhdong().equals("view")) {
                jButton5.setEnabled(true);
                jLabel4NCC.setEnabled(true);

            } 
        }
    }

    public void loadtablepx() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        ArrayList<DTO_PhieuXuat> listProduct = phieuxuatBUS.getAll();
        //reverse list
     
        for (DTO_PhieuXuat sp : listProduct) {
            model.addRow(new Object[] {
                    sp.getMaphieuxuat(),
                    sp.getThoigian(),
                    buskh.getkhtheoid(sp.getIdkhachhang()).getHoTen(),
                    busnv.getNvtheoid(sp.getIdnhanvien()).getHoten(),
                    toVND(sp.getTongtien())
            });

        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // add event click
        jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable2.getSelectedRow();
                    if (selectedRow != -1) {
                        currentIDselected = Integer.parseInt(jTable2.getValueAt(selectedRow, 0).toString());
  
                    }
                }
            }
        });
    }

    // public void loadtablepx2(ArrayList<DTO_PhieuXuat> list) {
    //     DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
    //     model.setRowCount(0);
    //     for (DTO_PhieuXuat sp : list) {
    //         model.addRow(new Object[] {
    //                 sp.getMaphieuxuat(),
    //                 sp.getThoigian(),
    //                 buskh.getkhtheoid(sp.getIdkhachhang()).getHoTen(),
    //                 busnv.getNvtheoid(sp.getIdnhanvien()).getHoten(),
    //                 toVND(sp.getTongtien())
    //         });
    //     }
    //     DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    //     centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    //     for (int i = 0; i < jTable2.getColumnCount(); i++) {
    //         jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    //     }
        
    //     jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    //         @Override
    //         public void valueChanged(ListSelectionEvent e) {
    //             if (!e.getValueIsAdjusting()) {
    //                 int selectedRow = jTable2.getSelectedRow();
    //                 if (selectedRow != -1) {
    //                     currentIDselected = Integer.parseInt(jTable2.getValueAt(selectedRow, 0).toString());
     
    //                 }
    //             }
    //         }
    //     });
    // }
   
    public String toVND(long x) {
        return String.format("%,d", x) + " VND";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel19 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel20 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jComboBoxNCC = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel2NCC = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3NCC = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel4NCC = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel6NCC = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel1NCC = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel4NCC1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(3200, 600));

        jPanel13.setBackground(new java.awt.Color(255, 204, 255));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(1300, 1220));
        jPanel22.setRequestFocusEnabled(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        jPanel22.setLayout(flowLayout1);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(210, 450));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nhà cung cấp");
        jPanel16.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(72, 30));
        jPanel16.add(jComboBox1, java.awt.BorderLayout.PAGE_END);

        jPanel15.add(jPanel16);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nhân viên nhập");
        jPanel17.add(jLabel2, java.awt.BorderLayout.CENTER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(72, 30));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel17.add(jComboBox2, java.awt.BorderLayout.PAGE_END);

        jPanel15.add(jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Từ ngày");
        jPanel18.add(jLabel3, java.awt.BorderLayout.PAGE_START);
        jPanel18.add(jDateChooser1, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel18);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Đến ngày");
        jPanel19.add(jLabel4, java.awt.BorderLayout.PAGE_START);
        jPanel19.add(jDateChooser2, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel19);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Từ số tiền (VNĐ)");
        jPanel20.add(jLabel5, java.awt.BorderLayout.CENTER);

        jTextField3.setPreferredSize(new java.awt.Dimension(71, 30));
        jPanel20.add(jTextField3, java.awt.BorderLayout.PAGE_END);

        jPanel15.add(jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel21.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Đến số tiền (VNĐ)");
        jPanel21.add(jLabel6, java.awt.BorderLayout.CENTER);

        jTextField4.setPreferredSize(new java.awt.Dimension(71, 30));
        jPanel21.add(jTextField4, java.awt.BorderLayout.PAGE_END);

        jPanel15.add(jPanel21);

        jPanel22.add(jPanel15);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 450));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu xuất", "Thời gian tạo", "Khách hàng", "Nhân viên tạo", "Tổng tiền"
            }
        ));
        // jTable2.setPreferredSize(new java.awt.Dimension(1250, 81));
        jScrollPane1.setViewportView(jTable2);

        jPanel22.add(jScrollPane1);

        jPanel13.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jComboBoxNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lọc theo hãng" }));
        jComboBoxNCC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jComboBoxNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNCCActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setText("Sua");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-edit-75.png"))); // NOI18N
        jLabel2NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2NCCMousePressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jTextField1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(494, 494, 494)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel2NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setText("Xoa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-85.png"))); // NOI18N
        jLabel3NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3NCCMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setText("Xem");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-view-75.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton5))
                    .addComponent(jLabel4NCC))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel4NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton7.setText("Xuất");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-75.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addComponent(jLabel6NCC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setText("Thêm");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1NCC.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-75.png"))); // NOI18N
        jLabel1NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1NCCMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1NCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel1NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton4))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setText("Hủy");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4NCC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-85.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton6))
                    .addComponent(jLabel4NCC1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4NCC1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton8.setText("Trả hàng về nhà cung cấp");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton8MousePressed(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))))
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton8))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel13.add(jPanel12, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1864, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jLabel1NCCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1NCCMousePressed
        // TODO add your handling code here:
        //        AddProducts add = new AddProducts(parent, true, this);
        //        add.setLocationRelativeTo(null);
        //        add.setVisible(true);
    }//GEN-LAST:event_jLabel1NCCMousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        // System.out.println("Button 4 được nhấn!");
        // ThemNhaCungCap dialog = new ThemNhaCungCap(new javax.swing.JFrame(), true, this);
        // dialog.setLocationRelativeTo(null);
        // dialog.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (currentIDselected == -1) {
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn phiếu xuất để xem", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        XemChiTietPhieuXuatDialog xem = new XemChiTietPhieuXuatDialog(parent, true, currentIDselected);
        xem.setLocationRelativeTo(null);
        xem.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void myButton1NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1NCCActionPerformed
        // TODO add your handling code here:
        //reset
        jTextField1.setText("");
        jComboBoxNCC.setSelectedIndex(0);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextField3.setText("0");
        jTextField4.setText("100000000");
 
    }//GEN-LAST:event_myButton1NCCActionPerformed

    private void jComboBoxNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNCCActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained

    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        // TODO add your handling code here: them phieu xuát
        ThemPhieuXuat themPhieuXuat = new ThemPhieuXuat(parent, true, user, this);
        themPhieuXuat.setLocationRelativeTo(null);
        themPhieuXuat.setVisible(true);
    }//GEN-LAST:event_jButton4MousePressed

    private void jLabel3NCCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3NCCMousePressed
        // this func to delete product
        //        if (currentIDselected == -1) {
            //            JOptionPane.showMessageDialog(parent, "Vui lòng chọn sản phẩm để xóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            //            return;
            //        }
        //        productBUS.delete(currentIDselected);
        //        JOptionPane.showMessageDialog(parent, "Xóa sản phẩm thành công", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
        //        FillTable(productBUS.getAllData());
    }//GEN-LAST:event_jLabel3NCCMousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: Xoa

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        exportJTableToExcel();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel2NCCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2NCCMousePressed
        // Thêm sự kiện lắng nghe sự kiện chọn hàng của JTable
        //        if (currentIDselected == -1) {
            //            JOptionPane.showMessageDialog(parent, "Vui lòng chọn sản phẩm để chỉnh sửa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            //            return;
            //        }
        //        EditProduct add = new EditProduct(parent, true, currentIDselected);
        //        add.setLocationRelativeTo(null);
        //        add.setVisible(true);
    }//GEN-LAST:event_jLabel2NCCMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        // TODO add your handling code here: huy hoa don
        if (currentIDselected == -1) {
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn phiếu xuất để hủy", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            return;
        }


        HuyHoaDon huy = new HuyHoaDon(parent, true, currentIDselected, user.getManv());
        huy.setLocationRelativeTo(null);
        huy.setVisible(true);
    }//GEN-LAST:event_jButton6MousePressed

    private void jButton8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MousePressed
        // TODO add your handling code here: tra hang
        ThemPhieuTraHang themPhieuTraHang = new ThemPhieuTraHang(parent, true, user, this);
        themPhieuTraHang.setLocationRelativeTo(null);
        themPhieuTraHang.setVisible(true);
    }//GEN-LAST:event_jButton8MousePressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBoxNCC;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1NCC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel2NCC;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel3NCC;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel4NCC;
    private javax.swing.JLabel jLabel4NCC1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel6NCC;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}