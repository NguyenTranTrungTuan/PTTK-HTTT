package NhanVienBanHang.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import NhanVienBanHang.Model.ChiTietDonHang;

public class ChiTietDonHang_DAO implements DAOInterface<ChiTietDonHang> {
    @Override
    public void insert(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        
    }
    @Override   
    public void delete(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public ChiTietDonHang selectById(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public java.util.ArrayList<ChiTietDonHang> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public java.util.ArrayList<ChiTietDonHang> selectByCondtion(String condition) {
        // TODO Auto-generated method stub
        return null;
    }


    public static ChiTietDonHang_DAO getInstance() {
        return new ChiTietDonHang_DAO();
    }

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

            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(rowData);
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableModel;
    }


    public DefaultTableModel loadDataToTableByMaDon(String maDon) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã Chi Tiết Đơn Hàng");
        tableModel.addColumn("Số Lượng");
        tableModel.addColumn("Thành Tiền");
        tableModel.addColumn("Mã Đơn");
        tableModel.addColumn("Đơn Giá");
        tableModel.addColumn("Mã Chi Tiết Nhập Kho");
    
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(url, username, password);
    
            String sql = "SELECT mactdh, soluong, thanhtien, madon, dongia, mactpnhap FROM ChiTietDonHang WHERE madon = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, maDon);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Object[] rowData = new Object[6];
                rowData[0] = rs.getString("mactdh");
                rowData[1] = rs.getInt("soluong");
                rowData[2] = rs.getInt("thanhtien");
                rowData[3] = rs.getString("madon");
                rowData[4] = rs.getInt("dongia");
                rowData[5] = rs.getString("mactpnhap"); 
                tableModel.addRow(rowData);
            }
    
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    
        return tableModel;
    }
    

    



}
