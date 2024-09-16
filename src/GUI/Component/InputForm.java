/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Kiet
 */
public class InputForm extends JPanel {
    private JLabel lblTitle;
    private JTextField txtForm;
    private JPasswordField txtPass;
    
    public InputForm() {
         
    }
     
    public InputForm(String title) {
       this.setLayout(new GridLayout(2, 1));
       this.setBackground(Color.white);
       this.setBorder(new EmptyBorder(0, 10, 5, 10));
       this.setPreferredSize(new Dimension(100, 100));
       lblTitle = new JLabel(title);
       txtForm = new JTextField();
       this.add(lblTitle);
       this.add(txtForm);
    }
    
    public InputForm(String title, String style) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 5, 10));
        this.setPreferredSize(new Dimension(100, 100));
        lblTitle = new JLabel(title);
        this.add(lblTitle);
        if (style.equals("password")) {
            txtPass = new JPasswordField();
            this.add(txtPass);
        }
    }
    
     public JTextField getTxtForm() {
        return txtForm;
    }

    public void setTxtForm(JTextField txtForm) {
        this.txtForm = txtForm;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(JPasswordField txtPass) {
        this.txtPass = txtPass;
    }
    
    public String getText() {
        return txtForm.getText();
    }
    
    public String getPass() {
        return txtPass.getText();
    }
    
    public void setPass(String s) {
        txtPass.setText(s);
    }

    public void setText(String content) {
        txtForm.setText(content);
    }
}
