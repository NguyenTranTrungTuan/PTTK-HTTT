package NhanVienBanHang.Model;

public class DonHang {
    private String madon;
    private String makh;
    private String manv;
    private String diachidat;
    private String ngaydat;
    private String pttt;
    private String tinhtrang;
    private String tongtien;

    

    public DonHang(String madon, String makh, String manv, String diachidat, String ngaydat, String pttt, String tinhtrang, String tongtien) {
        this.madon = madon;
        this.makh = makh;
        this.manv = manv;
        this.diachidat = diachidat;
        this.ngaydat = ngaydat;
        this.pttt = pttt;
        this.tinhtrang = tinhtrang;
        this.tongtien = tongtien;
    }

    public String getMadon() {
        return madon;
    }

    public void setMadon(String madon) {
        this.madon = madon;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }
    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getDiachidat() {
        return diachidat;
    }

    public void setDiachidat(String diachidat) {
        this.diachidat = diachidat;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }
    public String getPttt() {
        return pttt;
    }
    public void setPttt(String pttt) {
        this.pttt = pttt;
    }
    public String getTinhtrang() {
        return tinhtrang;
    }
    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
    public String getTongtien() {
        return tongtien;
    }
    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
    @Override
    
    public String toString() {
        return "DonHang{" +
                "madon='" + madon + '\'' +
                ", makh='" + makh + '\'' +
                ", manv='" + manv + '\'' +
                ", diachidat='" + diachidat + '\'' +
                ", ngaydat='" + ngaydat + '\'' +
                ", pttt='" + pttt + '\'' +
                ", tinhtrang='" + tinhtrang + '\'' +
                ", tongtien='" + tongtien + '\'' +
                '}';
    }


    
    
}
