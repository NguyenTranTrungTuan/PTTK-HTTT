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
import qlkho.oop.NhaCungCap;

public class ChiTietPhieuNhapDAO implements DAOInterface<ChiTietPhieuNhap> {

    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public void insert(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "INSERT INTO ChiTietPhieuNhap VALUES ('" + obj.getMaCTPnhap() + "', '" + obj.getMaHDNhap()
                    + "', '"
                    + obj.getDonGia() + "','" + obj.getThanhTien() + "','" + obj.getSoLuongNhap() + "','"
                    + obj.getMaDT() + "')";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "UPDATE ChiTietPhieuNhap SET MaHDNhap = '" + obj.getMaHDNhap() + "', DonGia = '"
                    + obj.getDonGia() + "',ThanhTien = '"
                    + obj.getThanhTien() + "',SoLuongNhap = '"
                    + obj.getSoLuongNhap() + "',MaDT = '"
                    + obj.getMaDT() + "' WHERE MaCTPnhap = '"
                    + obj.getMaCTPnhap() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "DELETE FROM ChiTietPhieuNhap WHERE MaCTPnhap = '" + obj.getMaCTPnhap() + "'";
            st.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ChiTietPhieuNhap> selectAll() {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<ChiTietPhieuNhap>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietPhieuNhap");
            while (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
                list.add(ctpn);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ChiTietPhieuNhap selectById(ChiTietPhieuNhap obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st
                    .executeQuery("SELECT * FROM ChiTietPhieuNhap WHERE MaCTPnhap = '" + obj.getMaCTPnhap() + "'");
            if (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
                return ctpn;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChiTietPhieuNhap selectById1(String obj) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ChiTietPhieuNhap WHERE MaCTPnhap = ?");
            ps.setString(1, obj);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
                return ctpn;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ChiTietPhieuNhap> selectByCondtion(String condition) {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<ChiTietPhieuNhap>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ChiTietPhieuNhap WHERE MaHDNhap = ?");
            ps.setString(1, condition);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getString("MaCTPnhap"), rs.getString("MaHDNhap"),
                        rs.getInt("DonGia"),
                        rs.getInt("ThanhTien"), rs.getInt("SoLuongNhap"), rs.getString("MaDT"));
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
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);

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

    public DefaultTableModel loadDataToTableCondition(String mahdnhap) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ChiTietPhieuNhap WHERE MaHDNhap = '" + mahdnhap + "'");
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

    public String autoUpdateMaCTPN() {
        String maCTPN = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Pttkhttt;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TOP 1 MaCTPnhap FROM ChiTietPhieuNhap ORDER BY MaCTPnhap DESC");
            if (rs.next()) {
                maCTPN = rs.getString("MaCTPnhap");
                int newMaCTPN = Integer.parseInt(maCTPN.substring(4)) + 1;
                maCTPN = String.format("CTPN%03d", newMaCTPN);
            } else {
                maCTPN = "CTPN001";
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maCTPN;
    }

    public void exportPDF(String mapn, String mancc, String ngaynhap) throws IOException {
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        document.addPage(firstPage);

        ArrayList<NhaCungCap> ncclist = NhaCungCapDAO.getInstance().selectAll();
        String ten = "";
        for (NhaCungCap ncc : ncclist) {
            if (ncc.getMaNCC().equals(mancc)) {
                ten = ncc.getTenNCC();
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

        myTextClass.addSingleLineText("HÓA ĐƠN NHẬP HÀNG", 25, pageHeight - 150, font, 40, Color.BLACK);

        myTextClass.addSingleLineText("Nhà cung cấp: " + ten, 25, pageHeight - 250, font, 16, Color.BLACK);
        myTextClass.addSingleLineText("Ngày nhập: " + ngaynhap, 25, pageHeight - 274, font, 16, Color.BLACK);

        String invoiceNo = mapn;
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

        int[] cellWidths = { 70, 160, 100, 90, 120 };
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
        ArrayList<ChiTietPhieuNhap> ctpnlist = ChiTietPhieuNhapDAO.getInstance().selectByCondtion(mapn);
        for (ChiTietPhieuNhap ctpn : ctpnlist) {
            String maDT = ctpn.getMaDT();
            DienThoai dt = DienThoaiDAO.getInstance().selectById1(maDT);
            myTable.addCell(String.valueOf(stt), tableBodyColor);
            myTable.addCell(dt.getTenDt(), tableBodyColor);
            myTable.addCell(String.valueOf(ctpn.getDonGia()), tableBodyColor);
            myTable.addCell(String.valueOf(ctpn.getSoLuongNhap()), tableBodyColor);
            myTable.addCell(String.valueOf(ctpn.getThanhTien()), tableBodyColor);
            stt++;
            totaltmp += ctpn.getThanhTien();
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
        document.save("test.pdf");
        document.close();
    }

}
