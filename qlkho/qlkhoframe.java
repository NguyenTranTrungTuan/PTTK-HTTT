package qlkho;

import qlkho.oop.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartPanel;

import qlkho.dao.DienThoaiDAO;
import qlkho.dao.NhaCungCapDAO;
import qlkho.dao.PhieuNhapDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class qlkhoframe extends JFrame implements MouseListener, ActionListener {
    JComboBox<String> cb_thang, cb_nam;
    ChartPanel chartPanel;
    JLabel lb_sanpham, lb_madtshowsp, lb_ncc, lb_phieunhap, lb_thongkenhapkho;
    JTable table;
    DefaultTableModel tableModel;
    JButton btn_add, btn_refresh, btn_xacnhanthem, btn_huythem, btn_delete, btn_thongke, btn_edit;
    JPanel panel_right, panel_themsp, panel_right_bottom, panel_right_bottom_top, panel_thongkenhapkho, panel_right_top,
            panel_thongkebottom_top, panel_thongkebottom_mid, panel_thongkebottom_bottom, panel_thongkenhapkhobottom;
    JScrollPane scrollPane_table;
    JTextField tf_madtshowsp, tf_tendtshowsp, tf_giabanshowsp, tf_gianhapshowsp, tf_matonshowsp, tf_xuatxushowsp,
            tf_trongluongshowsp, tf_kichthuocmanhinhshowsp, tf_dungluongdtshowsp, tf_ramshowsp, tf_baohanhshowsp,
            tf_manccshowsp, tf_madt, tf_tendt, tf_giaban, tf_gianhap, tf_maton, tf_xuatxu,
            tf_trongluong, tf_kichthuocmanhinh, tf_dungluongdt, tf_ram, tf_baohanh,
            tf_mancc, tf_search;
    boolean isSanPhamPanel, isNCCPanel, isPhieuNhap;

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
                // Xử lý sự kiện khi nhấn nút
                int selectedRow = table.getSelectedRow();
                String mahdnhap = table.getValueAt(selectedRow, 0).toString();
                cthdframe cthd = new cthdframe(mahdnhap);
                System.out.println("Button clicked: " + label);
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

    private void addSearchFunctionality() {
        tf_search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                searchTable(tf_search.getText());
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                searchTable(tf_search.getText());
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                searchTable(tf_search.getText());
            }
        });
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

    public qlkhoframe() {
        isSanPhamPanel = true;
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

        lb_ncc = new JLabel("Nha cung cap");
        lb_ncc.addMouseListener(this);
        lb_ncc.setBounds(10, 40, 160, 30);
        lb_ncc.setForeground(Color.white);
        ImageIcon icon_ncc = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_ncc.setIcon(icon_ncc);
        lb_ncc.setIconTextGap(10);
        lb_ncc.addMouseListener(this);
        panel_menu.add(lb_ncc);

        lb_phieunhap = new JLabel("Hoa don nhap");
        lb_phieunhap.addMouseListener(this);
        lb_phieunhap.setBounds(10, 80, 160, 30);
        lb_phieunhap.setForeground(Color.white);
        ImageIcon icon_phieunhap = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_phieunhap.setIcon(icon_phieunhap);
        lb_phieunhap.setIconTextGap(10);
        lb_phieunhap.addMouseListener(this);
        panel_menu.add(lb_phieunhap);

        lb_thongkenhapkho = new JLabel("Thong ke nhap kho");
        lb_thongkenhapkho.addMouseListener(this);
        lb_thongkenhapkho.setBounds(10, 120, 160, 30);
        lb_thongkenhapkho.setForeground(Color.white);
        ImageIcon icon_thongkenhapkho = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\2.png");
        lb_thongkenhapkho.setIcon(icon_thongkenhapkho);
        lb_thongkenhapkho.setIconTextGap(10);
        lb_thongkenhapkho.addMouseListener(this);
        panel_menu.add(lb_thongkenhapkho);

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

        JLabel lb_search = new JLabel();
        ImageIcon icon_search = new ImageIcon(
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\search.png");
        ImageIcon resizedIcon_search = ImageResizer.resizeImageIcon(icon_search, 30, 30);
        lb_search.setIcon(resizedIcon_search);
        lb_search.setBackground(new Color(51, 51, 51));
        lb_search.setOpaque(true);
        panel_right_top.add(lb_search, BorderLayout.WEST);

        tf_search = new JTextField("Tìm kiếm");
        addSearchFunctionality();
        tf_search.setBounds(40, 10, 100, 40);
        tf_search.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_search.setBackground(new Color(51, 51, 51));
        tf_search.setForeground(Color.white);
        panel_right_top.add(tf_search, BorderLayout.CENTER);

        JPanel panel_right_top_btn = new JPanel();
        panel_right_top_btn.setBackground(Color.RED);
        panel_right_top_btn.setLayout(new GridLayout(1, 4));
        panel_right_top.add(panel_right_top_btn, BorderLayout.EAST);

        btn_refresh = new JButton("<html>Lam moi</html>");
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
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\profile.jpg");
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
        lb_kichthuocmanhinhshowsp.setText("<html>Kich thuoc man hinh: </html>");
        lb_kichthuocmanhinhshowsp.setForeground(Color.white);
        tf_kichthuocmanhinhshowsp = new JTextField();
        tf_kichthuocmanhinhshowsp.setEditable(false);
        tf_kichthuocmanhinhshowsp.setBackground(Color.BLACK);
        tf_kichthuocmanhinhshowsp.setForeground(Color.white);
        tf_kichthuocmanhinhshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_kichthuocmanhinhshowsp);
        panel_right_bottom_showsp_right.add(tf_kichthuocmanhinhshowsp);
        JLabel lb_dungluongdtshowsp = new JLabel();
        lb_dungluongdtshowsp.setText("Dung luong dt: ");
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
        lb_baohanhshowsp.setText("Bao hanh: ");
        lb_baohanhshowsp.setForeground(Color.white);
        tf_baohanhshowsp = new JTextField();
        tf_baohanhshowsp.setEditable(false);
        tf_baohanhshowsp.setBackground(Color.BLACK);
        tf_baohanhshowsp.setForeground(Color.white);
        tf_baohanhshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_right_bottom_showsp_right.add(lb_baohanhshowsp);
        panel_right_bottom_showsp_right.add(tf_baohanhshowsp);
        JLabel lb_manccshowsp = new JLabel();
        lb_manccshowsp.setText("Ma NCC: ");
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

        panel_themsp = new JPanel();
        panel_themsp.setBackground(Color.red);
        panel_themsp.setLayout(new BorderLayout());

        JPanel panel_themsp_top = new JPanel();
        panel_themsp_top.setBackground(Color.BLACK);
        panel_themsp_top.setPreferredSize(new Dimension(0, 350));
        panel_themsp_top.setLayout(new BorderLayout());
        panel_themsp.add(panel_themsp_top, BorderLayout.NORTH);
        JPanel panel_themsp_mid = new JPanel();
        panel_themsp_mid.setBackground(Color.BLACK);
        panel_themsp_mid.setLayout(new BorderLayout());
        panel_themsp.add(panel_themsp_mid, BorderLayout.CENTER);
        JPanel panel_themsp_bottom = new JPanel();
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
                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\profile.jpg");
        imgsp.setIcon(imgsanpham);
        addimg.add(imgsp, BorderLayout.CENTER);
        panel_themsp_top_layout.add(addimg);

        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(220, 0));
        empty2.setBackground(new Color(51, 51, 51));
        empty2.setOpaque(true);
        panel_themsp_top_layout.add(empty2);

        JPanel panel_themsp_mid_layout = new JPanel();
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
        tf_madt = new JTextField();
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
        tf_maton = new JTextField();
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
        lb_kichthuocmanhinh.setText("<html>Kich thuoc man hinh: </html>");
        lb_kichthuocmanhinh.setForeground(Color.white);
        tf_kichthuocmanhinh = new JTextField();
        tf_kichthuocmanhinh.setBackground(Color.BLACK);
        tf_kichthuocmanhinh.setForeground(Color.white);
        tf_kichthuocmanhinh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_themsp_mid_layout.add(lb_kichthuocmanhinh);
        panel_themsp_mid_layout.add(tf_kichthuocmanhinh);
        JLabel lb_dungluongdt = new JLabel();
        lb_dungluongdt.setText("Dung luong dt: ");
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
        lb_baohanh.setText("Bao hanh: ");
        lb_baohanh.setForeground(Color.white);
        tf_baohanh = new JTextField();
        tf_baohanh.setBackground(Color.BLACK);
        tf_baohanh.setForeground(Color.white);
        tf_baohanh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_themsp_mid_layout.add(lb_baohanh);
        panel_themsp_mid_layout.add(tf_baohanh);
        JLabel lb_mancc = new JLabel();
        lb_mancc.setText("Ma NCC: ");
        lb_mancc.setForeground(Color.white);
        tf_mancc = new JTextField();
        tf_mancc.setBackground(Color.BLACK);
        tf_mancc.setForeground(Color.white);
        tf_mancc.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_themsp_mid_layout.add(lb_mancc);
        panel_themsp_mid_layout.add(tf_mancc);
        btn_xacnhanthem = new JButton("Xac nhan");
        btn_xacnhanthem.addActionListener(this);
        btn_xacnhanthem.setPreferredSize(new Dimension(150, 30));
        btn_huythem = new JButton("Huy");
        btn_huythem.setPreferredSize(new Dimension(150, 30));
        panel_themsp_bottom_layout.add(btn_xacnhanthem);
        panel_themsp_bottom_layout.add(btn_huythem);

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

        JLabel lb_thang = new JLabel("Thang:");
        lb_thang.setForeground(Color.WHITE);
        lb_thang.setBackground(new Color(51, 51, 51));
        cb_thang = new JComboBox<>(createThang());
        cb_thang.setPreferredSize(new Dimension(100, 30));
        cb_thang.setFocusable(false);

        JLabel lb_nam = new JLabel("Nam:");
        lb_nam.setForeground(Color.WHITE);
        lb_nam.setBackground(new Color(51, 51, 51));
        cb_nam = new JComboBox<>(createNam());
        cb_nam.setPreferredSize(new Dimension(100, 30));
        cb_nam.setFocusable(false);

        btn_thongke = new JButton("Loc");
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
        panel_thongkebottom_top.setPreferredSize(new Dimension(0, 60));
        panel_thongkebottom_top.setBackground(Color.red);
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
            panel_right.removeAll();
            panel_right.add(panel_thongkenhapkho, BorderLayout.CENTER);
            panel_right.revalidate();
            panel_right.repaint();
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
        if (e.getSource() == btn_add && isSanPhamPanel) {
            panel_right_bottom.removeAll();
            panel_right_bottom.add(panel_themsp, BorderLayout.CENTER);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == btn_refresh && isSanPhamPanel) {
            panel_right_bottom.removeAll();
            tableModel = DienThoaiDAO.getInstance().loadDataToTable("DienThoai");
            table.setModel(tableModel);
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.add(panel_right_bottom_top, BorderLayout.NORTH);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        } else if (e.getSource() == btn_xacnhanthem) {
            DienThoai newdt = new DienThoai(tf_madt.getText(), tf_tendt.getText(),
                    Integer.parseInt(tf_giaban.getText()), Integer.parseInt(tf_gianhap.getText()), tf_maton.getText(),
                    tf_xuatxu.getText(), Float.parseFloat(tf_trongluong.getText()),
                    Float.parseFloat(tf_kichthuocmanhinh.getText()), Integer.parseInt(tf_dungluongdt.getText()),
                    Integer.parseInt(tf_ram.getText()), Integer.parseInt(tf_baohanh.getText()), tf_mancc.getText());
            DienThoaiDAO.getInstance().insert(newdt);
        } else if (e.getSource() == btn_delete && isSanPhamPanel) {
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

                    DienThoai dienThoai = new DienThoai(ma_dt, ten_dt, gia_ban, gia_nhap, ma_ton, xuat_xu, trong_luong,
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
        } else if (e.getSource() == btn_thongke) {
            String selectedMonth = (String) cb_thang.getSelectedItem();
            String selectedYear = (String) cb_nam.getSelectedItem();
            chartPanel.setChart(
                    PhieuNhapDAO.getInstance().createLineChart(selectedMonth, selectedYear));
            panel_thongkebottom_bottom.removeAll();
            panel_thongkebottom_bottom.add(chartPanel, BorderLayout.CENTER);
            panel_thongkebottom_bottom.revalidate();
            panel_thongkebottom_bottom.repaint();
            panel_thongkenhapkhobottom.revalidate();
            panel_thongkenhapkhobottom.repaint();
        } else if (e.getSource() == btn_edit && isSanPhamPanel) {
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
        } else if (e.getSource() == btn_huythem) {
            panel_right_bottom.removeAll();
            panel_right_bottom.add(scrollPane_table, BorderLayout.CENTER);
            panel_right_bottom.add(panel_right_bottom_top, BorderLayout.NORTH);
            panel_right_bottom.revalidate();
            panel_right_bottom.repaint();
        }
    }
}
