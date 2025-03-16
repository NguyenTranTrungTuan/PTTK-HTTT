package OOP;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLySanPham implements QuanLyFile{
    private ArrayList<SanPham> danhSachSanPham;
    private static int dtCounter = 0;


    public QuanLySanPham(){
        danhSachSanPham = new ArrayList<SanPham>();
    }

    public ArrayList<SanPham> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    private String taoID(String loai){
        String prefix;
        int counter;

        switch (loai) {
            case "DienThoai":
                prefix = "DT";
                counter = dtCounter++;
                break;
            default:
                throw new IllegalArgumentException("Loai san pham khong hop le.");
        }
        
        // Định dạng ID với prefix và 8 chữ số từ counter
        return String.format("%s%08d", prefix, counter);
    }

    @Override
    public void layDuLieuTuFile() {

        // Đọc dữ liệu từ file DienThoai.txt
        try (BufferedReader br = new BufferedReader(new FileReader("./file/DienThoai.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 11) {
                    String id = parts[0];
                    String ten = parts[1];
                    Double gia = Double.parseDouble(parts[2]);
                    int soLuong = Integer.parseInt(parts[3]);
                    String XuatXu = parts[4];
                    String TrongLuong = parts[5];
                    String KichThuocManHinh = parts[6];
                    String DungLuong = parts[7];
                    String RAM = parts[8];
                    String ThuongHieu = parts[9];
                    int BaoHanh = Integer.parseInt(parts[10]);
                    DienThoai dt = new DienThoai(id, ten, gia, soLuong, XuatXu, TrongLuong, KichThuocManHinh, DungLuong, RAM, ThuongHieu, BaoHanh);
                    danhSachSanPham.add(dt);

                    int idNumber = Integer.parseInt(id.substring(2));
                    if (idNumber >= dtCounter) {
                        dtCounter = idNumber + 1;
                    }
                } else {
                    System.err.println("Du lieu khong hop le o dong: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Loi khi doc file DienThoai.txt: " + e.getMessage());
        }
    }

    public void menu(Scanner scanner){
        while (true) {
            System.out.println("=============================================");
            System.out.println("1. Them moi san pham");
            System.out.println("2. Xoa san pham");
            System.out.println("3. Tim kiem san pham");
            System.out.println("4. Hien thi danh sach san pham");
            System.out.println("5. Sua san pham");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Nhap lua chon cua ban: ");
            int luaChon = scanner.nextInt();
            scanner.nextLine();
            System.out.println("=============================================");
            switch (luaChon) {
                case 1:
                    themMoiSanPham(scanner);
                    break;
                case 2:
                    xoaSanPham(scanner);
                    luuDuLieuVaoFile();
                    break;
                case 3:
                    // timKiemSanPhamTheoID(scanner);
                    timKiemSanPhamTheoThuocTinh(scanner);
                    break;
                case 4:
                    hienThiDanhSachSanPham();
                    break;
                case 5:
                    suaSanPham(scanner);
                    luuDuLieuVaoFile();
                    break;
                case 0:
                    System.out.println("Chuong trinh ket thuc.");
                    luuDuLieuVaoFile();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    break;
            }
        }
    }

    //  Them moi san pham
    public void themMoiSanPham(Scanner scanner) {
        while (true) {
            System.out.println("1. Them Dien Thoai."); // Thêm lựa chọn cho điện thoại
            System.out.println("0. Thoat.");
            System.out.print("Nhap lua chon cua ban: ");
            int luaChon = scanner.nextInt();
            scanner.nextLine();
    
            String tenSanPham;
            int giaSanPham = 0; // khai báo int để cho vào hàm kiểm tra
            int soLuongSanPham = 0;
            String idSanPham;
    
            switch (luaChon) {
                case 1:
                    System.out.print("Nhap ten san pham: ");
                    tenSanPham = scanner.nextLine();
                    System.out.print("Nhap gia san pham: ");
                    giaSanPham = QuanLyNhanVien.kiemtraSoAm(giaSanPham, scanner);

                    System.out.print("Nhap so luong san pham: ");
                    soLuongSanPham = QuanLyNhanVien.kiemtraSoAm(soLuongSanPham, scanner);

                    System.out.print("Nhap xuat xu: ");
                    String xuatXu = scanner.nextLine();
                    System.out.print("Nhap trong luong (g): ");
                    String trongLuong = scanner.nextLine();
                    System.out.print("Nhap kich thuoc man hinh (inch): ");
                    String kichThuocManHinh = scanner.nextLine();
                    System.out.print("Nhap dung luong: ");
                    String dungLuong = scanner.nextLine();
                    System.out.print("Nhap RAM: ");
                    String ram = scanner.nextLine();
                    System.out.print("Nhap thuong hieu: ");
                    String thuongHieu = scanner.nextLine();
                    System.out.print("Nhap thoi gian bao hanh (thang): ");
                    int baoHanh = scanner.nextInt();
                    scanner.nextLine();

                    idSanPham = taoID("DienThoai");

                    SanPham dienThoai = new DienThoai(idSanPham, tenSanPham, giaSanPham, soLuongSanPham, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
                    danhSachSanPham.add(dienThoai);
                    break;
                    
                case 0:
                    System.out.println("<<<Da thoat>>>");
                    luuDuLieuVaoFile();
                    return;
                default:
                    System.out.println("Chon sai, xin chon lai");
                    break;
            }
        }
    }
    

    // Tìm kiêms sản phẩm theo ID
    public void timKiemSanPhamTheoID(Scanner scanner){
        System.out.print("Nhap id san pham: ");
        String id = scanner.nextLine();

        boolean timThay = false;

        for (SanPham sp : danhSachSanPham) {
            if (sp.getID_SanPham().equalsIgnoreCase(id)) {
                sp.hienThiThongTin();
                timThay = true;
                break; 
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay san pham co id: " + id);
        }
    }

    public void timKiemSanPhamTheoThuocTinh(Scanner scanner) {
        System.out.println("Chon thuoc tinh dien thoai muon tim:");
        System.out.println("1. Ten dien thoai.");
        System.out.println("2. Xuat xu.");
        System.out.println("3. Trong luong.");
        System.out.println("4. Kich thuoc man hinh.");
        System.out.println("5. Dung luong.");
        System.out.println("6. RAM.");
        System.out.println("7. Thuong hieu.");
        System.out.println("8. Bao hanh.");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhap gia tri tim kiem: ");
        String giaTri = scanner.nextLine();

        boolean timThay = false;
        for (SanPham sp : danhSachSanPham) {
            if (sp instanceof DienThoai) {
                DienThoai dt = (DienThoai) sp;
                boolean found = false;
                switch (luaChon) {
                    case 1:
                        found = dt.getTen_SanPham().contains(giaTri);
                        break;
                    case 2:
                        found = dt.getXuatXu().contains(giaTri);
                        break;
                    case 3:
                        found = String.valueOf(dt.getTrongLuong()).contains(giaTri);
                        break;
                    case 4:
                        found = String.valueOf(dt.getKichThuocManHinh()).contains(giaTri);
                        break;
                    case 5:
                        found = dt.getDungLuong().contains(giaTri);
                        break;
                    case 6:
                        found = dt.getRAM().contains(giaTri);
                        break;
                    case 7:
                        found = dt.getThuongHieu().contains(giaTri);
                        break;
                    case 8:
                        found = String.valueOf(dt.getBaoHanh()).contains(giaTri);
                        break;
                    default:
                        System.out.println("Lua chon khong hop le.");
                        return;
                }
                if (found) {
                    System.out.println("\n");
                    dt.hienThiThongTin();
                    System.out.println("\n");
                    timThay = true;
                }
            }
        }
        if (!timThay) {
            System.out.println("\nKhong tim thay san pham\n");
        }
    }

    // xoa san pham
    public void xoaSanPham(Scanner scanner) {
        System.out.print("Nhap ID san pham can xoa: ");
        String idSanPham = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < danhSachSanPham.size(); i++){
            if (danhSachSanPham.get(i).getID_SanPham().equals(idSanPham)){
                found = true;  
                danhSachSanPham.remove(i);
                break;
            }
            
        }
        if(!found)
        {
            System.out.println("Khong tim thay san pham.");
        }
        else{
            System.out.println("Da xoa san pham.");
        }
        luuDuLieuVaoFile();
    }

    public void suaSanPham(Scanner scanner) {
        System.out.print("Nhap ID san pham can sua: ");
        String idSanPham = scanner.nextLine();
    
        // Tìm sản phẩm theo ID
        SanPham sanPham = null;
        for (SanPham sp : danhSachSanPham) {
            if (sp.getID_SanPham().equals(idSanPham)) {
                sanPham = sp;
                break;
            }
        }
    
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham voi ID: " + idSanPham);
            return;
        }
    
        sanPham.hienThiThongTin();
        System.out.println("Bat dau sua thong tin san pham...");
        
        if (sanPham instanceof DienThoai){
            DienThoai dt = (DienThoai) sanPham;
            suaChiTietSanPham(dt, scanner);
        }
    
        System.out.println("Da cap nhat thong tin san pham thanh cong!");
    }

    public void hienThiDanhSachSanPham() {
        int i = 1;
        for (SanPham sp : danhSachSanPham) {
            System.out.print(i + ". \n");
            sp.hienThiThongTin();
            i++;
        }
    }

    @Override
    public void luuDuLieuVaoFile() {
        try {

            // Ghi dữ liệu vào file DienThoai.txt
            BufferedWriter dtWriter = new BufferedWriter(new FileWriter("./file/DienThoai.txt"));
            for (SanPham sp : danhSachSanPham) {
                if (sp instanceof DienThoai) {
                    DienThoai dt = (DienThoai) sp;
                    dtWriter.write(dt.getID_SanPham() + ", " + 
                                dt.getTen_SanPham() + ", " + 
                                dt.getGia_SanPham() + ", " + 
                                dt.getSoLuongTonKho() + ", " + 
                                dt.getXuatXu() + ", " + 
                                dt.getTrongLuong() + ", " + 
                                dt.getKichThuocManHinh() + ", " + 
                                dt.getDungLuong() + ", " + 
                                dt.getRAM() + ", " + 
                                dt.getThuongHieu() + ", " + 
                                dt.getBaoHanh());
                    dtWriter.newLine();
                }
            }
            dtWriter.close();

        } catch (IOException e) {
            System.err.println("Loi khi luu du lieu vao file: " + e.getMessage());
        }
    }

    // Phương thức lấy sản phẩm theo chỉ số
    public SanPham get(int index) {
        return danhSachSanPham.get(index);
    }

    public SanPham sanPham(String idSanPham){
        SanPham sp = null;
        
        for (SanPham sP : danhSachSanPham) {
            if (sP.getID_SanPham().equals(idSanPham)) {
                sp = sP;
                break;
            }
        }
        if (sp instanceof Sach){
            Sach sPS = (Sach) sp;
            return sPS;
        }else if (sp instanceof Vo){
            Vo sPV = (Vo) sp;
            return sPV;
        }
        But sPB = (But) sp;
        return sPB;
    }

     // Phương thức sửa thông tin sản phẩm
     public void suaChiTietSanPham(DienThoai sp, Scanner scanner) {
        while (true) {
            System.out.println("============================================");
            System.out.println("1. Ten san pham.");
            System.out.println("2. Xuat xu.");
            System.out.println("3. Trong luong.");
            System.out.println("4. Kich thuoc man hinh.");
            System.out.println("5. Dung luong.");
            System.out.println("6. RAM.");
            System.out.println("7. Thuong hieu.");
            System.out.println("8. Bao hanh.");
            System.out.println("0. Luu lai va thoat.");
            System.out.print("Nhap tuy chon: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("============================================");

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten san pham moi: ");
                    sp.setTen_SanPham(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Nhap xuat xu moi: ");
                    sp.setXuatXu(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Nhap trong luong moi: ");
                    sp.setTrongLuong(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Nhap kich thuoc man hinh moi: ");
                    sp.setKichThuocManHinh(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Nhap dung luong moi: ");
                    sp.setDungLuong(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Nhap RAM moi: ");
                    sp.setRAM(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Nhap thuong hieu moi: ");
                    sp.setThuongHieu(scanner.nextLine());
                    break;
                case 8:
                    System.out.print("Nhap bao hanh moi: ");
                    sp.setBaoHanh(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            }
        }
    }
    // Phương thức lấy kích thước của danh sách sản phẩm
    public int size() {
        return danhSachSanPham.size();
    }
    
}
