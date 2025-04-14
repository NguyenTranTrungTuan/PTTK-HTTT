package qlkho.oop;

public class PhieuNhap {
    private String maHDNhap;
    private String maNCC;
    private int soLoaiDT;
    private String ngayNhap;
    private int tongTien;
    private String maNV;

    public PhieuNhap(String maHDNhap, String maNCC, int soLoaiDT, String ngayNhap, int tongTien, String maNV) {
        this.maHDNhap = maHDNhap;
        this.maNCC = maNCC;
        this.soLoaiDT = soLoaiDT;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.maNV = maNV;
    }

    // Getter and Setter for maHDNhap
    public String getMaHDNhap() {
        return maHDNhap;
    }

    public void setMaHDNhap(String maHDNhap) {
        this.maHDNhap = maHDNhap;
    }

    // Getter and Setter for maNCC
    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    // Getter and Setter for soLoaiDT
    public int getSoLoaiDT() {
        return soLoaiDT;
    }

    public void setSoLoaiDT(int soLoaiDT) {
        this.soLoaiDT = soLoaiDT;
    }

    // Getter and Setter for ngayNhap
    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    // Getter and Setter for tongTien
    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    // Getter and Setter for maNV
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
}