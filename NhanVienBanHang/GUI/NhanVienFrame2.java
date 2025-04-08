package NhanVienBanHang.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

public class NhanVienFrame2 extends JFrame implements MouseListener,ActionListener {
    
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
  
    public NhanVienFrame2() {
        
        setTitle("Nhan Vien Ban Hang");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 


        JPanel panel_left = new JPanel();
        panel_left.setPreferredSize(new Dimension(300, 0));
        panel_left.setBackground(Color.black);
        add(panel_left, BorderLayout.WEST);
        panel_left.setLayout(null);
        JPanel panel_left_top = new JPanel();
        panel_left_top.setLayout(null);
        panel_left_top.setBounds(10, 10, 250, 100);
        panel_left_top.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_top);
        JLabel imageAvatar = new JLabel();
        ImageIcon icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\user.png");
        ImageIcon resizedIcon = ImageResizer.resizeImageIcon(icon, 50, 50);
        imageAvatar.setIcon(resizedIcon);
        imageAvatar.setBounds(40, 10, 90, 80);
        panel_left_top.add(imageAvatar);
        JLabel labelName = new JLabel("Nguyễn Văn A");
        labelName.setBounds(110, 30, 100, 20);
        labelName.setForeground(Color.white);
        panel_left_top.add(labelName);
        JLabel labelRole = new JLabel();
        labelRole.setBounds(110, 50, 100, 30);
        labelRole.setText("<html>Nhân viên</html>");
        labelRole.setForeground(Color.white);
        panel_left_top.add(labelRole);


