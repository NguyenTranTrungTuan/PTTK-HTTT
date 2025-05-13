package BLL;

import DAO.Kho_DAO;

public class Kho_BLL {
    Kho_DAO kho_DAO = new Kho_DAO();

    public int getSoLuongTon(String id){
        return kho_DAO.getSoLuongTon(id);
    }

    public String updateKho(String id, int soluongmua){
        if(!kho_DAO.hasKHOID(id)){
            return "không tìm thấy tồn kho";
        }
        int soLuong = getSoLuongTon(id);
        int new_soluong = soLuong - soluongmua; 
        if(kho_DAO.updateKHO(id, new_soluong)){
            return "cập nhật số lượng thành công!";
        }
        return "cập nhật số lượng thất bại!";
    }
}
