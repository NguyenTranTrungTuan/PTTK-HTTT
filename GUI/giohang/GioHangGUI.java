package GUI.giohang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import oop.DienThoai;
import dao.DienThoaiDAO;
import dao.DAOInterface;
import java.awt.event.MouseListener;


public class GioHangGUI extends JPanel{

    private JTextField txtMaDt, txtTenDt, txtGiaBan, txtGiaNhap, txtMaTon, txtXuatXu, txtTrongLuong,
            txtKichThuocManHinh, txtDungLuong, txtRam, txtBaoHanh, txtMaNcc;
    private JButton btnThem, btnXoa, btnCapNhat, btnTimKiem;
    private JTable tableDienThoai;
    private DefaultTableModel model;

    public GioHangGUI() {
        initComponents();
    }

    public void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblMaDt = new JLabel("Mã Điện Thoại:");
        lblMaDt.setBounds(20, 20, 120, 25);
        panel.add(lblMaDt);

        txtMaDt = new JTextField();
        txtMaDt.setBounds(150, 20, 150, 25);
        panel.add(txtMaDt);

        JLabel lblTenDt = new JLabel("Tên Điện Thoại:");
        lblTenDt.setBounds(20, 60, 120, 25);
        panel.add(lblTenDt);

        txtTenDt = new JTextField();
        txtTenDt.setBounds(150, 60, 150, 25);
        panel.add(txtTenDt);

        JLabel lblGiaBan = new JLabel("Giá Bán:");
        lblGiaBan.setBounds(20, 100, 120, 25);
        panel.add(lblGiaBan);

        txtGiaBan = new JTextField();
        txtGiaBan.setBounds(150, 100, 150, 25);
        panel.add(txtGiaBan);

        JLabel lblGiaNhap = new JLabel("Giá Nhập:");
        lblGiaNhap.setBounds(20, 140, 120, 25);
        panel.add(lblGiaNhap);

        txtGiaNhap = new JTextField();
        txtGiaNhap.setBounds(150, 140, 150, 25);
        panel.add(txtGiaNhap);

        JLabel lblMaTon = new JLabel("Mã Tồn:");
        lblMaTon.setBounds(20, 180, 120, 25);
        panel.add(lblMaTon);

        txtMaTon = new JTextField();
        txtMaTon.setBounds(150, 180, 150, 25);
        panel.add(txtMaTon);

        JLabel lblXuatXu = new JLabel("Xuất Xứ:");
        lblXuatXu.setBounds(20, 220, 120, 25);
        panel.add(lblXuatXu);

        txtXuatXu = new JTextField();
        txtXuatXu.setBounds(150, 220, 150, 25);
        panel.add(txtXuatXu);

        JLabel lblTrongLuong = new JLabel("Trọng Lượng:");
        lblTrongLuong.setBounds(20, 260, 120, 25);
        panel.add(lblTrongLuong);
    }
    
   public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tableDienThoai) {
            int row = tableDienThoai.getSelectedRow();
            if (row != -1) {
                txtMaDt.setText(model.getValueAt(row, 0).toString());
                txtTenDt.setText(model.getValueAt(row, 1).toString());
                txtGiaBan.setText(model.getValueAt(row, 2).toString());
                txtGiaNhap.setText(model.getValueAt(row, 3).toString());
                txtMaTon.setText(model.getValueAt(row, 4).toString());
                txtXuatXu.setText(model.getValueAt(row, 5).toString());
                txtTrongLuong.setText(model.getValueAt(row, 6).toString());
                txtKichThuocManHinh.setText(model.getValueAt(row, 7).toString());
                txtDungLuong.setText(model.getValueAt(row, 8).toString());
                txtRam.setText(model.getValueAt(row, 9).toString());
                txtBaoHanh.setText(model.getValueAt(row, 10).toString());
                txtMaNcc.setText(model.getValueAt(row, 11).toString());
            }
        }
    }

}