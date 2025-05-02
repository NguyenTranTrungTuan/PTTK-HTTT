package qlkho;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class thuchanhPDFBOX {
    public thuchanhPDFBOX() throws IOException {
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        document.addPage(firstPage);

        String ten = "Chadan Kumar";
        String sdt = "0987654321";

        SimpleDateFormat d_format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat t_format = new SimpleDateFormat("HH:mm");

        int pageWidth = (int) firstPage.getTrimBox().getWidth();
        int pageHeight = (int) firstPage.getTrimBox().getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
        MyTextClass myTextClass = new MyTextClass(document, contentStream);

        File fontFile = new File("C:\\Windows\\Fonts\\Arial.ttf");
        PDFont font = PDType0Font.load(document, fontFile);

        String[] contacDetails = new String[] { "123", "456" };
        myTextClass.addMultiLineText(contacDetails, 18, (int) (pageWidth - font.getStringWidth("123") / 1000 * 15 - 10),
                pageHeight - 25, font, 15, Color.BLACK);

        myTextClass.addSingleLineText("HÓA ĐƠN KHÁCH HÀNG", 25, pageHeight - 150, font, 40, Color.BLACK);

        myTextClass.addSingleLineText("Custiner Name: " + ten, 25, pageHeight - 250, font, 16, Color.BLACK);
        myTextClass.addSingleLineText("SDT: " + sdt, 25, pageHeight - 274, font, 16, Color.BLACK);

        String invoiceNo = "Invoice# 2536";
        float textWidth = myTextClass.getTextWidth(invoiceNo, font, 16);
        myTextClass.addSingleLineText(invoiceNo, (int) (pageWidth - textWidth - 25), pageHeight - 250, font, 16,
                Color.BLACK);

        float dateTextWidth = myTextClass.getTextWidth("Date: " + d_format.format(new Date()), font, 16);
        myTextClass.addSingleLineText("Date: " + d_format.format(new Date()), (int) (pageWidth - dateTextWidth - 25),
                pageHeight - 274, font, 16,
                Color.BLACK);

        String time = t_format.format(new Date());
        float timeTextWidth = myTextClass.getTextWidth("Time: " + time, font, 16);
        myTextClass.addSingleLineText("Time: " + time, (int) (pageWidth - timeTextWidth - 25),
                pageHeight - 298, font, 16,
                Color.BLACK);

        MyTableClass myTable = new MyTableClass(document, contentStream);

        int[] cellWidths = { 70, 160, 120, 90, 100 };
        myTable.setTable(cellWidths, 30, 25, pageHeight - 350);
        myTable.setTableFont(font, 16, Color.BLACK);

        Color tableHeaderColor = new Color(240, 93, 11);
        Color tableBodyColor = new Color(219, 218, 198);

        myTable.addCell("STT", tableHeaderColor);
        myTable.addCell("San pham", tableHeaderColor);
        myTable.addCell("Gia", tableHeaderColor);
        myTable.addCell("So luong", tableHeaderColor);
        myTable.addCell("Thanh tien", tableHeaderColor);

        myTable.addCell("1", tableBodyColor);
        myTable.addCell("a", tableBodyColor);
        myTable.addCell("10", tableBodyColor);
        myTable.addCell("2", tableBodyColor);
        myTable.addCell("20", tableBodyColor);

        myTable.addCell("1", tableBodyColor);
        myTable.addCell("a", tableBodyColor);
        myTable.addCell("10", tableBodyColor);
        myTable.addCell("2", tableBodyColor);
        myTable.addCell("20", tableBodyColor);

        myTable.addCell("1", tableBodyColor);
        myTable.addCell("a", tableBodyColor);
        myTable.addCell("10", tableBodyColor);
        myTable.addCell("2", tableBodyColor);
        myTable.addCell("20", tableBodyColor);

        myTable.addCell("1", tableBodyColor);
        myTable.addCell("a", tableBodyColor);
        myTable.addCell("10", tableBodyColor);
        myTable.addCell("2", tableBodyColor);
        myTable.addCell("20", tableBodyColor);

        myTable.addCell("", null);
        myTable.addCell("", null);
        myTable.addCell("Tam tinh", null);
        myTable.addCell("", null);
        myTable.addCell("200", null);

        myTable.addCell("", null);
        myTable.addCell("", null);
        myTable.addCell("Khuyến mãi dip lễ 30/4", null);
        myTable.addCell("10%", null);
        myTable.addCell("22", null);

        myTable.addCell("", null);
        myTable.addCell("", null);
        myTable.addCell("Tong tien", tableHeaderColor);
        myTable.addCell("", tableHeaderColor);
        myTable.addCell("90", tableHeaderColor);

        contentStream.close();
        document.save("test.pdf");
        document.close();
    }

    public static void main(String[] args) throws IOException {
        new thuchanhPDFBOX();
    }

}