package Admin_quanlitaikhoan.GUI;
import javax.swing.*;
import java.awt.*;

public class BackgroundImageFrame extends JFrame {

    public BackgroundImageFrame() {
        setTitle("Background Image Example");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một JPanel với ảnh nền
        BackgroundPanel bgPanel = new BackgroundPanel("E:\\Lap trinh Java\\QuanLyBanDienThoai\\Admin_quanlitaikhoan\\Icon\\login.png");
        bgPanel.setLayout(new BorderLayout()); 

        // Thêm các component khác nếu cần
        JLabel label = new JLabel("Hello World");
        label.setForeground(Color.WHITE); 
        label.setHorizontalAlignment(SwingConstants.CENTER);
        bgPanel.add(label, BorderLayout.CENTER);

        setContentPane(bgPanel); // Đặt bgPanel làm content pane
        setLocationRelativeTo(null); // Căn giữa màn hình
        setVisible(true);
    }

    // Lớp JPanel tùy biến để vẽ ảnh nền
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Vẽ ảnh phủ đầy panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
