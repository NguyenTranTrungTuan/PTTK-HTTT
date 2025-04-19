package GUI.user;
import GUI.giohang.GioHangGUI;
import qlgiohang.oop.Product;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionListener;

public class ProductItem extends JPanel implements ActionListener{
    private MyButton addCart_btn;
    private JLabel Imagelb;
    private JLabel Namelb;
    private JLabel PriceTaglb;
    private boolean selected;
    private GioHangGUI gioHangGUI; 
    private Model_ProductItem data;

    public String getName(){
        return Namelb.getText();
    }

    public ProductItem(Model_ProductItem data,GioHangGUI gioHangGUI) {
        initComponents(data);
        this.selected = false;
        setBorder(BorderFactory.createLineBorder(new Color(238, 238, 238), 1));
        setBackground(Color.WHITE); 
        setPreferredSize(new Dimension(200, 300));
        addCart_btn.addActionListener(this); // Đăng ký sự kiện với chính lớp này
        this.gioHangGUI = gioHangGUI; // Lưu tham chiếu đến gioHangGUI
    }

    private void initComponents(Model_ProductItem data) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 300)); 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        //new ImageIcon("user/ProductImage/LTG.png")
        Imagelb = new JLabel();
        Imagelb.setPreferredSize(new Dimension(180,180));
        Imagelb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Imagelb.setAlignmentX(Component.CENTER_ALIGNMENT);
        Imagelb.setIcon(data.toImage());
        // Imagelb.setBackground(new Color(205,205,205));

        Namelb = new JLabel("Product Name");
        Namelb.setText(data.getTitle());
        Namelb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        Namelb.setAlignmentX(Component.CENTER_ALIGNMENT);

        PriceTaglb = new JLabel("price");
        PriceTaglb.setText(data.getPrice());
        PriceTaglb.setFont(new Font("Segoe UI", Font.BOLD, 16));
        PriceTaglb.setForeground(new Color(255, 102, 102));
        PriceTaglb.setAlignmentX(Component.CENTER_ALIGNMENT);

        addCart_btn = new MyButton("Thêm vào giỏ");
        addCart_btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        addCart_btn.setBackground(Color.decode("#00B4DB"));
        addCart_btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add spacing and components
        add(Box.createVerticalStrut(10)); // Space at the top
        add(Imagelb);
        add(Box.createVerticalStrut(10));
        add(Namelb);
        add(Box.createVerticalStrut(5));
        add(PriceTaglb);
        add(Box.createVerticalStrut(10));
        add(addCart_btn);
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalGlue()); // Push everything upwards
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == addCart_btn) {
            Product sanPhamMoi = new Product(
                data.getTitle(),
                Double.parseDouble(data.getPrice().replaceAll("[^\\d]", "")), // Chuyển giá từ String sang double
                1 // Số lượng mặc định là 1
            );
            gioHangGUI.themSanPhamVaoGio(sanPhamMoi); // Thêm sản phẩm vào giỏ hàng
            JOptionPane.showMessageDialog(this, "Đã thêm sản phẩm vào giỏ hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

