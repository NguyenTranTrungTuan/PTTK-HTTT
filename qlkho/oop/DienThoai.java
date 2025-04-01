package qlkho.oop;

public class DienThoai {
    private String maDt;
    private String tenDt;
    private float giaBan;
    private float giaNhap;
    private String maTon;
    private String xuatXu;
    private float trongLuong;
    private float kichThuocManHinh;
    private int dungLuong;
    private int ram;
    private int baoHanh;
    private String maNcc;

    public DienThoai(String maDt, String tenDt, float giaBan, float giaNhap, String maTon, String xuatXu,
            float trongLuong,
            float kichThuocManHinh, int dungLuong, int ram, int baoHanh, String maNcc) {
        this.maDt = maDt;
        this.tenDt = tenDt;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.maTon = maTon;
        this.xuatXu = xuatXu;
        this.trongLuong = trongLuong;
        this.kichThuocManHinh = kichThuocManHinh;
        this.dungLuong = dungLuong;
        this.ram = ram;
        this.baoHanh = baoHanh;
        this.maNcc = maNcc;
    }

    public String getMaDt() {
        return maDt;
    }

    public void setMaDt(String maDt) {
        this.maDt = maDt;
    }

    public String getTenDt() {
        return tenDt;
    }

    public void setTenDt(String tenDt) {
        this.tenDt = tenDt;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getMaTon() {
        return maTon;
    }

    public void setMaTon(String maTon) {
        this.maTon = maTon;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public float getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(float trongLuong) {
        this.trongLuong = trongLuong;
    }

    public float getKichThuocManHinh() {
        return kichThuocManHinh;
    }

    public void setKichThuocManHinh(float kichThuocManHinh) {
        this.kichThuocManHinh = kichThuocManHinh;
    }

    public int getDungLuong() {
        return dungLuong;
    }

    public void setDungLuong(int dungLuong) {
        this.dungLuong = dungLuong;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String toString() {
        return "DienThoai [baoHanh=" + baoHanh + ", dungLuong=" + dungLuong + ", giaBan=" + giaBan + ", giaNhap="
                + giaNhap
                + ", kichThuocManHinh=" + kichThuocManHinh + ", maDt=" + maDt + ", maNcc=" + maNcc + ", maTon=" + maTon
                + ", ram=" + ram + ", tenDt=" + tenDt + ", trongLuong=" + trongLuong + ", xuatXu=" + xuatXu + "]";
    }
}
