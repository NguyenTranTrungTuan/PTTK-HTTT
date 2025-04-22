package NhanVienBanHang.BUS;

import NhanVienBanHang.DAO.DonHang_DAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhanVien_BUS {
    private DonHang_DAO donHangDAO;

    public NhanVien_BUS() {
        donHangDAO = DonHang_DAO.getInstance();
    }

    public DefaultTableModel loadTableData() {
        return donHangDAO.loadDataToTable("DonHang");
    }

    public String changeStatus(String currentStatus) {
        String[] options = {"Chưa Xử Lý", "Đã Xử Lý", "Đã Hủy"};
        return (String) JOptionPane.showInputDialog(
                null,
                "Chọn trạng thái mới:",
                "Thay Đổi Tình Trạng",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                currentStatus
        );
    }

    public boolean updateTinhTrang(String maDon, String tinhTrang) throws Exception {
        return donHangDAO.updateTinhTrang(maDon, tinhTrang);
    }
}