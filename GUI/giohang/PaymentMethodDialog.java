package GUI.giohang;
import GUI.user.Model_ProductItem;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDialog extends JDialog implements ActionListener {

    public interface PaymentMethodCallback {
        void onPaymentMethodSelected(String method);
    }

    private final List<JButton> methodButtons = new ArrayList<>();
    private JButton confirmButton;
    private JButton selectedButton = null;
    private String selectedMethod = null;

    private final Color primaryColor = new Color(215, 0, 24);
    private final PaymentMethodCallback callback;

    public PaymentMethodDialog(JFrame parent, PaymentMethodCallback callback) {
        super(parent, "Chọn phương thức thanh toán", true);
        this.callback = callback;
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        
    }

    private void initComponents() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setBackground(Color.WHITE);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Chọn phương thức thanh toán");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton closeButton = new JButton("✕");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setForeground(Color.GRAY);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setFocusPainted(false);
        closeButton.setBackground(null);
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> dispose());
        headerPanel.add(closeButton, BorderLayout.EAST);

        contentPane.add(headerPanel);
        contentPane.add(Box.createVerticalStrut(15));

        // Available label
        JLabel availableLabel = new JLabel("KHẢ DỤNG");
        availableLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        availableLabel.setForeground(Color.GRAY);
        contentPane.add(availableLabel);
        contentPane.add(Box.createVerticalStrut(10));

        // Payment methods
        String[] methodNames = {
                "Thanh toán khi nhận hàng",
                "Chuyển khoản ngân hàng qua mã QR",
               
        };

        String[] iconPaths = {
                "/images/cod_icon.png",
                "/images/qr_icon.png",
                "/images/vnpay_icon.png",
                "/images/onepay_icon.png",
                "/images/dots_icon.png"
        };

        for (int i = 0; i < methodNames.length; i++) {
            JButton methodButton = createMethodButton(methodNames[i], iconPaths[i]);
            methodButtons.add(methodButton);
            contentPane.add(methodButton);
            contentPane.add(Box.createVerticalStrut(8));
        }

        // Confirm button
        confirmButton = new JButton("Xác nhận");
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmButton.setBackground(new Color(240, 240, 240));
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setBorder(new LineBorder(new Color(220, 220, 220)));
        confirmButton.setFocusPainted(false);
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(this);

        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(confirmButton);
        
        add(contentPane);
    }

    private JButton createMethodButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        try {
            button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        } catch (Exception e) {
            System.out.println("Không tìm thấy icon: " + iconPath);
        }
        button.setIconTextGap(15);
        button.setPreferredSize(new Dimension(350, 45));
        button.setMaximumSize(new Dimension(350, 45));
        button.setMinimumSize(new Dimension(350, 45));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == confirmButton) {
            if (selectedMethod != null) {
                JOptionPane.showMessageDialog(this,
                        "Đã xác nhận phương thức: " + selectedMethod,
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                if (callback != null) {
                    callback.onPaymentMethodSelected(selectedMethod);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng chọn phương thức thanh toán.",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            }
            return;
        }

        // Chọn phương thức thanh toán
        for (JButton btn : methodButtons) {
            if (source == btn) {
                if (selectedButton != null) {
                    selectedButton.setBorder(new LineBorder(new Color(220, 220, 220), 1));
                    selectedButton.setBackground(Color.WHITE);
                }

                selectedButton = btn;
                selectedMethod = btn.getText();
                selectedButton.setBorder(new LineBorder(primaryColor, 2));
                selectedButton.setBackground(new Color(255, 240, 240));

                confirmButton.setBackground(primaryColor);
                confirmButton.setForeground(Color.WHITE);
                break;
            }
        }
    }

    
    public String getSelectedMethod() {
        return selectedMethod;
    }

    
}
