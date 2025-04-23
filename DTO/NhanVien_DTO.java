package DTO;

public class NhanVien_DTO {
    String maNV;
    String tenNV;
    String chucVu;
    String email;
    String password;
    String manql;
    String sdt;

    public NhanVien_DTO(String maNV, String tenNV, String chucVu, String email, String password, String manql, String sdt) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.email = email;
        this.password = password;
        this.manql = manql;
        this.sdt = sdt;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManql() {
        return manql;
    }

    public void setManql(String manql) {
        this.manql = manql;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}
