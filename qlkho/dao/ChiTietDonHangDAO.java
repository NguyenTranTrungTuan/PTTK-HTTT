package qlkho.dao;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import qlkho.MyTableClass;
import qlkho.MyTextClass;
import qlkho.oop.ChiTietDonHang;
import qlkho.oop.ChiTietPhieuNhap;
import qlkho.oop.DienThoai;
import qlkho.oop.KhachHang;

public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {

    public static ChiTietDonHangDAO getInstance() {
        return new ChiTietDonHangDAO();
    }

    @Override
    public void insert(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<ChiTietDonHang> selectAll() {
        ArrayList<ChiTietDonHang> list = new ArrayList<ChiTietDonHang>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietDonHang");
            while (rs.next()) {
                ChiTietDonHang ctpn = new ChiTietDonHang(rs.getString("MaCTDH"), rs.getInt("SoLuong"),
                        rs.getInt("ThanhTien"),
                        rs.getString("MaDon"), rs.getInt("DonGia"), rs.getString("MaCTPnhap"));
                list.add(ctpn);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ChiTietDonHang selectById(ChiTietDonHang obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public ArrayList<ChiTietDonHang> selectByCondtion(String condition) {
        ArrayList<ChiTietDonHang> list = new ArrayList<ChiTietDonHang>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ChiTietDonHang WHERE MaDon = ?");
            ps.setString(1, condition);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietDonHang ctpn = new ChiTietDonHang(rs.getString("MaCTDH"), rs.getInt("SoLuong"),
                        rs.getInt("ThanhTien"),
                        rs.getString("MaDon"), rs.getInt("DonGia"), rs.getString("MaCTPnhap"));
                list.add(ctpn);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DefaultTableModel loadDataToTable(String tableName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadDataToTable'");
    }

    public DefaultTableModel loadDataToTableCondition(String madhnhap) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietDonHang WHERE MaDon = '" + madhnhap + "'");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }
            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    public void exportPDF(String madh, String makh) throws IOException {
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        document.addPage(firstPage);

        String ten = "";
        String sdt = "";
        ArrayList<KhachHang> khlist = KhachHangDAO.getInstance().selectAll();
        for (KhachHang kh : khlist) {
            if (kh.getMaKH().equals(makh)) {
                ten = kh.getTenKH();
                sdt = kh.getSdt();
            }
        }

        SimpleDateFormat d_format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat t_format = new SimpleDateFormat("HH:mm");

        int pageWidth = (int) firstPage.getTrimBox().getWidth();
        int pageHeight = (int) firstPage.getTrimBox().getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
        MyTextClass myTextClass = new MyTextClass(document, contentStream);

        File fontFile = new File("C:\\Windows\\Fonts\\Arial.ttf");
        PDFont font = PDType0Font.load(document, fontFile);

        myTextClass.addSingleLineText("HÓA ĐƠN KHÁCH HÀNG", 25, pageHeight - 150, font, 40, Color.BLACK);

        myTextClass.addSingleLineText("Tên khách hàng: " + ten, 25, pageHeight - 250, font, 16, Color.BLACK);
        myTextClass.addSingleLineText("SDT: " + sdt, 25, pageHeight - 274, font, 16, Color.BLACK);

        String invoiceNo = madh;
        float textWidth = myTextClass.getTextWidth(invoiceNo, font, 16);
        myTextClass.addSingleLineText(invoiceNo, (int) (pageWidth - textWidth - 25), pageHeight - 250, font, 16,
                Color.BLACK);

        float dateTextWidth = myTextClass.getTextWidth("Date: " + d_format.format(new Date()), font, 16);
        myTextClass.addSingleLineText("Ngày: " + d_format.format(new Date()), (int) (pageWidth - dateTextWidth - 25),
                pageHeight - 274, font, 16,
                Color.BLACK);

        String time = t_format.format(new Date());
        float timeTextWidth = myTextClass.getTextWidth("Time: " + time, font, 16);
        myTextClass.addSingleLineText("Giờ: " + time, (int) (pageWidth - timeTextWidth - 25),
                pageHeight - 298, font, 16,
                Color.BLACK);

        MyTableClass myTable = new MyTableClass(document, contentStream);

        int[] cellWidths = { 70, 160, 120, 90, 100 };
        myTable.setTable(cellWidths, 30, 25, pageHeight - 350);
        myTable.setTableFont(font, 16, Color.BLACK);

        Color tableHeaderColor = new Color(240, 93, 11);
        Color tableBodyColor = new Color(219, 218, 198);

        myTable.addCell("STT", tableHeaderColor);
        myTable.addCell("Sản phẩm", tableHeaderColor);
        myTable.addCell("Giá", tableHeaderColor);
        myTable.addCell("Số lượng", tableHeaderColor);
        myTable.addCell("Thành tiền", tableHeaderColor);

        int stt = 1;
        int totaltmp = 0;
        ArrayList<ChiTietDonHang> ctdhlist = ChiTietDonHangDAO.getInstance().selectByCondtion(madh);
        for (ChiTietDonHang ctdh : ctdhlist) {
            ChiTietPhieuNhap ctpn = ChiTietPhieuNhapDAO.getInstance().selectById1(ctdh.getMaCTPN());
            String maDT = ctpn.getMaDT();
            DienThoai dt = DienThoaiDAO.getInstance().selectById1(maDT);
            myTable.addCell(String.valueOf(stt), tableBodyColor);
            myTable.addCell(dt.getTenDt(), tableBodyColor);
            myTable.addCell(String.valueOf(ctdh.getDonGia()), tableBodyColor);
            myTable.addCell(String.valueOf(ctdh.getSoLuong()), tableBodyColor);
            myTable.addCell(String.valueOf(ctdh.getThanhTien()), tableBodyColor);
            stt++;
            totaltmp += ctdh.getThanhTien();
        }

        myTable.addCell("", null);
        myTable.addCell("", null);
        myTable.addCell("Tạm tính", null);
        myTable.addCell("", null);
        myTable.addCell(String.valueOf(totaltmp), null);

        int tax = totaltmp * 5 / 100;
        myTable.addCell("", null);
        myTable.addCell("", null);
        myTable.addCell("Thuế VAT", null);
        myTable.addCell("5%", null);
        myTable.addCell(String.valueOf(tax), null);

        myTable.addCell("", null);
        myTable.addCell("", null);
        myTable.addCell("Tổng tiền", tableHeaderColor);
        myTable.addCell("", tableHeaderColor);
        myTable.addCell(String.valueOf(totaltmp - tax), tableHeaderColor);

        contentStream.close();
        document.save("HoaDonKhachHang.pdf");
        document.close();
    }

}
