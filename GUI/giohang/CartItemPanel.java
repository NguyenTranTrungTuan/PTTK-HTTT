package GUI.giohang;

import javax.swing.*;

import DTO.ChiTietDon_DTO;
import DTO.DienThoai_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartItemPanel extends RoundedPanel implements ActionListener {
    private final DienThoai_DTO product;
    private ChiTietDon_DTO chitiet;
    private JLabel lbSoLuong;
    private final Runnable onUpdate;
    private final ActionListener onDelete;

    private JButton btnMinus;
    private JButton btnPlus;

    public CartItemPanel(ChiTietDon_DTO chitiet, Runnable onUpdate, ActionListener onDelete) {
        super(20);
        this.chitiet = chitiet;
        this.product = chitiet.getThongTinSanPham();
        this.onUpdate = onUpdate;
        this.onDelete = onDelete;

        setLayout(new BorderLayout(15, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createImageLabel(), BorderLayout.WEST);
        add(createInfoPanel(), BorderLayout.CENTER);
        add(createRightPanel(), BorderLayout.EAST);
    }

    private JLabel createImageLabel() {
        String imagePath = "GUI/user/ProductImage/" + product.getID_SanPham() + ".png";
        ImageIcon icon;
        try {
            icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        } catch (Exception e) {
            icon = new ImageIcon("GUI/user/ProductImage/default.png");
        }

        JLabel lblImage = new JLabel(icon);
        lblImage.setPreferredSize(new Dimension(100, 100));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        return lblImage;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel lbTitle = new JLabel(product.getTen_SanPham());
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTitle.setForeground(Color.BLACK);

        JLabel lbPrice = new JLabel(String.format("%,.0f₫", product.getGia_SanPham()));
        lbPrice.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbPrice.setForeground(new Color(220, 20, 60));

        infoPanel.add(lbTitle);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lbPrice);

        return infoPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBackground(Color.WHITE);

        rightPanel.add(createQuantityPanel(), BorderLayout.CENTER);
        rightPanel.add(createDeleteButton(), BorderLayout.SOUTH);

        return rightPanel;
    }

    private JPanel createQuantityPanel() {
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        quantityPanel.setBackground(Color.WHITE);

        btnMinus = new JButton("-");
        styleButton(btnMinus);
        btnMinus.setPreferredSize(new Dimension(40, 30));
        lbSoLuong = new JLabel(String.valueOf(chitiet.getSoLuongMua()));
        lbSoLuong.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbSoLuong.setHorizontalAlignment(SwingConstants.CENTER);

        btnPlus = new JButton("+");
        styleButton(btnPlus);
        btnPlus.setPreferredSize(new Dimension(40, 30));
        btnMinus.addActionListener(this);
        btnPlus.addActionListener(this);

        quantityPanel.add(btnMinus);
        quantityPanel.add(lbSoLuong);
        quantityPanel.add(btnPlus);

        return quantityPanel;
    }

    private JButton createDeleteButton() {
        JButton btnDelete = new JButton("Xóa");
        btnDelete.setBackground(new Color(220, 53, 69));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDelete.addActionListener(onDelete);
        return btnDelete;
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(40, 30)); // bạn có thể tăng lên nếu cần
        button.setFocusPainted(false);
        button.setBackground(new Color(240, 240, 240));
        button.setFont(new Font("Segoe UI", Font.BOLD, 18)); // nhỏ hơn nếu bị tràn
        button.setMargin(new Insets(0, 0, 0, 0)); // xóa padding
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // viền rõ hơn nếu muốn
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnMinus) {
            if (chitiet.getSoLuongMua() > 1) {
                chitiet.setSoLuongMua(chitiet.getSoLuongMua() - 1);
                lbSoLuong.setText(String.valueOf(chitiet.getSoLuongMua()));
                onUpdate.run(); // cập nhật tổng tiền
            }
        } else if (source == btnPlus) {
            chitiet.setSoLuongMua(chitiet.getSoLuongMua() + 1);
            lbSoLuong.setText(String.valueOf(chitiet.getSoLuongMua()));
            onUpdate.run(); // cập nhật tổng tiền
        }
    }
}
