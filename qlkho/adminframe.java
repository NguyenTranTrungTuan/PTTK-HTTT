package qlkho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import qlkho.qlkhoframe.CustomScrollBarUI;
import qlkho.dao.DienThoaiDAO;
import qlkho.dao.KhachHangDAO;
import qlkho.dao.NhanVienDAO;

public class adminframe extends JFrame implements ActionListener {
    public adminframe() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Nhân viên");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JTabbedPane tab = new JTabbedPane();
        tab.setForeground(Color.BLACK);
        tab.setFont(new Font(null, Font.BOLD, 20));
        tab.setBackground(Color.white);

        JPanel panel_taikhoannv = new JPanel();
        panel_taikhoannv.setBackground(Color.red);
        panel_taikhoannv.setLayout(new BorderLayout());
        JScrollPane taikhoannvscroll = new JScrollPane();
        taikhoannvscroll.setViewportView(panel_taikhoannv);
        ImageIcon img_nv = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\employer.png");
        img_nv = ImageResizer.resizeImageIcon(img_nv, 30, 30);
        tab.addTab("Tài khoản nhân viên", img_nv, taikhoannvscroll);
        JPanel paneltknv_top = new JPanel();
        paneltknv_top.setBackground(Color.BLACK);
        paneltknv_top.setPreferredSize(new Dimension(0, 60));
        paneltknv_top.setLayout(new BorderLayout());
        paneltknv_top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Tạo khoảng cách 10px
        panel_taikhoannv.add(paneltknv_top, BorderLayout.NORTH);
        JLabel lb_search = new JLabel();
        ImageIcon icon_search = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\search.png");
        ImageIcon resizedIcon_search = ImageResizer.resizeImageIcon(icon_search, 30, 30);
        lb_search.setIcon(resizedIcon_search);
        lb_search.setBackground(new Color(51, 51, 51));
        lb_search.setOpaque(true);
        paneltknv_top.add(lb_search, BorderLayout.WEST);

        JTextField tf_search = new JTextField("Tìm kiếm");
        // tf_search.getDocument().addDocumentListener(this);
        tf_search.setBounds(40, 10, 100, 40);
        tf_search.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_search.setBackground(new Color(51, 51, 51));
        tf_search.setForeground(Color.white);
        paneltknv_top.add(tf_search, BorderLayout.CENTER);

        JPanel paneltknv_top_btn = new JPanel();
        paneltknv_top_btn.setBackground(Color.RED);
        paneltknv_top_btn.setLayout(new GridLayout(1, 4));
        paneltknv_top.add(paneltknv_top_btn, BorderLayout.EAST);

        JButton btn_refresh = new JButton("<html>Làm mới</html>");
        btn_refresh.addActionListener(this);
        ImageIcon icon_refresh = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\refresh.png");
        icon_refresh = ImageResizer.resizeImageIcon(icon_refresh, 30, 30);
        btn_refresh.setIcon(icon_refresh);
        btn_refresh.setFocusPainted(false);
        btn_refresh.setPreferredSize(new Dimension(80, 40));
        btn_refresh.setBackground(new Color(51, 51, 51));
        btn_refresh.setForeground(Color.white);
        btn_refresh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltknv_top_btn.add(btn_refresh);

        JButton btn_add = new JButton("Thêm");
        btn_add.addActionListener(this);
        ImageIcon icon_add = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\add.png");
        icon_add = ImageResizer.resizeImageIcon(icon_add, 30, 30);
        btn_add.setIcon(icon_add);
        btn_add.setFocusPainted(false);
        btn_add.setPreferredSize(new Dimension(80, 40));
        btn_add.setBackground(new Color(51, 51, 51));
        btn_add.setForeground(Color.white);
        btn_add.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltknv_top_btn.add(btn_add);

        JButton btn_edit = new JButton("Sửa");
        btn_edit.addActionListener(this);
        ImageIcon icon_edit = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\edit.png");
        icon_edit = ImageResizer.resizeImageIcon(icon_edit, 30, 30);
        btn_edit.setIcon(icon_edit);
        btn_edit.setFocusPainted(false);
        btn_edit.setPreferredSize(new Dimension(80, 40));
        btn_edit.setBackground(new Color(51, 51, 51));
        btn_edit.setForeground(Color.white);
        btn_edit.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltknv_top_btn.add(btn_edit);

