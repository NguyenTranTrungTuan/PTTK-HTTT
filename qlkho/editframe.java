package qlkho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import qlkho.dao.DienThoaiDAO;
import qlkho.oop.DienThoai;

public class editframe extends JFrame implements ActionListener {
    JButton btn_xacnhan, btn_huy;
    JTextField tf_madtshowsp, tf_tendtshowsp, tf_giabanshowsp, tf_gianhapshowsp, tf_matonshowsp, tf_xuatxushowsp,
            tf_trongluongshowsp, tf_kichthuocmanhinhshowsp, tf_dungluongdtshowsp, tf_ramshowsp, tf_baohanhshowsp,
            tf_manccshowsp;

    public editframe(DienThoai dt) {
        setSize(800, 600);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel_top = new JPanel();
        panel_top.setLayout(new GridLayout(4, 6));
        panel_top.setBackground(new Color(51, 51, 51));
        JLabel lb_madtshowsp = new JLabel();
        lb_madtshowsp.setText("Mã điện thoại: ");
        lb_madtshowsp.setForeground(Color.white);
        tf_madtshowsp = new JTextField(dt.getMaDt());
        tf_madtshowsp.setBackground(Color.BLACK);
        tf_madtshowsp.setForeground(Color.white);
        tf_madtshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_madtshowsp);
        panel_top.add(tf_madtshowsp);
        JLabel lb_tendtshowsp = new JLabel();
        lb_tendtshowsp.setText("Tên điện thoại: ");
        lb_tendtshowsp.setForeground(Color.white);
        tf_tendtshowsp = new JTextField(dt.getTenDt());
        tf_tendtshowsp.setBackground(Color.BLACK);
        tf_tendtshowsp.setForeground(Color.white);
        tf_tendtshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_tendtshowsp);
        panel_top.add(tf_tendtshowsp);
        JLabel lb_giabanshowsp = new JLabel();
        lb_giabanshowsp.setText("Giá bán: ");
        lb_giabanshowsp.setForeground(Color.white);
        tf_giabanshowsp = new JTextField(String.valueOf(dt.getGiaBan()));
        tf_giabanshowsp.setBackground(Color.BLACK);
        tf_giabanshowsp.setForeground(Color.white);
        tf_giabanshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_giabanshowsp);
        panel_top.add(tf_giabanshowsp);
        JLabel lb_gianhapshowsp = new JLabel();
        lb_gianhapshowsp.setText("Giá nhập: ");
        lb_gianhapshowsp.setForeground(Color.white);
        tf_gianhapshowsp = new JTextField(String.valueOf(dt.getGiaNhap()));
        tf_gianhapshowsp.setBackground(Color.BLACK);
        tf_gianhapshowsp.setForeground(Color.white);
        tf_gianhapshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_gianhapshowsp);
        panel_top.add(tf_gianhapshowsp);
        JLabel lb_matonshowsp = new JLabel();
        lb_matonshowsp.setText("Mã tồn: ");
        lb_matonshowsp.setForeground(Color.white);
        tf_matonshowsp = new JTextField(dt.getMaTon());
        tf_matonshowsp.setBackground(Color.BLACK);
        tf_matonshowsp.setForeground(Color.white);
        tf_matonshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_matonshowsp);
        panel_top.add(tf_matonshowsp);
        JLabel lb_xuatxushowsp = new JLabel();
        lb_xuatxushowsp.setText("Xuất xứ: ");
        lb_xuatxushowsp.setForeground(Color.white);
        tf_xuatxushowsp = new JTextField(dt.getXuatXu());
        tf_xuatxushowsp.setBackground(Color.BLACK);
        tf_xuatxushowsp.setForeground(Color.white);
        tf_xuatxushowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_xuatxushowsp);
        panel_top.add(tf_xuatxushowsp);
        JLabel lb_trongluongshowsp = new JLabel();
        lb_trongluongshowsp.setText("Trọng lượng: ");
        lb_trongluongshowsp.setForeground(Color.white);
        tf_trongluongshowsp = new JTextField(String.valueOf(dt.getTrongLuong()));
        tf_trongluongshowsp.setBackground(Color.BLACK);
        tf_trongluongshowsp.setForeground(Color.white);
        tf_trongluongshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_trongluongshowsp);
        panel_top.add(tf_trongluongshowsp);
        JLabel lb_kichthuocmanhinhshowsp = new JLabel();
        lb_kichthuocmanhinhshowsp.setText("<html>Kích thước màn hình: </html>");
        lb_kichthuocmanhinhshowsp.setForeground(Color.white);
        tf_kichthuocmanhinhshowsp = new JTextField(String.valueOf(dt.getKichThuocManHinh()));
        tf_kichthuocmanhinhshowsp.setBackground(Color.BLACK);
        tf_kichthuocmanhinhshowsp.setForeground(Color.white);
        tf_kichthuocmanhinhshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_kichthuocmanhinhshowsp);
        panel_top.add(tf_kichthuocmanhinhshowsp);
        JLabel lb_dungluongdtshowsp = new JLabel();
        lb_dungluongdtshowsp.setText("Dung lượng dt: ");
        lb_dungluongdtshowsp.setForeground(Color.white);
        tf_dungluongdtshowsp = new JTextField(String.valueOf(dt.getDungLuong()));
        tf_dungluongdtshowsp.setBackground(Color.BLACK);
        tf_dungluongdtshowsp.setForeground(Color.white);
        tf_dungluongdtshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_dungluongdtshowsp);
        panel_top.add(tf_dungluongdtshowsp);
        JLabel lb_ramshowsp = new JLabel();
        lb_ramshowsp.setText("Ram: ");
        lb_ramshowsp.setForeground(Color.white);
        tf_ramshowsp = new JTextField(String.valueOf(dt.getRam()));
        tf_ramshowsp.setBackground(Color.BLACK);
        tf_ramshowsp.setForeground(Color.white);
        tf_ramshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_ramshowsp);
        panel_top.add(tf_ramshowsp);
        JLabel lb_baohanhshowsp = new JLabel();
        lb_baohanhshowsp.setText("Bảo hành: ");
        lb_baohanhshowsp.setForeground(Color.white);
        tf_baohanhshowsp = new JTextField(String.valueOf(dt.getBaoHanh()));
        tf_baohanhshowsp.setBackground(Color.BLACK);
        tf_baohanhshowsp.setForeground(Color.white);
        tf_baohanhshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_baohanhshowsp);
        panel_top.add(tf_baohanhshowsp);
        JLabel lb_manccshowsp = new JLabel();
        lb_manccshowsp.setText("Mã NCC: ");
        lb_manccshowsp.setForeground(Color.white);
        tf_manccshowsp = new JTextField(dt.getMaNcc());
        tf_manccshowsp.setBackground(Color.BLACK);
        tf_manccshowsp.setForeground(Color.white);
        tf_manccshowsp.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        panel_top.add(lb_manccshowsp);
        panel_top.add(tf_manccshowsp);
        JPanel panel_bottom = new JPanel();
        panel_bottom.setPreferredSize(new Dimension(800, 30));
        panel_bottom.setLayout(new GridLayout(1, 2));
        btn_xacnhan = new JButton("Xác nhận");
        btn_xacnhan.setFocusPainted(false);
        btn_xacnhan.addActionListener(this);
        panel_bottom.add(btn_xacnhan);
        btn_huy = new JButton("Hủy");
        btn_huy.addActionListener(this);
        btn_huy.setFocusPainted(false);
        panel_bottom.add(btn_huy);
        add(panel_top, BorderLayout.CENTER);
        add(panel_bottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_xacnhan) {
            String madt = tf_madtshowsp.getText();
            String tendt = tf_tendtshowsp.getText();
            int giaban = Integer.parseInt(tf_giabanshowsp.getText());
            int gianhap = Integer.parseInt(tf_gianhapshowsp.getText());
            String maton = tf_matonshowsp.getText();
            String xuatxu = tf_xuatxushowsp.getText();
            float trongluong = Float.parseFloat(tf_trongluongshowsp.getText());
            float kichthuocmanhinh = Float.parseFloat(tf_kichthuocmanhinhshowsp.getText());
            int dungluong = Integer.parseInt(tf_dungluongdtshowsp.getText());
            int ram = Integer.parseInt(tf_ramshowsp.getText());
            int baohanh = Integer.parseInt(tf_baohanhshowsp.getText());
            String ncc = tf_manccshowsp.getText();
            DienThoai dt = new DienThoai(madt, tendt, giaban, gianhap, maton, xuatxu, trongluong, kichthuocmanhinh,
                    dungluong, ram, baohanh, ncc);
            DienThoaiDAO.getInstance().update(dt);
            this.dispose();
        } else if (e.getSource() == btn_huy) {
            this.dispose();
        }
    }
}
