package BLL;

import java.util.ArrayList;

import DAO.KhachHang_DAO;
import DTO.KhachHang_DTO;

public class KhachHang_BLL {
    KhachHang_DAO khachhangDao = new KhachHang_DAO();

    public ArrayList<KhachHang_DTO> getAllKhachHang(){
        return khachhangDao.getAllKhachHang();
    }

    public String getLatestID(){
        return khachhangDao.getLatestID();
    }

    public String getNextID(){
        String latestID = getLatestID();
        String prefix = latestID.replaceAll("\\d+", "");
        String numberic = latestID.replaceAll("[^\\d]", "");

        int number = Integer.parseInt(numberic);
        number++;
        String nextnumberic = String.format("0%" + numberic.length() + "d", number);
        return (prefix+nextnumberic).replace(" ", ""); 
    }

    public KhachHang_DTO getKhachHangFromAccount(String email, String password){
        KhachHang_DTO kh = khachhangDao.getKhachHangFromAccount(email, password);
        if (kh != null)
            return kh;
        return null;
    }

    public String changeKhachHangData(KhachHang_DTO newdata){
        if(!khachhangDao.hasKhachHangID(newdata.getId_KhachHang())){
            System.err.println(newdata.getId_KhachHang());
            return "khách hàng không tồn tại!";
        }
        if(khachhangDao.changeKhachHangData(newdata)){
            return "Cập nhật dữ liệu khách hàng thành công!";
        }

        return "Cập nhật dữ liệu Khách hàng thất bại!";
    }

    public String addKhachHang(KhachHang_DTO kh){
        if(khachhangDao.hasKhachHangID(kh.getId_KhachHang())){
            return "khách hàng đã tồn tại!";
        }
        if(khachhangDao.hasKhachHangEmail(kh.getEmail_KhachHang())){
            return "Email đã tồn tại!";
        }
        if(khachhangDao.addKhachHang(kh)){
            return "Thêm khách hàng thành công!";
        }
        return "Thêm khách hàng thất bại!";
    }

    public String removeKhachHang(KhachHang_DTO kh){
        if(!khachhangDao.hasKhachHangID(kh.getId_KhachHang())){
            return "khách hàng không tồn tại!";
        }
        if(khachhangDao.removeKhachHang(kh.getId_KhachHang())){
            return "Xoá khách hàng thành công!";
        }

        return "Xóa Khách hàng thất bại!";
    }
}
