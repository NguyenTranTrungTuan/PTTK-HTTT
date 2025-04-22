package NhanVienBanHang.BUS;

import NhanVienBanHang.DAO.ChiTietDonHang_DAO;
import NhanVienBanHang.DAO.DonHang_DAO;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NhanVien_BUS {
    private DonHang_DAO donHangDAO;
    private ChiTietDonHang_DAO chiTietDonHangDAO;

    public NhanVien_BUS() {
        donHangDAO = DonHang_DAO.getInstance();
        chiTietDonHangDAO = ChiTietDonHang_DAO.getInstance();
    }

    public DefaultTableModel loadTableData() {
        return donHangDAO.loadDataToTable("DonHang");
    }

    // Tải dữ liệu đơn hàng theo khoảng thời gian
    public DefaultTableModel loadTableDataByDateRange(Date startDate, Date endDate) {
        return donHangDAO.loadDataToTableByDateRange("DonHang", startDate, endDate);
    }



    // Cập nhật trạng thái đơn hàng
    public boolean updateTinhTrang(String maDon, String tinhTrang) {
        try {
            return donHangDAO.updateTinhTrang(maDon, tinhTrang);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

        // Lấy chi tiết đơn hàng
        public DefaultTableModel loadChiTietDonHang(String maDon) {
            return chiTietDonHangDAO.loadDataToTableByMaDon(maDon);
        }

        // Thay đổi trạng thái đơn hàng
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

            // Kiểm tra và xử lý trạng thái "Chưa Xử Lý"
        public void handleUnprocessedOrders(DefaultTableModel tableModel) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String tinhTrang = tableModel.getValueAt(i, 6).toString();
                if ("Chưa Xử Lý".equalsIgnoreCase(tinhTrang)) {
                    tableModel.setValueAt(null, i, 2); // Đặt mã nhân viên là null
                }
            }
        }

}