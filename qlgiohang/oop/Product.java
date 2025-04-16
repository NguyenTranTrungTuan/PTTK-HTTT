package qlgiohang.oop;

public class Product {
    private String tenSanPham;
    private double giaMoi;
    private int soLuong;

    public Product(String tenSanPham, double giaMoi, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.giaMoi = giaMoi;
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaMoi() {
        return giaMoi;
    }

    public void setGiaMoi(double giaMoi) {
        this.giaMoi = giaMoi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Product{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", giaMoi=" + giaMoi +
                ", soLuong=" + soLuong +
                '}';
    }
}