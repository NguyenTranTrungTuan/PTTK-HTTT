package GUI.giohang;

import GUI.user.Model_ProductItem;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ThongTinDatHang extends JPanel implements ActionListener, PaymentMethodDialog.PaymentMethodCallback {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton btnTiepTuc;
    private JButton btnQuayLai;
    private JTextField txtHoTen, txtSoDienThoai, txtDiaChi;
    private JLabel lblPhuongThuc;
    private JLabel lblTen, lblTag, lblSdt, lblEmail, lblAddress; 
    private String phuongThucThanhToan;
    private List<Model_ProductItem> products;
    private JPanel panelDanhSachSanPham;

    // Biến kiểm tra trạng thái đăng nhập
    private boolean isLoggedIn = false; 

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

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(245, 247, 250)); // màu nền nhạt
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Tiêu đề
        JLabel titleLabel = new JLabel("THÔNG TIN KHÁCH HÀNG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(10));

        // Panel thông tin bo tròn
        JPanel infoPanel = new RoundedPanel(15); // Panel bo tròn
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Dòng 1: Họ tên + Tag + SĐT
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);

        // Tên
        lblTen = new JLabel("Chưa đăng nhập");
        lblTen.setFont(new Font("Arial", Font.BOLD, 15));

        // Tag hồng
        lblTag = new JLabel("");
        lblTag.setOpaque(true);
        lblTag.setBackground(new Color(255, 105, 180));
        lblTag.setForeground(Color.WHITE);
        lblTag.setFont(new Font("Arial", Font.BOLD, 11));
        lblTag.setBorder(new EmptyBorder(2, 6, 2, 6));
        lblTag.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Tên + tag trong 1 dòng
        JPanel nameTagPanel = new JPanel();
        nameTagPanel.setOpaque(false);
        nameTagPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        nameTagPanel.add(lblTen);
        nameTagPanel.add(lblTag);

        topRow.add(nameTagPanel, BorderLayout.WEST);

        // SĐT
        lblSdt = new JLabel("");
        lblSdt.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSdt.setForeground(new Color(60, 120, 180));
        topRow.add(lblSdt, BorderLayout.EAST);

        infoPanel.add(topRow);

        // Dòng EMAIL
        infoPanel.add(Box.createVerticalStrut(10));
        JLabel lblEmailTag = new JLabel("EMAIL");
        lblEmailTag.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEmailTag.setForeground(Color.GRAY);
        infoPanel.add(lblEmailTag);

        lblEmail = new JLabel("");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 13));
        infoPanel.add(lblEmail);

        // Dòng ĐỊA CHỈ
        infoPanel.add(Box.createVerticalStrut(10));
        JLabel lblAddressTag = new JLabel("ĐỊA CHỈ");
        lblAddressTag.setFont(new Font("Arial", Font.PLAIN, 12));
        lblAddressTag.setForeground(Color.GRAY);
        infoPanel.add(lblAddressTag);

        lblAddress = new JLabel("");
        lblAddress.setFont(new Font("Arial", Font.PLAIN, 13));
        infoPanel.add(lblAddress);

        formPanel.add(infoPanel);
        formPanel.add(Box.createVerticalStrut(20));

        // Danh sách sản phẩm
        panelDanhSachSanPham = new JPanel();
        panelDanhSachSanPham.setLayout(new BoxLayout(panelDanhSachSanPham, BoxLayout.Y_AXIS));
        panelDanhSachSanPham.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelDanhSachSanPham);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        formPanel.add(scrollPane);

        // Phương thức thanh toán
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
                    ThongTinDatHang.this
            );
            dialog.setVisible(true);
        });

        methodPanel.add(btnChonPhuongThuc);
        methodPanel.add(lblPhuongThuc);

        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(methodPanel);

        // Nút điều hướng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        btnTiepTuc = new JButton("TIẾP TỤC");
        btnTiepTuc.setFont(new Font("Arial", Font.BOLD, 16));
        btnTiepTuc.setForeground(Color.WHITE);
        btnTiepTuc.setBackground(Color.RED);
        btnTiepTuc.setFocusPainted(false);
        btnTiepTuc.setPreferredSize(new Dimension(200, 40));
        btnTiepTuc.addActionListener(this);

        btnQuayLai = new JButton("QUAY LẠI");
        btnQuayLai.setFont(new Font("Arial", Font.BOLD, 16));
        btnQuayLai.setForeground(Color.WHITE);
        btnQuayLai.setBackground(Color.RED);
        btnQuayLai.setFocusPainted(false);
        btnQuayLai.setPreferredSize(new Dimension(200, 40));
        btnQuayLai.addActionListener(this);

        buttonPanel.add(btnQuayLai);
        buttonPanel.add(btnTiepTuc);

        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.CENTER);
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
        panelDanhSachSanPham.removeAll();
    
        for (Model_ProductItem product : products) {
            JPanel itemPanel = new JPanel(new BorderLayout(10, 10));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            itemPanel.setBackground(Color.WHITE);
    
            JLabel imageLabel = new JLabel();
            try {
                String imagePath = "GUI/user/ProductImage/" + product.getImageicon() + ".png";
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                imageLabel.setText("Không có ảnh");
            }
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setPreferredSize(new Dimension(80, 60));
            itemPanel.add(imageLabel, BorderLayout.WEST);
    
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.add(new JLabel(product.getTitle()));
            infoPanel.add(new JLabel("Số lượng: " + product.getAmount()));
            infoPanel.add(new JLabel("Giá: " + product.getPrice() + "₫"));
    
            itemPanel.add(infoPanel, BorderLayout.CENTER);
    
            panelDanhSachSanPham.add(Box.createVerticalStrut(5));
            panelDanhSachSanPham.add(itemPanel);
        }
    
        panelDanhSachSanPham.revalidate();
        panelDanhSachSanPham.repaint();
    }
    public void setCustomerInfo(String ten, String sdt, String email, String diaChi) {
        lblTen.setText(ten != null && !ten.isEmpty() ? ten : "Chưa đăng nhập");
        lblSdt.setText(sdt != null && !sdt.isEmpty() ? sdt : "Không có số điện thoại");
        lblEmail.setText(email != null && !email.isEmpty() ? email : "Không có email");
        lblAddress.setText(diaChi != null && !diaChi.isEmpty() ? diaChi : "Không có địa chỉ");
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTiepTuc) {
            if (!isLoggedIn) {
                JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập trước khi tiếp tục!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (txtHoTen.getText().isEmpty() || txtSoDienThoai.getText().isEmpty() || txtDiaChi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (phuongThucThanhToan == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức thanh toán!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            cardLayout.show(contentPanel, "ThanhToan");
        }
        if (e.getSource() == btnQuayLai) {
            cardLayout.show(contentPanel, "GioHang");
        }
    }

    @Override
    public void onPaymentMethodSelected(String method) {
        phuongThucThanhToan = method;
        lblPhuongThuc.setText(method);
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }
}