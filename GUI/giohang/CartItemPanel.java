package GUI.giohang;

import GUI.user.Model_ProductItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartItemPanel extends RoundedPanel implements ActionListener {
    private final Model_ProductItem product;
    private JLabel lbSoLuong;
    private final Runnable onUpdate;
    private final ActionListener onDelete;

    private JButton btnMinus;
    private JButton btnPlus;

    public CartItemPanel(Model_ProductItem product, Runnable onUpdate, ActionListener onDelete) {
        super(20);
        this.product = product;
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
        String imagePath = "GUI/user/ProductImage/" + product.getImageicon() + ".png";
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

        JLabel lbTitle = new JLabel(product.getTitle());
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTitle.setForeground(Color.BLACK);

        JLabel lbPrice = new JLabel(String.format("%,.0f₫", product.getPrice()));
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
        int amount = Math.max(1, product.getAmount());
        lbSoLuong = new JLabel(String.valueOf(amount));
        lbSoLuong.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbSoLuong.setHorizontalAlignment(SwingConstants.CENTER);

        btnPlus = new JButton("+");
        styleButton(btnPlus);
        btnPlus.setPreferredSize(new Dimension(40, 40));
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
        button.setPreferredSize(new Dimension(40, 30));
        button.setFocusPainted(false);
        button.setBackground(new Color(240, 240, 240));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnMinus) {
            if (product.getAmount() > 1) {
                product.setAmount(product.getAmount() - 1);
                lbSoLuong.setText(String.valueOf(product.getAmount()));
                onUpdate.run(); // cập nhật tổng tiền
            }
        } else if (source == btnPlus) {
            product.setAmount(product.getAmount() + 1);
            lbSoLuong.setText(String.valueOf(product.getAmount()));
            onUpdate.run(); // cập nhật tổng tiền
        }
    }
}
