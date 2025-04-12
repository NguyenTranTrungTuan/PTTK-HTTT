package NhanVienBanHang.Model;


public class ChiTietDonHang {
    private String mactdh;
    private int soluong;
    private int thanhtien;
    private String madon;
    private int dongia;
    private String mactnh;

    public ChiTietDonHang(String mactdh, int soluong, int thanhtien, String madon, int dongia, String mactnh) {
        this.mactdh = mactdh;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
        this.madon = madon;
        this.dongia = dongia;
        this.mactnh = mactnh;
    }
    public String getMactdh() {
        return mactdh;
    }
    public void setMactdh(String mactdh) {
        this.mactdh = mactdh;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public int getThanhtien() {
        return thanhtien;
    }
    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
    public String getMadon() {
        return madon;
    }
    public void setMadon(String madon) {
        this.madon = madon;
    }
    public int getDongia() {
        return dongia;
    }
    public void setDongia(int dongia) {
        this.dongia = dongia;
    }
    public String getMactnh() {
        return mactnh;
    }
    public void setMactnh(String mactnh) {
        this.mactnh = mactnh;
    }
    @Override
    public String toString() {
        return "ChiTietDonHang{" +
                "mactdh='" + mactdh + '\'' +
                ", soluong=" + soluong +
                ", thanhtien=" + thanhtien +
                ", madon='" + madon + '\'' +
                ", dongia=" + dongia +
                ", mactnh='" + mactnh + '\'' +
                '}';
    }



}
