package qlgiohang.oop;
public class ChiTietDonHang {
    private String maCTDH; 
    private int soLuong; 
    private int thanhTien; 
    private String maDon; 
    private int donGia;   
    private String maCTPnhap; 

    public ChiTietDonHang(String maCTDH, int soLuong, int thanhTien, String maDon, int donGia, String maCTPnhap) {
        this.maCTDH = maCTDH;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.maDon = maDon;
        this.donGia = donGia;
        this.maCTPnhap = maCTPnhap;
    }
    public ChiTietDonHang(String maCTDH, int soLuong, int thanhTien, String maDon, int donGia) {
        this.maCTDH = maCTDH;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.maDon = maDon;
        this.donGia = donGia;
    }

    public String getMaCTDH() {
        return maCTDH;
    }

    public void setMaCTDH(String maCTDH) {
        this.maCTDH = maCTDH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaDon() {
        return maDon;
    }

    public void setMaDon(String maDon) {
        this.maDon = maDon;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getMaCTPnhap() {
        return maCTPnhap;
    }

    public void setMaCTPnhap(String maCTPnhap) {
        this.maCTPnhap = maCTPnhap;
    }

    @Override
    public String toString() {
        return "ChiTietDonHang [maCTDH=" + maCTDH + ", soLuong=" + soLuong + ", thanhTien=" + thanhTien + ", maDon="
                + maDon + ", donGia=" + donGia + ", maCTPnhap=" + maCTPnhap + "]";
    }
}