        JPanel panel_left_bottom = new JPanel();
        panel_left_bottom.setLayout(null);
        panel_left_bottom.setBounds(10, 120, 250, 600);
        panel_left_bottom.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_bottom);

        // Tạo font Arial cỡ 20
        Font fontArial20 = new Font("Arial", Font.PLAIN, 18);

        // Đặt chiều rộng của các nút bằng với chiều rộng của panel_left_bottom
        int buttonWidth = 250; // Chiều rộng của panel_left_bottom
        int buttonHeight = 40; // Chiều cao cố định của mỗi nút
        int buttonX = 0; // Đặt nút sát mép trái

        JButton btnDuyetDonHang = new JButton("Duyệt đơn hàng");
        btnDuyetDonHang.setBounds(buttonX, 0, buttonWidth, buttonHeight); // Chiều rộng bằng với panel_left_bottom
        btnDuyetDonHang.setBackground(Color.BLACK);
        btnDuyetDonHang.setForeground(Color.WHITE);
        btnDuyetDonHang.setFocusPainted(false);
        panel_left_bottom.add(btnDuyetDonHang);

        JButton btnHuyDonHang = new JButton("Hủy đơn hàng");
        btnHuyDonHang.setBounds(buttonX, 50, buttonWidth, buttonHeight);
        btnHuyDonHang.setBackground(Color.BLACK);
        btnHuyDonHang.setForeground(Color.WHITE);
        btnHuyDonHang.setFocusPainted(false);
        panel_left_bottom.add(btnHuyDonHang);

        JButton btnInHoaDon = new JButton("In hóa đơn lưu trữ");
        btnInHoaDon.setBounds(buttonX, 100, buttonWidth, buttonHeight);
        btnInHoaDon.setBackground(Color.BLACK);
        btnInHoaDon.setForeground(Color.WHITE);
        btnInHoaDon.setFocusPainted(false);
        panel_left_bottom.add(btnInHoaDon);

        JButton btnXemHoaDon = new JButton("Xem hóa đơn");
        btnXemHoaDon.setBounds(buttonX, 150, buttonWidth, buttonHeight);
        btnXemHoaDon.setBackground(Color.BLACK);
        btnXemHoaDon.setForeground(Color.WHITE);
        btnXemHoaDon.setFocusPainted(false);
        panel_left_bottom.add(btnXemHoaDon);

        JButton btnXemThongKe = new JButton("Xem thống kê báo cáo");
        btnXemThongKe.setBounds(buttonX, 200, buttonWidth, buttonHeight);
        btnXemThongKe.setBackground(Color.BLACK);
        btnXemThongKe.setForeground(Color.WHITE);
        btnXemThongKe.setFocusPainted(false);
        panel_left_bottom.add(btnXemThongKe);

        JButton btnXemDonHang = new JButton("Xem đơn hàng");
        btnXemDonHang.setBounds(buttonX, 250, buttonWidth, buttonHeight);
        btnXemDonHang.setBackground(Color.BLACK);
        btnXemDonHang.setForeground(Color.WHITE);
        btnXemDonHang.setFocusPainted(false);
        panel_left_bottom.add(btnXemDonHang);
    

        btnDuyetDonHang.setFont(fontArial20);
        btnHuyDonHang.setFont(fontArial20);
        btnInHoaDon.setFont(fontArial20);
        btnXemHoaDon.setFont(fontArial20);
        btnXemThongKe.setFont(fontArial20);
        btnXemDonHang.setFont(fontArial20);

        // Thêm hiệu ứng hover màu trắng nhạt cho các nút
        addHoverEffect(btnDuyetDonHang, new Color(211, 211, 211), Color.BLACK); // Màu hover: trắng nhạt
        addHoverEffect(btnHuyDonHang, new Color(211, 211, 211), Color.BLACK);
        addHoverEffect(btnInHoaDon, new Color(211, 211, 211), Color.BLACK);
        addHoverEffect(btnXemHoaDon, new Color(211, 211, 211), Color.BLACK);
        addHoverEffect(btnXemThongKe, new Color(211, 211, 211), Color.BLACK);
        addHoverEffect(btnXemDonHang, new Color(211, 211, 211), Color.BLACK);

        //Thêm icon kế bên các button
        ImageIcon iconDuyetDonHang = ImageResizer.resizeImageIcon(new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\approved-order.png"), 20, 20);
        btnDuyetDonHang.setIcon(iconDuyetDonHang);
        btnDuyetDonHang.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon iconHuyDonHang = ImageResizer.resizeImageIcon(new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\out-of-stock.png"), 20, 20);
        btnHuyDonHang.setIcon(iconHuyDonHang);
        btnHuyDonHang.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon iconInHoaDon = ImageResizer.resizeImageIcon(new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\printer.png"), 20, 20);
        btnInHoaDon.setIcon(iconInHoaDon);
        btnInHoaDon.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon iconXemHoaDon = ImageResizer.resizeImageIcon(new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\bill.png"), 20, 20);
        btnXemHoaDon.setIcon(iconXemHoaDon);
        btnXemHoaDon.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon iconXemThongKe = ImageResizer.resizeImageIcon(new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\result.png"), 20, 20);
        btnXemThongKe.setIcon(iconXemThongKe);
        btnXemThongKe.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon iconXemDonHang = ImageResizer.resizeImageIcon(new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\order.png"), 20, 20);
        btnXemDonHang.setIcon(iconXemDonHang);  
        btnXemDonHang.setHorizontalAlignment(SwingConstants.LEFT);



        //Tạo panel_right để hiển thị thông tin các đơn hàng
        JPanel panel_right = new JPanel();
        panel_right.setBackground(Color.BLACK);
        panel_right.setLayout(new BorderLayout());
        panel_right.setPreferredSize(new Dimension(1470, 0)); 
        add(panel_right, BorderLayout.CENTER);

        // Tạo khoảng trống phía trên (nằm dưới chút xíu so với đầu panel_right)
        JPanel paddingTop = new JPanel();
        paddingTop.setPreferredSize(new Dimension(0, 10)); // tùy chỉnh độ cao cách xuống
        paddingTop.setBackground(Color.BLACK);
        panel_right.add(paddingTop, BorderLayout.NORTH);

        // Tạo panel chứa nội dung chính bên phải
        JPanel panel_main_content = new JPanel();
        
        panel_main_content.setLayout(null); // tự do đặt tọa độ nếu bạn muốn fix
        panel_main_content.setBackground(Color.BLACK);
        panel_right.add(panel_main_content, BorderLayout.CENTER);

        // panel_topright nằm trong panel_main_content
        JPanel panel_topright = new JPanel();
        panel_topright.setBackground(new Color(51, 51, 51)); 
        panel_topright.setBounds(0, 0, 1470, 100); // đặt ngay dưới phần padding
        panel_topright.setLayout(null); 
        panel_main_content.add(panel_topright);

        // Tạo padding giữa panel_topright và panel_bottomright
        JPanel paddingMiddle = new JPanel();
        paddingMiddle.setBounds(0, 100, 1470, 0); // Đặt chiều cao của padding là 20
        paddingMiddle.setBackground(Color.BLACK); // Màu nền đen để phù hợp với giao diện
        panel_main_content.add(paddingMiddle);

        // panel_bottomright nằm trong panel_main_content
        JPanel panel_bottomright = new JPanel();
        panel_bottomright.setBackground(new Color(51, 51, 51));
        panel_bottomright.setBounds(0, 110, 1470, 600); // Đặt panel_bottomright bên dưới paddingMiddle
        panel_bottomright.setLayout(null);  
        panel_main_content.add(panel_bottomright);















        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    private void addHoverEffect(JButton button, Color hoverColor, Color defaultColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor); 
            }
    
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor); 
            }
        });
    }

   
}
