/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Kiet
 */
public class InpIMG extends JPanel implements ActionListener {
    private String url_img;
    JPanel imge;
    JButton btnChooseImg;

    public InpIMG() {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(292, 122));
        btnChooseImg = new JButton("Thêm hình");
        imge = new JPanel();
        imge.setPreferredSize(new Dimension(500, 500));
        imge.setBackground(new Color(204, 204, 204));
        imge.setBorder(new EmptyBorder(300, 300, 0, 0));
        btnChooseImg.addActionListener(this);
        this.add(btnChooseImg);
        this.add(imge, BorderLayout.SOUTH);
    }
    // ẩn nút chọn ảnh
    public void hideBtn() {
        btnChooseImg.setEnabled(false);
    }
    
    public Image scale(ImageIcon x) {
        int WIDTH = 250;
        int HEIGHT = 280;
        Image scaledImage = x.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        return scaledImage;
    } 
    
    public String getUrl() {
        return this.url_img;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc;
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, GIF, JPG hoặc JPEG", "png", "gif", "jpg", "jpeg");
        jfc.addChoosableFileFilter(filter);
        int img = jfc.showOpenDialog(null);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(jfc.getSelectedFile().getPath());
            this.url_img = (String) jfc.getSelectedFile().getPath();
            File file = jfc.getSelectedFile();
            ImageIcon imgicon = new ImageIcon(String.valueOf(jfc.getSelectedFile()));
            BufferedImage b;
            try {
                 b = ImageIO.read(file);
            Image scaledImage = b.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
             imgicon = new ImageIcon(scaledImage);
            btnChooseImg.setIcon(imgicon);
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Chọn ảnh thất bại!");
              
            }

        }
    }
    
    public void showImg(String url) {
        ImageIcon imgicon = new ImageIcon("./src/HinhAnhSanPham/" + url);
        imgicon = new ImageIcon(scale(imgicon));
        btnChooseImg.setIcon(imgicon);       
    }
    
    public String showImgz(String url) {
        return "./src/HinhAnhSanPham/" + url;      
    }

    public void setHinhsanpham(String url) {
        this.url_img = url;      
    }
}
