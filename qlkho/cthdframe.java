package qlkho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import qlkho.qlkhoframe.CustomScrollBarUI;
import qlkho.dao.ChiTietPhieuNhapDAO;
import qlkho.dao.DienThoaiDAO;

public class cthdframe extends JFrame {
    public cthdframe(String mahdnhap) {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        table.setBackground(new Color(51, 51, 51));
        table.setForeground(Color.white);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(51, 51, 51));
        table.getTableHeader().setForeground(Color.white);

        tableModel = ChiTietPhieuNhapDAO.getInstance().loadDataToTableCondition(mahdnhap);
        if (tableModel.getRowCount() == 0) {
            System.out.println("No data found for mahdnhap: " + mahdnhap);
        }
        table.setModel(tableModel);

        JScrollPane scrollPane_table = new JScrollPane(table);
        scrollPane_table = new JScrollPane();
        scrollPane_table.setViewportView(table);
        scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
        scrollPane_table.setBorder(null);
        scrollPane_table.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_table.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));// Chiều rộng 12px
        scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(scrollPane_table, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
