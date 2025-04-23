package qlkho;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class CreateOrderReport {
    public static void main(String[] args) throws Exception {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        File fontFile = new File("C:\\Windows\\Fonts\\Arial.ttf");
        PDFont font = PDType0Font.load(document, fontFile);

        // Tiêu đề
        contentStream.setFont(font, 20);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 750);
        contentStream.showText("Báo Cáo Đơn Hàng");
        contentStream.endText();

        // Nội dung đơn hàng
        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700);
        contentStream.showText("Mã Đơn Hàng: DH001");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Khách Hàng: Nguyễn Văn A");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Sản Phẩm: iPhone 13");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Số Lượng: 2");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Tổng Tiền: 24,000,000 VND");
        contentStream.endText();

        contentStream.close();
        document.save("order_report.pdf");
        document.close();
    }
}