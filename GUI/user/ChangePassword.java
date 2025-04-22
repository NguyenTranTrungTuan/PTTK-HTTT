package GUI.user;

import java.awt.*;
import javax.swing.*;


public class ChangePassword extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU", JLabel.CENTER);
    protected JPasswordField txtMatKhau_Old = new JPasswordField();
    protected JPasswordField txtMatKhau_New = new JPasswordField();
    protected MyButton btnConfirm = new MyButton("XÁC NHẬN");

    public ChangePassword() {
        initComponents();
    }

    private void initComponents() {
        setTitle("ĐỔI MẬT KHẨU");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        txtMatKhau_Old.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtMatKhau_Old.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtMatKhau_Old.setBorder(BorderFactory.createTitledBorder("Nhập Mật khẩu cũ"));
        panel.add(txtMatKhau_Old);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtMatKhau_New.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtMatKhau_New.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtMatKhau_New.setBorder(BorderFactory.createTitledBorder("Nhập Mật khẩu mới"));
        panel.add(txtMatKhau_New);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirm.setBackground(Color.decode("#00B4DB"));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnConfirm);

        setContentPane(panel);
        setVisible(true);
    }
}
