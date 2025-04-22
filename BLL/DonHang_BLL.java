package BLL;

import java.util.ArrayList;
import DAO.DonHang_DAO;
import DTO.ChiTietDon_DTO;
import DTO.DonHang_DTO;


public class DonHang_BLL {
    DonHang_DAO dhDao = new DonHang_DAO();

    public ArrayList<DonHang_DTO> getAllDonHang(){
        return dhDao.getAllDonHang();
    }

    public ArrayList<ChiTietDon_DTO> getAllCTDH(){
        return dhDao.getAllCTDH();
    }

    public String addDonHang(DonHang_DTO dh){
        if(dhDao.hasDonHangID(dh.getMaDon())){
            return "Đơn hàng đã tồn tại!";
        }
        if(dhDao.addDonHang(dh)){
            return "Thêm đơn hàng thành công!";
        }
        return "Thêm đơn hàng thất bại!";
    }
}
