package NhanVienBanHang.GUI;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
        setText("Chi Tiết");
        setBackground(new Color(70, 130, 180)); // Màu nền của nút
        setForeground(Color.WHITE); // Màu chữ
        setFont(new Font("Arial", Font.BOLD, 12)); // Font chữ
        setFocusPainted(false); // Ẩn viền khi nhấn
        setBorderPainted(false); // Ẩn viền mặc định
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}