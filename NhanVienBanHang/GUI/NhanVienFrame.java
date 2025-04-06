package NhanVienBanHang.GUI;
import javax.swing.*;
public class NhanVienFrame extends JFrame {
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