package DTO;
public class GioHang_DTO {
    private String maDT;
    private String tenDT;
    private double giaBan;
    private int soLuong;

    public GioHang_DTO(String maDT, String tenDT, double giaBan, int soLuong) {
        this.maDT = maDT;
        this.tenDT = tenDT;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public String getMaDT() { return maDT; }
    public String getTenDT() { return tenDT; }
    public double getGiaBan() { return giaBan; }
    public int getSoLuong() { return soLuong; }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return giaBan * soLuong;
    }
}
