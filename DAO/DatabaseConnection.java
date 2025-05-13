package DAO;
import java.sql.*;


public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pttkhttt"; // Địa chỉ cơ sở dữ liệu
    private static final String USER = "root"; // Tên người dùng
    private static final String PASS = "123456789";

    public static Connection OpenConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null){
                con.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}