package Admin_quanlitaikhoan;
import javax.swing.SwingUtilities;

import Admin_quanlitaikhoan.GUI.Login_frame;

public class Main_admin  {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login_frame());
    }
}
