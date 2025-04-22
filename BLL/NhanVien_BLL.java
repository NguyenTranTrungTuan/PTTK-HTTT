package BLL;

import java.util.ArrayList;

import DTO.NhanVien_DTO;
import DAO.NhanVien_DAO;

public class NhanVien_BLL {
    NhanVien_DAO nvdao = new NhanVien_DAO();

    public NhanVien_DTO getNhanVienFromAccount(String email, String password){
        NhanVien_DTO nv = nvdao.getNhanVienFromAccount(email, password);
        if (nv != null && nv.getChucVu().equals("Người quản lý"))
            return nv;
        return null;
    }
}
