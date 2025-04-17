package Admin_quanlitaikhoan.GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import Admin_quanlitaikhoan.DTO.NhanVien;

public class Login_frame extends JFrame {
    public Login_frame() {
        // Thiết lập JFrame
        setTitle("Đăng Nhập");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa màn hình

        // Tạo panel chính với hình ảnh nền
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load hình ảnh nền
                ImageIcon backgroundImage = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\Admin_quanlitaikhoan\\Icon\\login.png"); 
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        // Tạo panel chứa các thành phần đăng nhập (nửa trong suốt)
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(300, 200));
        loginPanel.setBackground(new Color(0, 0, 0, 150)); // Nửa trong suốt với màu đen

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần

        // Tiêu đề
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        // Tên người dùng
        JLabel usernameLabel = new JLabel("Tên người dùng:");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        // Mật khẩu
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        // Nút đăng nhập
        JButton loginButton = new JButton("Đăng Nhập");
        loginButton.setBackground(new Color(0, 120, 255));
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        // Thêm panel đăng nhập vào backgroundPanel
        backgroundPanel.add(loginPanel);

        // Sự kiện cho nút đăng nhập
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Kiểm tra thông tin đăng nhập (ví dụ đơn giản)
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Chuyển đến frame mới
                    new Admin_frame();
                    dispose(); // Đóng frame đăng nhập
                }
            }
        });

        setVisible(true); // Hiển thị JFrame
    }

    public NhanVien checkLogin(String username, String password) {
    String sql = "SELECT * FROM NhanVien WHERE Username=? AND Password=?";
    try (Connection c = getConnection();
        PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new NhanVien(
                rs.getString("maNV"),
                rs.getString("tennv"),
                rs.getString("chucvu"),
                rs.getString("ttlienlac"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getString("maNQL")
            );
        }
    } catch (Exception e) {
        System.out.println("Login failed: " + e.getMessage());
    }
    return null;
}

}