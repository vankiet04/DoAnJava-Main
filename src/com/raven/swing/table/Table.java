package com.raven.swing.table;

import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import javax.swing.table.TableCellEditor;

public class Table extends JTable {

    public Table() {
        
    }

//    @Override
//    public TableCellEditor getCellEditor(int row, int col) {
//        if (col == 4) {
//            return new TableCellAction();
//        } else {
//            return super.getCellEditor(row, col);
//        }
//    }

//    public void addRow(Object[] row) {
//        DefaultTableModel mod = (DefaultTableModel) getModel();
//        mod.addRow(row);
//    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }
}
