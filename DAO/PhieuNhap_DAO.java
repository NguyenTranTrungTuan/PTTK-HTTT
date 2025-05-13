package DAO;

import java.sql.*;
import java.util.ArrayList;

public class PhieuNhap_DAO {
    private Connection con;

    public ArrayList<String> getIDCTPhieuNhapFromIDDienThoai(String id){
        ArrayList<String> arr = new  ArrayList<>();
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
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
                DatabaseConnection.closeConnection(con);
            }   
        }
        return arr;
    } 
}
