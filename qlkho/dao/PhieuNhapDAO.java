package qlkho.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.jfree.data.time.Month;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import qlkho.oop.DienThoai;
import qlkho.oop.PhieuNhap;

public class PhieuNhapDAO implements DAOInterface<PhieuNhap> {

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }

    @Override
    public void insert(PhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "INSERT INTO PhieuNhap VALUES ('" + obj.getMaHDNhap() + "', '" + obj.getMaNCC() + "', "
                    + obj.getSoLoaiDT() + ", " + obj.getNgayNhap() + ", '" + obj.getTongTien() + "', '" + obj.getMaNV()
                    + "')";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "UPDATE PhieuNhap SET MaNCC = '" + obj.getMaNCC() + "', SoLoaiDT = " + obj.getSoLoaiDT()
                    + ", NgayNhap = " + obj.getNgayNhap() + ", TongTien = '" + obj.getTongTien() + "', MaNV = N'"
                    + obj.getMaNV() + "' WHERE MaHDNhap = '"
                    + obj.getMaHDNhap() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "DELETE FROM PhieuNhap WHERE MaHDNhap = '" + obj.getMaHDNhap() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<PhieuNhap> selectAll() {
        ArrayList<PhieuNhap> list = new ArrayList<PhieuNhap>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PhieuNhap");
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap(rs.getString("MaHDNhap"), rs.getString("MaNCC"), rs.getInt("SoLoaiDT"),
                        rs.getString("NgayNhap"),
                        rs.getInt("TongTien"),
                        rs.getString("MaNV"));
                list.add(pn);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PhieuNhap selectById(PhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PhieuNhap WHERE MaHDNhap = '" + obj.getMaHDNhap() + "'");
            if (rs.next()) {
                PhieuNhap pn = new PhieuNhap(rs.getString("MaHDNhap"), rs.getString("MaNCC"), rs.getInt("SoLoaiDT"),
                        rs.getString("NgayNhap"),
                        rs.getInt("TongTien"),
                        rs.getString("MaNV"));
                return pn;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<PhieuNhap> selectByCondtion(String condition) {
        ArrayList<PhieuNhap> list = new ArrayList<PhieuNhap>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PhieuNhap WHERE " + condition);
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap(rs.getString("MaHDNhap"), rs.getString("MaNCC"), rs.getInt("SoLoaiDT"),
                        rs.getString("NgayNhap"),
                        rs.getInt("TongTien"),
                        rs.getString("MaNV"));
                list.add(pn);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
            columnNames[columnCount] = "Chi tiet hoa don";
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
                    "SELECT SUBSTRING(NgayNhap, 4, 2) AS Thang, " +
                            "SUBSTRING(NgayNhap, 7, 4) AS Nam, " +
                            "SUM(TongTien) AS TongTienTheoThang " +
                            "FROM PhieuNhap WHERE 1=1");

            // Thêm điều kiện lọc theo tháng và năm
            if (!"Tất cả".equals(month)) {
                query.append(" AND SUBSTRING(NgayNhap, 4, 2) = '").append(month).append("'");
            }
            if (!"Tất cả".equals(year)) {
                query.append(" AND SUBSTRING(NgayNhap, 7, 4) = '").append(year).append("'");
            }

            query.append(" GROUP BY SUBSTRING(NgayNhap, 4, 2), SUBSTRING(NgayNhap, 7, 4)" +
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
                "Tổng tiền nhập hàng theo tháng", // Tiêu đề
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

    public JFreeChart createBarChart(String month, String year) {
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
                    "SELECT SUBSTRING(NgayNhap, 4, 2) AS Thang, " +
                            "SUBSTRING(NgayNhap, 7, 4) AS Nam, " +
                            "SUM(TongTien) AS TongTienTheoThang " +
                            "FROM PhieuNhap WHERE 1=1");

            // Thêm điều kiện lọc theo tháng và năm
            if (!"Tất cả".equals(month)) {
                query.append(" AND SUBSTRING(NgayNhap, 4, 2) = '").append(month).append("'");
            }
            if (!"Tất cả".equals(year)) {
                query.append(" AND SUBSTRING(NgayNhap, 7, 4) = '").append(year).append("'");
            }

            query.append(" GROUP BY SUBSTRING(NgayNhap, 4, 2), SUBSTRING(NgayNhap, 7, 4)" +
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

        // Tạo biểu đồ cột
        JFreeChart chart = ChartFactory.createBarChart(
                "Tổng tiền nhập hàng theo tháng", // Tiêu đề
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

}
