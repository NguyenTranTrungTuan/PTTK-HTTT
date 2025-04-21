package Admin_quanlitaikhoan;
import Admin_quanlitaikhoan.GUI.Login_frame;
import javax.swing.SwingUtilities;

public class Main_admin  {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login_frame());
        
    }
}
