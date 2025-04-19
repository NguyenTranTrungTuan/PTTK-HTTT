package Admin_quanlitaikhoan.GUI;

import Admin_quanlitaikhoan.DAO.KhachHang_DAO;
import Admin_quanlitaikhoan.DAO.NhanVien_DAO;
import Admin_quanlitaikhoan.DTO.KhachHang;
import Admin_quanlitaikhoan.DTO.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Admin_frame extends JFrame {
    JPanel panel_left, panel_right, panel_topleft, panel_bottomleft, contentPanel;
    JTable listTable;

    public Admin_frame() {
        // Thiết lập JFrame
        setTitle("Quản Lý Tài Khoản");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa

        // Thiết lập bố cục chính
        setLayout(new BorderLayout());

        // Tạo bảng bên trái (điều hướng)
        panel_left = new JPanel();
        panel_left.setPreferredSize(new Dimension(400, 1040)); // Chiều rộng 400px
        panel_left.setBackground(new Color(52, 73, 85)); // #344955 - Xanh dương đậm
        panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));

        // Tạo panel_topleft (hiển thị thông tin người dùng)
        panel_topleft = new JPanel();
        panel_topleft.setBackground(new Color(74, 144, 226)); // #4A90E2 - Xanh dương nhạt
        panel_topleft.setLayout(new BoxLayout(panel_topleft, BoxLayout.Y_AXIS));
        panel_topleft.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding
        JLabel userName = new JLabel("Emily Johnson");
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userName.setForeground(new Color(17, 45, 78)); // #112D4E - Xanh đen đậm
        userName.setFont(new Font("Roboto", Font.BOLD, 18));
        JLabel userRole = new JLabel("Quản Trị Viên");
        userRole.setAlignmentX(Component.CENTER_ALIGNMENT);
        userRole.setForeground(new Color(17, 45, 78)); // #112D4E - Xanh đen đậm
        userRole.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel_topleft.add(userName);
        panel_topleft.add(Box.createVerticalStrut(10));
        panel_topleft.add(userRole);

        // Tạo panel_bottomleft (hiển thị danh mục điều hướng)
        panel_bottomleft = new JPanel();
        panel_bottomleft.setBackground(new Color(52, 73, 85)); // #344955 - Xanh dương đậm
        panel_bottomleft.setLayout(new BoxLayout(panel_bottomleft, BoxLayout.Y_AXIS));
        panel_bottomleft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        String[] navItems = {"Khách Hàng", "Nhân Viên"};
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(360, 40));
            navButton.setBackground(new Color(52, 73, 85)); // #344955 - Xanh dương đậm
            navButton.setForeground(new Color(249, 247, 247)); // #F9F7F7 - Chữ trắng nhạt
            panel_bottomleft.add(navButton);
            panel_bottomleft.add(Box.createVerticalStrut(10));

            // Thêm sự kiện cho nút
            navButton.addActionListener(e -> {
                if (item.equals("Khách Hàng")) {
                    loadKhachHangData();
                } else if (item.equals("Nhân Viên")) {
                    loadNhanVienData();
                }
            });
        }

        // Thêm panel_topleft và panel_bottomleft vào panel_left
        panel_left.add(panel_topleft);
        panel_left.add(Box.createVerticalStrut(10));
        panel_left.add(panel_bottomleft);
        panel_left.add(Box.createVerticalGlue());

        // Tạo bảng bên phải (nội dung)
        panel_right = new JPanel();
        panel_right.setBackground(new Color(219, 226, 239)); // #DBE2EF - Xanh nhạt rất nhẹ
        panel_right.setLayout(new BorderLayout());

        // Tạo header panel cho thanh tìm kiếm và các nút chức năng
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(219, 226, 239)); // #DBE2EF - Xanh nhạt rất nhẹ
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Thanh tìm kiếm
        JTextField searchField = new JTextField("Tìm kiếm...");
        searchField.setPreferredSize(new Dimension(200, 30));
        headerPanel.add(searchField, BorderLayout.WEST);

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setBackground(new Color(219, 226, 239)); // #DBE2EF - Xanh nhạt rất nhẹ

        JButton addButton = new JButton("Thêm");
        addButton.setBackground(new Color(74, 144, 226)); // #4A90E2 - Xanh dương nhạt
        addButton.setForeground(new Color(249, 247, 247)); // #F9F7F7 - Chữ trắng nhạt

        JButton editButton = new JButton("Sửa");
        editButton.setBackground(new Color(74, 144, 226)); // #4A90E2 - Xanh dương nhạt
        editButton.setForeground(new Color(249, 247, 247)); // #F9F7F7 - Chữ trắng nhạt

        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBackground(new Color(244, 162, 97)); // #F4A261 - Cam nhạt
        deleteButton.setForeground(new Color(249, 247, 247)); // #F9F7F7 - Chữ trắng nhạt

        JButton refreshButton = new JButton("Làm mới");
        refreshButton.setBackground(new Color(52, 73, 85)); // #344955 - Xanh dương đậm
        refreshButton.setForeground(new Color(249, 247, 247)); // #F9F7F7 - Chữ trắng nhạt

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        // Tạo content panel cho tiêu đề và bảng danh sách
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(219, 226, 239)); // #DBE2EF - Xanh nhạt rất nhẹ
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Thêm headerPanel và contentPanel vào panel_right
        panel_right.add(headerPanel, BorderLayout.NORTH);
        panel_right.add(contentPanel, BorderLayout.CENTER);

        // Thêm các bảng vào khung
        add(panel_left, BorderLayout.WEST);
        add(panel_right, BorderLayout.CENTER);

        // Hiển thị giao diện ban đầu (mặc định là danh sách nhân viên)
        loadNhanVienData();

        setVisible(true);
    }

    // Phương thức tải dữ liệu khách hàng từ KhachHang_DAO
    private void loadKhachHangData() {
        KhachHang_DAO khDAO = KhachHang_DAO.getInstance();
        ArrayList<KhachHang> listKH = khDAO.selectAll();

        // Tạo bảng dữ liệu
        String[] columnNames = {"Mã KH", "Tên KH", "SĐT", "Email", "Địa Chỉ", "Mật Khẩu"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (KhachHang kh : listKH) {
            Object[] row = {
                kh.getMakh(),
                kh.getTenkh(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiachikh(),
                kh.getPasswordkh()
            };
            model.addRow(row);
        }

        updateTable(model, "Danh Sách Khách Hàng");
    }

    // Phương thức tải dữ liệu nhân viên từ NhanVien_DAO
    private void loadNhanVienData() {
        NhanVien_DAO nvDAO = NhanVien_DAO.getInstance();
        ArrayList<NhanVien> listNV = nvDAO.selectAll();

        // Tạo bảng dữ liệu
        String[] columnNames = {"Mã NV", "Tên NV", "Chức Vụ", "TT Liên Lạc", "Username", "Password", "Mã NQL"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (NhanVien nv : listNV) {
            Object[] row = {
                nv.getMaNV(),
                nv.getTennv(),
                nv.getChucvu(),
                nv.getTtlienlac(),
                nv.getUsername(),
                nv.getPassword(),
                nv.getMaNQL()
            };
            model.addRow(row);
        }

        updateTable(model, "Danh Sách Nhân Viên");
    }

    // Phương thức cập nhật giao diện bảng
    private void updateTable(DefaultTableModel model, String title) {
        contentPanel.removeAll(); // Xóa nội dung cũ

        // Thêm tiêu đề
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(new Color(17, 45, 78)); // #112D4E - Xanh đen đậm
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Thêm bảng
        listTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(listTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Cập nhật giao diện
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}