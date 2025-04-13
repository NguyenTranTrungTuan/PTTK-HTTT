package NhanVienBanHang.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;


import NhanVienBanHang.DAO.DonHang_DAO;
import NhanVienBanHang.DAO.ChiTietDonHang_DAO;
import java.awt.Component;
import java.awt.FlowLayout;

public class NhanVienFrame2 extends JFrame implements MouseListener,ActionListener {

    private JButton activeButton = null;
    JPanel panel_bottomright, panel_topright, panel_right, panel_left, panel_left_top, panel_left_bottom;

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
     public class CustomScrollBarUI extends BasicScrollBarUI {

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(130, 130, 130, 100));
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 5, 5); 
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(51, 51, 51)); // Màu nền của thanh cuộn
            g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            g2.dispose();
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createInvisibleButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createInvisibleButton();
        }

        private JButton createInvisibleButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }
    }
  
    public NhanVienFrame2() {
        
        setTitle("Nhan Vien Ban Hang");
        setSize(1690, 1040);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 


        panel_left = new JPanel();
        panel_left.setPreferredSize(new Dimension(300, 0));
        panel_left.setBackground(Color.black);
        add(panel_left, BorderLayout.WEST);
        panel_left.setLayout(null);
        panel_left_top = new JPanel();
        panel_left_top.setLayout(null);
        panel_left_top.setBounds(10, 10, 250, 150);
        panel_left_top.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_top);
        JLabel imageAvatar = new JLabel();
        ImageIcon icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\user.png");
        ImageIcon resizedIcon = ImageResizer.resizeImageIcon(icon, 50, 50);
        imageAvatar.setIcon(resizedIcon);
        imageAvatar.setBounds(40, 10, 90, 80);
        panel_left_top.add(imageAvatar);
        JLabel labelName = new JLabel("Nguyễn Văn A");
        labelName.setBounds(110, 30, 100, 20);
        labelName.setForeground(Color.white);
        panel_left_top.add(labelName);
        JLabel labelRole = new JLabel();
        labelRole.setBounds(110, 50, 100, 30);
        labelRole.setText("<html>Nhân viên</html>");
        labelRole.setForeground(Color.white);
        panel_left_top.add(labelRole);


        panel_left_bottom = new JPanel();
        panel_left_bottom.setLayout(new BoxLayout(panel_left_bottom, BoxLayout.Y_AXIS));
        panel_left_bottom.setBounds(10, 170, 250, 600);
        panel_left_bottom.setBackground(new Color(51, 51, 51));
        panel_left.add(panel_left_bottom);


        // Tạo một nút với icon và viền bo tròn
        JButton btnXemDonHang = new JButton("Đơn Hàng");
        btnXemDonHang.setBackground(new Color(51, 51, 51)); // Màu nền
        btnXemDonHang.setForeground(Color.WHITE); // Màu chữ
        btnXemDonHang.setFocusPainted(false); // Loại bỏ viền khi nhấn
        btnXemDonHang.setFont(new Font("Arial", Font.PLAIN, 16)); // Font chữ
        btnXemDonHang.setHorizontalAlignment(SwingConstants.LEFT); // Căn chữ sang trái


        // Tạo viền bo tròn
        btnXemDonHang.setBorder(new RoundedBorder(10)); // Viền bo tròn với bán kính 20px

        // Thêm icon vào nút
        ImageIcon donhang_icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\checklist.png");
        donhang_icon = ImageResizer.resizeImageIcon(donhang_icon, 20, 20); // Thay đổi kích thước icon
        btnXemDonHang.setIcon(donhang_icon); // Đặt icon cho nút

        // Thêm hiệu ứng hover
        addHoverEffect(btnXemDonHang, new Color(70, 70, 70), new Color(51, 51, 51));

        // Thêm khoảng cách trước các nút
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 10))); // Thêm khoảng cách 10px trước các nút


        // Thêm nút vào panel_left_bottom
        panel_left_bottom.add(btnXemDonHang);

        
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách giữa các nút
        
        //Tao button xem chi tiet don hang
        JButton btnXemChiTietDonHang = new JButton("Chi Tiết Đơn Hàng");
        btnXemChiTietDonHang.setBackground(new Color(51, 51, 51)); // Màu nền
        btnXemChiTietDonHang.setForeground(Color.WHITE); // Màu chữ
        btnXemChiTietDonHang.setFocusPainted(false); // Loại bỏ viền khi nhấn
        btnXemChiTietDonHang.setFont(new Font("Arial", Font.PLAIN, 16)); // Font chữ
        btnXemChiTietDonHang.setHorizontalAlignment(SwingConstants.LEFT); // Căn chữ sang trái
        btnXemChiTietDonHang.setBorder(new RoundedBorder(10)); // Viền bo tròn với bán kính 20px
        ImageIcon chitietdonhang_icon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\document.png");
        chitietdonhang_icon = ImageResizer.resizeImageIcon(chitietdonhang_icon, 20, 20); // Thay đổi kích thước icon
        btnXemChiTietDonHang.setIcon(chitietdonhang_icon); // Đặt icon cho nút
        addHoverEffect(btnXemChiTietDonHang, new Color(70, 70, 70), new Color(51, 51, 51)); // Thêm hiệu ứng hover
        panel_left_bottom.add(btnXemChiTietDonHang); // Thêm nút vào panel_left_bottom

        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách giữa các nút


         // Tạo nút Báo Cáo
        JButton btnBaoCao = new JButton("Báo Cáo");
        btnBaoCao.setBackground(new Color(51, 51, 51)); // Màu nền
        btnBaoCao.setForeground(Color.WHITE); // Màu chữ
        btnBaoCao.setFocusPainted(false); // Loại bỏ viền khi nhấn
        btnBaoCao.setFont(new Font("Arial", Font.PLAIN, 16)); // Font chữ
        btnBaoCao.setHorizontalAlignment(SwingConstants.LEFT); // Căn chữ sang trái

        // Tạo viền bo tròn
        btnBaoCao.setBorder(new RoundedBorder(10)); // Viền bo tròn với bán kính 10px

        // Thêm icon vào nút Báo Cáo
        ImageIcon baoCaoIcon = new ImageIcon("E:\\Lap trinh Java\\QuanLyBanDienThoai\\NhanVienBanHang\\Icon\\report.png");
        baoCaoIcon = ImageResizer.resizeImageIcon(baoCaoIcon, 20, 20); // Thay đổi kích thước icon
        btnBaoCao.setIcon(baoCaoIcon); // Đặt icon cho nút

        // Thêm hiệu ứng hover
        addHoverEffect(btnBaoCao, new Color(70, 70, 70), new Color(51, 51, 51));

        // Thêm nút vào panel_left_bottom
        panel_left_bottom.add(btnBaoCao);

            // Thêm sự kiện cho nút Đơn Hàng
            btnXemDonHang.addActionListener(e -> {
            handleButtonClick(btnXemDonHang);
        });

        // Thêm sự kiện cho nút Báo Cáo
        btnBaoCao.addActionListener(e -> {
            handleButtonClick(btnBaoCao);
        });


            btnXemChiTietDonHang.addActionListener(e -> {
                handleButtonClick(btnXemChiTietDonHang);
            });



                // Thêm khoảng cách giữa hai phần
        panel_left_bottom.add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách giữa hai phần
            
        // Tạo panel_right để hiển thị thông tin các đơn hàng
        panel_right = new JPanel();
        panel_right.setBackground(Color.BLACK);
        panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout để sắp xếp theo chiều dọc
        panel_right.setPreferredSize(new Dimension(1470, 0));
        add(panel_right, BorderLayout.CENTER);

        // Thêm khoảng cách 10 pixel trước panel_topright
        panel_right.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách 10 pixel


        // Tạo panel_topright
        panel_topright = new JPanel();
        panel_topright.setBackground(new Color(51, 51, 51));
        panel_topright.setLayout(new GridLayout(4, 2, 10, 10)); // 4 hàng, 2 cột, khoảng cách 10px
        panel_topright.setPreferredSize(new Dimension(0, 150)); // Đặt chiều cao cho panel_topright
        panel_right.add(panel_topright); // Thêm panel_topright vào panel_right

        // Thêm khoảng cách giữa panel_topright và panel_bottomright
        panel_right.add(Box.createRigidArea(new Dimension(0, 10))); // Tạo khoảng cách 10 pixel

        // Tạo panel_bottomright
        panel_bottomright = new JPanel();
        panel_bottomright.setBackground(new Color(51, 51, 51));
        panel_bottomright.setLayout(new BorderLayout());
        panel_right.add(panel_bottomright);

        // Hiển thị bảng đơn hàng mặc định
        DefaultTableModel tableModel = DonHang_DAO.getInstance().loadDataToTable("DonHang");
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô
            }
        };

        // Tùy chỉnh bảng
        table.setBackground(new Color(51, 51, 51));
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(51, 51, 51));
        table.getTableHeader().setForeground(Color.WHITE);

        // Thêm bảng vào JScrollPane
        JScrollPane scrollPane_table = new JScrollPane(table);
        scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
        scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        scrollPane_table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_table.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        // Thêm JScrollPane vào panel_bottomright
        panel_bottomright.add(scrollPane_table, BorderLayout.CENTER);

        // Hiển thị thông tin dòng đầu tiên trên panel_topright
        if (tableModel.getRowCount() > 0) {
            String maDon = tableModel.getValueAt(0, 0).toString();
            String maKH = tableModel.getValueAt(0, 1).toString();
            String maNV = tableModel.getValueAt(0, 2).toString();
            String diaChiDat = tableModel.getValueAt(0, 3).toString();
            String ngayDat = tableModel.getValueAt(0, 4).toString();
            String pttt = tableModel.getValueAt(0, 5).toString();
            String tinhTrang = tableModel.getValueAt(0, 6).toString();
            String tongTien = tableModel.getValueAt(0, 7).toString();

            showProductInPanelTopRight(maDon, maKH, maNV, diaChiDat, ngayDat, pttt, tinhTrang, tongTien);
        }

        // Cập nhật lại giao diện
        panel_bottomright.revalidate();
        panel_bottomright.repaint();



        btnXemDonHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa các thành phần hiện tại trong panel_bottomright
                panel_bottomright.removeAll();
                panel_topright.removeAll(); // Xóa nội dung cũ trong panel_topright
        
                // Tạo bảng và thêm vào JScrollPane
                DefaultTableModel tableModel = DonHang_DAO.getInstance().loadDataToTable("DonHang"); // Tải dữ liệu từ cơ sở dữ liệu
                JTable table = new JTable(tableModel) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Không cho phép chỉnh sửa các ô
                    }
                };
        
                // Tùy chỉnh bảng
                table.setBackground(new Color(51, 51, 51));
                table.setForeground(Color.WHITE);
                table.setGridColor(Color.BLACK);
                table.setRowHeight(30);
                table.getTableHeader().setBackground(new Color(51, 51, 51));
                table.getTableHeader().setForeground(Color.WHITE);
        
                // Thêm bảng vào JScrollPane
                JScrollPane scrollPane_table = new JScrollPane(table);
                scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
                scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        
                // Tùy chỉnh thanh cuộn
                scrollPane_table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane_table.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        
                // Thêm JScrollPane vào panel_bottomright
                panel_bottomright.add(scrollPane_table, BorderLayout.CENTER);
        
                table.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        int row = table.getSelectedRow(); // Lấy chỉ số hàng được chọn
                        if (row != -1) {
                            // Lấy dữ liệu từ hàng được chọn
                            String maDon = table.getValueAt(row, 0).toString();
                            String maKH = table.getValueAt(row, 1).toString();
                            String maNV = table.getValueAt(row, 2).toString();
                            String diaChiDat = table.getValueAt(row, 3).toString();
                            String ngayDat = table.getValueAt(row, 4).toString();
                            String pttt = table.getValueAt(row, 5).toString();
                            String tinhTrang = table.getValueAt(row, 6).toString();
                            String tongTien = table.getValueAt(row, 7).toString();
                
                            // Hiển thị dữ liệu trong panel_topright
                            showProductInPanelTopRight(maDon, maKH, maNV, diaChiDat, ngayDat, pttt, tinhTrang, tongTien);
                        }
                    }
                });
        
                // Cập nhật lại giao diện
                panel_bottomright.revalidate();
                panel_bottomright.repaint();
            }
        });

        btnXemChiTietDonHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa các thành phần hiện tại trong panel_bottomright
                panel_bottomright.removeAll();
                panel_topright.removeAll(); // Xóa nội dung cũ trong panel_topright
        
                // Tạo bảng và thêm vào JScrollPane
                DefaultTableModel tableModel = ChiTietDonHang_DAO.getInstance().loadDataToTable("ChiTietDonHang"); // Tải dữ liệu từ cơ sở dữ liệu
                JTable table = new JTable(tableModel) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Không cho phép chỉnh sửa các ô
                    }
                };
        
                // Tùy chỉnh bảng
                table.setBackground(new Color(51, 51, 51));
                table.setForeground(Color.WHITE);
                table.setGridColor(Color.BLACK);
                table.setRowHeight(30);
                table.getTableHeader().setBackground(new Color(51, 51, 51));
                table.getTableHeader().setForeground(Color.WHITE);
        
                // Thêm bảng vào JScrollPane
                JScrollPane scrollPane_table = new JScrollPane(table);
                scrollPane_table.getViewport().setBackground(new Color(51, 51, 51));
                scrollPane_table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        
                // Tùy chỉnh thanh cuộn
                scrollPane_table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane_table.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        
                // Thêm JScrollPane vào panel_bottomright
                panel_bottomright.add(scrollPane_table, BorderLayout.CENTER);

                // Hiển thị thông tin dòng đầu tiên trên panel_topright
            if (tableModel.getRowCount() > 0) {
                String mactdh = tableModel.getValueAt(0, 0).toString();
                String soLuong = tableModel.getValueAt(0, 1).toString();
                String thanhtien = tableModel.getValueAt(0, 2).toString();
                String dongia = tableModel.getValueAt(0, 3).toString();
                String madon = tableModel.getValueAt(0, 4).toString();
                String mactpn = tableModel.getValueAt(0, 5).toString();
                
                // Hiển thị thông tin trên panel_topright
                showProductsDetailInPanelTopRight(mactdh, soLuong, thanhtien, dongia, madon, mactpn);
            }

            table.addMouseListener(new java.awt.event.MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent evt) {
                    int row = table.getSelectedRow(); // Lấy chỉ số hàng được chọn
                    if (row != -1) {
                        // Lấy dữ liệu từ hàng được chọn
                        String mactdh = table.getValueAt(row, 0).toString();
                        String soLuong = table.getValueAt(row, 1).toString();
                        String thanhtien = table.getValueAt(row, 2).toString();
                        String dongia = table.getValueAt(row, 3).toString();
                        String madon = table.getValueAt(row, 4).toString();
                        String mactnh = table.getValueAt(row, 5).toString();
                
                        // Hiển thị dữ liệu trong panel_topright
                        showProductsDetailInPanelTopRight(mactdh, soLuong, thanhtien, dongia, madon, mactnh);
                    }
            }
        });
            
        
                // Cập nhật lại giao diện
                panel_bottomright.revalidate();
                panel_bottomright.repaint();
            }
        });

        


    


        setVisible(true);

    }

    

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void addHoverEffect(JButton button, Color hoverColor, Color defaultColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor); // Thay đổi màu nền khi hover
            }
    
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor); // Khôi phục màu nền khi chuột rời đi
            }
    
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBounds(button.getX() + 2, button.getY() + 2, button.getWidth() - 4, button.getHeight() - 4); // Giảm kích thước để tạo hiệu ứng nhấn
            }
    
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBounds(button.getX() - 2, button.getY() - 2, button.getWidth() + 4, button.getHeight() + 4); // Khôi phục kích thước khi thả chuột
            }
        });
    }

    private void handleButtonClick(JButton button) {
 
            if (activeButton != null) {
                // Khôi phục kích thước nút trước đó
                activeButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Font chữ bình thường
                activeButton.setPreferredSize(null); // Khôi phục kích thước mặc định
                activeButton.revalidate();
            }

            // Thay đổi kích thước nút hiện tại
            button.setFont(new Font("Arial", Font.BOLD, 18)); // Font chữ lớn hơn
            button.setPreferredSize(new Dimension(button.getWidth() + 20, button.getHeight() + 10)); // Tăng kích thước
            button.revalidate();

            // Cập nhật nút đang được nhấn
            activeButton = button;
        }

        private void addLabelAndTextField(JPanel parentPanel, String labelText, String valueText) {
            // Tạo JLabel cho tiêu đề
            JLabel label = new JLabel(labelText);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.PLAIN, 14)); // Font nhỏ hơn để phù hợp với kích thước
        
            // Tạo JTextField để hiển thị thông tin
            JTextField textField = new JTextField(valueText);
            textField.setEditable(false); // Không cho phép chỉnh sửa
            textField.setBackground(Color.BLACK);
            textField.setForeground(Color.WHITE);
            textField.setFont(new Font("Arial", Font.PLAIN, 14)); // Font nhỏ hơn để phù hợp với kích thước
            textField.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51))); // Viền màu tối
            textField.setPreferredSize(new Dimension(150, 25)); // Đặt kích thước cố định cho JTextField
        
            // Thêm JLabel và JTextField trực tiếp vào panel_topright
            parentPanel.add(label);
            parentPanel.add(textField);
        }


