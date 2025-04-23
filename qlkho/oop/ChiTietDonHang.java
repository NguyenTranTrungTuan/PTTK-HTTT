package qlkho.oop;

public class ChiTietDonHang {
    String maCTDH;
    int soLuong;
    int thanhTien;
    String maDon;
    int donGia;
    String maCTPN;

    public ChiTietDonHang(String maCTDH, int soLuong, int thanhTien, String maDon, int donGia, String maCTPN) {
        this.maCTDH = maCTDH;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.maDon = maDon;
        this.donGia = donGia;
        this.maCTPN = maCTPN;
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

    public String getMaCTPN() {
        return maCTPN;
    }

    public void setMaCTPN(String maCTPN) {
        this.maCTPN = maCTPN;
    }
}
