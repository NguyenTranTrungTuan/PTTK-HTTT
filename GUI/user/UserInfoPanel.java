package GUI.user;

import javax.swing.*;
import java.awt.*;

public class UserInfoPanel extends JPanel{
    protected JPanel headerPanel = new JPanel();
    protected JLabel headerLabel = new JLabel("THÔNG TIN CÁ NHÂN");
    protected JPanel InfoPanel = new JPanel();

    protected JPanel NamePanel = new JPanel();
    protected JLabel NameLabel = new JLabel("Họ và Tên:");
    protected JLabel NameInfo = new JLabel();

    protected JPanel PhonePanel = new JPanel();
    protected JLabel PhoneNumberLabel = new JLabel("Số điện thoại:");
    protected JLabel PhoneInfo = new JLabel();


    protected JPanel EmailPanel = new JPanel();
    protected JLabel EmailLabel = new JLabel("Email:");
    protected JLabel EmailInfo = new JLabel();


    protected JPanel AddressPanel = new JPanel();
    protected JLabel AddressLabel = new JLabel("Địa chỉ mặc định:");
    protected JLabel AddressInfo = new JLabel();


    protected JPanel PasswordPanel = new JPanel();
    protected JLabel PasswordLabel = new JLabel("Mật khẩu:");
    protected JPasswordField PasswordInfo = new JPasswordField();

    protected JPanel[] PanelList = {NamePanel, PhonePanel, EmailPanel, AddressPanel, PasswordPanel};
    protected JLabel[] LabelList = {NameLabel, PhoneNumberLabel, EmailLabel, AddressLabel, PasswordLabel};
    protected JLabel[] InfoList = {NameInfo, PhoneInfo, EmailInfo, AddressInfo};


    public UserInfoPanel(){
        initComponents();
    }

    private void initComponents(){
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setOpaque(false);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerPanel.add(headerLabel);

        for(JLabel lb : LabelList){
            lb.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lb.setForeground(Color.BLACK);
            lb.setOpaque(false);
            lb.setHorizontalAlignment(SwingConstants.LEFT);
        }

        for(JLabel info : InfoList){
            info.setFont(new Font("Segoe UI", 1, 16));
            info.setForeground(Color.BLACK);
            info.setOpaque(false);
            info.setHorizontalAlignment(SwingConstants.LEFT);
        }

        PasswordInfo.setEditable(false);
        PasswordInfo.setPreferredSize(new Dimension(200, 15));
        PasswordInfo.setBackground(Color.WHITE);

        NamePanel.setLayout(new BorderLayout());
        NamePanel.setPreferredSize(new Dimension(740, 15));
        NamePanel.setBackground(Color.WHITE);
        NamePanel.add(NameLabel, BorderLayout.WEST);
        NamePanel.add(NameInfo, BorderLayout.EAST);

        PhonePanel.setLayout(new BorderLayout());
        PhonePanel.setPreferredSize(new Dimension(740, 15));
        PhonePanel.setBackground(Color.WHITE);
        PhonePanel.add(PhoneNumberLabel, BorderLayout.WEST);
        PhonePanel.add(PhoneInfo, BorderLayout.EAST);

        EmailPanel.setLayout(new BorderLayout());
        EmailPanel.setPreferredSize(new Dimension(740, 15));
        EmailPanel.setBackground(Color.WHITE);
        EmailPanel.add(EmailLabel, BorderLayout.WEST);
        EmailPanel.add(EmailInfo, BorderLayout.EAST);

        AddressPanel.setLayout(new BorderLayout());
        AddressPanel.setPreferredSize(new Dimension(740, 15));
        AddressPanel.setBackground(Color.WHITE);
        AddressPanel.add(AddressLabel, BorderLayout.WEST);
        AddressPanel.add(AddressInfo, BorderLayout.EAST);

        PasswordPanel.setLayout(new BorderLayout());
        PasswordPanel.setPreferredSize(new Dimension(740, 15));
        PasswordPanel.setBackground(Color.WHITE);
        PasswordPanel.add(PasswordLabel, BorderLayout.WEST);
        PasswordPanel.add(PasswordInfo, BorderLayout.EAST);

        InfoPanel.setLayout(new BoxLayout(InfoPanel, BoxLayout.Y_AXIS));
        for(JPanel panel : PanelList){
            InfoPanel.add(panel);
            // InfoPanel.add(Box.createVerticalStrut(15));
        }
        InfoPanel.setBackground(Color.WHITE);
        InfoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));

        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        setMaximumSize(new Dimension(740, 440));
        setBackground(Color.decode("#cfdef3"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(TOP_ALIGNMENT);

        add(headerPanel);
        add(InfoPanel);
    }
}   
