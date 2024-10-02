/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Menu;

import BUS.BUS_ChiTietSanPham;
import BUS.BUS_ChucNangNhomQuyen;
import BUS.BUS_NhaCungCap;
import BUS.BUS_NhanVien;
import BUS.BUS_PhieuNhap;
import DTO.DTO_ChiTietSanPham;
import DTO.DTO_ChucNangNhomQuyen;
import DTO.DTO_Phieu;
import DTO.DTO_PhieuNhap;
import DTO.DTO_TaiKhoan;
import GUI.CRUD.ThemPhieuNhap;
import GUI.CRUD.ThemPhieuNhapDialog;
import GUI.CRUD.XemChiTietPhieuNhapDialog;
import helper.Formater;

import java.sql.Timestamp;
import java.beans.PropertyChangeEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.formdev.flatlaf.json.ParseException;

/**
 *
 * @author Admin
 */
public class QuanLyPhieuNhap extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyPhieuNhap
     */
    BUS_PhieuNhap phieunhapBUS = new BUS_PhieuNhap();
    BUS_NhaCungCap nccBUS = new BUS_NhaCungCap();
    BUS_NhanVien nvBUS = new BUS_NhanVien();
    BUS_ChiTietSanPham ctspBUS = new BUS_ChiTietSanPham();
    ArrayList<DTO_PhieuNhap> listPhieu;
    DefaultTableModel model;

    GUI.MainProgram main;
    DTO_TaiKhoan user;
     BUS_ChucNangNhomQuyen busNQ = new BUS_ChucNangNhomQuyen();
     ArrayList<DTO_ChucNangNhomQuyen> listNQ = new ArrayList<>();
     BUS_ChiTietSanPham busCTSP = new BUS_ChiTietSanPham();
     public QuanLyPhieuNhap() {

         initComponents();
        //  loadTable();
        loadComboSearch();
        onChangeSearch();

        listPhieu = phieunhapBUS.getAll();
        loadDataTalbe(listPhieu);

    }

    public QuanLyPhieuNhap(GUI.MainProgram main, DTO_TaiKhoan user) {
        this.main = main;
        this.user = user;
        initComponents();
        // loadTable();
        listPhieu = phieunhapBUS.getAll();
        loadComboSearch();
        onChangeSearch();
        loadDataTalbe(listPhieu);
        jTextField3.setText("0");
        jTextField4.setText("100000000000");
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        jButton3.setEnabled(false);

        jLabel1NCC.setEnabled(false);
        jLabel3NCC.setEnabled(false);
        jLabel4NCC.setEnabled(false);
        loadChucNangNhomQuyen();

    }
    public void loadChucNangNhomQuyen() {
        // JOptionPane. showMessageDialog(null,"kkk"+ user.getManhomquyen());
        listNQ = busNQ.selectAllChucNangNQ(user.getManhomquyen());

        for (DTO_ChucNangNhomQuyen iNQ : listNQ) {
            if (iNQ.getMachucnang().equals("nhaphang") && iNQ.getHanhdong().equals("create")) {
                jButton4.setEnabled(true);
                jLabel1NCC.setEnabled(true);
            } 
            if (iNQ.getMachucnang().equals("nhaphang") && iNQ.getHanhdong().equals("delete")) {
                jButton3.setEnabled(true);
                jLabel3NCC.setEnabled(true);
            } 
            if (iNQ.getMachucnang().equals("nhaphang") && iNQ.getHanhdong().equals("view")) {
                jButton5.setEnabled(true);
                jLabel4NCC.setEnabled(true);
            }
        }
    }

    // onChangeSearch
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
        txtSearchNCC.addKeyListener(new java.awt.event.KeyAdapter() {
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
    // loadComboSearch
    public void loadComboSearch() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Tất cả");
        for (int i = 0; i < nccBUS.getAllData().size(); i++) {
            jComboBox1.addItem(nccBUS.getAllData().get(i).getTenncc());
        }

        jComboBox2.removeAllItems();
        jComboBox2.addItem("Tất cả");
        for (int i = 0; i < nvBUS.getAllData().size(); i++) {
            jComboBox2.addItem(nvBUS.getAllData().get(i).getHoten());
        }

        jComboBoxNCC.removeAllItems();
        jComboBoxNCC.addItem("Tất cả");
        jComboBoxNCC.addItem("Mã phiếu nhập");
        jComboBoxNCC.addItem("Tên nhà cung cấp");
        jComboBoxNCC.addItem("Tên nhân viên");
    }

    public void resetAll(){
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        txtSearchNCC.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextField3.setText("0");
        jTextField4.setText("1000000000");
        this.listPhieu = phieunhapBUS.getAll();
        loadDataTalbe(listPhieu);
    }

    public void Fillter() throws ParseException {
        int type = jComboBoxNCC.getSelectedIndex();
        int mancc = jComboBox1.getSelectedIndex() == 0 ? 0
                : nccBUS.getByIndex(jComboBox1.getSelectedIndex() - 1).getMancc();
        int manv = jComboBox2.getSelectedIndex() == 0 ? 0
                : nvBUS.getByIndex(jComboBox2.getSelectedIndex() - 1).getManv();
        String input = txtSearchNCC.getText() != null ? txtSearchNCC.getText() : "";
        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser2.setDateFormatString("dd/MM/yyyy");
        java.util.Date ngaybatdau = jDateChooser1.getDate();
        java.util.Date ngayketthuc = jDateChooser2.getDate();
        if (ngaybatdau != null && ngayketthuc != null && ngaybatdau.after(ngayketthuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
   
        
        int check = 0;
        // Kiểm tra jTextField3
        if (!jTextField3.getText().matches("[0-9]+") && !jTextField3.getText().equals("")) {
            jTextField3.setText("0");
            JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập số và số không âm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            check += 1;
        }

        // Kiểm tra jTextField4
        if (!jTextField4.getText().matches("[0-9]+") && !jTextField3.getText().equals("")) {
            jTextField4.setText("1000000000");
            JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập số và số không âm ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            check += 1;
        }

        if (check != 0) {
            return;
        }   
        
        if((jTextField3.getText().trim().equals("")) && (jTextField4.getText().trim().equals(""))){
            jTextField3.setText("0");    
            jTextField4.setText("1000000000");
            JOptionPane.showMessageDialog(this, "Cần nhập đủ 2 trường giá để lọc", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

       
        long min_price = jTextField3.getText().trim().equals("") ? 0 : Long.parseLong(jTextField3.getText());
        long max_price = jTextField4.getText().trim().equals("") ? 1000000000 : Long.parseLong(jTextField4.getText());
        if(min_price > max_price){
            JOptionPane.showMessageDialog(this, "Giá trị tối thiểu không được lớn hơn giá trị tối đa", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc"+ ngaybatdau + ngayketthuc, "Lỗi",
        //             JOptionPane.ERROR_MESSAGE);
        

   

        // Date time_start = jDateChooser1.getDate() != null ? new Date(jDateChooser1.getDate().getTime()) : null;
        // Date time_end = jDateChooser2.getDate() != null ? new Date(jDateChooser2.getDate().getTime()) : null;
        // String min_price = jTextField3.getText();
        // String max_price = jTextField4.getText();
        this.listPhieu = phieunhapBUS.fillterPhieuNhapCoNgay(type, input, mancc, manv, ngaybatdau, ngayketthuc, min_price, max_price);
        // neu tat ca deu chua gia tri thi khong load 
        if (jComboBox1.getSelectedIndex() == 0 && jComboBox2.getSelectedIndex() == 0 && txtSearchNCC.getText().equals("") && jDateChooser1.getDate() == null && jDateChooser2.getDate() == null && jTextField3.getText().equals("0") && jTextField4.getText().equals("100000000000")) {
            return;
        }
        loadDataTalbe(listPhieu);
    }
    

    public void resetForm() {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        txtSearchNCC.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextField3.setText("");
        jTextField4.setText("");
        this.listPhieu = phieunhapBUS.getAll();
        loadDataTalbe(listPhieu);
    }
    
    public boolean validateSelectDate() throws ParseException {
        Date time_start = jDateChooser1.getDate() != null ? new Date(jDateChooser1.getDate().getTime()) : null;
        Date time_end = jDateChooser2.getDate() != null ? new Date(jDateChooser2.getDate().getTime()) : null;   
        // if(time_start < time_end){
        //     JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
        //     return false;
        // }

        // Date current_date = new Date();
        // if (time_start != null && time_start.after(current_date)) {
        //     JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
        //     dateStart.getDateChooser().setCalendar(null);
        //     return false;
        // }
        // if (time_end != null && time_end.after(current_date)) {
        //     JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
        //     dateEnd.getDateChooser().setCalendar(null);
        //     return false;
        // }
        // if (time_start != null && time_end != null && time_start.after(time_end)) {
        //     JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
        //     dateEnd.getDateChooser().setCalendar(null);
        //     return false;
        // }
        return true;
    }

    public int getRowSelected() {
        return jTablePN.getSelectedRow();

    }
    // loadDataTalbe
    public void loadDataTalbe(ArrayList<DTO_PhieuNhap> list) {
        model = (DefaultTableModel) jTablePN.getModel();
        model.setRowCount(0);
        jTablePN.enable(false);
        for (int i = 0; i < list.size(); i++) {
            DTO_PhieuNhap phieu = list.get(i);
            model.addRow(new Object[] {
                    phieu.getMaphieu(),
                    phieu.getThoigiantao(),
                    nccBUS.getTenNhaCungCap(phieu.getManhacungcap()),
                    nvBUS.getNameById(phieu.getManguoitao()),
                    Formater.FormatVND(phieu.getTongTien())
            });
        }
        // JOptionPane.showMessageDialog(null, "Load table thành công:" + listPhieu.size());
        // can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 5; i++) {
            jTablePN.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    // public void loadTable() {
    //     model = (DefaultTableModel) jTablePN.getModel();
    //     model.setRowCount(0);

    //     JOptionPane.showMessageDialog(null, "Load table thành công:" + listPhieu.size());
    //     for (int i = 0; i < listPhieu.size(); i++) {
    //         DTO_PhieuNhap phieu = listPhieu.get(i);
    //         model.addRow(new Object[] {
    //                 phieu.getMaphieu(),
    //                 phieu.getThoigiantao(),
    //                 nccBUS.getTenNhaCungCap(phieu.getManhacungcap()),
    //                 nvBUS.getNameById(phieu.getManguoitao()),
    //                 phieu.getTongTien()
    //         });
    //     }
    //     // can giua
    //     DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    //     centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    //     for (int i = 0; i < 5; i++) {
    //         jTablePN.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    //     }

    // }
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

        model = (DefaultTableModel) jTablePN.getModel();

        Workbook workbook = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet();

        // Create header row
        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã phiếu");
        headerRow.createCell(1).setCellValue("Thời gian tạo");
        headerRow.createCell(2).setCellValue("Tên nhà cung cấp");
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePN = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtSearchNCC = new com.raven.suportSwing.TextField();
        jComboBoxNCC = new javax.swing.JComboBox<>();
        myButton1NCC = new com.raven.suportSwing.MyButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3NCC = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4NCC = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel6NCC = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel1NCC = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(3200, 800));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(1220, 460));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(210, 450));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel16.setLayout(new java.awt.BorderLayout());

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
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel20.add(jTextField3, java.awt.BorderLayout.PAGE_END);

        jPanel15.add(jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel21.setLayout(new java.awt.BorderLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Đến số tiền (VNĐ)");
        jPanel21.add(jLabel6, java.awt.BorderLayout.CENTER);

        jTextField4.setPreferredSize(new java.awt.Dimension(71, 30));
        jPanel21.add(jTextField4, java.awt.BorderLayout.PAGE_END);

        jPanel15.add(jPanel21);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1065, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jTablePN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Thời gian tạo", "Nhà cung cấp", "Nhân viên tạo", "Tổng tiền"
            }
        ));
        jScrollPane1.setViewportView(jTablePN);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txtSearchNCC.setLabelText("Tìm theo tên or mã");
        txtSearchNCC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchNCCFocusGained(evt);
            }
        });
        txtSearchNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchNCCActionPerformed(evt);
            }
        });
        txtSearchNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchNCCKeyReleased(evt);
            }
        });

        jComboBoxNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lọc theo hãng" }));
        jComboBoxNCC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jComboBoxNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNCCActionPerformed(evt);
            }
        });

        myButton1NCC.setBackground(new java.awt.Color(204, 255, 255));
        myButton1NCC.setText("Reset");
        myButton1NCC.setBorderColor(new java.awt.Color(153, 255, 255));
        myButton1NCC.setRadius(20);
        myButton1NCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1NCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jComboBoxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myButton1NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(myButton1NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSearchNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setText("HỦY PHIẾU");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-view-75.png"))); // NOI18N

        jButton5.setText("XEM PHIẾU");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4NCC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton7.setText("XUẤT");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setText("THÊM PHIẾU");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1NCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel1NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 1247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1947, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
         ThemPhieuNhapDialog dialog = new ThemPhieuNhapDialog(new javax.swing.JFrame(), true, this, user);
         dialog.setLocationRelativeTo(null);
         dialog.setVisible(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        exportJTableToExcel();
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int index = jTablePN.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập để xem chi tiết!");
            return;
        }
        int id = (int) jTablePN.getValueAt(index, 0);
     Timestamp date = (Timestamp) jTablePN.getValueAt(index, 1);
     int mancc = nccBUS.getMaNhaCungCap(jTablePN.getValueAt(index, 2).toString());
     int manv = nvBUS.getIdByName(jTablePN.getValueAt(index, 3).toString());
     String tongtienStr = jTablePN.getValueAt(index, 4).toString().replace("VND", "").replace(",", "");
     tongtienStr = tongtienStr.substring(0, tongtienStr.length() - 1);
    
     long tongtien = Long.parseLong(tongtienStr);
        // JOptionPane.showMessageDialog(null, "ID: " + id + " Date: " + date + " NCC: " + mancc + " NV: " + manv + " TongTien: " + tongtien);
        DTO_PhieuNhap pn = new DTO_PhieuNhap(id, date,mancc, manv, tongtien, 1);

        XemChiTietPhieuNhapDialog dialog = new XemChiTietPhieuNhapDialog(new javax.swing.JFrame(), true, pn);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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


    public void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed
  
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: Xoa
        int index = jTablePN.getSelectedRow();
        // kiem tra co bao nhieu phien ban phien ban trong ma phieu nhap co ton tai ma phieu xuat
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập để hủy!");
            return;
        }
        int cnt = busCTSP.getsoluongphienbansanphamtontaitrongphieuxuat(Integer.parseInt(jTablePN.getValueAt(index, 0).toString()));
        if (cnt > 0) {
            JOptionPane.showMessageDialog(null,
                    "Không thể hủy vì có " + cnt + " phiên bản sản phẩm trong phiếu nhập đã được xuất đi!");
            return;
        }
        if (index != -1) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn huỷ phiếu ?\n Hãy nghĩ kĩ vì sau khi hủy\n không thể hoàn tác lại.", "Huỷ phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                DTO_PhieuNhap pn = listPhieu.get(index);
                // if (!phieunhapBUS.checkCancelPn(pn.getMaphieu())) {
                //     JOptionPane.showMessageDialog(null, "Sản phẩm trong phiếu này đã được xuất đi không thể hủy phiếu này!");
                // } else {
                int c = phieunhapBUS.cancelPhieuNhap(pn.getMaphieu());
                //cap nhat maimei co ma phieu nhap vua xoa la tinh trang bang 0
                int check = ctspBUS.capNhatctspTinhTrangbang0(pn.getMaphieu());

                    if (c == 0 && check == 0) {
                        JOptionPane.showMessageDialog(null, "Hủy phiếu không thành công!");
                    } else {
                        // ma phieu nhap tang them 1
                        
                        JOptionPane.showMessageDialog(null, "Hủy phiếu thành công!");
                        listPhieu = phieunhapBUS.getAll();
                        loadDataTalbe(listPhieu);
                    }
                // }
            }
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void myButton1NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1NCCActionPerformed
        // TODO add your handling code here:
        resetAll();
    }//GEN-LAST:event_myButton1NCCActionPerformed

    private void jComboBoxNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNCCActionPerformed

    private void txtSearchNCCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNCCKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchNCCKeyReleased

    private void txtSearchNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchNCCActionPerformed

    private void txtSearchNCCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNCCFocusGained

    }//GEN-LAST:event_txtSearchNCCFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBoxNCC;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1NCC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel3NCC;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel4NCC;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePN;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private com.raven.suportSwing.MyButton myButton1NCC;
    private com.raven.suportSwing.TextField txtSearchNCC;
    // End of variables declaration//GEN-END:variables
}
