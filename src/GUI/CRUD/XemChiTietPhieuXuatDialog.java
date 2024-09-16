/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.BUS_ChiTietCauHinh;
import BUS.BUS_ChiTietPhieuXuat;
import BUS.BUS_ChiTietSanPham;
import BUS.BUS_KhachHang;
import BUS.BUS_NhanVien;
import BUS.BUS_PhieuNhap;
import BUS.BUS_PhieuXuat;
import BUS.BUS_Product;
import DAO.DAO_ChiTietPhieuNhap;
import DAO.DAO_ChiTietPhieuXuat;
import DAO.DAO_KhachHang;
import DAO.DAO_NhaCungCap;
import DAO.DAO_NhanVien;
import DAO.DAO_PhieuNhap;
import DAO.DAO_PhieuXuat;
import DAO.DAO_Product;
import DAO.DAO_RamList;
import DAO.DAO_RomList;
import DTO.DTO_ChiTietCauHinh;
import DTO.DTO_ChiTietPhieuNhap;
import DTO.DTO_ChiTietPhieuXuat;
import DTO.DTO_ChiTietSanPham;
import DTO.DTO_PhieuNhap;
import DTO.DTO_PhieuXuat;
import DTO.DTO_Product;
import helper.Formater;

/**
 *
 * @author KIET
 */
public class XemChiTietPhieuXuatDialog extends javax.swing.JDialog {
    DTO_PhieuNhap phieunhap;
    // PhieuXuatDTO phieuxuat;
    BUS_ChiTietCauHinh phienbanBus = new BUS_ChiTietCauHinh();
    BUS_ChiTietSanPham ctspBus = new BUS_ChiTietSanPham();
    BUS_Product productBus = new BUS_Product();
    BUS_PhieuNhap phieunhapBus;
    ArrayList<DTO_ChiTietPhieuNhap> chitietphieu;
    DefaultTableModel tblModel, tblImei;

    int currentidchitiet  = -1;
    FileOutputStream file;
    Font fontBold15;
    Document document = new Document();
    BUS_NhanVien nhanvienBus = new BUS_NhanVien();
    ArrayList<DTO_ChiTietSanPham>chitietsanpham = new ArrayList<>();
    BUS_KhachHang khachhangBus = new BUS_KhachHang();
    // PhieuXuatBUS phieuxuatBus;

    /**
     * Creates new form XemChiTietPhieuNhapDialog
     */
    BUS_PhieuXuat busPhieuXuat = new BUS_PhieuXuat();
    BUS_ChiTietPhieuXuat busChiTietPhieuXuat = new BUS_ChiTietPhieuXuat();
    int currentIDselected = -1;
    public XemChiTietPhieuXuatDialog(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        this.currentIDselected = id;
        initComponents();
        loadtt();
        loadctspxuat();
    }

    public void loadtt() {
        DTO_PhieuXuat arr = busPhieuXuat.getPhieuXuatTheoID(currentIDselected);
        String tennv = nhanvienBus.getNvtheoid(arr.getIdnhanvien()).getHoten();
        String tenkh = khachhangBus.getkhtheoid(arr.getIdkhachhang()).getHoTen();
        jTextField1.setText(arr.getMaphieuxuat() + "");
        jTextField2.setText(tennv);
        jTextField3.setText(tenkh);
        jTextField4.setText(arr.getThoigian());
    }

