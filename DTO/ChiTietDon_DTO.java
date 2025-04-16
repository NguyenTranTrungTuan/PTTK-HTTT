package DTO;

public class ChiTietDon_DTO {
    private String MaCTDH;
    private DienThoai_DTO thongTinSanPham;
    private int soLuongMua;
    private double thanhtien;
    private String MaCTPnhap;

    
    public ChiTietDon_DTO() {
        this.MaCTDH = "";
        this.thongTinSanPham = null;
        this.soLuongMua = 0;
        this.thanhtien = 0.0;
        this.MaCTPnhap = "";
    }

    public ChiTietDon_DTO(String mactdh, DienThoai_DTO thongTinSanPham, int soLuongMua, double thanhtien, String mactpnhap) {
        this.MaCTDH = mactdh;
        this.thongTinSanPham = thongTinSanPham;
        this.soLuongMua = soLuongMua;
        this.thanhtien = thanhtien;
        this.MaCTPnhap = mactpnhap;
    }

    public String getMaCTDH() {
        return MaCTDH;
    }

    public void setMaCTDH(String maCTDH) {
        MaCTDH = maCTDH;
    }

    public String getMaCTPnhap() {
        return MaCTPnhap;
    }

    public void setMaCTPnhap(String maCTPnhap) {
        MaCTPnhap = maCTPnhap;
    }

    public DienThoai_DTO getThongTinSanPham() {
        return thongTinSanPham;
    }

    public void setThongTinSanPham(DienThoai_DTO thongTinSanPham) {
        this.thongTinSanPham = thongTinSanPham;
    }

    // Getter và Setter cho soLuongMua
    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
        // this.thanhtien = tinhThanhTien();  // Cập nhật lại giá tiền sau khi thay đổi số lượng mua
    }

    // Getter cho giaTien
    public double getThanhTien() {
        return thanhtien;
    }

    // // Phương thức tính giá tiền
    // private double tinhThanhTien() {
    //     return thongTinSanPham != null ? thongTinSanPham.getGia_SanPham() * soLuongMua : 0.0;
    // }
}
