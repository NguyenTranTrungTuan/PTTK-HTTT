package GUI.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserMenuPanel extends JPanel{
    protected JPanel UsernamePanel;
    protected JLabel UsernameLabel;
    protected JPanel OptionPanel;
    protected JLabel InfoOptionLabel = new JLabel("Thông tin");
    protected JLabel HistoryOptionLabel = new JLabel("Đơn hàng");
    protected JLabel[] OptionList = {InfoOptionLabel, HistoryOptionLabel};

    public UserMenuPanel(){
        initComponents();
    }

    public void paintLabel(String name){
        for (JLabel label : OptionList){
            if (label.getText().equals(name)){
                label.setBackground(new Color(230, 230, 230));
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

    private void initComponents(){
        UsernamePanel = new JPanel();
        UsernameLabel = new JLabel();
        OptionPanel = new JPanel();

        UsernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        UsernameLabel.setForeground(Color.WHITE);
        UsernameLabel.setOpaque(false);
        UsernameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        OptionPanel.setLayout(new BoxLayout(OptionPanel, BoxLayout.Y_AXIS));
        for( JLabel option : OptionList){
            option.setFont(new Font("Segoe UI", 1, 16));
            option.setForeground(Color.BLACK);
            option.setPreferredSize(new Dimension(200, 30));
            option.setHorizontalAlignment(SwingConstants.LEFT);
            OptionPanel.add(option);
            OptionPanel.add(Box.createVerticalStrut(15));
        }
        InfoOptionLabel.setBackground(new Color(230, 230, 230));
        InfoOptionLabel.setOpaque(true);

        UsernamePanel.setBackground(Color.decode("#00B4DB"));
        UsernamePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        UsernamePanel.setMaximumSize(new Dimension(200, 60));
        UsernamePanel.add(UsernameLabel);

        OptionPanel.setBackground(Color.WHITE);
        OptionPanel.setOpaque(true);
        OptionPanel.setBorder(BorderFactory.createEmptyBorder(20,0, 20, 0));
        OptionPanel.setPreferredSize(new Dimension(200, 100));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        setMaximumSize(new Dimension(200, 300));
        setBackground(Color.decode("#cfdef3"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(TOP_ALIGNMENT);

        add(UsernamePanel);
        add(OptionPanel);
    }

}
