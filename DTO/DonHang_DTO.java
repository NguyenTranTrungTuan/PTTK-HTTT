package DTO;
import java.util.ArrayList;

public class DonHang_DTO {
    private String MaDon;
    private String idKhachHang;
    private String idNhanVien;
    private String DiaChiDat;
    private String NgayDat;
    private String PTTT;
    private String TinhTrangDonHang;
    private ArrayList<ChiTietDon_DTO> dsSanPhamMua;
    private double tongTien;

    public void output() {
        System.out.println("----- Thông Tin Đơn Hàng -----");
        System.out.println("Mã đơn hàng       : " + MaDon);
        System.out.println("Mã khách hàng     : " + idKhachHang);
        System.out.println("Mã nhân viên      : " + idNhanVien);
        System.out.println("Địa chỉ đặt       : " + DiaChiDat);
        System.out.println("Ngày đặt          : " + NgayDat);
        System.out.println("Phương thức TT    : " + PTTT);
        System.out.println("Tình trạng đơn    : " + TinhTrangDonHang);
        System.out.println("Tổng tiền         : " + tongTien);
        System.out.println("Danh sách sản phẩm mua:");
        
        if (dsSanPhamMua != null && !dsSanPhamMua.isEmpty()) {
            for (int i = 0; i < dsSanPhamMua.size(); i++) {
                System.out.println("  Sản phẩm " + (i + 1) + ":");
                dsSanPhamMua.get(i).output(); // Gọi phương thức output của ChiTietDon_DTO
            }
        } else {
            System.out.println("  Không có sản phẩm nào trong đơn.");
        }
    
        System.out.println("--------------------------------");
    }

    // Constructor không tham số
    public DonHang_DTO() {
        this.MaDon = "";
        this.idKhachHang = "";
        this.idNhanVien = "";
        this.DiaChiDat = "";
        this.NgayDat = "";
        this.PTTT = "";
        this.TinhTrangDonHang = "";
        this.dsSanPhamMua = new ArrayList<>();
        this.tongTien = 0.0;
    }
    

    // Constructor có tham số
    public DonHang_DTO(String madon, String makh, String manv, String Diachi, String Date, String pttt, String tinhtrang, ArrayList<ChiTietDon_DTO> ctdh, Double tongtien) {
        MaDon = madon;
        idKhachHang = makh;
        idNhanVien = manv;
        DiaChiDat = Diachi;
        NgayDat = Date;
        PTTT = pttt;
        TinhTrangDonHang = tinhtrang;
        dsSanPhamMua = ctdh;
        tongTien = tongtien;
    }

    public String getMaDon() {
        return MaDon;
    }

    public void setMaDon(String maDon) {
        MaDon = maDon;
    }

    // Getter và Setter cho idKhachHang
    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    // Getter cho dsSanPhamMua
    public ArrayList<ChiTietDon_DTO> getDsSanPhamMua() {
        return dsSanPhamMua;
    }

    // Getter cho tongTien
    public double getTongTien() {
        return tongTien;
    }

    public String getDiaChiDat() {
        return DiaChiDat;
    }

    public void setDiaChiDat(String diaChiDat) {
        DiaChiDat = diaChiDat;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String pTTT) {
        PTTT = pTTT;
    }

    public String getTinhTrangDonHang() {
        return TinhTrangDonHang;
    }

    public void setTinhTrangDonHang(String tinhTrangDonHang) {
        TinhTrangDonHang = tinhTrangDonHang;
    }

}
