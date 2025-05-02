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

import qlkho.dao.NhanVienDAO;
import qlkho.oop.NhanVien;

public class addframenv extends JFrame implements ActionListener {
    JLabel lb_manv, lb_tennv, lb_cv, lb_email, lb_sdt, lb_password, lb_nql;
    JTextField tf_manv, tf_tennv, tf_email, tf_sdt, tf_password, tf_nql;
    JComboBox<String> cb_cv, cb_nql;
    JButton btn_xacnhan, btn_huy;

    public addframenv() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(51, 51, 51));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        lb_manv = new JLabel("Mã NV:");
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
        tf_manv.setText(NhanVienDAO.getInstance().autoUpdateMaNV());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_manv, gbc);

        lb_tennv = new JLabel("Tên NV:");
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
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_tennv, gbc);

        lb_cv = new JLabel("Chức vụ:");
        lb_cv.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_cv, gbc);
        String[] cv = { "Nhân viên bán hàng", "Quản lý", "Nhân viên quản lí kho", "Admin" };
        cb_cv = new JComboBox<>(cv);
        cb_cv.setBackground(Color.BLACK);
        cb_cv.setForeground(Color.white);
        cb_cv.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        cb_cv.setSelectedItem("Nhân viên");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(cb_cv, gbc);

        lb_email = new JLabel("Email:");
        lb_email.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_email, gbc);
        tf_email = new JTextField(30);
        tf_email.setBackground(Color.BLACK);
        tf_email.setForeground(Color.white);
        tf_email.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_email, gbc);

        lb_sdt = new JLabel("SDT:");
        lb_sdt.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_sdt, gbc);
        tf_sdt = new JTextField(30);
        tf_sdt.setBackground(Color.BLACK);
        tf_sdt.setForeground(Color.white);
        tf_sdt.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_sdt, gbc);

        lb_password = new JLabel("Password:");
        lb_password.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_password, gbc);
        tf_password = new JTextField(30);
        tf_password.setBackground(Color.BLACK);
        tf_password.setForeground(Color.white);
        tf_password.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_password, gbc);

        lb_nql = new JLabel("Người quản lí:");
        lb_nql.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_nql, gbc);
        cb_nql = new JComboBox<>();
        cb_nql.addItem("Không");
        ArrayList<NhanVien> listnv = NhanVienDAO.getInstance().selectAll();
        for (NhanVien nv : listnv) {
            if (nv.getChucVu().equals("Quản lý")) {
                cb_nql.addItem(nv.getTenNV());
            }
        }
        cb_nql.setSelectedIndex(0);
        cb_nql.setBackground(Color.BLACK);
        cb_nql.setForeground(Color.white);
        cb_nql.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(cb_nql, gbc);

        JPanel panel_btn = new JPanel();
        panel_btn.setLayout(new GridLayout(1, 2));
        gbc.gridx = 0;
        gbc.gridy = 7;
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
            if (tf_manv.getText().isEmpty() || tf_tennv.getText().isEmpty() || tf_email.getText().isEmpty()
                    || tf_sdt.getText().isEmpty() || tf_password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            String manv = tf_manv.getText();
            String tennv = tf_tennv.getText();
            String cv = (String) cb_cv.getSelectedItem();
            String email = tf_email.getText();
            String sdt = tf_sdt.getText();
            String password = tf_password.getText();
            String nql = (String) cb_nql.getSelectedItem();
            String manql = null;
            ArrayList<NhanVien> listnv = NhanVienDAO.getInstance().selectAll();
            for (NhanVien nv : listnv) {
                if (nv.getTenNV().equals(nql)) {
                    manql = nv.getMaNV();
                }
            }
            NhanVien nv = new NhanVien(manv, tennv, cv, email, password, manql, sdt);
            NhanVienDAO.getInstance().insert(nv);
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            dispose();

        } else if (e.getSource() == btn_huy) {
            dispose();
        }

    }
}
