package GUI.user;

import javax.swing.*;
import java.awt.*;


public class ConfirmFrame extends JFrame{
    private JPanel headerPanel = new JPanel();
    private JLabel header = new JLabel("Bạn có chắc không?");
    private JPanel btPanel = new JPanel();
    protected MyButton confirmbtn = new MyButton("Xác nhận");
    protected MyButton cancelbtn = new MyButton("Hủy");

    public ConfirmFrame(){
        initComponents();
    }

    public void initComponents(){
        header.setFont(new Font("Segoe UI", Font.BOLD, 30));
        header.setForeground(Color.BLACK);
        header.setOpaque(false);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(header);
        header.setPreferredSize(new Dimension(500,50));


        confirmbtn.setBackground(Color.decode("#00B4DB"));
        confirmbtn.setForeground(Color.WHITE);
        confirmbtn.setPreferredSize(new Dimension(150,70));
        confirmbtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        cancelbtn.setBackground(Color.RED);
        cancelbtn.setForeground(Color.WHITE);
        cancelbtn.setPreferredSize(new Dimension(150,70));
        cancelbtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btPanel.setBackground(Color.WHITE);
        btPanel.setPreferredSize(new Dimension(500,200));
        btPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        btPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        btPanel.add(confirmbtn);
        btPanel.add(Box.createHorizontalStrut(100));
        btPanel.add(cancelbtn);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(headerPanel);
        add(btPanel);

        setBackground(Color.WHITE);
        setBounds(0, 0, 500, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
