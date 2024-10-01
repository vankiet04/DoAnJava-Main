/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import BUS.BUS_ChiTietCauHinh;
import BUS.BUS_NhaCungCap;
import BUS.BUS_NhanVien;
import BUS.BUS_PhieuNhap;
import BUS.BUS_Product;
import BUS.BUS_RamList;
import BUS.BUS_RomList;
//import COM.rsa.jsafe.bu;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_NhaCungCap;
import DTO.DTO_NhanVien;
import DTO.DTO_PhieuNhap;
import DTO.DTO_Product;
import DTO.DTO_TaiKhoan;
import GUI.Menu.QuanLyPhieuNhap;
import helper.Formater;

import java.security.Timestamp;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KIET
 */
public class ThemPhieuNhapDialog extends javax.swing.JDialog {

    /**
     * Creates new form ThemPhieuNhapDialog
     */
    BUS_Product spBUS = new BUS_Product();
    BUS_NhaCungCap nccBus = new BUS_NhaCungCap();
    BUS_NhanVien nvBus = new BUS_NhanVien();

    BUS_ChiTietCauHinh phienbanBus = new BUS_ChiTietCauHinh();
    BUS_RamList ramBus = new BUS_RamList();
    BUS_RomList romBus = new BUS_RomList();
    BUS_PhieuNhap phieunhapBus = new BUS_PhieuNhap();
    DTO_NhanVien nvDto;
    DefaultTableModel tblModelSP, tblModelCTPhieu;
    
    GUI.Menu.QuanLyPhieuNhap ap;
    
    ArrayList<DTO_Product> listSP = spBUS.getAllData();
    ArrayList<DTO_ChiTietCauHinh> listPhienBanSP = new ArrayList<>();
    ArrayList<DTO_ChiTietPhieuNhap> chitietphieu;
    int maphieunhap;
     GUI.MainProgram main;
    DTO_TaiKhoan user;
    
    public void loadComboboxNhaCungCap() {
        ArrayList<DTO_NhaCungCap> listNCC = nccBus.getAllData();
        for (DTO_NhaCungCap ncc : listNCC) {
            jComboBox5.addItem(ncc.getMancc() + " - " + ncc.getTenncc());
        }
    }

    public void loadComboboxNhanVien() {
        jComboBox4.removeAllItems();
        DTO_NhanVien nvCb = nvBus.getNhanVien(user.getManv());
        jComboBox4.addItem(nvCb.getManv() + " - " + nvCb.getHoten());
        jComboBox4.setFocusable(false);

        
    }

    

