package GUI.giohang;

import GUI.user.Model_ProductItem;

public class Product {
    private String tenSanPham;
    private double giaMoi;
    private int soLuong;

    public Product(String tenSanPham, double giaMoi, int soLuong) {
        if (giaMoi < 0) {
            throw new IllegalArgumentException("Giá không thể nhỏ hơn 0");
        }
        if (soLuong < 0) {
            throw new IllegalArgumentException("Số lượng không thể nhỏ hơn 0");
        }
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
        if (giaMoi < 0) {
            throw new IllegalArgumentException("Giá không thể nhỏ hơn 0");
        }
        this.giaMoi = giaMoi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("Số lượng không thể nhỏ hơn 0");
        }
        this.soLuong = soLuong;
    }
}