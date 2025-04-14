package qlkho.oop;

public class ChiTietPhieuNhap {
    private String maCTPnhap;
    private String maHDNhap;
    private int donGia;
    private int thanhTien;
    private int soLuongNhap;
    private String maDT;

    // Constructor đầy đủ tham số
    public ChiTietPhieuNhap(String maCTPnhap, String maHDNhap, int donGia, int thanhTien, int soLuongNhap,
            String maDT) {
        this.maCTPnhap = maCTPnhap;
        this.maHDNhap = maHDNhap;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.soLuongNhap = soLuongNhap;
        this.maDT = maDT;
    }

    // Getter và Setter cho từng thuộc tính
    public String getMaCTPnhap() {
        return maCTPnhap;
    }

    public void setMaCTPnhap(String maCTPnhap) {
        this.maCTPnhap = maCTPnhap;
    }

    public String getMaHDNhap() {
        return maHDNhap;
    }

    public void setMaHDNhap(String maHDNhap) {
        this.maHDNhap = maHDNhap;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }
}