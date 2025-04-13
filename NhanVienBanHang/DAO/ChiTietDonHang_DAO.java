package NhanVienBanHang.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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



}
