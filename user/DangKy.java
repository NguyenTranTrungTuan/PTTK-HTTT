package user;

import java.awt.*;
import javax.swing.*;

public class DangKy extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel lblTitle = new JLabel("ĐĂNG KÝ", JLabel.CENTER);
    protected JTextField txtHoTen = new JTextField();
    protected JTextField txtSDT = new JTextField();
    protected JTextField txtEmail = new JTextField();
    protected JPasswordField txtMatKhau = new JPasswordField();
    protected JComboBox<String> cbDiaChi;
    protected MyButton btnDangKy = new MyButton("ĐĂNG KÝ");

    private String[] diaChiList = {
        "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu",
        "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước",
        "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng",
        "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp",
        "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh",
        "Hải Dương", "Hải Phòng", "Hậu Giang", "TP.HCM", "Hòa Bình",
        "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu",
        "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định",
        "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên",
        "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị",
        "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên",
        "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Trà Vinh", "Tuyên Quang",
        "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
    };

    public DangKy() {
        initComponents();
    }

    private void initComponents() {
        setTitle("ĐĂNG KÝ");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtHoTen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtHoTen.setBorder(BorderFactory.createTitledBorder("Họ và tên"));
        panel.add(txtHoTen);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtSDT.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtSDT.setBorder(BorderFactory.createTitledBorder("Số điện thoại"));
        panel.add(txtSDT);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        panel.add(txtEmail);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtMatKhau.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtMatKhau.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        panel.add(txtMatKhau);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        cbDiaChi = new JComboBox<>(diaChiList);
        cbDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cbDiaChi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cbDiaChi.setBorder(BorderFactory.createTitledBorder("Địa chỉ"));
        cbDiaChi.setBackground(Color.WHITE);
        panel.add(cbDiaChi);
        panel.add(Box.createRigidArea(new Dimension(0, 60)));

        btnDangKy.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDangKy.setBackground(Color.decode("#00B4DB"));
        btnDangKy.setForeground(Color.WHITE);
        btnDangKy.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnDangKy);

        setContentPane(panel);
        setVisible(true);
    }
}

