package GUI.giohang;

import GUI.user.Model_ProductItem;

import GUI.user.MyButton;
import DTO.DienThoai_DTO;
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
    private List<Model_ProductItem> products;
    private JButton btnDatHang;
    private ThongTinDatHang thongTinNhanHang;
    private JPanel contentPanel;
    private ThanhToanUIDesigner thanhtoan;
    private CardLayout cardLayout;
  
    private Model_ProductItem data;
    public Model_ProductItem getCurrentProduct() {
        return this.data;
    }
    public void setCurrentProduct(Model_ProductItem data) {
        this.data = data;
    }
    
    public GioHangGUI() {
        products = new ArrayList<>();
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        this.setLayout(new BorderLayout());
        this.add(contentPanel, BorderLayout.CENTER);
        JPanel gioHangPanel = initGioHangPanel();
        contentPanel.add(gioHangPanel, "GioHang");
        thongTinNhanHang = new ThongTinDatHang(cardLayout, contentPanel);
        contentPanel.add(thongTinNhanHang, "ThongTinNhanHang");
        thanhtoan = new ThanhToanUIDesigner(cardLayout, contentPanel);
        contentPanel.add(thanhtoan, "ThanhToan");
    
        // Thêm sản phẩm mẫu
        products.add(new Model_ProductItem("Điện thoại A", "image1", 1500000.0, 1));
        products.add(new Model_ProductItem("Điện thoại B", "image2", 2000000.0, 2));
    
        // Cập nhật giao diện
        capNhatGiaoDienGioHang();
        updateTongTien();
    }
    private JPanel initGioHangPanel() {
        JPanel gioHangPanel = new JPanel(new BorderLayout());
        gioHangPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navPanel.add(createNavLabel("Giỏ hàng", true));
        navPanel.add(createNavLabel("Thông tin đặt hàng", false));
        navPanel.add(createNavLabel("Thanh toán", false));
        navPanel.add(createNavLabel("Hoàn tất", false));
        gioHangPanel.add(navPanel, BorderLayout.NORTH);

        itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        gioHangPanel.add(new JScrollPane(itemPanel), BorderLayout.CENTER);
        
      
        
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbTongTienLabel = new JLabel("Tổng tiền:");
        lbTongTienLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPanel.add(lbTongTienLabel);

        lbTongTienValue = new JLabel("0₫");
        lbTongTienValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTongTienValue.setForeground(Color.RED);
        totalPanel.add(lbTongTienValue);
        bottomPanel.add(totalPanel, BorderLayout.WEST);
        JButton btnXoaTatCa = new JButton("Xóa tất cả");
        btnXoaTatCa.setBackground(Color.RED);
        btnXoaTatCa.setForeground(Color.WHITE);
        btnXoaTatCa.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXoaTatCa.addActionListener(e -> {
            products.clear();
            itemPanel.removeAll();
            updateTongTien();
            itemPanel.revalidate();
            itemPanel.repaint();
        });
        bottomPanel.add(btnXoaTatCa, BorderLayout.CENTER);
        btnDatHang = new JButton("ĐẶT HÀNG NGAY");
        btnDatHang.setBackground(new Color(215, 0, 24));
        btnDatHang.setForeground(Color.WHITE);
        btnDatHang.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDatHang.setPreferredSize(new Dimension(250, 45));
        btnDatHang.setFocusPainted(false);
        btnDatHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDatHang.addActionListener(this);
        bottomPanel.add(btnDatHang, BorderLayout.EAST);
        gioHangPanel.add(bottomPanel, BorderLayout.SOUTH);
     
        return gioHangPanel;
    }
    
    /*private void Product(Product sanpham){
        for(sanpham: products){
            if(sanpham.getTenSanPham().equals(sanpham.getTenSanPham())){
                sanpham.setSoLuong(sanpham.getSoLuong()+1);
                return;
            }
        }
        String tenSanPham=sanpham.getTenSanPham();
        String giaMoi=sanpham.getGiaMoi();
        String image=sanpham.getImageicon();
        String soLuong=sanpham.getSoLuong();    

    }*/

    private void addProduct(Model_ProductItem data) {
        JPanel productPanel = new JPanel(new BorderLayout(10, 10));
        productPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        productPanel.setBackground(Color.WHITE);
        String tenSanPham = data.getTitle();
        Double giaMoi = data.getPrice();
        String image=data.getImageicon();
        
    
        
    
        JLabel lbhinhanh = new JLabel();
        try {
            lbhinhanh.setIcon(new ImageIcon("GUI/user/ProductImage/" + image + ".png"));
        } catch (Exception e) {
            lbhinhanh.setText("No Image");
        }
        lbhinhanh.setHorizontalAlignment(SwingConstants.CENTER);
        lbhinhanh.setPreferredSize(new Dimension(140, 80));
        productPanel.add(lbhinhanh, BorderLayout.WEST);
            
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(Color.WHITE);
        JLabel lbTenSanPham = new JLabel(tenSanPham);
        lbTenSanPham.setFont(new Font("Segoe UI", Font.BOLD, 14));
        detailsPanel.add(lbTenSanPham, BorderLayout.NORTH);
    
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pricePanel.setBackground(Color.WHITE);
        JLabel lbGiaMoi = new JLabel(String.format("%,.0f₫", giaMoi));
        lbGiaMoi.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbGiaMoi.setForeground(Color.RED);
        pricePanel.add(lbGiaMoi);
        detailsPanel.add(pricePanel, BorderLayout.CENTER);
    
        productPanel.add(detailsPanel, BorderLayout.CENTER);
    
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JButton btnGiam = new JButton("-");
        JButton btnThem = new JButton("+");
        JButton btnXoa = new JButton("Xóa");
        JLabel lbSoLuong = new JLabel(String.valueOf(data.getAmount()));
    
        
        btnGiam.addActionListener(e -> {
            int currentAmount = data.getAmount();
            if (currentAmount > 1) {
                data.setAmount(currentAmount - 1);
                lbSoLuong.setText(String.valueOf(currentAmount - 1));
                updateTongTien();
            } else {
                products.remove(data);
                itemPanel.remove(productPanel);
                itemPanel.revalidate();
                itemPanel.repaint();
                updateTongTien();
            }
        });
    
        btnThem.addActionListener(e -> {
            data.setAmount(data.getAmount() + 1);
            lbSoLuong.setText(String.valueOf(data.getAmount()));
            updateTongTien();
        });
    
        btnXoa.addActionListener(e -> {
            products.remove(data);
            itemPanel.remove(productPanel);
            itemPanel.revalidate();
            itemPanel.repaint();
            updateTongTien();
        });
    
        quantityPanel.add(btnGiam);
        quantityPanel.add(lbSoLuong);
        quantityPanel.add(btnThem);
        quantityPanel.add(btnXoa);
        productPanel.add(quantityPanel, BorderLayout.EAST);
    
        itemPanel.add(productPanel);
    }
    public void themSanPhamVaoGio(Model_ProductItem data) {
        boolean daTonTai = false;
    
        // Kiểm tra xem sản phẩm đã tồn tại trong danh sách chưa
        for (Model_ProductItem p : products) {
            if (p.getTitle().equals(data.getTitle())) {
                int currentAmount = p.getAmount();
                int newAmount = currentAmount + data.getAmount();
                p.setAmount(newAmount); // Cập nhật số lượng
                daTonTai = true;
                break;
            }
        }
    
        // Nếu sản phẩm chưa tồn tại, thêm sản phẩm mới vào danh sách
        if (!daTonTai) {
            products.add(data);
        }
    
        // Cập nhật giao diện giỏ hàng
        capNhatGiaoDienGioHang();
    
        // Cập nhật tổng tiền
        updateTongTien();
    }
    private void capNhatGiaoDienGioHang() {
        itemPanel.removeAll(); // xóa toàn bộ giao diện cũ
        for (Model_ProductItem p : products) {
            addProduct(p); // add lại từng sản phẩm hiện có trong list
        }
        itemPanel.revalidate();
        itemPanel.repaint();
    }
    
   
   
    

    private JPanel createNavPanel() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
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

    private void updateTongTien() {
        double tongTien = 0;
    
        // Tính tổng tiền từ danh sách products
        for (Model_ProductItem product : products) {
            tongTien += product.getPrice() * product.getAmount();
        }
    
        // Cập nhật giá trị tổng tiền trên giao diện
        lbTongTienValue.setText(String.format("%,.0f₫", tongTien));
    }
    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnDatHang) {
        try {
            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giỏ hàng trống! Vui lòng thêm sản phẩm trước khi đặt hàng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            thongTinNhanHang.updateItems(products);
            cardLayout.show(contentPanel, "ThongTinNhanHang");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi đặt hàng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

   
}