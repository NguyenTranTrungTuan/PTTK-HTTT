package GUI.giohang;

import DTO.KhachHang_DTO;
import GUI.user.Model_ProductItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GioHangGUI extends JPanel {
    private List<Model_ProductItem> products;
    private JLabel lbTongTienValue;
    private JPanel itemPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton btnDatHang;

    private ThongTinDatHang thongTinDatHang;
    private ThanhToanUIDesigner thanhToanPanel;

    private boolean isLoggedIn = true;
    private KhachHang_DTO khachHang;

    public GioHangGUI() {
        this.setLayout(new BorderLayout());
        this.products = new ArrayList<>();
        this.cardLayout = new CardLayout();
        this.contentPanel = new JPanel(cardLayout);
        this.add(contentPanel, BorderLayout.CENTER);

        JPanel gioHangPanel = initGioHangPanel();
        contentPanel.add(gioHangPanel, "GioHang");

        thongTinDatHang = new ThongTinDatHang(cardLayout, contentPanel);
        contentPanel.add(thongTinDatHang, "ThongTinNhanHang");

        thanhToanPanel = new ThanhToanUIDesigner(cardLayout, contentPanel);
        contentPanel.add(thanhToanPanel, "ThanhToan");
    }

    private JPanel initGioHangPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createNavPanel(), BorderLayout.NORTH);

        itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE);
        mainPanel.add(new JScrollPane(itemPanel), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        bottomPanel.setBackground(Color.WHITE);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalPanel.setBackground(Color.WHITE);

        JLabel lbTongTienLabel = new JLabel("Tổng tiền:");
        lbTongTienLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        lbTongTienValue = new JLabel("0₫");
        lbTongTienValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTongTienValue.setForeground(Color.RED);

        totalPanel.add(lbTongTienLabel);
        totalPanel.add(lbTongTienValue);

        JButton btnXoaTatCa = new JButton("Xóa tất cả");
        btnXoaTatCa.setBackground(Color.RED);
        btnXoaTatCa.setForeground(Color.WHITE);
        btnXoaTatCa.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXoaTatCa.addActionListener(e -> {
            products.clear();
            refreshProductList();
        });

        btnDatHang = new JButton("ĐẶT HÀNG");
        btnDatHang.setBackground(new Color(215, 0, 24));
        btnDatHang.setForeground(Color.WHITE);
        btnDatHang.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDatHang.setPreferredSize(new Dimension(200, 45));
        btnDatHang.setFocusPainted(false);
        btnDatHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDatHang.addActionListener(this::handleDatHang);

        bottomPanel.add(totalPanel, BorderLayout.WEST);
        bottomPanel.add(btnXoaTatCa, BorderLayout.CENTER);
        bottomPanel.add(btnDatHang, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel createNavPanel() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navPanel.setBackground(Color.WHITE);

        navPanel.add(createNavLabel("Giỏ hàng", true));
        navPanel.add(createNavLabel("Thông tin đặt hàng", false));
        navPanel.add(createNavLabel("Thanh toán", false));
        navPanel.add(createNavLabel("Hoàn tất", false));

        return navPanel;
    }

    private JLabel createNavLabel(String text, boolean isActive) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(isActive ? new Color(230, 0, 0) : Color.GRAY);
        label.setBorder(new EmptyBorder(0, 10, 0, 10));
        return label;
    }

    public void themSanPhamVaoGio(Model_ProductItem data) {
        boolean daTonTai = false;
        for (Model_ProductItem p : products) {
            if (p.getTitle().equals(data.getTitle())) {
                p.setAmount(p.getAmount() + data.getAmount());
                daTonTai = true;
                break;
            }
        }
        if (!daTonTai) {
            products.add(data);
        }
        refreshProductList();
    }

    private void refreshProductList() {
        itemPanel.removeAll();

        for (Model_ProductItem item : new ArrayList<>(products)) {
            Model_ProductItem currentItem = item;

            CartItemPanel cartItem = new CartItemPanel(currentItem, this::updateTongTien, e -> {
                products.remove(currentItem);
                refreshProductList();
            });

            RoundedPanel wrapper = new RoundedPanel(20);
            wrapper.setLayout(new BorderLayout());
            wrapper.setBackground(Color.WHITE);
            wrapper.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            wrapper.add(cartItem, BorderLayout.CENTER);
            wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

            itemPanel.add(Box.createVerticalStrut(10));
            itemPanel.add(wrapper);
        }

        itemPanel.add(Box.createVerticalStrut(10));
        updateTongTien();
        itemPanel.revalidate();
        itemPanel.repaint();
    }

    private void updateTongTien() {
        BigDecimal total = products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        lbTongTienValue.setText(String.format("%,.0f₫", total));
    }

    private void handleDatHang(ActionEvent e) {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống! Vui lòng thêm sản phẩm trước khi đặt hàng.",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập trước khi đặt hàng!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        thongTinDatHang.setCustomerInfo(
                khachHang.getTen_KhachHang(),
                khachHang.getSdt_KhachHang(),
                khachHang.getEmail_KhachHang(),
                khachHang.getDiaChi_KhachHang()
        );

        thongTinDatHang.updateItems(products);
        cardLayout.show(contentPanel, "ThongTinNhanHang");
    }

    public void setLoggedIn(boolean isLoggedIn, KhachHang_DTO khachHang) {
        this.isLoggedIn = isLoggedIn;
        this.khachHang = khachHang;
    }
}