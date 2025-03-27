package qlkho.dao;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import qlkho.oop.DienThoai;

public class DienThoaiDAO implements DAOInterface<DienThoai> {

    public static DienThoaiDAO getInstance() {
        return new DienThoaiDAO();
    }

    @Override
    public void insert(DienThoai obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "INSERT INTO DienThoai VALUES ('" + obj.getMaDt() + "', N'" + obj.getTenDt() + "', "
                    + obj.getGiaBan() + ", " + obj.getGiaNhap() + ", '" + obj.getMaTon() + "', N'" + obj.getXuatXu()
                    + "', " + obj.getTrongLuong() + ", " + obj.getKichThuocManHinh() + ", " + obj.getDungLuong() + ", "
                    + obj.getRam() + ", " + obj.getBaoHanh() + ", '" + obj.getMaNcc() + "')";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DienThoai obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "UPDATE DienThoai SET TenDT = N'" + obj.getTenDt() + "', GiaBan = " + obj.getGiaBan()
                    + ", GiaNhap = " + obj.getGiaNhap() + ", MaTon = '" + obj.getMaTon() + "', XuatXu = N'"
                    + obj.getXuatXu() + "', TrongLuong = " + obj.getTrongLuong() + ", KichThuocManHinh = "
                    + obj.getKichThuocManHinh() + ", DungLuong = " + obj.getDungLuong() + ", RAM = " + obj.getRam()
                    + ", BaoHanh = " + obj.getBaoHanh() + ", MaNCC = '" + obj.getMaNcc() + "' WHERE MaDT = '"
                    + obj.getMaDt() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DienThoai obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "DELETE FROM DienThoai WHERE MaDT = '" + obj.getMaDt() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<DienThoai> selectAll() {
        ArrayList<DienThoai> list = new ArrayList<DienThoai>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DienThoai");
            while (rs.next()) {
                DienThoai dt = new DienThoai(rs.getString("MaDT"), rs.getString("TenDT"), rs.getLong("GiaBan"),
                        rs.getLong("GiaNhap"),
                        rs.getString("MaTon"),
                        rs.getString("XuatXu"), rs.getFloat("TrongLuong"), rs.getFloat("KichThuocManHinh"),
                        rs.getInt("DungLuong"), rs.getInt("RAM"), rs.getInt("BaoHanh"),
                        rs.getString("MaNCC"));
                list.add(dt);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DienThoai selectById(DienThoai obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DienThoai WHERE MaDT = '" + obj.getMaDt() + "'");
            if (rs.next()) {
                DienThoai dt = new DienThoai(rs.getString("MaDT"), rs.getString("TenDT"), rs.getLong("GiaBan"),
                        rs.getLong("GiaNhap"),
                        rs.getString("MaTon"),
                        rs.getString("XuatXu"), rs.getFloat("TrongLuong"), rs.getFloat("KichThuocManHinh"),
                        rs.getInt("DungLuong"), rs.getInt("RAM"), rs.getInt("BaoHanh"),
                        rs.getString("MaNCC"));
                return dt;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<DienThoai> selectByCondtion(String condition) {
        ArrayList<DienThoai> list = new ArrayList<DienThoai>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DienThoai WHERE " + condition);
            while (rs.next()) {
                DienThoai dt = new DienThoai(rs.getString("MaDT"), rs.getString("TenDT"), rs.getLong("GiaBan"),
                        rs.getLong("GiaNhap"),
                        rs.getString("MaTon"),
                        rs.getString("XuatXu"), rs.getFloat("TrongLuong"), rs.getFloat("KichThuocManHinh"),
                        rs.getInt("DungLuong"), rs.getInt("RAM"), rs.getInt("BaoHanh"),
                        rs.getString("MaNCC"));
                list.add(dt);
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
}