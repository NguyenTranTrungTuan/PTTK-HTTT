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
    private JPanel shopPanel;
    private JPanel searchPanel;
    private CardLayout cardLayout; 

    public TrangChu() {
        gioHangGUI = new GioHangGUI(this);
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
                updateShopPanel("All");
                SwitchToShop();
            } else if (source == catalogPanel.SamsungLabel) {
                catalogPanel.paintLabel("SAMSUNG");
                updateShopPanel("Samsung");
                SwitchToShop();
            } else if (source == catalogPanel.AppleLabel) {
                catalogPanel.paintLabel("APPLE");
                updateShopPanel("Apple");
                SwitchToShop();
            } else if (source == catalogPanel.XiaomiLabel) {
                catalogPanel.paintLabel("XIAOMI");
                updateShopPanel("Xiaomi");
                SwitchToShop();
            } else if (source == catalogPanel.OppoLabel) {
                catalogPanel.paintLabel("OPPO");
                updateShopPanel("Oppo");
                SwitchToShop();
            } else if (source == catalogPanel.NokiaLabel) {
                catalogPanel.paintLabel("NOKIA");
                updateShopPanel("Nokia");
                SwitchToShop();
            } else if (source == header.accountLabel && !header.accountLabel.getText().equals("")) {
                SwitchToUserMenu();
            } else if (source == header.logoIcon) {
                SwitchToShop();
            } else if (source == header.searchIcon && !header.searchBox.getText().equals("")) {
                SwitchToFilter();
            } else if (source == header.cartIcon) {
                System.out.println("test");
                System.out.println(header.accountLabel.getText());
                if(!header.accountLabel.getText().equals("")){
                    SwitchToCart();
                }else{
                    header.dangnhapFrame = new DangNhap();
                    header.addDangNhapEvent();
                }
                
            } else if (Filter != null && source == Filter.FilterButton &&
                    !Filter.MinPriceTF.getText().equals("Giá thấp nhất") &&
                    !Filter.MaxPriceTF.getText().equals("Giá cao nhất")) {
                String keyword = header.searchBox.getText().equals("Search...") ? null : header.searchBox.getText();
                updateSearchPanel("Search", keyword, Filter);
            } else if (UserInfo != null && source == UserInfo.logoutButton) {
                confirmFrame = new ConfirmFrame();
                confirmFrame.confirmbtn.addMouseListener(this);
                confirmFrame.cancelbtn.addMouseListener(this);
            } else if (confirmFrame != null && source == confirmFrame.confirmbtn && !header.accountLabel.getText().equals("")) {
                header.accountLabel.setText("");
                confirmFrame.dispose();
                gioHangGUI.resetGioHang();
                header.kh = null;
                header.nv = null;
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

        productPanel = new ProductPanel("Search", header.searchBox.getText(), null, gioHangGUI, header);

        searchPanel.removeAll();
        searchPanel.add(Filter);
        searchPanel.add(productPanel);

        cardLayout.show(ContentPanel, "Filter");
    }

    
    public void SwitchToShop() {
        cardLayout.show(ContentPanel, "Shop");
    }

 
    public void SwitchToCart() {
        if(!header.accountLabel.getText().equals("")) {
            gioHangGUI.kh = header.kh;
            gioHangGUI.ql = header.nv;
        } 
        cardLayout.show(ContentPanel, "Cart");
    }

    public void SwitchToUserMenu() {
        UserInfo.khachhang = header.kh;
        UserInfo.BtnPanel = new JPanel();
        UserInfo.updateUserInfo(header.kh, header.nv);
        UserInfo.revalidate();
        UserInfo.repaint();

        cardLayout.show(ContentPanel, "UserMenu");
    }


    public void updateShopPanel(String category) {

        catalogPanel = new CatalogPanel();
        catalogPanel.headerLabel.addMouseListener(mouseListener);
        for (JLabel label : catalogPanel.list) {
            label.addMouseListener(mouseListener);
        }

        productPanel = new ProductPanel(category, null, null, gioHangGUI, header);
        shopPanel.removeAll();
        shopPanel.add(catalogPanel);
        shopPanel.add(productPanel);

        shopPanel.revalidate();
        shopPanel.repaint();
        // cardLayout.show(ContentPanel, "Shop");
    }

    public void updateSearchPanel(String category, String keyword, FilterPanel filter){
        Filter = new FilterPanel();
        Filter.FilterButton.addMouseListener(mouseListener);

        productPanel = new ProductPanel(category, keyword, filter, gioHangGUI, header);
        searchPanel.add(Filter);
        searchPanel.add(productPanel);

        // ContentPanel.add(searchPanel, "Filter");
        searchPanel.removeAll();
        searchPanel.add(Filter);
        searchPanel.add(productPanel);

        searchPanel.revalidate();
        searchPanel.repaint();
        cardLayout.show(ContentPanel, "Filter");
    }

    // Khởi tạo giao diện
    public void initComponents() {
        header = new HeaderPanel();
        header.accountLabel.addMouseListener(mouseListener);
        header.logoIcon.addMouseListener(mouseListener);
        header.searchIcon.addMouseListener(mouseListener);
        header.searchBox.addKeyListener(KeyListener);
        header.cartIcon.addMouseListener(mouseListener);

        cardLayout = new CardLayout();
        ContentPanel = new JPanel(cardLayout);

        // Tạo Shop Panel
        shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.X_AXIS));
        shopPanel.setBackground(Color.decode("#cfdef3"));

        catalogPanel = new CatalogPanel();
        catalogPanel.headerLabel.addMouseListener(mouseListener);
        for (JLabel label : catalogPanel.list) {
            label.addMouseListener(mouseListener);
        }

        productPanel = new ProductPanel("All", null, null, gioHangGUI, header);
        shopPanel.add(catalogPanel);
        shopPanel.add(productPanel);

        // Thêm shopPanel vào cardlayout
        ContentPanel.add(shopPanel, "Shop");

        // Thêm giỏ hàng vào cardlayout
        ContentPanel.add(gioHangGUI, "Cart");

        // Thêm userPanel vào cardlayout
        UserInfo = new UserInfoPanel();
        UserInfo.logoutButton.addMouseListener(mouseListener);
        ContentPanel.add(UserInfo, "UserMenu"); 

        // Thêm search Panel vào cardlayout
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBackground(Color.decode("#cfdef3"));
        ContentPanel.add(searchPanel, "Filter");

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
