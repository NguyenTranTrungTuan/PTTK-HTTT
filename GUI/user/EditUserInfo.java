package GUI.user;

import java.awt.*;
import javax.swing.*;

import DTO.KhachHang_DTO;

public class EditUserInfo extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel lblTitle = new JLabel("SỬA THÔNG TIN", JLabel.CENTER);
    protected JTextField txtHoTen = new JTextField();
    protected JTextField txtSDT = new JTextField();
    protected JTextField txtEmail = new JTextField();
    protected JComboBox<String> cbDiaChi;
    protected MyButton btnConfirm = new MyButton("XÁC NHẬN");

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

    public EditUserInfo(KhachHang_DTO kh) {
        initComponents(kh);
    }

    private void initComponents(KhachHang_DTO kh) {
        setTitle("SỬA THÔNG TIN");
        setSize(400, 420);
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
        txtHoTen.setText(kh.getTen_KhachHang());
        panel.add(txtHoTen);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtSDT.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtSDT.setBorder(BorderFactory.createTitledBorder("Số điện thoại"));
        txtSDT.setText(kh.getSdt_KhachHang());
        panel.add(txtSDT);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        txtEmail.setText(kh.getEmail_KhachHang());
        panel.add(txtEmail);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        cbDiaChi = new JComboBox<>(diaChiList);
        cbDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cbDiaChi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cbDiaChi.setBorder(BorderFactory.createTitledBorder("Địa chỉ"));
        cbDiaChi.setBackground(Color.WHITE);
        cbDiaChi.setSelectedItem(kh.getDiaChi_KhachHang());
        panel.add(cbDiaChi);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));

        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirm.setBackground(Color.decode("#00B4DB"));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnConfirm);

        setContentPane(panel);
        setVisible(true);
    }
}
