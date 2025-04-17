
package qlgiohang.GUI;
import qlgiohang.oop.Product;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TTDatHangUI extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JTextField txtHoTen, txtSoDienThoai, txtDiaChi;
    private JButton btnXacNhan;

    public TTDatHangUI(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("THÔNG TIN NHẬN HÀNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(215, 0, 24)); 
        add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 15, 15));
        formPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        formPanel.setBackground(Color.WHITE);

        txtHoTen = taoTextField("Họ và tên");
        txtSoDienThoai = taoTextField("Số điện thoại");
        txtDiaChi = taoTextField("Địa chỉ nhận hàng");

        formPanel.add(txtHoTen);
        formPanel.add(txtSoDienThoai);
        formPanel.add(txtDiaChi);

        btnXacNhan = new JButton("TIẾP TỤC THANH TOÁN");
        btnXacNhan.setBackground(new Color(215, 0, 24));
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnXacNhan.setFocusPainted(false);
        btnXacNhan.setPreferredSize(new Dimension(250, 45));
        btnXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXacNhan.addActionListener(e -> {
            cardLayout.show(contentPanel, "ThanhToan"); // Chuyển đến màn Thanh toán
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnXacNhan);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField taoTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createTitledBorder(placeholder));
        return textField;
    }

    public void updateItems(List<Product> products) {
        // Có thể sử dụng để hiển thị sản phẩm ở trang này nếu muốn
    }
}
