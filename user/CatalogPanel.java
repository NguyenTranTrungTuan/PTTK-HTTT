package user;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CatalogPanel extends JPanel {
    protected JLabel headerLabel;
    private JPanel listPanel;
    protected JLabel SamsungLabel = new JLabel("SAMSUNG");
    protected JLabel AppleLabel = new JLabel("APPLE");
    protected JLabel XiaomiLabel = new JLabel("XIAOMI");
    protected JLabel OppoLabel = new JLabel("OPPO");
    protected JLabel NokiaLabel = new JLabel("NOKIA");
    protected JLabel list[] = {SamsungLabel, AppleLabel, XiaomiLabel, OppoLabel, NokiaLabel};

    public CatalogPanel() {
        initComponents();
    }

    // MouseListener mouseListener = new MouseListener() {
    //     @Override
    //     public void mouseClicked(MouseEvent e) {
    //         if (e.getSource() == headerLabel) {
    //             for ( JLabel label : list){
    //                 label.setOpaque(false);
    //             }
    //             ContentPanel.remove(productPanel);
    //             productPanel = new ProductPanel("All");
    //             ContentPanel.add(productPanel);
    //         }
    //         else if (e.getSource() == SamsungLabel) {
    //             paintLabel("SAMSUNG");
    //             ContentPanel.remove(productPanel);
    //             productPanel = new ProductPanel("Samsung");
    //             ContentPanel.add(productPanel);
    //         } 
    //         else if (e.getSource() == AppleLabel) {
    //             paintLabel("APPLE");
    //             ContentPanel.remove(productPanel);
    //             productPanel = new ProductPanel("Apple");
    //             ContentPanel.add(productPanel);
    //         } 
    //         else if (e.getSource() == XiaomiLabel) {
    //             paintLabel("XIAOMI");
    //             ContentPanel.remove(productPanel);
    //             productPanel = new ProductPanel("Xiaomi");
    //             ContentPanel.add(productPanel);
    //         } 
    //         else if (e.getSource() == OppoLabel) {
    //             paintLabel("OPPO");
    //             ContentPanel.remove(productPanel);
    //             productPanel = new ProductPanel("Oppo");
    //             ContentPanel.add(productPanel);
    //         } 
    //         else if (e.getSource() == NokiaLabel) {
    //             paintLabel("NOKIA");
    //             ContentPanel.remove(productPanel);
    //             productPanel = new ProductPanel("Nokia");
    //             ContentPanel.add(productPanel);
    //         }
    //         // Revalidate and repaint the ContentPanel to reflect changes
    //         ContentPanel.revalidate();
    //         ContentPanel.repaint();
    //         }

    //     @Override
    //     public void mousePressed(MouseEvent e) {}

    //     @Override
    //     public void mouseReleased(MouseEvent e) {}

    //     @Override
    //     public void mouseEntered(MouseEvent e) {
    //         if (e.getSource() == SamsungLabel) {
    //             paintLabel("SAMSUNG");
    //         } 
    //         else if (e.getSource() == AppleLabel) {
    //             paintLabel("APPLE");
    //         } 
    //         else if (e.getSource() == XiaomiLabel) {
    //             paintLabel("XIAOMI");
    //         } 
    //         else if (e.getSource() == OppoLabel) {
    //             paintLabel("OPPO");
    //         } 
    //         else if (e.getSource() == NokiaLabel) {
    //             paintLabel("NOKIA");
    //         }
    //         // Revalidate and repaint the ContentPanel to reflect changes
    //         ContentPanel.revalidate();
    //         ContentPanel.repaint();
    //     }
    //     @Override
    //     public void mouseExited(MouseEvent e) {}
    // };

    public void paintLabel(String name){
        for (JLabel label : list){
            if (label.getText().equals(name)){
                label.setBackground(new Color(255,255,255, 80));
                label.setOpaque(true);
                label.revalidate();
                label.repaint();
            }
            else{
                label.setOpaque(false);
                label.revalidate();
                label.repaint();
            }
        }
    }

    private void initComponents() {
        headerLabel = new JLabel();
        listPanel = new JPanel();

        for (JLabel lb : list) {
            lb.setFont(new Font("Segoe UI", 1, 20));
            lb.setForeground(Color.WHITE);
            lb.setMaximumSize(new Dimension(120, 50));
            lb.setHorizontalAlignment(SwingConstants.LEADING);
            listPanel.add(lb);
            listPanel.add(Box.createVerticalStrut(15));
        }

        headerLabel.setFont(new Font("Segoe UI", 1, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setText("DANH MỤC");
        headerLabel.setBackground(Color.decode("#00B4DB"));
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.decode("#00B4DB"));
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        setPreferredSize(new Dimension(200, 540));
        setBackground(Color.decode("#cfdef3"));
        setLayout(new BorderLayout());

        add(headerLabel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }
}
