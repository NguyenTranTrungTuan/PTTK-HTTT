package qlkho;

import qlkho.oop.NhanVien;

public class testrun {
    public static void main(String[] args) {
        NhanVien nv = new NhanVien("NV004", "Nguyễn Thị Lan", "Nhân viên", "nguyentl@gmail.com", "0934567890", "nv101",
                "NV001");
        new qlkhoframe(nv);
        // new abc();
    }
}