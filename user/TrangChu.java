package user;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;;


public class TrangChu extends JFrame{
    private HeaderPanel header;
    private JPanel ContentPanel;
    private CatalogPanel catalogPanel;
    private ProductPanel productPanel;

    public TrangChu(){
        initComponents();
        setTitle("Trang Chủ");
        setResizable(false);
    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == catalogPanel.headerLabel) {
                for ( JLabel label : catalogPanel.list){
                    label.setOpaque(false);
                }
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("All");
                ContentPanel.add(productPanel);
            }
            else if (e.getSource() == catalogPanel.SamsungLabel) {
                catalogPanel.paintLabel("SAMSUNG");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Samsung");
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.AppleLabel) {
                catalogPanel.paintLabel("APPLE");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Apple");
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.XiaomiLabel) {
                catalogPanel.paintLabel("XIAOMI");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Xiaomi");
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.OppoLabel) {
                catalogPanel.paintLabel("OPPO");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Oppo");
                ContentPanel.add(productPanel);
            } 
            else if (e.getSource() == catalogPanel.NokiaLabel) {
                catalogPanel.paintLabel("NOKIA");
                ContentPanel.remove(productPanel);
                productPanel = new ProductPanel("Nokia");
                ContentPanel.add(productPanel);
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
        public void mouseExited(MouseEvent e) {}
    };

    public void initComponents(){
        JPanel spacer = new JPanel();
        header = new HeaderPanel();

        ContentPanel = new JPanel();
        catalogPanel = new CatalogPanel();
        catalogPanel.headerLabel.addMouseListener(mouseListener);
        for ( JLabel label : catalogPanel.list){
            label.addMouseListener(mouseListener);
        }
        productPanel = new ProductPanel("All");
        spacer.setPreferredSize(new Dimension(20, 1));
        spacer.setOpaque(false);
        

        ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.X_AXIS));
        ContentPanel.setBackground(Color.decode("#cfdef3"));
        ContentPanel.add(catalogPanel);
        ContentPanel.add(spacer);
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
