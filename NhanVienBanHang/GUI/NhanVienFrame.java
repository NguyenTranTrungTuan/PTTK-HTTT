package NhanVienBanHang.GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class NhanVienFrame extends JFrame {
    JLabel lb_tennv,lb_chucvu;
    JTable table;
    DefaultTableModel tableModel;
    JPanel panel_info,panel_chucnang, panel_topright, panel_bottomright,panel_display;
    JButton btn_confirm, btn_delete, btn_cancel;


    public NhanVienFrame() {
        setTitle("Nhan Vien Ban Hang");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Tạo panel hiển thị chính (panel_display)
        panel_display = new JPanel();
        panel_display.setLayout(null);
        panel_display.setBounds(0, 0, 1690, 1040); // Kích thước toàn bộ JFrame
        panel_display.setBackground(new java.awt.Color(240, 240, 240)); // Màu nền cho panel_display

        // Tạo panel bên trái phía trên là thông tin tài khoản (panel_info)
        panel_info = new JPanel();
        panel_info.setLayout(null);
        // Đặt kích thước nhỏ hơn và vị trí trong panel_display
        panel_info.setBounds(20, 20, 400, 150); // X: 20, Y: 20, Width: 400, Height: 300
        panel_info.setBackground(new java.awt.Color(255, 255, 255));
        panel_info.setBorder(new LineBorder(Color.BLACK, 2)); // Viền cho panel_info


        // Thêm các thành phần vào panel_info
        lb_tennv = new JLabel("Ten Nhan Vien: ");
        lb_chucvu = new JLabel("Chuc Vu: ");
        lb_tennv.setBounds(120, 30, 200, 30);
        lb_chucvu.setBounds(120, 70, 200, 30);
        panel_info.add(lb_tennv);
        panel_info.add(lb_chucvu);

        ImageIcon icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\saler.png");
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(10, 20, 100, 100);
        panel_info.add(iconLabel);

        // Thêm panel_info vào panel_display
        panel_display.add(panel_info);

        // Thêm panel_display vào JFrame
        add(panel_display);

        setVisible(true); // Hiển thị JFrame
    }
}