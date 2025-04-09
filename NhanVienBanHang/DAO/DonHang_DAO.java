package NhanVienBanHang.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import java.sql.Statement;
import NhanVienBanHang.Model.DonHang;

public class DonHang_DAO implements DAOInterface<DonHang> {
    @Override
    public void insert(DonHang obj) {
        // TODO Auto-generated method stub

        
    }

    @Override
    public void update(DonHang obj) {
        // TODO Auto-generated method stub
        
    }
    @Override   
    public void delete(DonHang obj) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public DonHang selectById(DonHang obj) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public java.util.ArrayList<DonHang> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public java.util.ArrayList<DonHang> selectByCondtion(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public javax.swing.table.DefaultTableModel loadDataToTable(String tableName) {
        DefaultTableModel tableModel = new DefaultTableModel();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối đến cơ sở dữ liệu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "1234566";
            con = DriverManager.getConnection(url, username, password);

            // Truy vấn dữ liệu từ bảng
            String sql = "SELECT * FROM " + tableName;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            // Lấy thông tin cột từ ResultSet
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(rs.getMetaData().getColumnName(i));
            }

            // Lấy dữ liệu từ ResultSet và thêm vào DefaultTableModel
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tableModel;
    }

    public ArrayList<DonHang> getAllDonHang() {
        ArrayList<DonHang> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối đến cơ sở dữ liệu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            con = DriverManager.getConnection(url, username, password);

            // Truy vấn dữ liệu từ bảng DonHang
            String sql = "SELECT * FROM DonHang";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                DonHang donHang = new DonHang(
                    rs.getString("madon"),
                    rs.getString("makh"),
                    rs.getString("manv"),
                    rs.getString("diachidat"),
                    rs.getString("ngaydat"),
                    rs.getString("pttt"),
                    rs.getString("tinhtrang"),
                    rs.getString("tongtien")
                );
                list.add(donHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    
    public static DonHang_DAO getInstance() {
        return new DonHang_DAO();
    }
    

    

}