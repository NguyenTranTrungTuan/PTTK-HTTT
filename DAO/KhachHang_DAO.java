package DAO;

import java.sql.*;
import java.util.ArrayList;

import DTO.KhachHang_DTO;

public class KhachHang_DAO {
    private Connection con;


    public String getLatestID(){
        ArrayList<KhachHang_DTO> arr = getAllKhachHang();
        return arr.get(arr.size()-1).getId_KhachHang();
    }

    public ArrayList<KhachHang_DTO> getAllKhachHang(){
        ArrayList<KhachHang_DTO> arr = new  ArrayList<>();
        con = DatabaseConnection.OpenConnection();  
        if (con != null){
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM KHACHHANG");
                while (rs.next()) {
                    String id = rs.getString("MaKH");
                    String ten = rs.getString("TenKH");
                    String sdt = rs.getString("SDT");
                    String emailkh = rs.getString("Email");
                    String diachi = rs.getString("DiaChiKH");
                    String passwdkh = rs.getString("Passwordkh");
                    KhachHang_DTO kh = new KhachHang_DTO(id, ten, sdt, diachi, emailkh, passwdkh);
                    arr.add(kh);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally{
                DatabaseConnection.closeConnection(con);
            }
        }
        return arr;
    }

    public KhachHang_DTO getKhachHangfromID(String id){
        con = DatabaseConnection.OpenConnection();  
        if (con != null) {
            try {            
                String sql = "SELECT * FROM KHACHHANG WHERE KHACHHANG.MaKH='"+id+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    String idkh = rs.getString("MaKH");
                    String ten = rs.getString("TenKH");
                    String sdt = rs.getString("SDT");
                    String emailkh = rs.getString("Email");
                    String diachi = rs.getString("DiaChiKH");
                    String passwdkh = rs.getString("Passwordkh");
                    KhachHang_DTO kh = new KhachHang_DTO(idkh, ten, sdt, diachi, emailkh, passwdkh);
                    return kh;
                }
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally {     
                DatabaseConnection.closeConnection(con); 
            }   
        }
        return null;
    }

    public boolean changeKhachHangData(KhachHang_DTO newdata){
        boolean result = false;
        con = DatabaseConnection.OpenConnection();  
        if (con != null) {
            try {                    
                String query1 = "UPDATE KHACHHANG "+
                                "SET TenKH = ?, SDT = ?, Email = ?, DiaChiKH = ?, Passwordkh = ? "+
                                "WHERE MaKH = ?";
                PreparedStatement stmt1 = con.prepareStatement(query1);
                stmt1.setString(1, newdata.getTen_KhachHang());
                stmt1.setString(2, newdata.getSdt_KhachHang());
                stmt1.setString(3, newdata.getEmail_KhachHang());
                stmt1.setString(4, newdata.getDiaChi_KhachHang());
                stmt1.setString(5, newdata.getPass_KhachHang());
                stmt1.setString(6, newdata.getId_KhachHang());

                if (stmt1.executeUpdate()>=1)
                    result = true;
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally{
                DatabaseConnection.closeConnection(con);  
            } 
        }
        return result;
    }

    public boolean addKhachHang(KhachHang_DTO kh){
        boolean result = false;
        con = DatabaseConnection.OpenConnection();  
        if (con != null) {
            try {                    
                String query1 = "INSERT INTO KHACHHANG VALUES(?,?,?,?,?,?)";
                PreparedStatement stmt1 = con.prepareStatement(query1);
                stmt1.setString(1, kh.getId_KhachHang());
                stmt1.setString(2, kh.getTen_KhachHang());
                stmt1.setString(3, kh.getSdt_KhachHang());
                stmt1.setString(4, kh.getEmail_KhachHang());
                stmt1.setString(5, kh.getDiaChi_KhachHang());
                stmt1.setString(6, kh.getPass_KhachHang());

                if (stmt1.executeUpdate()>=1)
                    result = true;
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally{
                DatabaseConnection.closeConnection(con);  
            } 
        }
        return result;
    }

    public boolean removeKhachHang(String id){
        boolean result = false;
        con = DatabaseConnection.OpenConnection();  
        if (con != null) {
            try {                    
                String query1 = "DELETE FROM KHACHHANG WHERE KHACHHANG.MaKH = ?";
                PreparedStatement stmt1 = con.prepareStatement(query1);

                stmt1.setString(1, id);
                if (stmt1.executeUpdate()>=1)
                    result = true;
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally{
                DatabaseConnection.closeConnection(con);  
            } 
        }
        return result;
    }

    public boolean hasKhachHangEmail(String email){
        con = DatabaseConnection.OpenConnection();  
        if (con != null) {
            try {            
                String sql = "SELECT * FROM KHACHHANG WHERE KHACHHANG.Email='"+email+"'";
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

    public boolean hasKhachHangID(String id){   
        con = DatabaseConnection.OpenConnection();                       
        if (con != null) {
            try {            
                String sql = "SELECT * FROM KHACHHANG WHERE KHACHHANG.MaKH='"+id+"'";
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

    public KhachHang_DTO getKhachHangFromAccount(String email, String password){
        con = DatabaseConnection.OpenConnection();  
        if (con != null) {
            try {            
                String query = "SELECT * FROM KHACHHANG WHERE Email = ? AND Passwordkh = ?";
                PreparedStatement prestmt = con.prepareStatement(query);
                prestmt.setString(1, email);
                prestmt.setString(2, password);
                ResultSet rs = prestmt.executeQuery();
                if (rs.next()){
                    String idkh = rs.getString("MaKH");
                    String ten = rs.getString("TenKH");
                    String sdt = rs.getString("SDT");
                    String emailkh = rs.getString("Email");
                    String diachi = rs.getString("DiaChiKH");
                    String passwdkh = rs.getString("Passwordkh");
                    KhachHang_DTO kh = new KhachHang_DTO(idkh, ten, sdt, diachi, emailkh, passwdkh);
                    return kh;
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
