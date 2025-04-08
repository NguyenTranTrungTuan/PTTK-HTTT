package user;

import javax.swing.*;
import BLL.DienThoai_BLL;
import DTO.DienThoai_DTO;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductPanel extends JScrollPane {
    private JPanel ItemPanel;
    private ArrayList<ProductItem> ProductList;
    private ArrayList<Model_Product_Description> DescriptionList;
    private ProductDescription CurrentDescription;
    protected DienThoai_BLL dtbll;

    public ProductPanel(String typename) { 
        initComponents();
        if(typename.equals("All"))
            getAllSp();
        else{
            getAllProductType(typename);
        }
        addEvent();
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

    private void getAllSp() {
        DescriptionList = new ArrayList<>();
        ProductList = new ArrayList<>();
        for (DienThoai_DTO dt : dtbll.getAllDienThoai()){
            String id = dt.getID_SanPham();
            String ten = dt.getTen_SanPham();
            double gia = dt.getGia_SanPham();
            int soLuong = dtbll.getSoLuongTon(dt.getID_Tonkho());
            String xuatXu = dt.getXuatXu();
            String trongLuong = dt.getTrongLuong();
            String kichThuocManHinh = dt.getKichThuocManHinh();
            String dungLuong = dt.getDungLuong();
            String ram = dt.getRAM();
            String thuongHieu = dtbll.getThuongHieu(dt.getID_NCC());
            int baoHanh = dt.getBaoHanh();

            ProductItem item = new ProductItem(new Model_ProductItem(ten, "LTG", String.valueOf(new BigDecimal(gia))));
            ProductList.add(item);
            ItemPanel.add(item);

            Model_Product_Description description = new Model_Product_Description(ten,soLuong, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
            DescriptionList.add(description);
        }
    }

    private void getAllProductType(String typename){
        DescriptionList = new ArrayList<>();
        ProductList = new ArrayList<>();
        for (DienThoai_DTO dt : dtbll.getAllType(typename)){
            String id = dt.getID_SanPham();
            String ten = dt.getTen_SanPham();
            double gia = dt.getGia_SanPham();
            int soLuong = dtbll.getSoLuongTon(dt.getID_Tonkho());
            String xuatXu = dt.getXuatXu();
            String trongLuong = dt.getTrongLuong();
            String kichThuocManHinh = dt.getKichThuocManHinh();
            String dungLuong = dt.getDungLuong();
            String ram = dt.getRAM();
            String thuongHieu = dtbll.getThuongHieu(dt.getID_NCC());
            int baoHanh = dt.getBaoHanh();

            ProductItem item = new ProductItem(new Model_ProductItem(ten, "LTG", String.valueOf(new BigDecimal(gia))));
            ProductList.add(item);
            ItemPanel.add(item);

            Model_Product_Description description = new Model_Product_Description(ten,soLuong, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
            DescriptionList.add(description);
        }
    }

    private void addEvent(){
        for(ProductItem item:ProductList){
            item.addMouseListener(mouseListener);
        }
    }

    private void initComponents() {
        dtbll = new DienThoai_BLL();
        ItemPanel = new JPanel(new GridLayout(0, 3, 35, 35));
        ItemPanel.setBackground(Color.decode("#cfdef3"));
        ItemPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        ItemPanel.revalidate();
        ItemPanel.repaint();

        setViewportView(ItemPanel);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getVerticalScrollBar().setUI(new MyScrollBarUI());
        getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setPreferredSize(new Dimension(740, 540));
    }
}

