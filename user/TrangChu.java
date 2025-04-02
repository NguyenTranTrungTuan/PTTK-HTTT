package user;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

public class TrangChu extends JFrame{
    private HeaderPanel header;
    private JPanel ContentPanel;
    private CatalogPanel catalogPanel;
    private ProductPanel productPanel;
    private DangNhap dangnhapFrame;
    private DangKy dangkyFrame;
    protected KhachHang khachhang;

    // Regex cho email
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    // Regex cho số điện thoại Việt Nam (bắt đầu bằng 0, theo sau là 9 số)
    private static final String PHONE_PATTERN = 
        "^0[35789][0-9]{8}$";

    public TrangChu(){
        initComponents();
        setTitle("Trang Chủ");
        setResizable(false);
    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == header.accountIcon){
                if(header.accountLabel.getText()==""){
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
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };

    private Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/pttkhttt", "root", "123456789");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private boolean CheckAccount(String email, String passwd){
        String query = "SELECT * FROM KHACHHANG WHERE Email = ? AND Passwordkh = ?";
        try (Connection con = connectDB()) {
            if (con != null) {
                PreparedStatement prestmt = con.prepareStatement(query);
                prestmt.setString(1, email);
                prestmt.setString(2, passwd);
                ResultSet rs = prestmt.executeQuery();
                if(rs.next()){
                    String id = rs.getString("MaKH");
                    String ten = rs.getString("TenKH");
                    String sdt = rs.getString("SDT");
                    String emailkh = rs.getString("Email");
                    String diachi = rs.getString("DiaChiKH");
                    String passwdkh = rs.getString("Passwordkh");
                    khachhang = new KhachHang(id, ten, sdt, diachi, emailkh, passwdkh);
                }
                return khachhang != null ? true : false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(dangnhapFrame, "Lỗi khi kiểm tra tài khoản: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

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

                JOptionPane.showMessageDialog(dangkyFrame, "Đăng ký thành công!");
                header.accountLabel.setText(hoTen);
                dangkyFrame.dispose();
            }
        });
    }

    private void addDangNhapEvent(){
        dangnhapFrame.btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = dangnhapFrame.txtEmail.getText().trim();
                String matKhau = new String(dangnhapFrame.txtMatKhau.getPassword()).trim();

                if (CheckAccount(email, matKhau)) {
                    JOptionPane.showMessageDialog(dangnhapFrame, "Đăng nhập thành công!");
                    header.accountLabel.setText(khachhang.getTen_KhachHang());
                    dangnhapFrame.dispose();
                    
                } else {
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


    public void initComponents(){
        JPanel spacer = new JPanel();
        header = new HeaderPanel();
        header.accountIcon.addMouseListener(mouseListener);

        ContentPanel = new JPanel();
        catalogPanel = new CatalogPanel();
        productPanel = new ProductPanel();
        spacer.setPreferredSize(new Dimension(20, 1));
        spacer.setOpaque(false);
        // spacer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        // productPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#cfdef3"), 35, false));

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
