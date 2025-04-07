package NhanVienBanHang.GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;


public class NhanVienFrame extends JFrame {
    JLabel lb_tennv,lb_chucvu;
    JTable table;
    DefaultTableModel tableModel;
    JPanel panel_info,panel_chucnang, panel_topright, panel_bottomright,panel_display, panel_table,panel_scroll;
    JButton btn_confirm, btn_delete, btn_cancel,btn_exit;


    public NhanVienFrame() {
        setTitle("Nhan Vien Ban Hang");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Tạo panel hiển thị chính (panel_display)
        panel_display = new JPanel();
        panel_display.setBackground(Color.BLACK);
        panel_display.setLayout(null);
        panel_display.setBounds(0, 0, 1690, 1040); 
    


        // Tạo panel bên trái phía trên là thông tin tài khoản (panel_info)
        LineBorder infoBorder = new LineBorder(Color.WHITE, 2);
        panel_info = new JPanel();
        panel_info.setLayout(null);
        panel_info.setBounds(20, 20, 400, 150);
        panel_info.setBackground(Color.GRAY);
        panel_info.setBorder(infoBorder); 


        lb_tennv = new JLabel();
        lb_tennv.setText("Ten Nhan Vien: Nguyen Van A");
        lb_chucvu = new JLabel();
        lb_chucvu.setText("Chuc Vu: Nhan Vien Ban Hang");
        lb_tennv.setBounds(120, 20, 200, 30);
        lb_chucvu.setBounds(120, 40, 200, 30);
        lb_tennv.setFont(new Font("Arial", Font.BOLD, 12)); 
        lb_chucvu.setFont(new Font("Arial", Font.BOLD, 12));
        lb_tennv.setForeground(Color.WHITE); 
        lb_chucvu.setForeground(Color.WHITE);
        panel_info.add(lb_tennv);
        panel_info.add(lb_chucvu);

        ImageIcon icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\user.png");
        icon = ImageResizer.resizeImageIcon(icon, 50, 50); 
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(10, 20, 100, 100);
        // Tạo viền cho icon
        LineBorder iconBorder = new LineBorder(Color.WHITE, 2); // Viền màu trắng, độ dày 2px
        iconLabel.setBorder(iconBorder); // Áp dụng viền cho JLabel chứa icon
        panel_info.add(iconLabel);


        // Tạo panel chức năng nằm phía dưới panel_info
        panel_chucnang = new JPanel();
        panel_chucnang.setLayout(new BoxLayout(panel_chucnang, BoxLayout.Y_AXIS)); 
        panel_chucnang.setBounds(20, 170, 400, 870);
        panel_chucnang.setBackground(Color.LIGHT_GRAY);

        //Tạo các nút nằm trong panel_chucnang
        JButton btn_duyetDonHang = new JButton("Duyệt đơn hàng");
        JButton btn_huyDonHang = new JButton("Hủy đơn hàng");
        JButton btn_inHoaDon = new JButton("In hóa đơn bán hàng lưu trữ");
        JButton btn_xemHoaDon = new JButton("Xem hóa đơn bán hàng");
        JButton btn_xemThongKe = new JButton("Xem thống kê báo bán hàng");

        Font buttonFont = new Font("Arial", Font.BOLD, 20); 
        btn_duyetDonHang.setFont(buttonFont);
        btn_huyDonHang.setFont(buttonFont);
        btn_inHoaDon.setFont(buttonFont);
        btn_xemHoaDon.setFont(buttonFont);
        btn_xemThongKe.setFont(buttonFont);
            

        

        int panelWidth = panel_chucnang.getBounds().width; // Lấy chiều rộng của panel_chucnang
        btn_duyetDonHang.setMaximumSize(new java.awt.Dimension(panelWidth, 80)); 
        btn_huyDonHang.setMaximumSize(new java.awt.Dimension(panelWidth, 80));
        btn_inHoaDon.setMaximumSize(new java.awt.Dimension(panelWidth, 80));
        btn_xemHoaDon.setMaximumSize(new java.awt.Dimension(panelWidth, 80));
        btn_xemThongKe.setMaximumSize(new java.awt.Dimension(panelWidth, 80));

        btn_duyetDonHang.setAlignmentX(JButton.LEFT_ALIGNMENT);
        btn_huyDonHang.setAlignmentX(JButton.LEFT_ALIGNMENT);
        btn_inHoaDon.setAlignmentX(JButton.LEFT_ALIGNMENT);
        btn_xemHoaDon.setAlignmentX(JButton.LEFT_ALIGNMENT);
        btn_xemThongKe.setAlignmentX(JButton.LEFT_ALIGNMENT);


        btn_duyetDonHang.setBackground(Color.LIGHT_GRAY);
        btn_huyDonHang.setBackground(Color.LIGHT_GRAY);
        btn_inHoaDon.setBackground(Color.LIGHT_GRAY);
        btn_xemHoaDon.setBackground(Color.LIGHT_GRAY);
        btn_xemThongKe.setBackground(Color.LIGHT_GRAY);

        btn_duyetDonHang.setForeground(Color.BLACK);
        btn_huyDonHang.setForeground(Color.BLACK);
        btn_inHoaDon.setForeground(Color.BLACK);
        btn_xemHoaDon.setForeground(Color.BLACK);
        btn_xemThongKe.setForeground(Color.BLACK);

        // Tạo một LineBorder dùng chung
        LineBorder buttonBorder = new LineBorder(Color.BLACK, 2); // Viền màu đen, độ dày 2px

        // Áp dụng viền chung cho các nút
        btn_duyetDonHang.setBorder(buttonBorder);
        btn_huyDonHang.setBorder(buttonBorder);
        btn_inHoaDon.setBorder(buttonBorder);
        btn_xemHoaDon.setBorder(buttonBorder);
        btn_xemThongKe.setBorder(buttonBorder);

        //Bỏ viền nút khi nhấn
        btn_duyetDonHang.setBorderPainted(false); 
        btn_duyetDonHang.setBorderPainted(false);
        btn_huyDonHang.setBorderPainted(false);
        btn_inHoaDon.setBorderPainted(false);
        btn_xemHoaDon.setBorderPainted(false);
        btn_xemThongKe.setBorderPainted(false);

        btn_duyetDonHang.setFocusPainted(false);
        btn_huyDonHang.setFocusPainted(false);
        btn_inHoaDon.setFocusPainted(false);
        btn_xemHoaDon.setFocusPainted(false);
        btn_xemThongKe.setFocusPainted(false);


        //Thêm hiệu ứng hover cho các nút 
        addHoverEffect(btn_duyetDonHang, Color.DARK_GRAY, Color.LIGHT_GRAY);
        addHoverEffect(btn_huyDonHang, Color.DARK_GRAY, Color.LIGHT_GRAY);
        addHoverEffect(btn_inHoaDon, Color.DARK_GRAY, Color.LIGHT_GRAY);
        addHoverEffect(btn_xemHoaDon, Color.DARK_GRAY, Color.LIGHT_GRAY);
        addHoverEffect(btn_xemThongKe, Color.DARK_GRAY, Color.LIGHT_GRAY);

        
        panel_chucnang.add(btn_duyetDonHang);
        panel_chucnang.add(btn_huyDonHang);
        panel_chucnang.add(btn_inHoaDon);
        panel_chucnang.add(btn_xemHoaDon);
        panel_chucnang.add(btn_xemThongKe);
 



        //Tạo panel bên phải phía dưới là bảng thông tin đơn hàng (panel_table)
        panel_table = new JPanel(); 
        panel_table.setLayout(null);
        panel_table.setBounds(450, 20, 1200, 1020); 
        panel_table.setBackground(Color.GRAY);
        panel_table.setBorder(infoBorder);
        panel_table.setLayout(new BoxLayout(panel_table, BoxLayout.Y_AXIS));
        panel_table.setBackground(Color.GRAY);
        panel_table.setBorder(infoBorder);
        panel_table.setLayout(new BoxLayout(panel_table, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout theo chiều dọc

        
        // Thêm panel_info và panel_chucnang vào panel_display
        panel_display.add(panel_info);
        panel_display.add(panel_chucnang);
        panel_display.add(panel_table);

        // Thêm panel_display vào JFrame
        add(panel_display);

        setVisible(true); // Hiển thị JFrame
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