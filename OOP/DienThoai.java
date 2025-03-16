package OOP;

public class DienThoai extends SanPham {
    private String XuatXu;
    private String TrongLuong;
    private String KichThuocManHinh;
    private String DungLuong;
    private String RAM;
    private String ThuongHieu;
    private int BaoHanh;

    public DienThoai(String xuatXu, String trongLuong, String kichThuocManHinh, String dungLuong, String RAM, String thuongHieu, int baoHanh) {
        this.XuatXu = xuatXu;
        this.TrongLuong = trongLuong;
        this.KichThuocManHinh = kichThuocManHinh;
        this.DungLuong = dungLuong;
        this.RAM = RAM;
        this.ThuongHieu = thuongHieu;
        this.BaoHanh = baoHanh;
    }

    public DienThoai(String id, String ten, double gia, int soluong, String xuatXu, String trongLuong, String kichThuocManHinh, String dungLuong, String RAM, String thuongHieu, int baoHanh) {
        super(id, ten, gia, soluong);
        this.XuatXu = xuatXu;
        this.TrongLuong = trongLuong;
        this.KichThuocManHinh = kichThuocManHinh;
        this.DungLuong = dungLuong;
        this.RAM = RAM;
        this.ThuongHieu = thuongHieu;
        this.BaoHanh = baoHanh;
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

    // Getter và Setter cho ThuongHieu
    public String getThuongHieu() {
        return ThuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        ThuongHieu = thuongHieu;
    }

    // Getter và Setter cho BaoHanh
    public int getBaoHanh() {
        return BaoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        BaoHanh = baoHanh;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println(super.toString() + "\n" + this.toString());
    }

    public String hienThiThongTin1(){
        return super.getTen_SanPham() + "\n" + super.getGia_SanPham() + "\n" + this.toString();
    }

    @Override
    public String toString() {
        return "Xuat Xu: " + XuatXu + 
                "\nTrong luong: " + TrongLuong + 
                "\nKich thuoc man hinh: " + KichThuocManHinh + 
                "\nDung luong: "+ DungLuong + 
                "\nRAM: " + RAM + 
                "\nThuong hieu: "+ ThuongHieu + 
                "\nBao hanh: "+ BaoHanh;
    }
}
