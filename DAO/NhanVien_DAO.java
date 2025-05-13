package DAO;
import java.sql.*;
import java.util.ArrayList;

import DTO.NhanVien_DTO;

public class NhanVien_DAO {
    private Connection con;


    public boolean hasNHANVIENID(String id){           
        con = DatabaseConnection.OpenConnection();             
        if (con != null) {
            try {            
                String sql = "SELECT * FROM NHANVIEN WHERE NHANVIEN.MaNV='"+id+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next())
                    return true;
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally {     
                DatabaseConnection.closeConnection(con); 
            }   
        }
        return false;
    }

    public NhanVien_DTO getNhanVienFromAccount(String email, String password){
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
            try {            
                String query = "SELECT * FROM NHANVIEN WHERE Email = ? AND Passwordnv = ?";
                PreparedStatement prestmt = con.prepareStatement(query);
                prestmt.setString(1, email);
                prestmt.setString(2, password);
                ResultSet rs = prestmt.executeQuery();
                if (rs.next()){
                    String idnv = rs.getString("MaNV");
                    String ten = rs.getString("TenNV");
                    String chucvu = rs.getString("ChucVu");
                    String emailnv = rs.getString("Email");
                    String sdt = rs.getString("SDT");
                    String passwdnv = rs.getString("Passwordnv");
                    String idNQL = rs.getString("MaNQL");
                    NhanVien_DTO nv = new NhanVien_DTO(idnv, ten, chucvu, emailnv, passwdnv, idNQL, sdt);
                    return nv;
                }
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally {     
                DatabaseConnection.closeConnection(con); 
            }   
        }
        return null;
    }
}
