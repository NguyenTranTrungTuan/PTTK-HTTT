package Admin_quanlitaikhoan.GUI;

import javax.swing.*;
import java.awt.*;

public class Admin_frame extends JFrame {
    JPanel panel_left, panel_right, panel_topleft, panel_bottomleft;

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
        panel_left.setPreferredSize(new Dimension(338, 1040)); // ~20% của 1690px
        panel_left.setBackground(new Color(52, 73, 85)); // #344955 - Xanh dương đậm
        panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));

        // Tạo panel_topleft (hiển thị thông tin người dùng)
        panel_topleft = new JPanel();
        panel_topleft.setBackground(new Color(74, 144, 226)); // #4A90E2 - Xanh dương nhạt
        panel_topleft.setLayout(new BoxLayout(panel_topleft, BoxLayout.Y_AXIS));
        panel_topleft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding
        JLabel userName = new JLabel("Emily Johnson");
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userName.setForeground(new Color(17, 45, 78)); // #112D4E - Xanh đen đậm
        JLabel userRole = new JLabel("Quản Trị Viên");
        userRole.setAlignmentX(Component.CENTER_ALIGNMENT);
        userRole.setForeground(new Color(17, 45, 78)); // #112D4E - Xanh đen đậm
        panel_topleft.add(userName);
        panel_topleft.add(userRole);

        // Tạo panel_bottomleft (hiển thị danh mục điều hướng)
        panel_bottomleft = new JPanel();
        panel_bottomleft.setBackground(new Color(244, 162, 97)); // #F4A261 - Cam nhạt
        panel_bottomleft.setLayout(new BoxLayout(panel_bottomleft, BoxLayout.Y_AXIS));
        panel_bottomleft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding
        String[] navItems = {"Khách Hàng", "Nhân Viên"};
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(300, 40));
            navButton.setBackground(new Color(52, 73, 85)); // #344955 - Nút có màu xanh dương đậm
            navButton.setForeground(new Color(249, 247, 247)); // #F9F7F7 - Chữ trắng nhạt
            panel_bottomleft.add(navButton);
        }

        // Thêm panel_topleft và panel_bottomleft vào panel_left
        panel_left.add(panel_topleft);
        panel_left.add(Box.createVerticalStrut(10)); // Thêm khoảng cách giữa hai panel
        panel_left.add(panel_bottomleft);
        panel_left.add(Box.createVerticalGlue()); // Đẩy các thành phần lên trên

        // Tạo bảng bên phải (nội dung)
        panel_right = new JPanel();
        panel_right.setBackground(new Color(219, 226, 239)); // #DBE2EF - Xanh nhạt rất nhẹ
        panel_right.setLayout(new BorderLayout());

        // Thêm các bảng vào khung
        add(panel_left, BorderLayout.WEST);
        add(panel_right, BorderLayout.CENTER);

        // Thiết lập panel_right
        // Tạo header panel cho thanh tìm kiếm
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(219, 226, 239)); // #DBE2EF - Xanh nhạt rất nhẹ
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        // Thanh tìm kiếm
        JTextField searchField = new JTextField("Tìm kiếm...");
        searchField.setPreferredSize(new Dimension(200, 30));
        headerPanel.add(searchField, BorderLayout.WEST);

        // Tạo content panel cho tiêu đề và bảng danh sách Nhân Viên
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(219, 226, 239)); // #DBE2EF - Xanh nhạt rất nhẹ
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        // Tiêu đề "Danh Sách Nhân Viên" trong contentPanel
        JLabel titleLabel = new JLabel("Danh Sách Nhân Viên");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(new Color(17, 45, 78)); // #112D4E - Xanh đen đậm
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Bảng danh sách Nhân Viên
        String[] columnNames = {"ID", "Tên", "Email", "Số Điện Thoại", "Trạng Thái"};
        Object[][] data = {
            {"001", "Nguyễn Văn A", "a.nguyen@email.com", "0123456789", "Hoạt động"},
            {"002", "Trần Thị B", "b.tran@email.com", "0987654321", "Không hoạt động"},
            {"003", "Lê Văn C", "c.le@email.com", "0912345678", "Hoạt động"}
        };
        JTable listTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(listTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm headerPanel và contentPanel vào panel_right
        panel_right.add(headerPanel, BorderLayout.NORTH);
        panel_right.add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }


}