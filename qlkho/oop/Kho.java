package qlkho.oop;

public class Kho {
    String maTon;
    int soLuongTon;

    public Kho(String maTon, int soLuongTon) {
        this.maTon = maTon;
        this.soLuongTon = soLuongTon;
    }

    public String getMaTon() {
        return maTon;
    }

    public void setMaTon(String maTon) {
        this.maTon = maTon;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}
