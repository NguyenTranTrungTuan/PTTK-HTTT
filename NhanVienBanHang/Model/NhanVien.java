package NhanVienBanHang.Model;

public class NhanVien {
    private String maNV;
    private String tennv;
    private String chucvu;
    private String ttlienlac;
    private String Username;
    private String Password;
    private String maNQL;

    public NhanVien(String maNV, String tennv, String chucvu, String ttlienlac, String Username, String Password, String maNQL) {
        this.maNV = maNV;
        this.tennv = tennv;
        this.chucvu = chucvu;
        this.ttlienlac = ttlienlac;
        this.Username = Username;
        this.Password = Password;
        this.maNQL = maNQL;
    }
    public String getMaNV() {
        return maNV;
    }   
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    public String getTennv() {
        return tennv;
    }
    public void setTennv(String tennv) {
        this.tennv = tennv;
    }
    public String getChucvu() {
        return chucvu;
    }
    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }
    public String getTtlienlac() {
        return ttlienlac;
    }
    public void setTtlienlac(String ttlienlac) {
        this.ttlienlac = ttlienlac;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getMaNQL() {
        return maNQL;
    }

    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", tennv=" + tennv + ", chucvu=" + chucvu + ", ttlienlac=" + ttlienlac
                + ", Username=" + Username + ", Password=" + Password + ", maNQL=" + maNQL + "]";
    }
    
}
