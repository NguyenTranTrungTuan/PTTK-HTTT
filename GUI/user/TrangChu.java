package GUI.user;

import javax.swing.*;
import GUI.giohang.GioHangGUI;

import java.awt.*;
import java.awt.event.*;

public class TrangChu extends JFrame {
    private HeaderPanel header;
    private JPanel ContentPanel;
    private CatalogPanel catalogPanel;
    private ProductPanel productPanel;
    private UserInfoPanel UserInfo;
    private FilterPanel Filter;
    private ConfirmFrame confirmFrame;
   


    
    private GioHangGUI gioHangGUI;
    private CardLayout cardLayout; 

    public TrangChu() {
        gioHangGUI = new GioHangGUI();
        initComponents();
        setTitle("Trang Chủ");
        setResizable(false);
    }

    // KeyListener xử lý Enter trong ô tìm kiếm
    KeyListener KeyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !header.searchBox.getText().equals("")) {
                SwitchToFilter();
            }
        }
    };

    // MouseListener xử lý tương tác giao diện
    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();

            if (source == catalogPanel.headerLabel) {
                updateProductPanel("All", null, null);
            } else if (source == catalogPanel.SamsungLabel) {
                catalogPanel.paintLabel("SAMSUNG");
                updateProductPanel("Samsung", null, null);
            } else if (source == catalogPanel.AppleLabel) {
                catalogPanel.paintLabel("APPLE");
                updateProductPanel("Apple", null, null);
            } else if (source == catalogPanel.XiaomiLabel) {
                catalogPanel.paintLabel("XIAOMI");
                updateProductPanel("Xiaomi", null, null);
            } else if (source == catalogPanel.OppoLabel) {
                catalogPanel.paintLabel("OPPO");
                updateProductPanel("Oppo", null, null);
            } else if (source == catalogPanel.NokiaLabel) {
                catalogPanel.paintLabel("NOKIA");
                updateProductPanel("Nokia", null, null);
            } else if (source == header.accountLabel && !header.accountLabel.getText().equals("")) {
                SwitchToUserMenu();
            } else if (source == header.logoIcon) {
                SwitchToShop();
            } else if (source == header.searchIcon && !header.searchBox.getText().equals("")) {
                SwitchToFilter();
            } else if (source == header.cartIcon&&!header.accountLabel.getText().equals("")) {
                SwitchToCart();
            } else if (Filter != null && source == Filter.FilterButton &&
                    !Filter.MinPriceTF.getText().equals("Giá thấp nhất") &&
                    !Filter.MaxPriceTF.getText().equals("Giá cao nhất")) {
                String keyword = header.searchBox.getText().equals("Search...") ? null : header.searchBox.getText();
                updateProductPanel("Search", keyword, Filter);
            } else if (UserInfo != null && source == UserInfo.logoutButton) {
                confirmFrame = new ConfirmFrame();
                confirmFrame.confirmbtn.addMouseListener(this);
                confirmFrame.cancelbtn.addMouseListener(this);
            } else if (confirmFrame != null && source == confirmFrame.confirmbtn && !header.accountLabel.getText().equals("")) {
                header.accountLabel.setText("");
                confirmFrame.dispose();
                SwitchToShop();
            } else if (confirmFrame != null && source == confirmFrame.cancelbtn) {
                confirmFrame.dispose();
            }

            ContentPanel.revalidate();
            ContentPanel.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Object src = e.getSource();
            if (src == catalogPanel.SamsungLabel) catalogPanel.paintLabel("SAMSUNG");
            else if (src == catalogPanel.AppleLabel) catalogPanel.paintLabel("APPLE");
            else if (src == catalogPanel.XiaomiLabel) catalogPanel.paintLabel("XIAOMI");
            else if (src == catalogPanel.OppoLabel) catalogPanel.paintLabel("OPPO");
            else if (src == catalogPanel.NokiaLabel) catalogPanel.paintLabel("NOKIA");

            ContentPanel.revalidate();
            ContentPanel.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for (JLabel label : catalogPanel.list) {
                label.setOpaque(false);
            }
            ContentPanel.revalidate();
            ContentPanel.repaint();
        }
    };

   
    public void SwitchToFilter() {
        Filter = new FilterPanel();
        Filter.FilterButton.addMouseListener(mouseListener);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));
        filterPanel.setBackground(Color.decode("#cfdef3"));

        productPanel = new ProductPanel("Search", header.searchBox.getText(), null, gioHangGUI);

        filterPanel.add(Filter);
        filterPanel.add(productPanel);

        ContentPanel.add(filterPanel, "Filter");
        cardLayout.show(ContentPanel, "Filter");
    }

    
    public void SwitchToShop() {
        cardLayout.show(ContentPanel, "Shop");
    }

 
    public void SwitchToCart() {
        if(!header.accountLabel.getText().equals("")) {
            gioHangGUI.kh = header.kh;
        } 
        cardLayout.show(ContentPanel, "Cart");
    }


    public void updateProductPanel(String category, String keyword, FilterPanel filter) {
        JPanel newShopPanel = new JPanel();
        newShopPanel.setLayout(new BoxLayout(newShopPanel, BoxLayout.X_AXIS));
        newShopPanel.setBackground(Color.decode("#cfdef3"));

        catalogPanel = new CatalogPanel();
        catalogPanel.headerLabel.addMouseListener(mouseListener);
        for (JLabel label : catalogPanel.list) {
            label.addMouseListener(mouseListener);
        }

        productPanel = new ProductPanel(category, keyword, filter, gioHangGUI);
        newShopPanel.add(catalogPanel);
        newShopPanel.add(productPanel);

        ContentPanel.add(newShopPanel, "Shop");
        cardLayout.show(ContentPanel, "Shop");
    }

   
    public void SwitchToUserMenu() {
        UserInfo = new UserInfoPanel(header.kh);
        /*UserInfo.NameInfo.setText(header.kh.getTen_KhachHang());
        UserInfo.PhoneInfo.setText(header.kh.getSdt_KhachHang());
        UserInfo.EmailInfo.setText(header.kh.getEmail_KhachHang());
        UserInfo.AddressInfo.setText(header.kh.getDiaChi_KhachHang());
        UserInfo.PasswordInfo.setText(header.kh.getPass_KhachHang());*/

        UserInfo.logoutButton.addMouseListener(mouseListener);

        ContentPanel.add(UserInfo, "UserMenu");
        cardLayout.show(ContentPanel, "UserMenu");
    }

    // Khởi tạo giao diện
    public void initComponents() {
        header = new HeaderPanel(this);
        header.accountLabel.addMouseListener(mouseListener);
        header.logoIcon.addMouseListener(mouseListener);
        header.searchIcon.addMouseListener(mouseListener);
        header.searchBox.addKeyListener(KeyListener);
        header.cartIcon.addMouseListener(mouseListener);

        cardLayout = new CardLayout();
        ContentPanel = new JPanel(cardLayout);

        // Tạo Shop Panel
        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.X_AXIS));
        shopPanel.setBackground(Color.decode("#cfdef3"));

        catalogPanel = new CatalogPanel();
        catalogPanel.headerLabel.addMouseListener(mouseListener);
        for (JLabel label : catalogPanel.list) {
            label.addMouseListener(mouseListener);
        }

        productPanel = new ProductPanel("All", null, null, gioHangGUI);
        shopPanel.add(catalogPanel);
        shopPanel.add(productPanel);

        ContentPanel.add(shopPanel, "Shop");
        ContentPanel.add(gioHangGUI, "Cart");
        ContentPanel.add(new JPanel(), "UserMenu"); 

        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#cfdef3"));
        add(header, BorderLayout.NORTH);
        add(ContentPanel, BorderLayout.CENTER);

        setBounds(0, 0, 1000, 730);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TrangChu();
    }
}
