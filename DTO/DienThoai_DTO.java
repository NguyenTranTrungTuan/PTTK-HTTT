package DTO;

public class DienThoai_DTO{
    private String ID_SanPham;
    private String Ten_SanPham;
    private double Gia_SanPham;
    private double GiaNhap;
    private String ID_Tonkho;
    private String XuatXu;
    private String TrongLuong;
    private String KichThuocManHinh;
    private String DungLuong;
    private String RAM;
    private String ID_NCC;
    private int BaoHanh;

    public DienThoai_DTO(){
        ID_SanPham = "";
        Ten_SanPham = "";
        Gia_SanPham = 0.0;
        GiaNhap = 0.0;
        ID_Tonkho = "";
        XuatXu = "";
        TrongLuong = "";
        KichThuocManHinh = "";
        DungLuong = "";
        RAM = "";
        ID_NCC = "";
        BaoHanh = 0;
    }

    public DienThoai_DTO(String id, String ten, double gia, double gianhap, String idtonkho, String xuatXu, String trongLuong,
            String kichThuocManHinh, String dungLuong, String RAM, String idncc, int baoHanh) {
        this.ID_SanPham = id;
        this.Ten_SanPham = ten;
        this.Gia_SanPham = gia;
        this.GiaNhap = gianhap;
        this.ID_Tonkho = idtonkho;
        this.XuatXu = xuatXu;
        this.TrongLuong = trongLuong;
        this.KichThuocManHinh = kichThuocManHinh;
        this.DungLuong = dungLuong;
        this.RAM = RAM;
        this.ID_NCC = idncc;
        this.BaoHanh = baoHanh;
    }

    public String getID_NCC() {
        return ID_NCC;
    }

    public void setID_NCC(String iD_NCC) {
        ID_NCC = iD_NCC;
    }

    public String getID_Tonkho() {
        return ID_Tonkho;
    }

    public void setID_Tonkho(String iD_Tonkho) {
        ID_Tonkho = iD_Tonkho;
    }

    public double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        GiaNhap = giaNhap;
    }

    public String getID_SanPham() {
        return ID_SanPham;
    }

    public void setID_SanPham(String iD_SanPham) {
        ID_SanPham = iD_SanPham;
    }

    public String getTen_SanPham() {
        return Ten_SanPham;
    }

    public void setTen_SanPham(String ten_SanPham) {
        Ten_SanPham = ten_SanPham;
    }

    public double getGia_SanPham() {
        return Gia_SanPham;
    }

    public void setGia_SanPham(double gia_SanPham) {
        Gia_SanPham = gia_SanPham;
    }

    // Getter và Setter cho XuatXu
    public String getXuatXu() {
        return XuatXu;
    }

    public void setXuatXu(String xuatXu) {
        XuatXu = xuatXu;
    }

    // Getter và Setter cho TrongLuong
    public String getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(String trongLuong) {
        TrongLuong = trongLuong;
    }

    // Getter và Setter cho KichThuocManHinh
    public String getKichThuocManHinh() {
        return KichThuocManHinh;
    }

    public void setKichThuocManHinh(String kichThuocManHinh) {
        KichThuocManHinh = kichThuocManHinh;
    }

    // Getter và Setter cho DungLuong
    public String getDungLuong() {
        return DungLuong;
    }

    public void setDungLuong(String dungLuong) {
        DungLuong = dungLuong;
    }

    // Getter và Setter cho RAM
    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    // Getter và Setter cho BaoHanh
    public int getBaoHanh() {
        return BaoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        BaoHanh = baoHanh;
    }
}
