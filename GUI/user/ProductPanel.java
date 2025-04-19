package GUI.user;
import GUI.giohang.GioHangGUI;
import javax.swing.*;
import BLL.DienThoai_BLL;
import DTO.DienThoai_DTO;
import qlgiohang.oop.Product;
import DTO.GioHang_DTO;
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
    private GioHangGUI gioHangGUI;

    public ProductPanel(String typename, String searchtext, FilterPanel filter, GioHangGUI gioHangGUI) { 
        this.gioHangGUI = gioHangGUI;
        initComponents();
        if (typename.equals("All"))
            getAllSp(filter);
        else if (typename.equals("Search")) {
            getSearchedItem(searchtext, filter);
        } else {
            getAllProductType(typename);
        }
        addEvent();
    }
   /*public ProductPanel(String category, String search, FilterPanel filter, GioHangGUI gioHangGUI) {
    
        for (Model_ProductItem item : productList) {
            ProductItem productItem = new ProductItem(item, gioHangGUI);
            add(productItem);
        }
    }*/

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

    private void getAllSp(FilterPanel filter) {
        DescriptionList = new ArrayList<>();
        ProductList = new ArrayList<>();
        for (DienThoai_DTO dt : dtbll.getAllDienThoai()){
            String id = null;
            String ten = null;
            double gia = 0.0;
            int soLuong = 0;
            String xuatXu = null;
            String trongLuong = null;
            String kichThuocManHinh = null;
            String dungLuong = null;
            String ram = null;
            String thuongHieu = null;
            int baoHanh = 0;
            if (filter == null){
                id = dt.getID_SanPham();
                ten = dt.getTen_SanPham();
                gia = dt.getGia_SanPham();
                soLuong = dtbll.getSoLuongTon(dt.getID_Tonkho());
                xuatXu = dt.getXuatXu();
                trongLuong = dt.getTrongLuong();
                kichThuocManHinh = dt.getKichThuocManHinh();
                dungLuong = dt.getDungLuong();
                ram = dt.getRAM();
                thuongHieu = dtbll.getThuongHieu(dt.getID_NCC());
                baoHanh = dt.getBaoHanh();
                ProductItem item = new ProductItem(new Model_ProductItem(ten, id.toUpperCase(), String.valueOf(new BigDecimal(gia))), gioHangGUI);
                ProductList.add(item);
                ItemPanel.add(item);
    
                Model_Product_Description description = new Model_Product_Description(ten,soLuong, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
                DescriptionList.add(description);
            }
            else{
                DienThoai_DTO dth = filter.Filter(dt);
                if (dth != null){
                    id = dth.getID_SanPham();
                    ten = dth.getTen_SanPham();
                    gia = dth.getGia_SanPham();
                    soLuong = dtbll.getSoLuongTon(dth.getID_Tonkho());
                    xuatXu = dth.getXuatXu();
                    trongLuong = dth.getTrongLuong();
                    kichThuocManHinh = dth.getKichThuocManHinh();
                    dungLuong = dth.getDungLuong();
                    ram = dth.getRAM();
                    thuongHieu = dtbll.getThuongHieu(dth.getID_NCC());
                    baoHanh = dth.getBaoHanh();

                    ProductItem item = new ProductItem(new Model_ProductItem(ten, id, String.valueOf(new BigDecimal(gia))), gioHangGUI);
                    ProductList.add(item);
                    ItemPanel.add(item);
        
                    Model_Product_Description description = new Model_Product_Description(ten,soLuong, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
                    DescriptionList.add(description);
                }
            }
        }
    }

    private void getSearchedItem(String searchtext, FilterPanel Filter){
        DescriptionList = new ArrayList<>();
        ProductList = new ArrayList<>();
        for (DienThoai_DTO dt : dtbll.getAllDienThoai()){
            if(dt.getTen_SanPham().toLowerCase().contains(searchtext.toLowerCase())){
                String id = null;
                String ten = null;
                double gia = 0.0;
                int soLuong = 0;
                String xuatXu = null;
                String trongLuong = null;
                String kichThuocManHinh = null;
                String dungLuong = null;
                String ram = null;
                String thuongHieu = null;
                int baoHanh = 0;
                if(Filter == null){
                    id = dt.getID_SanPham();
                    ten = dt.getTen_SanPham();
                    gia = dt.getGia_SanPham();
                    soLuong = dtbll.getSoLuongTon(dt.getID_Tonkho());
                    xuatXu = dt.getXuatXu();
                    trongLuong = dt.getTrongLuong();
                    kichThuocManHinh = dt.getKichThuocManHinh();
                    dungLuong = dt.getDungLuong();
                    ram = dt.getRAM();
                    thuongHieu = dtbll.getThuongHieu(dt.getID_NCC());
                    baoHanh = dt.getBaoHanh();

                    ProductItem item = new ProductItem(new Model_ProductItem(ten, id, String.valueOf(new BigDecimal(gia))), gioHangGUI);
                    ProductList.add(item);
                    ItemPanel.add(item);

                    Model_Product_Description description = new Model_Product_Description(ten,soLuong, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
                    DescriptionList.add(description);
                }
                else{
                    DienThoai_DTO dth = Filter.Filter(dt);
                    if(dth!= null){
                        id = dth.getID_SanPham();
                        ten = dth.getTen_SanPham();
                        gia = dth.getGia_SanPham();
                        soLuong = dtbll.getSoLuongTon(dth.getID_Tonkho());
                        xuatXu = dth.getXuatXu();
                        trongLuong = dth.getTrongLuong();
                        kichThuocManHinh = dth.getKichThuocManHinh();
                        dungLuong = dth.getDungLuong();
                        ram = dth.getRAM();
                        thuongHieu = dtbll.getThuongHieu(dth.getID_NCC());
                        baoHanh = dth.getBaoHanh();

                        ProductItem item = new ProductItem(new Model_ProductItem(ten, id, String.valueOf(new BigDecimal(gia))), gioHangGUI);
                        ProductList.add(item);
                        ItemPanel.add(item);

                        Model_Product_Description description = new Model_Product_Description(ten,soLuong, xuatXu, trongLuong, kichThuocManHinh, dungLuong, ram, thuongHieu, baoHanh);
                        DescriptionList.add(description);
                    }
                }

                
            }
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

              // Truyền gioHangGUI vào ProductItem
            ProductItem item = new ProductItem(new Model_ProductItem(ten, id.toUpperCase(), String.valueOf(new BigDecimal(gia))), gioHangGUI);
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
        /*for (ProductItem item : ProductList) {
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        GioHangGUI.getInstance().addToCart(
                            item.getID(),
                            item.getName(),
                            item.getPrice(),
                            1
                        );
                        JOptionPane.showMessageDialog(null, "Đã thêm " + item.getName() + " vào giỏ hàng!");
                    }
                }
            });
        }*/
        
    }

    private void initComponents() {
        dtbll = new DienThoai_BLL();
        // ItemPanel = new JPanel(new GridLayout(0, 3, 35, 35));
        ItemPanel = new JPanel(new WrapLayout(WrapLayout.LEADING, 30, 30));
        ItemPanel.setBackground(Color.decode("#cfdef3"));
        ItemPanel.setBorder(BorderFactory.createEmptyBorder(-10, -10, 0, 0));
        ItemPanel.revalidate();
        ItemPanel.repaint();

        setViewportView(ItemPanel);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        setBackground(Color.decode("#cfdef3"));
        getVerticalScrollBar().setUI(new MyScrollBarUI());
        getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setPreferredSize(new Dimension(740, 540));
    }

}

