package qlkho;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import qlkho.dao.DienThoaiDAO;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class qlkhoframe extends JFrame implements MouseListener {
    JLabel lb_sanpham;
    JTable table;
    DefaultTableModel tableModel;

    public class CustomScrollBarUI extends BasicScrollBarUI {

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(130, 130, 130, 100)); // Màu của thanh cuộn
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 5, 5); // Bo góc
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(51, 51, 51)); // Màu nền của thanh cuộn
            g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            g2.dispose();
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createInvisibleButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createInvisibleButton();
        }

        private JButton createInvisibleButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }
    }

    public qlkhoframe() {

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
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        panel_left_bottom.add(scrollPane);

        JPanel panel_menu = new JPanel();
        panel_menu.setLayout(null);
        panel_menu.setPreferredSize(new Dimension(180, 1000));
        panel_menu.setBackground(new Color(51, 51, 51));
        scrollPane.setViewportView(panel_menu);

        lb_sanpham = new JLabel("Sản phẩm");
        lb_sanpham.setBounds(10, 0, 160, 30);
        lb_sanpham.setForeground(Color.white);
        ImageIcon icon_sanpham = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_sanpham.setIcon(icon_sanpham);
        lb_sanpham.setIconTextGap(10);
        lb_sanpham.addMouseListener(this);
        panel_menu.add(lb_sanpham);

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
        tf_search.setBounds(40, 10, 100, 40);
        tf_search.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_search.setBackground(new Color(51, 51, 51));
        tf_search.setForeground(Color.white);
        panel_right_top.add(tf_search, BorderLayout.CENTER);

        JPanel panel_right_top_btn = new JPanel();
        panel_right_top_btn.setBackground(Color.RED);
        panel_right_top_btn.setLayout(new GridLayout(1, 3));
        panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);

        JButton btn_add = new JButton("Thêm");
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

        JPanel panel_right_bottom = new JPanel();
        panel_right_bottom.setBackground(Color.BLACK);
        panel_right_bottom.setLayout(new BorderLayout());
        panel_right_bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panel_right.add(panel_right_bottom, BorderLayout.CENTER);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
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
        scrollPane_table.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);

        tableModel = DienThoaiDAO.getInstance().loadDataToTable("DienThoai");
        table.setModel(tableModel);

        JPanel panel_right_bottom_top = new JPanel();
        panel_right_bottom_top.setBackground(Color.BLACK);
        panel_right_bottom_top.setPreferredSize(new Dimension(0, 150));
        panel_right_bottom_top.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel_right_bottom_top.setLayout(new BorderLayout());
        panel_right_bottom.add(panel_right_bottom_top, BorderLayout.NORTH);

        JPanel panel_right_bottom_showsp = new JPanel();
        panel_right_bottom_showsp.setBackground(new Color(51, 51, 51));
        panel_right_bottom_top.add(panel_right_bottom_showsp, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lb_sanpham.setBackground(new Color(150, 150, 150)); // Màu nền khi bấm
        lb_sanpham.setOpaque(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lb_sanpham.setBackground(new Color(100, 100, 100)); // Quay lại màu hover khi thả chuột
        lb_sanpham.setOpaque(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lb_sanpham.setBackground(new Color(100, 100, 100)); // Màu nền khi hover
        lb_sanpham.setOpaque(true); // Đảm bảo màu nền được hiển thị
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lb_sanpham.setBackground(new Color(51, 51, 51)); // Màu nền mặc định
        lb_sanpham.setOpaque(true);
    }
}
