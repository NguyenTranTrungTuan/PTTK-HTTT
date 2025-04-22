package GUI.user;
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
