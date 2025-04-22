package GUI.giohang;
import GUI.user.Model_ProductItem;
import javax.swing.*;

import DTO.DonHang_DTO;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class HoaDonPanel extends JPanel {

    public HoaDonPanel(String hoTen, String sdt, String diaChi,
                       DonHang_DTO thongtindonhang,
                       CardLayout cardLayout, JPanel contentPanel) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("HÓA ĐƠN ĐẶT HÀNG");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(215, 0, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JTextArea receiptArea = new JTextArea();
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        receiptArea.setEditable(false);

        StringBuilder receipt = new StringBuilder();
        receipt.append("Thông tin khách hàng:\n");
        receipt.append("Họ tên: ").append(hoTen).append("\n");
        receipt.append("SĐT: ").append(sdt).append("\n");
        receipt.append("Địa chỉ: ").append(diaChi).append("\n\n");

        receipt.append("Danh sách sản phẩm:\n");
        BigDecimal total = BigDecimal.ZERO; // Sử dụng BigDecimal cho tổng tiền
        for (Model_ProductItem product : products) {
            BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(product.getAmount())); // Tính tiền từng sản phẩm
            receipt.append(String.format("- %s: %d x %,d₫ = %,d₫\n",
                    product.getTitle(), product.getAmount(), product.getPrice().intValue(), subTotal.intValue()));
            total = total.add(subTotal); // Cộng dồn vào tổng tiền
        }



        receipt.append("\nPhương thức thanh toán: ").append(paymentMethod);
        receipt.append(String.format("\n\nTỔNG CỘNG: %,d₫", total.intValue())); 

        receiptArea.setText(receipt.toString());

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(scrollPane, BorderLayout.CENTER);

        // Nút "Quay về trang chủ"
        JButton btnTrangChu = new JButton("Quay về trang chủ");
        btnTrangChu.setBackground(new Color(215, 0, 24));
        btnTrangChu.setForeground(Color.WHITE);
        btnTrangChu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTrangChu.setFocusPainted(false);
        btnTrangChu.setPreferredSize(new Dimension(180, 40));
        btnTrangChu.addActionListener(e -> cardLayout.show(contentPanel, "TrangChu"));

        // Nút "Quay lại trang thanh toán"
        JButton btnQuayLai = new JButton("Quay lại trang thanh toán");
        btnQuayLai.setBackground(new Color(215, 0, 24));
        btnQuayLai.setForeground(Color.WHITE);
        btnQuayLai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnQuayLai.setFocusPainted(false);
        btnQuayLai.setPreferredSize(new Dimension(220, 40));
        btnQuayLai.addActionListener(e -> cardLayout.show(contentPanel, "ThanhToan"));

        // Panel chứa các nút
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(btnQuayLai);
        bottomPanel.add(btnTrangChu);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}