package GUI.user;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.math.BigDecimal;

public class Model_ProductItem {

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the Image
     */
    public String getImageicon() {
        return Imageicon;
    }

    /**
     * @param Image the Image to set
     */
    public void setImageicon(String Image) {
        this.Imageicon = Image;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = Math.max(1, amount); // Đảm bảo số lượng tối thiểu là 1
    }

    // Constructor mặc định
    public Model_ProductItem() {
        this.amount = 1; // Số lượng mặc định là 1
    }

    // Constructor có tham số
    public Model_ProductItem(String Title, String Image, BigDecimal price, int amount) {
        this.Title = Title;
        this.Imageicon = Image;
        this.price = price;
        this.amount = Math.max(1, amount); // Đảm bảo số lượng tối thiểu là 1
        
    }

    private String Title;
    private String Imageicon;
    private BigDecimal price;
    private int amount;

    public Icon toImage() {
        ImageIcon image = new ImageIcon("GUI/user/ProductImage/" + Imageicon + ".png");
        Image og = image.getImage();
        int w = 140;
        int h = 180;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xscale = (double) w / iw;
        double yscale = (double) h / ih;
        double scale = Math.max(xscale, yscale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        Image scaledImage = og.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }
}