package NhanVienBanHang.Model;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String Chucvu;
    private String TTLIENLAC;
    private String Username;
    private String Password;
    private String MaNQL;

    public NhanVien(String maNV, String tenNV, String Chucvu, String TTLIENLAC, String Username, String Password, String MaNQL) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.Chucvu = Chucvu;
        this.TTLIENLAC = TTLIENLAC;
        this.Username = Username;
        this.Password = Password;
        this.MaNQL = MaNQL;
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
    public String getChucvu() {
        return Chucvu;
    }
    public void setChucvu(String chucvu) {
        Chucvu = chucvu;
    }
    public String getTTLIENLAC() {
        return TTLIENLAC;
    }
    public void setTTLIENLAC(String TTLIENLAC) {
        this.TTLIENLAC = TTLIENLAC;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public String getMaNQL() {
        return MaNQL;
    }
    public void setMaNQL(String maNQL) {
        MaNQL = maNQL;
    }
    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", Chucvu='" + Chucvu + '\'' +
                ", TTLIENLAC='" + TTLIENLAC + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", MaNQL='" + MaNQL + '\'' +
                '}';
    }

}
