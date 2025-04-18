package Admin_quanlitaikhoan.DTO;

public class NhanVien {
    private String maNV;
    private String tennv;
    private String chucvu;
    private String ttlienlac;
    private String Username;
    private String Passwordnv;
    private String maNQL;

    public NhanVien(String maNV, String tennv, String chucvu, String ttlienlac, String Username, String Passwordnv, String maNQL) {
        this.maNV = maNV;
        this.tennv = tennv;
        this.chucvu = chucvu;
        this.ttlienlac = ttlienlac;
        this.Username = Username;
        this.Passwordnv = Passwordnv;
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
    }
    public String getPassword() {
        return Passwordnv;
    }
    public void setPassword(String Passwordnv) {
        Passwordnv = Passwordnv;
    }
    public String getMaNQL() {
        return maNQL;
    }

    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", tennv=" + tennv + ", chucvu=" + chucvu + ", ttlienlac=" + ttlienlac
                + ", Username=" + Username + ", Passwordnv=" + Passwordnv + ", maNQL=" + maNQL + "]";
    }
    
}
