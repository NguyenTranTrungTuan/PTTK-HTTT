package NhanVienBanHang.GUI;

import NhanVienBanHang.BUS.NhanVien_BUS;
import NhanVienBanHang.Model.NhanVien;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class NhanVienFrame_GUI extends JFrame {
    private JPanel panel_bottomright, panel_topright, panel_right, panel_left, panel_left_top, panel_left_bottom;
    private JButton activeButton = null;
    private JTable table;
    private DefaultTableModel tableModel;
    private NhanVien_BUS bus;

    public NhanVienFrame_GUI(NhanVien nv) {
        setTitle("Nhân Viên Bán Hàng");
        setSize(1690, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bus = new NhanVien_BUS();

        // Setup giao diện
        setupLeftPanel(nv);
        setupRightPanel();
        setupTable();

        setVisible(true);
    }

    private void setupLeftPanel(NhanVien nv) {
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

        JLabel labelRole = new JLabel("<html>" + nv.getChucvu() + "</html>");
        labelRole.setBounds(110, 50, 100, 30);
        labelRole.setForeground(Color.white);
        panel_left_top.add(labelRole);
    }

    private void setupRightPanel() {
        panel_right = new JPanel();
        panel_right.setBackground(Color.BLACK);
        panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS));
        panel_right.setPreferredSize(new Dimension(1470, 0));
        add(panel_right, BorderLayout.CENTER);

        panel_topright = new JPanel();
        panel_topright.setBackground(new Color(51, 51, 51));
        panel_topright.setLayout(new GridLayout(3, 2, 10, 10));
        panel_topright.setPreferredSize(new Dimension(0, 150));
        panel_right.add(panel_topright);

        panel_bottomright = new JPanel();
        panel_bottomright.setBackground(new Color(51, 51, 51));
        panel_bottomright.setLayout(new BorderLayout());
        panel_right.add(panel_bottomright);
    }

    private void setupTable() {
        tableModel = bus.loadTableData();
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Chỉ cho phép chỉnh sửa cột "Tình Trạng"
            }
        };

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());

                if (column == 6) { // Nếu nhấn vào cột "Tình Trạng"
                    String currentStatus = table.getValueAt(row, column).toString();
                    String newStatus = bus.changeStatus(currentStatus);
                    if (newStatus != null) {
                        table.setValueAt(newStatus, row, column);
                        String maDon = table.getValueAt(row, 0).toString();
                        try {
                            boolean updated = bus.updateTinhTrang(maDon, newStatus);
                            if (updated) {
                                JOptionPane.showMessageDialog(null, "Cập nhật tình trạng thành công!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Cập nhật tình trạng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel_bottomright.add(scrollPane, BorderLayout.CENTER);
    }
}