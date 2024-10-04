/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAO.AccountDAO;
import DAO.DAO_TaiKhoan;
import DTO.AccountDTO;
import DTO.DTO_TaiKhoan;
import GUI.Component.InputForm;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


public class LogIn extends JFrame implements KeyListener {

    JPanel pnlMain, pnlLogIn;
    JLabel lblImage, lbl1, lbl2, lbl3, lbl4, lbl5, lbLogin, lbl7;
    InputForm txtUsername, txtPassword;

    public LogIn() {
        KhoiTaoComponent();
        txtUsername.setText("admin");
        txtPassword.setPass("123456");
    }
    
    private void KhoiTaoComponent() {
        this.setSize(new Dimension(1000, 460));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("HỆ THỐNG QUẢN LÝ BÁN MÁY TÍNH TEST2");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame jf = this;
        
        InitRightPicture();
        
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(new EmptyBorder(50, 0, 0, 0));

        pnlMain.setPreferredSize(new Dimension(500, 940));
        pnlMain.setLayout(new FlowLayout(1, 0, 10));
        
        lbl3 = new JLabel("ĐĂNG NHẬP");
        lbl3.setFont(new Font(FlatRobotoFont.FAMILY_SEMIBOLD, Font.BOLD, 20));
        pnlMain.add(lbl3);
        
        JPanel paneldn = new JPanel();
        paneldn.setBackground(Color.WHITE);
        paneldn.setPreferredSize(new Dimension(400, 200));
        paneldn.setLayout(new GridLayout(2, 1));
        paneldn.setBorder(new EmptyBorder(0, 0, 20, 0));
        txtUsername = new InputForm("Tên đăng nhập");
        paneldn.add(txtUsername);
        txtPassword = new InputForm("Mật khẩu", "password");
        paneldn.add(txtPassword);
        
        txtUsername.addKeyListener(this);
        txtPassword.addKeyListener(this);
        pnlMain.add(paneldn);
        
        lbLogin = new JLabel("ĐĂNG NHẬP");
        lbLogin.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lbLogin.setForeground(Color.white);
 
        pnlLogIn = new JPanel();
        pnlLogIn.putClientProperty(FlatClientProperties.STYLE, "arc: 100" );
        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setPreferredSize(new Dimension(380, 45));
        pnlLogIn.setLayout(new FlowLayout(1, 0, 15));
        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                try {
                    pnlLogInMousePressed(evt);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pnlLogIn.add(lbLogin);
        
        pnlMain.add(pnlLogIn);
        this.add(pnlMain, BorderLayout.WEST);
        
    }
    
    public void login() throws UnsupportedLookAndFeelException {
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtPassword.getPass();
        if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        } else {
            int tks = AccountDAO.getInstance().login(usernameCheck, passwordCheck);
            if (tks == 0) {
                JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            } else {    
                DAO_TaiKhoan daoTK = new DAO_TaiKhoan();
                DTO_TaiKhoan tk = daoTK.getTaiKhoan(usernameCheck, passwordCheck);
                if (tk.getTrangthai() == 0) {
                    JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                } else {
                    this.dispose();
                    MainProgram main = new MainProgram(tk);
                    main.setVisible(true);
                }

            }
        }
    }
    public static void main(String[] args)  {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("PopupMenu.borderCornerRadius", 100);
        LogIn login = new LogIn();
        login.setVisible(true);
    }
    
    public void InitRightPicture() {
        
        JPanel bo = new JPanel();
        bo.setBorder(new EmptyBorder(0  , 15, 15, 15));
        bo.setPreferredSize(new Dimension(500, 740));
        bo.setBackground(Color.WHITE);      
        this.add(bo, BorderLayout.EAST);

        lblImage = new JLabel(); 
        lblImage.setIcon(new FlatSVGIcon("./img/login.svg   "));  
        bo.add(lblImage);
    }
    
    
    private void pnlLogInMousePressed(java.awt.event.MouseEvent evt) throws UnsupportedLookAndFeelException {
        login();
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}