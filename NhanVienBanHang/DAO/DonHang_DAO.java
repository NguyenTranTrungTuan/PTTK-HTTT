package NhanVienBanHang.DAO;

import NhanVienBanHang.Model.DonHang;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    public static DonHang_DAO getInstance() {
        return new DonHang_DAO();
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }
    


    @Override
        public DefaultTableModel loadDataToTable(String tableName) {
            DefaultTableModel tableModel = new DefaultTableModel();
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
                String username = "sa";
                String password = "123456";
                Connection con = DriverManager.getConnection(url, username, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                String[] columnNames = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    columnNames[i - 1] = metaData.getColumnName(i);
                }
                tableModel.setColumnIdentifiers(columnNames);

                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    tableModel.addRow(row);
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
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
    
    public boolean updateTinhTrang(String madon, String newTinhTrang) {
        Connection con = null;
        Statement stmt = null;
        try {
            // Kết nối đến cơ sở dữ liệu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            con = DriverManager.getConnection(url, username, password);

            // Cập nhật tình trạng đơn hàng
            String sql = "UPDATE DonHang SET tinhtrang = '" + newTinhTrang + "' WHERE madon = '" + madon + "'";
            stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    



    


    public DefaultTableModel loadDataToTableByDateRange(String tableName, Date startDate, Date endDate) {
    DefaultTableModel tableModel = new DefaultTableModel();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        con = getConnection();
        String query = "SELECT * FROM " + tableName + " WHERE ngaydat BETWEEN ? AND ?";
        stmt = con.prepareStatement(query);
        stmt.setDate(1, new java.sql.Date(startDate.getTime()));
        stmt.setDate(2, new java.sql.Date(endDate.getTime()));
        rs = stmt.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        tableModel.setColumnIdentifiers(columnNames);

        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            tableModel.addRow(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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



    

}