
package GUI.Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
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
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.List;

import BUS.BUS_ChucNangNhomQuyen;
import BUS.BUS_KhachHang;
import DTO.DTO_ChucNangNhomQuyen;
import DTO.DTO_KhachHang;
import DTO.DTO_TaiKhoan;
import GUI.CRUD.SuaKhachHang;
import GUI.CRUD.ThemKhachHang;
import GUI.CRUD.XemKhachHang;
/**
 *
 * @author Admin
 */
public class KhachHang extends javax.swing.JPanel {
    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
    BUS_KhachHang busKh = new BUS_KhachHang();
    String currentIDSelected = "-1";
    public ArrayList<DTO_KhachHang> listKh = busKh.getAllData();

    GUI.MainProgram main;
    DTO_TaiKhoan user;
     BUS_ChucNangNhomQuyen busNQ = new BUS_ChucNangNhomQuyen();
    ArrayList<DTO_ChucNangNhomQuyen> listNQ = new ArrayList<>();
    /**
     * Creates new form KhachHang
     */
    public KhachHang(GUI.MainProgram main, DTO_TaiKhoan user) {
        this.main = main;
        this.user = user;
        initComponents();
        filltable(listKh);

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        currentIDSelected = jTable1.getValueAt(selectedRow, 0).toString();
                    }
                }
            }
        });
        //key le
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {

                String ten = jTextField2.getText();
                if (ten.equals("")) {
                    listKh = busKh.getAllData();
                    filltable(listKh);
                }
                if (!ten.equals("")) {
                    listKh = busKh.search(ten);
                    filltable(listKh);
                }
            }
        });

        jToggleButton4.setEnabled(false);
        jToggleButton2.setEnabled(false);
        jToggleButton3.setEnabled(false);
        jToggleButton6.setEnabled(false);
        loadChucNangNhomQuyen();
    }

    public void loadChucNangNhomQuyen() {
        // JOptionPane. showMessageDialog(null,"kkk"+ user.getManhomquyen());
        listNQ = busNQ.selectAllChucNangNQ(user.getManhomquyen());

        for (DTO_ChucNangNhomQuyen iNQ : listNQ) {
            if (iNQ.getMachucnang().equals("khachhang") && iNQ.getHanhdong().equals("create")) {
                jToggleButton4.setEnabled(true);
            } 
            if (iNQ.getMachucnang().equals("khachhang") && iNQ.getHanhdong().equals("update")) {
                jToggleButton2.setEnabled(true);
            } 
            if (iNQ.getMachucnang().equals("khachhang") && iNQ.getHanhdong().equals("delete")) {
                jToggleButton3.setEnabled(true);
            } 
            if (iNQ.getMachucnang().equals("khachhang") && iNQ.getHanhdong().equals("view")) {
                jToggleButton6.setEnabled(true);
            }
        }
    }

    public void filltable(ArrayList<DTO_KhachHang> list) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (DTO_KhachHang kh : list) {
            model.addRow(new Object[] { kh.getMaKhachHang(), kh.getHoTen(), kh.getDiaChi(), kh.getSoDienThoai(),
                    kh.getNgayThamGia() });
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
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

            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Workbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Nhà cung cấp");

            // Create header row
            XSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(model.getColumnName(i));
            }

            // Create data rows
           
            for (int i = 0; i < model.getRowCount(); i++) {
                XSSFRow dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    TableModel tableModel = jTable1.getModel();
                    dataRow.createCell(j).setCellValue(tableModel.getValueAt(i, j).toString());
                }
            }

            // Resize all columns to fit the content size
            for (int i = 0; i < model.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file


            JOptionPane.showMessageDialog(null, "Xuất file thành công");
            
            try {
                FileOutputStream fileOut = new FileOutputStream(filePath);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();

        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("Tìm kiếm tên khách hàng");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jToggleButton2.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-edit-75.png"))); // NOI18N
        jToggleButton2.setText("Sửa");
        jToggleButton2.setToolTipText("");
        jToggleButton2.setBorder(null);
        jToggleButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton2.setDoubleBuffered(true);
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton2MouseExited(evt);
            }
        });
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jToggleButton3.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-85.png"))); // NOI18N
        jToggleButton3.setText("Xóa");
        jToggleButton3.setToolTipText("");
        jToggleButton3.setBorder(null);
        jToggleButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton3MouseExited(evt);
            }
        });
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-75.png"))); // NOI18N
        jToggleButton4.setText("Thêm");
        jToggleButton4.setToolTipText("");
        jToggleButton4.setBorder(null);
        jToggleButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jToggleButton4FocusGained(evt);
            }
        });
        jToggleButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton4MouseExited(evt);
            }
        });
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jToggleButton5.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-75.png"))); // NOI18N
        jToggleButton5.setText("Xuất excel");
        jToggleButton5.setToolTipText("");
        jToggleButton5.setBorder(null);
        jToggleButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton5MouseExited(evt);
            }
        });
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        jToggleButton6.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-view-75.png"))); // NOI18N
        jToggleButton6.setText("Chi tiết");
        jToggleButton6.setToolTipText("");
        jToggleButton6.setBorder(null);
        jToggleButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton6MouseExited(evt);
            }
        });
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });

        
        jToggleButton7.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-75.png"))); // NOI18N
        jToggleButton7.setText("Nhap excel");
        jToggleButton7.setToolTipText("");
        jToggleButton7.setBorder(null);
        jToggleButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton7MouseExited(evt);
            }
        });
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                
                
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToggleButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToggleButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jToggleButton2.getAccessibleContext().setAccessibleName("75");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(87, 87, 87)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Ngày tham gia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // sua khach hang
        if (currentIDSelected != "-1") {
            SuaKhachHang suakh = new SuaKhachHang(parent, true, this, currentIDSelected);
            suakh.setLocationRelativeTo(null);
            suakh.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        }
    }                                              

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here: xoa
        if (currentIDSelected != "-1") {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khách hàng này?", "Cảnh báo", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                if (busKh.delete(currentIDSelected) == 1) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    filltable(busKh.getAllData());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        }
    }                                              

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // them khach hang
        ThemKhachHang themkh = new ThemKhachHang(parent, true, this);
        themkh.setLocationRelativeTo(null);
        themkh.setVisible(true);
    }                                              

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // xuat excel
        exportJTableToExcel();
    }                                              

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // xem chi tiet
        if (currentIDSelected != "-1") {
            XemKhachHang suakh = new XemKhachHang(parent, true, this, currentIDSelected);
            suakh.setLocationRelativeTo(null);
            suakh.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xem", "Cảnh báo!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // xem chi tiet
        if (currentIDSelected != "-1") {
            XemKhachHang suakh = new XemKhachHang(parent, true, this, currentIDSelected);
            suakh.setLocationRelativeTo(null);
            suakh.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xem", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        }
    }                                         

    private void jToggleButton4FocusGained(java.awt.event.FocusEvent evt) {                                           
        
    }                                          

    private void jToggleButton4MouseEntered(java.awt.event.MouseEvent evt) {                                            
        jToggleButton4.setBackground(getBackground().darker());
        jToggleButton4.setOpaque(true);
    }                                           

    private void jToggleButton4MouseExited(java.awt.event.MouseEvent evt) {                                           
        jToggleButton4.setBackground(Color.WHITE);
        jToggleButton4.setOpaque(true);
    }                                          

    private void jToggleButton2MouseEntered(java.awt.event.MouseEvent evt) {                                            
        jToggleButton2.setBackground(getBackground().darker());
        jToggleButton2.setOpaque(true);
    }                                           

    private void jToggleButton2MouseExited(java.awt.event.MouseEvent evt) {                                           
        jToggleButton2.setBackground(Color.WHITE);
        jToggleButton2.setOpaque(true);
    }                                          

    private void jToggleButton3MouseEntered(java.awt.event.MouseEvent evt) {                                            
        jToggleButton3.setBackground(getBackground().darker());
        jToggleButton3.setOpaque(true);
    }                                           

    private void jToggleButton3MouseExited(java.awt.event.MouseEvent evt) {                                           
        jToggleButton3.setBackground(Color.WHITE);
        jToggleButton3.setOpaque(true);
    }                                          

    private void jToggleButton6MouseEntered(java.awt.event.MouseEvent evt) {                                            
        jToggleButton6.setBackground(getBackground().darker());
        jToggleButton6.setOpaque(true);
    }                                           

    private void jToggleButton6MouseExited(java.awt.event.MouseEvent evt) {
        jToggleButton6.setBackground(Color.WHITE);
        jToggleButton6.setOpaque(true);
    }
    
    private void jToggleButton7MouseEntered(java.awt.event.MouseEvent evt) {                                            
        jToggleButton7.setBackground(getBackground().darker());
        jToggleButton7.setOpaque(true);
    }                                           

    private void jToggleButton7MouseExited(java.awt.event.MouseEvent evt) {                                           
        jToggleButton7.setBackground(Color.WHITE);
        jToggleButton7.setOpaque(true);
    }   

    private void jToggleButton5MouseEntered(java.awt.event.MouseEvent evt) {                                            
        jToggleButton5.setBackground(getBackground().darker());
        jToggleButton5.setOpaque(true);
    }                                           

    private void jToggleButton5MouseExited(java.awt.event.MouseEvent evt) {                                           
        jToggleButton5.setBackground(Color.WHITE);
        jToggleButton5.setOpaque(true);
    }                                          

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {                                        
        if(jTextField2.getText().equals("Tìm kiếm tên khách hàng")){
            jTextField2.setText("");
            jTextField2.setForeground(Color.BLACK);
        }
    }                                       

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {                                      
        if( jTextField2.getText().isEmpty()){
            jTextField2.setForeground(Color.GRAY);
            jTextField2.setText("Tìm kiếm tên khách hàng");
                }
    }      
    
    private void jTextField2ActionPerformed(ActionEvent evt) {
        // search theo ten
        String ten = jTextField2.getText();
        if (!ten.equals("")) {
            listKh = busKh.search(ten);
            filltable(listKh);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;

    // End of variables declaration                   
}
