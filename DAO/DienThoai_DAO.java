package DAO;

import DTO.DienThoai_DTO;

import java.sql.*;
import java.util.ArrayList;

public class DienThoai_DAO {
    private Connection con;


    public ArrayList<DienThoai_DTO> getAllDT(){
        ArrayList<DienThoai_DTO> arr = new  ArrayList<>();
        con = DatabaseConnection.OpenConnection();
        if (con != null){
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM DIENTHOAI");
                while (rs.next()) {
                    String id = rs.getString("MaDT");
                    String ten = rs.getString("TenDT");
                    double gia = rs.getDouble("GiaBan");
                    double gianhap = rs.getDouble("GiaNhap");
                    String idtonkho = rs.getString("MaTon");
                    String xuatXu = rs.getString("XuatXu");
                    String trongLuong = rs.getString("TrongLuong");
                    String kichThuocManHinh = rs.getString("KichThuocManHinh");
                    String dungLuong = rs.getString("DungLuong");
                    String ram = rs.getString("RAM");
                    String idncc = rs.getString("MaNCC");
                    int baoHanh = rs.getInt("BaoHanh");

                    DienThoai_DTO dt = new DienThoai_DTO(id, ten, gia, gianhap, idtonkho, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, idncc, baoHanh);
                    arr.add(dt);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally{
                DatabaseConnection.closeConnection(con);
            }
        }
        return arr;
    }

    public DienThoai_DTO getDTFromID(String id){
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
            try {            
                String sql = "SELECT * FROM DIENTHOAI WHERE DIENTHOAI.MaDT='"+id+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    String madt = rs.getString("MaDT");
                    String ten = rs.getString("TenDT");
                    double gia = rs.getDouble("GiaBan");
                    double gianhap = rs.getDouble("GiaNhap");
                    String idtonkho = rs.getString("MaTon");
                    String xuatXu = rs.getString("XuatXu");
                    String trongLuong = rs.getString("TrongLuong");
                    String kichThuocManHinh = rs.getString("KichThuocManHinh");
                    String dungLuong = rs.getString("DungLuong");
                    String ram = rs.getString("RAM");
                    String idncc = rs.getString("MaNCC");
                    int baoHanh = rs.getInt("BaoHanh");

                    DienThoai_DTO dt = new DienThoai_DTO(madt, ten, gia, gianhap, idtonkho, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, idncc, baoHanh);
                    return dt;
                }
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally {     
                DatabaseConnection.closeConnection(con); 
            }   
        }
        return null;
    }

    public ArrayList<DienThoai_DTO> getAllType(String typename){
        ArrayList<DienThoai_DTO> arr = new  ArrayList<>();
        con = DatabaseConnection.OpenConnection();
        if (con != null){
            try{
                String query = 
                        "SELECT * FROM DIENTHOAI, KHO, NHACUNGCAP " +
                        "WHERE DIENTHOAI.MaTon = KHO.MaTon AND " +
                        "DIENTHOAI.MaNCC = NHACUNGCAP.MaNCC AND " +
                        "TenNCC LIKE ? ";
                PreparedStatement prestmt = con.prepareStatement(query);
                prestmt.setString(1, "%"+typename+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("MaDT");
                    String ten = rs.getString("TenDT");
                    double gia = rs.getDouble("GiaBan");
                    double gianhap = rs.getDouble("GiaNhap");
                    String idtonkho = rs.getString("MaTon");
                    String xuatXu = rs.getString("XuatXu");
                    String trongLuong = rs.getString("TrongLuong");
                    String kichThuocManHinh = rs.getString("KichThuocManHinh");
                    String dungLuong = rs.getString("DungLuong");
                    String ram = rs.getString("RAM");
                    String idncc = rs.getString("MaNCC");
                    int baoHanh = rs.getInt("BaoHanh");

                    DienThoai_DTO dt = new DienThoai_DTO(id, ten, gia, gianhap, idtonkho, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, idncc, baoHanh);
                    arr.add(dt);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally{
                DatabaseConnection.closeConnection(con);
            }
        }
        return arr;
    }

    public int getSoLuongDienThoai(String id) {
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM KHO WHERE KHO.MaTon = ?";
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
    
    public String getThuongHieu(String id) {
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM NHACUNGCAP WHERE NHACUNGCAP.MaNCC = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("TenNCC");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                DatabaseConnection.closeConnection(con);
            }
        }
        return "N/A";
    }

    public boolean addDienThoai(DienThoai_DTO dt){
        boolean result = false;
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
            try {                    
                String query = "INSERT INTO DIENTHOAI VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, dt.getID_SanPham());
                stmt.setString(2, dt.getTen_SanPham());
                stmt.setDouble(3, dt.getGia_SanPham());
                stmt.setDouble(4,dt.getGiaNhap());
                stmt.setString(5, dt.getID_Tonkho());
                stmt.setString(6, dt.getXuatXu());
                stmt.setString(7, dt.getTrongLuong());
                stmt.setString(8, dt.getKichThuocManHinh());
                stmt.setString(9, dt.getDungLuong());
                stmt.setString(10, dt.getRAM());
                stmt.setInt(11, dt.getBaoHanh());
                stmt.setString(11, dt.getID_NCC());
                if (stmt.executeUpdate()>=1)
                    result = true;
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally{
                DatabaseConnection.closeConnection(con);  
            } 
        }
        return result;
    }

    public boolean removeDienThoai(String id){
        boolean result = false;
        con = DatabaseConnection.OpenConnection();
        if (con != null) {
            try {                    
                String query1 = "DELETE FROM DIENTHOAI WHERE DIENTHOAI.MaDT = ?";
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

    public boolean hasDienThoaiID(String id){    
        con = DatabaseConnection.OpenConnection();                    
        if (con != null) {
            try {            
            String sql = "SELECT * FROM DIENTHOAI WHERE DIENTHOAI.MaDT='"+id+"'";
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