package Admin_quanlitaikhoan.DAO;

import Admin_quanlitaikhoan.DTO.KhachHang;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class KhachHang_DAO implements DAOInterface<KhachHang> {
    private static KhachHang_DAO instance;

    public static KhachHang_DAO getInstance() {
        if (instance == null) {
            instance = new KhachHang_DAO();
        }
        return instance;
    }

    private KhachHang_DAO() {}

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage(), e);
        }
    }

    @Override
    public void insert(KhachHang obj) {
        String sql = "INSERT INTO KhachHang (maKH, tenKH, sdt, email, diachikh, passwordkh) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getMakh());
            stmt.setString(2, obj.getTenkh());
            stmt.setString(3, obj.getSdt());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getDiachikh());
            stmt.setString(6, obj.getPasswordkh());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting KhachHang: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(KhachHang obj) {
        String sql = "UPDATE KhachHang SET tenKH = ?, sdt = ?, email = ?, diachikh = ?, passwordkh = ? WHERE maKH = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getTenkh());
            stmt.setString(2, obj.getSdt());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getDiachikh());
            stmt.setString(5, obj.getPasswordkh());
            stmt.setString(6, obj.getMakh());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating KhachHang: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(KhachHang obj) {
        String sql = "DELETE FROM KhachHang WHERE maKH = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getMakh());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting KhachHang: " + e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> result = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMakh(rs.getString("maKH"));
                kh.setTenkh(rs.getString("tenKH"));
                kh.setSdt(rs.getString("sdt"));
                kh.setEmail(rs.getString("email"));
                kh.setDiachikh(rs.getString("diachikh"));
                kh.setPasswordkh(rs.getString("passwordkh"));
                result.add(kh);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error selecting all KhachHang: " + e.getMessage(), e);
        }
        return result;
    }

    @Override
    public KhachHang selectById(KhachHang obj) {
        String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getMakh());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMakh(rs.getString("maKH"));
                    kh.setTenkh(rs.getString("tenKH"));
                    kh.setSdt(rs.getString("sdt"));
                    kh.setEmail(rs.getString("email"));
                    kh.setDiachikh(rs.getString("diachikh"));
                    kh.setPasswordkh(rs.getString("passwordkh"));
                    return kh;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error selecting KhachHang by ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public ArrayList<KhachHang> selectByCondtion(String condition) {
        ArrayList<KhachHang> result = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE " + condition;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMakh(rs.getString("maKH"));
                kh.setTenkh(rs.getString("tenKH"));
                kh.setSdt(rs.getString("sdt"));
                kh.setEmail(rs.getString("email"));
                kh.setDiachikh(rs.getString("diachikh"));
                kh.setPasswordkh(rs.getString("passwordkh"));
                result.add(kh);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error selecting KhachHang by condition: " + e.getMessage(), e);
        }
        return result;
    }

    @Override
    public DefaultTableModel loadDataToTable(String tableName) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã KH");
        model.addColumn("Tên KH");
        model.addColumn("SĐT");
        model.addColumn("Email");
        model.addColumn("Địa Chỉ");
        model.addColumn("Mật Khẩu");

        String sql = "SELECT * FROM " + tableName;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getString("maKH"),
                    rs.getString("tenKH"),
                    rs.getString("sdt"),
                    rs.getString("email"),
                    rs.getString("diachikh"),
                    rs.getString("passwordkh")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error loading data to table: " + e.getMessage(), e);
        }
        return model;
    }
}