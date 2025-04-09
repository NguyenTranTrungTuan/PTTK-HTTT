package BLL;

import DTO.DienThoai_DTO;
import DAO.DienThoai_DAO;
import java.util.ArrayList;

public class DienThoai_BLL {
    DienThoai_DAO dtdao = new DienThoai_DAO();

    public ArrayList<DienThoai_DTO> getAllDienThoai(){
        return dtdao.getAllDT();
    }

    public ArrayList<DienThoai_DTO> getAllType(String typename){
        return dtdao.getAllType(typename);
    }

    public int getSoLuongTon(String id){
        return dtdao.getSoLuongDienThoai(id);
    }

    public String getThuongHieu(String id){
        return dtdao.getThuongHieu(id);
    }

    public String addDienThoai(DienThoai_DTO dt){
        if(dtdao.hasDienThoaiID(dt.getID_SanPham())){
            return "Điện thoại đã tồn tại!";
        }
        if(dtdao.addDienThoai(dt)){
            return "Thêm điện thoại thành công!";
        }
        return "Thêm điện thoại thất bại!";
    }

    public String removeDienThoai(DienThoai_DTO dt){
        if(!dtdao.hasDienThoaiID(dt.getID_SanPham())){
            return "Điện thoại không tồn tại!";
        }
        if(dtdao.removeDienThoai(dt.getID_SanPham())){
            return "Xoá điện thoại thành công!";
        }

        return "Xóa điện thoại thất bại!";
    }
}
