package qlkho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class abc extends JFrame {
    public abc() {
        setTitle("Quản lý kho");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel_left = new JPanel();
        panel_left.setPreferredSize(new Dimension(220, 0));
        panel_left.setBackground(Color.black);
        add(panel_left, BorderLayout.WEST);
        panel_left.setLayout(null);
        JPanel panel_left_top = new JPanel();
        panel_left_top.setLayout(null);
        panel_left_top.setBounds(10, 10, 200, 100);
        panel_left_top.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_top);
        JLabel imageAvatar = new JLabel();
        ImageIcon icon = new ImageIcon("C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\profile.jpg");
        ImageIcon resizedIcon = ImageResizer.resizeImageIcon(icon, 90, 90);
        imageAvatar.setIcon(resizedIcon);
        imageAvatar.setBounds(10, 10, 90, 80);
        panel_left_top.add(imageAvatar);
        JLabel labelName = new JLabel("Nguyễn Văn A");
        labelName.setBounds(110, 10, 100, 20);
        labelName.setForeground(Color.white);
        panel_left_top.add(labelName);
        JLabel labelRole = new JLabel();
        labelRole.setBounds(110, 30, 100, 30);
        labelRole.setText("<html>Nhân viên</html>");
        labelRole.setForeground(Color.white);
        panel_left_top.add(labelRole);

        JPanel panel_left_bottom = new JPanel();
        panel_left_bottom.setLayout(null);
        panel_left_bottom.setBounds(10, 120, 200, 600);
        panel_left_bottom.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_bottom);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 187, 580);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));
        // scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        panel_left_bottom.add(scrollPane);

        JPanel panel_menu = new JPanel();
        panel_menu.setLayout(null);
        panel_menu.setPreferredSize(new Dimension(180, 1000));
        panel_menu.setBackground(new Color(51, 51, 51));
        scrollPane.setViewportView(panel_menu);

        JLabel lb_sanpham = new JLabel("Sản phẩm");
        lb_sanpham.setBounds(10, 0, 160, 30);
        lb_sanpham.setForeground(Color.white);
        ImageIcon icon_sanpham = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_sanpham.setIcon(icon_sanpham);
        lb_sanpham.setIconTextGap(10);
        // lb_sanpham.addMouseListener(this);
        panel_menu.add(lb_sanpham);

        JLabel lb_ncc = new JLabel("Nha cung cap");
        // lb_ncc.addMouseListener(this);
        lb_ncc.setBounds(10, 40, 160, 30);
        lb_ncc.setForeground(Color.white);
        ImageIcon icon_ncc = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_ncc.setIcon(icon_ncc);
        lb_ncc.setIconTextGap(10);
        // lb_ncc.addMouseListener(this);
        panel_menu.add(lb_ncc);

        JLabel lb_phieunhap = new JLabel("Hoa don nhap");
        // lb_phieunhap.addMouseListener(this);
        lb_phieunhap.setBounds(10, 80, 160, 30);
        lb_phieunhap.setForeground(Color.white);
        ImageIcon icon_phieunhap = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_phieunhap.setIcon(icon_phieunhap);
        lb_phieunhap.setIconTextGap(10);
        // lb_phieunhap.addMouseListener(this);
        panel_menu.add(lb_phieunhap);

        JLabel lb_thongkenhapkho = new JLabel("Thong ke nhap kho");
        // lb_thongkenhapkho.addMouseListener(this);
        lb_thongkenhapkho.setBounds(10, 120, 160, 30);
        lb_thongkenhapkho.setForeground(Color.white);
        ImageIcon icon_thongkenhapkho = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_thongkenhapkho.setIcon(icon_thongkenhapkho);
        lb_thongkenhapkho.setIconTextGap(10);
        // lb_thongkenhapkho.addMouseListener(this);
        panel_menu.add(lb_thongkenhapkho);

        JPanel panel_right = new JPanel();
        panel_right.setBackground(Color.BLUE);
        panel_right.setLayout(new BorderLayout());
        add(panel_right, BorderLayout.CENTER);

        JPanel panel_right_top = new JPanel();
        panel_right_top.setBackground(Color.BLACK);
        panel_right_top.setPreferredSize(new Dimension(0, 60));
        panel_right_top.setLayout(new BorderLayout());
        panel_right_top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10)); // Tạo khoảng cách 10px
        panel_right.add(panel_right_top, BorderLayout.NORTH);

        JLabel lb_search = new JLabel();
        ImageIcon icon_search = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\search.png");
        ImageIcon resizedIcon_search = ImageResizer.resizeImageIcon(icon_search, 30, 30);
        lb_search.setIcon(resizedIcon_search);
        lb_search.setBackground(new Color(51, 51, 51));
        lb_search.setOpaque(true);
        panel_right_top.add(lb_search, BorderLayout.WEST);

        JTextField tf_search = new JTextField("Tìm kiếm");
        // addSearchFunctionality();
        tf_search.setBounds(40, 10, 100, 40);
        tf_search.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_search.setBackground(new Color(51, 51, 51));
        tf_search.setForeground(Color.white);
        panel_right_top.add(tf_search, BorderLayout.CENTER);

        JPanel panel_right_top_btn = new JPanel();
        panel_right_top_btn.setBackground(Color.RED);
        panel_right_top_btn.setLayout(new GridLayout(1, 4));
        panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);

        JButton btn_refresh = new JButton("<html>Lam moi</html>");
        // btn_refresh.addActionListener(this);
        ImageIcon icon_refresh = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\refresh.png");
        icon_refresh = ImageResizer.resizeImageIcon(icon_refresh, 30, 30);
        btn_refresh.setIcon(icon_refresh);
        btn_refresh.setFocusPainted(false);
        btn_refresh.setPreferredSize(new Dimension(80, 40));
        btn_refresh.setBackground(new Color(51, 51, 51));
        btn_refresh.setForeground(Color.white);
        btn_refresh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_top_btn.add(btn_refresh);

        JButton btn_add = new JButton("Thêm");
        // btn_add.addActionListener(this);
        ImageIcon icon_add = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\add.png");
        icon_add = ImageResizer.resizeImageIcon(icon_add, 30, 30);
        btn_add.setIcon(icon_add);
        btn_add.setFocusPainted(false);
        btn_add.setPreferredSize(new Dimension(80, 40));
        btn_add.setBackground(new Color(51, 51, 51));
        btn_add.setForeground(Color.white);
        btn_add.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_top_btn.add(btn_add);

        JButton btn_edit = new JButton("Sửa");
        ImageIcon icon_edit = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\edit.png");
        icon_edit = ImageResizer.resizeImageIcon(icon_edit, 30, 30);
        btn_edit.setIcon(icon_edit);
        btn_edit.setFocusPainted(false);
        btn_edit.setPreferredSize(new Dimension(80, 40));
        btn_edit.setBackground(new Color(51, 51, 51));
        btn_edit.setForeground(Color.white);
        btn_edit.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_top_btn.add(btn_edit);

        JButton btn_delete = new JButton("Xóa");
        // btn_delete.addActionListener(this);
        ImageIcon icon_delete = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\delete.png");
        icon_delete = ImageResizer.resizeImageIcon(icon_delete, 30, 30);
        btn_delete.setIcon(icon_delete);
        btn_delete.setFocusPainted(false);
        btn_delete.setPreferredSize(new Dimension(80, 40));
        btn_delete.setBackground(new Color(51, 51, 51));
        btn_delete.setForeground(Color.white);
        btn_delete.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_top_btn.add(btn_delete);

        JComponent panel_right_bottom = new JPanel();
        panel_right_bottom.setBackground(Color.red);
        panel_right_bottom.setLayout(new BorderLayout());
        panel_right_bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panel_right.add(panel_right_bottom, BorderLayout.CENTER);

        setVisible(true);
    }
}
