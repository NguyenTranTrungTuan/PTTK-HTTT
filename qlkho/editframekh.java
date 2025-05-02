package qlkho;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import qlkho.dao.KhachHangDAO;
import qlkho.dao.NhanVienDAO;
import qlkho.oop.KhachHang;
import qlkho.oop.NhanVien;

public class editframekh extends JFrame implements ActionListener {
    JLabel lb_manv, lb_tennv, lb_cv, lb_email, lb_sdt, lb_password, lb_sonha, lb_quan, lb_thanhpho, lb_phuong, lb_duong;
    JTextField tf_manv, tf_tennv, tf_email, tf_sdt, tf_password, tf_sonha, tf_quan, tf_thanhpho, tf_phuong, tf_duong;
    JButton btn_xacnhan, btn_huy;

    public editframekh(KhachHang kh) {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(51, 51, 51));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        lb_manv = new JLabel("Mã KH:");
        lb_manv.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_manv, gbc);
        tf_manv = new JTextField(30);
        tf_manv.setBackground(Color.BLACK);
        tf_manv.setForeground(Color.white);
        tf_manv.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_manv.setText(kh.getMaKH());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_manv, gbc);

        lb_tennv = new JLabel("Tên KH:");
        lb_tennv.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_tennv, gbc);
        tf_tennv = new JTextField(30);
        tf_tennv.setBackground(Color.BLACK);
        tf_tennv.setForeground(Color.white);
        tf_tennv.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_tennv.setText(kh.getTenKH());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_tennv, gbc);

        lb_email = new JLabel("Email:");
        lb_email.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_email, gbc);
        tf_email = new JTextField(30);
        tf_email.setBackground(Color.BLACK);
        tf_email.setForeground(Color.white);
        tf_email.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_email.setText(kh.getEmail());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_email, gbc);

        lb_sdt = new JLabel("SDT:");
        lb_sdt.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_sdt, gbc);
        tf_sdt = new JTextField(30);
        tf_sdt.setBackground(Color.BLACK);
        tf_sdt.setForeground(Color.white);
        tf_sdt.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_sdt.setText(kh.getSdt());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_sdt, gbc);

        lb_password = new JLabel("Password:");
        lb_password.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_password, gbc);
        tf_password = new JTextField(30);
        tf_password.setBackground(Color.BLACK);
        tf_password.setForeground(Color.white);
        tf_password.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_password.setText(kh.getPassword());
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_password, gbc);

        lb_sonha = new JLabel("Số nhà:");
        lb_sonha.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_sonha, gbc);
        tf_sonha = new JTextField(30);
        tf_sonha.setBackground(Color.BLACK);
        tf_sonha.setForeground(Color.white);
        tf_sonha.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_sonha, gbc);

        lb_duong = new JLabel("Tên đường:");
        lb_duong.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_duong, gbc);
        tf_duong = new JTextField(30);
        tf_duong.setBackground(Color.BLACK);
        tf_duong.setForeground(Color.white);
        tf_duong.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_duong, gbc);

        lb_phuong = new JLabel("Phường:");
        lb_phuong.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_phuong, gbc);
        tf_phuong = new JTextField(30);
        tf_phuong.setBackground(Color.BLACK);
        tf_phuong.setForeground(Color.white);
        tf_phuong.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_phuong, gbc);

        lb_quan = new JLabel("Quận:");
        lb_quan.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_quan, gbc);
        tf_quan = new JTextField(30);
        tf_quan.setBackground(Color.BLACK);
        tf_quan.setForeground(Color.white);
        tf_quan.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_quan, gbc);

        lb_thanhpho = new JLabel("Thành phố:");
        lb_thanhpho.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_thanhpho, gbc);
        tf_thanhpho = new JTextField(30);
        tf_thanhpho.setBackground(Color.BLACK);
        tf_thanhpho.setForeground(Color.white);
        tf_thanhpho.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_thanhpho, gbc);

        String diachi = kh.getDiaChi();
        String[] diachiParts = diachi.split(",");
        tf_sonha.setText(diachiParts[0]);
        tf_duong.setText(diachiParts[1].substring(6));
        tf_phuong.setText(diachiParts[2].substring(7));
        tf_quan.setText(diachiParts[3].substring(5));
        tf_thanhpho.setText(diachiParts[4].substring(9));

        JPanel panel_btn = new JPanel();
        panel_btn.setLayout(new GridLayout(1, 2));
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        btn_xacnhan = new JButton("Xác nhận");
        btn_xacnhan.addActionListener(this);
        panel_btn.add(btn_xacnhan);
        btn_huy = new JButton("Hủy");
        btn_huy.addActionListener(this);
        panel_btn.add(btn_huy);
        add(panel_btn, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_xacnhan) {
            if (tf_tennv.getText().isEmpty() || tf_email.getText().isEmpty()
                    || tf_sdt.getText().isEmpty() || tf_password.getText().isEmpty() || tf_sonha.getText().isEmpty()
                    || tf_duong.getText().isEmpty() || tf_phuong.getText().isEmpty() || tf_quan.getText().isEmpty()
                    || tf_thanhpho.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            String makh = tf_manv.getText();
            String tenkh = tf_tennv.getText();
            String sdt = tf_sdt.getText();
            String email = tf_email.getText();
            String diachi = tf_sonha.getText() + ",Đường " + tf_duong.getText() + ",Phường " + tf_phuong.getText()
                    + ",Quận " + tf_quan.getText() + ",Thành phố " + tf_thanhpho.getText();
            String password = tf_password.getText();

            KhachHang kh = new KhachHang(makh, tenkh, sdt, email, diachi, password);
            KhachHangDAO.getInstance().update(kh);
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
            dispose();

        } else if (e.getSource() == btn_huy) {
            dispose();
        }

    }
}
