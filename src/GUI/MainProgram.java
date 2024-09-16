/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.AccountDTO;
import DTO.DTO_TaiKhoan;
import GUI.Component.MenuTaskBar;
import GUI.Menu.QuanLyPhieuXuat;
import GUI.Menu.TrangChu;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
//import static org.apache.commons.math3.stat.StatUtils.mode; 

/**
 *
 * @author Kiet
 */
public class MainProgram extends JFrame {
    DTO_TaiKhoan user;
    public JPanel MainContent;
    Color MainColor = new Color(255, 255, 255);
    Color white = new Color(255,255,255);
    private MenuTaskBar menuTaskbar;
    public MainProgram() {
        KhoiTao();
    }
    
    public MainProgram(DTO_TaiKhoan acc) throws UnsupportedLookAndFeelException {
        this.user = acc;
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        FlatLightLaf.setup();
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
        UIManager.put("ComboBox.buttonHoverArrowColor", new Color(135,206,235));
        UIManager.put("ComboBox.buttonSeparatorWidth", 5);
        UIManager.put("ComboBox.buttonSeparatorColor", new Color(135,206,235));
        UIManager.put("Label.background", new Color(255,0,0));
        KhoiTao();

    }
    
    private void KhoiTao() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        int screenWidth = mode.getWidth();
        int screenHeight = mode.getHeight();
        this.setSize(screenWidth, screenHeight);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("HỆ THỐNG QUẢN LÝ BÁN MÁY TÍNH");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuTaskbar = new MenuTaskBar(this, user);
        menuTaskbar.setPreferredSize(new Dimension(250, 1400));
        this.add(menuTaskbar, BorderLayout.WEST);
        MainContent = new JPanel();
        MainContent.setBackground(MainColor);
        MainContent.setLayout(new BorderLayout(0, 0));
        this.add(MainContent, BorderLayout.CENTER);
        
        TrangChu dashboard = new TrangChu();
        dashboard.setBackground(white);
        MainContent.add(dashboard); 

    }
    
    public void changePages(JPanel pn) {
        MainContent.removeAll();
        MainContent.add(pn);
        MainContent.repaint();
        MainContent.validate();
    }

    

  
    
}
