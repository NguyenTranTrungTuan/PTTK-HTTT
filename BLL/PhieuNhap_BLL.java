package BLL;

import java.util.ArrayList;
import DAO.PhieuNhap_DAO;

public class PhieuNhap_BLL {
    PhieuNhap_DAO pNhap_DAO = new PhieuNhap_DAO();

    public ArrayList<String> getAllIDCTPhieuNhapFromIDDienThoai(String id){
        return pNhap_DAO.getIDCTPhieuNhapFromIDDienThoai(id);
    }
    
}
