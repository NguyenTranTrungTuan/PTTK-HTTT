package GUI.giohang;

import DTO.ChiTietDon_DTO;
import DTO.DienThoai_DTO;
import DTO.DonHang_DTO;
import DTO.KhachHang_DTO;
import DTO.NhanVien_DTO;
import GUI.user.TrangChu;
import BLL.PhieuNhap_BLL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GioHangGUI extends JPanel {

    public ArrayList<ChiTietDon_DTO> products;

    private JLabel labelTongTienValue;
    private JPanel itemPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton btnDatHang;

    protected ThongTinDatHang thongTinDatHang;
    protected DonHang_DTO ThongTinDonHang;
    private PhieuNhap_BLL pnbll;
    public KhachHang_DTO kh;
    public NhanVien_DTO ql;

    private Double total = 0.0;
    private TrangChu parent;

    public GioHangGUI(TrangChu parent) {
        this.pnbll = new PhieuNhap_BLL();
        this.setLayout(new BorderLayout());
        this.products = new ArrayList<>();
        this.parent = parent;

        this.cardLayout = new CardLayout();
        this.contentPanel = new JPanel(cardLayout);
        this.add(contentPanel, BorderLayout.CENTER);
        
        JPanel gioHangPanel = initGioHangPanel();
        contentPanel.add(gioHangPanel, "GioHang");
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

        labelTongTienValue = new JLabel("0₫");
        labelTongTienValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelTongTienValue.setForeground(Color.RED);

        totalPanel.add(lbTongTienLabel);
        totalPanel.add(labelTongTienValue);

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

        return navPanel;
    }

    private JLabel createNavLabel(String text, boolean isActive) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(isActive ? new Color(230, 0, 0) : Color.GRAY);
        label.setBorder(new EmptyBorder(0, 10, 0, 10));
        return label;
    }

    public void themSanPhamVaoGio(DienThoai_DTO dt) {
        boolean daTonTai = false;
        for (ChiTietDon_DTO chitiet : products) {
            DienThoai_DTO dienthoai = chitiet.getThongTinSanPham();
            if (dt.getID_SanPham().equals(dienthoai.getID_SanPham())) {
                chitiet.setSoLuongMua(chitiet.getSoLuongMua() + 1);
                daTonTai = true;
                break;
            }
        }
        ChiTietDon_DTO chitiet = new ChiTietDon_DTO("", dt, 1, dt.getGia_SanPham(), pnbll.getAllIDCTPhieuNhapFromIDDienThoai(dt.getID_SanPham()).get(0));
        if (!daTonTai) {
            products.add(chitiet);
        }
        refreshProductList();
    }

    private void refreshProductList() {
        itemPanel.removeAll();

        for (ChiTietDon_DTO chitiet : products) {
            ChiTietDon_DTO currentItem = chitiet;

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
        total = 0.0;
        for (ChiTietDon_DTO chitiet : products){
            total+= chitiet.getThanhTien();
        }

        labelTongTienValue.setText(String.format("%,.0f₫", total));
    }

    private void handleDatHang(ActionEvent e) {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống! Vui lòng thêm sản phẩm trước khi đặt hàng.",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ThongTinDonHang = new DonHang_DTO(
                "", 
                "", 
                "", 
                "", 
                layNgayHienTai(), 
                "", 
                "Chưa xử lý", 
                products, 
                total
                );
        if (kh!=null)
            ThongTinDonHang.setIdKhachHang(kh.getId_KhachHang());
        else{
            ThongTinDonHang.setIdKhachHang(null);
        }

        thongTinDatHang = new ThongTinDatHang(cardLayout, contentPanel,kh,ql,ThongTinDonHang,this);
        thongTinDatHang.updateItems(products);

        contentPanel.add(thongTinDatHang, "ThongTinNhanHang");
        cardLayout.show(contentPanel, "ThongTinNhanHang");
    }

    public void resetGioHang() {
        parent.updateShopPanel("All");
        products.clear();
        refreshProductList();
        if(thongTinDatHang!= null)
            contentPanel.remove(thongTinDatHang);
    }
    

    public String layNgayHienTai() {
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ngayHienTai.format(dinhDang);
    }
}