package GUI.giohang;

import javax.swing.*;
import javax.swing.border.*;

import BLL.DonHang_BLL;
import DTO.ChiTietDon_DTO;
import DTO.DonHang_DTO;
import DTO.KhachHang_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ThongTinDatHang extends JPanel implements ActionListener, PaymentMethodDialog.PaymentMethodCallback {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton btnThanhtoan;
    private JButton btnQuayLai;
    private JLabel lblPhuongThuc;
    private JLabel lblTen, lblTag, lblSdt, lblEmail, lblAddress; 

    private JPanel panelDanhSachSanPham;
    private GioHangGUI giohang;

    private String phuongThucThanhToan;

    private DonHang_DTO ThongTinDonHang;
    private DonHang_BLL dhbll;

    public ThongTinDatHang(CardLayout cardLayout, JPanel contentPanel,KhachHang_DTO kh, DonHang_DTO ThongTinDonHang, GioHangGUI giohang) {
        this.ThongTinDonHang = ThongTinDonHang;
        this.giohang = giohang;
        this.dhbll = new DonHang_BLL();

        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Tiêu đề điều hướng
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navPanel.add(createNavLabel("Giỏ hàng", false));
        navPanel.add(createNavLabel("Thông tin đặt hàng", true));
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
        topRow.setMaximumSize(new Dimension(300,30));

        // Tên
        lblTen = new JLabel(kh.getTen_KhachHang());
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

        // Đặt kích thước tối đa cho nameTagPanel để tránh tên quá dài
        nameTagPanel.setMaximumSize(new Dimension(300, 20)); // Giới hạn chiều rộng tối đa
        nameTagPanel.setPreferredSize(new Dimension(300, 20)); // Đảm bảo chiều rộng cố định

        topRow.add(nameTagPanel, BorderLayout.WEST);

        // SĐT
        lblSdt = new JLabel(kh.getSdt_KhachHang());
        lblSdt.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSdt.setForeground(new Color(60, 120, 180));

        topRow.add(lblSdt, BorderLayout.EAST);

        infoPanel.add(topRow);

        // Dòng EMAIL
        infoPanel.add(Box.createVerticalStrut(10));
        JLabel lblEmailTag = new JLabel("EMAIL");
        lblEmailTag.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEmailTag.setForeground(Color.GRAY);
        lblEmailTag.setAlignmentX(SwingConstants.LEFT);

        lblEmail = new JLabel(kh.getEmail_KhachHang());
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel emailRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailRow.setOpaque(false);
        emailRow.add(lblEmailTag);
        emailRow.add(lblEmail);
        infoPanel.add(emailRow);

        // Dòng ĐỊA CHỈ
        infoPanel.add(Box.createVerticalStrut(10));
        JLabel lblAddressTag = new JLabel("ĐỊA CHỈ");
        lblAddressTag.setFont(new Font("Arial", Font.PLAIN, 12));
        lblAddressTag.setForeground(Color.GRAY);
        lblAddressTag.setAlignmentX(SwingConstants.LEFT);

        lblAddress = new JLabel(kh.getDiaChi_KhachHang());
        JPanel addressRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addressRow.setOpaque(false);
        addressRow.add(lblAddressTag);
        addressRow.add(lblAddress);
        infoPanel.add(addressRow);

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

        btnThanhtoan = new JButton("THANH TOÁN");
        btnThanhtoan.setFont(new Font("Arial", Font.BOLD, 16));
        btnThanhtoan.setForeground(Color.WHITE);
        btnThanhtoan.setBackground(Color.RED);
        btnThanhtoan.setFocusPainted(false);
        btnThanhtoan.setPreferredSize(new Dimension(200, 40));
        btnThanhtoan.addActionListener(this);

        btnQuayLai = new JButton("QUAY LẠI");
        btnQuayLai.setFont(new Font("Arial", Font.BOLD, 16));
        btnQuayLai.setForeground(Color.WHITE);
        btnQuayLai.setBackground(Color.RED);
        btnQuayLai.setFocusPainted(false);
        btnQuayLai.setPreferredSize(new Dimension(200, 40));
        btnQuayLai.addActionListener(this);

        buttonPanel.add(btnQuayLai);
        buttonPanel.add(btnThanhtoan);

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


    public void updateItems(ArrayList<ChiTietDon_DTO> products) {
        if (products == null || products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong giỏ hàng!", 
                                          "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        panelDanhSachSanPham.removeAll();
    
        for (ChiTietDon_DTO chitiet : products) {
            JPanel itemPanel = new JPanel(new BorderLayout(10, 10));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            itemPanel.setBackground(Color.WHITE);
    
            JLabel imageLabel = new JLabel();
            try {
                String imagePath = "GUI/user/ProductImage/" + chitiet.getThongTinSanPham().getID_SanPham() + ".png";
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
            infoPanel.add(new JLabel(chitiet.getThongTinSanPham().getTen_SanPham()));
            infoPanel.add(new JLabel("Số lượng: " + chitiet.getSoLuongMua()));

            DecimalFormat formatter = new DecimalFormat("#,###");
            String formattedPrice = formatter.format(chitiet.getThongTinSanPham().getGia_SanPham()*chitiet.getSoLuongMua());
            infoPanel.add(new JLabel("Giá: " + formattedPrice + "₫"));
    
            itemPanel.add(infoPanel, BorderLayout.CENTER);
    
            panelDanhSachSanPham.add(Box.createVerticalStrut(5));
            panelDanhSachSanPham.add(itemPanel);
        }
    
        panelDanhSachSanPham.revalidate();
        panelDanhSachSanPham.repaint();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThanhtoan) {
            if (phuongThucThanhToan == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức thanh toán!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(phuongThucThanhToan.equals("Thanh toán khi nhận hàng")){
                ThongTinDonHang.setPTTT("COD");
            }
            else{
                ThongTinDonHang.setPTTT("Chuyển khoản");
            }
            ThongTinDonHang.setDiaChiDat(lblAddress.getText());
            ThongTinDonHang.output();

            dhbll.addDonHang(ThongTinDonHang); // thêm đơn hàng mới vào csdl

            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            giohang.resetGioHang();
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
}