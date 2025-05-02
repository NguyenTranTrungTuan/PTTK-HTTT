package qlkho.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import qlkho.oop.ChiTietDonHang;
import qlkho.oop.KhachHang;
import qlkho.oop.NhanVien;

public class KhachHangDAO implements DAOInterface<KhachHang> {
    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public void insert(KhachHang obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("insert into KhachHang values(?,?,?,?,?,?)");

            ps.setString(1, obj.getMaKH());
            ps.setString(2, obj.getTenKH());
            ps.setString(3, obj.getSdt());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getDiaChi());
            ps.setString(6, obj.getPassword());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(KhachHang obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(
                    "update NhanVien set TenKH = ?, SDT = ?, Email = ?, DiaChiKH = ?, Passwordkh = ? where MaKH = ?");
            ps.setString(6, obj.getMaKH());
            ps.setString(1, obj.getTenKH());
            ps.setString(2, obj.getSdt());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getDiaChi());
            ps.setString(5, obj.getPassword());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(KhachHang obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("delete from KhachHang where MaKH = ?");
            ps.setString(1, obj.getMaKH());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM KhachHang");
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString("MaKH"), rs.getString("TenKH"),
                        rs.getString("SDT"),
                        rs.getString("Email"), rs.getString("DiaChiKH"), rs.getString("Passwordkh"));
                list.add(kh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public KhachHang selectById(KhachHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    public KhachHang selectById1(String id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM KhachHang WHERE MaKH = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString("MaKH"), rs.getString("TenKH"),
                        rs.getString("SDT"),
                        rs.getString("Email"), rs.getString("DiaChiKH"), rs.getString("Passwordkh"));
                return kh;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<KhachHang> selectByCondtion(String condition) {
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

    public String autoUpdateMaKH() {
        String maDt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TOP 1 MaKH FROM KhachHang ORDER BY MaKH DESC");
            if (rs.next()) {
                maDt = rs.getString("MaKH");
                int newMaDt = Integer.parseInt(maDt.substring(2)) + 1;
                maDt = String.format("KH%03d", newMaDt);
            } else {
                maDt = "KH001";
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maDt;
    }

}
