package Admin_quanlitaikhoan.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Login_frame extends JFrame {

    public Login_frame() {
        setTitle("Giao diện đăng nhập");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel nền mờ
        BackgroundPanel backgroundPanel = new BackgroundPanel("E:\\Lap trinh Java\\QuanLyBanDienThoai\\Admin_quanlitaikhoan\\Icon\\login.png");
        backgroundPanel.setLayout(new BorderLayout()); // Dùng BorderLayout

        // Tạo panel_login
        JPanel panel_login = new JPanel();
        panel_login.setPreferredSize(new Dimension(900, 600)); // Kích thước panel_login
        panel_login.setBackground(new Color(255, 255, 255, 200)); // Màu nền trong suốt
        panel_login.setOpaque(true); // Đảm bảo panel không trong suốt
        panel_login.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Viền cho panel_login

        // Thêm JLabel vào panel_login để kiểm tra
        JLabel label = new JLabel("Form đăng nhập", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel_login.add(label);

        // Đặt panel_login vào giữa backgroundPanel
        backgroundPanel.add(panel_login, BorderLayout.CENTER); // Căn giữa với BorderLayout.CENTER

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    // JPanel tùy biến để vẽ ảnh nền
    class BackgroundPanel extends JPanel {
        private Image blurredImage;

        public BackgroundPanel(String imagePath) {
            try {
                // Load ảnh gốc
                BufferedImage original = ImageIO.read(new File(imagePath));

                // Làm mờ ảnh (ví dụ 90% mờ)
                BufferedImage transparentImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = transparentImage.createGraphics();
                g2d.drawImage(original, 0, 0, null);

                // Giảm độ mờ (alpha transparency 90%)
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f); // 90% mờ
                g2d.setComposite(alphaComposite);
                g2d.fillRect(0, 0, original.getWidth(), original.getHeight());
                g2d.dispose();

                blurredImage = transparentImage;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (blurredImage != null) {
                g.drawImage(blurredImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

}
