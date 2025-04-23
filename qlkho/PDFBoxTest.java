package qlkho;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFBoxTest {
    public static void main(String[] args) throws Exception {
        PDDocument document = new PDDocument();
        document.save("test.pdf");
        document.close();
        System.out.println("PDF created successfully!");
    }
}