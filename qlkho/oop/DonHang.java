package qlkho.oop;

public class DonHang {
    String maDon;
    String maKH;
    String maNV;
    String diaChi;
    String ngayDat;
    String pttt;
    String tinhTrang;
    int tongTien;

    public DonHang(String maDon, String maKH, String maNV, String diaChi, String ngayDat, String pttt, String tinhTrang,
            int tongTien) {
        this.maDon = maDon;
        this.maKH = maKH;
        this.maNV = maNV;
        this.diaChi = diaChi;
        this.ngayDat = ngayDat;
        this.pttt = pttt;
        this.tinhTrang = tinhTrang;
        this.tongTien = tongTien;
    }

    public String getMaDon() {
        return maDon;
    }

    public void setMaDon(String maDon) {
        this.maDon = maDon;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getPttt() {
        return pttt;
    }

    public void setPttt(String pttt) {
        this.pttt = pttt;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
