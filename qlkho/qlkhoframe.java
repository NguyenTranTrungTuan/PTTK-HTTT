package qlkho;

import qlkho.oop.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartPanel;

import qlkho.dao.ChiTietDonHangDAO;
import qlkho.dao.ChiTietPhieuNhapDAO;
import qlkho.dao.DienThoaiDAO;
import qlkho.dao.DonHangDAO;
import qlkho.dao.KhoDAO;
import qlkho.dao.NhaCungCapDAO;
import qlkho.dao.PhieuNhapDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;

public class qlkhoframe extends JFrame implements MouseListener, ActionListener, DocumentListener {
    NhanVien nhanVien;
    JComboBox<String> cb_thang, cb_nam, cb_tenncc, cb_tendt, cb_tenncc1;
    ChartPanel chartPanel;
    JLabel lb_sanpham, lb_madtshowsp, lb_ncc, lb_phieunhap, lb_thongkenhapkho, lb_tongtiennhap, lb_donhang,
            lb_thongkedonhang, lb_tongdoanhthu, lb_search, lb_kho, lb_dangxuat;
    JTable table;
    DefaultTableModel tableModel;
    JButton btn_add, btn_refresh, btn_xacnhanthem, btn_huythem, btn_delete, btn_thongke, btn_edit, btn_huythemncc,
            btn_xacnhanthemncc, btn_xacnhanthemphieunhap, btn_huythemphieunhap, btn_duyetdh, btn_huydh, btn_xuatfilepn,
            btn_xuatfiledh;
    JPanel panel_right, panel_themsp, panel_right_bottom, panel_right_bottom_top, panel_thongkenhapkho, panel_right_top,
            panel_thongkebottom_top, panel_thongkebottom_mid, panel_thongkebottom_bottom, panel_thongkenhapkhobottom,
            panel_themncc, panel_themphieunhap, panel_right_top_btn, panel_themsp_mid_layout, panel_themsp_top,
            panel_themsp_mid, panel_themsp_bottom;
    JScrollPane scrollPane_table;
    JTextField tf_madtshowsp, tf_tendtshowsp, tf_giabanshowsp, tf_gianhapshowsp, tf_matonshowsp, tf_xuatxushowsp,
            tf_trongluongshowsp, tf_kichthuocmanhinhshowsp, tf_dungluongdtshowsp, tf_ramshowsp, tf_baohanhshowsp,
            tf_manccshowsp, tf_madt, tf_tendt, tf_giaban, tf_gianhap, tf_maton, tf_xuatxu,
            tf_trongluong, tf_kichthuocmanhinh, tf_dungluongdt, tf_ram, tf_baohanh,
            tf_mancc, tf_search, tf_nccmancc, tf_ncctenncc, tf_nccquocgia, tf_sl;
    boolean isSanPhamPanel, isNCCPanel, isPhieuNhap, isDonHangPanel;

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

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText(value == null ? "Button" : value.toString());
            return this;
        }
    }

    public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JButton button;
        private String label;
        private boolean clicked;

        public ButtonEditor() {
            button = new JButton();
            button.addActionListener(this);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            label = value == null ? "Button" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (clicked) {
                if (isPhieuNhap) {
                    int selectedRow = table.getSelectedRow();
                    String mahdnhap = table.getValueAt(selectedRow, 0).toString();
                    cthdframe cthd = new cthdframe(mahdnhap);
                    System.out.println("Button clicked1: " + label);
                } else if (isDonHangPanel) {
                    int selectedRow = table.getSelectedRow();
                    String madhnhap = table.getValueAt(selectedRow, 0).toString();
                    ctdhframe ctdh = new ctdhframe(madhnhap);
                    System.out.println("Button clicked2: " + label);
                }

            }
            clicked = false;
            fireEditingStopped();
        }
    }

    private String[] createThang() {
        String[] thang = new String[13];
        thang[0] = "Tất cả"; // Lựa chọn để không lọc theo tháng
        for (int i = 1; i <= 12; i++) {
            thang[i] = String.format("%02d", i);
        }
        return thang;
    }

    private String[] createNam() {
        String[] nam = new String[7];
        nam[0] = "Tất cả"; // Lựa chọn để không lọc theo năm
        for (int i = 2020, j = 1; i <= 2025; i++, j++) {
            nam[j] = String.valueOf(i);
        }
        return nam;
    }

    private void searchTable(String query) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        if (query.trim().isEmpty()) {
            sorter.setRowFilter(null); // Hiển thị tất cả nếu không có từ khóa
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query)); // Lọc theo từ khóa (không phân biệt hoa thường)
        }
    }

    public qlkhoframe(NhanVien nv) {
        nhanVien = nv;
        isSanPhamPanel = true;
        setTitle("Quản lý kho");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel_left = new JPanel();
        panel_left.setPreferredSize(new Dimension(220, 0));
        panel_left.setBackground(Color.black);
        panel_left.setLayout(null);
        add(panel_left, BorderLayout.WEST);
        JPanel panel_left_top = new JPanel();
        panel_left_top.setLayout(null);
        panel_left_top.setBounds(10, 10, 200, 100);
        panel_left_top.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_top);
        JLabel imageAvatar = new JLabel();
        ImageIcon icon = new ImageIcon("C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\profile.png");
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
        labelRole.setText("<html>Bán Hàng</html>");
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

        lb_dangxuat = new JLabel("Đăng xuất");
        lb_dangxuat.addMouseListener(this);
        lb_dangxuat.setBounds(25, 730, 200, 30);
        lb_dangxuat.setForeground(Color.white);
        ImageIcon icon_dangxuat = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\logout.png");
        lb_dangxuat.setIcon(icon_dangxuat);
        lb_dangxuat.setIconTextGap(10);
        lb_dangxuat.addMouseListener(this);
        panel_left.add(lb_dangxuat);

        JPanel panel_menu = new JPanel();
        panel_menu.setLayout(null);
        panel_menu.setPreferredSize(new Dimension(180, 1000));
        panel_menu.setBackground(new Color(51, 51, 51));
        scrollPane.setViewportView(panel_menu);

        lb_sanpham = new JLabel("Sản phẩm");
        lb_sanpham.setBounds(10, 0, 160, 30);
        lb_sanpham.setForeground(Color.white);
        ImageIcon icon_sanpham = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\product.png");
        lb_sanpham.setIcon(icon_sanpham);
        lb_sanpham.setIconTextGap(10);
        lb_sanpham.addMouseListener(this);
        panel_menu.add(lb_sanpham);

        lb_ncc = new JLabel("Nhà cung cấp");
        lb_ncc.addMouseListener(this);
        lb_ncc.setBounds(10, 40, 160, 30);
        lb_ncc.setForeground(Color.white);
        ImageIcon icon_ncc = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\supplier.png");
        lb_ncc.setIcon(icon_ncc);
        lb_ncc.setIconTextGap(10);
        lb_ncc.addMouseListener(this);
        panel_menu.add(lb_ncc);

        lb_phieunhap = new JLabel("Hóa đơn nhập");
        lb_phieunhap.addMouseListener(this);
        lb_phieunhap.setBounds(10, 80, 160, 30);
        lb_phieunhap.setForeground(Color.white);
        ImageIcon icon_phieunhap = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\import.png");
        lb_phieunhap.setIcon(icon_phieunhap);
        lb_phieunhap.setIconTextGap(10);
        lb_phieunhap.addMouseListener(this);
        panel_menu.add(lb_phieunhap);

        lb_thongkenhapkho = new JLabel("Thống kê nhập kho");
        lb_thongkenhapkho.addMouseListener(this);
        lb_thongkenhapkho.setBounds(10, 120, 160, 30);
        lb_thongkenhapkho.setForeground(Color.white);
        ImageIcon icon_thongkenhapkho = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\importsta.png");
        lb_thongkenhapkho.setIcon(icon_thongkenhapkho);
        lb_thongkenhapkho.setIconTextGap(10);
        lb_thongkenhapkho.addMouseListener(this);
        panel_menu.add(lb_thongkenhapkho);

        lb_thongkedonhang = new JLabel("Thống kê đơn hàng");
        lb_thongkedonhang.addMouseListener(this);
        lb_thongkedonhang.setBounds(10, 160, 160, 30);
        lb_thongkedonhang.setForeground(Color.white);
        ImageIcon icon_thongkedonhang = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\salesta.png");
        lb_thongkedonhang.setIcon(icon_thongkedonhang);
        lb_thongkedonhang.setIconTextGap(10);
        lb_thongkedonhang.addMouseListener(this);
        panel_menu.add(lb_thongkedonhang);

        lb_donhang = new JLabel("Đơn hàng");
        lb_donhang.addMouseListener(this);
        lb_donhang.setBounds(10, 200, 160, 30);
        lb_donhang.setForeground(Color.white);
        ImageIcon icon_donhang = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\order.png");
        lb_donhang.setIcon(icon_donhang);
        lb_donhang.setIconTextGap(10);
        lb_donhang.addMouseListener(this);
        panel_menu.add(lb_donhang);

        lb_kho = new JLabel("Kho");
        lb_kho.addMouseListener(this);
        lb_kho.setBounds(10, 240, 160, 30);
        lb_kho.setForeground(Color.white);
        ImageIcon icon_kho = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\storage.png");
        lb_kho.setIcon(icon_kho);
        lb_kho.setIconTextGap(10);
        lb_kho.addMouseListener(this);
        panel_menu.add(lb_kho);

        panel_right = new JPanel();
        panel_right.setBackground(Color.BLUE);
        panel_right.setLayout(new BorderLayout());
        add(panel_right, BorderLayout.CENTER);

        panel_right_top = new JPanel();
        panel_right_top.setBackground(Color.BLACK);
        panel_right_top.setPreferredSize(new Dimension(0, 60));
        panel_right_top.setLayout(new BorderLayout());
        panel_right_top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10)); // Tạo khoảng cách 10px
        panel_right.add(panel_right_top, BorderLayout.NORTH);

        lb_search = new JLabel();
        ImageIcon icon_search = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\search.png");
        ImageIcon resizedIcon_search = ImageResizer.resizeImageIcon(icon_search, 30, 30);
        lb_search.setIcon(resizedIcon_search);
        lb_search.setBackground(new Color(51, 51, 51));
        lb_search.setOpaque(true);
        panel_right_top.add(lb_search, BorderLayout.WEST);

        tf_search = new JTextField("Tìm kiếm");
        tf_search.getDocument().addDocumentListener(this);
        tf_search.setBounds(40, 10, 100, 40);
        tf_search.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_search.setBackground(new Color(51, 51, 51));
        tf_search.setForeground(Color.white);
        panel_right_top.add(tf_search, BorderLayout.CENTER);

        panel_right_top_btn = new JPanel();
        panel_right_top_btn.setBackground(Color.RED);
        panel_right_top_btn.setLayout(new GridLayout(1, 4));
        panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);

        btn_refresh = new JButton("<html>Làm mới</html>");
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
        panel_right_top_btn.add(btn_refresh);

        btn_add = new JButton("Thêm");
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
        panel_right_top_btn.add(btn_add);

        btn_edit = new JButton("Sửa");
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
        panel_right_top_btn.add(btn_edit);

        btn_delete = new JButton("Xóa");
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
        panel_right_top_btn.add(btn_delete);

        btn_xuatfilepn = new JButton("<html>In ra file</html>");
        btn_xuatfilepn.addActionListener(this);
        ImageIcon icon_xuatfilepn = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\print.png");
        icon_xuatfilepn = ImageResizer.resizeImageIcon(icon_xuatfilepn, 30, 30);
        btn_xuatfilepn.setIcon(icon_xuatfilepn);
        btn_xuatfilepn.setFocusPainted(false);
        btn_xuatfilepn.setPreferredSize(new Dimension(80, 40));
        btn_xuatfilepn.setBackground(new Color(51, 51, 51));
        btn_xuatfilepn.setForeground(Color.white);
        btn_xuatfilepn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));

        panel_right_bottom = new JPanel();
        panel_right_bottom.setBackground(Color.BLACK);
        panel_right_bottom.setLayout(new BorderLayout());
        panel_right_bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panel_right.add(panel_right_bottom, BorderLayout.CENTER);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.addMouseListener(this);
        table.setBackground(new Color(51, 51, 51));
        table.setForeground(Color.white);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(51, 51, 51));
        table.getTableHeader().setForeground(Color.white);

        scrollPane_table = new JScrollPane();
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

        panel_right_bottom_top = new JPanel();
        panel_right_bottom_top.setBackground(Color.BLACK);
        panel_right_bottom_top.setPreferredSize(new Dimension(0, 150));
        panel_right_bottom_top.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel_right_bottom_top.setLayout(new BorderLayout());
        panel_right_bottom.add(panel_right_bottom_top, BorderLayout.NORTH);

        JPanel panel_right_bottom_showsp = new JPanel();
        panel_right_bottom_showsp.setBackground(new Color(51, 51, 51));
        panel_right_bottom_showsp.setLayout(new BorderLayout());
        panel_right_bottom_showsp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        JPanel panel_right_bottom_showsp_left = new JPanel();
        panel_right_bottom_showsp_left.setPreferredSize(new Dimension(130, 130));
        JLabel imgshowsp = new JLabel();
        ImageIcon img_imgshowsp = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\DT002.png");
        img_imgshowsp = ImageResizer.resizeImageIcon(img_imgshowsp, 115, 130);
        imgshowsp.setIcon(img_imgshowsp);
        panel_right_bottom_showsp_left.add(imgshowsp);
        panel_right_bottom_showsp_left.setBackground(new Color(51, 51, 51));

        JPanel panel_right_bottom_showsp_right = new JPanel();
        panel_right_bottom_showsp_right.setBackground(new Color(51, 51, 51));
        panel_right_bottom_showsp_right.setLayout(new GridLayout(4, 6));
        // showsp
        lb_madtshowsp = new JLabel();
        lb_madtshowsp.setText("Mã điện thoại: ");
        lb_madtshowsp.setForeground(Color.white);
        tf_madtshowsp = new JTextField();
        tf_madtshowsp.setEditable(false);
        tf_madtshowsp.setBackground(Color.BLACK);
        tf_madtshowsp.setForeground(Color.white);
        tf_madtshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_madtshowsp);
        panel_right_bottom_showsp_right.add(tf_madtshowsp);
        JLabel lb_tendtshowsp = new JLabel();
        lb_tendtshowsp.setText("Tên điện thoại: ");
        lb_tendtshowsp.setForeground(Color.white);
        tf_tendtshowsp = new JTextField(20);
        tf_tendtshowsp.setEditable(false);
        tf_tendtshowsp.setBackground(Color.BLACK);
        tf_tendtshowsp.setForeground(Color.white);
        tf_tendtshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_tendtshowsp);
        panel_right_bottom_showsp_right.add(tf_tendtshowsp);
        JLabel lb_giabanshowsp = new JLabel();
        lb_giabanshowsp.setText("Giá bán: ");
        lb_giabanshowsp.setForeground(Color.white);
        tf_giabanshowsp = new JTextField();
        tf_giabanshowsp.setEditable(false);
        tf_giabanshowsp.setBackground(Color.BLACK);
        tf_giabanshowsp.setForeground(Color.white);
        tf_giabanshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_giabanshowsp);
        panel_right_bottom_showsp_right.add(tf_giabanshowsp);
        JLabel lb_gianhapshowsp = new JLabel();
        lb_gianhapshowsp.setText("Giá nhập: ");
        lb_gianhapshowsp.setForeground(Color.white);
        tf_gianhapshowsp = new JTextField();
        tf_gianhapshowsp.setEditable(false);
        tf_gianhapshowsp.setBackground(Color.BLACK);
        tf_gianhapshowsp.setForeground(Color.white);
        tf_gianhapshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_gianhapshowsp);
        panel_right_bottom_showsp_right.add(tf_gianhapshowsp);
        JLabel lb_matonshowsp = new JLabel();
        lb_matonshowsp.setText("Mã tồn: ");
        lb_matonshowsp.setForeground(Color.white);
        tf_matonshowsp = new JTextField();
        tf_matonshowsp.setEditable(false);
        tf_matonshowsp.setBackground(Color.BLACK);
        tf_matonshowsp.setForeground(Color.white);
        tf_matonshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_matonshowsp);
        panel_right_bottom_showsp_right.add(tf_matonshowsp);
        JLabel lb_xuatxushowsp = new JLabel();
        lb_xuatxushowsp.setText("Xuất xứ: ");
        lb_xuatxushowsp.setForeground(Color.white);
        tf_xuatxushowsp = new JTextField();
        tf_xuatxushowsp.setEditable(false);
        tf_xuatxushowsp.setBackground(Color.BLACK);
        tf_xuatxushowsp.setForeground(Color.white);
        tf_xuatxushowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_xuatxushowsp);
        panel_right_bottom_showsp_right.add(tf_xuatxushowsp);
        JLabel lb_trongluongshowsp = new JLabel();
        lb_trongluongshowsp.setText("Trọng lượng: ");
        lb_trongluongshowsp.setForeground(Color.white);
        tf_trongluongshowsp = new JTextField();
        tf_trongluongshowsp.setEditable(false);
        tf_trongluongshowsp.setBackground(Color.BLACK);
        tf_trongluongshowsp.setForeground(Color.white);
        tf_trongluongshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_trongluongshowsp);
        panel_right_bottom_showsp_right.add(tf_trongluongshowsp);
        JLabel lb_kichthuocmanhinhshowsp = new JLabel();
        lb_kichthuocmanhinhshowsp.setText("<html>Kích thước màn hình: </html>");
        lb_kichthuocmanhinhshowsp.setForeground(Color.white);
        tf_kichthuocmanhinhshowsp = new JTextField();
        tf_kichthuocmanhinhshowsp.setEditable(false);
        tf_kichthuocmanhinhshowsp.setBackground(Color.BLACK);
        tf_kichthuocmanhinhshowsp.setForeground(Color.white);
        tf_kichthuocmanhinhshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_kichthuocmanhinhshowsp);
        panel_right_bottom_showsp_right.add(tf_kichthuocmanhinhshowsp);
        JLabel lb_dungluongdtshowsp = new JLabel();
        lb_dungluongdtshowsp.setText("Dung lượng dt: ");
        lb_dungluongdtshowsp.setForeground(Color.white);
        tf_dungluongdtshowsp = new JTextField();
        tf_dungluongdtshowsp.setEditable(false);
        tf_dungluongdtshowsp.setBackground(Color.BLACK);
        tf_dungluongdtshowsp.setForeground(Color.white);
        tf_dungluongdtshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_dungluongdtshowsp);
        panel_right_bottom_showsp_right.add(tf_dungluongdtshowsp);
        JLabel lb_ramshowsp = new JLabel();
        lb_ramshowsp.setText("Ram: ");
        lb_ramshowsp.setForeground(Color.white);
        tf_ramshowsp = new JTextField();
        tf_ramshowsp.setEditable(false);
        tf_ramshowsp.setBackground(Color.BLACK);
        tf_ramshowsp.setForeground(Color.white);
        tf_ramshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_ramshowsp);
        panel_right_bottom_showsp_right.add(tf_ramshowsp);
        JLabel lb_baohanhshowsp = new JLabel();
        lb_baohanhshowsp.setText("Bảo hành: ");
        lb_baohanhshowsp.setForeground(Color.white);
        tf_baohanhshowsp = new JTextField();
        tf_baohanhshowsp.setEditable(false);
        tf_baohanhshowsp.setBackground(Color.BLACK);
        tf_baohanhshowsp.setForeground(Color.white);
        tf_baohanhshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_baohanhshowsp);
        panel_right_bottom_showsp_right.add(tf_baohanhshowsp);
        JLabel lb_manccshowsp = new JLabel();
        lb_manccshowsp.setText("Mã NCC: ");
        lb_manccshowsp.setForeground(Color.white);
        tf_manccshowsp = new JTextField();
        tf_manccshowsp.setEditable(false);
        tf_manccshowsp.setBackground(Color.BLACK);
        tf_manccshowsp.setForeground(Color.white);
        tf_manccshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_manccshowsp);
        panel_right_bottom_showsp_right.add(tf_manccshowsp);
        // showsp
        panel_right_bottom_showsp.add(panel_right_bottom_showsp_left, BorderLayout.WEST);
        panel_right_bottom_showsp.add(panel_right_bottom_showsp_right, BorderLayout.CENTER);
        panel_right_bottom_top.add(panel_right_bottom_showsp, BorderLayout.CENTER);

        // themsp

        // panelthemsp

        // startpanelthemncc

        // endpanelthemncc

        // panelncc

        // panelthongkenhapkho
        panel_thongkenhapkho = new JPanel();
        panel_thongkenhapkho.setBackground(Color.black);
        panel_thongkenhapkho.setLayout(new BorderLayout());
        panel_thongkenhapkho.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
        JPanel panel_thongkenhapkhotop = new JPanel();
        panel_thongkenhapkhotop.setBackground(new Color(51, 51, 51));
        panel_thongkenhapkhotop.setPreferredSize(new Dimension(0, 50));
        panel_thongkenhapkhotop.setLayout(new BorderLayout());
        panel_thongkenhapkhotop.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panel_thongketop_left = new JPanel();
        panel_thongketop_left.setLayout(new GridLayout(1, 4, 10, 0));
        panel_thongketop_left.setBackground(new Color(51, 51, 51));
        JPanel panel_thongketop_right = new JPanel();
        panel_thongketop_right.setPreferredSize(new Dimension(100, 60));
        panel_thongketop_right.setLayout(new BorderLayout());
        panel_thongketop_right.setBackground(new Color(51, 51, 51));

        panel_thongkenhapkhotop.add(panel_thongketop_left, BorderLayout.CENTER);
        panel_thongkenhapkhotop.add(panel_thongketop_right, BorderLayout.EAST);

        JLabel lb_thang = new JLabel("Tháng:");
        lb_thang.setForeground(Color.WHITE);
        lb_thang.setBackground(new Color(51, 51, 51));
        cb_thang = new JComboBox<>(createThang());
        cb_thang.setPreferredSize(new Dimension(100, 30));
        cb_thang.setFocusable(false);

        JLabel lb_nam = new JLabel("Năm:");
        lb_nam.setForeground(Color.WHITE);
        lb_nam.setBackground(new Color(51, 51, 51));
        cb_nam = new JComboBox<>(createNam());
        cb_nam.setPreferredSize(new Dimension(100, 30));
        cb_nam.setFocusable(false);

        btn_thongke = new JButton("Lọc");
        btn_thongke.addActionListener(this);
        btn_thongke.setBackground(new Color(51, 51, 51));
        btn_thongke.setForeground(Color.white);
        btn_thongke.setFocusPainted(false);
        btn_thongke.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));

        panel_thongketop_left.add(lb_thang);
        panel_thongketop_left.add(cb_thang);
        panel_thongketop_left.add(lb_nam);
        panel_thongketop_left.add(cb_nam);
        panel_thongketop_right.add(btn_thongke, BorderLayout.CENTER);
        panel_thongkenhapkho.add(panel_thongkenhapkhotop, BorderLayout.NORTH);

        panel_thongkenhapkhobottom = new JPanel();
        panel_thongkenhapkhobottom.setBackground(Color.pink);
        panel_thongkenhapkhobottom.setLayout(new BorderLayout());
        panel_thongkebottom_top = new JPanel();
        panel_thongkebottom_top.setLayout(new BorderLayout());
        panel_thongkebottom_top.setPreferredSize(new Dimension(0, 60));
        panel_thongkebottom_top.setBackground(new Color(51, 51, 51));
        panel_thongkebottom_mid = new JPanel();
        panel_thongkebottom_mid.setLayout(new BorderLayout());
        chartPanel = new ChartPanel(null);
        panel_thongkebottom_bottom = new JPanel();
        panel_thongkebottom_bottom.setLayout(new BorderLayout());
        panel_thongkebottom_bottom.setPreferredSize(new Dimension(0, 380));
        panel_thongkebottom_bottom.setBackground(Color.yellow);
        panel_thongkenhapkhobottom.add(panel_thongkebottom_top, BorderLayout.NORTH);
        panel_thongkenhapkhobottom.add(panel_thongkebottom_mid, BorderLayout.CENTER);
        panel_thongkenhapkhobottom.add(panel_thongkebottom_bottom, BorderLayout.SOUTH);
        panel_thongkenhapkho.add(panel_thongkenhapkhobottom, BorderLayout.CENTER);

        // startpanelthemphieunhap

        // endpanelthemphieunhap

        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table && isSanPhamPanel) {
            int rowSelected = table.getSelectedRow();
            if (rowSelected != -1) {
                String ma_dt = String.valueOf(table.getValueAt(rowSelected, 0));
                String ten_dt = String.valueOf(table.getValueAt(rowSelected, 1));
                String gia_ban = String.valueOf((table.getValueAt(rowSelected, 2)));
                String gia_nhap = String.valueOf((table.getValueAt(rowSelected, 3)));
                String ma_ton = String.valueOf(table.getValueAt(rowSelected, 4));
                String xuat_xu = String.valueOf(table.getValueAt(rowSelected, 5));
                String trong_luong = String.valueOf(table.getValueAt(rowSelected, 6));
                String ktmh = String.valueOf(table.getValueAt(rowSelected, 7));
                String dung_luong = String.valueOf(table.getValueAt(rowSelected, 8));
                String ram = String.valueOf(table.getValueAt(rowSelected, 9));
                String bao_hanh = String.valueOf(table.getValueAt(rowSelected, 10));
                String ma_ncc = String.valueOf(table.getValueAt(rowSelected, 11));
                tf_madtshowsp.setText(ma_dt);
                tf_tendtshowsp.setText(ten_dt);
                tf_giabanshowsp.setText(gia_ban);
                tf_gianhapshowsp.setText(gia_nhap);
                tf_matonshowsp.setText(ma_ton);
                tf_xuatxushowsp.setText(xuat_xu);
                tf_trongluongshowsp.setText(trong_luong);
                tf_kichthuocmanhinhshowsp.setText(ktmh);
                tf_dungluongdtshowsp.setText(dung_luong);
                tf_ramshowsp.setText(ram);
                tf_baohanhshowsp.setText(bao_hanh);
                tf_manccshowsp.setText(ma_ncc);
            }
        } else if (e.getSource() == lb_sanpham) {
            isSanPhamPanel = true;
            isNCCPanel = false;
            isPhieuNhap = false;
            isDonHangPanel = false;
            panel_right_top.removeAll();
            panel_right_top.add(lb_search, BorderLayout.WEST);
            panel_right_top.add(tf_search, BorderLayout.CENTER);
            panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);
            panel_right_top_btn.removeAll();
            panel_right_top_btn.add(btn_refresh);
            panel_right_top_btn.add(btn_add);
            panel_right_top_btn.add(btn_edit);
            panel_right_top_btn.add(btn_delete);
            panel_right_top.revalidate();
            panel_right_top.repaint();
            panel_right.removeAll();
            panel_right.add(panel_right_top, BorderLayout.NORTH);
            panel_right.add(panel_right_bottom, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
            panel_right_bottom.removeAll();
            tableModel = DienThoaiDAO.getInstance().loadDataToTable("DienThoai");
            table.setModel(tableModel);
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.add(panel_right_bottom_top, BorderLayout.NORTH);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == lb_ncc) {
            isNCCPanel = true;
            isSanPhamPanel = false;
            isPhieuNhap = false;
            isDonHangPanel = false;
            panel_right_top.removeAll();
            panel_right_top.add(lb_search, BorderLayout.WEST);
            panel_right_top.add(tf_search, BorderLayout.CENTER);
            panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);
            panel_right_top_btn.removeAll();
            panel_right_top_btn.add(btn_refresh);
            panel_right_top_btn.add(btn_add);
            panel_right_top_btn.add(btn_edit);
            panel_right_top_btn.add(btn_delete);
            panel_right_top.revalidate();
            panel_right_top.repaint();
            panel_right.removeAll();
            panel_right.add(panel_right_top, BorderLayout.NORTH);
            panel_right.add(panel_right_bottom, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
            panel_right_bottom.removeAll();
            tableModel = NhaCungCapDAO.getInstance().loadDataToTable("NhaCungCap");
            table.setModel(tableModel);
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == lb_phieunhap) {
            isNCCPanel = false;
            isSanPhamPanel = false;
            isPhieuNhap = true;
            isDonHangPanel = false;
            panel_right_top.removeAll();
            panel_right_top.add(lb_search, BorderLayout.WEST);
            panel_right_top.add(tf_search, BorderLayout.CENTER);
            panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);
            panel_right_top_btn.removeAll();
            panel_right_top_btn.add(btn_refresh);
            panel_right_top_btn.add(btn_add);
            panel_right_top_btn.add(btn_xuatfilepn);
            panel_right_top.revalidate();
            panel_right_top.repaint();
            panel_right.removeAll();
            panel_right.add(panel_right_top, BorderLayout.NORTH);
            panel_right.add(panel_right_bottom, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
            panel_right_bottom.removeAll();
            tableModel = PhieuNhapDAO.getInstance().loadDataToTable("PhieuNhap");
            table.setModel(tableModel);
            table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == lb_thongkenhapkho) {
            isNCCPanel = false;
            isSanPhamPanel = false;
            isPhieuNhap = false;
            isDonHangPanel = false;
            panel_right.removeAll();
            panel_thongkebottom_top.removeAll();
            lb_tongtiennhap = new JLabel();
            lb_tongtiennhap.setText("Tổng tiền nhập: " + PhieuNhapDAO.getInstance().countSum() + " VND");
            lb_tongtiennhap.setForeground(Color.white);
            lb_tongtiennhap.setFont(new Font("Arial", Font.BOLD, 20));
            lb_tongtiennhap.setHorizontalAlignment(SwingConstants.CENTER);
            panel_thongkebottom_top.add(lb_tongtiennhap, BorderLayout.CENTER);
            panel_thongkebottom_top.revalidate();
            panel_thongkebottom_top.repaint();
            chartPanel.setChart(
                    PhieuNhapDAO.getInstance().createBarChart("Tất cả", "Tất cả"));
            panel_thongkebottom_bottom.add(chartPanel, BorderLayout.CENTER);
            tableModel = PhieuNhapDAO.getInstance().loadDataToTable("PhieuNhap");
            table.setModel(tableModel);
            table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
            panel_thongkebottom_mid.add(scrollPane_table, BorderLayout.CENTER);
            panel_right.add(panel_thongkenhapkho, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
        } else if (e.getSource() == lb_thongkedonhang) {
            isNCCPanel = false;
            isSanPhamPanel = false;
            isPhieuNhap = false;
            isDonHangPanel = false;
            panel_right.removeAll();
            panel_thongkebottom_top.removeAll();
            lb_tongdoanhthu = new JLabel();
            lb_tongdoanhthu.setText("Tổng doanh thu: " + DonHangDAO.getInstance().countSum() + " VND");
            lb_tongdoanhthu.setForeground(Color.white);
            lb_tongdoanhthu.setFont(new Font("Arial", Font.BOLD, 20));
            lb_tongdoanhthu.setHorizontalAlignment(SwingConstants.CENTER);
            panel_thongkebottom_top.add(lb_tongdoanhthu, BorderLayout.CENTER);
            panel_thongkebottom_top.revalidate();
            panel_thongkebottom_top.repaint();
            chartPanel.setChart(
                    DonHangDAO.getInstance().createLineChart("Tất cả", "Tất cả"));
            panel_thongkebottom_bottom.add(chartPanel, BorderLayout.CENTER);
            tableModel = DonHangDAO.getInstance().loadSuccessOrder("DonHang");
            table.setModel(tableModel);
            table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());
            panel_thongkebottom_mid.add(scrollPane_table, BorderLayout.CENTER);
            panel_right.add(panel_thongkenhapkho, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
        } else if (e.getSource() == lb_donhang) {
            isNCCPanel = false;
            isSanPhamPanel = false;
            isPhieuNhap = false;
            isDonHangPanel = true;
            panel_right_top.removeAll();
            panel_right_top.add(lb_search, BorderLayout.WEST);
            panel_right_top.add(tf_search, BorderLayout.CENTER);
            panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);

            btn_duyetdh = new JButton("<html>Duyệt đơn</html>");
            btn_duyetdh.addActionListener(this);
            ImageIcon icon_duyetdh = new ImageIcon(
                    "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\tick.png");
            icon_duyetdh = ImageResizer.resizeImageIcon(icon_duyetdh, 30, 30);
            btn_duyetdh.setIcon(icon_duyetdh);
            btn_duyetdh.setFocusPainted(false);
            btn_duyetdh.setPreferredSize(new Dimension(80, 40));
            btn_duyetdh.setBackground(new Color(51, 51, 51));
            btn_duyetdh.setForeground(Color.white);
            btn_duyetdh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));

            btn_huydh = new JButton("<html>Hủy đơn</html>");
            btn_huydh.addActionListener(this);
            ImageIcon icon_huydh = new ImageIcon(
                    "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\x.png");
            icon_huydh = ImageResizer.resizeImageIcon(icon_huydh, 30, 30);
            btn_huydh.setIcon(icon_huydh);
            btn_huydh.setFocusPainted(false);
            btn_huydh.setPreferredSize(new Dimension(80, 40));
            btn_huydh.setBackground(new Color(51, 51, 51));
            btn_huydh.setForeground(Color.white);
            btn_huydh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));

            panel_right_top_btn.removeAll();
            panel_right_top_btn.add(btn_refresh);
            panel_right_top_btn.add(btn_duyetdh);
            panel_right_top_btn.add(btn_huydh);
            panel_right_top_btn.add(btn_xuatfilepn);
            panel_right_top.revalidate();
            panel_right_top.repaint();
            panel_right.removeAll();
            panel_right.add(panel_right_top, BorderLayout.NORTH);
            panel_right.add(panel_right_bottom, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
            panel_right_bottom.removeAll();
            tableModel = DonHangDAO.getInstance().loadDataToTable("DonHang");
            table.setModel(tableModel);
            table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == lb_kho) {
            isNCCPanel = false;
            isSanPhamPanel = false;
            isPhieuNhap = false;
            isDonHangPanel = false;
            panel_right_top.removeAll();
            panel_right_top.add(lb_search, BorderLayout.WEST);
            panel_right_top.add(tf_search, BorderLayout.CENTER);
            panel_right_top.revalidate();
            panel_right_top.repaint();
            panel_right.removeAll();
            panel_right.add(panel_right_top, BorderLayout.NORTH);
            panel_right.add(panel_right_bottom, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
            panel_right_bottom.removeAll();
            tableModel = KhoDAO.getInstance().loadDataToTable("Kho");
            table.setModel(tableModel);
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_add) {
            if (isSanPhamPanel) {
                panel_themsp = new JPanel();
                panel_themsp.setBackground(Color.red);
                panel_themsp.setLayout(new BorderLayout());
                panel_themsp_top = new JPanel();
                panel_themsp_top.setBackground(Color.BLACK);
                panel_themsp_top.setPreferredSize(new Dimension(0, 350));
                panel_themsp_top.setLayout(new BorderLayout());
                panel_themsp.add(panel_themsp_top, BorderLayout.NORTH);
                panel_themsp_mid = new JPanel();
                panel_themsp_mid.setBackground(Color.BLACK);
                panel_themsp_mid.setLayout(new BorderLayout());
                panel_themsp.add(panel_themsp_mid, BorderLayout.CENTER);
                panel_themsp_bottom = new JPanel();
                panel_themsp_bottom.setBackground(Color.BLACK);
                panel_themsp_bottom.setPreferredSize(new Dimension(0, 70));
                panel_themsp_bottom.setLayout(new BorderLayout());
                panel_themsp.add(panel_themsp_bottom, BorderLayout.SOUTH);

                JPanel panel_themsp_top_layout = new JPanel();
                panel_themsp_top_layout.setBackground(new Color(151, 151, 151));
                panel_themsp_top_layout.setLayout(new GridLayout(1, 3));
                panel_themsp_top.add(panel_themsp_top_layout, BorderLayout.CENTER);

                JPanel addimg = new JPanel();
                addimg.setPreferredSize(new Dimension(310, 290));
                addimg.setBackground(Color.BLACK);
                addimg.setOpaque(true);
                addimg.setLayout(new BorderLayout());
                addimg.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                JButton chooseButton = new JButton("Chọn hình ảnh");
                addimg.add(chooseButton, BorderLayout.SOUTH);

                JPanel empty1 = new JPanel();
                empty1.setPreferredSize(new Dimension(220, 0));
                empty1.setBackground(new Color(51, 51, 51));
                empty1.setOpaque(true);
                panel_themsp_top_layout.add(empty1);
                JLabel imgsp = new JLabel("(Tai anh len)") {
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (getIcon() != null) {
                            ImageIcon icon = (ImageIcon) getIcon();
                            Image image = icon.getImage();
                            // Scale hình ảnh theo kích thước của JLabel
                            g.drawImage(image, 0, 0, getWidth(), getHeight() - chooseButton.getHeight(), this);
                        }
                    }
                };
                imgsp.setForeground(Color.WHITE);
                imgsp.setVerticalTextPosition(JLabel.BOTTOM); // Văn bản dưới hình
                imgsp.setHorizontalTextPosition(JLabel.CENTER);
                ImageIcon imgsanpham = new ImageIcon(
                        "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\DT002.png");
                imgsp.setIcon(imgsanpham);
                addimg.add(imgsp, BorderLayout.CENTER);
                panel_themsp_top_layout.add(addimg);

                JPanel empty2 = new JPanel();
                empty2.setPreferredSize(new Dimension(220, 0));
                empty2.setBackground(new Color(51, 51, 51));
                empty2.setOpaque(true);
                panel_themsp_top_layout.add(empty2);

                panel_themsp_mid_layout = new JPanel();
                panel_themsp_mid_layout.setBackground(new Color(51, 51, 51));
                panel_themsp_mid_layout.setLayout(new GridLayout(4, 6, 10, 20));
                panel_themsp_mid.add(panel_themsp_mid_layout, BorderLayout.CENTER);

                JPanel panel_themsp_bottom_layout = new JPanel();
                panel_themsp_bottom_layout.setBackground(new Color(51, 51, 51));
                panel_themsp_bottom_layout.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 25));
                panel_themsp_bottom.add(panel_themsp_bottom_layout, BorderLayout.CENTER);

                JLabel lb_madt = new JLabel();
                lb_madt.setText("Mã điện thoại: ");
                lb_madt.setForeground(Color.white);
                tf_madt = new JTextField(DienThoaiDAO.getInstance().autoUpdateMaDT());
                tf_madt.setBackground(Color.BLACK);
                tf_madt.setForeground(Color.white);
                tf_madt.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_madt);
                panel_themsp_mid_layout.add(tf_madt);
                JLabel lb_tendt = new JLabel();
                lb_tendt.setText("Tên điện thoại: ");
                lb_tendt.setForeground(Color.white);
                tf_tendt = new JTextField(20);
                tf_tendt.setBackground(Color.BLACK);
                tf_tendt.setForeground(Color.white);
                tf_tendt.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_tendt);
                panel_themsp_mid_layout.add(tf_tendt);
                JLabel lb_giaban = new JLabel();
                lb_giaban.setText("Giá bán: ");
                lb_giaban.setForeground(Color.white);
                tf_giaban = new JTextField();
                tf_giaban.setBackground(Color.BLACK);
                tf_giaban.setForeground(Color.white);
                tf_giaban.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_giaban);
                panel_themsp_mid_layout.add(tf_giaban);
                JLabel lb_gianhap = new JLabel();
                lb_gianhap.setText("Giá nhập: ");
                lb_gianhap.setForeground(Color.white);
                tf_gianhap = new JTextField();
                tf_gianhap.setBackground(Color.BLACK);
                tf_gianhap.setForeground(Color.white);
                tf_gianhap.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_gianhap);
                panel_themsp_mid_layout.add(tf_gianhap);
                JLabel lb_maton = new JLabel();
                lb_maton.setText("Mã tồn: ");
                lb_maton.setForeground(Color.white);
                tf_maton = new JTextField(KhoDAO.getInstance().autoUpdateMaTon());
                tf_maton.setBackground(Color.BLACK);
                tf_maton.setForeground(Color.white);
                tf_maton.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_maton);
                panel_themsp_mid_layout.add(tf_maton);
                JLabel lb_xuatxu = new JLabel();
                lb_xuatxu.setText("Xuất xứ: ");
                lb_xuatxu.setForeground(Color.white);
                tf_xuatxu = new JTextField();
                tf_xuatxu.setBackground(Color.BLACK);
                tf_xuatxu.setForeground(Color.white);
                tf_xuatxu.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_xuatxu);
                panel_themsp_mid_layout.add(tf_xuatxu);
                JLabel lb_trongluong = new JLabel();
                lb_trongluong.setText("Trọng lượng: ");
                lb_trongluong.setForeground(Color.white);
                tf_trongluong = new JTextField();
                tf_trongluong.setBackground(Color.BLACK);
                tf_trongluong.setForeground(Color.white);
                tf_trongluong.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_trongluong);
                panel_themsp_mid_layout.add(tf_trongluong);
                JLabel lb_kichthuocmanhinh = new JLabel();
                lb_kichthuocmanhinh.setText("<html>Kích thước màn hình: </html>");
                lb_kichthuocmanhinh.setForeground(Color.white);
                tf_kichthuocmanhinh = new JTextField();
                tf_kichthuocmanhinh.setBackground(Color.BLACK);
                tf_kichthuocmanhinh.setForeground(Color.white);
                tf_kichthuocmanhinh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_kichthuocmanhinh);
                panel_themsp_mid_layout.add(tf_kichthuocmanhinh);
                JLabel lb_dungluongdt = new JLabel();
                lb_dungluongdt.setText("Dung lượng dt: ");
                lb_dungluongdt.setForeground(Color.white);
                tf_dungluongdt = new JTextField();
                tf_dungluongdt.setBackground(Color.BLACK);
                tf_dungluongdt.setForeground(Color.white);
                tf_dungluongdt.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_dungluongdt);
                panel_themsp_mid_layout.add(tf_dungluongdt);
                JLabel lb_ram = new JLabel();
                lb_ram.setText("Ram: ");
                lb_ram.setForeground(Color.white);
                tf_ram = new JTextField();
                tf_ram.setBackground(Color.BLACK);
                tf_ram.setForeground(Color.white);
                tf_ram.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_ram);
                panel_themsp_mid_layout.add(tf_ram);
                JLabel lb_baohanh = new JLabel();
                lb_baohanh.setText("Bảo hành: ");
                lb_baohanh.setForeground(Color.white);
                tf_baohanh = new JTextField();
                tf_baohanh.setBackground(Color.BLACK);
                tf_baohanh.setForeground(Color.white);
                tf_baohanh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                panel_themsp_mid_layout.add(lb_baohanh);
                panel_themsp_mid_layout.add(tf_baohanh);
                JLabel lb_mancc = new JLabel();
                lb_mancc.setText("Tên NCC: ");
                lb_mancc.setForeground(Color.white);
                cb_tenncc1 = new JComboBox<>();
                cb_tenncc1.removeAllItems();
                ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
                dsNCC = NhaCungCapDAO.getInstance().selectAll();
                for (NhaCungCap ncc : dsNCC) {
                    cb_tenncc1.addItem(ncc.getTenNCC());
                }
                panel_themsp_mid_layout.add(lb_mancc);
                panel_themsp_mid_layout.add(cb_tenncc1);
                btn_xacnhanthem = new JButton("Xác nhận");
                btn_xacnhanthem.addActionListener(this);
                btn_xacnhanthem.setPreferredSize(new Dimension(150, 30));
                btn_huythem = new JButton("Hủy");
                btn_huythem.addActionListener(this);
                btn_huythem.setPreferredSize(new Dimension(150, 30));
                panel_themsp_bottom_layout.add(btn_xacnhanthem);
                panel_themsp_bottom_layout.add(btn_huythem);
                panel_right_bottom.removeAll();
                panel_right_bottom.add(panel_themsp, BorderLayout.CENTER);
                panel_right_bottom.revalidate();
                panel_right_bottom.repaint();
            } else if (isNCCPanel) {
                panel_themncc = new JPanel();
                panel_themncc.setLayout(new BorderLayout());
                JPanel panel_themncc_top = new JPanel(new GridBagLayout());
                panel_themncc_top.setBackground(new Color(51, 51, 51));
                panel_themncc_top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
                gbc.fill = GridBagConstraints.HORIZONTAL; // Thành phần lấp đầy theo chiều ngang

                // Mã NCC
                JLabel lb_nccmancc = new JLabel("Mã NCC:");
                lb_nccmancc.setForeground(Color.white);
                gbc.gridx = 0; // Cột đầu tiên
                gbc.gridy = 0; // Hàng đầu tiên
                gbc.weightx = 0.3; // Tỷ lệ phân bổ không gian ngang
                panel_themncc_top.add(lb_nccmancc, gbc);

                tf_nccmancc = new JTextField(NhaCungCapDAO.getInstance().autoUpdateMaNCC());
                tf_nccmancc.setPreferredSize(new Dimension(20, 45));
                tf_nccmancc.setBackground(Color.BLACK);
                tf_nccmancc.setForeground(Color.white);
                tf_nccmancc.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                gbc.gridx = 1; // Cột thứ hai
                gbc.gridy = 0; // Hàng đầu tiên
                gbc.weightx = 0.7; // Tỷ lệ phân bổ không gian ngang
                panel_themncc_top.add(tf_nccmancc, gbc);

                // Tên NCC
                JLabel lb_ncctenncc = new JLabel("Tên NCC:");
                lb_ncctenncc.setForeground(Color.white);
                gbc.gridx = 0; // Cột đầu tiên
                gbc.gridy = 1; // Hàng thứ hai
                gbc.weightx = 0.3;
                panel_themncc_top.add(lb_ncctenncc, gbc);

                tf_ncctenncc = new JTextField();
                tf_ncctenncc.setPreferredSize(new Dimension(20, 45));
                tf_ncctenncc.setBackground(Color.BLACK);
                tf_ncctenncc.setForeground(Color.white);
                tf_ncctenncc.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                gbc.gridx = 1; // Cột thứ hai
                gbc.gridy = 1; // Hàng thứ hai
                gbc.weightx = 0.7;
                panel_themncc_top.add(tf_ncctenncc, gbc);

                // Quốc gia
                JLabel lb_nccquocgia = new JLabel("Quốc gia:");
                lb_nccquocgia.setForeground(Color.white);
                gbc.gridx = 0; // Cột đầu tiên
                gbc.gridy = 2; // Hàng thứ ba
                gbc.weightx = 0.3;
                panel_themncc_top.add(lb_nccquocgia, gbc);

                tf_nccquocgia = new JTextField();
                tf_nccquocgia.setPreferredSize(new Dimension(20, 45));
                tf_nccquocgia.setBackground(Color.BLACK);
                tf_nccquocgia.setForeground(Color.white);
                tf_nccquocgia.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                gbc.gridx = 1; // Cột thứ hai
                gbc.gridy = 2; // Hàng thứ ba
                gbc.weightx = 0.7;
                panel_themncc_top.add(tf_nccquocgia, gbc);

                JPanel panel_themncc_bottom = new JPanel();
                panel_themncc_bottom.setLayout(new GridLayout(1, 2));
                btn_xacnhanthemncc = new JButton("Xác nhận");
                btn_xacnhanthemncc.setFocusPainted(false);
                btn_xacnhanthemncc.addActionListener(this);
                panel_themncc_bottom.add(btn_xacnhanthemncc);
                btn_huythemncc = new JButton("Hủy");
                btn_huythemncc.addActionListener(this);
                btn_huythemncc.setFocusPainted(false);
                panel_themncc_bottom.add(btn_huythemncc);

                panel_themncc.add(panel_themncc_top, BorderLayout.CENTER);
                panel_themncc.add(panel_themncc_bottom, BorderLayout.SOUTH);
                panel_right_bottom.removeAll();
                panel_right_bottom.add(panel_themncc, BorderLayout.CENTER);
                panel_right_bottom.revalidate();
                panel_right_bottom.repaint();
            } else if (isPhieuNhap) {
                panel_themphieunhap = new JPanel();
                panel_themphieunhap.setBackground(new Color(51, 51, 51));
                panel_themphieunhap.setLayout(new GridBagLayout());
                GridBagConstraints gbc_themphieunhap = new GridBagConstraints();
                gbc_themphieunhap.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
                gbc_themphieunhap.fill = GridBagConstraints.HORIZONTAL; // Thành phần lấp đầy theo chiều ngang
                JLabel lb_tenncc = new JLabel("Tên NCC:");
                lb_tenncc.setForeground(Color.white);
                gbc_themphieunhap.gridx = 0;
                gbc_themphieunhap.gridy = 0;
                gbc_themphieunhap.gridwidth = 1;
                gbc_themphieunhap.gridheight = 1;
                gbc_themphieunhap.weightx = 0;
                gbc_themphieunhap.weighty = 1;
                panel_themphieunhap.add(lb_tenncc, gbc_themphieunhap);
                cb_tenncc = new JComboBox<>();
                cb_tenncc.addActionListener(this);
                cb_tenncc.setPreferredSize(new Dimension(150, 45));
                gbc_themphieunhap.gridx = 1;
                gbc_themphieunhap.gridy = 0;
                gbc_themphieunhap.gridwidth = 1;
                gbc_themphieunhap.gridheight = 1;
                gbc_themphieunhap.weightx = 1;
                gbc_themphieunhap.weighty = 1;
                panel_themphieunhap.add(cb_tenncc, gbc_themphieunhap);

                JLabel lb_tendtpn = new JLabel("Tên điện thoại:");
                lb_tendtpn.setForeground(Color.white);
                gbc_themphieunhap.gridx = 0;
                gbc_themphieunhap.gridy = 1;
                gbc_themphieunhap.gridwidth = 1;
                gbc_themphieunhap.gridheight = 1;
                gbc_themphieunhap.weightx = 0;
                gbc_themphieunhap.weighty = 1;
                panel_themphieunhap.add(lb_tendtpn, gbc_themphieunhap);
                cb_tendt = new JComboBox<>();
                cb_tendt.addActionListener(this);
                cb_tendt.setPreferredSize(new Dimension(150, 45));
                gbc_themphieunhap.gridx = 1;
                gbc_themphieunhap.gridy = 1;
                gbc_themphieunhap.gridwidth = 1;
                gbc_themphieunhap.gridheight = 1;
                gbc_themphieunhap.weightx = 1;
                gbc_themphieunhap.weighty = 1;
                panel_themphieunhap.add(cb_tendt, gbc_themphieunhap);

                JLabel lb_sl = new JLabel("Số lượng:");
                lb_sl.setForeground(Color.white);
                gbc_themphieunhap.gridx = 0;
                gbc_themphieunhap.gridy = 2;
                gbc_themphieunhap.gridwidth = 1;
                gbc_themphieunhap.gridheight = 1;
                gbc_themphieunhap.weightx = 0;
                gbc_themphieunhap.weighty = 1;
                panel_themphieunhap.add(lb_sl, gbc_themphieunhap);
                tf_sl = new JTextField();
                tf_sl.setPreferredSize(new Dimension(150, 45));
                gbc_themphieunhap.gridx = 1;
                gbc_themphieunhap.gridy = 2;
                gbc_themphieunhap.gridwidth = 1;
                gbc_themphieunhap.gridheight = 1;
                gbc_themphieunhap.weightx = 1;
                gbc_themphieunhap.weighty = 1;
                panel_themphieunhap.add(tf_sl, gbc_themphieunhap);

                JPanel panel_btn = new JPanel();
                panel_btn.setLayout(new GridLayout(1, 2, 50, 0));
                panel_btn.setBackground(new Color(51, 51, 51));
                gbc_themphieunhap.gridx = 0;
                gbc_themphieunhap.gridy = 3;
                gbc_themphieunhap.gridwidth = 2;
                gbc_themphieunhap.gridheight = 1;
                gbc_themphieunhap.weightx = 1;
                gbc_themphieunhap.weighty = 1;
                panel_themphieunhap.add(panel_btn, gbc_themphieunhap);
                btn_xacnhanthemphieunhap = new JButton("Xác nhận");
                btn_xacnhanthemphieunhap.addActionListener(this);
                panel_btn.add(btn_xacnhanthemphieunhap);
                btn_huythemphieunhap = new JButton("Hủy");
                panel_btn.add(btn_huythemphieunhap);

                cb_tendt.removeAllItems();
                cb_tenncc.removeAllItems();
                ArrayList<DienThoai> dsDT = new ArrayList<>();
                dsDT = DienThoaiDAO.getInstance().selectAll();
                for (DienThoai dt : dsDT) {
                    cb_tendt.addItem(dt.getTenDt());
                }
                ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
                dsNCC = NhaCungCapDAO.getInstance().selectAll();
                for (NhaCungCap ncc : dsNCC) {
                    cb_tenncc.addItem(ncc.getTenNCC());
                }
                panel_right_bottom.removeAll();
                panel_right_bottom.add(panel_themphieunhap, BorderLayout.CENTER);
                panel_right_bottom.revalidate();
                panel_right_bottom.repaint();

            }
        } else if (e.getSource() == cb_tenncc) {
            String maNCC = null;
            String tenNCC = (String) cb_tenncc.getSelectedItem();
            ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
            dsNCC = NhaCungCapDAO.getInstance().selectAll();
            for (NhaCungCap ncc : dsNCC) {
                if (ncc.getTenNCC().equals(tenNCC)) {
                    maNCC = ncc.getMaNCC();
                }
            }
            ArrayList<DienThoai> dsDT = new ArrayList<>();
            dsDT = DienThoaiDAO.getInstance().selectAll();
            cb_tendt.removeAllItems();
            for (DienThoai dt : dsDT) {
                if (dt.getMaNcc().equals(maNCC)) {
                    cb_tendt.addItem(dt.getTenDt());
                }
            }
        } else if (e.getSource() == cb_tendt) {
            String maNCC = null;
            String tenDT = (String) cb_tendt.getSelectedItem();
            ArrayList<DienThoai> dsDT = new ArrayList<>();
            dsDT = DienThoaiDAO.getInstance().selectAll();
            for (DienThoai dt : dsDT) {
                if (dt.getTenDt().equals(tenDT)) {
                    maNCC = dt.getMaNcc();
                }
            }
            ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
            dsNCC = NhaCungCapDAO.getInstance().selectAll();
            cb_tenncc.removeAllItems();
            for (NhaCungCap ncc : dsNCC) {
                if (ncc.getMaNCC().equals(maNCC)) {
                    cb_tenncc.addItem(ncc.getTenNCC());
                }
            }
        } else if (e.getSource() == btn_refresh) {
            if (isSanPhamPanel) {
                panel_right_bottom.removeAll();
                tableModel = DienThoaiDAO.getInstance().loadDataToTable("DienThoai");
                table.setModel(tableModel);
                panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
                panel_right_bottom.add(panel_right_bottom_top, BorderLayout.NORTH);
                panel_right_bottom.revalidate();
                panel_right_bottom.repaint();
            } else if (isNCCPanel) {
                panel_right_bottom.removeAll();
                tableModel = NhaCungCapDAO.getInstance().loadDataToTable("NhaCungCap");
                table.setModel(tableModel);
                panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
                panel_right_bottom.revalidate();
                panel_right_bottom.repaint();
            } else if (isPhieuNhap) {
                panel_right_bottom.removeAll();
                tableModel = PhieuNhapDAO.getInstance().loadDataToTable("PhieuNhap");
                table.setModel(tableModel);
                table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
                table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
                panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
                panel_right_bottom.revalidate();
                panel_right_bottom.repaint();
            } else if (isDonHangPanel) {
                panel_right_bottom.removeAll();
                tableModel = DonHangDAO.getInstance().loadDataToTable("DonHang");
                table.setModel(tableModel);
                table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
                table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());
                panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
                panel_right_bottom.revalidate();
                panel_right_bottom.repaint();
            }
        } else if (e.getSource() == btn_xacnhanthem) {
            Kho kho = new Kho(tf_maton.getText(), 0);
            KhoDAO.getInstance().insert(kho);
            String maNCC = null;
            String tenNCC = (String) cb_tenncc1.getSelectedItem();
            ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
            dsNCC = NhaCungCapDAO.getInstance().selectAll();
            for (NhaCungCap ncc : dsNCC) {
                if (ncc.getTenNCC().equals(tenNCC)) {
                    maNCC = ncc.getMaNCC();
                }
            }
            DienThoai newdt = new DienThoai(tf_madt.getText(), tf_tendt.getText(),
                    Integer.parseInt(tf_giaban.getText()), Integer.parseInt(tf_gianhap.getText()), tf_maton.getText(),
                    tf_xuatxu.getText(), Float.parseFloat(tf_trongluong.getText()),
                    Float.parseFloat(tf_kichthuocmanhinh.getText()), Integer.parseInt(tf_dungluongdt.getText()),
                    Integer.parseInt(tf_ram.getText()), Integer.parseInt(tf_baohanh.getText()), maNCC);
            DienThoaiDAO.getInstance().insert(newdt);
        } else if (e.getSource() == btn_xacnhanthemncc) {
            NhaCungCap newncc = new NhaCungCap(tf_nccmancc.getText(), tf_ncctenncc.getText(),
                    tf_nccquocgia.getText());
            NhaCungCapDAO.getInstance().insert(newncc);
        } else if (e.getSource() == btn_delete) {
            if (isSanPhamPanel) {
                int[] selectRow = table.getSelectedRows();
                ArrayList<DienThoai> dsxoa = new ArrayList<>();
                if (selectRow.length > 0) {
                    for (int row : selectRow) {
                        String ma_dt = String.valueOf(table.getValueAt(row, 0));
                        String ten_dt = String.valueOf(table.getValueAt(row, 1));
                        int gia_ban = Integer.parseInt(String.valueOf(table.getValueAt(row, 2)));
                        int gia_nhap = Integer.parseInt(String.valueOf(table.getValueAt(row, 3)));
                        String ma_ton = String.valueOf(table.getValueAt(row, 4));
                        String xuat_xu = String.valueOf(table.getValueAt(row, 5));
                        float trong_luong = Float.parseFloat(String.valueOf(table.getValueAt(row, 6)));
                        float kich_thuoc_mh = Float.parseFloat(String.valueOf(table.getValueAt(row, 7)));
                        int dung_luong = Integer.parseInt(String.valueOf(table.getValueAt(row, 8)));
                        int ram = Integer.parseInt(String.valueOf(table.getValueAt(row, 9)));
                        int bao_hanh = Integer.parseInt(String.valueOf(table.getValueAt(row, 10)));
                        String ma_ncc = String.valueOf(table.getValueAt(row, 11));

                        DienThoai dienThoai = new DienThoai(ma_dt, ten_dt, gia_ban, gia_nhap, ma_ton, xuat_xu,
                                trong_luong,
                                kich_thuoc_mh, dung_luong, ram, bao_hanh, ma_ncc);
                        dsxoa.add(dienThoai);
                    }
                    StringBuilder dsxoaStr = new StringBuilder();
                    for (DienThoai dt : dsxoa) {
                        dsxoaStr.append("Mã: ").append(dt.getMaDt())
                                .append(", Tên: ").append(dt.getTenDt())
                                .append("\n");
                    }
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xóa các sản phẩm sau?\n" + dsxoaStr,
                            "Xóa sản phẩm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirm == JOptionPane.YES_OPTION) {
                        for (DienThoai dt : dsxoa) {
                            DienThoaiDAO.getInstance().delete(dt);
                        }
                        tableModel = DienThoaiDAO.getInstance().loadDataToTable("DienThoai");
                        table.setModel(tableModel);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui long chon san pham trong table de xoa", "Xoa san pham",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (isNCCPanel) {
                int[] selectRow = table.getSelectedRows();
                ArrayList<NhaCungCap> dsxoa = new ArrayList<>();
                if (selectRow.length > 0) {
                    for (int row : selectRow) {
                        String ma_ncc = String.valueOf(table.getValueAt(row, 0));
                        String ten_ncc = String.valueOf(table.getValueAt(row, 1));
                        String quoc_gia = String.valueOf(table.getValueAt(row, 2));
                        NhaCungCap ncc = new NhaCungCap(ma_ncc, ten_ncc, quoc_gia);
                        dsxoa.add(ncc);
                    }
                    StringBuilder dsxoaStr = new StringBuilder();
                    for (NhaCungCap ncc : dsxoa) {
                        dsxoaStr.append("Mã: ").append(ncc.getMaNCC())
                                .append(", Tên: ").append(ncc.getTenNCC())
                                .append(", Quoc gia: ").append(ncc.getQuocGia())
                                .append("\n");
                    }
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xóa các sản phẩm sau?\n" + dsxoaStr,
                            "Xóa sản phẩm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirm == JOptionPane.YES_OPTION) {
                        for (NhaCungCap ncc : dsxoa) {
                            NhaCungCapDAO.getInstance().delete(ncc);
                        }
                        tableModel = NhaCungCapDAO.getInstance().loadDataToTable("NhaCungCap");
                        table.setModel(tableModel);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui long chon san pham trong table de xoa", "Xoa san pham",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        else if (e.getSource() == btn_thongke) {
            String selectedMonth = (String) cb_thang.getSelectedItem();
            String selectedYear = (String) cb_nam.getSelectedItem();
            panel_thongkebottom_top.removeAll();
            lb_tongtiennhap.setText("Tổng tiền nhập: "
                    + PhieuNhapDAO.getInstance().countSumCondition(selectedMonth, selectedYear) + " VND");
            lb_tongtiennhap.setForeground(Color.white);
            lb_tongtiennhap.setFont(new Font("Arial", Font.BOLD, 20));
            lb_tongtiennhap.setHorizontalAlignment(SwingConstants.CENTER);
            panel_thongkebottom_top.add(lb_tongtiennhap, BorderLayout.CENTER);
            panel_thongkebottom_mid.removeAll();
            tableModel = PhieuNhapDAO.getInstance().loadDataToTableCondition(selectedMonth, selectedYear);
            table.setModel(tableModel);
            table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
            panel_thongkebottom_mid.add(scrollPane_table, BorderLayout.CENTER);
            panel_thongkebottom_mid.revalidate();
            panel_thongkebottom_mid.repaint();

            chartPanel.setChart(
                    PhieuNhapDAO.getInstance().createBarChart(selectedMonth, selectedYear));
            panel_thongkebottom_bottom.removeAll();
            panel_thongkebottom_bottom.add(chartPanel, BorderLayout.CENTER);
            panel_thongkebottom_bottom.revalidate();
            panel_thongkebottom_bottom.repaint();
            panel_thongkenhapkhobottom.revalidate();
            panel_thongkenhapkhobottom.repaint();
        } else if (e.getSource() == btn_edit) {
            if (isSanPhamPanel) {
                int rowSelected = table.getSelectedRow();
                if (rowSelected != -1) {
                    String ma_dt = String.valueOf(table.getValueAt(rowSelected, 0));
                    String ten_dt = String.valueOf(table.getValueAt(rowSelected, 1));
                    String gia_ban = String.valueOf((table.getValueAt(rowSelected, 2)));
                    String gia_nhap = String.valueOf((table.getValueAt(rowSelected, 3)));
                    String ma_ton = String.valueOf(table.getValueAt(rowSelected, 4));
                    String xuat_xu = String.valueOf(table.getValueAt(rowSelected, 5));
                    String trong_luong = String.valueOf(table.getValueAt(rowSelected, 6));
                    String ktmh = String.valueOf(table.getValueAt(rowSelected, 7));
                    String dung_luong = String.valueOf(table.getValueAt(rowSelected, 8));
                    String ram = String.valueOf(table.getValueAt(rowSelected, 9));
                    String bao_hanh = String.valueOf(table.getValueAt(rowSelected, 10));
                    String ma_ncc = String.valueOf(table.getValueAt(rowSelected, 11));
                    DienThoai dt = new DienThoai(ma_dt, ten_dt,
                            Integer.parseInt(gia_ban), Integer.parseInt(gia_nhap), ma_ton,
                            xuat_xu, Float.parseFloat(trong_luong),
                            Float.parseFloat(ktmh), Integer.parseInt(dung_luong),
                            Integer.parseInt(ram), Integer.parseInt(bao_hanh), ma_ncc);
                    editframe editFrame = new editframe(dt);
                }
            } else if (isNCCPanel) {
                int rowSelected = table.getSelectedRow();
                if (rowSelected != -1) {
                    String ma_ncc = String.valueOf(table.getValueAt(rowSelected, 0));
                    String ten_ncc = String.valueOf(table.getValueAt(rowSelected, 1));
                    String quoc_gia = String.valueOf(table.getValueAt(rowSelected, 2));
                    NhaCungCap ncc = new NhaCungCap(ma_ncc, ten_ncc, quoc_gia);
                    editframencc editFrame = new editframencc(ncc);
                }
            }
        } else if (e.getSource() == btn_huythem) {
            panel_right_bottom.removeAll();
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.add(panel_right_bottom_top, BorderLayout.NORTH);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == btn_huythemncc) {
            panel_right_bottom.removeAll();
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == btn_xacnhanthemphieunhap) {
            String maTon = null;
            try {
                // Lấy và kiểm tra dữ liệu đầu vào
                String tenNCC = (String) cb_tenncc.getSelectedItem();
                String tenDT = (String) cb_tendt.getSelectedItem();
                if (tenNCC == null || tenDT == null || tenNCC.isEmpty() || tenDT.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp và điện thoại!");
                    return;
                }
                int sl;
                try {
                    sl = Integer.parseInt(tf_sl.getText());
                    if (sl <= 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương!");
                    return;
                }

                // Lấy mã NCC và DT
                String maNCC = null;
                String maDT = null;
                int gianhap = 0;
                ArrayList<NhaCungCap> dsNCC = NhaCungCapDAO.getInstance().selectAll();
                for (NhaCungCap ncc : dsNCC) {
                    if (ncc.getTenNCC().equals(tenNCC)) {
                        maNCC = ncc.getMaNCC();
                        break;
                    }
                }
                if (maNCC == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhà cung cấp!");
                    return;
                }

                ArrayList<DienThoai> dsDT = DienThoaiDAO.getInstance().selectAll();
                for (DienThoai dt : dsDT) {
                    if (dt.getTenDt().equals(tenDT)) {
                        maDT = dt.getMaDt();
                        gianhap = dt.getGiaNhap();
                        break;
                    }
                }
                if (maDT == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy điện thoại!");
                    return;
                }

                // Tính tổng tiền
                int tongtien = sl * gianhap;

                // Lấy ngày hiện tại
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = currentDate.format(formatter);

                // Lấy mã nhân viên (giả định từ phiên đăng nhập)
                String maNV = "NV001"; // Nên lấy từ giao diện hoặc phiên đăng nhập

                // Kiểm tra phiếu nhập
                ArrayList<PhieuNhap> dsPhieuNhap = PhieuNhapDAO.getInstance().selectAll();
                PhieuNhap phieuNhap = null;
                boolean isNewPhieuNhap = true;

                for (PhieuNhap pn : dsPhieuNhap) {
                    if (pn.getNgayNhap().equals(formattedDate) && pn.getMaNV().equals(maNV)
                            && pn.getMaNCC().equals(maNCC)) {
                        phieuNhap = pn;
                        isNewPhieuNhap = false;
                        break;
                    }
                }

                if (isNewPhieuNhap) {
                    // Tạo phiếu nhập mới
                    phieuNhap = new PhieuNhap(PhieuNhapDAO.getInstance().autoUpdateMaHDN(), maNCC, 1, formattedDate,
                            0, maNV);
                    System.out.println(phieuNhap.getTongTien());
                    PhieuNhapDAO.getInstance().insert(phieuNhap);

                }

                // Xử lý chi tiết phiếu nhập
                ArrayList<ChiTietPhieuNhap> dsCTPN = ChiTietPhieuNhapDAO.getInstance().selectAll();
                boolean foundCTPN = false;

                for (ChiTietPhieuNhap ct : dsCTPN) {
                    if (ct.getMaHDNhap().equals(phieuNhap.getMaHDNhap()) && ct.getMaDT().equals(maDT)) {
                        // Cập nhật chi tiết phiếu nhập nếu trùng maDT
                        ct.setSoLuongNhap(ct.getSoLuongNhap() + sl);
                        ct.setThanhTien(ct.getThanhTien() + tongtien);
                        ChiTietPhieuNhapDAO.getInstance().update(ct);
                        foundCTPN = true;
                        break;
                    }
                }

                if (!foundCTPN) {
                    // Thêm chi tiết phiếu nhập mới và tăng soLoaiDT
                    ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(
                            ChiTietPhieuNhapDAO.getInstance().autoUpdateMaCTPN(),
                            phieuNhap.getMaHDNhap(), gianhap, tongtien, sl, maDT);
                    ChiTietPhieuNhapDAO.getInstance().insert(chiTietPhieuNhap);
                    ArrayList<DienThoai> dsDienThoai = DienThoaiDAO.getInstance().selectAll();
                    for (DienThoai dt : dsDienThoai) {
                        if (dt.getMaDt().equals(maDT)) {
                            maTon = dt.getMaTon();
                            break;
                        }
                    }
                    KhoDAO.getInstance().updateSoLuongTon(maTon, sl);
                    if (!isNewPhieuNhap) {
                        // Cập nhật soLoaiDT và tongtien nếu không phải phiếu nhập mới
                        phieuNhap.setSoLoaiDT(phieuNhap.getSoLoaiDT() + 1);
                    }
                    // Cập nhật tổng tiền của phiếu nhập
                    phieuNhap.setTongTien(phieuNhap.getTongTien() + tongtien);
                    System.out.println(phieuNhap.getTongTien() + "OK");
                    PhieuNhapDAO.getInstance().update(phieuNhap);
                    System.out.println(phieuNhap.getTongTien() + "OKA");
                } else {
                    // Cập nhật tổng tiền của phiếu nhập khi chỉ cập nhật chi tiết
                    phieuNhap.setTongTien(phieuNhap.getTongTien() + tongtien);
                    System.out.println(phieuNhap.getTongTien() + "BA");
                    PhieuNhapDAO.getInstance().update(phieuNhap);
                }

                JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
            }
        } else if (e.getSource() == btn_duyetdh) {
            int rowSelected = table.getSelectedRow();
            if (rowSelected != -1) {
                String maDon = String.valueOf(table.getValueAt(rowSelected, 0));
                String maKH = String.valueOf(table.getValueAt(rowSelected, 1));
                String maNV = String.valueOf(table.getValueAt(rowSelected, 2));
                String diaChi = String.valueOf(table.getValueAt(rowSelected, 3));
                String ngayDat = String.valueOf(table.getValueAt(rowSelected, 4));
                String pttt = String.valueOf(table.getValueAt(rowSelected, 5));
                String tinhTrang = String.valueOf(table.getValueAt(rowSelected, 6));
                int tongTien = Integer.parseInt(String.valueOf(table.getValueAt(rowSelected, 7)));
                DonHang dh = new DonHang(maDon, maKH, maNV, diaChi, ngayDat, pttt, tinhTrang, tongTien);
                DonHangDAO.getInstance().updateDuyet(dh, nhanVien);
                ArrayList<ChiTietDonHang> dsCTDH = ChiTietDonHangDAO.getInstance().selectByCondtion(maDon);
                for (ChiTietDonHang ct : dsCTDH) {
                    String maTon = null;
                    String maCTPN = ct.getMaCTPN();
                    int soLuong = ct.getSoLuong();
                    ChiTietPhieuNhap ctPhieuNhap = ChiTietPhieuNhapDAO.getInstance().selectById1(maCTPN);
                    ArrayList<DienThoai> dsDienThoai = DienThoaiDAO.getInstance().selectAll();
                    for (DienThoai dt : dsDienThoai) {
                        if (dt.getMaDt().equals(ctPhieuNhap.getMaDT())) {
                            maTon = dt.getMaTon();
                            break;
                        }
                    }
                    KhoDAO.getInstance().updateSoLuongTon1(maTon, soLuong);
                }
            }
        } else if (e.getSource() == btn_huydh) {
            int rowSelected = table.getSelectedRow();
            if (rowSelected != -1) {
                String maDon = String.valueOf(table.getValueAt(rowSelected, 0));
                String maKH = String.valueOf(table.getValueAt(rowSelected, 1));
                String maNV = String.valueOf(table.getValueAt(rowSelected, 2));
                String diaChi = String.valueOf(table.getValueAt(rowSelected, 3));
                String ngayDat = String.valueOf(table.getValueAt(rowSelected, 4));
                String pttt = String.valueOf(table.getValueAt(rowSelected, 5));
                String tinhTrang = String.valueOf(table.getValueAt(rowSelected, 6));
                int tongTien = Integer.parseInt(String.valueOf(table.getValueAt(rowSelected, 7)));
                DonHang dh = new DonHang(maDon, maKH, maNV, diaChi, ngayDat, pttt, tinhTrang, tongTien);
                DonHangDAO.getInstance().updateHuy(dh, nhanVien);
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        searchTable(tf_search.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        searchTable(tf_search.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        searchTable(tf_search.getText());
    }
}
