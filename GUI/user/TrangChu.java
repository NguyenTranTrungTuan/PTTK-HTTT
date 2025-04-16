package GUI.user;
import javax.swing.*;

import OOP.GioHang;

import java.awt.*;
import java.awt.event.*;


public class TrangChu extends JFrame{
    private HeaderPanel header;
    private JPanel ContentPanel;
    private CatalogPanel catalogPanel;
    private ProductPanel productPanel;
    private GioHang gh;
    public JPanel getContentPanel() {
        return ContentPanel;
    }    // private UserMenuPanel UserMenu;
    private UserInfoPanel UserInfo;
    private FilterPanel Filter;
    private ConfirmFrame confirmFrame;

    public TrangChu(){
        initComponents();
        setTitle("Trang Chủ");
        setResizable(false);
    }

    KeyListener KeyListener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !header.searchBox.getText().equals("")){
                SwitchToFilter();
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    };

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == catalogPanel.headerLabel) {
                for ( JLabel label : catalogPanel.list){
                    label.setOpaque(false);
                }
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("All", null, null);
                ContentPanel.add(productPanel);
            }
            else if (e.getSource() == catalogPanel.SamsungLabel) {
                catalogPanel.paintLabel("SAMSUNG");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Samsung", null, null);
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.AppleLabel) {
                catalogPanel.paintLabel("APPLE");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Apple", null, null);
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.XiaomiLabel) {
                catalogPanel.paintLabel("XIAOMI");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Xiaomi", null, null);
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.OppoLabel) {
                catalogPanel.paintLabel("OPPO");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Oppo", null, null);
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.NokiaLabel) {
                catalogPanel.paintLabel("NOKIA");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Nokia", null, null);
                ContentPanel.add(productPanel);
            }
            else if (e.getSource() == header.accountLabel  && !header.accountLabel.getText().equals("")){
                SwitchToUserMenu();
            }
            else if (e.getSource() == header.logoIcon){
                SwitchToShop();
            }
            else if(e.getSource() == header.searchIcon && !header.searchBox.getText().equals("")){
                SwitchToFilter();
            }
            else if(Filter != null && e.getSource() == Filter.FilterButton && !Filter.MinPriceTF.getText().equals("Giá thấp nhất") && !Filter.MaxPriceTF.getText().equals("Giá cao nhất")){
                ContentPanel.remove(productPanel);
                // ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.X_AXIS));
                if(header.searchBox.getText().equals("Search...")){
                    productPanel = new ProductPanel("All", null, Filter);
                }
                else{
                    productPanel = new ProductPanel("Search", header.searchBox.getText(), Filter);
                    
                }
                ContentPanel.add(productPanel);
            }
            else if( UserInfo != null && e.getSource() == UserInfo.logoutButton){
                confirmFrame = new ConfirmFrame();
                confirmFrame.confirmbtn.addMouseListener(mouseListener);
                confirmFrame.cancelbtn.addMouseListener(mouseListener);
            }
            else if(confirmFrame != null && e.getSource() == confirmFrame.confirmbtn && !header.accountLabel.getText().equals("")){
                header.accountLabel.setText("");
                confirmFrame.dispose();
                SwitchToShop();
            }
            else if(confirmFrame != null && e.getSource() == confirmFrame.cancelbtn){
                confirmFrame.dispose();
            }
            // Revalidate and repaint the ContentPanel to reflect changes
            ContentPanel.revalidate();
            ContentPanel.repaint();
            }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}
            
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == catalogPanel.SamsungLabel) {
                catalogPanel.paintLabel("SAMSUNG");
            } 
            else if (e.getSource() == catalogPanel.AppleLabel) {
                catalogPanel.paintLabel("APPLE");
            } 
            else if (e.getSource() == catalogPanel.XiaomiLabel) {
                catalogPanel.paintLabel("XIAOMI");
            } 
            else if (e.getSource() == catalogPanel.OppoLabel) {
                catalogPanel.paintLabel("OPPO");
            } 
            else if (e.getSource() == catalogPanel.NokiaLabel) {
                catalogPanel.paintLabel("NOKIA");
            }
            
            // Revalidate and repaint the ContentPanel to reflect changes
            ContentPanel.revalidate();
            ContentPanel.repaint();
        }
        @Override
        public void mouseExited(MouseEvent e) {
            for (JLabel label : catalogPanel.list){
                label.setOpaque(false);
            }
            ContentPanel.revalidate();
            ContentPanel.repaint();
        }
    };

    public void SwitchToFilter(){
        ContentPanel.removeAll();;
        ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.X_AXIS));

        Filter = new FilterPanel();
        Filter.FilterButton.addMouseListener(mouseListener);
        productPanel = new ProductPanel("Search", header.searchBox.getText(), null);

        // ContentPanel.add(Filter);
        ContentPanel.add(Filter);
        ContentPanel.add(productPanel);

        ContentPanel.revalidate();
        ContentPanel.repaint();
    }

    public void SwitchToShop(){
        ContentPanel.removeAll();
        ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.X_AXIS));
        productPanel = new ProductPanel("All", null, null);
        catalogPanel = new CatalogPanel();
        catalogPanel.headerLabel.addMouseListener(mouseListener);
        for ( JLabel label : catalogPanel.list){
            label.addMouseListener(mouseListener);
        }
        ContentPanel.add(catalogPanel);
        ContentPanel.add(productPanel);
        ContentPanel.revalidate();
        ContentPanel.repaint();
    }

    public void SwitchToUserMenu(){
        ContentPanel.removeAll();

        // UserMenu.UsernameLabel.setText(header.accountLabel.getText().toUpperCase());
        // for(JLabel lb : UserMenu.OptionList){
        //     lb.addMouseListener(mouseListener);
        // }
        ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.Y_AXIS));

        UserInfo = new UserInfoPanel();
        UserInfo.NameInfo.setText(header.kh.getTen_KhachHang());
        UserInfo.PhoneInfo.setText(header.kh.getSdt_KhachHang());
        UserInfo.EmailInfo.setText(header.kh.getEmail_KhachHang());
        UserInfo.AddressInfo.setText(header.kh.getDiaChi_KhachHang());
        UserInfo.PasswordInfo.setText(header.kh.getPass_KhachHang());

        UserInfo.logoutButton.addMouseListener(mouseListener);

        // ContentPanel.add(UserMenu);
        // ContentPanel.add(Box.createHorizontalStrut(20));
        ContentPanel.add(UserInfo);
        ContentPanel.revalidate();
        ContentPanel.repaint();
    }

    public void initComponents(){
        header = new HeaderPanel(this);
        gh=new GioHang();        header.accountLabel.addMouseListener(mouseListener);
        header.logoIcon.addMouseListener(mouseListener);
        header.searchIcon.addMouseListener(mouseListener);
        header.searchBox.addKeyListener(KeyListener);

        ContentPanel = new JPanel();
        catalogPanel = new CatalogPanel();
        catalogPanel.headerLabel.addMouseListener(mouseListener);
        for ( JLabel label : catalogPanel.list){
            label.addMouseListener(mouseListener);
        }
        productPanel = new ProductPanel("All", null, null);

        ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.X_AXIS));
        ContentPanel.setBackground(Color.decode("#cfdef3"));
        ContentPanel.add(catalogPanel);
        ContentPanel.add(productPanel);
        ContentPanel.setPreferredSize(new Dimension(960,540));
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#cfdef3"));
        add(header, BorderLayout.NORTH);
        add(ContentPanel, BorderLayout.CENTER);
        setBounds(0, 0, 1000, 730);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String args[]){
        new TrangChu();
    }
}
