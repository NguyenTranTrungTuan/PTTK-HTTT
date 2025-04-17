package Admin_quanlitaikhoan.GUI;
import javax.swing.*;
import java.awt.*;

public class Login_frame extends JFrame {
    JPanel panel_login;
    JLayeredPane layeredPane;

    public Login_frame() {
        setTitle("Giao diện đăng nhập");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo JLayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1690, 1040));
        layeredPane.setLayout(new BorderLayout());
        add(layeredPane);

        // Tạo JPanel làm lớp nền
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.DARK_GRAY); // Đặt màu nền
        backgroundPanel.setLayout(new BorderLayout()); // Đặt layout cho backgroundPanel
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER); // Thêm vào lớp nền của JLayeredPane

        // Tạo một panel cho đăng nhập
        panel_login = new JPanel();
        panel_login.setLayout(new BorderLayout());
        panel_login.setBackground(Color.YELLOW); // Đặt màu nền cho panel_login
        panel_login.setPreferredSize(new Dimension(900, 600)); // Đặt kích thước mong muốn cho panel_login
        panel_login.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm panel_login vào backgroundPanel
        backgroundPanel.add(panel_login, BorderLayout.CENTER);

        setVisible(true);
    }
}