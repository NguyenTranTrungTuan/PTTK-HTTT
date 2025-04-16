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

    // Constructor không tham số
    // public DonHang_DTO() {
    //     this.idKhachHang = "";
    //     this.dsSanPhamMua = new ArrayList<>();
    //     this.tongTien = 0.0;
    // }

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


    // // Phương thức thêm sản phẩm vào giỏ hàng
    // public void themSanPham(ChitietHoaDon spMua) {
    //     boolean i = true;
    //     for (ChitietHoaDon hd :dsSanPhamMua ){
    //         if (hd.getThongTinSanPham().getID_SanPham().equals(spMua.getThongTinSanPham().getID_SanPham())){
    //             i = false;
    //             hd.setSoLuongMua(hd.getSoLuongMua() + spMua.getSoLuongMua());
    //             break;
    //         }
    //     }
    //     if (i == true)
    //     dsSanPhamMua.add(spMua);
    //     tinhTongTien();  // Cập nhật lại tổng tiền sau khi thêm sản phẩm
    // }

    // // Phương thức tính tổng tiền của giỏ hàng
    // private void tinhTongTien() {
    //     tongTien = 0.0;
    //     for (ChitietHoaDon spMua : dsSanPhamMua) {
    //         tongTien += spMua.getGiaTien();
    //     }
    // }

    // // Phương thức hiển thị thông tin giỏ hàng
    // public void hienThiThongTinGioHang() {
    //     int i = 1;
    //     System.out.println("Gio hang cua khach hang: " + idKhachHang);
    //     for (ChitietHoaDon spMua : dsSanPhamMua) {
    //         System.out.print(i + ". ");
    //         spMua.hienThiThongTinMua();
    //         i++;
    //     }
    //     System.out.println("Tong tien gio hang: " + tongTien);
    // }

    // @Override
    // public String toString() {
    //     return "GioHang [idKhachHang=" + idKhachHang + ", dsSanPhamMua=" + dsSanPhamMua + ", tongTien=" + tongTien + "]";
    // }

    // // Phương thức xóa sản phẩm khỏi giỏ hàng
    // public void xoaSanPham(int index) {
    //     if (index >= 0 && index < dsSanPhamMua.size()) {
    //         dsSanPhamMua.remove(index);
    //         tinhTongTien(); // Cập nhật lại tổng tiền
    //         System.out.println("Da xoa san pham khoi gio hang.");
    //     } else {
    //         System.out.println("Lua chon khong hop le. Khong the xoa san pham.");
    //     }
    // }

    // // Phương thức tăng số lượng sản phẩm
    // public void tangSoLuongSanPham(int index, int soLuong) {
    //     if (soLuong >= 0) {
    //         ChitietHoaDon spMua = dsSanPhamMua.get(index);
    //         spMua.setSoLuongMua(spMua.getSoLuongMua() + soLuong); // Tăng số lượng
    //         tinhTongTien(); // Cập nhật lại tổng tiền
    //         System.out.println("Da them " + soLuong + " san pham vao gio hang.");
    //     } else {
    //         System.out.println("Lua chon so luong khong hop le.");
    //     }
    // }

    // // Phương thức giảm số lượng sản phẩm
    // public void giamSoLuongSanPham(int index, int soLuong) {
    //     if (soLuong >= 0) {
    //         ChitietHoaDon spMua = dsSanPhamMua.get(index);
    //         spMua.setSoLuongMua(spMua.getSoLuongMua() - soLuong); // giảm số lượng
    //         tinhTongTien(); // Cập nhật lại tổng tiền
    //         System.out.println("Da giam " + soLuong + " so luong san pham da chon.");
    //     } else {
    //         System.out.println("Lua chon so luong khong hop le.");
    //     }
    // }

    // public HoaDon thanhToan(QuanLyHoaDon qlhd) {
    //     if (dsSanPhamMua.isEmpty()) {
            
    //         return null;
    //     }

    //     // Tạo ID hóa đơn duy nhất
    //     String idHoaDon = qlhd.taoID();
    //     Date date = new Date();
    //     DateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
    //     String strdate = dFormat.format(date);
    //     String idNhanVien = "Chua co nhan vien xu ly"; // Chưa có nhân viên xử lý
    //     String TinhTrangDonHang = "Chua xu ly"; // Tình trạng hóa đơn sẽ là chưa xử lý khi mới thanh toán
    //     // Tạo hóa đơn từ thông tin giỏ hàng
    //     HoaDon hoaDon = new HoaDon(idHoaDon, idNhanVien, idKhachHang, dsSanPhamMua, tongTien, strdate, TinhTrangDonHang);

    //     // Xóa giỏ hàng sau khi thanh toán
    //     dsSanPhamMua.clear();
    //     tongTien = 0.0;

    //     System.out.println("Thanh toan thanh cong! Hoa don da duoc tao.");
    //     hoaDon.hienThiHoaDon();

    //     return hoaDon;
    // }
}
