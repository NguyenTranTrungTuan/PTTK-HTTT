package Admin_quanlitaikhoan.DAO;

import Admin_quanlitaikhoan.DTO.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
        String sql = "INSERT INTO NhanVien (maNV, tenNV, chucvu, ttlienlac, username, password, maNQL) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getMaNV());
            stmt.setString(2, obj.getTennv());
            stmt.setString(3, obj.getChucvu());
            stmt.setString(4, obj.getTtlienlac());
            stmt.setString(5, obj.getUsername());
            stmt.setString(6, obj.getPassword());
            stmt.setString(7, obj.getMaNQL());
            stmt.executeUpdate();
            System.out.println("Insert thành công!");
        } catch (Exception e) {
            System.out.println("Insert thất bại: " + e.getMessage());
        }
    }

    @Override
    public void update(NhanVien obj) {
        String sql = "UPDATE NhanVien SET tenNV = ?, chucvu = ?, ttlienlac = ?, username = ?, password = ?, maNQL = ? WHERE maNV = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getTennv());
            stmt.setString(2, obj.getChucvu());
            stmt.setString(3, obj.getTtlienlac());
            stmt.setString(4, obj.getUsername());
            stmt.setString(5, obj.getPassword());
            stmt.setString(6, obj.getMaNQL());
            stmt.setString(7, obj.getMaNV());
            stmt.executeUpdate();
            System.out.println("Update thành công!");
        } catch (Exception e) {
            System.out.println("Update thất bại: " + e.getMessage());
        }
    }

    @Override
    public void delete(NhanVien obj) {
        String sql = "DELETE FROM NhanVien WHERE maNV = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getMaNV());
            stmt.executeUpdate();
            System.out.println("Delete thành công!");
        } catch (Exception e) {
            System.out.println("Delete thất bại: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<NhanVien> selectAll() {
        ArrayList<NhanVien> result = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("maNV"));
                nv.setTennv(rs.getString("tenNV"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setTtlienlac(rs.getString("ttlienlac"));
                nv.setUsername(rs.getString("username"));
                nv.setPassword(rs.getString("password"));
                nv.setMaNQL(rs.getString("maNQL"));
                result.add(nv);
            }
        } catch (Exception e) {
            System.out.println("Select all thất bại: " + e.getMessage());
        }
        return result;
    }

    @Override
    public NhanVien selectById(NhanVien obj) {
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getMaNV());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("maNV"));
                nv.setTennv(rs.getString("tenNV"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setTtlienlac(rs.getString("ttlienlac"));
                nv.setUsername(rs.getString("username"));
                nv.setPassword(rs.getString("password"));
                nv.setMaNQL(rs.getString("maNQL"));
                return nv;
            }
        } catch (Exception e) {
            System.out.println("Select by ID thất bại: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<NhanVien> selectByCondtion(String condition) {
        ArrayList<NhanVien> result = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE " + condition;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("maNV"));
                nv.setTennv(rs.getString("tenNV"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setTtlienlac(rs.getString("ttlienlac"));
                nv.setUsername(rs.getString("username"));
                nv.setPassword(rs.getString("password"));
                nv.setMaNQL(rs.getString("maNQL"));
                result.add(nv);
            }
        } catch (Exception e) {
            System.out.println("Select by condition thất bại: " + e.getMessage());
        }
        return result;
    }

    @Override
    public DefaultTableModel loadDataToTable(String tableName) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã NV");
        model.addColumn("Tên NV");
        model.addColumn("Chức Vụ");
        model.addColumn("TT Liên Lạc");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Mã NQL");

        String sql = "SELECT * FROM " + tableName;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getString("maNV"),
                    rs.getString("tenNV"),
                    rs.getString("chucvu"),
                    rs.getString("ttlienlac"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("maNQL")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("Load data to table thất bại: " + e.getMessage());
        }
        return model;
    }
}
