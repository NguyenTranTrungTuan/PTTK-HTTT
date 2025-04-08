package NhanVienBanHang.DAO;

import NhanVienBanHang.Model.DonHang;

public class DonHang_DAO implements DAOInterface<DonHang> {
    @Override
    public void insert(DonHang obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(DonHang obj) {
        // TODO Auto-generated method stub
        
    }
    @Override   
    public void delete(DonHang obj) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public DonHang selectById(DonHang obj) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public java.util.ArrayList<DonHang> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public java.util.ArrayList<DonHang> selectByCondtion(String condition) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public javax.swing.table.DefaultTableModel loadDataToTable(String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    
    public static DonHang_DAO getInstance() {
        return new DonHang_DAO();
    }
    

    

}