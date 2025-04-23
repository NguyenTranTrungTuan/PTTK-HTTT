package qlkho;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import qlkho.dao.NhaCungCapDAO;
import qlkho.oop.NhaCungCap;

public class editframencc extends JFrame implements ActionListener {
    JTextField tf_mancc, tf_tenncc, tf_quocgia;
    JButton btn_xacnhan, btn_huy;

    public editframencc(NhaCungCap ncc) {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(51, 51, 51));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel lb_mancc = new JLabel("Mã NCC:");
        lb_mancc.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_mancc, gbc);
        tf_mancc = new JTextField(30);
        tf_mancc.setBackground(Color.BLACK);
        tf_mancc.setForeground(Color.white);
        tf_mancc.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_mancc.setText(ncc.getMaNCC());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_mancc, gbc);

        JLabel lb_tenncc = new JLabel("Tên NCC:");
        lb_tenncc.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_tenncc, gbc);
        tf_tenncc = new JTextField(30);
        tf_tenncc.setBackground(Color.BLACK);
        tf_tenncc.setForeground(Color.white);
        tf_tenncc.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_tenncc.setText(ncc.getTenNCC());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_tenncc, gbc);

        JLabel lb_quocgia = new JLabel("Quốc Gia:");
        lb_quocgia.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(lb_quocgia, gbc);
        tf_quocgia = new JTextField(30);
        tf_quocgia.setBackground(Color.BLACK);
        tf_quocgia.setForeground(Color.white);
        tf_quocgia.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        tf_quocgia.setText(ncc.getQuocGia());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tf_quocgia, gbc);

        JPanel panel_btn = new JPanel();
        panel_btn.setLayout(new GridLayout(1, 2));
        gbc.gridx = 0;
        gbc.gridy = 3;
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
            String mancc = tf_mancc.getText();
            String tenncc = tf_tenncc.getText();
            String quocgia = tf_quocgia.getText();
            if (mancc.isEmpty() || tenncc.isEmpty() || quocgia.isEmpty()) {
                System.out.println("Vui long nhap day du thong tin!");
                return;
            }
            NhaCungCap ncc = new NhaCungCap(mancc, tenncc, quocgia);
            NhaCungCapDAO.getInstance().update(ncc);
            this.dispose();
        } else if (e.getSource() == btn_huy) {
            this.dispose();
        }
    }
}
