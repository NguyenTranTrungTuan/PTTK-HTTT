package qlgiohang;
import GUI.user.MyButton;
import qlgiohang.oop.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GioHangGUI extends JPanel implements ActionListener {

    private JLabel lbTongTienValue;
    private JPanel itemPanel;
    private List<Product> products;
    private JButton btnDatHang;

    public GioHangGUI() {
        products = new ArrayList<>();
        initComponents();
        updateTongTien();
    }

    public void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10)); 

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navPanel.add(createNavLabel("Giỏ hàng", true));
        navPanel.add(createNavLabel("Thông tin đặt hàng", false));
        navPanel.add(createNavLabel("Thanh toán", false));
        navPanel.add(createNavLabel("Hoàn tất", false));
        this.add(navPanel, BorderLayout.NORTH);

        // Item Panel
        itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS)); 
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        addProduct("Màn hình Asus TUF GAMING VG279QE5A-R 27\" IPS 146Hz chuyên game", 3290000);
        addProduct("Laptop Dell Inspiron 15 3000", 15990000);

        this.add(new JScrollPane(itemPanel), BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbTongTienLabel = new JLabel("Tổng tiền:");
        lbTongTienLabel.setFont(lbTongTienLabel.getFont().deriveFont(Font.BOLD, 16));
        totalPanel.add(lbTongTienLabel);

        lbTongTienValue = new JLabel("0₫");
        lbTongTienValue.setFont(lbTongTienValue.getFont().deriveFont(Font.BOLD, 18));
        lbTongTienValue.setForeground(Color.RED);
        totalPanel.add(lbTongTienValue);
        bottomPanel.add(totalPanel, BorderLayout.WEST);

        btnDatHang = new JButton("ĐẶT HÀNG NGAY");
        btnDatHang.setBackground(Color.RED);
        btnDatHang.setForeground(Color.WHITE);
        btnDatHang.setFont(btnDatHang.getFont().deriveFont(Font.BOLD, 16));
        btnDatHang.setPreferredSize(new Dimension(200, 40));
        btnDatHang.addActionListener(this); 
        bottomPanel.add(btnDatHang, BorderLayout.EAST);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addProduct(String tenSanPham, double giaMoi) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());
        productPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        Product product = new Product(tenSanPham, giaMoi, 1);
        products.add(product);

        // Image
        JLabel lbhinhanh = new JLabel(new ImageIcon("src/images/phone.png"));
        lbhinhanh.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lbhinhanh.setHorizontalAlignment(SwingConstants.CENTER);
        lbhinhanh.setPreferredSize(new Dimension(140, 80));
        productPanel.add(lbhinhanh, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel(new BorderLayout());

        JLabel lbTenSanPham = new JLabel(tenSanPham);
        lbTenSanPham.setFont(lbTenSanPham.getFont().deriveFont(Font.BOLD, 14));
        detailsPanel.add(lbTenSanPham, BorderLayout.NORTH);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbGiaMoi = new JLabel(String.format("%,.0f₫", giaMoi));
        lbGiaMoi.setFont(lbGiaMoi.getFont().deriveFont(Font.BOLD, 16));
        lbGiaMoi.setForeground(Color.RED);
        pricePanel.add(lbGiaMoi);
        detailsPanel.add(pricePanel, BorderLayout.CENTER);

        productPanel.add(detailsPanel, BorderLayout.CENTER);

        // Quantity Control Panel
        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        quantityPanel.setPreferredSize(new Dimension(150,80));
        JButton btnGiam = new JButton("-");
        btnGiam.setPreferredSize(new Dimension(30, 30));
        btnGiam.setBackground(Color.LIGHT_GRAY);
        btnGiam.setForeground(Color.BLACK);
        btnGiam.setOpaque(true);
        JLabel lbSoLuong = new JLabel(String.valueOf(product.getSoLuong()));
        lbSoLuong.setPreferredSize(new Dimension(30, 30));
        lbSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        JButton btnThem = new JButton("+");
        btnThem.setPreferredSize(new Dimension(30, 30));
        btnThem.setBackground(Color.LIGHT_GRAY);
        btnThem.setOpaque(true);
        btnGiam.addActionListener(e -> {
            if (product.getSoLuong() > 1) {
                product.setSoLuong(product.getSoLuong() - 1);
                lbSoLuong.setText(String.valueOf(product.getSoLuong()));
                updateTongTien();
            } else if (product.getSoLuong() == 1) {
                // Xóa sản phẩm nếu số lượng giảm về 0
                products.remove(product);
                itemPanel.remove(productPanel);
                itemPanel.revalidate();
                itemPanel.repaint();
                updateTongTien();
            }
        });

        btnThem.addActionListener(e -> {
            product.setSoLuong(product.getSoLuong() + 1);
            lbSoLuong.setText(String.valueOf(product.getSoLuong()));
            updateTongTien();
        });

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBackground(Color.RED);
        btnXoa.setPreferredSize(new Dimension(60, 30));
        btnXoa.addActionListener(e -> {
            products.remove(product);
            itemPanel.remove(productPanel);
            itemPanel.revalidate();
            itemPanel.repaint();
            updateTongTien();
        });
        //them cac nut vao 
        quantityPanel.add(btnGiam);
        quantityPanel.add(lbSoLuong);
        quantityPanel.add(btnThem);
        quantityPanel.add(btnXoa);

        productPanel.add(quantityPanel, BorderLayout.EAST);
        itemPanel.add(productPanel);
    }

    private JLabel createNavLabel(String text, boolean isActive) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 14));
        label.setForeground(isActive ? Color.RED : Color.GRAY);
        return label;
    }

    private void updateTongTien() {
        double tongTien = 0;
        for (Product product : products) {
            tongTien += product.getGiaMoi() * product.getSoLuong();
        }
        lbTongTienValue.setText(String.format("%,.0f₫", tongTien));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDatHang) {
            JOptionPane.showMessageDialog(this, "Đặt hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Giỏ hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GioHangGUI());
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}