    public void loadctspxuat() {
        ArrayList<DTO_ChiTietPhieuXuat> arr = busChiTietPhieuXuat.getPhieuXuatTheoID(currentIDselected);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (int i = 0; i < arr.size(); i++) {
            DTO_ChiTietPhieuXuat pb = arr.get(i);
            DTO_ChiTietCauHinh cth = phienbanBus.getByMaPhienBan(pb.getMaphienbansp());
            DTO_Product sp = productBus.getsanphamtheomaphienban(cth.getMasanpham());
            model.addRow(new Object[] { pb.getMaphienbansp(), sp.getMasanpham(), sp.getTensanpham(), cth.getRam(), cth.getRom(),
                    Formater.FormatVND(pb.getDongia() / pb.getSoluong()) , pb.getSoluong() });
        }

        // can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 7; i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //add event click
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    currentidchitiet = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                    loadDataTableImei(currentidchitiet);
                }
            }
        });
    }

    public XemChiTietPhieuXuatDialog(java.awt.Frame parent, boolean modal, DTO_PhieuNhap phieunhapDTO) {
        super(parent, modal);
        this.phieunhap = phieunhapDTO;
        phieunhapBus = new BUS_PhieuNhap();
        chitietphieu = phieunhapBus.getChiTietPhieu_Type(phieunhapDTO.getMaphieu());

        initComponents();
        initPhieuNhap(phieunhap);
        loadDataTableChiTietPhieu(chitietphieu);
        loadTongTien();

    }
    public static Chunk createWhiteSpace(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(" ");
        }
        return new Chunk(builder.toString());
    }

    public void writePDFPhieuXuat(int maphieuxuat) {
        String url = "phieuxuat";
        url = maphieuxuat + "";
        try {
            if (url.equals("nullnull")) {
                return;
            }
            BaseFont bf = null;
            try {
                bf = BaseFont.createFont("lib/Roboto/Roboto-Black.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi tạo font chữ");
            }
            Font font = new Font(bf);
            url = url + ".pdf";
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            Paragraph company = new Paragraph("HỆ THỐNG QUẢN LÝ LAPTOP", font);
            // company.add(new Chunk(createWhiteSpace(20)));
            // cach nhau 20
            String whiteSpace50 = String.join("", Collections.nCopies(50, " "));
            String whiteSpace20 = String.join("", Collections.nCopies(20, " "));
            String whiteSpace30 = String.join("", Collections.nCopies(30, " "));
            company.add(new Chunk(whiteSpace50));
            
            Date today = new Date(System.currentTimeMillis());
            company.add(new Chunk("Ngày in phiếu: " + today, font));

            company.setAlignment(Element.ALIGN_LEFT);
            document.add(company);
            // Thêm tên công ty vào file PDF
            document.add(Chunk.NEWLINE);
            Paragraph header = new Paragraph("THÔNG TIN PHIẾU XUẤT", font);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            DTO_PhieuXuat pn = DAO_PhieuXuat.getInstance().selectById(maphieuxuat + "");
            // Thêm dòng Paragraph vào file PDF

            Paragraph paragraph1 = new Paragraph("Mã phiếu: " + pn.getMaphieuxuat(), font);
            String kh = DAO_KhachHang.getInstance().selectById(pn.getIdkhachhang() + "").getHoTen();
            Paragraph paragraph2 = new Paragraph("Khách hàng: " + kh, font);
            // paragraph2.add(new Chunk(createWhiteSpace(5)));
            paragraph2.add(new Chunk("        -       Địa chỉ: ", font));
            // paragraph2.add(new Chunk(createWhiteSpace(5)));
            String diachikh = DAO_KhachHang.getInstance().selectById(pn.getIdkhachhang() + "").getDiaChi();
            paragraph2.add(new Chunk(diachikh));

            String ngtao = DAO_NhanVien.getInstance().selectById(pn.getIdnhanvien() + "").getHoten();
            Paragraph paragraph3 = new Paragraph("Nhân viên tạo: " + ngtao, font);
            // khoang cach 5 

            // paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("-"));
            // paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("Mã nhân viên: " + pn.getIdnhanvien(), font));
            Paragraph paragraph4 = new Paragraph("Thời gian xuất: " + pn.getThoigian(), font);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(Chunk.NEWLINE);
            // Thêm table 5 cột vào file PDF
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[] { 30f, 35f, 20f, 15f, 20f });
            PdfPCell cell;

            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", font)));
            table.addCell(new PdfPCell(new Phrase("Phiên bản", font)));
            table.addCell(new PdfPCell(new Phrase("Đơn giá (đ)", font)));
            table.addCell(new PdfPCell(new Phrase("Số lượng " , font)));
            table.addCell(new PdfPCell(new Phrase("Tổng tiền (đ)", font)));
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                table.addCell(cell);
            }
            JOptionPane. showMessageDialog(null, "Mã phiếu xuất oke: " + maphieuxuat);

            //Truyen thong tin tung chi tiet vao table
            for (DTO_ChiTietPhieuXuat ctp : DAO_ChiTietPhieuXuat.getInstance().selectAll(maphieuxuat + "")) {
                DTO_ChiTietCauHinh pb = phienbanBus.getByMaPhienBan(ctp.getMaphienbansp());
                cell = new PdfPCell(
                        new Phrase(DAO_Product.getInstance().selectById(pb.getMasanpham() + "").getTensanpham(), font));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(pb.getRam() + "GB - " + pb.getRom() + "GB"));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(Formater.FormatVND(ctp.getDongia())));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(ctp.getSoluong() + ""));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(Formater.FormatVND(ctp.getDongia() * ctp.getSoluong())));
                table.addCell(cell);
            }
            


            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thành tiền: " + Formater.FormatVND(pn.getTongtien()), font));
            paraTongThanhToan.setIndentationLeft(300);

            document.add(paraTongThanhToan);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph paragraph = new Paragraph();
            paragraph.setIndentationLeft(23);
            paragraph.add(new Chunk("Người lập phiếu", font));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Người giao", font));
            paragraph.add(new Chunk(createWhiteSpace(40)));
            paragraph.add(new Chunk("Người nhận", font));

            Paragraph sign = new Paragraph();
            sign.setIndentationLeft(23);
            sign.add(new Chunk("(Ký  tên)", font));
            sign.add(new Chunk(createWhiteSpace(45)));
            sign.add(new Chunk("(Ký tên)", font));
            sign.add(new Chunk(createWhiteSpace(45)));
            sign.add(new Chunk("(Ký tên)", font));
            document.add(paragraph);
            document.add(sign);

            document.close();
            writer.close();
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }

    }
    private void openFile(String file) {
        try {
            File path = new File(file);
            java.awt.Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    




    public void loadDataTableImei(int maphienban) {
        // set row count 0 jtabl2
        tblImei = (DefaultTableModel) jTable2.getModel();
        tblImei.setRowCount(0);
        ArrayList<String> imeis = ctspBus.getImeiTheoMaPhieuXuat(maphienban, Integer.parseInt(jTextField1.getText()));
        int size = imeis.size();
        for (int i = 0; i < size; i++) {
            tblImei.addRow(new Object[]{
                i + 1, imeis.get(i)
            });
        }
    }

    

    // public void loadDataTableImei(ArrayList<ChiTietSanPhamDTO> dssp) {
    //     tblModelImei.setRowCount(0);
    //     int size = dssp.size();
    //     for (int i = 0; i < size; i++) {
    //         tblModelImei.addRow(new Object[]{
    //             i + 1, dssp.get(i).getImei()
    //         });
    //     }
    // }
    public void loadTongTien() {
        int tongtien = 0;
        for (DTO_ChiTietPhieuNhap ct : chitietphieu) {
            tongtien += ct.getDongia() * ct.getSoluong();
        }
        jLabel6.setText(Formater.FormatVND(tongtien));
    }
    
    public void loadDataTableChiTietPhieu(ArrayList<DTO_ChiTietPhieuNhap> ctPhieu) {
        tblModel = (DefaultTableModel) jTable1.getModel();
        tblModel.setRowCount(0);
        DTO_ChiTietCauHinh pb;
        for (DTO_ChiTietPhieuNhap ct : ctPhieu) {
            pb = phienbanBus.getByMaPhienBan(ct.getMaphienbansp());
            tblModel.addRow(new Object[] {
                    ct.getMaphienbansp(),
                    pb.getMasanpham(),
                    DAO_Product.getInstance().selectById(pb.getMasanpham() + "").getTensanpham(),
                    pb.getRam(),
                    pb.getRom(),
                    Formater.FormatVND(ct.getDongia()),
                    ct.getSoluong()
            });
        }
        // Can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i<7; i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
        // for(int i = 0; i< size; i++) {
        //     pb = phienbanBus.getByMaPhienBan(ctPhieu.get(i).getMaphienbansp());
        //     tblModel.addRow(new Object[]{
        //         i + 1,
        //         pb.getMasanpham(),
        //         DAO_Product.getInstance().selectById(pb.getMasanpham()+"").getTensanpham(), 
        //         DAO_RamList.getInstance().selectById(pb.getRam()+"").getKichThuocRam() + "GB",
        //         DAO_RomList.getInstance().selectById(pb.getRom()+"").getKichThuocRom() + "GB", 
        //         Formater.FormatVND(ctPhieu.get(i).getDongia()), 
        //         ctPhieu.get(i).getSoluong()
        //     });
            
        // }
    }

    public void initPhieuNhap(DTO_PhieuNhap phieunhapDTO) {
        jTextField1.setFocusable(false);
        jTextField1.setText(phieunhapDTO.getMaphieu() + "");
        
        jTextField2.setFocusable(false);
        jTextField2.setText(DAO_NhanVien.getInstance().selectById(phieunhapDTO.getManguoitao() + "").getHoten());
        
        jTextField3.setFocusable(false);
        jTextField3.setText(DAO_NhaCungCap.getInstance().selectById(phieunhapDTO.getManhacungcap() + "").getTenncc());
        
        jTextField4.setFocusable(false);
        jTextField4.setText(Formater.FormatTime(phieunhapDTO.getThoigiantao()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setText("HỦY BỎ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã phiếu xuất:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nhân viên xuất:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Khách hàng:");

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setText("XUẤT FILE PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Thời gian tạo:");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Ram", "Rom", "Đơn giá", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(51, 153, 0));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("THÔNG TIN PHIẾU XUẤT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel7)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("TỔNG TIỀN:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 102));
        jLabel6.setText("0đ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "STT", "Ma imei"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField1)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(274, 274, 274)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jTextField2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int maphieuxuat = Integer.parseInt(jTextField1.getText());
        writePDFPhieuXuat(maphieuxuat);

 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

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
            java.util.logging.Logger.getLogger(XemChiTietPhieuXuatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XemChiTietPhieuXuatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XemChiTietPhieuXuatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemChiTietPhieuXuatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
