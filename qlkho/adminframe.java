package qlkho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import qlkho.qlkhoframe.CustomScrollBarUI;
import qlkho.dao.DienThoaiDAO;
import qlkho.dao.KhachHangDAO;
import qlkho.dao.NhanVienDAO;
import qlkho.oop.DienThoai;
import qlkho.oop.KhachHang;
import qlkho.oop.NhanVien;

public class adminframe extends JFrame implements ActionListener {
        JButton btn_refresh, btn_refresh1, btn_add, btn_add1, btn_edit, btn_edit1, btn_delete, btn_delete1;
        JTabbedPane tab;
        JPanel panel_taikhoannv, panel_taikhoankh, paneltknv_top, paneltkkh_top, paneltknv_top_btn, paneltkkh_top_btn;
        JScrollPane scrollPane_table, scrollPane_table1;
        JTable table, table1;
        DefaultTableModel tableModel, tableModel1;

        public adminframe() {
                setSize(1280, 720);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setTitle("Nhân viên");
                getContentPane().setBackground(Color.WHITE);
                setLayout(new BorderLayout());

                tab = new JTabbedPane();
                tab.setForeground(Color.BLACK);
                tab.setFont(new Font(null, Font.BOLD, 20));
                tab.setBackground(Color.white);

                panel_taikhoannv = new JPanel();
                panel_taikhoannv.setBackground(Color.red);
                panel_taikhoannv.setLayout(new BorderLayout());
                JScrollPane taikhoannvscroll = new JScrollPane();
                taikhoannvscroll.setViewportView(panel_taikhoannv);
                ImageIcon img_nv = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\employer.png");
                img_nv = ImageResizer.resizeImageIcon(img_nv, 30, 30);
                tab.addTab("Tài khoản nhân viên", img_nv, taikhoannvscroll);
                paneltknv_top = new JPanel();
                paneltknv_top.setBackground(Color.BLACK);
                paneltknv_top.setPreferredSize(new Dimension(0, 60));
                paneltknv_top.setLayout(new BorderLayout());
                paneltknv_top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Tạo khoảng cách 10px
                panel_taikhoannv.add(paneltknv_top, BorderLayout.NORTH);
                JLabel lb_search = new JLabel();
                ImageIcon icon_search = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\search.png");
                ImageIcon resizedIcon_search = ImageResizer.resizeImageIcon(icon_search, 30, 30);
                lb_search.setIcon(resizedIcon_search);
                lb_search.setBackground(new Color(51, 51, 51));
                lb_search.setOpaque(true);
                paneltknv_top.add(lb_search, BorderLayout.WEST);

                JTextField tf_search = new JTextField("Tìm kiếm");
                // tf_search.getDocument().addDocumentListener(this);
                tf_search.setBounds(40, 10, 100, 40);
                tf_search.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                tf_search.setBackground(new Color(51, 51, 51));
                tf_search.setForeground(Color.white);
                paneltknv_top.add(tf_search, BorderLayout.CENTER);

                paneltknv_top_btn = new JPanel();
                paneltknv_top_btn.setBackground(Color.RED);
                paneltknv_top_btn.setLayout(new GridLayout(1, 4));
                paneltknv_top.add(paneltknv_top_btn, BorderLayout.EAST);

                btn_refresh = new JButton("<html>Làm mới</html>");
                btn_refresh.addActionListener(this);
                ImageIcon icon_refresh = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\refresh.png");
                icon_refresh = ImageResizer.resizeImageIcon(icon_refresh, 30, 30);
                btn_refresh.setIcon(icon_refresh);
                btn_refresh.setFocusPainted(false);
                btn_refresh.setPreferredSize(new Dimension(80, 40));
                btn_refresh.setBackground(new Color(51, 51, 51));
                btn_refresh.setForeground(Color.white);
                btn_refresh.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltknv_top_btn.add(btn_refresh);

                btn_add = new JButton("Thêm");
                btn_add.addActionListener(this);
                ImageIcon icon_add = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\add.png");
                icon_add = ImageResizer.resizeImageIcon(icon_add, 30, 30);
                btn_add.setIcon(icon_add);
                btn_add.setFocusPainted(false);
                btn_add.setPreferredSize(new Dimension(80, 40));
                btn_add.setBackground(new Color(51, 51, 51));
                btn_add.setForeground(Color.white);
                btn_add.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltknv_top_btn.add(btn_add);

                btn_edit = new JButton("Sửa");
                btn_edit.addActionListener(this);
                ImageIcon icon_edit = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\edit.png");
                icon_edit = ImageResizer.resizeImageIcon(icon_edit, 30, 30);
                btn_edit.setIcon(icon_edit);
                btn_edit.setFocusPainted(false);
                btn_edit.setPreferredSize(new Dimension(80, 40));
                btn_edit.setBackground(new Color(51, 51, 51));
                btn_edit.setForeground(Color.white);
                btn_edit.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltknv_top_btn.add(btn_edit);

                btn_delete = new JButton("Xóa");
                btn_delete.addActionListener(this);
                ImageIcon icon_delete = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\delete.png");
                icon_delete = ImageResizer.resizeImageIcon(icon_delete, 30, 30);
                btn_delete.setIcon(icon_delete);
                btn_delete.setFocusPainted(false);
                btn_delete.setPreferredSize(new Dimension(80, 40));
                btn_delete.setBackground(new Color(51, 51, 51));
                btn_delete.setForeground(Color.white);
                btn_delete.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltknv_top_btn.add(btn_delete);
                tableModel = new DefaultTableModel();
                table = new JTable(tableModel);
                // table.addMouseListener(this);
                table.setBackground(new Color(51, 51, 51));
                table.setForeground(Color.white);
                table.setGridColor(Color.BLACK);
                table.setRowHeight(30);
                table.getTableHeader().setBackground(new Color(51, 51, 51));
                table.getTableHeader().setForeground(Color.white);

                scrollPane_table = new JScrollPane();
                scrollPane_table.setViewportView(table);
                scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
                scrollPane_table.setBorder(null);
                scrollPane_table.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane_table.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));// Chiều rộng 12px
                scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                panel_taikhoannv.add(scrollPane_table, BorderLayout.CENTER);

                tableModel = NhanVienDAO.getInstance().loadDataToTable("NhanVien");
                table.setModel(tableModel);

                panel_taikhoankh = new JPanel();
                panel_taikhoankh.setBackground(Color.blue);
                panel_taikhoankh.setLayout(new BorderLayout());
                JScrollPane taikhoankhscroll = new JScrollPane();
                taikhoankhscroll.setViewportView(panel_taikhoankh);
                ImageIcon img_kh = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\customer.png");
                img_kh = ImageResizer.resizeImageIcon(img_kh, 30, 30);
                tab.addTab("Tài khoản khách hàng", img_kh, taikhoankhscroll);
                paneltkkh_top = new JPanel();
                paneltkkh_top.setBackground(Color.BLACK);
                paneltkkh_top.setPreferredSize(new Dimension(0, 60));
                paneltkkh_top.setLayout(new BorderLayout());
                paneltkkh_top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Tạo khoảng cách 10px
                panel_taikhoankh.add(paneltkkh_top, BorderLayout.NORTH);
                JLabel lb_search1 = new JLabel();
                ImageIcon icon_search1 = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\search.png");
                ImageIcon resizedIcon_search1 = ImageResizer.resizeImageIcon(icon_search1, 30, 30);
                lb_search1.setIcon(resizedIcon_search1);
                lb_search1.setBackground(new Color(51, 51, 51));
                lb_search1.setOpaque(true);
                paneltkkh_top.add(lb_search1, BorderLayout.WEST);

                JTextField tf_search1 = new JTextField("Tìm kiếm");
                // tf_search1.getDocument().addDocumentListener(this);
                tf_search1.setBounds(40, 10, 100, 40);
                tf_search1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                tf_search1.setBackground(new Color(51, 51, 51));
                tf_search1.setForeground(Color.white);
                paneltkkh_top.add(tf_search1, BorderLayout.CENTER);

                paneltkkh_top_btn = new JPanel();
                paneltkkh_top_btn.setBackground(Color.RED);
                paneltkkh_top_btn.setLayout(new GridLayout(1, 4));
                paneltkkh_top.add(paneltkkh_top_btn, BorderLayout.EAST);

                btn_refresh1 = new JButton("<html>Làm mới</html>");
                btn_refresh1.addActionListener(this);
                ImageIcon icon_refresh1 = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\refresh.png");
                icon_refresh1 = ImageResizer.resizeImageIcon(icon_refresh1, 30, 30);
                btn_refresh1.setIcon(icon_refresh1);
                btn_refresh1.setFocusPainted(false);
                btn_refresh1.setPreferredSize(new Dimension(80, 40));
                btn_refresh1.setBackground(new Color(51, 51, 51));
                btn_refresh1.setForeground(Color.white);
                btn_refresh1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltkkh_top_btn.add(btn_refresh1);

                btn_add1 = new JButton("Thêm");
                btn_add1.addActionListener(this);
                ImageIcon icon_add1 = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\add.png");
                icon_add1 = ImageResizer.resizeImageIcon(icon_add1, 30, 30);
                btn_add1.setIcon(icon_add1);
                btn_add1.setFocusPainted(false);
                btn_add1.setPreferredSize(new Dimension(80, 40));
                btn_add1.setBackground(new Color(51, 51, 51));
                btn_add1.setForeground(Color.white);
                btn_add1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltkkh_top_btn.add(btn_add1);

                btn_edit1 = new JButton("Sửa");
                btn_edit1.addActionListener(this);
                ImageIcon icon_edit1 = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\edit.png");
                icon_edit1 = ImageResizer.resizeImageIcon(icon_edit1, 30, 30);
                btn_edit1.setIcon(icon_edit1);
                btn_edit1.setFocusPainted(false);
                btn_edit1.setPreferredSize(new Dimension(80, 40));
                btn_edit1.setBackground(new Color(51, 51, 51));
                btn_edit1.setForeground(Color.white);
                btn_edit1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltkkh_top_btn.add(btn_edit1);

                btn_delete1 = new JButton("Xóa");
                btn_delete1.addActionListener(this);
                ImageIcon icon_delete1 = new ImageIcon(
                                "C:\\Users\\tuanv\\Documents\\DO_AN_PTTK\\PTTK-HTTT\\qlkho\\icon\\delete.png");
                icon_delete1 = ImageResizer.resizeImageIcon(icon_delete1, 30, 30);
                btn_delete1.setIcon(icon_delete1);
                btn_delete1.setFocusPainted(false);
                btn_delete1.setPreferredSize(new Dimension(80, 40));
                btn_delete1.setBackground(new Color(51, 51, 51));
                btn_delete1.setForeground(Color.white);
                btn_delete1.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
                paneltkkh_top_btn.add(btn_delete1);
                tableModel1 = new DefaultTableModel();
                table1 = new JTable(tableModel1);
                // table.addMouseListener(this);
                table1.setBackground(new Color(51, 51, 51));
                table1.setForeground(Color.white);
                table1.setGridColor(Color.BLACK);
                table1.setRowHeight(30);
                table1.getTableHeader().setBackground(new Color(51, 51, 51));
                table1.getTableHeader().setForeground(Color.white);

                scrollPane_table1 = new JScrollPane();
                scrollPane_table1.setViewportView(table1);
                scrollPane_table1.getViewport().setBackground(new Color(51, 51, 51));
                scrollPane_table1.setBorder(null);
                scrollPane_table1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane_table1.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));// Chiều rộng 12px
                scrollPane_table1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                panel_taikhoankh.add(scrollPane_table1, BorderLayout.CENTER);

                tableModel1 = KhachHangDAO.getInstance().loadDataToTable("KhachHang");
                table1.setModel(tableModel1);

                add(tab, BorderLayout.CENTER);
                setVisible(true);
        }

        public static void main(String[] args) {
                new adminframe();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn_refresh) {
                        panel_taikhoannv.removeAll();
                        tableModel = NhanVienDAO.getInstance().loadDataToTable("NhanVien");
                        table.setModel(tableModel);
                        panel_taikhoannv.add(paneltknv_top, BorderLayout.NORTH);
                        panel_taikhoannv.add(scrollPane_table, BorderLayout.CENTER);
                        panel_taikhoannv.revalidate();
                        panel_taikhoannv.repaint();
                } else if (e.getSource() == btn_add) {
                        new addframenv();
                } else if (e.getSource() == btn_edit) {
                        int row = table.getSelectedRow();
                        if (row == -1) {
                                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa!");
                                return;
                        }
                        String manv = (String) tableModel.getValueAt(row, 0);
                        NhanVien nv = NhanVienDAO.getInstance().selectById1(manv);
                        new editframenv(nv);
                } else if (e.getSource() == btn_delete) {
                        int[] selectRow = table.getSelectedRows();
                        ArrayList<NhanVien> dsxoa = new ArrayList<>();
                        if (selectRow.length > 0) {
                                for (int row : selectRow) {
                                        String ma_nv = String.valueOf(table.getValueAt(row, 0));
                                        String ten_nv = String.valueOf(table.getValueAt(row, 1));
                                        String cv = String.valueOf(table.getValueAt(row, 2));
                                        String email = String.valueOf(table.getValueAt(row, 3));
                                        String sdt = String.valueOf(table.getValueAt(row, 4));
                                        String pass = String.valueOf(table.getValueAt(row, 5));
                                        String manql = String.valueOf(table.getValueAt(row, 6));
                                        NhanVien nv = new NhanVien(ma_nv, ten_nv, cv, email, pass, manql, sdt);
                                        dsxoa.add(nv);
                                }
                                StringBuilder dsxoaStr = new StringBuilder();
                                for (NhanVien dt : dsxoa) {
                                        dsxoaStr.append("Mã: ").append(dt.getMaNV())
                                                        .append(", Tên: ").append(dt.getTenNV())
                                                        .append("\n");
                                }
                                int confirm = JOptionPane.showConfirmDialog(
                                                null,
                                                "Bạn có chắc chắn muốn xóa các nhân viên sau?\n" + dsxoaStr,
                                                "Xóa nhân viên",
                                                JOptionPane.YES_NO_OPTION,
                                                JOptionPane.WARNING_MESSAGE);

                                if (confirm == JOptionPane.YES_OPTION) {
                                        for (NhanVien dt : dsxoa) {
                                                NhanVienDAO.getInstance().delete(dt);
                                        }
                                        tableModel = NhanVienDAO.getInstance().loadDataToTable("NhanVien");
                                        table.setModel(tableModel);
                                }
                        } else {
                                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên để xóa!",
                                                "Xóa nhân viên",
                                                JOptionPane.INFORMATION_MESSAGE);
                        }
                } else if (e.getSource() == btn_refresh1) {
                        panel_taikhoankh.removeAll();
                        tableModel1 = KhachHangDAO.getInstance().loadDataToTable("KhachHang");
                        table1.setModel(tableModel1);
                        panel_taikhoankh.add(paneltkkh_top, BorderLayout.NORTH);
                        panel_taikhoankh.add(scrollPane_table1, BorderLayout.CENTER);
                        panel_taikhoankh.revalidate();
                        panel_taikhoankh.repaint();
                } else if (e.getSource() == btn_add1) {
                        new addframekh();
                } else if (e.getSource() == btn_edit1) {
                        int row = table1.getSelectedRow();
                        if (row == -1) {
                                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để sửa!");
                                return;
                        }
                        String makh = (String) tableModel1.getValueAt(row, 0);
                        KhachHang kh = KhachHangDAO.getInstance().selectById1(makh);
                        new editframekh(kh);
                } else if (e.getSource() == btn_delete1) {
                        int[] selectRow = table1.getSelectedRows();
                        ArrayList<KhachHang> dsxoa = new ArrayList<>();
                        if (selectRow.length > 0) {
                                for (int row : selectRow) {
                                        String ma_kh = String.valueOf(table1.getValueAt(row, 0));
                                        String ten_kh = String.valueOf(table1.getValueAt(row, 1));
                                        String sdt = String.valueOf(table1.getValueAt(row, 2));
                                        String email = String.valueOf(table1.getValueAt(row, 3));
                                        String diachi = String.valueOf(table1.getValueAt(row, 4));
                                        String pass = String.valueOf(table1.getValueAt(row, 5));
                                        KhachHang kh = new KhachHang(ma_kh, ten_kh, sdt, email, diachi, pass);
                                        dsxoa.add(kh);
                                }
                                StringBuilder dsxoaStr = new StringBuilder();
                                for (KhachHang dt : dsxoa) {
                                        dsxoaStr.append("Mã: ").append(dt.getMaKH())
                                                        .append(", Tên: ").append(dt.getTenKH())
                                                        .append("\n");
                                }
                                int confirm = JOptionPane.showConfirmDialog(
                                                null,
                                                "Bạn có chắc chắn muốn xóa các khách hàng sau?\n" + dsxoaStr,
                                                "Xóa khách hàng",
                                                JOptionPane.YES_NO_OPTION,
                                                JOptionPane.WARNING_MESSAGE);

                                if (confirm == JOptionPane.YES_OPTION) {
                                        for (KhachHang dt : dsxoa) {
                                                KhachHangDAO.getInstance().delete(dt);
                                        }
                                        tableModel1 = KhachHangDAO.getInstance().loadDataToTable("KhachHang");
                                        table1.setModel(tableModel1);
                                }
                        } else {
                                JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để xóa!",
                                                "Xóa khách hàng",
                                                JOptionPane.INFORMATION_MESSAGE);
                        }
                }

        }

}
