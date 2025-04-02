package user;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ProductPanel extends JScrollPane {
    private JPanel ItemPanel;
    private ArrayList<ProductItem> ProductList;
    private ArrayList<Model_Product_Description> DescriptionList;
    private ProductDescription CurrentDescription;

    public ProductPanel() { 
        initComponents();
    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(CurrentDescription != null){
                CurrentDescription.dispose();
            }
            ProductItem item = (ProductItem) e.getSource();
            for(Model_Product_Description description:DescriptionList){
                if (description.getname().equals(item.getName())) {
                    CurrentDescription = new ProductDescription(description);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };

    private Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/pttkhttt", "root", "123456789");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void getAllSp() {
        DescriptionList = new ArrayList<>();
        try (Connection con = connectDB()) {
            if (con != null) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM DIENTHOAI " +
                    "JOIN KHO ON DIENTHOAI.MaTon = KHO.MaTon " +
                    "JOIN NHACUNGCAP ON DIENTHOAI.MaNCC = NHACUNGCAP.MaNCC"
                );
                while (rs.next()) {
                    String id = rs.getString("MaDT");
                    String ten = rs.getString("TenDT");
                    double gia = rs.getDouble("GiaBan");
                    int soLuong = rs.getInt("SoLuongTon");
                    String xuatXu = rs.getString("XuatXu");
                    String trongLuong = rs.getString("TrongLuong");
                    String kichThuocManHinh = rs.getString("KichThuocManHinh");
                    String dungLuong = rs.getString("DungLuong");
                    String ram = rs.getString("RAM");
                    String thuongHieu = rs.getString("TenNCC");
                    int baoHanh = rs.getInt("BaoHanh");

                    Model_Product_Description description = new Model_Product_Description(ten,soLuong, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
                    DescriptionList.add(description);

                    // Add to ProductList as well
                    ProductItem item = new ProductItem(new Model_ProductItem(ten, "LTG", String.valueOf(new BigDecimal(gia))));
                    ProductList.add(item);
                    ItemPanel.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addEvent(){
        for(ProductItem item:ProductList){
            item.addMouseListener(mouseListener);
        }
    }

    private void initComponents() {
        ProductList = new ArrayList<>();
        ItemPanel = new JPanel(new GridLayout(0, 3, 35, 35));
        ItemPanel.setBackground(Color.decode("#cfdef3"));
        ItemPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));

        getAllSp(); // Fetch and populate items from DB
        ItemPanel.revalidate();
        ItemPanel.repaint();
        addEvent();

        setViewportView(ItemPanel);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getVerticalScrollBar().setUI(new MyScrollBarUI());
        getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setPreferredSize(new Dimension(740, 540));
    }
}

