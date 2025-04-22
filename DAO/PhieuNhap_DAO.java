package DAO;

import java.sql.*;
import java.util.ArrayList;

public class PhieuNhap_DAO {
    private Connection con;

    public boolean OpenConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pttkhttt", "root", "123456789");
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (con != null){
                con.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getIDCTPhieuNhapFromIDDienThoai(String id){
        ArrayList<String> arr = new  ArrayList<>();
        if (OpenConnection()) {
            try {
                String sql = "SELECT CHITIETPHIEUNHAP.MaCTPnhap FROM CHITIETPHIEUNHAP WHERE CHITIETPHIEUNHAP.MaDT='"+id+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    arr.add(rs.getString("MaCTPnhap"));
                }
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally {     
                closeConnection(); 
            }   
        }
        return arr;
    } 
}
