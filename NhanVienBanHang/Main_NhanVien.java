
package NhanVienBanHang;

import NhanVienBanHang.GUI.NhanVienFrame_GUI;
import NhanVienBanHang.Model.NhanVien;
import javax.swing.SwingUtilities;


public class Main_NhanVien {

    
    public static void main(String[] args) {
        // Tạo một đối tượng NhanVien với thông tin 
        // MaNV	TenNV	ChucVu	TTLienLac	Username	Passwordnv	MaNQL
        //NV001	Nguyễn Văn An	Nhân viên bán hàng	an_nv001@gmail.com	an_nv001	NVBH001	NV010
        NhanVien nv = new NhanVien("NV001", "Nguyễn Văn An", "Nhân viên bán hàng", "an_nv001@gmail.com", "an_nv001", "123456", "NV010");
        SwingUtilities.invokeLater(() -> new NhanVienFrame_GUI(nv));
        
    }
}
