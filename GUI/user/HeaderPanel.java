package GUI.user;

import javax.swing.*;

import BLL.KhachHang_BLL;
import BLL.NhanVien_BLL;
import DTO.KhachHang_DTO;
import DTO.NhanVien_DTO;
import GUI.giohang.GioHangGUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.regex.Pattern;
import java.util.Map;

public class HeaderPanel extends JPanel {
    private JPanel logoPanel, searchPanel, btnPanel;
    protected JLabel logoIcon, searchIcon, accountIcon, cartIcon, accountLabel;
    protected JTextField searchBox;
    protected DangKy dangkyFrame;
    protected DangNhap dangnhapFrame;
    private KhachHang_BLL khbll;
    private NhanVien_BLL nvbll;
    public KhachHang_DTO kh;
    public NhanVien_DTO nv;

    // Regex cho email
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    // Regex cho số điện thoại Việt Nam (bắt đầu bằng 0, theo sau là 9 số)
    private static final String PHONE_PATTERN = 
        "^0[35789][0-9]{8}$";


    public HeaderPanel() {
        initComponents();
    }

    FocusListener Focus = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (searchBox.getText().equals("Search...")) {
                searchBox.setText("");
                searchBox.setForeground(Color.BLACK);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (searchBox.getText().isEmpty()) {
                searchBox.setForeground(Color.GRAY);
                searchBox.setText("Search...");
            }
        }
    };

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == accountIcon){
                if (dangnhapFrame != null){
                    dangnhapFrame.dispose();
                }
                if(accountLabel.getText()==""){
                    dangnhapFrame = new DangNhap();
                    addDangNhapEvent();
                }
            }
    }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == accountLabel  && !accountLabel.getText().equals("")){
                Font font = accountLabel.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                accountLabel.setFont(font.deriveFont(attributes));
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == accountLabel  && !accountLabel.getText().equals("")){
                Font font = accountLabel.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, null);
                accountLabel.setFont(font.deriveFont(attributes));
            }
        }
    };

    private void addDangKyEvent(){
        dangkyFrame.btnDangKy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String hoTen = dangkyFrame.txtHoTen.getText();
                String sdt = dangkyFrame.txtSDT.getText();
                String email = dangkyFrame.txtEmail.getText();
                String matKhau = new String(dangkyFrame.txtMatKhau.getPassword());
                String diaChi = (String) dangkyFrame.cbDiaChi.getSelectedItem();

                // Kiểm tra các trường rỗng
                if (hoTen.isEmpty() || sdt.isEmpty() || email.isEmpty() || matKhau.isEmpty() || diaChi == null) {
                    JOptionPane.showMessageDialog(dangkyFrame, 
                        "Vui lòng điền đầy đủ thông tin!", 
                        "Cảnh báo", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Kiểm tra định dạng số điện thoại
                if (!Pattern.matches(PHONE_PATTERN, sdt)) {
                    JOptionPane.showMessageDialog(dangkyFrame, 
                        "Số điện thoại không đúng định dạng!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra định dạng email
                if (!Pattern.matches(EMAIL_PATTERN, email)) {
                    JOptionPane.showMessageDialog(dangkyFrame, 
                        "Email không đúng định dạng!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra độ dài mật khẩu
                if (matKhau.length() < 8) {
                    JOptionPane.showMessageDialog(dangkyFrame, 
                        "Mật khẩu phải dài ít nhất 8 ký tự!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                kh = new KhachHang_DTO(khbll.getNextID(), hoTen,sdt,diaChi,email,matKhau);
                JOptionPane.showMessageDialog(dangkyFrame, "Đăng ký thành công!");
                System.out.println(khbll.addKhachHang(kh));
                accountLabel.setText(hoTen);
                dangkyFrame.dispose();
            }
        });
    }

    protected void addDangNhapEvent(){
        dangnhapFrame.btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = dangnhapFrame.txtEmail.getText().trim();
                String matKhau = new String(dangnhapFrame.txtMatKhau.getPassword()).trim();
                kh = khbll.getKhachHangFromAccount(email, matKhau);
                nv = nvbll.getNhanVienFromAccount(email, matKhau);
                if (kh != null) {
                    JOptionPane.showMessageDialog(dangnhapFrame, "Đăng nhập thành công!");
                    accountLabel.setText(kh.getTen_KhachHang());
                    dangnhapFrame.dispose();
                    
                }
                else if(nv != null){
                    JOptionPane.showMessageDialog(dangnhapFrame, "Đăng nhập thành công!");
                    accountLabel.setText(nv.getTenNV());
                    dangnhapFrame.dispose();
                } 
                else {
                    JOptionPane.showMessageDialog(dangnhapFrame, "Sai email hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dangnhapFrame.btnDangKy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dangnhapFrame.dispose();
                dangkyFrame = new DangKy();
                addDangKyEvent();
            }
        });
    }


    private void initComponents() {
        khbll = new KhachHang_BLL();
        nvbll = new NhanVien_BLL();
        // Header panel settings
        setPreferredSize(new Dimension(1000, 100));
        setBackground(Color.decode("#0083B0"));
        setLayout(new BorderLayout());

        // ==== LOGO PANEL ====
        logoPanel = new JPanel();
        logoPanel.setLayout(null);
        logoPanel.setPreferredSize(new Dimension(200, 100));
        logoPanel.setOpaque(false);

        logoIcon = new JLabel(new ImageIcon("GUI/user/Icon/home.png")); 
        logoIcon.setBounds(100, 30, 30, 40);
        logoPanel.add(logoIcon);

        // ==== SEARCH PANEL ====
        searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setPreferredSize(new Dimension(500, 100));
        searchPanel.setOpaque(false);

        searchIcon = new JLabel(new ImageIcon("GUI/user/Icon/search.png"));
        searchIcon.setBounds(10, 30, 30, 40);

        searchBox = new JTextField("Search...");
        searchBox.setBounds(50, 30, 400, 40);
        searchBox.setForeground(Color.GRAY);
        searchBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        searchPanel.add(searchIcon);
        searchPanel.add(searchBox);

        // ==== BUTTON PANEL ====
        btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(300, 100));
        btnPanel.setOpaque(false);
        btnPanel.setLayout(null);

        accountIcon = new JLabel(new ImageIcon("GUI/user/Icon/user.png"));
        accountIcon.setBounds(20, 30, 30, 40);

        cartIcon = new JLabel(new ImageIcon("GUI/user/Icon/shopping-cart.png"));
        cartIcon.setBounds(180, 30, 40, 40);

        accountLabel = new JLabel("");
        accountLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        accountLabel.setForeground(Color.WHITE);
        accountLabel.setBounds(60, 40, 100, 30);

        btnPanel.add(accountIcon);
        btnPanel.add(accountLabel);
        btnPanel.add(cartIcon);

        accountIcon.addMouseListener(mouseListener);
        accountLabel.addMouseListener(mouseListener);
        searchBox.addFocusListener(Focus);

        // ==== ADD TO HEADER ====
        add(logoPanel, BorderLayout.WEST);
        add(searchPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.EAST);
    }
}

