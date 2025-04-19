package GUI.giohang;


import GUI.user.Model_ProductItem;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ThongTinDatHang extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton btnTiepTuc;
    private JButton btnQuayLai;
    private JTextField txtHoTen, txtSoDienThoai, txtDiaChi;
    private List<Model_ProductItem> products;
    public ThongTinDatHang(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Tiêu đề điều hướng
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navPanel.add(createNavLabel("Giỏ hàng", false));
        navPanel.add(createNavLabel("Thông tin đặt hàng", true));
        navPanel.add(createNavLabel("Thanh toán", false));
        navPanel.add(createNavLabel("Hoàn tất", false));
        add(navPanel, BorderLayout.NORTH);

        // Form nhập thông tin
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new CompoundBorder(
                new LineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(20, 30, 20, 30)
        ));
        formPanel.setBackground(Color.WHITE);

        txtHoTen = createStyledTextField("Họ và tên");
        txtSoDienThoai = createStyledTextField("Số điện thoại");
        txtDiaChi = createStyledTextField("Địa chỉ nhận hàng");

        formPanel.add(txtHoTen);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(txtSoDienThoai);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(txtDiaChi);
        formPanel.add(Box.createVerticalStrut(20));

        btnTiepTuc = new JButton("TIẾP TỤC");
        btnTiepTuc.setFont(new Font("Arial", Font.BOLD, 16));
        btnTiepTuc.setForeground(Color.WHITE);
        btnTiepTuc.setBackground(Color.RED);
        btnTiepTuc.setFocusPainted(false);
        btnTiepTuc.setPreferredSize(new Dimension(200, 40));
        btnTiepTuc.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTiepTuc.addActionListener(this);
        btnQuayLai = new JButton("QUAY LẠI");
        btnQuayLai.setFont(new Font("Arial", Font.BOLD, 16));
        btnQuayLai.setForeground(Color.WHITE);
        btnQuayLai.setBackground(Color.RED);
        btnQuayLai.setFocusPainted(false);
        btnQuayLai.setPreferredSize(new Dimension(200, 40));
        btnQuayLai.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(btnQuayLai);
        formPanel.add(btnTiepTuc);
        btnQuayLai.addActionListener(this);
        add(formPanel, BorderLayout.CENTER);
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                placeholder,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 12)
        ));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        return textField;
    }

    private JLabel createNavLabel(String text, boolean isActive) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(isActive ? Color.RED : Color.GRAY);
        return label;
    }

    public void updateItems(List<Model_ProductItem> products) {
        if (products == null || products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong giỏ hàng!", 
                                          "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        this.products = products;
    
        // Hiển thị tóm tắt đơn hàng (ví dụ: sử dụng JTextArea hoặc JTable)
        StringBuilder summary = new StringBuilder("Tóm tắt đơn hàng:\n");
        for (Model_ProductItem product : products) {
            summary.append("- ")
                   .append(product.getTitle())
                   .append(": ")
                   .append(product.getAmount())
                   .append(" x ")
                   .append(product.getPrice())
                   .append("₫\n");
        }
    
        // Hiển thị tóm tắt trong một hộp thoại hoặc cập nhật giao diện
        JOptionPane.showMessageDialog(this, summary.toString(), 
                                      "Tóm tắt đơn hàng", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTiepTuc) {
            if (txtHoTen.getText().isEmpty() || txtSoDienThoai.getText().isEmpty() || txtDiaChi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            cardLayout.show(contentPanel, "ThanhToan"); 
        }
        if(e.getSource() == btnQuayLai) {
            cardLayout.show(contentPanel, "GioHang"); // Quay lại Giỏ hàng
        }
    }
}
