package qlkho.oop;

public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String quocGia;

    public NhaCungCap(String maNCC, String tenNCC, String quocGia) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.quocGia = quocGia;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getQuocGia() {
        return quocGia;
    }
}
