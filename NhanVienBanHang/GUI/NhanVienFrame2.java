package NhanVienBanHang.GUI;

import NhanVienBanHang.DAO.ChiTietDonHang_DAO;
import NhanVienBanHang.DAO.DonHang_DAO;
import NhanVienBanHang.Model.NhanVien;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.table.*;

public class NhanVienFrame2 extends JFrame {
    private JButton activeButton = null;
    private JPanel panel_bottomright, panel_topright, panel_right, panel_left, panel_left_top, panel_left_bottom;

    // Custom ComboBoxUI to change arrow button color
    public static class CustomComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton arrowButton = new JButton() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(new Color(130, 130, 130)); // Dark gray arrow
                    int x = getWidth() / 2 - 3;
                    int y = getHeight() / 2 - 2;
                    int[] xPoints = {x, x + 6, x + 3};
                    int[] yPoints = {y, y, y + 5};
                    g2.fillPolygon(xPoints, yPoints, 3); // Draw downward arrow
                    g2.dispose();
                }
            };
            arrowButton.setBackground(new Color(51, 51, 51)); // Match table background
            arrowButton.setBorder(BorderFactory.createEmptyBorder());
            arrowButton.setFocusPainted(false);
            return arrowButton;
        }
    }

    public class CustomScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(130, 130, 130, 100));
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 5, 5);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(51, 51, 51));
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

    public NhanVienFrame2(NhanVien nv) {
        setTitle("Nhan Vien Ban Hang");
        setSize(1690, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Left panel setup
        panel_left = new JPanel();
        panel_left.setPreferredSize(new Dimension(300, 0));
        panel_left.setBackground(Color.black);
        add(panel_left, BorderLayout.WEST);
        panel_left.setLayout(null);

        panel_left_top = new JPanel();
        panel_left_top.setLayout(null);
        panel_left_top.setBounds(10, 10, 250, 150);
        panel_left_top.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_top);

        JLabel imageAvatar = new JLabel();
        ImageIcon icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\user.png");
        ImageIcon resizedIcon = ImageResizer.resizeImageIcon(icon, 50, 50);
        imageAvatar.setIcon(resizedIcon);
        imageAvatar.setBounds(40, 10, 90, 80);
        panel_left_top.add(imageAvatar);

        JLabel labelName = new JLabel(nv.getTennv());
        labelName.setBounds(110, 30, 100, 20);
        labelName.setForeground(Color.white);
        panel_left_top.add(labelName);

        JLabel labelRole = new JLabel();
        labelRole.setBounds(110, 50, 100, 30);
        labelRole.setText("<html>" + nv.getChucvu() + "</html>");
        labelRole.setForeground(Color.white);
        panel_left_top.add(labelRole);

        panel_left_bottom = new JPanel();
        panel_left_bottom.setLayout(new BoxLayout(panel_left_bottom, BoxLayout.Y_AXIS));
        panel_left_bottom.setBounds(10, 170, 250, 600);
        panel_left_bottom.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_bottom);

        JLabel labelDanhMuc = new JLabel("Danh Mục");
        labelDanhMuc.setForeground(Color.WHITE);
        labelDanhMuc.setFont(new Font("Arial", Font.BOLD, 20));
        labelDanhMuc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_left_bottom.add(labelDanhMuc);
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnXemDonHang = new JButton("Đơn Hàng");
        btnXemDonHang.setBackground(new Color(51, 51, 51));
        btnXemDonHang.setForeground(Color.WHITE);
        btnXemDonHang.setFocusPainted(false);
        btnXemDonHang.setFont(new Font("Arial", Font.PLAIN, 16));
        btnXemDonHang.setHorizontalAlignment(SwingConstants.LEFT);
        btnXemDonHang.setBorder(new RoundedBorder(10));
        ImageIcon donhang_icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\checklist.png");
        donhang_icon = ImageResizer.resizeImageIcon(donhang_icon, 20, 20);
        btnXemDonHang.setIcon(donhang_icon);
        addHoverEffect(btnXemDonHang, new Color(70, 70, 70), new Color(51, 51, 51));
        panel_left_bottom.add(btnXemDonHang);
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnBaoCao = new JButton("Báo Cáo");
        btnBaoCao.setBackground(new Color(51, 51, 51));
        btnBaoCao.setForeground(Color.WHITE);
        btnBaoCao.setFocusPainted(false);
        btnBaoCao.setFont(new Font("Arial", Font.PLAIN, 16));
        btnBaoCao.setHorizontalAlignment(SwingConstants.LEFT);
        btnBaoCao.setBorder(new RoundedBorder(10));
        ImageIcon baoCaoIcon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\report.png");
        baoCaoIcon = ImageResizer.resizeImageIcon(baoCaoIcon, 20, 20);
        btnBaoCao.setIcon(baoCaoIcon);
        addHoverEffect(btnBaoCao, new Color(70, 70, 70), new Color(51, 51, 51));
        panel_left_bottom.add(btnBaoCao);
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel labelCongCu = new JLabel("Công Cụ");
        labelCongCu.setForeground(Color.WHITE);
        labelCongCu.setFont(new Font("Arial", Font.BOLD, 20));
        labelCongCu.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 10)));
        panel_left_bottom.add(labelCongCu);

        JButton btnChonThoiDiem = new JButton("Chọn Thời Điểm");
        btnChonThoiDiem.setBackground(new Color(51, 51, 51));
        btnChonThoiDiem.setForeground(Color.WHITE);
        btnChonThoiDiem.setFocusPainted(false);
        btnChonThoiDiem.setFont(new Font("Arial", Font.PLAIN, 16));
        btnChonThoiDiem.setHorizontalAlignment(SwingConstants.LEFT);
        btnChonThoiDiem.setBorder(new RoundedBorder(10));
        ImageIcon thoiDiemIcon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\back-in-time.png");
        thoiDiemIcon = ImageResizer.resizeImageIcon(thoiDiemIcon, 20, 20);
        btnChonThoiDiem.setIcon(thoiDiemIcon);
        addHoverEffect(btnChonThoiDiem, new Color(70, 70, 70), new Color(51, 51, 51));
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 10)));
        panel_left_bottom.add(btnChonThoiDiem);

        btnChonThoiDiem.addActionListener(e -> {
            handleButtonClick(btnChonThoiDiem);
            showDateRangePickerDialog();
        });

        panel_right = new JPanel();
        panel_right.setBackground(Color.BLACK);
        panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS));
        panel_right.setPreferredSize(new Dimension(1470, 0));
        add(panel_right, BorderLayout.CENTER);

        panel_right.add(Box.createRigidArea(new Dimension(0, 10)));
        panel_topright = new JPanel();
        panel_topright.setBackground(new Color(51, 51, 51));
        panel_topright.setLayout(new GridLayout(3, 2, 10, 10));
        panel_topright.setPreferredSize(new Dimension(0, 150));
        panel_right.add(panel_topright);
        panel_right.add(Box.createRigidArea(new Dimension(0, 10)));

        panel_bottomright = new JPanel();
        panel_bottomright.setBackground(new Color(51, 51, 51));
        panel_bottomright.setLayout(new BorderLayout());
        panel_right.add(panel_bottomright);

        setupTable();

        btnXemDonHang.addActionListener(e -> {
            handleButtonClick(btnXemDonHang);
            panel_bottomright.removeAll();
            panel_topright.removeAll();
            setupTable();
            panel_bottomright.revalidate();
            panel_bottomright.repaint();
        });

        btnBaoCao.addActionListener(e -> handleButtonClick(btnBaoCao));
        handleButtonClick(btnXemDonHang);

        setVisible(true);
    }

    private void setupTable() {
        DefaultTableModel tableModel = DonHang_DAO.getInstance().loadDataToTable("DonHang");
        tableModel.addColumn("Chi Tiết Đơn Hàng");
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == tableModel.getColumnCount() - 1; // Tình Trạng (6) and Chi Tiết
            }
        };

            // Kiểm tra và xử lý các đơn hàng có tình trạng "Chưa Xử Lý"
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String tinhTrang = tableModel.getValueAt(i, 6).toString();
            if ("Chưa Xử Lý".equalsIgnoreCase(tinhTrang)) {
                tableModel.setValueAt(null, i, 2); // Đặt mã nhân viên là null
            }
        }

          // Thêm MouseListener để xử lý sự kiện nhấp chuột vào cột "Tình Trạng"
    table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());

            // Kiểm tra nếu người dùng nhấn vào cột "Tình Trạng" (cột 6)
            if (column == 6) {
                String currentStatus = table.getValueAt(row, column).toString();
                String[] options = {"Chưa Xử Lý", "Đã Xử Lý", "Đã Hủy"};
                String newStatus = (String) JOptionPane.showInputDialog(
                        null,
                        "Chọn trạng thái mới:",
                        "Thay Đổi Tình Trạng",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        currentStatus
                );

                // Nếu người dùng chọn trạng thái mới
                if (newStatus != null && !newStatus.equals(currentStatus)) {
                    table.setValueAt(newStatus, row, column); // Cập nhật giá trị trong bảng

                    // Cập nhật trạng thái trong cơ sở dữ liệu
                    String maDon = table.getValueAt(row, 0).toString();
                    boolean updated = false;
                    try {
                        updated = DonHang_DAO.getInstance().updateTinhTrang(maDon, newStatus);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    if (updated) {
                        JOptionPane.showMessageDialog(null, "Cập nhật tình trạng thành công!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật tình trạng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    });


        

        

        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(table));



        table.setBackground(new Color(51, 51, 51));
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(51, 51, 51));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String maDon = table.getValueAt(selectedRow, 0).toString();
                    String maKH = table.getValueAt(selectedRow, 1).toString();
                    String maNV = table.getValueAt(selectedRow, 2).toString();
                    String diaChiDat = table.getValueAt(selectedRow, 3).toString();
                    String ngayDat = table.getValueAt(selectedRow, 4).toString();
                    String pttt = table.getValueAt(selectedRow, 5).toString();
                    String tinhTrang = table.getValueAt(selectedRow, 6).toString();
                    String tongTien = table.getValueAt(selectedRow, 7).toString();
                    showProductInPanelTopRight(maDon, maKH, maNV, diaChiDat, ngayDat, pttt, tinhTrang, tongTien);
                }
            }
        });

        JScrollPane scrollPane_table = new JScrollPane(table);
        scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
        scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        scrollPane_table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_table.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        panel_bottomright.add(scrollPane_table, BorderLayout.CENTER);

        if (tableModel.getRowCount() > 0) {
            String maDon = tableModel.getValueAt(0, 0).toString();
            String maKH = tableModel.getValueAt(0, 1).toString();
            String maNV = tableModel.getValueAt(0, 2).toString();
            String diaChiDat = tableModel.getValueAt(0, 3).toString();
            String ngayDat = tableModel.getValueAt(0, 4).toString();
            String pttt = tableModel.getValueAt(0, 5).toString();
            String tinhTrang = tableModel.getValueAt(0, 6).toString();
            String tongTien = tableModel.getValueAt(0, 7).toString();
            showProductInPanelTopRight(maDon, maKH, maNV, diaChiDat, ngayDat, pttt, tinhTrang, tongTien);
        }
    }

    private void setupTableByDateRange(Date startDate, Date endDate) {
        DefaultTableModel tableModel = DonHang_DAO.getInstance().loadDataToTableByDateRange("DonHang", startDate, endDate);
        tableModel.addColumn("Chi Tiết Đơn Hàng");
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
          return column == 6 || column == tableModel.getColumnCount() - 1; // Tình Trạng (6) and Chi Tiết
            }
        };

            // Kiểm tra và xử lý các đơn hàng có tình trạng "Chưa Xử Lý"
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String tinhTrang = tableModel.getValueAt(i, 6).toString();
            if ("Chưa Xử Lý".equalsIgnoreCase(tinhTrang)) {
                tableModel.setValueAt(null, i, 2); // Đặt mã nhân viên là null
            }
        }


        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(table));

        table.setBackground(new Color(51, 51, 51));
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(51, 51, 51));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String maDon = table.getValueAt(selectedRow, 0).toString();
                    String maKH = table.getValueAt(selectedRow, 1).toString();
                    String maNV = table.getValueAt(selectedRow, 2).toString();
                    String diaChiDat = table.getValueAt(selectedRow, 3).toString();
                    String ngayDat = table.getValueAt(selectedRow, 4).toString();
                    String pttt = table.getValueAt(selectedRow, 5).toString();
                    String tinhTrang = table.getValueAt(selectedRow, 6).toString();
                    String tongTien = table.getValueAt(selectedRow, 7).toString();
                    showProductInPanelTopRight(maDon, maKH, maNV, diaChiDat, ngayDat, pttt, tinhTrang, tongTien);
                }
            }
        });

        JScrollPane scrollPane_table = new JScrollPane(table);
        scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
        scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        scrollPane_table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_table.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        panel_bottomright.removeAll();
        panel_bottomright.add(scrollPane_table, BorderLayout.CENTER);
        panel_bottomright.revalidate();
        panel_bottomright.repaint();

        if (tableModel.getRowCount() > 0) {
            String maDon = tableModel.getValueAt(0, 0).toString();
            String maKH = tableModel.getValueAt(0, 1).toString();
            String maNV = tableModel.getValueAt(0, 2).toString();
            String diaChiDat = tableModel.getValueAt(0, 3).toString();
            String ngayDat = tableModel.getValueAt(0, 4).toString();
            String pttt = tableModel.getValueAt(0, 5).toString();
            String tinhTrang = tableModel.getValueAt(0, 6).toString();
            String tongTien = tableModel.getValueAt(0, 7).toString();
            showProductInPanelTopRight(maDon, maKH, maNV, diaChiDat, ngayDat, pttt, tinhTrang, tongTien);
        } else {
            panel_topright.removeAll();
            panel_topright.revalidate();
            panel_topright.repaint();
            ShowNotification("Không có đơn hàng trong khoảng thời gian đã chọn!", "Thông Báo");
        }
    }

    private void showDateRangePickerDialog() {
        JDialog dateDialog = new JDialog(this, "Chọn Khoảng Thời Gian", true);
        dateDialog.setSize(500, 250);
        dateDialog.setLocationRelativeTo(this);
        dateDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel startLabel = new JLabel("Ngày Bắt Đầu:");
        startLabel.setForeground(Color.WHITE);
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));
        JComponent startEditor = startDateSpinner.getEditor();
        JFormattedTextField startField = ((JSpinner.DefaultEditor) startEditor).getTextField();
        startField.setBackground(new Color(51, 51, 51));
        startField.setForeground(Color.WHITE);
        startField.setBorder(BorderFactory.createLineBorder(new Color(130, 130, 130)));

        JLabel endLabel = new JLabel("Ngày Kết Thúc:");
        endLabel.setForeground(Color.WHITE);
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd"));
        JComponent endEditor = endDateSpinner.getEditor();
        JFormattedTextField endField = ((JSpinner.DefaultEditor) endEditor).getTextField();
        endField.setBackground(new Color(51, 51, 51));
        endField.setForeground(Color.WHITE);
        endField.setBorder(BorderFactory.createLineBorder(new Color(130, 130, 130)));

        JButton confirmButton = new JButton("Xác Nhận");
        confirmButton.setBackground(new Color(70, 130, 180));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(70, 130, 180));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        gbc.gridx = 0; gbc.gridy = 0;
        dateDialog.add(startLabel, gbc);
        gbc.gridx = 1;
        dateDialog.add(startDateSpinner, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        dateDialog.add(endLabel, gbc);
        gbc.gridx = 1;
        dateDialog.add(endDateSpinner, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        dateDialog.add(cancelButton, gbc);
        gbc.gridx = 1;
        dateDialog.add(confirmButton, gbc);

        confirmButton.addActionListener(e -> {
            Date startDate = (Date) startDateSpinner.getValue();
            Date endDate = (Date) endDateSpinner.getValue();
            if (startDate == null || endDate == null) {
                ShowNotification("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc!", "Lỗi");
                return;
            }
            if (startDate.after(endDate)) {
                ShowNotification("Ngày bắt đầu phải trước ngày kết thúc!", "Lỗi");
                return;
            }
            setupTableByDateRange(startDate, endDate);
            dateDialog.dispose();
        });

        cancelButton.addActionListener(e -> dateDialog.dispose());

        dateDialog.getContentPane().setBackground(new Color(51, 51, 51));
        dateDialog.setVisible(true);
    }

    private void addHoverEffect(JButton button, Color hoverColor, Color defaultColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(defaultColor);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                button.setBounds(button.getX() + 2, button.getY() + 2, button.getWidth() - 4, button.getHeight() - 4);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                button.setBounds(button.getX() - 2, button.getY() - 2, button.getWidth() + 4, button.getHeight() + 4);
            }
        });
    }

    private void handleButtonClick(JButton button) {
        if (activeButton != null) {
            activeButton.setFont(new Font("Arial", Font.PLAIN, 16));
            activeButton.setPreferredSize(null);
            activeButton.revalidate();
        }
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(button.getWidth() + 20, button.getHeight() + 10));
        button.revalidate();
        activeButton = button;
    }

    private void addLabelAndTextField(JPanel parentPanel, String labelText, String valueText) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField textField = new JTextField(valueText);
        textField.setEditable(false);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        textField.setPreferredSize(new Dimension(150, 25));
        parentPanel.add(label);
        parentPanel.add(textField);
    }

    private void showProductInPanelTopRight(String MaDon, String MaKH, String MaNV, String DiaChiDat, String NgayDat, String PTTT, String TinhTrang, String TongTien) {
        panel_topright.removeAll();
        addLabelAndTextField(panel_topright, "Mã Đơn Hàng:", MaDon);
        addLabelAndTextField(panel_topright, "Mã Khách Hàng:", MaKH);
        addLabelAndTextField(panel_topright, "Mã Nhân Viên:", MaNV);
        addLabelAndTextField(panel_topright, "Địa Chỉ Đặt Hàng:", DiaChiDat);
        addLabelAndTextField(panel_topright, "Ngày Đặt:", NgayDat);
        addLabelAndTextField(panel_topright, "Phương Thức Thanh Toán:", PTTT);
        addLabelAndTextField(panel_topright, "Tình Trạng:", TinhTrang);
        addLabelAndTextField(panel_topright, "Tổng Tiền:", TongTien);
        panel_topright.revalidate();
        panel_topright.repaint();
    }

    private void ShowNotification(String message, String title) {
        JPanel panelThongBao = new JPanel();
        panelThongBao.setLayout(new BoxLayout(panelThongBao, BoxLayout.Y_AXIS));
        panelThongBao.setBackground(new Color(51, 51, 51));
        JLabel labelMessage = new JLabel(message);
        labelMessage.setForeground(Color.WHITE);
        labelMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        labelMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelThongBao.add(Box.createRigidArea(new Dimension(0, 10)));
        panelThongBao.add(labelMessage);
        panelThongBao.add(Box.createRigidArea(new Dimension(0, 10)));
        JOptionPane.showMessageDialog(this, panelThongBao, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setText("Chi Tiết");
            setBackground(new Color(70, 130, 180));
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 12));
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(new Color(100, 149, 237));
            } else {
                setBackground(new Color(70, 130, 180));
            }
            return this;
        }
    }

    public static class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JTable table;
        private JButton button;
        private boolean isPushed;

        public ButtonEditor(JTable table) {
            this.table = table;
            button = new JButton("Chi Tiết");
            button.setOpaque(true);
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String maDon = table.getValueAt(row, 0).toString();
                    showChiTietDonHang(maDon);
                }
            }
            isPushed = false;
            return null;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        private void showChiTietDonHang(String maDon) {
            DefaultTableModel chiTietTableModel = ChiTietDonHang_DAO.getInstance().loadDataToTableByMaDon(maDon);
            if (chiTietTableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu chi tiết cho Mã Đơn: " + maDon, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JTable chiTietTable = new JTable(chiTietTableModel);
            chiTietTable.setBackground(new Color(51, 51, 51));
            chiTietTable.setForeground(Color.WHITE);
            chiTietTable.setGridColor(Color.BLACK);
            chiTietTable.setRowHeight(30);
            chiTietTable.getTableHeader().setBackground(new Color(51, 51, 51));
            chiTietTable.getTableHeader().setForeground(Color.WHITE);
            JScrollPane scrollPane = new JScrollPane(chiTietTable);
            scrollPane.getViewport().setBackground(new Color(51, 51, 51));
            scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            JDialog dialog = new JDialog((Frame) null, "Chi Tiết Đơn Hàng - Mã Đơn: " + maDon, true);
            dialog.setSize(800, 400);
            dialog.setLocationRelativeTo(null);
            dialog.add(scrollPane);
            dialog.setVisible(true);
        }
    }

}