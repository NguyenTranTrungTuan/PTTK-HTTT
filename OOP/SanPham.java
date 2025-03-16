package OOP;

abstract class SanPham{
    private String ID_SanPham;
    private String Ten_SanPham;
    private double Gia_SanPham;
    private int SoLuongTonKho;

    public SanPham(){
        this.ID_SanPham="";
        this.Ten_SanPham="";
        this.Gia_SanPham=0.00;
        this.SoLuongTonKho=0;
    }

    public SanPham(String id, String ten, double gia, int soluong){
        this.ID_SanPham = id;
        this.Ten_SanPham = ten;
        this.Gia_SanPham = gia;
        this.SoLuongTonKho = soluong;
    }

    // Getter và Setter cho ID_SanPham
    public String getID_SanPham() {
        return ID_SanPham;
    }

    public void setID_SanPham(String ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }

    // Getter và Setter cho Ten_SanPham
    public String getTen_SanPham() {
        return Ten_SanPham;
    }

    public void setTen_SanPham(String Ten_SanPham) {
        this.Ten_SanPham = Ten_SanPham;
    }

    // Getter và Setter cho Gia_SanPham
    public double getGia_SanPham() {
        return Gia_SanPham;
    }

    public void setGia_SanPham(double Gia_SanPham) {
        this.Gia_SanPham = Gia_SanPham;
    }

    // Getter và Setter cho SoLuongTonKho
    public int getSoLuongTonKho() {
        return SoLuongTonKho;
    }

    public void setSoLuongTonKho(int SoLuongTonKho) {
        this.SoLuongTonKho = SoLuongTonKho;
    }

    public abstract void hienThiThongTin();

    @Override
    public String toString() {
        return "ID:" + ID_SanPham +
        "\nTen san pham: " + Ten_SanPham + 
        "\nGia san pham: " + Gia_SanPham+ 
        "\nSo luong ton kho:" + SoLuongTonKho;
    }

    
    public abstract String hienThiThongTin1();
    

    
}