    public void eventBtnNhapHang() {
        if (chitietphieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
        } else {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo phiếu nhập !", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                int mancc = Integer.parseInt(jComboBox5.getSelectedItem().toString().split(" - ")[0]);
                int manv = Integer.parseInt(jComboBox4.getSelectedItem().toString().split(" - ")[0]);
                long now = System.currentTimeMillis();
                java.sql.Timestamp currenTime = new java.sql.Timestamp(now);
                maphieunhap = phieunhapBus.phieunhapDAO.getAutoIncrement();
                DTO_PhieuNhap pn = new DTO_PhieuNhap(maphieunhap, currenTime, mancc, manv,
                        phieunhapBus.getTongTien(chitietphieu), 1);
                 if (phieunhapBus.getTongTien(chitietphieu) > 50000000000L) {
                    JOptionPane.showMessageDialog(this, "Tổng tiền phiếu nhập vượt quá 50 tỷ, không thể nhập hàng !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean result = phieunhapBus.add(pn, chitietphieu);
                // neu gia > 50 ty thi ko cho nhap
               
               if (result) {
                   JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
                   ArrayList<DTO_PhieuNhap> listPhieuNhap = phieunhapBus.getAll();
                   ap.loadDataTalbe(listPhieuNhap);
                    this.dispose();                  
               } else {
                   JOptionPane.showMessageDialog(this, "Nhập hàng không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
               }
            }
        }
    }
    
    
    public ThemPhieuNhapDialog(java.awt.Frame parent, boolean modal, GUI.Menu.QuanLyPhieuNhap ap) {
        super(parent, modal);
        this.ap = ap;

        initComponents();
        loadDataTalbeSanPham(listSP);
        this.jComboBox3.removeAllItems();
        this.jComboBox4.removeAllItems();
        this.jComboBox5.removeAllItems();
        // this.jComboBox3.addItem("Chọn cấu hình");
        loadComboboxNhaCungCap();
        loadComboboxNhanVien();

        tblModelSP = (DefaultTableModel) jTable1.getModel();
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    chonSanPham();
                }
            }
        });

    }
    public ThemPhieuNhapDialog(java.awt.Frame parent, boolean modal, GUI.Menu.QuanLyPhieuNhap ap, DTO_TaiKhoan user) {
        super(parent, modal);
        this.user = user;
        this.ap = ap;
        initComponents();
        loadDataTalbeSanPham(listSP);
        this.jComboBox3.removeAllItems();
        this.jComboBox4.removeAllItems();
        this.jComboBox5.removeAllItems();
        // this.jComboBox3.addItem("Chọn cấu hình");
        loadComboboxNhaCungCap();
        loadComboboxNhanVien();
        
        tblModelSP = (DefaultTableModel) jTable1.getModel();
          jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    chonSanPham();
                }
            }
        });
        
    }

    public DTO_ChiTietPhieuNhap getInfoChiTietPhieu() {
        DTO_ChiTietPhieuNhap ctphieu = new DTO_ChiTietPhieuNhap();
        int maphienbansp = listPhienBanSP.get(jComboBox3.getSelectedIndex()).getMaphienbansp();
        ctphieu.setMaphienbansp(maphienbansp);
     
        ctphieu.setSoluong(Long.parseLong(this.jTextField8.getText()));
        ctphieu.setDongia(Long.parseLong(this.jTextField7.getText()));
        return ctphieu;
    }

    public void loadDataTableChiTietPhieu(ArrayList<DTO_ChiTietPhieuNhap> listCtPhieu) {
        DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();
        tblModel.setRowCount(0);

        int size = listCtPhieu.size();
        for (int i = 0; i < size; i++) {
            DTO_ChiTietCauHinh pb = phienbanBus.getChiTietCauHinh(listCtPhieu.get(i).getMaphienbansp());

            tblModel.addRow(new Object[] {
                    i + 1, pb.getMaphienbansp(), spBUS.getProductByID(pb.getMasanpham()).getTensanpham(),
                    pb.getRam() + "GB",
                    pb.getRom() + "GB",
                    Formater.FormatVND(listCtPhieu.get(i).getDongia()), listCtPhieu.get(i).getSoluong()
            });
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 7; i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        jLabel4.setText(Formater.FormatVND(phieunhapBus.getTongTien(listCtPhieu)));
    }

  
    
    public void addCtPhieu() {
        tblModelCTPhieu = (DefaultTableModel) jTable2.getModel();
            if (chitietphieu == null) {
                chitietphieu = new ArrayList<>();
            }
            DTO_ChiTietPhieuNhap ctphieu = getInfoChiTietPhieu();
            //kiem tra co trung ma phien bang trong jTable 2 khong
            //jTable2
            for(int i = 0; i < tblModelCTPhieu.getRowCount(); i++) {
                Object cellValue = jTable2.getValueAt(i, 1);
                if (cellValue != null) {
                    if (ctphieu.getMaphienbansp() == Long.parseLong(cellValue.toString())) {
                        JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại trong phiếu, vui lòng sửa trong phiếu!");
                        return;
                    }
                }
            }
            
            chitietphieu.add(ctphieu);
            loadDataTableChiTietPhieu(chitietphieu);
            resetForm();
    }

    public void chonSanPham() {
        int index = jTable1.getSelectedRow();
        int masp = Integer.parseInt(tblModelSP.getValueAt(index, 0).toString());
        //kiem tra masp co ton tai trong listPhienBanSP khong
        boolean check = phienbanBus.checkMaSP(masp);
        if (!check) {
            JOptionPane.showMessageDialog(null, "Không thể chọn sản phẩm này vì chưa có phiên bản cấu hình!");
            return;
        }
        if (index >= 0) {
            resetForm();
            DTO_Product sp = listSP.get(index);
            setInfoSanPham(sp);
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào!");
        }
    }
    
    public void resetForm() {
        this.jTextField5.setText("");
        this.jTextField5.setFocusable(false);
        this.jTextField6.setText("");
        this.jTextField6.setFocusable(false);
        this.jComboBox3.removeAllItems();
        // this.jComboBox3.addItem("Chọn cấu hình");
        this.jTextField7.setText("");
        this.jTextField8.setText("");
        this.jTextField9.setText("");
    }
    public String[] getCauHinhPhienBan(int masp) {
        listPhienBanSP = phienbanBus.getCauHinhbyID(masp);
        int size = listPhienBanSP.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = listPhienBanSP.get(i).getRam() + " GB" + " - " + listPhienBanSP.get(i).getRom()+ " GB";
        }
        return arr;
    }

    public void setInfoSanPham(DTO_Product sp) {
        this.jTextField5.setText(Integer.toString(sp.getMasanpham()));
        this.jTextField6.setText(sp.getTensanpham());
        listPhienBanSP = phienbanBus.getCauHinhbyID(sp.getMasanpham());
        this.jComboBox3.removeAllItems();
        String arr[] = getCauHinhPhienBan(sp.getMasanpham());
        for (int i = 0; i < arr.length; i++) {
            this.jComboBox3.addItem(arr[i]);
        }
        // this.jTextField7.setText(Integer.toString(listPhienBanSP.get(0).getGianhap()));
    }
   
    public int layTongSoLuongCacPhienBan(int masp) {
        int tong = 0;
        ArrayList<DTO_ChiTietCauHinh> list = phienbanBus.getCauHinhbyID(masp);
        for (DTO_ChiTietCauHinh pb : list) {
            tong += pb.getSoluongton();
        }
        return tong;
    }

   
    
    public void loadDataTalbeSanPham(ArrayList<DTO_Product> result) {
        tblModelSP = (DefaultTableModel) jTable1.getModel();
        tblModelSP.setRowCount(0);
        for (DTO_Product sp : result) {
            tblModelSP.addRow(new Object[] { sp.getMasanpham(), sp.getTensanpham(),
                    layTongSoLuongCacPhienBan(sp.getMasanpham()) });
        }
        // can giua 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

    }

    public int laySoLuongPhienBanSanPham(String cauhinhString) {
        String[] arr = cauhinhString.split(" - ");
        int ram = Integer.parseInt(arr[0].split(" ")[0]);
        int rom = Integer.parseInt(arr[1].split(" ")[0]);
        for (DTO_ChiTietCauHinh pb : listPhienBanSP) {
            if (pb.getRam() == ram && pb.getRom() == rom) {
                return pb.getSoluongton();
            }
        }
        return 0;
    }
    public long layGiaPhienBanSanPham(String cauhinhString) {
        String[] arr = cauhinhString.split(" - ");
        int ram = Integer.parseInt(arr[0].split(" ")[0]);
        int rom = Integer.parseInt(arr[1].split(" ")[0]);
        for (DTO_ChiTietCauHinh pb : listPhienBanSP) {
            if (pb.getRam() == ram && pb.getRom() == rom) {
                return pb.getGianhap();
            }
        }
        return 0;
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
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtSearchNCC = new com.raven.suportSwing.TextField();
        myButton1NCC = new com.raven.suportSwing.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

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

        myButton1NCC.setText("Tìm cơ bản");
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
                .addComponent(txtSearchNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(myButton1NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(myButton1NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Tổng số lượng"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã sản phẩm đã chọn:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên sản phẩm đã chọn:");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Nhập đơn giá:");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Nhập số lượng thêm:");

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Chọn cấu hình ram - rom:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setPreferredSize(new java.awt.Dimension(72, 30));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Hiện có trong kho:");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField8)
                    .addComponent(jTextField7)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField9))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Chọn nhà cung cấp:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Chọn nhân viên nhập");

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
        jButton1.setText("NHẬP HÀNG");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Thông tin sản phẩm đã được chọn, để thêm vào phiếu nhập:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "RAM", "ROM", "Đơn giá nhập", "Số lượng nhập"
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setText("Sửa ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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

    private void txtSearchNCCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNCCFocusGained

    }//GEN-LAST:event_txtSearchNCCFocusGained

    private void txtSearchNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchNCCActionPerformed

    private void txtSearchNCCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchNCCKeyReleased
        // TODO add your handling code here:
        String key = txtSearchNCC.getText();
        if (key.isEmpty()) {
            loadDataTalbeSanPham(listSP);
        } else {
            ArrayList<DTO_Product> result = new ArrayList<>();
            for (DTO_Product sp : listSP) {
                if (String.valueOf(sp.getMasanpham()).contains(key) || sp.getTensanpham().toLowerCase().contains(key.toLowerCase())) {
                    result.add(sp);
                }
            }
            loadDataTalbeSanPham(result);
        }
    }//GEN-LAST:event_txtSearchNCCKeyReleased

    private void myButton1NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1NCCActionPerformed
        // TODO add your handling code here:
        // reset
        txtSearchNCC.setText("");
        loadDataTalbeSanPham(listSP);
    }//GEN-LAST:event_myButton1NCCActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = jTable2.getSelectedRow();
        if (index >= 0) {
            Object[] options = { "Sửa đơn giá","Sửa số lượng"};
            int response = JOptionPane.showOptionDialog(null, "Bạn muốn sửa gì?", "Chọn một tùy chọn",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == 1) {
                String newQuantity = JOptionPane.showInputDialog(null, "Nhập số lượng mới:");
                if (newQuantity != null && !newQuantity.isEmpty()) {
                    try {
                        long quantity = Long.parseLong(newQuantity);
                        if (quantity <= 0) {
                            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0 ");
                            return;
                        }
                       
                        chitietphieu.get(index).setSoluong(quantity);
                        loadDataTableChiTietPhieu(chitietphieu);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
                    }
                }
            } else if (response == 0) {
                String newPrice = JOptionPane.showInputDialog(null, "Nhập đơn giá mới:");
                if (newPrice != null && !newPrice.isEmpty()) {
                    try {
                        long price = Long.parseLong(newPrice);
                        if (price <= 0) {
                            JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn 0 ");
                            return;
                        }
                        if(price > 50000000){
                            JOptionPane.showMessageDialog(null, "Đơn giá không vượt quá 50 triệu đồng");
                            return;
                        }
                        chitietphieu.get(index).setDongia(price);
                        loadDataTableChiTietPhieu(chitietphieu);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ!");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào!");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: Xoa
        int index = jTable2.getSelectedRow();
    if (index >= 0) {
        int response = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            chitietphieu.remove(index);
            loadDataTableChiTietPhieu(chitietphieu);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào!");
    }

}//GEN-LAST:event_jButton3ActionPerformed

  
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
     
        if (jTextField5.getText().equals("") || jTextField6.getText().equals("") || jTextField7.getText().equals("")
                || jTextField8.getText().equals("") || jTextField9.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm và nhập đầy đủ thông tin !");
            return;
        }
       
        if (Long.parseLong(jTextField8.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0 ");
            return;
        }

       
        if (Long.parseLong(jTextField7.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn 0 ");
            return;
        }
        
        addCtPhieu();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        Object selectedItem = jComboBox3.getSelectedItem();
    if (selectedItem != null) {
        int quantity = laySoLuongPhienBanSanPham(selectedItem.toString());
        jTextField9.setText(String.valueOf(quantity));
        jTextField9.setFocusable(false);
        long dongia = layGiaPhienBanSanPham(selectedItem.toString());
        jTextField7.setText(String.valueOf(dongia));

        
}

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        eventBtnNhapHang();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

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
            java.util.logging.Logger.getLogger(ThemPhieuNhapDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuNhapDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuNhapDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuNhapDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemPhieuNhapDialog dialog = new ThemPhieuNhapDialog(new javax.swing.JFrame(), true,    new GUI.Menu.QuanLyPhieuNhap());
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private com.raven.suportSwing.MyButton myButton1NCC;
    private com.raven.suportSwing.TextField txtSearchNCC;
    // End of variables declaration//GEN-END:variables
}
