package DTO;

public class ChiTietDon_DTO {
    private DienThoai_DTO thongTinSanPham;
    private int soLuongMua;
    private double thanhtien;
    private String idDonHang;

    
    public ChiTietDon_DTO() {
        this.thongTinSanPham = null;
        this.soLuongMua = 0;
        this.thanhtien = 0.0;
        this.idDonHang = "";
    }

    public ChiTietDon_DTO(DienThoai_DTO thongTinSanPham, int soLuongMua, double thanhtien, String idDH) {
        this.thongTinSanPham = thongTinSanPham;
        this.soLuongMua = soLuongMua;
        this.thanhtien = thanhtien;
        this.idDonHang = idDH;
    }

    public String getIdDonHang() {
        return idDonHang;
    }
    
    public void setIdDonHang(String idDonHang) {
        this.idDonHang = idDonHang;
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
