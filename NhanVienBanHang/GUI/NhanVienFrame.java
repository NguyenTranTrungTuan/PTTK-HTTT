package NhanVienBanHang.GUI;
import javax.swing.*;
public class NhanVienFrame extends JFrame {
    JLabel lb_sanpham, lb_madtshowsp;
    JTable table;
    DefaultTableModel tableModel;
    JButton btn_add, btn_refresh, btn_xacnhanthem, btn_huythem, btn_delete;
    JPanel panel_right, panel_themsp, panel_right_bottom, panel_right_bottom_top;
    JScrollPane scrollPane_table;
    JTextField tf_madtshowsp, tf_tendtshowsp, tf_giabanshowsp, tf_gianhapshowsp, tf_matonshowsp, tf_xuatxushowsp,
            tf_trongluongshowsp, tf_kichthuocmanhinhshowsp, tf_dungluongdtshowsp, tf_ramshowsp, tf_baohanhshowsp,
            tf_manccshowsp, tf_madt, tf_tendt, tf_giaban, tf_gianhap, tf_maton, tf_xuatxu,
            tf_trongluong, tf_kichthuocmanhinh, tf_dungluongdt, tf_ram, tf_baohanh,
            tf_mancc;
    public NhanVienFrame() {
        setTitle("Nhan-Vien Ban-Hang");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        
        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(null); // use null layout for absolute positioning
        
        // Create and add components to the panel
        JLabel label = new JLabel("Welcome to the Sales Management System!");
        label.setBounds(50, 50, 300, 30);
        panel.add(label);
        
        JButton button = new JButton("Click Me");
        button.setBounds(50, 100, 100, 30);
        panel.add(button);
        
        add(panel); // Don't forget to add the panel to the frame
        setVisible(true); // Make the frame visible
    }
}