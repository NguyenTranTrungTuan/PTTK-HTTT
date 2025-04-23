package GUI.giohang;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.user.Model_ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanUIDesigner extends JPanel implements PaymentMethodDialog.PaymentMethodCallback {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JLabel lblPhuongThuc;
    private String phuongThucThanhToan;
    private List<Model_ProductItem> products;
    private String hoTen, sdt, diaChi;
    private HoaDonPanel hoaDonPanel;
    private ThongTinDatHang thongTinPanel;

    public ThanhToanUIDesigner(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.products = new ArrayList<>(); 
         // Tiêu đề điều hướng
         JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
         navPanel.add(createNavLabel("Giỏ hàng", false));
         navPanel.add(createNavLabel("Thông tin đặt hàng", false));
         navPanel.add(createNavLabel("Thanh toán", true));
         navPanel.add(createNavLabel("Hoàn tất", false));
         add(navPanel, BorderLayout.NORTH);
        initComponents();
    }

    public void setOrderInfo(List<Model_ProductItem> products, String hoTen, String sdt, String diaChi) {
        this.products = products;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
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
    
        btnChonPhuongThuc.addActionListener(e -> {
            PaymentMethodDialog dialog = new PaymentMethodDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(this),
                    ThanhToanUIDesigner.this
            );
            dialog.setVisible(true);
        });
    
        methodPanel.add(btnChonPhuongThuc);
        methodPanel.add(lblPhuongThuc);
    
        wrapper.add(methodPanel);
    
        // --- Xác nhận thanh toán ---
        JButton btnXacNhan = new JButton("Xác nhận thanh toán");
        btnXacNhan.setBackground(new Color(215, 0, 24));
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXacNhan.setFocusPainted(false);
        btnXacNhan.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        btnXacNhan.addActionListener(e -> {
            if (phuongThucThanhToan == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức thanh toán!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            
        });
    
        wrapper.add(Box.createVerticalStrut(30));
        wrapper.add(btnXacNhan);
    
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
    
        wrapper.add(Box.createVerticalStrut(10));
        wrapper.add(navPanel);
    
        add(wrapper, BorderLayout.CENTER);
    }
    private JLabel createNavLabel(String text, boolean isActive) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(isActive ? Color.RED : Color.GRAY);
        return label;
    }
  

    @Override
public void onPaymentMethodSelected(String method) {
    phuongThucThanhToan = method;
    lblPhuongThuc.setText(method);
}
}
