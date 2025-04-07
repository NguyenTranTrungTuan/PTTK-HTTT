package NhanVienBanHang.GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhanVienFrame extends JFrame {
    JLabel lb_tennv,lb_chucvu;
    JTable table;
    DefaultTableModel tableModel;
    JPanel panel_info,panel_chucnang, panel_topright, panel_bottomright;
    JButton btn_confirm, btn_delete, btn_cancel;


    public NhanVienFrame() {
        setTitle("Nhan Vien Ban Hang");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        //Tạo panel bên trái phía trên là thông tin tài khoản
        panel_info = new JPanel();
        panel_info.setLayout(null);
        panel_info.setBounds(0, 0, 800, 1040);
        panel_info.setBackground(new java.awt.Color(255, 255, 255)); 
        
        lb_tennv = new JLabel("Ten Nhan Vien: ");
        lb_tennv.setBounds(20, 20, 200, 30); 
        lb_chucvu = new JLabel("Chuc Vu: ");
        lb_chucvu.setBounds(20, 60, 200, 30);
        panel_info.add(lb_tennv);
        panel_info.add(lb_chucvu);  
        ImageIcon icon = new ImageIcon("E://Lap trinh Java//QuanLyBanDienThoai//NhanVienBanHang//Icon//saler.webp");
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(20, 100, 200, 200);
        panel_info.add(iconLabel); 


        add(panel_info);
        setVisible(true); // Make the frame visible
    }
}