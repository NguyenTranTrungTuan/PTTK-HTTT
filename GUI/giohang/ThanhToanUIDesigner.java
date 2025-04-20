package GUI.giohang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.user.Model_ProductItem;
public class ThanhToanUIDesigner extends JPanel implements PaymentMethodDialog.PaymentMethodCallback {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JLabel lblPhuongThuc;
    private String phuongThucThanhToan;

    public ThanhToanUIDesigner(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Tiêu đề ---
        JLabel title = new JLabel("THANH TOÁN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(215, 0, 24));
        wrapper.add(title);
        wrapper.add(Box.createVerticalStrut(20));

        // --- Phương thức thanh toán ---
        JPanel methodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        methodPanel.setBackground(Color.WHITE);

        JButton btnChonPhuongThuc = new JButton("Chọn phương thức thanh toán");
        btnChonPhuongThuc.setBackground(new Color(215, 0, 24));
        btnChonPhuongThuc.setForeground(Color.WHITE);
        btnChonPhuongThuc.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnChonPhuongThuc.setFocusPainted(false);
        btnChonPhuongThuc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblPhuongThuc = new JLabel("Chưa chọn");
        lblPhuongThuc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPhuongThuc.setForeground(Color.DARK_GRAY);
        lblPhuongThuc.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        btnChonPhuongThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentMethodDialog dialog = new PaymentMethodDialog(
                        (JFrame) SwingUtilities.getWindowAncestor(ThanhToanUIDesigner.this),
                        ThanhToanUIDesigner.this
                );
                dialog.setVisible(true);
            }
        });

        methodPanel.add(btnChonPhuongThuc);
        methodPanel.add(lblPhuongThuc);

        wrapper.add(methodPanel);

        // --- Nút quay lại ---
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setBackground(Color.WHITE);

        JButton btnBack = new JButton("← Quay lại");
        btnBack.setFocusPainted(false);
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnBack.setBackground(Color.LIGHT_GRAY);
        btnBack.setForeground(Color.BLACK);
        btnBack.addActionListener(e -> cardLayout.show(contentPanel, "ThongTinNhanHang"));

        navPanel.add(btnBack);

        wrapper.add(Box.createVerticalStrut(30));
        wrapper.add(navPanel);

        add(wrapper, BorderLayout.CENTER);
    }

    @Override
    public void onPaymentMethodSelected(String method) {
        phuongThucThanhToan = method;
        lblPhuongThuc.setText(method);
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }
}
