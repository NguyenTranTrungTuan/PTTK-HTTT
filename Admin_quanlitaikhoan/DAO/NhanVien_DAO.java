package Admin_quanlitaikhoan.DAO;

import java.sql.*;
import java.util.ArrayList;
import Admin_quanlitaikhoan.DTO.NhanVien;

public class NhanVien_DAO implements DAOInterface<NhanVien> {

    public static NhanVien_DAO getInstance() {
        return new NhanVien_DAO();
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }


    @Override
    public void insert(NhanVien obj) {
        String sql = "INSERT INTO NhanVien (maNV, tennv, chucvu, ttlienlac, Username, Password, maNQL) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, obj.getMaNV());
            stmt.setString(2, obj.getTennv());
            stmt.setString(3, obj.getChucvu());
            stmt.setString(4, obj.getTtlienlac());
            stmt.setString(5, obj.getUsername());
            stmt.setString(6, obj.getPassword());
            stmt.setString(7, obj.getMaNQL());
            stmt.executeUpdate();
            System.out.println("Insert successful!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    @Override
    public void update(NhanVien obj) {
        String sql = "UPDATE NhanVien SET tennv=?, chucvu=?, ttlienlac=?, Username=?, Password=?, maNQL=? WHERE maNV=?";
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, obj.getTennv());
            stmt.setString(2, obj.getChucvu());
            stmt.setString(3, obj.getTtlienlac());
            stmt.setString(4, obj.getUsername());
            stmt.setString(5, obj.getPassword());
            stmt.setString(6, obj.getMaNQL());
            stmt.setString(7, obj.getMaNV());
            stmt.executeUpdate();
            System.out.println("Update successful!");
        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    @Override
    public void delete(NhanVien obj) {
        String sql = "DELETE FROM NhanVien WHERE maNV=?";
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, obj.getMaNV());
            stmt.executeUpdate();
            System.out.println("Delete successful!");
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    @Override
    public NhanVien selectById(NhanVien obj) {
        String sql = "SELECT * FROM NhanVien WHERE maNV=?";
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, obj.getMaNV());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NhanVien(
                    rs.getString("maNV"),
                    rs.getString("tennv"),
                    rs.getString("chucvu"),
                    rs.getString("ttlienlac"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("maNQL")
                );
            }
        } catch (Exception e) {
            System.out.println("Select by ID failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<NhanVien> selectAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                    rs.getString("maNV"),
                    rs.getString("tennv"),
                    rs.getString("chucvu"),
                    rs.getString("ttlienlac"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("maNQL")
                );
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println("Select all failed: " + e.getMessage());
        }
        return list;
    }

    @Override
    public ArrayList<NhanVien> selectByCondtion(String condition) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE " + condition;
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                    rs.getString("maNV"),
                    rs.getString("tennv"),
                    rs.getString("chucvu"),
                    rs.getString("ttlienlac"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("maNQL")
                );
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println("Select by condition failed: " + e.getMessage());
        }
        return list;
    }
}
