package GUI.user;

import javax.swing.*;

import BLL.KhachHang_BLL;
import DTO.KhachHang_DTO;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;;

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

    protected JPanel BtnPanel = new JPanel();
    protected MyButton logoutButton = new MyButton("ĐĂNG XUẤT");

    protected MyButton EditInfoButton = new MyButton("SỬA THÔNG TIN");
    protected MyButton EditPasswordButton = new MyButton("ĐỔI MẬT KHẨU");
    protected EditUserInfo EditInfo;
    protected ChangePassword ChangePasswordFrame;
    protected KhachHang_DTO khachhang;
    protected KhachHang_BLL khbll;

    // Regex cho email
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    // Regex cho số điện thoại Việt Nam (bắt đầu bằng 0, theo sau là 9 số)
    private static final String PHONE_PATTERN = 
        "^0[35789][0-9]{8}$";

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
           if(e.getSource() == EditInfoButton){
                if(EditInfo != null)
                    EditInfo.dispose();
                EditInfo = new EditUserInfo(khachhang);
                addEditInfoEvent();
           }
           else if(e.getSource() == EditPasswordButton){
                if(ChangePasswordFrame != null)
                    ChangePasswordFrame.dispose();
                ChangePasswordFrame = new ChangePassword();
                addChangePasswordEvent();
           }
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    };

    private void addChangePasswordEvent(){
        ChangePasswordFrame.btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String OldmatKhau = new String(ChangePasswordFrame.txtMatKhau_Old.getPassword()).trim();
                String NewmatKhau = new String(ChangePasswordFrame.txtMatKhau_New.getPassword()).trim();
                if (OldmatKhau.isEmpty() || NewmatKhau.isEmpty()) {
                    JOptionPane.showMessageDialog(EditInfo, 
                        "Vui lòng điền đầy đủ thông tin!", 
                        "Cảnh báo", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }else{
                    if (NewmatKhau.length() < 8) {
                        JOptionPane.showMessageDialog(ChangePasswordFrame, 
                            "Mật khẩu mới phải dài ít nhất 8 ký tự!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(OldmatKhau.equals(khachhang.getPass_KhachHang())){
                        JOptionPane.showMessageDialog(EditInfo, "Đổi mật khẩu thành công!");
                        khachhang.setPass_KhachHang(NewmatKhau);
                        System.err.println(khbll.changeKhachHangData(khachhang));
                        PasswordInfo.setText(NewmatKhau);
                        ChangePasswordFrame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(ChangePasswordFrame, "Sai mật khẩu cũ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addEditInfoEvent(){
        EditInfo.btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String hoTen = EditInfo.txtHoTen.getText();
                String sdt = EditInfo.txtSDT.getText();
                String email = EditInfo.txtEmail.getText();
                String diaChi = (String) EditInfo.cbDiaChi.getSelectedItem();

                // Kiểm tra các trường rỗng
                if (hoTen.isEmpty() || sdt.isEmpty() || email.isEmpty() ||  diaChi == null) {
                    JOptionPane.showMessageDialog(EditInfo, 
                        "Vui lòng điền đầy đủ thông tin!", 
                        "Cảnh báo", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Kiểm tra định dạng số điện thoại
                if (!Pattern.matches(PHONE_PATTERN, sdt)) {
                    JOptionPane.showMessageDialog(EditInfo, 
                        "Số điện thoại không đúng định dạng!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra định dạng email
                if (!Pattern.matches(EMAIL_PATTERN, email)) {
                    JOptionPane.showMessageDialog(EditInfo, 
                        "Email không đúng định dạng!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                JOptionPane.showMessageDialog(EditInfo, "Sửa thông tin thành công!");
                khachhang.setTen_KhachHang(hoTen);
                khachhang.setEmail_KhachHang(email);
                khachhang.setSdt_KhachHang(sdt);
                khachhang.setDiaChi_KhachHang(diaChi);
                System.err.println(khbll.changeKhachHangData(khachhang));

                NameInfo.setText(hoTen);
                EmailInfo.setText(email);
                PhoneInfo.setText(sdt);
                AddressInfo.setText(diaChi);
                EditInfo.dispose();
            }
        });
    }


    public UserInfoPanel(){
        khbll = new KhachHang_BLL();
        EditInfoButton.addMouseListener(mouseListener);
        EditPasswordButton.addMouseListener(mouseListener);
        // initComponents(kh);
    }

    protected void initComponents(KhachHang_DTO kh){
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
        PasswordInfo.setText(kh.getPass_KhachHang());

        NameInfo.setText(kh.getTen_KhachHang());
        NamePanel.setLayout(new BorderLayout());
        NamePanel.setPreferredSize(new Dimension(740, 15));
        NamePanel.setBackground(Color.WHITE);
        NamePanel.add(NameLabel, BorderLayout.WEST);
        NamePanel.add(NameInfo, BorderLayout.EAST);


        PhoneInfo.setText(kh.getSdt_KhachHang());
        PhonePanel.setLayout(new BorderLayout());
        PhonePanel.setPreferredSize(new Dimension(740, 15));
        PhonePanel.setBackground(Color.WHITE);
        PhonePanel.add(PhoneNumberLabel, BorderLayout.WEST);
        PhonePanel.add(PhoneInfo, BorderLayout.EAST);

        EmailInfo.setText(kh.getEmail_KhachHang());
        EmailPanel.setLayout(new BorderLayout());
        EmailPanel.setPreferredSize(new Dimension(740, 15));
        EmailPanel.setBackground(Color.WHITE);
        EmailPanel.add(EmailLabel, BorderLayout.WEST);
        EmailPanel.add(EmailInfo, BorderLayout.EAST);

        AddressInfo.setText(kh.getDiaChi_KhachHang());
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

        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(100,50));

        EditInfoButton.setBackground(Color.GRAY);
        EditInfoButton.setForeground(Color.WHITE);
        EditInfoButton.setPreferredSize(new Dimension(150,50));

        EditPasswordButton.setBackground(Color.BLUE);
        EditPasswordButton.setForeground(Color.WHITE);
        EditPasswordButton.setPreferredSize(new Dimension(150,50));

        BtnPanel.setLayout(new BoxLayout(BtnPanel, BoxLayout.X_AXIS));
        BtnPanel.setBackground(Color.WHITE);
        BtnPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
        BtnPanel.add(EditInfoButton);
        BtnPanel.add(Box.createHorizontalStrut(10));
        BtnPanel.add(EditPasswordButton);
        BtnPanel.add(Box.createHorizontalStrut(10));
        BtnPanel.add(logoutButton);
        BtnPanel.add(Box.createHorizontalGlue());

        setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
        setMaximumSize(new Dimension(740, 440));
        setBackground(Color.decode("#cfdef3"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(TOP_ALIGNMENT);

        add(headerPanel);
        add(InfoPanel);
        add(BtnPanel);
    }
}   
