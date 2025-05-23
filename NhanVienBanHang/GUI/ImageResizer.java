package NhanVienBanHang.GUI;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageResizer {
    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Lấy Image từ ImageIcon
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Thay đổi kích thước
        return new ImageIcon(resizedImage); // Trả về ImageIcon mới
    }
}
