package qlkho.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import qlkho.oop.ChiTietPhieuNhap;
import qlkho.oop.NhaCungCap;

public class ChiTietPhieuNhapDAO implements DAOInterface<ChiTietPhieuNhap> {

    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public void insert(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "INSERT INTO ChiTietPhieuNhap VALUES ('" + obj.getMaCTPnhap() + "', '" + obj.getMaHDNhap()
                    + "', '"
                    + obj.getDonGia() + "','" + obj.getThanhTien() + "','" + obj.getSoLuongNhap() + "','"
                    + obj.getMaDT() + "')";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "UPDATE ChiTietPhieuNhap SET MaHDNhap = '" + obj.getMaHDNhap() + "', DonGia = '"
                    + obj.getDonGia() + "',ThanhTien = '"
                    + obj.getThanhTien() + "',SoLuongNhap = '"
                    + obj.getSoLuongNhap() + "',MaDT = '"
                    + obj.getMaDT() + "' WHERE MaCTPnhap = '"
                    + obj.getMaCTPnhap() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "DELETE FROM ChiTietPhieuNhap WHERE MaCTPnhap = '" + obj.getMaCTPnhap() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ChiTietPhieuNhap> selectAll() {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<ChiTietPhieuNhap>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietPhieuNhap");
            while (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
                list.add(ctpn);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ChiTietPhieuNhap selectById(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st
                    .executeQuery("SELECT * FROM ChiTietPhieuNhap WHERE MaCTPnhap = '" + obj.getMaCTPnhap() + "'");
            if (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
                return ctpn;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChiTietPhieuNhap selectById1(String obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ChiTietPhieuNhap WHERE MaCTPnhap = ?");
            ps.setString(1, obj);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
                return ctpn;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ChiTietPhieuNhap> selectByCondtion(String condition) {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<ChiTietPhieuNhap>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietPhieuNhap WHERE " + condition);
            while (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
                list.add(ctpn);
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

    public DefaultTableModel loadDataToTableCondition(String mahdnhap) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietPhieuNhap WHERE MaHDNhap = '" + mahdnhap + "'");
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

    public String autoUpdateMaCTPN() {
        String maCTPN = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TOP 1 MaCTPnhap FROM ChiTietPhieuNhap ORDER BY MaCTPnhap DESC");
            if (rs.next()) {
                maCTPN = rs.getString("MaCTPnhap");
                int newMaCTPN = Integer.parseInt(maCTPN.substring(4)) + 1;
                maCTPN = String.format("CTPN%03d", newMaCTPN);
            } else {
                maCTPN = "CTPN001";
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maCTPN;
    }

}
