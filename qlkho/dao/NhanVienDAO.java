package qlkho.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import qlkho.oop.DienThoai;
import qlkho.oop.NhanVien;

public class NhanVienDAO implements DAOInterface<NhanVien> {
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    @Override
    public void insert(NhanVien obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?)");

            ps.setString(1, obj.getMaNV());
            ps.setString(2, obj.getTenNV());
            ps.setString(3, obj.getChucVu());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getSdt());
            ps.setString(6, obj.getPassword());
            ps.setString(7, obj.getManql());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(NhanVien obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(
                    "update NhanVien set TenNV = ?, ChucVu = ?, Email = ?, SDT = ?, Passwordnv = ?, MaNQL = ? where MaNV = ?");
            ps.setString(7, obj.getMaNV());
            ps.setString(1, obj.getTenNV());
            ps.setString(2, obj.getChucVu());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getSdt());
            ps.setString(5, obj.getPassword());
            ps.setString(6, obj.getManql());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(NhanVien obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("delete from NhanVien where MaNV = ?");
            ps.setString(1, obj.getMaNV());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<NhanVien> selectAll() {
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM NhanVien");
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("ChucVu"),
                        rs.getString("Email"),
                        rs.getString("Passwordnv"),
                        rs.getString("MaNQL"), rs.getString("SDT"));
                list.add(nv);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NhanVien selectById(NhanVien obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    public NhanVien selectById1(String id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE MaNV = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("ChucVu"),
                        rs.getString("Email"),
                        rs.getString("Passwordnv"),
                        rs.getString("MaNQL"), rs.getString("SDT"));
                return nv;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<NhanVien> selectByCondtion(String condition) {
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

    public String autoUpdateMaNV() {
        String maDt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TOP 1 MaNV FROM NhanVien ORDER BY MaNV DESC");
            if (rs.next()) {
                maDt = rs.getString("MaNV");
                int newMaDt = Integer.parseInt(maDt.substring(2)) + 1;
                maDt = String.format("NV%03d", newMaDt);
            } else {
                maDt = "NV001";
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maDt;
    }

}