        JButton btn_delete = new JButton("Xóa");
        btn_delete.addActionListener(this);
        ImageIcon icon_delete = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\delete.png");
        icon_delete = ImageResizer.resizeImageIcon(icon_delete, 30, 30);
        btn_delete.setIcon(icon_delete);
        btn_delete.setFocusPainted(false);
        btn_delete.setPreferredSize(new Dimension(80, 40));
        btn_delete.setBackground(new Color(51, 51, 51));
        btn_delete.setForeground(Color.white);
        btn_delete.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltknv_top_btn.add(btn_delete);
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        // table.addMouseListener(this);
        table.setBackground(new Color(51, 51, 51));
        table.setForeground(Color.white);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(51, 51, 51));
        table.getTableHeader().setForeground(Color.white);

        JScrollPane scrollPane_table = new JScrollPane();
        scrollPane_table.setViewportView(table);
        scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
        scrollPane_table.setBorder(null);
        scrollPane_table.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_table.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));// Chiều rộng 12px
        scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel_taikhoannv.add(scrollPane_table, BorderLayout.CENTER);

        tableModel = NhanVienDAO.getInstance().loadDataToTable("NhanVien");
        table.setModel(tableModel);

        JPanel panel_taikhoankh = new JPanel();
        panel_taikhoankh.setBackground(Color.blue);
        panel_taikhoankh.setLayout(new BorderLayout());
        JScrollPane taikhoankhscroll = new JScrollPane();
        taikhoankhscroll.setViewportView(panel_taikhoankh);
        ImageIcon img_kh = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\customer.png");
        img_kh = ImageResizer.resizeImageIcon(img_kh, 30, 30);
        tab.addTab("Tài khoản khách hàng", img_kh, taikhoankhscroll);
        JPanel paneltkkh_top = new JPanel();
        paneltkkh_top.setBackground(Color.BLACK);
        paneltkkh_top.setPreferredSize(new Dimension(0, 60));
        paneltkkh_top.setLayout(new BorderLayout());
        paneltkkh_top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Tạo khoảng cách 10px
        panel_taikhoankh.add(paneltkkh_top, BorderLayout.NORTH);
        JLabel lb_search1 = new JLabel();
        ImageIcon icon_search1 = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\search.png");
        ImageIcon resizedIcon_search1 = ImageResizer.resizeImageIcon(icon_search1, 30, 30);
        lb_search1.setIcon(resizedIcon_search1);
        lb_search1.setBackground(new Color(51, 51, 51));
        lb_search1.setOpaque(true);
        paneltkkh_top.add(lb_search1, BorderLayout.WEST);

        JTextField tf_search1 = new JTextField("Tìm kiếm");
        // tf_search1.getDocument().addDocumentListener(this);
        tf_search1.setBounds(40, 10, 100, 40);
        tf_search1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_search1.setBackground(new Color(51, 51, 51));
        tf_search1.setForeground(Color.white);
        paneltkkh_top.add(tf_search1, BorderLayout.CENTER);

        JPanel paneltkkh_top_btn = new JPanel();
        paneltkkh_top_btn.setBackground(Color.RED);
        paneltkkh_top_btn.setLayout(new GridLayout(1, 4));
        paneltkkh_top.add(paneltkkh_top_btn, BorderLayout.EAST);

        JButton btn_refresh1 = new JButton("<html>Làm mới</html>");
        btn_refresh1.addActionListener(this);
        ImageIcon icon_refresh1 = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\refresh.png");
        icon_refresh1 = ImageResizer.resizeImageIcon(icon_refresh1, 30, 30);
        btn_refresh1.setIcon(icon_refresh1);
        btn_refresh1.setFocusPainted(false);
        btn_refresh1.setPreferredSize(new Dimension(80, 40));
        btn_refresh1.setBackground(new Color(51, 51, 51));
        btn_refresh1.setForeground(Color.white);
        btn_refresh1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltkkh_top_btn.add(btn_refresh1);

        JButton btn_add1 = new JButton("Thêm");
        btn_add1.addActionListener(this);
        ImageIcon icon_add1 = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\add.png");
        icon_add1 = ImageResizer.resizeImageIcon(icon_add1, 30, 30);
        btn_add1.setIcon(icon_add1);
        btn_add1.setFocusPainted(false);
        btn_add1.setPreferredSize(new Dimension(80, 40));
        btn_add1.setBackground(new Color(51, 51, 51));
        btn_add1.setForeground(Color.white);
        btn_add1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltkkh_top_btn.add(btn_add1);

        JButton btn_edit1 = new JButton("Sửa");
        btn_edit1.addActionListener(this);
        ImageIcon icon_edit1 = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\edit.png");
        icon_edit1 = ImageResizer.resizeImageIcon(icon_edit1, 30, 30);
        btn_edit1.setIcon(icon_edit1);
        btn_edit1.setFocusPainted(false);
        btn_edit1.setPreferredSize(new Dimension(80, 40));
        btn_edit1.setBackground(new Color(51, 51, 51));
        btn_edit1.setForeground(Color.white);
        btn_edit1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltkkh_top_btn.add(btn_edit1);

        JButton btn_delete1 = new JButton("Xóa");
        btn_delete1.addActionListener(this);
        ImageIcon icon_delete1 = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\delete.png");
        icon_delete1 = ImageResizer.resizeImageIcon(icon_delete1, 30, 30);
        btn_delete1.setIcon(icon_delete1);
        btn_delete1.setFocusPainted(false);
        btn_delete1.setPreferredSize(new Dimension(80, 40));
        btn_delete1.setBackground(new Color(51, 51, 51));
        btn_delete1.setForeground(Color.white);
        btn_delete1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        paneltkkh_top_btn.add(btn_delete1);
        DefaultTableModel tableModel1 = new DefaultTableModel();
        JTable table1 = new JTable(tableModel1);
        // table.addMouseListener(this);
        table1.setBackground(new Color(51, 51, 51));
        table1.setForeground(Color.white);
        table1.setGridColor(Color.BLACK);
        table1.setRowHeight(30);
        table1.getTableHeader().setBackground(new Color(51, 51, 51));
        table1.getTableHeader().setForeground(Color.white);

        JScrollPane scrollPane_table1 = new JScrollPane();
        scrollPane_table1.setViewportView(table1);
        scrollPane_table1.getViewport().setBackground(new Color(51, 51, 51));
        scrollPane_table1.setBorder(null);
        scrollPane_table1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_table1.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));// Chiều rộng 12px
        scrollPane_table1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel_taikhoankh.add(scrollPane_table1, BorderLayout.CENTER);

        tableModel1 = KhachHangDAO.getInstance().loadDataToTable("KhachHang");
        table1.setModel(tableModel1);

        add(tab, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new adminframe();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
