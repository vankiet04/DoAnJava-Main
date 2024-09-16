/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Kiet
 */
public class headerTaskbar extends JPanel {
    Color DefaultColor = new Color(255, 255, 255);
    Color ColorBlack = new Color(26, 26, 26);
    JLabel lblIcon, pnlContent;
     
    public headerTaskbar(String linkIcon, String content) {
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(225, 145));
        this.setBackground(DefaultColor);
        this.putClientProperty( FlatClientProperties.STYLE, "arc: 15" );
        lblIcon = new JLabel();
        lblIcon.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblIcon.setPreferredSize(new Dimension(125, 100));
        lblIcon.setIcon(new FlatSVGIcon("./icon/" + linkIcon));
        this.add(lblIcon);

        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(150, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 145% $medium.font");
        pnlContent.setForeground(ColorBlack);
        this.add(pnlContent, BorderLayout.CENTER);
    }
}
