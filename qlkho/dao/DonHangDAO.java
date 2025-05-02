package qlkho.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import qlkho.oop.DonHang;
import qlkho.oop.NhanVien;

public class DonHangDAO implements DAOInterface<DonHang> {

    public static DonHangDAO getInstance() {
        return new DonHangDAO();
    }

    @Override
    public void insert(DonHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(DonHang obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE DonHang SET MaKH = ?,MaNV = ?,DiaChiDat = ?,NgayDat = ?,PTTT = ?,TinhTrang = ?,TongTien = ? WHERE MaDon = ?");
            ps.setString(1, obj.getMaKH());
            ps.setString(2, obj.getMaNV());
            ps.setString(3, obj.getDiaChi());
            ps.setString(4, obj.getNgayDat());
            ps.setString(5, obj.getPttt());
            ps.setString(6, obj.getTinhTrang());
            ps.setInt(7, obj.getTongTien());
            ps.setString(8, obj.getMaDon());
            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHuy(DonHang obj, NhanVien nv) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("UPDATE DonHang SET TinhTrang = ?,MaNV = ? WHERE MaDon = ?");
            ps.setString(1, "Đã hủy");
            ps.setString(2, nv.getMaNV());
            ps.setString(3, obj.getMaDon());
            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDuyet(DonHang obj, NhanVien nv) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("UPDATE DonHang SET TinhTrang = ?,MaNV = ? WHERE MaDon = ?");
            ps.setString(1, "Đã duyệt");
            ps.setString(2, nv.getMaNV());
            ps.setString(3, obj.getMaDon());
            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DonHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DonHang> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public DonHang selectById(DonHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public ArrayList<DonHang> selectByCondtion(String condition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByCondtion'");
    }

    @Override
    public DefaultTableModel loadDataToTable(String tableName) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount + 1];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }
            columnNames[columnCount] = "Chi tiết đơn hàng";
            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] row = new Object[columnCount + 1];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                row[columnCount] = "Chi tiết";
                tableModel.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    public DefaultTableModel loadSuccessOrder(String tableName) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + tableName + " WHERE TinhTrang = N'Đã duyệt'");

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount + 1];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }
            columnNames[columnCount] = "Chi tiết đơn hàng";
            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] row = new Object[columnCount + 1];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                row[columnCount] = "Chi tiết";
                tableModel.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    public JFreeChart createLineChart(String month, String year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();

            // Truy vấn nhóm theo tháng và năm
            StringBuilder query = new StringBuilder(
                    "SELECT SUBSTRING(NgayDat, 4, 2) AS Thang, " +
                            "SUBSTRING(NgayDat, 7, 4) AS Nam, " +
                            "SUM(TongTien) AS TongTienTheoThang " +
                            "FROM DonHang WHERE 1=1 AND TinhTrang = N'Đã duyệt'");

            // Thêm điều kiện lọc theo tháng và năm
            if (!"Tất cả".equals(month)) {
                query.append(" AND SUBSTRING(NgayDat, 4, 2) = '").append(month).append("'");
            }
            if (!"Tất cả".equals(year)) {
                query.append(" AND SUBSTRING(NgayDat, 7, 4) = '").append(year).append("'");
            }

            query.append(" GROUP BY SUBSTRING(NgayDat, 4, 2), SUBSTRING(NgayDat, 7, 4)" +
                    " ORDER BY Nam, Thang");

            ResultSet rs = st.executeQuery(query.toString());

            while (rs.next()) {
                int thang = Integer.parseInt(rs.getString("Thang")); // Lấy tháng
                int nam = Integer.parseInt(rs.getString("Nam")); // Lấy năm
                int tongTien = rs.getInt("TongTienTheoThang"); // Tổng tiền theo tháng

                // Tạo giá trị trục X dạng "Tháng.Năm" (ví dụ: "04.2025")
                String xValue = String.format("%02d.%d", thang, nam);
                dataset.addValue(tongTien, "Tổng tiền nhập hàng", xValue);
            }

            // Đóng kết nối
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createLineChart(
                "Tổng doanh thu theo tháng", // Tiêu đề
                "Thời gian (Tháng.Năm)", // Nhãn trục X
                "Tổng tiền (VNĐ)", // Nhãn trục Y
                dataset, // Dataset
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true, // Hiển thị legend
                true, // Hiển thị tooltips
                false // Không hiển thị URLs
        );

        return chart;

    }

    public String countSum() {
        int tong = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TongTien FROM DonHang WHERE TinhTrang = N'Đã duyệt'");
            while (rs.next()) {
                tong += rs.getInt("TongTien");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(tong);
    }

    public DefaultTableModel loadDataToTableCondition(String thang, String nam) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            if (thang.equals("Tất cả") && nam.equals("Tất cả")) {
                return loadDataToTable("DonHang");
            }
            ResultSet rs = st.executeQuery("SELECT * FROM DonHang WHERE SUBSTRING(NgayDat, 4, 2) = '" + thang
                    + "' AND SUBSTRING(NgayDat, 7, 4) = '" + nam + "'");

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount + 1];

            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }
            columnNames[columnCount] = "Chi tiet don hang";
            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] row = new Object[columnCount + 1];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                row[columnCount] = "Chi tiet";
                tableModel.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    public String countSumCondition(String thang, String nam) {
        int tong = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            if (thang.equals("Tất cả") && nam.equals("Tất cả")) {
                return countSum();
            }
            ResultSet rs = st.executeQuery("SELECT TongTien FROM DonHang WHERE SUBSTRING(NgayDat, 4, 2) = '" + thang
                    + "' AND SUBSTRING(NgayDat, 7, 4) = '" + nam + "'");
            while (rs.next()) {
                tong += rs.getInt("TongTien");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(tong);
    }

}
