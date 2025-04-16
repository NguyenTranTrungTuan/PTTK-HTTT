package NhanVienBanHang.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.setBackground(new Color(70, 130, 180)); // Màu nền của nút
        button.setForeground(Color.WHITE); // Màu chữ
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Font chữ
        button.setFocusPainted(false); // Ẩn viền khi nhấn
        button.setBorderPainted(false); // Ẩn viền mặc định
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Chi Tiết" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Lấy mã đơn hàng từ dòng được chọn
            JTable table = (JTable) button.getParent().getParent();
            int row = table.getSelectedRow();
            String maDon = table.getValueAt(row, 0).toString();

            // Hiển thị chi tiết đơn hàng
            JOptionPane.showMessageDialog(button, "Hiển thị chi tiết đơn hàng cho Mã Đơn: " + maDon);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}