private void showProductInPanelTopRight(String MaDon, String MaKH, String MaNV, String DiaChiDat, String NgayDat, String PTTT, String TinhTrang, String TongTien) {
    // Xóa nội dung cũ trong panel_topright
    panel_topright.removeAll();

    JPanel panel_ChiTietDonHang = panel_topright;
    panel_ChiTietDonHang.setLayout(new GridLayout(3, 2, 10, 10)); // 4 hàng, 2 cột, khoảng cách 10px

    // Hiển thị thông tin chi tiết với JLabel và JTextField
    addLabelAndTextField(panel_ChiTietDonHang, "Mã Đơn Hàng:", MaDon);
    addLabelAndTextField(panel_ChiTietDonHang, "Mã Khách Hàng:", MaKH);
    addLabelAndTextField(panel_ChiTietDonHang, "Mã Nhân Viên:", MaNV);
    addLabelAndTextField(panel_ChiTietDonHang, "Địa Chỉ Đặt Hàng:", DiaChiDat);
    addLabelAndTextField(panel_ChiTietDonHang, "Ngày Đặt:", NgayDat);
    addLabelAndTextField(panel_ChiTietDonHang, "Phương Thức Thanh Toán:", PTTT);
    addLabelAndTextField(panel_ChiTietDonHang, "Tình Trạng:", TinhTrang);
    addLabelAndTextField(panel_ChiTietDonHang, "Tổng Tiền:", TongTien);

    // Cập nhật lại giao diện
    panel_ChiTietDonHang.revalidate();
    panel_ChiTietDonHang.repaint();
}

private void showProductsDetailInPanelTopRight(String mactdh, String soLuong, String thanhtien, String dongia, String madon, String mactnh) {    
    // Xóa nội dung cũ trong panel_topright
    panel_topright.removeAll();

    addLabelAndTextField(panel_topright, "Mã Chi Tiết Đơn Hàng:", mactdh);
    addLabelAndTextField(panel_topright, "Số Lượng:", soLuong);
    addLabelAndTextField(panel_topright, "Thành Tiền:", thanhtien);
    addLabelAndTextField(panel_topright, "Đơn Giá:", dongia);
    addLabelAndTextField(panel_topright, "Mã Đơn Hàng:", madon);
    addLabelAndTextField(panel_topright, "Mã Chi Tiết Phiếu Nhập:", mactnh);


    // Cập nhật lại giao diện
    panel_topright.revalidate();
    panel_topright.repaint();

        

}
}
