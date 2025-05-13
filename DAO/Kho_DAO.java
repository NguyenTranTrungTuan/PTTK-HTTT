package DAO;

import java.sql.*;

public class Kho_DAO {
    Connection con;

    public boolean updateKHO(String id, int new_soluong){
        boolean result = false;
        con = DatabaseConnection.OpenConnection();
        if(con != null){
            try{        
                String query1 = "UPDATE KHO "+
                                "SET SoLuongTon = ? "+
                                "WHERE MaTon = ?";
                PreparedStatement stmt1 = con.prepareStatement(query1);
                stmt1.setInt(1, new_soluong);
                stmt1.setString(2, id);

                if(stmt1.executeUpdate() >=1){
                    result = true;
                }
            }
            catch(SQLException e){
                System.out.println(e);
            } finally{
                DatabaseConnection.closeConnection(con);
            }
        }
        return result;
    }

    public int getSoLuongTon(String id){
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
            try {
                String query = "SELECT SoLuongTon FROM KHO WHERE KHO.MaTon = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("SoLuongTon");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                DatabaseConnection.closeConnection(con);
            }
        }
        return 0;
    }

    public boolean hasKHOID(String id){    
        con = DatabaseConnection.OpenConnection();                    
        if (con != null) {
            try {            
                String sql = "SELECT * FROM KHO WHERE KHO.MaTon='"+id+"'";
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
}
