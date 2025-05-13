package DAO;

import DTO.ChiTietDon_DTO;
import DTO.DienThoai_DTO;
import DTO.DonHang_DTO;

import java.sql.*;
import java.util.ArrayList;

import BLL.DienThoai_BLL;

public class DonHang_DAO{
    private Connection con;
    private DienThoai_BLL dtbll = new DienThoai_BLL();


    public String getNextDHID(Connection con){
        String latestID = "";
        try {
            String sql = "SELECT MaDon FROM DONHANG ORDER BY MaDon DESC LIMIT 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                latestID = rs.getString("MaDon");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String prefix = latestID.replaceAll("\\d+", "");
        String numberic = latestID.replaceAll("[^\\d]", "");

        int number = Integer.parseInt(numberic);
        number++;
        String nextnumberic = String.format("0%" + numberic.length() + "d", number);
        return (prefix+nextnumberic).replace(" ", ""); 
    }

    public String getNextCTDHID(Connection con){
        String latestID = "";
        try {
            String sql = "SELECT MaCTDH FROM CHITIETDONHANG ORDER BY MaCTDH DESC LIMIT 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                latestID = rs.getString("MaCTDH");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String prefix = latestID.replaceAll("\\d+", "");
        String numberic = latestID.replaceAll("[^\\d]", "");

        int number = Integer.parseInt(numberic);
        number++;
        String nextnumberic = String.format("0%" + numberic.length() + "d", number);
        return (prefix+nextnumberic).replace(" ", ""); 
    }

    public ArrayList<DonHang_DTO> getAllDonHang(){
        ArrayList<DonHang_DTO> arr = new  ArrayList<>();
        con = DatabaseConnection.OpenConnection();  
        if (con != null){
            try{
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery("SELECT * FROM DONHANG");
                while (rs.next()) {
                    String id = rs.getString("MaDon");
                    String makh = rs.getString("MaKH");
                    String manv = rs.getString("MaNV");
                    String diachidat = rs.getString("DiaChiDat");
                    String date = rs.getString("NgayDat");
                    String pttt = rs.getString("PTTT");
                    String tinhtrang = rs.getString("TinhTrang");
                    Double TongTien = rs.getDouble("TongTien");
                    ArrayList<ChiTietDon_DTO> dsctdh = getAllChiTietForDH(id);
                    DonHang_DTO dh = new DonHang_DTO(id, makh, manv, diachidat, date, pttt, tinhtrang, dsctdh, TongTien);
                    arr.add(dh);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally{
                DatabaseConnection.closeConnection(con);
            }
        }
        return arr;
    }

    public ArrayList<ChiTietDon_DTO> getAllChiTietForDH(String id){
        ArrayList<ChiTietDon_DTO> arr = new  ArrayList<>();
        con = DatabaseConnection.OpenConnection();  
        if (con != null){
            try{
                String query = "SELECT * FROM CHITIETDONHANG, CHITIETPHIEUNHAP WHERE CHITIETDONHANG.MaCTPnhap = CHITIETPHIEUNHAP.MaCTPnhap AND CHITIETDONHANG.MaDon = ?";
                PreparedStatement prstmt = con.prepareStatement(query);
                prstmt.setString(1, id);
                ResultSet rs = prstmt.executeQuery();
                while (rs.next()) {
                    String idCT = rs.getString("MaCTDH");
                    int SoLuong = rs.getInt("SoLuong");
                    // Double DonGia = rs.getDouble("DonGia");
                    Double ThanhTien = rs.getDouble("ThanhTien");
                    String MaCTPnhap = rs.getString("MaCTPnhap");
                    String MaDT = rs.getString("MaDT"); 
                    DienThoai_DTO dt = dtbll.getDTFromID(MaDT);
                    ChiTietDon_DTO ctdh = new ChiTietDon_DTO(idCT, dt, SoLuong, ThanhTien, MaCTPnhap);
                    arr.add(ctdh);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally{
                DatabaseConnection.closeConnection(con);
            }
        }
        return arr;
    }

    public ArrayList<ChiTietDon_DTO> getAllCTDH(){
        ArrayList<ChiTietDon_DTO> arr = new  ArrayList<>();
        con = DatabaseConnection.OpenConnection();  
        if (con != null){
            try{
                String query = "SELECT * FROM CHITIETDONHANG, CHITIETPHIEUNHAP WHERE CHITIETDONHANG.MaCTPnhap = CHITIETPHIEUNHAP.MaCTPnhap";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String idCT = rs.getString("MaCTDH");
                    int SoLuong = rs.getInt("SoLuong");
                    // Double DonGia = rs.getDouble("DonGia");
                    Double ThanhTien = rs.getDouble("ThanhTien");
                    String MaCTPnhap = rs.getString("MaCTPnhap");
                    String MaDT = rs.getString("MaDT"); 
                    DienThoai_DTO dt = dtbll.getDTFromID(MaDT);
                    ChiTietDon_DTO ctdh = new ChiTietDon_DTO(idCT, dt, SoLuong, ThanhTien, MaCTPnhap);
                    arr.add(ctdh);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally{
                DatabaseConnection.closeConnection(con);
            }
        }
        return arr;
    }

    public boolean addDonHang(DonHang_DTO dh){
        boolean result = false;
        int success_count = 0;
        con = DatabaseConnection.OpenConnection();  
        if (con != null) {
            try {                    
                String query1 = "INSERT INTO DONHANG VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement stmt1 = con.prepareStatement(query1);
                String DonHangID = getNextDHID(con);
                stmt1.setString(1, DonHangID);
                stmt1.setString(2, dh.getIdKhachHang());
                stmt1.setString(3, null);
                stmt1.setString(4, dh.getDiaChiDat());
                stmt1.setString(5, dh.getNgayDat());
                stmt1.setString(6, dh.getPTTT());
                stmt1.setString(7, dh.getTinhTrangDonHang());
                stmt1.setDouble(8, dh.getTongTien());
                if(stmt1.executeUpdate()>=1){
                    for(ChiTietDon_DTO ct:dh.getDsSanPhamMua()){
                        try{
                            String query2 = "INSERT INTO CHITIETDONHANG VALUES(?,?,?,?,?,?)";
                            PreparedStatement stmt2 = con.prepareStatement(query2);

                            stmt2.setString(1, getNextCTDHID(con));
                            stmt2.setInt(2, ct.getSoLuongMua());
                            stmt2.setDouble(3, ct.getThanhTien());
                            stmt2.setString(4, DonHangID);
                            stmt2.setDouble(5, ct.getThongTinSanPham().getGia_SanPham());
                            stmt2.setString(6, ct.getMaCTPnhap());

                            if(stmt2.executeUpdate()>=1)
                                success_count++;
                        } catch (SQLException ex) {
                            System.out.println(ex);
                        } 
                    }
                }
                if (success_count == dh.getDsSanPhamMua().size())
                    result = true;
            } catch (SQLException ex) {
                System.out.println(ex);            
            } finally{
                DatabaseConnection.closeConnection(con);  
            } 
        }
        return result;
    }

    public boolean hasDonHangID(String id){   
        con = DatabaseConnection.OpenConnection();                       
        if (con != null) {
            try {            
                String sql = "SELECT * FROM DONHANG WHERE DONHANG.MaDon='"+id+"'";
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